package command;

import collection.CollectionManager;

public class RemoveByIdCommand extends Command{
    private CollectionManager collectionManager;
    public RemoveByIdCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        try{
            int id = Integer.parseInt(getArg());
           if(!collectionManager.removeById(id)) setResult("Can't remove element from the collection");
           else setResult("Person with the id of #"+getArg()+"#has been successfully removed");
        }catch (NumberFormatException exception){
           setResult("Invalid id");
        }
    }

    @Override
    public String description() {
        return "remove an element from a collection by its id";
    }
}
