package com.gollsy.alogrithm.entity.tree;

import com.gollsy.alogrithm.entity.graph.AdjMultiLinkedTabGraph.Arc;

/**
 * @author Admin
 * @version 1.0
 * @className Tree
 * @description todo
 * @date 2022/11/25 10:20
 */
public class Tree<G> extends TreeNode<Tree,G>{

    public Tree(G data) {
        super(data);
    }

    public Tree() {
        super();
    }

    public void add(Arc smallestValuedArc) {

    }
}
