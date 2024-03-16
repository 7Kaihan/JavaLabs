package commands;


import colorizedConsole.ConsoleColor;

public class HelpCommand extends AbstractCommand{
    private CommandManager commandManager;
    private static  String tempResult = "";
    public HelpCommand (CommandManager commandManager){
        this.commandManager = commandManager;
    }
    @Override
    public void execute() {
        commandManager.getCommandMap().forEach(HelpCommand::addToTempResult);
        setResult(tempResult);
        tempResult = "";
    }
    private static  void addToTempResult (String c,AbstractCommand abstractCommand){
        tempResult += ConsoleColor.CYAN_BOLD+c+":"+ConsoleColor.RESET+abstractCommand.getDescription()+"\n";
    }
    @Override
    public String getDescription() {
        return "displays help on available commands";
    }
}
