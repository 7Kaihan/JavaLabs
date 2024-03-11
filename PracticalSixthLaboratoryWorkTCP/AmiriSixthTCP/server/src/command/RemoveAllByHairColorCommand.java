package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;
import modules.Color;

public class RemoveAllByHairColorCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveAllByHairColorCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        try {
            Color color = Color.valueOf(getArg().toUpperCase());
            if(!collectionManager.removeAllByHairColor(color)) {
                setResult(ConsoleColor.RED_BACKGROUND+"Collection doesn't contain person with hair color of "+ConsoleColor.YELLOW_BOLD+"["+ConsoleColor.RESET+ConsoleColor.RED_BACKGROUND+getArg()+ConsoleColor.YELLOW_BOLD+"]"+ConsoleColor.RESET);
            }
            else setResult(ConsoleColor.GREEN_BACKGROUND+"All elements which their hair color value were #"+getArg()+"# have been removed successfully"+ConsoleColor.RESET);
        } catch (IllegalArgumentException exception) {
            setResult(ConsoleColor.RED_BACKGROUND+"Invalid color"+ConsoleColor.RESET);
        }
    }

    @Override
    public String description() {
        return "remove from the collection all elements whose hairColor field value is equivalent to the given one";
    }
}
