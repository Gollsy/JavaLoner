package com.gollsy.alogrithm.entity.graph;

import com.gollsy.alogrithm.entity.tree.UnionFindSets;

import java.util.Set;

/**
 * @author Admin
 * @version 1.0
 * @className AdjMultiList
 * @description Adjacency Multiple Linked Table—— 图的邻接多重表，邻接表针对于无向图的改进，结果不唯一
 * @date 2022/11/23 11:15
 */
public class AdjMultiLinkedTabGraph {

    /**
     * 图中的顶点表
     */
    public VNode[] nodeList;

    /**
     * 图的顶点数，边/弧数
     */
    public int nodeNum,arcNum;

    public Arc findSmallestOutsideAdjArc(Set<VNode> node) {
        return null;
    }

    public Arc findSmallestArcInUnconnectedComponents(UnionFindSets<VNode> connectedComponents) {
        return null;
    }

    /**
     * 图中的边/弧
     */
    public static class Arc {
        //该边是否被搜索过
        public boolean mark;
        //该边的一个顶点
        public int oneEdge;
        //依附于oneEdge的另一条边
        public Arc oneEdgeBro;
        //该边的另一个顶点
        public int anotherEdge;
        //依附于anotherEdge的另一条边
        public Arc anotherEdgeBro;
    }

    /**
     * 图中的节点
     */
    public static class VNode{
        public String data;
        //当前节点的一条边，不唯一
        public Arc firstArc;
    }
}
