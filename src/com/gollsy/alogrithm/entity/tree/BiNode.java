package com.gollsy.alogrithm.entity.tree;

/**
 * @author Admin
 * @version 1.0
 * @className Tree
 * @description 二叉树节点
 * @date 2022/11/18 20:32
 */
public class BiNode<T> {
    /**
     * 数据域
     */
    public String data;

    /**
     * 左孩子，右孩子
     */
    public T lChild, rChild;

    public BiNode(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}
