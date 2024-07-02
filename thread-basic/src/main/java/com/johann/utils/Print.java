package com.johann.utils;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Print {
    // 创建一个固定大小的线程池
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(5);
    /**
     * 打印到控制台
     * @param o
     */
    public static void toConsole(Object o) {
        String msg = "["+ThreadUtils.getCurrentThreadName()+"]: "+o;
        // 提交线程池进行一步输出，使得输出过程不影响当前线程的执行
        // 异步输出的好处：不会造成输出乱序，也不会造成当前线程阻塞
        threadPool.execute(() -> {
            synchronized (System.out){
                System.out.println(msg);
            }
        });
    }
    /**
     * 关闭线程池
     */
    public static void shutdownThreadPool() {
        //System.out.println("["+ThreadUtils.getCurrentThreadName()+"]: Print.shutdownThreadPool");
        //threadPool.shutdown();
        try {
            System.out.println("["+ThreadUtils.getCurrentThreadName()+"]: Print.awaitTermination(5, TimeUnit.SECONDS)");
            // 等待其他异步线程执行完毕
            if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("["+ThreadUtils.getCurrentThreadName()+"]: Print.threadPool.shutdownNow()");
                // 如果超时，强制关闭线程池
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("["+ThreadUtils.getCurrentThreadName()+"]: 【Exception】 Print.threadPool.shutdownNow()");
            // 处理异常
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 等待 seconds 秒后，关闭线程池
     */
    public static void shutdownThreadPool(long seconds) {
        //System.out.println("["+ThreadUtils.getCurrentThreadName()+"]: Print.shutdownThreadPool");
        //threadPool.shutdown();
        try {
            System.out.println("["+ThreadUtils.getCurrentThreadName()+"]: Print.awaitTermination("+seconds+", TimeUnit.SECONDS)");
            // 等待其他异步线程执行完毕
            if (!threadPool.awaitTermination(seconds, TimeUnit.SECONDS)) {
                System.out.println("["+ThreadUtils.getCurrentThreadName()+"]: Print.threadPool.shutdownNow()");
                // 如果超时，强制关闭线程池
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("["+ThreadUtils.getCurrentThreadName()+"]: 【Exception】 Print.threadPool.shutdownNow()");
            // 处理异常
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
