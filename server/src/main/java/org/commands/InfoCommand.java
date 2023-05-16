package org.commands;

import org.collection.StudyGroupCollectionManager;
import org.exceptions.InvalidDataException;

public class InfoCommand extends CommandImpl{
    private StudyGroupCollectionManager collectionManager;
    public InfoCommand(StudyGroupCollectionManager cm){
        super("info",CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute() throws InvalidDataException {
        return collectionManager.getInfo();
    }

}
