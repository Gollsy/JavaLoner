package com.gollsy.alogrithm.entity.graph;

/**
 * @author Admin
 * @version 1.0
 * @className OrthoList
 * @description Orthogonal linked Graph —— 图的十字链表，是邻接表针对有向图的改进，不唯一
 * @date 2022/11/23 11:12
 */
public class OrthoLinkedGraph {

    /**
     * 图中的顶点表
     */
    public VNode[] nodeList;

    /**
     * 图的顶点数，边/弧数
     */
    public int nodeNum,arcNum;

    public VNode get(int index) {
        if(nodeList==null) return null;
        return nodeList[index];
    }

    /**
     * 图中的边/弧
     */
    public static class Arc {
        //该有向弧的终点，弧头
        public int head;
        //该有向弧的起点，弧尾
        public int tail;
        //指向同一个节点的有向弧，不唯一
        public Arc nextSameHeadArc;
        //同一个节点指出的有向弧，不唯一
        public Arc nextSameTailArc;
    }

    /**
     * 图中的节点
     */
    public static class VNode{
        public int index;

        public String data;
        //当前节点的一条入度的弧，不唯一
        public Arc oneInArc;
        //当前节点的一条出度的弧，不唯一
        public Arc oneOutArc;

        public int outArcNum() {
            if(oneOutArc == null){
                return 0;
            }
            int count = 0;
            while(oneOutArc!=null){
                count++;
                oneOutArc = oneOutArc.nextSameTailArc;
            }
            return count;
        }
    }
}
