package com.gollsy.alogrithm.entity.tree;

/**
 * @author Admin
 * @version 1.0
 * @className ThreadBiTree
 * @description 线索二叉树
 * @date 2022/11/18 20:08
 */
public class ThreadedBiTree extends BiNode<ThreadedBiTree,String>{

    /**
     * 左、右节点线索标志，1为线索，0为子节点
     */
    public int lTag,rTag;

    public ThreadedBiTree(String data) {
        super(data);
    }
}
