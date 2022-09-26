package com.demo.treemanipulation.utility;

import com.demo.treemanipulation.dao.TreeNode;
import com.demo.treemanipulation.exceptionhandling.InputValidationException;
import com.demo.treemanipulation.exceptionhandling.OperationalException;

import java.util.Vector;

public final class RemoveNodeUtility {

    private RemoveNodeUtility() {
    }

    public static boolean isNodeToBeDeleted(TreeNode treeNode, Object nodeToBeDeleted) {
            return treeNode != null && treeNode.getVal() != null && treeNode.getVal().equals(nodeToBeDeleted);
    }

    public static void  addInSubTreeListIfNodeIsNotNull(TreeNode treeNode, Vector<TreeNode> subTreeRoots) {
        if(treeNode!= null && treeNode.getLeft() != null && treeNode.getLeft().getVal()!=null) {
            subTreeRoots.add(treeNode.getLeft());
        }
        if(treeNode!= null && treeNode.getRight() != null && treeNode.getRight().getVal()!=null) {
            subTreeRoots.add(treeNode.getRight());
        }
    }

    public static boolean checkIfTreeNodeIsNull(TreeNode treeNode) {
        return treeNode == null;
    }

    public static boolean checkIfNodeToBeDeletedIsValid(TreeNode treeNode, Object nodeToBeDeleted) {
       return treeNode!= null && (treeNode.getVal().getClass().equals(nodeToBeDeleted.getClass()));
    }

    public static void validate(TreeNode treeNode, Object nodeToBeDeleted) throws InputValidationException, OperationalException {
        if(RemoveNodeUtility.checkIfTreeNodeIsNull(treeNode) || treeNode.getVal() == null) {
            throw new InputValidationException("Tree is empty");
        }
        if(!RemoveNodeUtility.checkIfNodeToBeDeletedIsValid(treeNode, nodeToBeDeleted))
            throw new OperationalException("Invalid Key to delete");
    }

    public static void addInSubTreeIfNodeIsNotTheDeleteNode( TreeNode treeNode, Object nodeToBeDeleted, Vector<TreeNode> subTreeRoots) {
        if (!checkIfTreeNodeIsNull(treeNode.getLeft()) && !isNodeToBeDeleted(treeNode.getLeft(), nodeToBeDeleted) && treeNode.getLeft().getVal()!= null)
            subTreeRoots.add(treeNode.getLeft());
        if (!checkIfTreeNodeIsNull(treeNode.getRight())  && !isNodeToBeDeleted(treeNode.getRight(), nodeToBeDeleted) && treeNode.getRight().getVal()!= null)
            subTreeRoots.add(treeNode.getRight());
    }

}
