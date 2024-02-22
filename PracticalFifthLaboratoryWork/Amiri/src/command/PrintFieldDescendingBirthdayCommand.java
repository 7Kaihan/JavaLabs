package command;

import collection.CollectionManager;

public class PrintFieldDescendingBirthdayCommand extends Command{
    private CollectionManager collectionManager;
    public PrintFieldDescendingBirthdayCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        try{
            if(collectionManager.descendingBirthday().size() ==0) throw new RuntimeException("No birthdays to display");
            collectionManager.descendingBirthday().forEach(System.out::println);
        }catch (RuntimeException exception){
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public String description() {
        return "display the birthday field values of all elements in descending order";
    }
}
