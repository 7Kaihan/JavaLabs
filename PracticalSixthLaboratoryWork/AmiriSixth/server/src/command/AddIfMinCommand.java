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
        Person person = getPerson();
            if (!collectionManager.addIfMin(person)) setResult("Couldn't add person to the collection");
            else setResult("Person successfully added to the collection"+"\n"+getPerson());
    }

    @Override
    public String description() {
        return "add a new element to a collection if its value is less than the smallest element of this collection";
    }
}
