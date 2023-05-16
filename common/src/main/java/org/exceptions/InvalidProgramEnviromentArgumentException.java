package org.exceptions;

/**
 * thrown when arguments passed by command line are invalid
 */
public class InvalidProgramEnviromentArgumentException extends InvalidDataException{
    public InvalidProgramEnviromentArgumentException(String s){
        super(s);
    }
}
