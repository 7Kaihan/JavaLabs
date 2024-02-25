package command;

import collection.CollectionManager;
import input.InputDataManager;
import modules.Person;

public class RemoveLowerCommand extends Command {
    private CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        Person person = getPerson();
        if (!collectionManager.removeLower(person))
            setResult("Can't remove smaller elements from the collection");
        else setResult("Element lower than the given one has been successfully deleted");
    }

    @Override
    public String description() {
        return "remove all elements from a collection smaller than a given one";
    }
}
