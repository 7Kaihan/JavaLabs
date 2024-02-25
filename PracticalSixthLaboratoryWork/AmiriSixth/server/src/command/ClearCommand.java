package command;

import collection.CollectionManager;

public class ClearCommand extends Command {
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        if (collectionManager.getCollection().size() == 0) {
            setResult("No elements in collection to be deleted ");
            return;
        }
        collectionManager.clear();
        if (collectionManager.getCollection().size() == 0)
           setResult("Elements from the collection has been successfully cleared");
    }

    @Override
    public String description() {
        return "clear collection";
    }
}
