package com.gollsy.alogrithm.graph;

import com.gollsy.alogrithm.entity.graph.AdjLinkedTabGraph;
import com.gollsy.alogrithm.entity.queue.SqQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Admin
 * @version 1.0
 * @className GraphTraverse
 * @description 图的遍历，主要是广度遍历和深度遍历
 * @date 2022/11/23 19:18
 */
public class GraphTraverse {

    private AdjLinkedTabGraph graph;

    public GraphTraverse(AdjLinkedTabGraph graph) {
        this.graph = graph;
    }

    /**
     *    用于记录图中所有节点的访问情况
     */
    private boolean[] nodeVisited = new boolean[graph.nodeNum];


    /**
     * 图的【广度优先遍历算法】
     * 对应于树的【层序遍历】
     */
    public void breadthFirstTraverse() {
        Arrays.fill(nodeVisited, false);
        //这里用【栈也可以】，因为算法并不受节点之间关系的影响，所以谁先谁后并没有影响
        SqQueue<AdjLinkedTabGraph.VNode> queue = new SqQueue<>(AdjLinkedTabGraph.VNode.class);
        for (int i = 0; i < graph.nodeNum; i++) {
            //只要这个顶点没有被访问过
            if (!nodeVisited[i]) {
                breadthFirstSearch(i, queue);
            }
        }
    }

    /**
     * 访问当前点
     * 将当前点的所有邻接点都访问一遍，同时将其都放入队列
     * 修改当前点以及所有邻接点的访问状态
     * 【空间复杂度】最坏的情况下，一个顶点有（vNodeNum-1）个邻接点，这些邻接点需要一次性放入队列中，此时空间消耗——O(vNodeNum-1)=O(vNodeNum)
     * 【时间复杂度】每个节点都需要访问一次，为了找邻接点每条边也都要访问一次，所以耗时——O(vNodeNum+arcNum)
     * 【广度优先生成树】：基于【邻接矩阵】的生成树是唯一的，基于【邻接表】则不唯一
     *
     * @param nodeIndex
     * @param queue
     */
    private void breadthFirstSearch(int nodeIndex, SqQueue<AdjLinkedTabGraph.VNode> queue) {
        AdjLinkedTabGraph.VNode vNode = graph.nodeList[nodeIndex];
        //访问节点
        visit(vNode);
        //将该节点标记为已访问
        nodeVisited[nodeIndex] = true;
        queue.enQueue(vNode);
        while (!queue.isEmpty()) {
            AdjLinkedTabGraph.VNode node = queue.deQueue();
            AdjLinkedTabGraph.Arc arc = node.firstArc;
            for (nodeIndex = arc.vNodeIndex; nodeIndex > 0; nodeIndex = arc.vNodeIndex) {
                //如果该节点已经访问过，那么该节点要么在队列中，要么已经连同其邻接点都被访问过了
                if (nodeVisited[nodeIndex]) {
                    continue;
                }
                //访问该节点
                visit(graph.nodeList[nodeIndex]);
                //将该节点标记为已访问
                nodeVisited[nodeIndex] = true;
                //将该节点入队列，下次将遍历该节点的所有邻接点
                queue.enQueue(graph.nodeList[nodeIndex]);
                //指向依附于同一个点的下一条边
                arc = arc.nextArc;
            }
        }
    }

    /**
     * 使用【广度有限遍历】求解单源最短路径
     */
    public Integer[] BFS_MIN_DISTANCE(int nodeIndex, SqQueue<AdjLinkedTabGraph.VNode> queue){
        //distance[]表示顶点u到其他各个顶点的距离，初值null表示没有通路可达
        Integer[] distance = new Integer[graph.nodeNum];
        distance[nodeIndex] = 0;
        //接下来就和广度优先遍历的流程一样了
        AdjLinkedTabGraph.VNode vNode = graph.nodeList[nodeIndex];
        //访问节点
        visit(vNode);
        //将该节点标记为已访问
        nodeVisited[nodeIndex] = true;
        queue.enQueue(vNode);
        while (!queue.isEmpty()) {
            AdjLinkedTabGraph.VNode node = queue.deQueue();
            AdjLinkedTabGraph.Arc arc = node.firstArc;
            for (nodeIndex = arc.vNodeIndex; nodeIndex > 0; nodeIndex = arc.vNodeIndex) {
                //如果该节点已经访问过，那么该节点要么在队列中，要么已经连同其邻接点都被访问过了
                if (nodeVisited[nodeIndex]) {
                    continue;
                }
                //访问该节点
                visit(graph.nodeList[nodeIndex]);
                //【路径长度加一】
                distance[nodeIndex] = distance[node.index]+1;
                //将该节点标记为已访问
                nodeVisited[nodeIndex] = true;
                //将该节点入队列，下次将遍历该节点的所有邻接点
                queue.enQueue(graph.nodeList[nodeIndex]);
                //指向依附于同一个点的下一条边
                arc = arc.nextArc;
            }
        }
        return distance;
    }

    /**
     * 图的【深度优先遍历算法】
     * 对应于树的【先序遍历】
     *
     * 【连通图】采用深度优先遍历会产生【深度优先树】
     * 【非连通图】则会产生【深度优先森林】
     */
    public void deepthFirstTraverse(){
        Arrays.fill(nodeVisited, false);
        for (int i = 0; i < graph.nodeNum; i++) {
            //只要这个顶点没有被访问过
            if (!nodeVisited[i]) {
                deepthFirstSearch(i);
            }
        }
    }

    /**
     * 访问当前节点
     * 【递归】访问当前节点的每一个节点
     * 修改当前点以及所有邻接点的访问状态
     *
     * 【空间复杂度】：递归需要递归栈作为额外空间，所以空间复杂度——O(vNodeNum)
     * 【时间复杂度】：以【邻接矩阵】表示时，很明显是 O(vNodeNum^2) ，因为要逐行查询每一个顶点的邻接点；
     *              以【邻接表】表示时，依然是每条边查一次，已经查过的边的两个端点在下次查询时会被跳过；然后是每个顶点查一次，所以总的时间复杂度是 O(vNodeNum + arcNum)
     * 【深度优先生成树】：基于【邻接矩阵】的生成树是唯一的，基于【邻接表】则不唯一
     *
     * @param nodeIndex
     */
    private void deepthFirstSearch(int nodeIndex) {
        AdjLinkedTabGraph.VNode vNode = graph.nodeList[nodeIndex];
        //访问该节点
        visit(graph.nodeList[nodeIndex]);
        //将该节点标记为已访问
        nodeVisited[nodeIndex] = true;
        AdjLinkedTabGraph.Arc arc = vNode.firstArc;
        for (nodeIndex = arc.vNodeIndex; nodeIndex > 0; nodeIndex = arc.vNodeIndex) {
            //如果该节点已经访问过，那么该节点要么在队列中，要么已经连同其邻接点都被访问过了
            if (nodeVisited[nodeIndex]) {
                continue;
            }
            //递归去访问邻接点
            deepthFirstSearch(nodeIndex);
            arc = arc.nextArc;
        }
    }


    private static void visit(AdjLinkedTabGraph.VNode vNode) {
        System.out.println(vNode.data);
    }
}
