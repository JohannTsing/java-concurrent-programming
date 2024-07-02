package com.johann.utils;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: Johann
 */
public class ThreadUtils {

    /**
     * 获取当前线程名称
     * @return
     */
    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * 获取当前线程ID
     * @return
     */
    public static long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 阻塞当前线程，直到时间到达
     * @param millis
     */
    public static void parkMillis(long millis) {
        LockSupport.parkNanos(millis * 1000000L);
    }
}
