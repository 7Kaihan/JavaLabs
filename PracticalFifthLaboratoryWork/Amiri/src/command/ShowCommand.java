package command;

import collection.CollectionManager;

public class ShowCommand extends Command{
    private CollectionManager collectionManager;
    public ShowCommand (CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        try {
            if (collectionManager.getCollection().size() == 0) throw new RuntimeException();
        }catch (RuntimeException exception){
            System.err.println("There are no elements in the collection");
        }
        collectionManager.getCollection().forEach(System.out::println);
    }

    @Override
    public String description() {
        return "Print to standard output all the elements of a collection in string representation";
    }
}
