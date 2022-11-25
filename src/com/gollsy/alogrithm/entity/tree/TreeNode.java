package com.gollsy.alogrithm.entity.tree;

import java.util.List;

/**
 * @author Admin
 * @version 1.0
 * @className Tree
 * @description todo
 * @date 2022/11/25 10:18
 */
public class TreeNode<T extends TreeNode,G>{

    public G data;

    public List<T> nextNode;

    public int size;

    public TreeNode(G data) {
        this.data = data;
    }

    public TreeNode() {
    }
}
