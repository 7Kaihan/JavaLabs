package command;

import collection.CollectionManager;
import file.FileManager;
import serializer.Serializer;

public class SaveCommand extends Command{
    private CollectionManager collectionManager;
    private FileManager fileManager;
    public SaveCommand(FileManager fileManager,CollectionManager collectionManager){
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        Serializer serializer = new Serializer(collectionManager);
        String xml = serializer.toXML();
        if(!fileManager.write(xml)) System.err.println("Couldn't save data in the file");
        else System.out.println("Data has been successfully saved in file");
    }

    @Override
    public String description() {
        return "save the collection to a file";
    }
}
