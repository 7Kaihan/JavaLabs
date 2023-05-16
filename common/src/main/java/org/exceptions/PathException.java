package org.exceptions;



public class PathException extends FileException {
    public PathException(){
        this("path not found");
    }

    public PathException(String msg) {
        super(msg);
    }
}
