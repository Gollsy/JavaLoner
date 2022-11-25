package com.gollsy.alogrithm.entity.tree;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Admin
 * @version 1.0
 * @className UnionFindSets
 * @description todo
 * @date 2022/11/25 11:25
 */
public class UnionFindSets<T> extends Sets<T>{
    public T[] data;
    public int[] parent;

    public UnionFindSets(int size) {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type actualTypeArgument = genericSuperclass.getActualTypeArguments()[0];
        this.data = (T[])Array.newInstance(actualTypeArgument.getClass(),size);
        this.parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = -1;
        }
    }

    public int find(T data){
        return 0;
    }

    public void union(){

    }

    public void findAndUnion(int p,int q){

    }
}
