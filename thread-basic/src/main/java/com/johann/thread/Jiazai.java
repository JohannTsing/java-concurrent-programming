package com.johann.thread;

/**
 * @author: Johann
 */
public class Jiazai {

    public static void main(String[] args) {

        // 静态内部类的实例化依赖于外部类本身的定义，与外部类的实例无关
        //Jiazai.PrivateStaticInnerClass privateStaticInnerClass = new Jiazai.PrivateStaticInnerClass();
        //Jiazai.StaticInnerClass staticInnerClass = new Jiazai.StaticInnerClass();

        StaticInnerClass.staticInnerClassStaticMethod();
        PrivateStaticInnerClass.privateStaticInnerClassStaticMethod();

        Jiazai jiazai = new Jiazai();
        // 普通内部类的实例化依赖外部类的实例
        Jiazai.InnerClass innerClass = jiazai.new InnerClass();
    }

    // 加载顺序：静态变量 -> 静态代码块 -> 构造方法 -> 普通变量 -> 普通代码块 -> 普通方法

    // 静态变量
    static Integer STATIC_VAR = 1;
    // 成员变量
    Integer MEMBER = 1;

    // 静态代码块
    static {
        System.out.println("静态代码块");
    }
    // 普通代码块
    {
        System.out.println("普通代码块");
    }
    public Jiazai(){
        System.out.println("构造方法");
    }
    // 静态方法
    public static void staticMethod(){
        System.out.println("静态方法");
    }
    // 普通方法
    public void method(){
        System.out.println("普通方法");
    }

    // 静态内部类
    static class StaticInnerClass{

        // 静态内部类的静态变量
        static Integer STATIC_INNER_CLASS_STATIC_VAR = 1;
        // 静态内部类的成员变量
        Integer STATIC_INNER_CLASS_MEMBER = 1;

        // 静态内部类的静态代码块
        static {
            System.out.println("静态内部类的静态代码块");
        }
        // 静态内部类的普通代码块
        {
            System.out.println("静态内部类的普通代码块");
        }
        // 静态内部类的静态方法
        public static void staticInnerClassStaticMethod(){
            System.out.println("静态内部类的静态方法");
        }
        // 静态内部类的普通方法
        public void staticInnerClassMethod(){
            System.out.println("静态内部类的普通方法");
        }
    }

    // 私有静态内部类
    private static class PrivateStaticInnerClass{

        // 私有静态内部类的静态变量
        private static Integer PRIVATE_STATIC_INNER_CLASS_STATIC_VAR = 1;
        // 私有静态内部类的成员变量
        private Integer PRIVATE_STATIC_INNER_CLASS_MEMBER = 1;

        // 私有静态内部类的静态代码块
        static {
            System.out.println("私有静态内部类的静态代码块");
        }
        // 私有静态内部类的普通代码块
        {
            System.out.println("私有静态内部类的普通代码块");
        }
        // 私有静态内部类的静态方法
        public static void privateStaticInnerClassStaticMethod(){
            System.out.println("私有静态内部类的静态方法");
        }
        // 私有静态内部类的普通方法
        public void privateStaticInnerClassMethod(){
            System.out.println("私有静态内部类的普通方法");
        }

    }

    // 普通内部类
    class InnerClass{

        // 普通内部类的静态变量
        // 语言级别 '8' 不支持 内部类中的 static 声明
        // static Integer INNER_CLASS_STATIC_VAR = 1;

        // 普通内部类的成员变量
        Integer INNER_CLASS_MEMBER = 1;

        // 普通内部类的静态代码块
        // 语言级别 '8' 不支持 内部类中的 static 声明
//        static {
//            System.out.println("普通内部类的静态代码块");
//        }

        // 普通内部类的普通代码块
        {
            System.out.println("普通内部类的普通代码块");
        }
        // 普通内部类的静态方法
        // 语言级别 '8' 不支持 内部类中的 static 声明
//        public static void innerClassStaticMethod(){
//            System.out.println("普通内部类的静态方法");
//        }
        // 普通内部类的普通方法
        public void innerClassMethod(){
            System.out.println("普通内部类的普通方法");
        }
    }

}
