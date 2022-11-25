package com.gollsy.alogrithm.graph;

import com.gollsy.alogrithm.entity.graph.AdjMultiLinkedTabGraph;
import com.gollsy.alogrithm.entity.tree.Tree;
import com.gollsy.alogrithm.entity.tree.UnionFindSets;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Admin
 * @version 1.0
 * @className MiniSpanningTree
 * @description 最小生成树，主要涉及Prim算法和Kruskal算法，最终目标是生成包含【n个顶点，n-1条边】的最小生成树
 *              要注意的是，只有【连通图】才会生成【最小生成树】，【非连通图】只会生成【最小生成森林】
 * @date 2022/11/23 19:19
 */
public class MiniSpanningTree{

    private AdjMultiLinkedTabGraph graph;

    /**
     * Prim算法生成【连通图】的最小生成树
     * 【起始】：随即选择一个顶点
     * 【步骤】：在当前生成树的已有节点中选择权值最小的一条【外邻接边】，即只有一个顶点在当前树中；
     *         将该外邻接边的另一个点插入到树中，重复这个步骤直到所有顶点都遍历完
     * 【复杂度】：每次都要在剩余的顶点中寻找最小外邻接点，故而时间复杂度O(vNodeNum^2)
     * 【优点】：适用于【边稠密的图】，因为该算法只与【顶点数量】有关
     *
     */
    public void prim(){
        //初始化一条空树，并随即选择一个顶点放入树中
        Tree<AdjMultiLinkedTabGraph.VNode> tree =  new Tree<>(graph.nodeList[0]);
        //记录该顶点，这里是为了打印【最小生成树的生成顺序】
        Set<AdjMultiLinkedTabGraph.VNode> record = new HashSet<>();
        record.add(graph.nodeList[0]);
        //当图中还有顶点没有被访问到
        while(tree.size < graph.nodeNum){
            //在当前生成树中的所有节点里寻找一条【权值最小】的邻接边
            //要求该权值最小邻接边的【只能有一个顶点在当前的生成树中】
            AdjMultiLinkedTabGraph.Arc smallestValuedArc = graph.findSmallestOutsideAdjArc(record);
            //这里的代码当然是很难看的，不过主要看实现的思想
            //目的就是将该【权值最小的邻接点】放入到node当中
            record.add(graph.nodeList[smallestValuedArc.oneEdge]);
            record.add(graph.nodeList[smallestValuedArc.anotherEdge]);
            //将新顶点插入到树中
            tree.add(smallestValuedArc);
        }
    }

    /**
     * Kruskal算法生成【连通图】的最小生成树
     * 【起始】：所有的顶点都当成一个独立的【连通分量】，寻找【权值最小的一条边】
     * 【步骤】：将该条最小权值边的两个顶点放入到最小生成树中，同时连通分量数减一；
     *         接着在不同的连通分量之间寻找新的最小权值边，要求不能使任何连通分量形成回路；
     *         重复上述步骤，直到连通分量数为1
     * 【复杂度】：通常来说，图的边并不是直接从邻接表中获得，而是储存在【堆结构】中，算法流程只是为了描述大概的思想。
     *          因而获取最小权值边的复杂度为 O(log|arcNum|)，底为2，那么总的时间复杂度就是O(arcNum * log|arcNum|)
     * 【优点】：根据时间复杂度可以看出适用于【顶点稠密】的图，因为该算法只与【边的数量】有关
     */
    public void kruskal(){
        //初始化一条空树
        Tree<AdjMultiLinkedTabGraph.VNode> tree =  new Tree<>();
        //起始将所有节点都看成独立的连通分量
        int connectedComponentNum = graph.nodeNum;
        //用顺序存储实现并查集
        UnionFindSets<AdjMultiLinkedTabGraph.VNode> connectedComponents = new UnionFindSets<>(connectedComponentNum);
        while(connectedComponentNum>1){
            //寻找不同连通分量之间的最小权值边
            AdjMultiLinkedTabGraph.Arc arc = graph.findSmallestArcInUnconnectedComponents(connectedComponents);
            //将边的两个顶点插入到树中
            tree.add(arc);
            //连通分量数减一
            connectedComponentNum--;
        }

    }
}
