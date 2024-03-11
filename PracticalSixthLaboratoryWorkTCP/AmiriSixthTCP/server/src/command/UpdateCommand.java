package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;
import modules.Person;

public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        try {
            int id = Integer.parseInt(getArg());
            Person person = getPerson();
            if (!collectionManager.getIds().contains(id)) {
                setResult(ConsoleColor.RED_BACKGROUND + "Collection doesn't contain id " + ConsoleColor.YELLOW_BOLD + "[" + ConsoleColor.RESET + ConsoleColor.RED_BACKGROUND + id + ConsoleColor.YELLOW_BOLD + "]" + ConsoleColor.RESET);
                return;
            }
            boolean isUpdated = collectionManager.updateById(id, person);
            if (!isUpdated) {
                setResult(ConsoleColor.RED_BACKGROUND + "Can't execute update" + ConsoleColor.RESET);
            } else
                setResult(ConsoleColor.GREEN_BACKGROUND + "Person with id of #" + id + " has been successfully updated" + ConsoleColor.RESET);
        } catch (NumberFormatException exception) {
            setResult(ConsoleColor.RED_BACKGROUND + "Invalid id" + ConsoleColor.RESET);
        }
    }

    @Override
    public String description() {
        return "update the value of a collection element whose id is equal to the given one";
    }
}
