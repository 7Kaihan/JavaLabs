package org.commands;

import org.collection.CollectionManager;
import org.collection.StudyGroupCollectionManager;
import org.exceptions.CommandException;
import org.exceptions.InvalidDataException;

public class AddCommand extends CommandImpl{
    private StudyGroupCollectionManager collectionManager;
    public AddCommand(StudyGroupCollectionManager cm){
        super("add",CommandType.NORMAL);
        collectionManager = cm;
    }
    public CollectionManager getManager(){
        return collectionManager;
    }

    @Override
    public String execute() throws InvalidDataException, CommandException {
        getManager().add(getStudyGroupArg());
        return "Added element: " + getStudyGroupArg().toString();
    }
}
