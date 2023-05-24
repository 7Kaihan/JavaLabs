package commands;

import connection.AnswerMsg;
import connection.Request;
import connection.Response;
import connection.Status;

import javax.sound.midi.InvalidMidiDataException;
import java.net.ConnectException;

public abstract class CommandImplementation implements Command {
    private CommandType type;
    private String name;
    private Request argument;

    public CommandImplementation(String name, CommandType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public Response run() {
        AnswerMsg res = new AnswerMsg();
        try {
            res.info(execute());
        } catch (Exit exception) {
            res.info(exception.getMessage());
            res.setStatus(Status.EXIT);
        } catch (InvalidDataException | CommandException | FileException | ConnectionException exception) {
            res.error(exception.getMessage());
        }
        return res;
    }
    public abstract String execute()throws InvalidMidiDataException,CommandException,FileException, ConnectException;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public CommandType getType() {
        return type;
    }

    @Override
    public void setArgument(Request a) {
        argument = a;
    }

    public Request getArgument() {
        return argument;
    }

    public boolean hasStringCommand() {
        return argument != null && argument.getStringArg() != null && !argument.getStringArg().equals("");
    }

    public boolean hasStudyGroup() {
        return argument != null && argument.getStudyGroup() != null;
    }

    public String getStringAng() {
        return getArgument().getStringArg();

    }
}
