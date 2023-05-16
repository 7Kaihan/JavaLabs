package org.commands;


import org.collection.StudyGroupCollectionManager;
import org.exceptions.*;

public class RemoveByIdCommand extends CommandImpl{
    private StudyGroupCollectionManager collectionManager;
    public RemoveByIdCommand(StudyGroupCollectionManager cm){
        super("remove_by_id",CommandType.NORMAL);
        collectionManager = cm;
    }


    @Override
    public String execute() throws InvalidDataException {
        if(collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException("collection is empty");
        if(!hasStringArg()) throw new MissedCommandArgumentException();
        Integer id = Integer.parseInt(getStringArg());
        if(!collectionManager.isIdInCollection(id)) throw new InvalidCommandArgumentException("no such id");

        boolean success = collectionManager.removeById(id);
        if (success) return "element #" + Integer.toString(id) + " removed";
        else throw new CommandException("cannot remove");
    }
    
}
