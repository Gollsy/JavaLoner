package com.gollsy.alogrithm.tree;

import com.gollsy.alogrithm.entity.tree.BiNode;
import com.gollsy.alogrithm.entity.tree.BiTree;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Admin
 * @version 1.0
 * @className BiTreeTool
 * @description 二叉树算法的一些工具方法
 * @date 2022/11/18 20:39
 */
public class BiTreeTool {

    /**
     * 生成一棵简单的二叉树，形如下方
     *          A
     *       /    \
     *      B      C
     *    /  \      \
     *   D    E      F
     *  /  \  /     /  \
     * G   H I     J   K
     *
     * @return
     */
    public static <T extends BiNode<T>> T generateEasyBiTree(Class<T> clazzName)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("二叉树形如：\n" +
                "              A\n" +
                "           /    \\\n" +
                "          B      C\n" +
                "        /  \\      \\\n" +
                "       D    E      F\n" +
                "     /  \\  /     /  \\\n" +
                "    G   H I     J    K");
        T rootA = clazzName.getConstructor(String.class).newInstance("A");
        rootA.lChild = clazzName.getConstructor(String.class).newInstance("B");
        rootA.rChild = clazzName.getConstructor(String.class).newInstance("C");
        rootA.lChild.lChild = clazzName.getConstructor(String.class).newInstance("D");
        rootA.lChild.rChild = clazzName.getConstructor(String.class).newInstance("E");
        rootA.rChild.rChild = clazzName.getConstructor(String.class).newInstance("F");
        rootA.lChild.lChild.lChild = clazzName.getConstructor(String.class).newInstance("G");
        rootA.lChild.lChild.rChild = clazzName.getConstructor(String.class).newInstance("H");
        rootA.lChild.rChild.lChild = clazzName.getConstructor(String.class).newInstance("I");
        rootA.rChild.rChild.lChild = clazzName.getConstructor(String.class).newInstance("J");
        rootA.rChild.rChild.rChild = clazzName.getConstructor(String.class).newInstance("K");
        return rootA;
    }

}
