package collection;

import modules.Color;
import modules.Person;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionManager {
    private HashSet<Person> collection;
    private HashSet<Integer> ids;
    private ZonedDateTime initializationDate;
    public CollectionManager(){
        this.collection = new HashSet<>();
        this.ids = new HashSet<>();
        initializationDate = ZonedDateTime.now();
    }

    public void setCollection(HashSet<Person> collection) {
        this.collection = collection;
    }

    public HashSet<Person> getCollection() {
        return collection;
    }

    public void add (Person person){
        person.setId(generateNextId());
        collection.add(person);
        System.out.println(person);
    }

    public String collectionInfo (){
        return "HashSet of Person (HashSet<Person>) with size of #"+collection.size()+" initialized in ["+initializationDate+"]";
    }
    private int generateNextId () {
        if (collection.isEmpty())
            return 1;
        else {
            Integer id = getLastElement().getId() + 1;
            if (ids.contains(id)) {
                while (ids.contains(id)) id += 1;
            }
            ids.add(id);
            return id;
        }
    }
    private Person getLastElement (){
        ArrayList<Person> array = new ArrayList<>(collection);
        return array.get(array.size()-1);
    }
    public boolean updateById (int id,Person person){
        boolean isUpdated = false;
        Optional<Person> personToUpdate = collection.stream().filter(p->p.getId()==id).findAny();
        if(personToUpdate.isPresent()){
            Person p1 = personToUpdate.get();
            p1.setName(person.getName());
            p1.setHairColor(person.getHairColor());
            p1.setLocation(person.getLocation());
            p1.setWeight(person.getWeight());
            p1.setBirthday(person.getBirthday());
            p1.setCreationDate(person.getCreationDate());
            p1.setHeight(person.getHeight());
            p1.setCoordinates(person.getCoordinates());
            isUpdated = true;
        }
        return isUpdated;
    }
    public boolean removeById (int id){
        return collection.removeIf(p -> p.getId().compareTo(id) == 0);
    }
    public void clear (){
        collection.clear();
    }
    public boolean addIfMin (Person person){
        boolean isAdded = false;
        Optional<Person> minPerson = collection
                .stream()
                .filter(p -> p.compareTo(person)>=1 )
                .findAny();
        if(minPerson.isPresent()) {
            add(person);
            isAdded = true;
        }
        return isAdded;
    }
    public boolean removeGreater (Person person){
        return collection.removeIf(p -> p.compareTo(person) > 0);
    }
    public boolean removeLower (Person person){
        return collection.removeIf(p -> p.compareTo(person) < 0);
    }
    public boolean removeAllByHairColor (Color color){
        ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.values()));
        if(colors.contains(color)){
            collection.removeIf(p -> p.getHairColor().name().equals(color.name()));
            return true;
        }
        return false;
    }
    public List<Person> filterByBirthday (Date birthday){
        return collection
                .stream()
                .filter(p -> p.getBirthday().compareTo(birthday) == 0)
                .collect(Collectors.toList());
    }
    public List<Date> descendingBirthday (){
        List<Date> dates = new ArrayList<>();
        collection.forEach(p->{
            dates.add(p.getBirthday());
        });
        Collections.reverse(dates);
        return dates;
    }
}
