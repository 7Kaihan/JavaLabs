package serializer;

import collection.CollectionManager;
import com.thoughtworks.xstream.XStream;
import modules.Person;

import java.util.Set;

public class Serializer {
    private CollectionManager collectionManager;
    public Serializer(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public String toXML (){
        XStream xStream = new XStream();
        xStream.alias("person", Person.class);
        xStream.alias("persons", Set.class);
        return xStream.toXML(collectionManager.getCollection());
    }
}
