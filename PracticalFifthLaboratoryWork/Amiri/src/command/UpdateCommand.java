package command;

import collection.CollectionManager;
import input.InputDataManager;
import modules.Person;

public class UpdateCommand extends Command{
    private CollectionManager collectionManager;
    public UpdateCommand (CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        try {
            int id = Integer.parseInt(getArg());
            InputDataManager dataManager = new InputDataManager();
            Person person = dataManager.readPerson();
            boolean isUpdated = collectionManager.updateById(id,person);
            if(!isUpdated) throw new RuntimeException();
            else System.out.println("Person with id of #"+id+" has been successfully updated");
        }catch (NumberFormatException exception){
            System.err.println("Invalid id");
        }catch (RuntimeException exception){
            System.err.println("Can't execute update");
        }
    }

    @Override
    public String description() {
        return "update the value of a collection element whose id is equal to the given one";
    }
}
