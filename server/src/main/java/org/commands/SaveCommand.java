package org.commands;


import org.collection.StudyGroupCollectionManager;
import org.exceptions.FileException;
import org.file.FileManager;

public class SaveCommand extends CommandImpl {
    FileManager fileManager;
    StudyGroupCollectionManager collectionManager;
    public SaveCommand(StudyGroupCollectionManager cm, FileManager fm){
        super("save",CommandType.SERVER_ONLY);
        collectionManager = cm;
        fileManager = fm;
    }
    @Override
    public String execute() throws FileException {
        if(hasStringArg()) {
            fileManager.setPath(getStringArg());
        };
        fileManager.write(collectionManager.csvSerializer());
        return "collection successfully saved";
    }
}
