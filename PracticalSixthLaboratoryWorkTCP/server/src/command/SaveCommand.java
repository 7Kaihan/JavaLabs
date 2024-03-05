package command;

import collection.CollectionManager;
import colorizedConsole.ConsoleColor;
import file.FileManager;
import serializer.Serializer;

public class SaveCommand extends AbstractCommand {
    private FileManager fileManager;
    private CollectionManager collectionManager;
    public SaveCommand(FileManager fileManager, CollectionManager collectionManager){
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        Serializer serializer = new Serializer(collectionManager);
        String xml = serializer.toXML();
        if(!fileManager.write(xml)) System.out.println(ConsoleColor.RED_BACKGROUND+"Couldn't save the collection in the file"+ConsoleColor.RESET);
        else System.out.println(ConsoleColor.GREEN_BACKGROUND+"Collection has been saved to file successfully"+ConsoleColor.RESET);
    }

    @Override
    public String description() {
        return null;
    }
}
