package command;

import collection.CollectionManager;

import java.util.concurrent.atomic.AtomicReference;

public class PrintFieldDescendingBirthdayCommand extends Command {
    private CollectionManager collectionManager;

    public PrintFieldDescendingBirthdayCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {

        if (collectionManager.descendingBirthday().size() == 0) {
            setResult("No birthdays to display");
            return;
        }
        AtomicReference<String> descendingBirthdays = new AtomicReference<>("");
        collectionManager.descendingBirthday().forEach(b -> descendingBirthdays.set(descendingBirthdays.get()+"\n"+b));
        setResult(descendingBirthdays.get());
    }

    @Override
    public String description() {
        return "display the birthday field values of all elements in descending order";
    }
}
