package command;

import collection.CollectionManager;
import input.InputDataManager;
import modules.Person;

public class RemoveLowerCommand extends Command{
    private CollectionManager collectionManager;
    public RemoveLowerCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        InputDataManager dataManager = new InputDataManager();
        Person person = dataManager.readPerson();
        try{
            if(!collectionManager.removeLower(person)) throw new RuntimeException();
            else System.out.println("Element lower than the given one has been successfully deleted");
        }catch (RuntimeException exception){
            System.err.println("Can't remove smaller elements from the collection ");
        }
    }

    @Override
    public String description() {
        return "remove all elements from a collection smaller than a given one";
    }
}
