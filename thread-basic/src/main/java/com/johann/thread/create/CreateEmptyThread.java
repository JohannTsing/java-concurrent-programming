package com.johann.thread.create;

import com.google.common.collect.BiMap;

import java.util.Arrays;
import java.util.BitSet;

/**
 * @author: Johann
 */
public class CreateEmptyThread  {

    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.printf("线程名称：%s%n",thread.getName());
        System.out.printf("线程ID：%d%n",thread.getId());
        System.out.printf("线程状态：%s%n",thread.getState());
        System.out.printf("线程优先级：%d%n",thread.getPriority());
        System.out.println(Thread.currentThread().getName()+" 运行结束");
        thread.start();
    }

}
