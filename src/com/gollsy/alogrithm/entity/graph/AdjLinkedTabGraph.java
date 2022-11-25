package com.gollsy.alogrithm.entity.graph;

import java.util.List;

/**
 * @author Admin
 * @version 1.0
 * @className AdjTable
 * @description Adjacency table —— 图的邻接表，适用于稀疏图
 * @date 2022/11/23 11:10
 */
public class AdjLinkedTabGraph {

    /**
     * 图中的顶点表
     */
    public VNode[] nodeList;

    /**
     * 图的顶点数，边/弧数
     */
    public int nodeNum,arcNum;


    /**
     * 图中的边/弧
     */
    public static class Arc{
        public int vNodeIndex;
        public Arc nextArc;
    }

    /**
     * 图中的节点
     */
    public static class VNode{
        public int index;

        public String data;
        //连接该节点的一条弧
        public Arc firstArc;
    }
}
