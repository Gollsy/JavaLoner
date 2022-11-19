package com.gollsy.alogrithm.entity.queue;

import java.lang.reflect.Array;

/**
 * @author Admin
 * @version 1.0
 * @className SqQueue
 * @description 线性存储的循环队列
 * @date 2022/11/19 15:30
 */
public class SqQueue<T> {
    private static final int MAX_SIZE = 21;

    /**
     * 成员变量全部public，是为了方便书写和直观显示
     */
    private T[] data;

    /**
     * 队首指针：队首元素
     * 队尾指针：下次入栈元素的位置
     */
    private int front,rear;

    /**
     * 判断队列是否为空的方法之二：
     * 出入队列标记，用于判断队列是空是满；
     * 出是1，入是0
     */
    private boolean flag;


    public SqQueue(Class<T> clazz) {
        this.data = (T[])Array.newInstance(clazz,MAX_SIZE);
        this.front = this.rear = 0;
    }

    /**
     * 队列为空
     * @return
     */
    public boolean isEmpty(){
        //上次操作是出队，由此导致首尾指针相等，说明是栈空；
        //这种并发性较差
//        return flag && (this.front == this.rear);
        //初始状态下，队空，首尾指针重合
        return this.front == this.rear;
    }

    /**
     * 队列为满
     * @return
     */
    public boolean isFull(){
        //上次操作是入队，由此导致首尾指针相等，说明是栈空；
//        return !flag && (this.front == this.rear);
        //如果rear指向了front前一个节点，则表示已满；
        //这会导致队列浪费一个元素
        return (rear+1)%MAX_SIZE == front;
    }

    /**
     * 入队
     * 队尾指针顺时针增加，溢出则取模
     * @param data
     * @return
     */
    public boolean enQueue(T data){
        if(isFull())  return false;
        this.data[rear] = data;
        rear=(rear+1)%MAX_SIZE;
        return true;
    }

    /**
     * 出队
     * 队首指针顺时针增加，溢出则取模
     * @return
     */
    public T deQueue(){
        if(isEmpty()) return null;
        int tmp = front;
        front=(front+1)%MAX_SIZE;
        return data[tmp];
    }

    /**
     * 队列长度
     * 例如：front=3,rear=2,此时length=(2-3+11)%11=10
     * @return
     */
    public int length(){
        return (rear-front+MAX_SIZE)%MAX_SIZE;
    }

}
