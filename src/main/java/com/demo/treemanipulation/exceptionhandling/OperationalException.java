package com.demo.treemanipulation.exceptionhandling;

import com.demo.treemanipulation.client.RemoveNodePreOrder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This class exception handling for remove node operation
 */
public class OperationalException extends Exception{
    private static final Logger logger = LogManager.getLogger(OperationalException.class);

    public OperationalException(String errorMessage) {

        super(errorMessage);
        logger.error(errorMessage);
    }
}
