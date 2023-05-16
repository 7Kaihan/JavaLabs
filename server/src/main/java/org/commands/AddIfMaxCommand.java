package org.commands;

import org.collection.StudyGroupCollectionManager;
import org.exceptions.CommandException;

public class AddIfMaxCommand extends CommandImpl{
    private StudyGroupCollectionManager collectionManager;
    public AddIfMaxCommand(StudyGroupCollectionManager cm){
        super("add_if_max",CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute(){
        boolean success = collectionManager.addIfMax(getStudyGroupArg());
        if (success) return ("Added element: " + getStudyGroupArg().toString());
        else throw new CommandException("cannot add");
    }

}
