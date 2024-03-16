package JDBC.JDBCWithCollection.commands;

import JDBC.JDBCWithCollection.collectionManager.PersonManager;
import JDBC.JDBCWithCollection.input.InputDataManager;
import JDBC.JDBCWithCollection.modules.Person;

public class AddCommand extends AbstractCommand{
    private PersonManager<Person,Integer> collectionManager;
    public AddCommand (PersonManager<Person,Integer> collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        InputDataManager inputDataManager = new InputDataManager();
        Person person = inputDataManager.readPerson();
        collectionManager.add(person);
    }

    @Override
    public String getDescription() {
        return "adds new element to the collection";
    }
}
