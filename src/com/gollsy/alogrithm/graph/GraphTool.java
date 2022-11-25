package com.gollsy.alogrithm.graph;

import com.gollsy.alogrithm.entity.graph.OrthoLinkedGraph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Admin
 * @version 1.0
 * @className GraphTool
 * @description 主要是AOV网的拓扑排序，AOE网的关键路径；
 *              AOV和AOE都是有向无环图
 * @date 2022/11/23 20:50
 */
public class GraphTool {

    /**
     * 【有向无环图】的拓扑排序
     * 【时间复杂度】：基于【邻接表】时，为O(vNodeNum + arcNum)；基于【邻接矩阵】时，为O( vNodeNum^2 )
     * 【说明】：如果某一顶点的后继不唯一，则拓扑序列不唯一；如果所有顶点都有唯一的后继，则拓扑序列唯一
     *         拓扑有序的AOV图的邻接矩阵是【三角矩阵】，【互为充分必要条件】；
     *         普通的图的邻接矩阵如果是三角矩阵，则该图拓扑有序，【反之则不一定成立】
     * @param graph
     * @return
     */
    public boolean topologicalSort(OrthoLinkedGraph graph){
        //工作栈，用来记录所有入度为零的顶点
        Stack<OrthoLinkedGraph.VNode> stack = new Stack<>();
        //记录每个顶点的入度情况
        int[] indegree = new int[graph.nodeNum];
        for (OrthoLinkedGraph.VNode vNode : graph.nodeList) {
            //入度为零
            if(vNode.oneInArc == null){
                stack.push(vNode);
            }
            //初始化入度表
            indegree[vNode.index] = vNode.outArcNum();
        }
        //用来保存排序的结果
        List<OrthoLinkedGraph.VNode> record = new ArrayList<>();
        while(!stack.isEmpty()){
            OrthoLinkedGraph.VNode vNode = stack.pop();
            record.add(vNode);
            //将该顶点以及所有出度弧全部删除
            for(OrthoLinkedGraph.Arc arc = vNode.oneOutArc;arc!=null;arc =arc.nextSameTailArc){
                vNode = graph.get(arc.head);
                //如果删除的弧的邻接点入度为0
                if((--indegree[vNode.index])!=0){
                    //加入到工作栈中，下次弹出时加入到拓扑序列中
                    stack.push(vNode);
                }
            }
        }
        //如果拓扑序列的长度小于顶点数，说明此时不存在无前驱的顶点，即【有回路】
        return record.size() == graph.nodeNum;
    }
}
