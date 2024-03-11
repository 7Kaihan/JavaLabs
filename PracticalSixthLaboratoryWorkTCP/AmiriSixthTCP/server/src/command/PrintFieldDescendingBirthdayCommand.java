package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class PrintFieldDescendingBirthdayCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public PrintFieldDescendingBirthdayCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {

        if (collectionManager.descendingBirthday().size() == 0) {
            setResult(ConsoleColor.RED_BACKGROUND + "No birthdays to display" + ConsoleColor.RESET);
            return;
        }
        AtomicReference<String> descendingBirthdays = new AtomicReference<>("");
        collectionManager.descendingBirthday().forEach(b -> {
            if (b != null) {
                descendingBirthdays.set(descendingBirthdays.get() + "\n" + ConsoleColor.CYAN_BOLD + getActualYear(b) + ConsoleColor.RESET + ConsoleColor.YELLOW_BOLD + "-" + ConsoleColor.RESET + ConsoleColor.CYAN_BOLD + getActualMonth(b) + ConsoleColor.RESET + ConsoleColor.YELLOW_BOLD + "-" + ConsoleColor.RESET + ConsoleColor.CYAN_BOLD + b.getDate() + ConsoleColor.RESET);
            }
        });
        setResult(descendingBirthdays.get());
    }

    @Override
    public String description() {
        return "display the birthday field values of all elements in descending order";
    }

    private String getActualMonth(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM", Locale.US);
        String month = sdf.format(date);
        return month;
    }
    private String getActualYear (Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy",Locale.US);
        String year = sdf.format(date);
        return year;
    }
}
