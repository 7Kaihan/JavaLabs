package command;

import collection.CollectionManager;
import common.modules.Student;

public class AddCommand extends Command{
   private CollectionManager<Student> collectionManager;
    public AddCommand(CollectionManager<Student> collectionManager) {
        super("add", "adds elements to the collection", CommandAccess.NORMAL, CommandType.NON_ARGUMENT);
        this.collectionManager = collectionManager;
    }
    @Override
    public String execute() {
        try {
            Student student = getObjectArgument();
            if(student == null) return "fuck you";
            else {
                boolean success = collectionManager.addElement(student);
                if (success) return "object has been successfully added to the collection";
                else return "object s addition to the collection wasn't successful";
            }
        }catch (IndexOutOfBoundsException exception){
            System.err.println("array Index out of bound");
        }
        return "invalid object received, fuck you with this kind of sending the object";
    }
}
