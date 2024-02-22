package command;

import collection.CollectionManager;
import input.InputDataManager;
import modules.Person;

public class AddCommand extends Command{
    private CollectionManager collectionManager;
    private InputDataManager dataManager;
    public AddCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        dataManager = new InputDataManager();
    }
    @Override
    public void execute() {
        Person person = dataManager.readPerson();
        collectionManager.add(person);
    }

    @Override
    public String description() {
        return "add a new element to the collection";
    }
}
