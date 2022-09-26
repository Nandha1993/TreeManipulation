package com.demo.treemanipulation.client;

import com.demo.treemanipulation.dao.TreeNode;
import com.demo.treemanipulation.exceptionhandling.InputValidationException;
import com.demo.treemanipulation.exceptionhandling.OperationalException;
import com.demo.treemanipulation.treehandler.Tree;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * This is the context class of strategy pattern
 */
public class RemoveNodeAndGetRootOfSubTrees {

    private static final Logger logger = LogManager.getLogger(RemoveNodePostOrder.class);

    private RemoveNodeStrategy removeNodeStrategy;

    /**
     * THe strategy is interchangeable at run-time
     * @param removeNodeStrategy
     */
    public RemoveNodeAndGetRootOfSubTrees(RemoveNodeStrategy removeNodeStrategy) {
        logger.info("Initializing the strategy");
        this.removeNodeStrategy = removeNodeStrategy;
    }

    /**
     * Executes the strategy
     * @return Vector of TreeNodes
     * @throws OperationalException
     * @throws InputValidationException
     */
    public Vector<TreeNode> removeNode() throws OperationalException, InputValidationException {
        logger.info("Executing the strategy");
        return removeNodeStrategy.removeNode();
    }

    /**
     * method for retrieving subTrees
     * @param treeNodes
     * @return
     */
    public Vector<Object> getNodeSubTreeRoots( Vector<TreeNode> treeNodes) {

        Vector<Object> subTrees = new Vector<>();
        for(TreeNode treeNode: treeNodes) {
            subTrees.add(treeNode.getVal());
        }
        return subTrees;
    }/**
     * method for retrieving subTrees
     * @param treeNodes
     * @return
     */
    public List<List<Object>> getNodeSubTree( Vector<TreeNode> treeNodes, Tree tree) throws InputValidationException {

        List<List<Object>> subTrees = new ArrayList<>();
        for(TreeNode treeNode: treeNodes) {
            subTrees.add(tree.traverseLevelOrder(treeNode));
        }
        return subTrees;
    }


}
