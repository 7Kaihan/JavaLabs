package org.commands;


import org.collection.StudyGroupCollectionManager;
import org.exceptions.FileException;
import org.file.FileManager;

public class LoadCommand extends CommandImpl {
    FileManager fileManager;
    StudyGroupCollectionManager collectionManager;
    public LoadCommand(StudyGroupCollectionManager cm, FileManager fm){
        super("load",CommandType.SERVER_ONLY);
        collectionManager = cm;
        fileManager = fm;
    }
    @Override
    public String execute() throws FileException {
        if(hasStringArg()) {
            fileManager.setPath(getStringArg());
        };
        collectionManager.csvDeserializer(fileManager.read());
        return "collection successfully loaded";
    }
}
