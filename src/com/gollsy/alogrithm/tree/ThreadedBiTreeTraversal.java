package com.gollsy.alogrithm.tree;

import com.gollsy.alogrithm.entity.tree.ThreadedBiTree;
import static com.gollsy.alogrithm.tree.BiTreeTool.generateEasyBiTree;

/**
 * @author Admin
 * @version 1.0
 * @className ThreadedBiTreeTraversal
 * @description 线索二叉树
 * @date 2022/11/18 20:07
 */
public class ThreadedBiTreeTraversal extends BiTreeTraversal {
    private static ThreadedBiTree prevNode = null;

    /**
     * 中序线索化二叉树
     *
     * @param tBiTree
     */
    public static void inOrderThread(ThreadedBiTree tBiTree) {
        if (tBiTree != null) {
            inOrderThread(tBiTree.lChild);
            visit(tBiTree);
            inOrderThread(tBiTree.rChild);
        }
    }

    public static void visit(ThreadedBiTree tBiTree) {
        //如果当前节点的左指针为空，则指向前驱，同时标记
        if (tBiTree.lChild == null) {
            tBiTree.lChild = prevNode;
            tBiTree.lTag = 1;
        }
        //如果上一节点的右指针为空，则后继指向当前节点，同时标记
        if (prevNode != null && prevNode.rChild == null) {
            prevNode.rChild = tBiTree;
            prevNode.rTag = 1;
        }
        prevNode = tBiTree;
    }

    /**
     * 中序线索二叉树的遍历
     *
     * @param tBiTree
     */
    private static void inOrderTraversal(ThreadedBiTree tBiTree) {
        for (ThreadedBiTree point = firstNodeInOrder(tBiTree); point != null; point = nextNodeInOrder(point)) {
            String prefix = point.lChild==null?null+"(-1)":point.lChild.data+"("+point.lTag+")";
            String suffix = point.rChild==null?null+"(-1)":point.rChild.data+"("+point.rTag+")";
            System.out.println(prefix+ "<-【" + point.data + "】->" + suffix);
        }
    }

    /**
     * 以当前节点为起始，寻找中序序列下的第一个起点
     * @param point
     * @return
     */
    private static ThreadedBiTree firstNodeInOrder(ThreadedBiTree point) {
        while (point != null && point.lTag == 0) point = point.lChild;
        return point;
    }

    /**
     * 中序线索二叉树中该节点的后继
     * @param point
     * @return
     */
    private static ThreadedBiTree nextNodeInOrder(ThreadedBiTree point) {
        if (point != null && point.rTag == 1) return point.rChild;
        else if (point != null && point.rTag == 0) return firstNodeInOrder(point.rChild);
        return point;
    }

    public static void main(String[] args) throws Exception {
        ThreadedBiTree tBiTree = generateEasyBiTree(ThreadedBiTree.class);
        inOrderThread(tBiTree);
        inOrderTraversal(tBiTree);
    }
}
