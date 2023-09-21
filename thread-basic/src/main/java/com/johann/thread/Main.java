package com.johann.thread;
/**
 * @author: Johann
 */
public class Main {
    static int i= 0;

    public static void main(String[] args) throws InterruptedException {
        new Main().test();
        Thread.sleep(1000*10);
        System.out.println(i);
    }
    // 编写方法：对一个共享的变量 i，初始值是 0。使用 20 个线程，每个线程自增 10000次，验证 i 最终的结果。

    public void test() {
        for (int j = 0; j < 20; j++) {
            new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    i++;
                    //System.out.println(Thread.currentThread().getName() + " 执行 i++后:" + i);
                }
            }).start();
        }
    }

}