package com.gollsy.alogrithm.tree;

import com.gollsy.alogrithm.entity.queue.SqQueue;
import com.gollsy.alogrithm.entity.tree.BiTree;
import java.util.*;
import java.util.stream.Collectors;
import static com.gollsy.alogrithm.tree.BiTreeTool.generateEasyBiTree;

/**
 * @author Admin
 * @version 1.0
 * @className BiTreeTraversal
 * @description 二叉树的先序、中序、后序、层序遍历
 * @date 2022/11/18 16:19
 */
public class BiTreeTraversal {

    private static BiTree DefaultNode = new BiTree("Null Node");

    private static List<String> result = new ArrayList<>();

    /* 二叉树遍历 START **/

    /**
     * 【递归】【先序】遍历
     * 1、先访问根节点
     * 2、再访问左子树
     * 3、后访问右子树
     *
     * @param biTree
     */
    public static void preOrderByRecursion(BiTree biTree) {
        if (biTree != null) {
            visit(biTree);
            preOrderByRecursion(biTree.lChild);
            preOrderByRecursion(biTree.rChild);
        }
    }

    /**
     * 【循环】【先序】遍历
     *
     * @param biTree
     */
    public static void preOrderByLoop(BiTree biTree) {
        Stack<BiTree> stack = new Stack<>();
        BiTree point = biTree;
        //当前节点不为空 或 当前栈不为空
        while (point != null || !stack.isEmpty()) {
            if (point != null) {
                //先序先访问当前节点
                visit(point);
                //当前节点入栈
                stack.push(point);
                //转到当前节点的左子节点，搜寻二叉树最左边的一个子节点，该子节点不一定是叶子节点，因为可能还有个右子节点
                point = point.lChild;
            } else {
                //栈顶元素一定是当前元素的双亲，如果当前节点为空，说明prevNode的lChild为空，即prevNode就是二叉树最左边的一个节点
                BiTree prevNode = stack.pop();
                //转到右兄弟遍历
                point = prevNode.rChild;
            }
        }
    }

    /**
     * 递归中序遍历
     * 1、先访问左子树
     * 2、再访问根节点
     * 3、后访问右子树
     *
     * @param biTree
     */
    public static void inOrderByRecursion(BiTree biTree) {
        if (biTree != null) {
            inOrderByRecursion(biTree.lChild);
            visit(biTree);
            inOrderByRecursion(biTree.rChild);
        }
    }

    /**
     * 【循环】【中序】遍历
     *
     * @param biTree
     */
    public static void inOrderByLoop(BiTree biTree) {
        Stack<BiTree> stack = new Stack<>();
        BiTree point = biTree;
        //当前节点不为空 或 当前栈不为空
        while (point != null || !stack.isEmpty()) {
            if (point != null) {
                //当前节点入栈
                stack.push(point);
                //转到当前节点的左子节点，搜寻二叉树最左边的一个子节点，该子节点不一定是叶子节点，因为可能还有个右子节点
                point = point.lChild;
            } else {
                //如果当前节点为空，说明：
                // 1、上一个prevNode的lChild为空。第一次为空时，栈顶元素prevNode就是二叉树最左边的一个节点
                // 2、上一个prevNode的rChild为空，此时右子树遍历完毕，栈顶元素prevNode就是上一个prevNode的双亲
                //栈顶元素为当前元素的双亲
                BiTree prevNode = stack.pop();
                //中序访问当前节点
                visit(prevNode);
                //转到右兄弟遍历
                point = prevNode.rChild;
            }
        }
    }

    /**
     * 递归后序遍历
     * 1、先访问左子树
     * 2、再访问右子树
     * 3、后访问根节点
     *
     * @param biTree
     */
    public static void postOrderByRecursion(BiTree biTree) {
        if (biTree != null) {
            postOrderByRecursion(biTree.lChild);
            postOrderByRecursion(biTree.rChild);
            visit(biTree);
        }
    }

    /**
     * 【循环】【后序】遍历
     *
     * @param biTree
     */
    public static void postOrderByLoop(BiTree biTree) {
        Stack<BiTree> stack = new Stack<>();
        BiTree prevNode = null,point = biTree;
        while (point != null || !stack.isEmpty()) {
            if (point != null) {
                stack.push(point);
                //不管是先中后序，永远都是优先查询左子树
                point = point.lChild;
            } else {
                point = stack.peek();
                //如果当前节点的右指针不为空
                //且没有访问过右子树
                 if(point.rChild!=null && point.rChild!=prevNode) {
                     //开始访问右子树
                    point=point.rChild;
                 }else{
                     //当前节点的右指针为空，或者已经访问过了，弹出双亲节点
                     point = stack.pop();
                     visit(point);
                     //更新访问记录
                     prevNode = point;
                     //下一次进入时，会继承栈顶元素
                     point = null;
                 }
            }
        }
    }

    /**
     * 【循环】【层序】遍历
     *
     * 从上至下每一个元素依次入队列，然后取出将其子节点放入队列
     * @param biTree
     */
    public static void levelOrderByLoop(BiTree biTree){
        SqQueue<BiTree> queue = new SqQueue<>(BiTree.class);
        //根节点入队列
        queue.enQueue(biTree);
        BiTree point;
        //循环打印队列中的节点
        while(!queue.isEmpty()){
            point = queue.deQueue();
            //子节点入队
            if(point.lChild!=null){
                queue.enQueue(point.lChild);
            }
            if(point.rChild!=null){
                queue.enQueue(point.rChild);
            }
            visit(point);
        }
    }
    /* 二叉树遍历 END **/

    /**
     * 访问当前节点
     * @param biTree
     */
    private static void visit(BiTree biTree) {
        result.add(Optional.ofNullable(biTree).orElse(DefaultNode).data);
    }

    /**
     * 打印结果函数
     * @param prefix
     */
    private static void print(String prefix) {
        System.out.println(result.stream().collect(Collectors.joining(" -> ", prefix + "{ ", " }")));
        result.clear();
    }

    public static void main(String[] args) throws Exception{
        BiTree biTree = generateEasyBiTree(BiTree.class);
        //先序遍历
        preOrderByRecursion(biTree);
        print("【递归】【先序】遍历结果是：");
        preOrderByLoop(biTree);
        print("【循环】【先序】遍历结果是：");
        //中序遍历
        inOrderByRecursion(biTree);
        print("【递归】【中序】遍历结果是：");
        inOrderByLoop(biTree);
        print("【循环】【中序】遍历结果是：");
        //后序遍历
        postOrderByRecursion(biTree);
        print("【递归】【后序】遍历结果是：");
        postOrderByLoop(biTree);
        print("【循环】【后序】遍历结果是：");
        //层序遍历
        levelOrderByLoop(biTree);
        print("【循环】【层序】遍历结果是：");
    }
}

