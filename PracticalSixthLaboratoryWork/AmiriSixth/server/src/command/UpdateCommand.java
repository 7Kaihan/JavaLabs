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
            Person person = getPerson();
            boolean isUpdated = collectionManager.updateById(id,person);
            if(!isUpdated) {
                setResult("Can't execute update");
            }
            else setResult("Person with id of #"+id+" has been successfully updated");
        }catch (NumberFormatException exception){
            setResult("Invalid id");
        }
    }

    @Override
    public String description() {
        return "update the value of a collection element whose id is equal to the given one";
    }
}
