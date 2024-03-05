package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;

import java.util.concurrent.atomic.AtomicReference;

public class PrintFieldDescendingBirthdayCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public PrintFieldDescendingBirthdayCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {

        if (collectionManager.descendingBirthday().size() == 0) {
            setResult(ConsoleColor.RED_BACKGROUND+"No birthdays to display"+ConsoleColor.RESET);
            return;
        }
        AtomicReference<String> descendingBirthdays = new AtomicReference<>("");
        collectionManager.descendingBirthday().forEach(b -> {
            if (b != null)
                descendingBirthdays.set(descendingBirthdays.get()+"\n"+ConsoleColor.CYAN_BOLD+b+ConsoleColor.RESET);
        });
        setResult(descendingBirthdays.get());
    }

    @Override
    public String description() {
        return "display the birthday field values of all elements in descending order";
    }
}
