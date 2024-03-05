package command;

import colorizedConsole.ConsoleColor;

import java.util.concurrent.atomic.AtomicReference;

public class HelpCommand extends AbstractCommand {
    private CommandManager commandManager;
    public HelpCommand(CommandManager commandManager){
        this.commandManager = commandManager;
    }
    @Override
    public void execute() {
        AtomicReference<String> helpList = new AtomicReference<>("");
        commandManager.getCommandMap().forEach((s,c)->{
            helpList.set(helpList.get()+"\n"+ ConsoleColor.BLUE_BOLD+s+":"+ConsoleColor.RESET+""+ConsoleColor.CYAN+c.description()+ConsoleColor.RESET);
        });
        setResult(helpList.get());
    }

    @Override
    public String description() {
        return "Display help on available commands";
    }
}
