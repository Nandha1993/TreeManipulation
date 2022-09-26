package com.demo.treemanipulation.treehandler;

import com.demo.treemanipulation.dao.TreeNode;
import com.demo.treemanipulation.exceptionhandling.InputValidationException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * This class provides a skeletal implementation of the Tree interface
 * The Tree Implementation class such as BinaryTree or BinarySearchTree can
 * have the shared functionality which can be customized from the abstract class.
 * The common behavior like node insertion/deletion that differs for tree implementation
 * which class needs to provide for their own implementation
 * @author Nandhakumar
 * @param <E>
 */
public abstract class AbstractTree<E> implements Tree<E>{

    private static final Logger logger = LogManager.getLogger(AbstractTree.class);

    /**
     * Sole Constructor
     */
    protected AbstractTree() {
    }

    /**
     * Breadth First Traversal of Tree
     * @param treeNode
     * @return list of nodes of tree in level order
     */
    @Override
    public List<Object> traverseLevelOrder(TreeNode treeNode) throws InputValidationException {
        logger.info("Traversing level order");
        if(treeNode == null)
            throw new InputValidationException("The Root is null");

        List<Object> levelOrder = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(treeNode);
        while (!queue.isEmpty()) {

            TreeNode rootNode = queue.poll();
            levelOrder.add(rootNode.getVal());

            /*Enqueue left child */
            if (rootNode.getLeft() != null) {
                queue.add(rootNode.getLeft());
            }

            /*Enqueue right child */
            if (rootNode.getRight() != null) {
                queue.add(rootNode.getRight());
            }
        }
        logger.info("Traversing level order complete");
        return levelOrder;
    }

    /**
     *  Depth First Traversal of Tree
     *  Pre-order traversal of tree
     *  Traverse recursively Root->Left->Right fashion
     * @param treeNode
     * @return
     */
    public List<Object> traversePreOrder(TreeNode treeNode) {
        List<Object> preOrder = new ArrayList<>();
        if (treeNode == null)
            return null;

        preOrder.add(treeNode.getVal());

        /* then recur on left subtree */
        traversePreOrder(treeNode.getLeft());

        /* now recur on right subtree */
        traversePreOrder(treeNode.getRight());
        return preOrder;
    }

    /**
     * Depth First Traversal of Tree
     * In-order traversal of tree
     * Traverse recursively Left->Root->Right fashion
     * @param treeNode
     * @return
     */
    public List<Object> traverseInOrder(TreeNode treeNode) {
        List<Object> inOrder = new ArrayList<>();
        if (treeNode == null)
            return null;

        /* first print data of node */
        traverseInOrder(treeNode.getLeft());
        inOrder.add(treeNode.getVal());

        /* then recur on left subtree */

        /* now recur on right subtree */
        traverseInOrder(treeNode.getRight());
        return inOrder;
    }

    /**
     * Depth First Traversal of Tree
     * Post-order traversal of tree
     * Traverse recursively Left->Right->Root fashion
     * @param treeNode
     * @return
     */
    public List<Object> traversePostOrder(TreeNode treeNode) {
        List<Object> postOrder = new ArrayList<>();
        if (treeNode == null)
            return null;

        /* first print data of node */
        traverseInOrder(treeNode.getLeft());
        traverseInOrder(treeNode.getRight());
        postOrder.add(treeNode.getVal());

        /* then recur on left subtree */

        /* now recur on right subtree */

        return postOrder;
    }
}
