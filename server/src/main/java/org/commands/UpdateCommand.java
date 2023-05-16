package org.commands;

import org.collection.CollectionManager;
import org.exceptions.*;

public class UpdateCommand extends CommandImpl{
    private CollectionManager collectionManager;
    public UpdateCommand(CollectionManager cm){
        super("update",CommandType.NORMAL);
        collectionManager = cm;
    }


    @Override
    public String execute() throws InvalidDataException {
        if(collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException("collection is empty");
        if(!hasStringArg()||!hasStudyGroupArg()) throw new MissedCommandArgumentException();
        Integer id = Integer.parseInt(getStringArg());
        if(!collectionManager.getCollection().contains(id)) throw new InvalidCommandArgumentException("no such id");

        boolean success = collectionManager.update(id,getStudyGroupArg());
        if (success) return "element #" + Integer.toString(id) + " updated";
        else throw new CommandException("cannot update");
    }

}
