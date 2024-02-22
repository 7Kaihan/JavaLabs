package command;

import collection.CollectionManager;
import modules.Color;

public class RemoveAllByHairColorCommand extends Command {
    private CollectionManager collectionManager;

    public RemoveAllByHairColorCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        try {
            Color color = Color.valueOf(getArg().toUpperCase());
            if(!collectionManager.removeAllByHairColor(color)) throw new RuntimeException();
            else System.out.println("All elements which their hair color value were #"+getArg()+"# have been removed successfully");
        } catch (IllegalArgumentException exception) {
            System.err.println("Invalid color");
        } catch (RuntimeException exception) {
            System.err.println("Can't remove all elements ");
        }
    }

    @Override
    public String description() {
        return "remove from the collection all elements whose hairColor field value is equivalent to the given one";
    }
}
