package org.commands;


import org.client.Client;
import org.connection.AnswerMsg;
import org.connection.Request;
import org.connection.Status;
import org.exceptions.ConnectionException;
import org.exceptions.ConnectionTimeoutException;
import org.exceptions.InvalidDataException;

/**
 * command manager for client
 */
public class ClientCommandManager extends CommandManager{
    private Client client;
    public ClientCommandManager(Client c){
        client = c;
        addCommand(new ExecuteScriptCommand(this));
        addCommand(new ExitCommand());
        addCommand(new HelpCommand());
    }

    public Client getClient(){
        return client;
    }
    @Override
    public AnswerMsg runCommand(Request msg) {
        AnswerMsg res = new AnswerMsg();
        if (hasCommand(msg)){
            Command cmd =  getCommand(msg);
            cmd.setArgument(msg);
            res = (AnswerMsg)cmd.run();
        } else {
            try{
                client.send(msg);
                res = (AnswerMsg)client.receive();
            }
            catch (ConnectionTimeoutException e){
                res.info("no attempts left, shutting down").setStatus(Status.EXIT);
            }
            catch(InvalidDataException | ConnectionException e){
                res.error(e.getMessage());
            }
        }
        System.out.println(res);
        return res;
    }
}
