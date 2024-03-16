package JDBC.JDBCWithCollection.commands;

import JDBC.JDBCWithCollection.collectionManager.PersonManager;
import JDBC.JDBCWithCollection.colorizedConsole.ConsoleColor;
import JDBC.JDBCWithCollection.modules.Person;

public class RemoveByIdCommand extends AbstractCommand {
    private PersonManager<Person, Integer> collectionManager;

    public RemoveByIdCommand(PersonManager<Person, Integer> collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        if (getArg() == null){
            System.out.println(ConsoleColor.RED_BACKGROUND+"No id has passed to be removed"+ConsoleColor.RESET);
            return;
        }
            try {
                int id = Integer.parseInt(getArg());
                if (!collectionManager.getUniqueIds().contains(id)) {
                    System.out.println(ConsoleColor.RED_BACKGROUND+"Such id doesn't exists"+ConsoleColor.RESET);
                    return;
                }
                if (!collectionManager.removeById(id)) {
                    System.out.println(ConsoleColor.RED_BACKGROUND + "Couldn't remove element" + ConsoleColor.RESET);
                } else
                    System.out.println(ConsoleColor.GREEN_BACKGROUND + "Element has been removed from the collection" + ConsoleColor.RESET);
            } catch (NumberFormatException exception) {
                System.out.println(ConsoleColor.RED_BACKGROUND + "Invalid Id" + ConsoleColor.RESET);
            }
    }

    @Override
    public String getDescription() {
        return "remove an element with the given id";
    }
}
