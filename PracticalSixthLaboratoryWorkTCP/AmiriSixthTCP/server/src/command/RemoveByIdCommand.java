package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;

public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        try {
            int id = Integer.parseInt(getArg());
            if (!collectionManager.getIds().contains(id)) {
                setResult(ConsoleColor.RED_BACKGROUND + "Collection doesn't contain id " + ConsoleColor.YELLOW_BOLD + "[" + ConsoleColor.RESET + ConsoleColor.RED_BACKGROUND + id + ConsoleColor.YELLOW_BOLD + "]" + ConsoleColor.RESET);
                return;
            }
            if (!collectionManager.removeById(id))
                setResult(ConsoleColor.RED_BACKGROUND + "Can't remove element from the collection" + ConsoleColor.RESET);
            else
                setResult(ConsoleColor.GREEN_BACKGROUND + "Person with the id of #" + getArg() + "# has been successfully removed" + ConsoleColor.RESET);
        } catch (NumberFormatException exception) {
            setResult(ConsoleColor.RED_BACKGROUND + "Invalid id" + ConsoleColor.RESET);
        }
    }

    @Override
    public String description() {
        return "remove an element from a collection by its id";
    }
}
