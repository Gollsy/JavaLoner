package com.gollsy.alogrithm.search;

import com.gollsy.alogrithm.entity.tree.BiNode;

/**
 * @author Admin
 * @version 1.0
 * @className BinarySortedTree
 * @description 关于二叉排序树的搜索和插入
 * @date 2022/11/25 17:03
 */
public class BinarySortedTree {

    /**
     * 二叉排序树的特点： 左子树的所有顶点 < 顶点 < 右子树的所有顶点
     * 这是一个递归的定义
     * @param treeNode
     * @param target
     * @return
     */
    public static BiTreeNode bstSearch(BiTreeNode treeNode, int target){
        while(treeNode!=null && treeNode.data!=target){
            if(target< treeNode.data){
                treeNode = treeNode.lChild;
            }else{
                treeNode = treeNode.rChild;
            }
        }
        return treeNode;
    }

    /**
     * 【排序二叉树的插入】
     * 这只是一段伪代码，事实上无法运行
     * 由于树的结构定义比较复杂，java又是值传递，所以如果传进来一个null，实际上不会有任何效果
     * @param treeNode
     * @param newNode
     * @return
     */
    public static boolean bstInsert(BiTreeNode treeNode, int newNode){
        if(treeNode==null){
            treeNode = new BiTreeNode(newNode);
            return true;
        } else if (newNode == treeNode.data) {
            return false;
        } else if (newNode < treeNode.data) {
            return bstInsert(treeNode.lChild,newNode);
        } else {
            return bstInsert(treeNode.rChild,newNode);
        }
    }

    static class BiTreeNode extends BiNode<BiTreeNode,Integer>{
        public BiTreeNode(Integer data) {
            super(data);
        }

        public BiTreeNode() {
        }
    }
}
