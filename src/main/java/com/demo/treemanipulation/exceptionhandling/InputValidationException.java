package com.demo.treemanipulation.exceptionhandling;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This Exception class handles input validation errors
 */
public class InputValidationException extends Exception{

    private static final Logger logger = LogManager.getLogger(InputValidationException.class);
    /**
     * this is the constructor that logs exception message
     * @param message
     */
    public InputValidationException(String message) {
        super(message);
        logger.error(message);
    }
}
