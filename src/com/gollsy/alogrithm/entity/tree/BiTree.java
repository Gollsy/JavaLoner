package com.gollsy.alogrithm.entity.tree;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Admin
 * @version 1.0
 * @className BiTree
 * @description 基础的二叉树
 * @date 2022/11/18 16:13
 */
public class BiTree extends BiNode<BiTree,String>{

    public BiTree(String data) {
        super(data);
    }
}
