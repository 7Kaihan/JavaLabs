package org.commands;

import org.exceptions.ExitException;

public class ExitCommand extends CommandImpl {
    public ExitCommand(){
        super("exit", CommandType.NORMAL);
    }
    @Override 
    public String execute(){
        throw new ExitException();
    }
}
