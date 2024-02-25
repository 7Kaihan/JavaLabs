package command;

import java.util.concurrent.atomic.AtomicReference;

public class HelpCommand extends Command{
    private CommandManager commandManager;
    public HelpCommand (CommandManager commandManager){
        this.commandManager = commandManager;
    }
    @Override
    public void execute() {
        AtomicReference<String> helpList = new AtomicReference<>("");
        commandManager.getCommandMap().forEach((s,c)->{
            helpList.set(helpList.get()+"\n"+s+" ".repeat(36-s.length())+":"+c.description());
        });
        setResult(helpList.get());
    }

    @Override
    public String description() {
        return "Display help on available commands";
    }
}
