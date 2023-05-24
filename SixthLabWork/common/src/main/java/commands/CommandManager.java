package commands;

import connection.Response;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.Stack;

public class CommandManager implements Commandable, Closeable {
    private Map<String,Command> map;
    private InputManager inputManager;
    private boolean isRunning;
    private String currentScriptFileName;

    private static Stack<String> callStack = new Stack<>();


    @Override
    public void addCommand(Command cmd) {

    }

    @Override
    public Response runCommand(Command cmd) {
        return null;
    }

    @Override
    public Command getCommand(String key) {
        return null;
    }

    @Override
    public boolean hasCommand(String s) {
        return false;
    }

    @Override
    public void consoleMode() {

    }

    @Override
    public void fileMode() {

    }

    @Override
    public void close() throws IOException {

    }
}
