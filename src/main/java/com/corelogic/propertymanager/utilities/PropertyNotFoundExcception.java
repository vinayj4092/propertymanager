package com.corelogic.propertymanager.utilities;

/**
 * This class is used to indicate that a Property does not exist in the Database
 */
public class PropertyNotFoundExcception extends Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public PropertyNotFoundExcception(String message) {
        super(message);
    }
}
