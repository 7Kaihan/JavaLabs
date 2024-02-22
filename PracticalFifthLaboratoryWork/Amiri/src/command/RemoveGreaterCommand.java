package command;

import collection.CollectionManager;
import input.InputDataManager;
import modules.Person;

public class RemoveGreaterCommand extends Command {
    private CollectionManager collectionManager;
    public RemoveGreaterCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        InputDataManager dataManager = new InputDataManager();
        Person person = dataManager.readPerson();
        try {
            if (!collectionManager.removeGreater(person)) throw new RuntimeException();
            else System.out.println("Elements greater than the given one has been successfully deleted");
        }catch (RuntimeException exception){
            System.err.println("Can't remove greater elements");
        }
    }

    @Override
    public String description() {
        return "Remove all elements from a collection larger than a given one";
    }
}
