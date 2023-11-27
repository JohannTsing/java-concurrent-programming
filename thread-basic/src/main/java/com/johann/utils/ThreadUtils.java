package com.johann.utils;

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
}
