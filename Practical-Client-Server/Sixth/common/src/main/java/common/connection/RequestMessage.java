package common.connection;

import common.modules.Student;

import java.io.Serializable;

public class RequestMessage implements Serializable{
    private String commandName;
    private String commandArgument;
    private Student object;
    public RequestMessage(String commandName,String commandArgument,Student object){
        this.commandName = commandName;
        this.commandArgument = commandArgument;
        this.object = object;
    }

    public String getCommandName() {
        return commandName;
    }


    public String getCommandArgument() {
        return commandArgument;
    }


    public Student getObject() {
        return object;
    }
}
