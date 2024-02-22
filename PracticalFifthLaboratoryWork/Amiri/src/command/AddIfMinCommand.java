package command;

import collection.CollectionManager;
import input.InputDataManager;
import modules.Person;

public class AddIfMinCommand extends Command{
    private CollectionManager collectionManager;
    public AddIfMinCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        InputDataManager dataManager = new InputDataManager();
        Person person = dataManager.readPerson();
        try {
            if (!collectionManager.addIfMin(person)) throw new RuntimeException();
        }catch (RuntimeException exception){
            System.err.println("Can't add new element");
        }
    }

    @Override
    public String description() {
        return "add a new element to a collection if its value is less than the smallest element of this collection";
    }
}
