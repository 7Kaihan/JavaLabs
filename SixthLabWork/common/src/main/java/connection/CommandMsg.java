package connection;

import Modules.StudyGroup;

import java.io.Serializable;

public class CommandMsg implements Request{
    private String commandName;
    private String commandStringArgument;
    private Serializable commandObjectArgument;

    public CommandMsg(String commandName,String commandStringArgument,Serializable commandObjectArgument){
        this.commandName = commandName;
        this.commandStringArgument = commandStringArgument;
        this.commandObjectArgument = commandObjectArgument;
    }
    @Override
    public String getStringArg() {
        return null;
    }

    @Override
    public StudyGroup getStudyGroup() {
        return null;
    }

    @Override
    public String getCommandName() {
        return null;
    }
}
