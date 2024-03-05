package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;

public class AddCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public AddCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        collectionManager.add(getPerson());
        setResult(ConsoleColor.GREEN_BACKGROUND+"New Element has been added to the collection"+ConsoleColor.RESET+"\n"+getPerson());
    }

    @Override
    public String description() {
        return "add a new element to the collection";
    }
}
