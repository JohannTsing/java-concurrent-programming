package com.johann;

/**
 * 逻辑门
 */
public class LogicGate {

    /**
     * 与门（AND gate）：当所有输入都为1时，输出为1；否则输出为0。
     * @param a
     * @param b
     * @return
     */
    public static int andGate(int a, int b) {
        return a & b;
    }

    /**
     * 或门（OR gate）：当任一输入为1时，输出为1；否则输出为0。
     * @param a
     * @param b
     * @return
     */
    public static int orGate(int a, int b) {
        return a | b;
    }

    /**
     * 非门（NOT gate）：对输入取反。
     * @param a
     * @return
     */
    public static int notGate(int a) {
        return ~a;
    }

    /**
     * 与非门（NAND gate）：与门的输出取反（当所有输入都为1时，输出为0；否则输出为1）
     * @param a
     * @param b
     * @return
     */
    public static int nandGate(int a, int b) {
        return ~(a & b);
        // return (a == 1 && b == 1) ? 0 : 1;
    }

    /**
     * 或非门（NOR gate）：或门的输出取反（当所有输入都为0时，输出为1；否则输出为0）
     * @param a
     * @param b
     * @return
     */
    public static int norGate(int a, int b) {
        return ~(a | b);
        // return (a == 0 && b == 0)? 1 : 0;
    }

    /**
     * 异或门（XOR gate）：当输入不同时，输出为1；否则输出为0。
     * @param a
     * @param b
     * @return
     */
    public static int xorGate(int a, int b) {
        return a ^ b;
    }

    /**
     * 同或门（XNOR gate）：又称异或非门，异或门的输出取反（当输入不同时，输出为0；否则输出为1）
     * @param a
     * @param b
     * @return
     */
    public static int xnorGate(int a, int b) {
        return ~(a ^ b);
    }

    /**
     * 三态门（tri-state gate）是一种逻辑门电路，其输出可以处于三种不同的状态：
     *   高电平、低电平和高阻态。高电平表示逻辑值1，低电平表示逻辑值0，而高阻态表示输出与输入之间存在断开，相当于电路中的断路状态。
     * <p>
     * 三态门通常由一个输入端和一个输出端组成，并带有一个控制端。
     *   当控制端为有效状态时，三态门的输入信号会传递到输出端；当控制端为无效状态时，输出端进入高阻态，即断路状态。
     */
    public static int triStateGate() {
        return 0;
    }

}
