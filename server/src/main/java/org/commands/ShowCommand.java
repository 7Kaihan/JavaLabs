package org.commands;


import org.collection.CollectionManager;
import org.exceptions.EmptyCollectionException;

public class ShowCommand extends CommandImpl{
    private CollectionManager collectionManager;
    public ShowCommand(CollectionManager cm){
        super("show",CommandType.NORMAL);
        collectionManager = cm;
    }

    @Override
    public String execute(){
        if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException("collection is empty");
        try {
            return collectionManager.csvSerializer();
        }catch (Exception exception){
            throw new EmptyCollectionException("empty");
        }
    }

}
