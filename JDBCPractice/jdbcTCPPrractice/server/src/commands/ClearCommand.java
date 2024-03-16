package commands;


import collectionManager.PersonManager;
import colorizedConsole.ConsoleColor;
import modules.Person;

public class ClearCommand extends AbstractCommand{
    private PersonManager<Person,Integer> collectionManager;
    public ClearCommand (PersonManager<Person,Integer> collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        if (collectionManager.getCollection().size() == 0){
            setResult(ConsoleColor.RED_BACKGROUND+"Collection is empty"+ConsoleColor.RESET);
            return;
        }
        collectionManager.clear();
        setResult(ConsoleColor.GREEN_BACKGROUND+"Collection has been cleared"+ConsoleColor.RESET);
    }

    @Override
    public String getDescription() {
        return "clears the collection";
    }
}
