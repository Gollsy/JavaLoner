package com.gollsy.alogrithm.graph;

import com.gollsy.alogrithm.entity.graph.AdjMatirxGraph;
import com.gollsy.alogrithm.entity.graph.AdjMultiLinkedTabGraph;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Admin
 * @version 1.0
 * @className ShortestPath
 * @description 最短路径，主要涉及到Dijkstra算法和Floyd算法，现在只介绍原理，不会具体实现
 * @date 2022/11/23 19:21
 */
public class ShortestPath {

    private AdjMatirxGraph graph;

    /**
     * Dijkstra算法是用来求【单源点到其他顶点的最短路径】
     * 用于【带权有向图】
     * 【核心】：贪心算法
     * 【复杂度】：无论基于【邻接矩阵】还是【带权的邻接表】，时间复杂度都为 O(v^2)；
     * 【局限】：不能应用于【负权值】的图
     */
    public void dijkstra(int u) {
        //准备一个dist[]数组，用来记录源点到其他顶点的距离
        //初始时其就等于【邻接矩阵】中对应行的内容，如果无邻接点就用∞表示
        Integer[] distance = graph.data[u];
        //将源点放入到【已找到的最短路径】集合当中，代表最短路径点找到的先后顺序
        Set<Integer> record = new HashSet<>(u);
        while (record.size() < graph.nodeNum) {
            //在distance中寻找一个权值最小的点，要求该点不在最短路径集合当中
            int smallestArcNode = findSmallestArcNodeExcept(distance, record);
            //这个顶点就是新找到的最短路径点，放入到distance中
            record.add(smallestArcNode);
            //以该新顶点为中转点，【重新计算源点到其他各顶点的最短路径】
            for (int i = 0; i < graph.nodeNum; i++) {
                //如果源点到某一顶点的原路径【大于】中转之后的路径，则更新源点到该顶点的该路径
                if (distance[i] > distance[smallestArcNode] + graph.data[smallestArcNode][i]) {
                    distance[i] = distance[smallestArcNode] + graph.data[smallestArcNode][i];
                }
            }
        }
    }

    //在distance寻找权值最小的顶点下标，要求该顶点下标不在record当中
    private int findSmallestArcNodeExcept(Integer[] distance, Set<Integer> record) {
        return 0;
    }


    /**
     * Floyd算法是用来求【任意两顶点间的最短路径】
     * 也可以用n次dijkstra来计算任意两点间的最短路径，时间复杂度一致
     * 【复杂度】：标准的O(v^3)
     * 【适用】：无回路的负权有向图，所有正权有向图，带权无向图
     */
    public Integer[][] floyd() {
        //该算法基于【邻接矩阵】最合适
        Integer[][] matrix = graph.data;
        //假设从顶点v0开始
        int vNode = 0;
        while (vNode++ < graph.nodeNum) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    //可以理解为向量的加法
                    //如果任意两点间的路径小于以vNode为中转点的路径，则更新
                    if (matrix[i][j] > matrix[i][vNode] + matrix[vNode][j]) {
                        matrix[i][j] = matrix[i][vNode] + matrix[vNode][j];
                    }
                }
            }
        }
        //最后的matrix就是任意两点间的最短路径
        return matrix;
    }

}
