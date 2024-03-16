package commands;


import collectionManager.PersonManager;
import colorizedConsole.ConsoleColor;
import modules.Person;

public class AddCommand extends AbstractCommand{
    private PersonManager<Person,Integer> collectionManager;
    public AddCommand (PersonManager<Person,Integer> collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        if (getPerson() == null){
            setResult(ConsoleColor.RED_BACKGROUND+"No object has been passed"+ConsoleColor.RESET);
            return;
        }
        Person person = getPerson();
        collectionManager.add(person);
        setResult(ConsoleColor.GREEN_BACKGROUND+"New object that is added to the collection is :"+ConsoleColor.RESET+"\n"+person);
    }

    @Override
    public String getDescription() {
        return "adds new element to the collection";
    }
}
