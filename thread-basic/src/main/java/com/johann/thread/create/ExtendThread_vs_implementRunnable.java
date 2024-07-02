package com.johann.thread.create;

import cn.hutool.core.thread.ThreadUtil;
import com.johann.utils.Print;
import com.johann.utils.ThreadUtils;

import java.util.concurrent.atomic.AtomicInteger;

/** 继承Thread类和实现Runnable接口的对比
 * @author: Johann
 */
public class ExtendThread_vs_implementRunnable {

    // 商品数量
    public static final int MAX_AMOUNT = 100;
    // 共享的销售数量,如果要保证线程安全，需使用原子类
    public static AtomicInteger SHARE_AMOUNT = new AtomicInteger(MAX_AMOUNT);

    public static void main(String[] args) {
        //salesThreadRun();
        //salesThreadShareAmountRun();
        salesRunnableRun();
        Print.shutdownThreadPool(3);
    }
    /**
     * 继承Thread类的销售线程，每个线程有自己单独的销售数量
     */
    static class SalesThread extends Thread{
        private int goodsAmount = MAX_AMOUNT;
        public SalesThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            for (int i = 0; i <= MAX_AMOUNT; i++) {
                if (goodsAmount > 0) {
                    Print.toConsole(" 售出一件商品，剩余商品为: " + (--goodsAmount));
                    ThreadUtils.parkMillis(15);
                }else {
                    Print.toConsole(" 商品售罄！");
                    break;
                }
            }
            Print.toConsole(" 已售罄. 运行结束！");
        }
    }

    /** 继承Thread类的销售线程，每个线程有自己单独的销售数量 */
    public static void salesThreadRun() {
        Print.toConsole("===== 继承Thread类的销售线程，每个线程有自己单独的销售数量 =====");
        for (int i = 1; i <= 5; i++) {
            new SalesThread("销售员-" + i).start();
        }
    }

    /**
     * 继承Thread类的销售线程，每个线程共享一个销售数量
     */
    static class SalesThreadShareAmount extends Thread{
        public SalesThreadShareAmount(String name) {
            super(name);
        }
        @Override
        public void run() {
            for (int i = 0; i <= MAX_AMOUNT; i++) {
                if (SHARE_AMOUNT.get() > 0 && SHARE_AMOUNT.compareAndSet(SHARE_AMOUNT.get(), SHARE_AMOUNT.get() - 1)) {
                    Print.toConsole(" 售出一件商品，剩余商品为: " + (SHARE_AMOUNT.get()));
                    ThreadUtils.parkMillis(15);
                }else {
                    if (SHARE_AMOUNT.get() <= 0) {
                        Print.toConsole(" 商品售罄！");
                        break;
                    }else {
                        Print.toConsole(" ----- [ i="+i+"]空转----- ");
                    }
                }
            }
            Print.toConsole(" 已售罄. 运行结束！");
        }
    }

    public static void salesThreadShareAmountRun() {
        Print.toConsole("===== 继承Thread类的销售线程，每个线程共享一个销售数量 =====");
        for (int i = 1; i <= 5; i++) {
            new SalesThreadShareAmount("销售员-" + i).start();
        }
    }



    /**
     * 实现Runnable接口的销售线程，每个线程共享一个销售数量
     */
    static class SalesRunnable implements Runnable{
        //private final AtomicInteger goodsAmount = new AtomicInteger(MAX_AMOUNT);
        @Override
        public void run() {
            for (int i = 0; i <= MAX_AMOUNT; i++) {
                // compareAndSet(预期值,更新值). 如果AtomicInteger中的当前值==预期值，则自动将该值设置为给定的更新值。
                /*if (goodsAmount.get() > 0 && goodsAmount.compareAndSet(goodsAmount.get(), goodsAmount.get() - 1)) {
                    System.out.println(ThreadUtils.getCurrentThreadName() + " 售出一件商品，剩余商品为: " + goodsAmount.get());
                    sleepMillSeconds(15);
                }else {
                    System.out.println(ThreadUtils.getCurrentThreadName() + " 商品售罄！");
                    break;
                }*/

                int currentAmount;
                do {
                    // 取出当前值
                    currentAmount = SHARE_AMOUNT.get();
                    if (currentAmount <= 0) {
                        Print.toConsole(" 商品售罄！");
                        break;
                    }
                // compareAndSet(预期值,更新值). 如果预期值等于当前值,则将该值设置为给定的更新值,并返回true.
                // 前方添加一个 !，只有预期值不等于当前值时，才会再次循环执行 do 中的代码块，此时会重新获取新的当前值
                } while (!SHARE_AMOUNT.compareAndSet(currentAmount, currentAmount - 1));

                if (currentAmount > 0) {
                    Print.toConsole(" 售出一件商品，剩余商品为: " + (currentAmount - 1));
                    ThreadUtils.parkMillis(15);
                } else {
                    Print.toConsole(" ----- [ i="+i+"]空转----- ");
                    break;
                }
            }
            Print.toConsole(" 已售罄. 运行结束！");
        }
    }

    /**
     * 实现Runnable接口的销售线程，每个线程共享一个销售数量
     */
    public static void salesRunnableRun() {
        Runnable target = new SalesRunnable();
        Print.toConsole("===== 实现Runnable接口的销售线程，每个线程共享一个销售数量 =====");
        for (int i = 1; i <= 5; i++) {
            new Thread(target, "销售员-" + i).start();
        }
    }
}
