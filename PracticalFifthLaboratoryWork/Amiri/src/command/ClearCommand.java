package command;

import collection.CollectionManager;

public class ClearCommand extends Command{
    private CollectionManager collectionManager;
    public ClearCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        try{
            if(collectionManager.getCollection().size() == 0) throw new RuntimeException("No elements in collection to be deleted ");
            collectionManager.clear();
            if(collectionManager.getCollection().size() == 0) System.out.println("Elements from the collection has been successfully cleared");
        }catch (RuntimeException exception){
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public String description() {
        return "clear collection";
    }
}
