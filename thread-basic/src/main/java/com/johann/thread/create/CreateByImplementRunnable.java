package com.johann.thread.create;

import cn.hutool.core.thread.ThreadUtil;
import com.johann.utils.ThreadUtils;

/** 通过实现Runnable接口创建线程
 * @author: Johann
 */
public class CreateByImplementRunnable {

    // 轮次
    public static final int MAX_TURN = 5;
    // 线程编号
    public static int threadNo = 1;

    /**
     * 实现Runnable接口
     */
    static class RunTarget implements Runnable {
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
//        for (int i = 0; i < 5; i++) {
//            thread = new Thread(new RunTarget(),"RunnableThread-"+(threadNo++));
//            thread.start();
//        }

        // 使用匿名内部类的方式创建线程
        for (int i = 0; i < 5; i++) {
            thread = new Thread(() -> {
                for  (int i1 = 0; i1 < MAX_TURN; i1++) {
                    System.out.println("【线程名称："+ ThreadUtils.getCurrentThreadName()+"~~线程ID："+ThreadUtils.getCurrentThreadId()+"】, 轮次: "+ i1);
                }
            },"RunnableThread-"+(threadNo++));
            thread.start();
        }
        System.out.println("【线程名称："+ThreadUtils.getCurrentThreadName()+"~~线程ID："+ThreadUtils.getCurrentThreadId()+"】, 运行结束");
    }
}
