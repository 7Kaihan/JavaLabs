package command;

import collection.CollectionManager;
import file.FileManager;
import serializer.Serializer;

public class SaveCommand extends Command{
    private FileManager fileManager;
    private CollectionManager collectionManager;
    public SaveCommand (FileManager fileManager,CollectionManager collectionManager){
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute() {
        Serializer serializer = new Serializer(collectionManager);
        String xml = serializer.toXML();
        if(!fileManager.write(xml)) System.err.println("Couldn't save the collection in the file");
        else System.out.println("Collection has been saved to file successfully");
    }

    @Override
    public String description() {
        return null;
    }
}
