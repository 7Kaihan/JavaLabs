package command;

import file.FileManager;

public class ExecuteScriptCommand extends Command{
    private FileManager fileManager;
    private CommandManager commandManager;
    public ExecuteScriptCommand (FileManager fileManager, CommandManager commandManager){
        this.fileManager = fileManager;
        this.commandManager = commandManager;
    }
    @Override
    public void execute() {

    }

    @Override
    public String description() {
        return "read and execute the script from the specified file. The script contains commands in the same form in which the user enters them interactively.";
    }
}
