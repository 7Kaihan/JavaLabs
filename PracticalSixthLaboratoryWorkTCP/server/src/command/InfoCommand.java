package command;

import collection.CollectionManager;

public class InfoCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public InfoCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        setResult(collectionManager.collectionInfo());
    }

    @Override
    public String description() {
        return "Print information about the collection (type, initialization date, number of elements, etc.) to standard output.";
    }
}
