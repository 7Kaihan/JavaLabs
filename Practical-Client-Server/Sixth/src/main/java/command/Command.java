package command;

import common.modules.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Command {
    private String commandName;
    private String commandDescription;
    private CommandAccess commandAccess;
    private CommandType commandType;
    private String stringArgument;
    private Student objectArgument;
    public Command(String commandName, String commandDescription, CommandAccess commandAccess,CommandType commandType){
        this.commandName = commandName;
        this.commandDescription = commandDescription;
        this.commandAccess = commandAccess;
        this.commandType = commandType;
    }
    public abstract String execute();
    public final void setObjectArgument(Student student){
        this.objectArgument = student;
    }
    public final Student getObjectArgument(){
        return objectArgument;
    }
    public final void setStringArgument(String argument){
        this.stringArgument = argument;
    }
    public List argumentGetter(){
        List<Object> list = new ArrayList(2);
        if(needsArgument()&&!needsObjectArgument()){
            System.err.println("object with String argument");
           list.add(getStringArgument());
        }else if(needsArgument()){
            System.err.println("object with String argument and Object argument");
            list.add(getStringArgument());
            list.add(getObjectArgument());
        }else if(needsObjectArgument()){
            System.err.println("object with object argument");
            list.add(getObjectArgument());
        }
        System.err.println("fuck you");
        return list;
    }
    private boolean needsArgument(){
        if(commandType == CommandType.NON_ARGUMENT)
            return false;
         return true;
    }
    private boolean needsObjectArgument(){
        if(commandType == CommandType.ARGUMENT_WITH_OBJECT)
            return true;
        return false;
    }
    private String getStringArgument(){
        return stringArgument;
    }
}
