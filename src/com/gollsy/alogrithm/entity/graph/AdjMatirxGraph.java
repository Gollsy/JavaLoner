package com.gollsy.alogrithm.entity.graph;

import java.util.Arrays;

/**
 * @author Admin
 * @version 1.0
 * @className AdjMatirx
 * @description Adjacency Matirx Graoh —— 图的邻接矩阵，适用于稠密图，结果唯一
 * @date 2022/11/23 11:09
 */
public class AdjMatirxGraph {

    /**
     * 无向图：邻接矩阵是个对称矩阵，a[i][j]表示边<i,j>
     * 有向图：a[i][j]表示弧<i,j>，从节点i指向节点j
     */
    public Integer[][] data;

    public int nodeNum;


    /**
     * @param nodeNum 节点个数
     */
    public AdjMatirxGraph(int nodeNum) {
        this(nodeNum, 0);
        this.nodeNum = nodeNum;
    }

    public AdjMatirxGraph(int nodeNum, int defaultValue) {
        this.data = new Integer[nodeNum][nodeNum];
        for (Integer[] datum : this.data) {
            Arrays.fill(datum, defaultValue);
        }
    }
}
