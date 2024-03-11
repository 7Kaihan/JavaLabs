package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;
import modules.Person;

public class AddIfMinCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    public AddIfMinCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        Person person = getPerson();
            if (!collectionManager.addIfMin(person)) setResult(ConsoleColor.RED_BACKGROUND+"Couldn't add person to the collection"+ConsoleColor.RESET);
            else setResult(ConsoleColor.GREEN_BACKGROUND+"Person successfully added to the collection"+ConsoleColor.RESET+"\n"+getPerson());
    }

    @Override
    public String description() {
        return "add a new element to a collection if its value is less than the smallest element of this collection";
    }
}
