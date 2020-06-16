package com.anuous.boot.algorithm.tree;

public class BinarySearchTreeMyself {

    /**
     * 插入  查找的逻辑类似
     * @param rootNode 根节点
     * @param insertValue 插入的值
     */
    public static void insert(TreeNode rootNode, Integer insertValue){
       Integer rootValue = rootNode.getData();
       if(rootValue.compareTo(insertValue)>0){
            if(rootNode.getRightNode()==null){
                rootNode.getRightNode().setData(insertValue);
                return ;
            }
            insert(rootNode.getRightNode(),insertValue);
        }else{
           if(rootNode.getLeftNode()==null){
               rootNode.getLeftNode().setData(insertValue);
               return ;
           }
           insert(rootNode.getLeftNode(),insertValue);
       }
    }

    /**
     * first 首先找到被删除数据的 位置
     * 删除分三种情况
     * 1、被删除的节点 为根节点 没有子树
     * 2、被删除的节点 只有一颗子树（左子树或者右子树）
     * 3、被删除的节点有两棵子树，则找到其后继节点，后继节点的左子树一定为空 （将后继节点的值赋给被删除节点，然后删除后继节点）
     */
    public static void delete(TreeNode rootNode, Integer deleteValue){
        //1、根节点为null 返回
        if(rootNode==null){
            return ;
        }
        //找到被s删除节点所在位置 并记录其父节点
        TreeNode curNode = rootNode;
        TreeNode parentNode = rootNode;
        while(curNode.getData()!=deleteValue){
            parentNode=curNode;
            if(curNode.getData()>deleteValue){
                curNode =curNode.getLeftNode();
            }else{
                curNode = curNode.getRightNode();
            }
        }
        //被删除的节点不存在 返回
        if(curNode==null){
            return;
        }
        //删除分三种情况处理
        //1、被删除节点为根节点
        if(curNode.getLeftNode()==null && curNode.getRightNode()==null){
            if(curNode == rootNode){
                rootNode = null;
            }
            if(parentNode.getLeftNode().equals(curNode)){
                parentNode.setLeftNode(null);
                return;
            }
            if(curNode.equals(parentNode.getRightNode())){
                parentNode.setRightNode(null);
                return;
            }
            //2.1被删除节点左节点为空，则将被删节点的右节点移到被删除节点
        }else if(curNode.getLeftNode()==null ){
            if(parentNode.getLeftNode().equals(curNode)){
                parentNode.setLeftNode(curNode.getRightNode());
                return;
            }
            if(curNode.equals(parentNode.getRightNode())){
                parentNode.setRightNode(curNode.getRightNode());
                return;
            }
            //2.2被删除节点左节点为空，则将被删节点的右节点移到被删除节点
        }else if(curNode.getRightNode()==null ){
            if(parentNode.getLeftNode().equals(curNode)){
                parentNode.setLeftNode(curNode.getLeftNode());
                return;
            }
            if(curNode.equals(parentNode.getRightNode())){
                parentNode.setRightNode(curNode.getLeftNode());
                return;
            }
        }else{
            //3、被删除的节点有两棵子树，则找到其后继节点，后继节点的左子树一定为空 （将后继节点的值赋给被删除节点，然后删除后继节点）
            //后继节点
            TreeNode successor = curNode.getRightNode();
            //后继节点的父节点
            TreeNode successorParentNode =curNode;
            while(successor.getLeftNode()!=null){
                successorParentNode=successor;
                successor = successor.getLeftNode();
            }
           // ============以下代码 后继节点移位到 被删除节点==============================
            curNode.setData(successor.getData());
            successorParentNode.setLeftNode(successor.getRightNode());
        }


    }

}
