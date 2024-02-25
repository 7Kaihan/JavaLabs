package runner;

import command.Command;
import command.CommandManager;
import connection.Request;
import modules.Person;

public class Runner {
    private Request request;
    public Runner(Request request) {
        this.request = request;
    }

    public String  run (CommandManager commandManager){
        String res ="";
        try {
            String command = request.getCommandName();
            String arg = request.getCommandArg();
            Person person = request.getPerson();
            Command c = commandManager.getCommandMap().get(command);
            c.setArg(arg);
            c.setPerson(person);
            c.execute();
            res= c.getResult();
        }catch (NullPointerException exception){
            exception.printStackTrace();
        }
        return res;
    }

}
