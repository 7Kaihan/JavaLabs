package deserializer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import modules.Person;

import java.util.*;

public class Deserializer {
    private HashSet<Person> collection;
    private HashSet<Integer> ids;
    public Deserializer (){
        collection = new HashSet<>();
        ids = new HashSet<>();
    }
    public HashSet<Person> deserialize (String xml){
        XStream xStream = new XStream();
        xStream.alias("person", Person.class);
        xStream.alias("persons", Set.class);
        xStream.addPermission(AnyTypePermission.ANY);
        HashSet<Person> persons = (HashSet<Person>) xStream.fromXML(xml,HashSet.class);
        persons.forEach(p ->{
            ids.add(p.getId());
        });
        return persons;
    }
    public HashSet<Integer> getIds() {
        return ids;
    }
}
