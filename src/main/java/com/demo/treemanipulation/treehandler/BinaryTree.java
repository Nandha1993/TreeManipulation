package com.demo.treemanipulation.treehandler;

import com.demo.treemanipulation.dao.TreeNode;
import com.demo.treemanipulation.exceptionhandling.InputValidationException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Tree Implementation of Tree Interface
 * @param <E>
 */
public class BinaryTree<E> extends AbstractTree<E> implements Tree<E> {


    private static final Logger logger = LogManager.getLogger(BinaryTree.class);
    /**
     * root of the Binary Tree
     */
    private TreeNode<E> root;

    /**
     *  if tree is empty then true, otherwise false
     */
    private boolean isEmpty;

    /**
     * Constructor for initializing Binary Tree
     *
     */
    public BinaryTree() {
        logger.info("Initializing the Binary Tree");
        isEmpty = true;
    }

    /**
     * Inserts the new node in the Tree in level order
     * @param key
     * @throws InputValidationException
     */
    @Override
    public void insertTreeNode( E key) throws InputValidationException {

        logger.info("Inserting the TreeNode Binary Tree");

        TreeNode treeNode = root;
        // checks if tree is empty
        if (isEmpty) {
            root = new TreeNode(key);
            treeNode = root;
            isEmpty = false;
            return;
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(treeNode);

        // Do level order traversal until we find
        // an empty place.
        while (!q.isEmpty()) {
            treeNode = q.peek();
            q.remove();

            if (treeNode.getLeft() == null) {
                treeNode.setLeft(new TreeNode(key));
                break;
            }

            if (treeNode.getRight() == null) {
                treeNode.setRight(new TreeNode(key));
                break;
            }

            if(treeNode.getLeft() != null && treeNode.getRight() != null) {
                q.add(treeNode.getLeft());
                q.add(treeNode.getRight());
            }

            logger.info("Inserting the TreeNode Binary Tree Completed.");
    } }


    /**
     * This method for level order traversal of Tree
     * Collection<? extends E> c guarntees type should be E or sub class of E
     * @param c
     * @throws InputValidationException
     */
    @Override
    public void insertTreeNode(Collection<? extends E> c) throws InputValidationException {
        for(E key : c) {
            insertTreeNode(key);
        }
    }

    /**
     *
     * @return
     * @throws NullPointerException
     */
    @Override
    public TreeNode getRoot() throws NullPointerException{
        if(root == null)
            throw new NullPointerException("No Tree Exists");
        return root;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }
}
