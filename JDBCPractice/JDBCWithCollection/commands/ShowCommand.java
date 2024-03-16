package JDBC.JDBCWithCollection.commands;

import JDBC.JDBCWithCollection.collectionManager.PersonManager;
import JDBC.JDBCWithCollection.colorizedConsole.ConsoleColor;
import JDBC.JDBCWithCollection.modules.Person;

public class ShowCommand extends AbstractCommand{
    private final PersonManager<Person,Integer> collectionManager;
    public ShowCommand(PersonManager<Person,Integer> collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        if (collectionManager.getCollection().size() == 0){
            System.out.println(ConsoleColor.YELLOW_BACKGROUND+"No elements to show"+ConsoleColor.RESET);
            return;
        }
        collectionManager.getCollection().forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "display all elements stored in the collection";
    }
}
