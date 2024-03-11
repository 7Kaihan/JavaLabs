package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;

import java.util.concurrent.atomic.AtomicReference;

public class ShowCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {

        if (collectionManager.getCollection().size() == 0) {
            setResult(ConsoleColor.YELLOW_BACKGROUND+"There are no elements in the collection"+ConsoleColor.RESET);
            return;
        }
        AtomicReference<String> elements = new AtomicReference<>("");
        collectionManager.getCollection().forEach(p -> elements.set(elements.get()+"\n"+p));
        setResult(elements.get());
    }

    @Override
    public String description() {
        return "Print to standard output all the elements of a collection in string representation";
    }
}
