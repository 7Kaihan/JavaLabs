package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;
import modules.Person;

public class RemoveLowerCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        Person person = getPerson();
        if (!collectionManager.removeLower(person))
            setResult(ConsoleColor.RED_BACKGROUND+"Can't remove smaller elements from the collection"+ConsoleColor.RESET);
        else setResult(ConsoleColor.GREEN_BACKGROUND+"Element lower than the given one has been successfully deleted"+ConsoleColor.RESET);
    }

    @Override
    public String description() {
        return "remove all elements from a collection smaller than a given one";
    }
}
