package commands;


import collectionManager.PersonManager;
import colorizedConsole.ConsoleColor;
import modules.Person;

public class ShowCommand extends AbstractCommand{
    private final PersonManager<Person,Integer> collectionManager;
    private static String showTemp = "";
    public ShowCommand(PersonManager<Person,Integer> collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        if (collectionManager.getCollection().size() == 0){
            setResult(ConsoleColor.YELLOW_BACKGROUND+"No elements to show"+ConsoleColor.RESET);
            return;
        }
        collectionManager.getCollection().forEach(ShowCommand::addToShowTemp);
        setResult(showTemp);
        showTemp = "";
    }
    private static void addToShowTemp (Person person){
        showTemp += person +"\n";
    }
    @Override
    public String getDescription() {
        return "display all elements stored in the collection";
    }
}
