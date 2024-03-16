package JDBC.JDBCWithCollection.commands;

import JDBC.JDBCWithCollection.collectionManager.PersonManager;
import JDBC.JDBCWithCollection.colorizedConsole.ConsoleColor;
import JDBC.JDBCWithCollection.modules.Person;

public class ClearCommand extends AbstractCommand{
    private PersonManager<Person,Integer> collectionManager;
    public ClearCommand (PersonManager<Person,Integer> collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        if (collectionManager.getCollection().size() == 0){
            System.out.println(ConsoleColor.RED_BACKGROUND+"Collection is empty"+ConsoleColor.RESET);
            return;
        }
        collectionManager.clear();
        System.out.println(ConsoleColor.GREEN_BACKGROUND+"Collection has been cleared"+ConsoleColor.RESET);
    }

    @Override
    public String getDescription() {
        return "clears the collection";
    }
}
