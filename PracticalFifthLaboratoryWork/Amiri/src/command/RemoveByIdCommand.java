package command;

import collection.CollectionManager;

import java.util.concurrent.atomic.AtomicBoolean;

public class RemoveByIdCommand extends Command{
    private CollectionManager collectionManager;
    public RemoveByIdCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        try{
            System.out.println(getArg());
            int id = Integer.parseInt(getArg());
           if(!collectionManager.removeById(id)) throw new RuntimeException();
        }catch (NumberFormatException exception){
            System.err.println("Invalid id");
        }catch (RuntimeException exception){
            System.err.println("Can't remove element from the collection");
        }
    }

    @Override
    public String description() {
        return "remove an element from a collection by its id";
    }
}
