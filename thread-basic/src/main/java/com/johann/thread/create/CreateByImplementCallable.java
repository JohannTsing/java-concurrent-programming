package com.johann.thread.create;

import cn.hutool.core.thread.ThreadUtil;
import com.johann.utils.ThreadUtils;

import java.util.concurrent.*;

/**通过实现Callable接口创建线程
 * @author: Johann
 */
public class CreateByImplementCallable {

    public static final int COMPUTE_TIMES = 100000000;

    public static void main(String[] args) throws InterruptedException {
        ReturnTask task = new ReturnTask();
        FutureTask<Long> futureTask = new FutureTask<Long>(task);
        Thread thread = new Thread(futureTask,"returnTaskThread");
        thread.start();
        Thread.sleep(1000*2);

        System.out.println("【"+ThreadUtils.getCurrentThreadName()+"】 做点别的事情。");

        for (int i = 0; i < COMPUTE_TIMES/2; i++) {
            int j = i * 10000;
        }

        System.out.println("【"+ThreadUtils.getCurrentThreadName()+"】 获取并发任务的结果");

        try {
            long timeout = 3L;
            System.out.println("【"+ThreadUtils.getCurrentThreadName()+"】 尝试获取线程执行结果,获取结果的超时时间为 "+ timeout + " 秒");
            System.out.println(thread.getName()+" 线程占用时间: "+futureTask.get(timeout, TimeUnit.SECONDS));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println("【"+ThreadUtils.getCurrentThreadName()+"】 尝试获取结果失败");
            throw new RuntimeException(e);
        }

        System.out.println(ThreadUtils.getCurrentThreadName()+" 运行结束");
    }


    // 实现Callable接口
    static class ReturnTask implements Callable<Long> {
        public Long call() throws Exception {
            long startTime = System.currentTimeMillis();
            System.out.println("【"+ThreadUtils.getCurrentThreadName()+"】 线程【开始运行】");
            Thread.sleep(1000*5);

            long sum = 0;
            for (int i = 0; i < COMPUTE_TIMES; i++) {
                sum = i * 10000L;
            }
            System.out.println("【"+ThreadUtils.getCurrentThreadName()+"】 线程【运行结束】");
            return System.currentTimeMillis()-startTime;
        }
    }
}
