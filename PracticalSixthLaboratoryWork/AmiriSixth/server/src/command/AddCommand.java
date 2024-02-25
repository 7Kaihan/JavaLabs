package command;

import collection.CollectionManager;
import input.InputDataManager;
import modules.Person;

public class AddCommand extends Command{
    private CollectionManager collectionManager;
    public AddCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        collectionManager.add(getPerson());
        setResult("New Element has been added to the collection"+"\n"+getPerson());
    }

    @Override
    public String description() {
        return "add a new element to the collection";
    }
}
