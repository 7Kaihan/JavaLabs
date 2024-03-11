package connection;

import modules.Person;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    private String commandArg;
    private Person person;
    public Request (String commandName,String commandArg, Person person){
        this.commandName = commandName;
        this.commandArg = commandArg;
        this.person = person;
    }

    public String getCommandName() {
        return commandName;
    }
    public String getCommandArg() {
        return commandArg;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                ", commandArg='" + commandArg + '\'' +
                ", person=" + person +
                '}';
    }
}
