package com.gollsy.alogrithm.entity.tree;

import com.gollsy.alogrithm.entity.queue.SqQueue;

/**
 * @author Admin
 * @version 1.0
 * @className Tree
 * @description 二叉树节点
 * @date 2022/11/18 20:32
 */
public class BiNode<T extends BiNode,G> {
    /**
     * 数据域
     */
    public G data;

    /**
     * 左孩子，右孩子
     */
    public T lChild, rChild;

    public BiNode(G data) {
        this.data = data;
    }

    public BiNode() {
    }

    @Override
    public String toString() {
        return data.toString();
    }

    /**
     * 计算当前二叉树的高度
     * 层序遍历实现
     * 递归实现以及其他遍历实现参考下方
     *
     * @return
     * @see com.gollsy.alogrithm.tree.BiTreeTool#heightByRecursion(BiTree)
     * @see com.gollsy.alogrithm.tree.BiTreeTool#heightByPreOrderLoop(BiTree)
     */
    public int height() {
        BiNode point = this;
        SqQueue<BiNode> queue = new SqQueue<>(BiNode.class);
        queue.enQueue(point);
        int last = 1, level = 0;
        while (!queue.isEmpty()) {
            point = queue.deQueue();
            if (point.lChild != null) {
                queue.enQueue(point.lChild);
            }
            if (point.rChild != null) {
                queue.enQueue(point.rChild);
            }
            if (queue.getFront() == last) {
                level++;
                last = queue.getRear();
            }
        }
        return level;
    }

    /**
     * 获取二叉树某一层的节点数
     * 依旧使用层序遍历实现
     *
     * @param level
     * @return
     */
    public int width(int level) {
        BiNode point = this;
        SqQueue<BiNode> queue = new SqQueue<>(BiNode.class);
        queue.enQueue(point);
        int last = 1, tLevel = 0, width = 0;
        while (!queue.isEmpty()) {
            point = queue.deQueue();
            width++;
            if (point.lChild != null) {
                queue.enQueue(point.lChild);
            }
            if (point.rChild != null) {
                queue.enQueue(point.rChild);
            }
            if (last == queue.getFront()) {
                tLevel++;
                if (tLevel == level)
                    return width;
                width = 0;
                last = queue.getRear();
            }
        }
        return 0;
    }

    /**
     * 删除二叉树中指定内容的节点，并将其子树全部删除
     *
     * @param data
     * @return
     */
    public void deleteEqual(String data) {
        SqQueue<BiNode> queue = new SqQueue<>(BiNode.class);
        BiNode point = this;
        queue.enQueue(point);
        while (!queue.isEmpty()) {
            point = queue.deQueue();
            if(point.lChild!=null ){
                if (point.lChild.data==null && data!=null) {
                    queue.enQueue(point.lChild);
                } else if (data != null && !data.equals(point.lChild.data)) {
                    queue.enQueue(point.lChild);
                }else{
                    point.lChild=null;
                }
            }
            if(point.rChild!=null ){
                if (data==null && point.rChild.data!=null) {
                    queue.enQueue(point.rChild);
                } else if (data != null && !data.equals(point.rChild.data)) {
                    queue.enQueue(point.rChild);
                }else{
                    point.rChild=null;
                }
            }

        }
    }
}
