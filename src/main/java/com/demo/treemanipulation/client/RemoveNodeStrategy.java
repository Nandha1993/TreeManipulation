package com.demo.treemanipulation.client;

import com.demo.treemanipulation.dao.TreeNode;
import com.demo.treemanipulation.exceptionhandling.InputValidationException;
import com.demo.treemanipulation.exceptionhandling.OperationalException;

import java.util.Vector;

/**
 * This is the policy/strategy interface
 * The classes uses this interface and implements the strategy which is interchangeable during run-time.
 * RemoveNode functionality has different variations and can be implemented using this pattern
 */
public interface RemoveNodeStrategy  {

     /**
      * This method implements actual strategies
      * @return
      */
     Vector<TreeNode> removeNode() throws OperationalException, InputValidationException;

}
