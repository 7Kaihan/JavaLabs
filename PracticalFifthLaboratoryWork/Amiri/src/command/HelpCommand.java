package command;

public class HelpCommand extends Command{
    private CommandManager commandManager;
    public HelpCommand (CommandManager commandManager){
        this.commandManager = commandManager;
    }
    @Override
    public void execute() {
        commandManager.getCommandMap().forEach((s,c)->{
            System.out.println(s+" ".repeat(36-s.length())+":"+c.description());
        });
    }

    @Override
    public String description() {
        return "Display help on available commands";
    }
}
