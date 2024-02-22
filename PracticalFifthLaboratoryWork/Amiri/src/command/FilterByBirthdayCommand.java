package command;

import collection.CollectionManager;

import java.util.Date;

public class FilterByBirthdayCommand extends Command{
    private CollectionManager collectionManager;
    public FilterByBirthdayCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        try{
            String[] spilt = getArg().trim().split("-");
            int year = Integer.parseInt(spilt[0]);
            int month = Integer.parseInt(spilt[1]);
            int day = Integer.parseInt(spilt[2]);
            Date date = new Date(year,month,day);
            collectionManager.filterByBirthday(date).forEach(System.out::println);
        }catch (RuntimeException exception){
            System.err.println("Invalid date");
        }
    }

    @Override
    public String description() {
        return "display elements whose birthday field value is equal to the specified one";
    }
}
