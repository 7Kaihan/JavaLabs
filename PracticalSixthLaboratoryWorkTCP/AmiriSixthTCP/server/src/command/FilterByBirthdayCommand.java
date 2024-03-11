package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;
import modules.Person;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FilterByBirthdayCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterByBirthdayCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        try {
            String[] spilt = getArg().trim().split("-");
            int year = Integer.parseInt(spilt[0]);
            int month = Integer.parseInt(spilt[1]);
            int day = Integer.parseInt(spilt[2]);
            Date date = new Date(year, month, day);
            AtomicReference<String> birthdays = new AtomicReference<>("");
            List<Person> birthdayList = collectionManager.filterByBirthday(date);
            birthdayList.forEach(d -> {
                if (d != null)
                    birthdays.set(birthdays.get() + "\n" + d);
            });
            if (birthdayList.isEmpty()) setResult(ConsoleColor.RED_BACKGROUND+"Couldn't find any match"+ConsoleColor.RESET);
            else setResult(birthdays.get());
        } catch (Exception exception) {
            setResult(ConsoleColor.RED_BACKGROUND + "Invalid date" + ConsoleColor.RESET);
        }
    }

    @Override
    public String description() {
        return "display elements whose birthday field value is equal to the specified one";
    }
}
