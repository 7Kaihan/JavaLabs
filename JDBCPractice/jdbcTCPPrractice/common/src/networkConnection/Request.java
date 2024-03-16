package networkConnection;

import modules.Person;

import java.io.Serializable;

public class Request implements Serializable {
    private String commandName;
    private String arg;
    private Person person;
    public Request (String commandName,String arg,Person person){
        this.commandName = commandName;
        this.arg = arg;
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public String getArg() {
        return arg;
    }

    public String getCommandName() {
        return commandName;
    }

    @Override
    public String toString() {
        return "Request{\n" +
                "       commandName='" + commandName + '\n' +
                "        arg='" + arg + '\n' +
                "       person=" + person + '\n' +
                '}';
    }
}
