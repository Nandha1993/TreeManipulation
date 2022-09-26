package com.demo.treemanipulation.treehandler;

import com.demo.treemanipulation.dao.TreeNode;
import com.demo.treemanipulation.exceptionhandling.InputValidationException;

import java.util.Collection;
import java.util.List;

/**
 *The root interface in Tree hierarchy that is common to
 * all Tree implementations such as BinaryTree, Binary Search Tree.
 * @author NandhaKumar
 * @param <E>
 */
public interface Tree<E> {

    /**
     * This method inserts a node in the tree in level order
     * @param key
     * @throws InputValidationException
     * @throws IllegalArgumentException
     */
    void insertTreeNode(E key) throws InputValidationException, IllegalArgumentException;

    /**
     * This method inserts a collection of nodes in tree in level order
     * @param c
     * @throws InputValidationException
     * @throws IllegalArgumentException
     */
    void insertTreeNode(Collection<? extends E> c) throws InputValidationException, IllegalArgumentException;

    /**
     * This method returns the root of Tree.
     * @return root node of Tree
     */
    TreeNode getRoot() throws NullPointerException;

    /**
     * This method for level order traversal of Tree
     * @param treeNode
     * @return
     * @throws InputValidationException
     */
    List<Object> traverseLevelOrder(TreeNode treeNode) throws InputValidationException;

    /**
     * This method for Pre Order traversal of tree
     * @param treeNode
     * @return
     * @throws IllegalArgumentException
     */
    List<Object> traversePreOrder(TreeNode treeNode) throws IllegalArgumentException;

    /**
     * This method for inorder traversal of Tree
     * @param treeNode
     * @return
     * @throws IllegalArgumentException
     */
    List<Object> traverseInOrder(TreeNode treeNode) throws IllegalArgumentException;

    /**
     * This method for Post order traversal of Tree
     * @param treeNode
     * @return List of Tree Nodes keys
     * @throws IllegalArgumentException
     */
    List<Object> traversePostOrder(TreeNode treeNode) throws IllegalArgumentException;

    /**
     * This methood returns true , if Tree has no nodes
     * returns false, if tree has nodes.
     * @return true or false
     */
    boolean isEmpty();

    /**
     * This utility method finds the height of the tree
     * @param node
     * @return integer value - height of tree
     */
    static int maxDepth(TreeNode node)
    {
        if (node == null)
            return -1;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.getLeft());
            int rDepth = maxDepth(node.getRight());

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }
}
