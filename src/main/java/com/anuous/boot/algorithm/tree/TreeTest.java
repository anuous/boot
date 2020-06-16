package com.anuous.boot.algorithm.tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TreeTest {
    public static void main(String[] args) {
        //TreeNode a=new TreeNode(null,);

    }

    /**
     * 前序遍历二叉树 根、左、右
     * @param rootNode
     */
    public static void pre(TreeNode rootNode){
        System.out.print(rootNode.getData() );
        if(rootNode.getLeftNode()!=null){
            pre(rootNode.getLeftNode());
        }
        if(rootNode.getRightNode()!=null){
            pre(rootNode.getRightNode());
        }
    }

    /**
     * 中序遍历二叉树 根、左、右
     * @param rootNode
     */
    public static void middle(TreeNode rootNode){

        if(rootNode.getLeftNode()!=null){
            middle(rootNode.getLeftNode());
        }
        System.out.print(rootNode.getData() );
        if(rootNode.getRightNode()!=null){
            middle(rootNode.getRightNode());
        }
    }

    /**
     * 后序遍历二叉树 根、左、右
     * @param rootNode
     */
    public static void post(TreeNode rootNode){

        if(rootNode.getLeftNode()!=null){
            post(rootNode.getLeftNode());
        }
        if(rootNode.getRightNode()!=null){
            post(rootNode.getRightNode());
        }
        System.out.print(rootNode.getData( ));
    }

    /**
     * 层遍历 利用队列先进先出的特性
     * 先将跟节点放进队列 拿出该节点
     * @param rootNode
     */
    public static void level(TreeNode rootNode){
        if(rootNode ==null){
            return ;
        }
        Queue<TreeNode> queue = new LinkedBlockingQueue<TreeNode>();
        queue.offer(rootNode);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.getData() );
            if(node.getLeftNode()!=null){
                queue.offer(node.getLeftNode());
            }
            if(node.getRightNode()!=null){
                queue.offer(node.getRightNode());
            }
        }
    }
}
