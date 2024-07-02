package com.johann.thread.basic;
import com.johann.utils.Print;
import com.johann.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.List;
import static com.johann.utils.ThreadUtils.parkMillis;

public class StateDemo {

    public static final long MAX_TURN = 5L;

    // 线程编号
    public static int threadNo = 0;

    static List<Thread> threadList = new ArrayList<>();

    /**
     * 输出线程状态
     */
    private static void printThreadState(String threadName,long turn){
        for (Thread thread : threadList){
            Print.toConsole("由当前线程【"+threadName+"】 第 ["+turn+"] 轮启动的打印列表"+",【"+thread.getName() + "】 >>>>> 状态为： " + thread.getState());
        }
    }

    /**
     * 添加线程到线程列表
     * @param thread
     */
    private static void addStateThread(Thread thread){
        threadList.add(thread);
    }

    static class StateThread extends Thread {
        public StateThread(){
            super("stateThread-"+(++threadNo));
            addStateThread(this);
        }

        public void run() {
            Print.toConsole(getName()+ " -- 状态为： " + getState());
            long turn = 0;
            while (turn < MAX_TURN){
                // 线程睡500ms
                parkMillis(500);
                Print.toConsole("【"+getName()+"】 执行的轮次: "+turn);
                // 输出线程状态
                printThreadState(getName(),turn);
                turn++;
            }
            Print.toConsole(getName()+" -- 线程运行结束");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        // 将Main线程加入线程列表
        addStateThread(Thread.currentThread());

        // 新建3个线程
//        for (int i = 0; i < 3; i++){
//
//        }
        Thread thread1 = new StateThread();
        Print.toConsole(thread1.getName()+","+thread1.getId()+", 状态为： " + thread1.getState());
        Thread thread2 = new StateThread();
        Print.toConsole(thread2.getName()+","+thread2.getId()+", 状态为： " + thread2.getState());
        Thread thread3 = new StateThread();
        Print.toConsole(thread3.getName()+","+thread3.getId()+", 状态为： " + thread3.getState());

        // 启动线程1
        thread1.start();
        // 主线程睡500ms
        parkMillis(500);
        // 主线程睡500ms后，启动线程2
        thread2.start();
        // 主线程睡500ms
        parkMillis(500);
        // 主线程睡500ms后，启动线程3
        thread3.start();

        // 主线程睡100ms
        parkMillis(2000);
        Print.toConsole(ThreadUtils.getCurrentThreadName()+","+ThreadUtils.getCurrentThreadId()+" -- 线程运行结束");
        Print.shutdownThreadPool(3);
    }
}
