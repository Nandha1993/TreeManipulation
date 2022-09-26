package com.demo.treemanipulation.client;

import com.demo.treemanipulation.dao.TreeNode;
import com.demo.treemanipulation.exceptionhandling.InputValidationException;
import com.demo.treemanipulation.exceptionhandling.OperationalException;
import com.demo.treemanipulation.treehandler.Tree;
import com.demo.treemanipulation.utility.RemoveNodeUtility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Vector;

/**
 * This class is a strategy to remove node and returns subtree root nodes
 * based on in-order traversal
 */
public class RemoveNodeInOrder implements RemoveNodeStrategy{

    private static final Logger logger = LogManager.getLogger(RemoveNodeInOrder.class);

    private Tree tree;
    private final Object nodeToBeRemoved;
    private Vector<TreeNode> subTreeRoots;

    /**
     * This constructor initializes the Tree and the node that to be deleted.
     * @param tree
     * @param nodeToBeRemoved
     */
    public RemoveNodeInOrder(Tree tree, Object nodeToBeRemoved) throws InputValidationException {
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
        logger.info("Entering the method Inorder - removeNode");
        RemoveNodeUtility.validate(tree.getRoot(), nodeToBeRemoved);
        removeNodeInOrder();
        logger.info("Exiting the method Inorder - removeNode");
        return subTreeRoots;
    }

    /**
     * In-order Traversal of Tree
     */
    private void  removeNodeInOrder() {
        logger.info("Entering the method Inorder - removeNodeInOrder");
        TreeNode treeNode = tree.getRoot();
        if (RemoveNodeUtility.isNodeToBeDeleted(treeNode, nodeToBeRemoved)) {
            RemoveNodeUtility.addInSubTreeIfNodeIsNotTheDeleteNode(treeNode, nodeToBeRemoved, subTreeRoots);
            removeNode(treeNode.getLeft(), treeNode, false);
            removeNode(treeNode.getRight(), treeNode, true);
        }  else {
            subTreeRoots.add(treeNode);
            removeNode(treeNode, null, false);
        }
        logger.info("Exiting the method Inorder - removeNodeInOrder");
    }

    /**
     * Recursive function that removes node and adds the root in resultant vector.
     * @param treeNode
     * @return
     */
    private void removeNode(TreeNode treeNode, TreeNode prev, boolean leftOrRight) {
        //inorder
        if (treeNode == null) return;

        removeNode(treeNode.getLeft(), treeNode, false);

        if (nodeToBeRemoved.equals(treeNode.getVal())) {
            //came through left subtree
            if (!leftOrRight) prev.setLeft(null);
            else prev.setRight(null);

            //add new roots
            RemoveNodeUtility.addInSubTreeIfNodeIsNotTheDeleteNode(treeNode, nodeToBeRemoved, subTreeRoots);
        }
        removeNode(treeNode.getRight(), treeNode, true);
    }
}
