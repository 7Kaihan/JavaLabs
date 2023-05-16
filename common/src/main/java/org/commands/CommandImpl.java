package org.commands;

import org.connection.AnswerMsg;
import org.connection.Request;
import org.connection.Response;
import org.connection.Status;
import org.modules.StudyGroup;
import org.exceptions.*;

/**
 * basic command implementation
 */
public abstract class CommandImpl implements Command{
    private CommandType type;
    private String name;
    private Request arg;
    public CommandImpl(String n, CommandType t){
        name = n;
        type = t;
    }
    public CommandType getType(){
        return type;
    }
    public String getName(){
        return name;
    }

    /**
     * custom execute command
     * @return
     * @throws InvalidDataException
     * @throws CommandException
     * @throws FileException
     * @throws ConnectionException
     */
    public abstract String execute() throws InvalidDataException,CommandException, FileException, ConnectionException;

    /**
     * wraps execute into response
     * @return
     */
    public Response run(){
        AnswerMsg res = new AnswerMsg();
        try{
            res.info(execute());
        }
        catch(ExitException e){
            res.info(e.getMessage());
            res.setStatus(Status.EXIT);
        }
        catch(InvalidDataException|CommandException|FileException|ConnectionException e){
            res.error(e.getMessage());
        }
        return res;
    }
    public Request getArgument(){
        return arg;
    }
    public void setArgument(Request req){
        arg=req;
    }
    public boolean hasStringArg(){
        return arg!=null && arg.getStringArg()!=null && !arg.getStringArg().equals("");
    }
    public boolean hasStudyGroupArg(){
        return arg!=null && arg.getStudyGroup()!=null;
    }

    public String getStringArg(){
        return getArgument().getStringArg();
    }

    public StudyGroup getStudyGroupArg(){
        return getArgument().getStudyGroup();
    }
}
