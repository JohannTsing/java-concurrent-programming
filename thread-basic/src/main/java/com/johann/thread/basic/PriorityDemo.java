package com.johann.thread.basic;

import com.johann.utils.Print;

/*
 * 测试线程优先级
 * @author Johann
 */
public class PriorityDemo {

    static class PriorityThread extends Thread {
        static int threadNo = 1;
        public PriorityThread() {
            super("thread-"+threadNo);
            threadNo++;
        }

        public long opportunities = 0;
        @Override
        public void run() {
            while(true) {
                opportunities++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PriorityThread [] threads = new PriorityThread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new PriorityThread();
            // 设置优先级
            threads[i].setPriority(i+1);
        }

        for (PriorityThread value : threads) {
            value.start();
        }

        Thread.sleep(1000);

        for (PriorityThread priorityThread : threads) {
            // 停止线程
            priorityThread.stop();
        }

        for (PriorityThread thread : threads) {
            Print.toConsole(thread.getName() + " 优先级：" + thread.getPriority() + " 竞争次数：" + thread.opportunities);
        }
        Print.shutdownThreadPool(2);
    }

}
