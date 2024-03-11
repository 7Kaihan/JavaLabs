package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;
import modules.Person;

public class RemoveGreaterCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        Person person = getPerson();
        if (!collectionManager.removeGreater(person)) {
            setResult(ConsoleColor.RED_BACKGROUND+"Couldn't remove greater elements from the collection"+ConsoleColor.RESET);
        }
        else setResult(ConsoleColor.GREEN_BACKGROUND+"Elements greater than the given one has been successfully deleted"+ConsoleColor.RESET);

    }

    @Override
    public String description() {
        return "Remove all elements from a collection larger than a given one";
    }
}
