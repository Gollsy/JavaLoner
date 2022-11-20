package com.gollsy.alogrithm.tree;

import com.gollsy.alogrithm.entity.tree.BiNode;
import com.gollsy.alogrithm.entity.tree.BiTree;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

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
                "        /   \\     \\\n" +
                "       D     E     F\n" +
                "     /  \\   /    /  \\\n" +
                "    G    H I    J    K\n" +
                "            \\\n" +
                "             L");
        T rootA = clazzName.getConstructor(String.class).newInstance("A");
        rootA.lChild = clazzName.getConstructor(String.class).newInstance("B");
        rootA.rChild = clazzName.getConstructor(String.class).newInstance("C");
        rootA.lChild.lChild = clazzName.getConstructor(String.class).newInstance("D");
        rootA.lChild.rChild = clazzName.getConstructor(String.class).newInstance("E");
        rootA.rChild.rChild = clazzName.getConstructor(String.class).newInstance("F");
        rootA.lChild.lChild.lChild = clazzName.getConstructor(String.class).newInstance("G");
        rootA.lChild.lChild.rChild = clazzName.getConstructor(String.class).newInstance("H");
        rootA.lChild.rChild.lChild = clazzName.getConstructor(String.class).newInstance("I");
        rootA.lChild.rChild.lChild.rChild = clazzName.getConstructor(String.class).newInstance("L");
        rootA.rChild.rChild.lChild = clazzName.getConstructor(String.class).newInstance("J");
        rootA.rChild.rChild.rChild = clazzName.getConstructor(String.class).newInstance("K");
        return rootA;
    }

    /**
     * 计算当前二叉树的高度
     * 递归实现
     * @return
     */
    public static int heightByRecursion(BiTree root){
        if(root.rChild!=null && root.lChild!=null){
            return Math.max(heightByRecursion(root.rChild), heightByRecursion(root.lChild))+1;
        } else if (root.rChild!=null){
            return heightByRecursion(root.rChild)+1;
        }else if(root.lChild!=null){
            return heightByRecursion(root.lChild)+1;
        }
        return 1;
    }

    /**
     * 计算当前二叉树的高度
     * 先序遍历实现，中序或是后序遍历只需将访问节点的操作换成计数操作即可
     * @return
     */
    public static int heightByInOrderLoop(BiTree root){
        BiNode point = root;
        int record =1;
        Stack<BiNode> stack = new Stack<>();
        Stack<Integer> height = new Stack<>();
        height.push(1);
        while(point!=null || !stack.isEmpty()) {
            if (point != null) {
                stack.push(point);
                Integer level = height.peek();
                height.push(level+1);
                record = Math.max((level + 1), record);
                point = point.lChild;
            }else {
                point = stack.pop();
                height.pop();
                point = point.rChild;
            }
        }
        return record;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        BiTree biTree = generateEasyBiTree(BiTree.class);
        System.out.println("递归实现的二叉树高度为："+heightByRecursion(biTree));
        System.out.println("先序循环实现的二叉树高度为："+heightByInOrderLoop(biTree));
        System.out.println("层序循环实现的二叉树高度为："+biTree.height());
        System.out.println("层序循环实现的二叉树第6层宽度为："+biTree.width(6));
    }

}
