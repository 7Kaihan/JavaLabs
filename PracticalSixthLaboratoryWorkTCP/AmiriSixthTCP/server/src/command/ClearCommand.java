package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;

public class ClearCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        if (collectionManager.getCollection().size() == 0) {
            setResult(ConsoleColor.YELLOW_BACKGROUND+"No elements in collection to be deleted "+ConsoleColor.RESET);
            return;
        }
        collectionManager.clear();
        if (collectionManager.getCollection().size() == 0)
           setResult(ConsoleColor.GREEN_BACKGROUND+"Elements from the collection has been successfully cleared"+ConsoleColor.RESET);
    }

    @Override
    public String description() {
        return "clear collection";
    }
}
