package com.demo.treemanipulation.client;

import com.demo.treemanipulation.dao.TreeNode;
import com.demo.treemanipulation.exceptionhandling.InputValidationException;
import com.demo.treemanipulation.exceptionhandling.OperationalException;
import com.demo.treemanipulation.treehandler.AbstractTree;
import com.demo.treemanipulation.treehandler.Tree;
import com.demo.treemanipulation.utility.RemoveNodeUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Vector;

/**
 * This class is a strategy to remove node and returns subtree root nodes
 * based on pre-order traversal
 */
public class RemoveNodePreOrder implements RemoveNodeStrategy{

    private static final Logger logger = LogManager.getLogger(RemoveNodePreOrder.class);

    private Tree tree;
    private final Object nodeToBeRemoved;
    private Vector<TreeNode> subTreeRoots;

    /**
     * This constructor initializes the Tree and the node that to be deleted.
     * @param tree
     * @param nodeToBeRemoved
     */
    public RemoveNodePreOrder(Tree<?> tree, Object nodeToBeRemoved) throws InputValidationException {
        logger.info("Initializing Pre-Order Strategy");
        if(tree.isEmpty()) {
            logger.error("Tree is Empty");
            throw new InputValidationException("Tree is Empty");
        }
        this.tree = tree;
        this.nodeToBeRemoved = nodeToBeRemoved;
        subTreeRoots = new Vector<>();
    }

    /**
     * This method validates the input , delete the node and returns subtree root nodes
     * @return vector of TreeNode
     * @throws OperationalException
     * @throws InputValidationException
     */
    @Override
    public Vector<TreeNode> removeNode() throws OperationalException, InputValidationException {
         logger.info("Pre-Order Strategy - Inside removeNode method");
         // checking if tree node is null and node to be deleted of same type with Tree node key
         RemoveNodeUtility.validate(tree.getRoot(), nodeToBeRemoved);
         // if validation is success, the preorder strategy starts
         removeNodeInPreOrder();
         logger.info("Pre-Order Strategy - Exit removeNode method");
         return subTreeRoots;
    }

    /**
     * Pre-order Traversal of Tree
     */
    private void  removeNodeInPreOrder()  {
        logger.info("Pre-Order Strategy - Inside removeNodeInPreOrder method");
        TreeNode treeNode = tree.getRoot();
        //checks if treenode and node to be deleted are same
        if(!RemoveNodeUtility.isNodeToBeDeleted(treeNode, nodeToBeRemoved)) {
            //if they are not same, then the node is added in vector
            subTreeRoots.add(treeNode);
        }
        removeNode(treeNode);
        logger.info("Pre-Order Strategy - Exit removeNodeInPreOrder method");
    }

    /**
     * Recursive function that removes node and adds the root in resultant vector.
     * @param treeNode
     * @return
     */
    private TreeNode removeNode(TreeNode treeNode) {
        logger.info("Pre-Order Strategy - Inside removeNode Recursive method");
        if(RemoveNodeUtility.checkIfTreeNodeIsNull(treeNode))  return null;

        treeNode.setLeft(removeNode(treeNode.getLeft()));
        treeNode.setRight(removeNode(treeNode.getRight()));

        //checks if Treenode and node to be deleted are same
        if(!RemoveNodeUtility.isNodeToBeDeleted(treeNode, nodeToBeRemoved)) {
            return treeNode;
        }

        RemoveNodeUtility.addInSubTreeListIfNodeIsNotNull(treeNode,subTreeRoots);
        logger.info("Pre-Order Strategy - Inside removeNode Recursive method");
        return null;
    }

}
