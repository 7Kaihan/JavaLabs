package JDBC.JDBCWithCollection.commands;

import JDBC.JDBCWithCollection.modules.Person;

public abstract class AbstractCommand implements Command{
    private String commandName;
    private String arg;
    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getArg() {
        return arg;
    }

    public Person getPerson() {
        return person;
    }
}
