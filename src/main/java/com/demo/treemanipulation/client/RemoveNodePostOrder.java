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
 * based on post-order traversal
 */
public class RemoveNodePostOrder implements RemoveNodeStrategy{

    private static final Logger logger = LogManager.getLogger(RemoveNodePostOrder.class);

    private Tree tree;
    private final Object nodeToBeRemoved;
    private Vector<TreeNode> subTreeRoots;

    /**
     * This constructor initializes the Tree and the node that to be deleted.
     * @param tree
     * @param nodeToBeRemoved
     */
    public RemoveNodePostOrder(Tree<?> tree, Object nodeToBeRemoved) throws InputValidationException {
        if(tree.isEmpty()) {
            logger.info("Tree is Empty");
            throw new InputValidationException("Tree is Empty");
        }
        this.tree = tree;
        this.nodeToBeRemoved = nodeToBeRemoved;
        subTreeRoots = new Vector<>();
    }

    /**
     * This method validates the input , delete the node and returns subtree root nodes
     * @return vector of TreeNode
     * @throws InputValidationException
     * @throws OperationalException
     */
    @Override
    public Vector<TreeNode> removeNode() throws InputValidationException, OperationalException {
        logger.info("Entering the method post order - removeNode");
        // checking if tree node is null and node to be deleted of same type with Tree node key
        RemoveNodeUtility.validate(tree.getRoot(), nodeToBeRemoved);
        removeNodePostOrder();
        logger.info("Exiting the method Postorder - removeNode");
        return subTreeRoots;
    }

    /**
     * Post-order Traversal of Tree
     */
    private void  removeNodePostOrder() {
        logger.info("Entering the method post order - removeNodePostOrder");
        TreeNode treeNode = tree.getRoot();
        removeNode(treeNode);
        if(!RemoveNodeUtility.checkIfTreeNodeIsNull(treeNode))
            subTreeRoots.add(treeNode);
        logger.info("Entering the method post order - removeNodePostOrder");
    }

    /**
     * Recursive function that removes node and adds the root in resultant vector.
     * @param treeNode
     * @return
     */
    private TreeNode removeNode(TreeNode<?> treeNode) {
        if(RemoveNodeUtility.checkIfTreeNodeIsNull(treeNode))return null;

        treeNode.setLeft(removeNode(treeNode.getLeft()));
        treeNode.setRight(removeNode(treeNode.getRight()));

        if(RemoveNodeUtility.isNodeToBeDeleted(treeNode, nodeToBeRemoved)){

            RemoveNodeUtility.addInSubTreeListIfNodeIsNotNull(treeNode, subTreeRoots);
            treeNode =null;
        }
        return treeNode;
    }

}
