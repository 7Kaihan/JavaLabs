package command;

import modules.Person;

public abstract class AbstractCommand implements Command{
    private String arg;
    private Person person;
    private String result;
    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult(){
        return result;
    }
    public Person getPerson (){
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
