package com.johann.thread.create;

import com.johann.utils.ThreadUtils;

import java.util.List;
import java.util.concurrent.*;

/**通过线程池创建线程
 * @author :  Johann
 */
public class CreateByThreadPool {

    public static int THREAD_NUM = 2;

    public static final int MAX_TURN = 10;

    // 创建固定大小的线程池
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_NUM);

    // 创建实现Runnable接口的执行目标
    static class RunnableTarget implements Runnable {
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.printf("===【%s】, 第 %d 次运行. === %n", ThreadUtils.getCurrentThreadName(),i);
                try{
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // 创建实现Callable接口的执行目标
    static class CallableTarget implements Callable<Long> {
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            System.out.printf("$$$$$【%s】 线程开始运行. $$$$$%n", ThreadUtils.getCurrentThreadName());
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.printf("$$$$$【%s】, 第 %d 次运行. $$$$$%n", ThreadUtils.getCurrentThreadName(),i);
                Thread.sleep(50);
            }
            System.out.printf("$$$$$【%s】 线程运行结束. $$$$$%n", ThreadUtils.getCurrentThreadName());
            return System.currentTimeMillis() - start;
        }
    }

    public static void main(String[] args){
        threadPool.execute(new RunnableTarget());

        threadPool.execute(() -> {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.printf("#####【%s】, 第 %d 次运行. #####%n", ThreadUtils.getCurrentThreadName(),i);
                try{
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Future<Long> future = threadPool.submit(new CallableTarget());
        try{
            Long result = future.get(1L, TimeUnit.SECONDS);
            System.out.printf("【异步任务】, 耗时 %d 毫秒%n", result);
        }catch (TimeoutException | InterruptedException | ExecutionException e){
            if (e instanceof TimeoutException){
                System.err.println("【异步任务】, 超时");
            }else if (e instanceof InterruptedException){
                System.err.println("【异步任务】, 被中断");
            }else {
                System.err.println("【异步任务】, 异常");
            }
        }

        // 如果关闭后所有任务都已完成，则返回true。请注意，除非先调用shutdown或shutdownNow，否则isTerminated永远不会为真。
        // 返回值：如果关闭后所有任务都已完成，则为true
        System.out.println("***** threadPool.isTerminated(): "+threadPool.isTerminated()+" ***** ");
        // 如果该执行器已关闭，则返回true。
        // 返回值：如果该执行器已关闭，则返回true
        System.out.println("***** threadPool.isShutdown(): "+threadPool.isShutdown()+" ***** ");
        System.out.println("&&&&& 调用 shutdown() &&&&& ");

        // 启动有序关机，执行之前提交的任务，但不接受新任务。如果已经关闭，则调用不会产生额外影响。
        // 此方法不会等待先前提交的任务完成执行。请使用awaitTermination来等待。
        threadPool.shutdown();

        System.out.println("***** threadPool.isTerminated(): "+threadPool.isTerminated()+" ***** ");
        System.out.println("***** threadPool.isShutdown(): "+threadPool.isShutdown()+" ***** ");
        try {
            System.out.println("***** threadPool.isTerminated(): "+threadPool.isTerminated()+" ***** ");
            System.out.println("***** threadPool.isShutdown(): "+threadPool.isShutdown()+" ***** ");
            System.out.println("&&&&& 调用 awaitTermination(10, TimeUnit.SECONDS) &&&&& ");
            // 阻塞，直到关闭请求发出后所有任务都已执行完毕，或超时发生，或当前线程被中断，以先发生者为准。
            // 形参：timeout- 等待的最长时间 unit- 超时参数的时间单位
            // 返回值：如果该执行器已终止，则返回值为true；如果在终止前超时，则返回值为false。
            // 抛出：InterruptedException- 如果在等待过程中被中断
            if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("&&&&& 调用 shutdownNow() &&&&& ");
                threadPool.shutdownNow();
                if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("线程池没有正确关闭");
                }
            }
            System.out.println("***** threadPool.isTerminated(): "+threadPool.isTerminated()+" ***** ");
            System.out.println("***** threadPool.isShutdown(): "+threadPool.isShutdown()+" ***** ");
        } catch (InterruptedException ie) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
