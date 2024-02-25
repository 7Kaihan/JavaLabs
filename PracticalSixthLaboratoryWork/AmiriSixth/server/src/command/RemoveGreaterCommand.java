package command;

import collection.CollectionManager;
import input.InputDataManager;
import modules.Person;

public class RemoveGreaterCommand extends Command {
    private CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        Person person = getPerson();
        if (!collectionManager.removeGreater(person)) {
            setResult("Couldn't remove greater elements from the collection");
        }
        else setResult("Elements greater than the given one has been successfully deleted");

    }

    @Override
    public String description() {
        return "Remove all elements from a collection larger than a given one";
    }
}
