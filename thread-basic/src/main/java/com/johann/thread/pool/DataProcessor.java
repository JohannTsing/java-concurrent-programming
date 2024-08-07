package com.johann.thread.pool;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 首先，我们需要创建一个ThreadPoolExecutor实例。线程池的核心线程数和最大线程数都设置为m，其中m = N/1000 + 1。我们还需要为线程池提供一个阻塞队列，用于存储等待执行的任务。
 * 此外，我们还需要提供一个ThreadFactory，用于创建新的线程，以及一个RejectedExecutionHandler，用于处理无法执行的任务
 *
 * 然后，我们将List中的每个元素封装为一个Runnable任务，并提交给线程池执行。每个任务都会调用dataProcessing方法处理对应的OrigionData对象
 *
 * 最后，我们需要确保所有任务都已完成，然后关闭线程池。这是一个重要的步骤，因为如果不关闭线程池，程序可能会一直运行，而不会结束
 *
 * 在这个代码中，我们使用ThreadPoolExecutor.AbortPolicy作为拒绝策略，这意味着如果任务无法执行，将会抛出一个RejectedExecutionException异常
 * 在实际应用中，您可能需要根据具体情况选择合适的拒绝策略。
 *
 * 此外，我们使用executor.shutdown()和executor.awaitTermination()方法来关闭线程池并等待所有任务完成。
 * 如果在指定的时间内任务没有完成，我们将调用executor.shutdownNow()方法尝试取消所有正在执行的任务，并返回尚未开始执行的任务列表
 *
 */
public class DataProcessor {
    private static final int N = 10000; // 假设N为10000
    private static final int M = N / 1000 + 1;

    public void doTask(){
        List<OrigionData> firstProcessData = firstProcess();
        // 后续处理
    }

    /**
     * 数据处理的第一步
     * @return
     */
    public List<OrigionData> firstProcess(){
        // 填充原始数据，步骤省略...
        List<OrigionData> originDataList = new ArrayList<>();
        List<OrigionData> processedData = new ArrayList<>();
        // 多线程处理数据
        List<Future<?>> futures = process(originDataList);
        for (Future<?> future:futures) {
            try {
                processedData.add((OrigionData) future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        // 将处理后的数据返回
        return processedData;
    }


    /**
     * 数据处理
     * @param originDataList
     */
    public List<Future<?>> process(List<OrigionData> originDataList) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                M, M, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        List<Future<?>> futures = new ArrayList<>();

        for (OrigionData data : originDataList) {
            // 无法阻塞线程
//            executor.execute(() -> {
//                dataProcessing(data);
//            });

            // 可以阻塞主线程
            futures.add(
                    executor.submit(() -> dataProcessing(data))
            );
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Thread pool not closing properly");
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        return futures;
    }

    public OrigionData dataProcessing(OrigionData data) {
        // 在这里处理数据
        return data;
    }
}

class OrigionData {
    // 假设这是一个简单的类
}
