package org.commands;

import org.collection.StudyGroupCollectionManager;
import org.exceptions.EmptyCollectionException;
import org.exceptions.InvalidDataException;

public class ClearCommand extends CommandImpl{
    private StudyGroupCollectionManager collectionManager;
    public ClearCommand(StudyGroupCollectionManager cm){
        super("clear",CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute() throws InvalidDataException {
        if(collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException("collection is empty");
        collectionManager.clear();
        return "collection cleared";
    }

}
