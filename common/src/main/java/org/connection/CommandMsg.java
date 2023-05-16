package org.connection;

import org.modules.StudyGroup;
import java.io.Serializable;

/**
 * Message witch include command and arguments
 */
public class CommandMsg implements Request{
    private String commandName;
    private String commandStringArgument;
    private Serializable commandObjectArgument;

    public CommandMsg(String commandNm, String commandSA, Serializable commandOA) {
        commandName = commandNm;
        commandStringArgument = commandSA;
        commandObjectArgument = commandOA;
    }

    /**
     * @return Command name.
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * @return Command string argument.
     */
    public String getStringArg() {
        return commandStringArgument;
    }

    /**
     * @return Command object argument.
     */
    public StudyGroup getStudyGroup() {
        return (StudyGroup) commandObjectArgument;
    }
}