package com.johann.thread.basic;

import com.johann.utils.Print;
import com.johann.utils.ThreadUtils;

/**
 * 线程睡眠测试
 * `sleep`的作用是让目前正在执行的线程休眠，让CPU去执行其他的任务。从线程状态来说，就是从【执行状态】变成【限时阻塞状态】。
 *
 * 导出线程状态信息：
 *     jps;
 *     jstack pid
 * @author Johann
 */
public class SleepDemo {

    public static final int SLEEP_TIME = 5000;

    public static final int MAX_TURN = 50;

    static class SleepThread extends Thread {
        static int threadNo = 0;
        public SleepThread() {
            super("SleepThread"+(++threadNo));
        }
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                Print.toConsole("--睡眠轮次-- "+i);
                ThreadUtils.sleep(SLEEP_TIME);
            }
            Print.toConsole("运行结束");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SleepThread().start();
        }
        Print.toConsole("运行结束");
    }


}
