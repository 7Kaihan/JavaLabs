package JDBC.JDBCWithCollection.commands;

import JDBC.JDBCWithCollection.colorizedConsole.ConsoleColor;

public class HelpCommand extends AbstractCommand{
    private CommandManager commandManager;
    public HelpCommand (CommandManager commandManager){
        this.commandManager = commandManager;
    }
    @Override
    public void execute() {
        commandManager.getCommandMap().forEach(HelpCommand::print);
    }
    private static void print (String commandName,AbstractCommand command){
        System.out.println(ConsoleColor.BLUE_BOLD+commandName+":"+ConsoleColor.RESET+ConsoleColor.YELLOW_BOLD+command.getDescription()+ConsoleColor.RESET);
    }
    @Override
    public String getDescription() {
        return "displays help on available commands";
    }
}
