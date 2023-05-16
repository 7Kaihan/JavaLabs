package org.exceptions;
/**
 * thrown when collection is empty
 */
public class EmptyCollectionException extends CommandException{
    public EmptyCollectionException(String s){
        super("collection is empty");
    }
}
