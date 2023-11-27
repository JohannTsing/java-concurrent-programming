package com.johann.thread.create;

import com.johann.utils.ThreadUtils;

/**
 * 通过继承Thread类创建线程
 * @author: Johann
 */
public class CreateByExtendsThread {

    // 轮次
    public static final int MAX_TURN = 5;
    // 线程编号
    public static int threadNo = 1;

    /**
     * 继承Thread类创建线程
     */
    static class DemoThread extends Thread {
        public DemoThread() {
            super("DemoThread-" + (threadNo++));
        }
        @Override
        public void run() {
            for  (int i = 0; i < MAX_TURN; i++) {
                System.out.println("【线程名称："+ ThreadUtils.getCurrentThreadName()+"~~线程ID："+ThreadUtils.getCurrentThreadId()+"】, 轮次: "+i);
            }
            System.out.println("【线程名称："+ThreadUtils.getCurrentThreadName()+"~~线程ID："+ThreadUtils.getCurrentThreadId()+"】, 运行结束");
        }
    }

    public static void main(String[] args) {
        Thread thread = null;

        for (int i = 0; i < 4; i++) {
            thread = new DemoThread();
            thread.start();
        }
        System.out.println("【线程名称："+ThreadUtils.getCurrentThreadName()+"~~线程ID："+ThreadUtils.getCurrentThreadId()+"】, 运行结束");
    }
}
