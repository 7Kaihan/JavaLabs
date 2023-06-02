package common.connection;

import common.modules.Student;

import java.io.Serializable;

public class RequestMessage implements Request{
    private String commandName;
    private String commandArgument;
    private Serializable object;
    public RequestMessage(String commandName,String commandArgument,Serializable object){
        this.commandName = commandName;
        this.commandArgument = commandArgument;
        this.object = object;
    }
    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getCommandArgument() {
        return commandArgument;
    }

    @Override
    public Student getObject() {
        return (Student)object;
    }
}
