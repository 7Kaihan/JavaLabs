package commands;

import connection.Request;
import connection.Response;

public interface Commandable {

    default void addCommand(String key, Command cmd) {
        addCommand(key, cmd);
    }

    void addCommand(Command cmd);

    Response runCommand(Command cmd);

    Command getCommand(String key);

    default Command getCommand(Request request) {
        return getCommand(request.getCommandName());
    }

    boolean hasCommand(String s);

    default boolean hasCommand(Request request) {
        return hasCommand(request.getCommandName());
    }

    void consoleMode();

    void fileMode();
}
