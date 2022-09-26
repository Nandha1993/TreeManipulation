package com.demo.treemanipulation.dao;

/**
 * The Node structure of Tree Data Structure with at most 2 children eg: Binary Tree, BST
 * @param <T>
 */
public class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T val;

    /**
     * Constructor that sets node value.
     * @param val
     */
    public TreeNode(T val) {
        this.val = val;
    }

    /**
     * Constructor that sets node value and its subTree nodes;
     * @param val
     * @param left
     * @param right
     */
    public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * Getter method to get Left Sub Tree node
     * @return
     */
    public TreeNode<T> getLeft() {
        return left;
    }

    /**
     * Setter method to set Left Sub Tree node
     * @param left
     */
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    /**
     * Getter method to get Right Sub Tree node
     * @return
     */
    public TreeNode<T> getRight() {
        return right;
    }

    /**
     * Setter method to set Right Sub Tree node
     * @param right
     */
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    /**
     * Get value of Tree Node
     * @return
     */
    public T getVal() {
        return val;
    }
}
