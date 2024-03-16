package collectionManager;

import colorizedConsole.ConsoleColor;
import modules.Color;
import modules.Person;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class PersonCollectionManager implements PersonManager<Person, Integer> {
    private Set<Person> collection;
    private Set<Integer> uniqueIds;
    private ZonedDateTime initializationDate;

    public PersonCollectionManager() {
        collection = new HashSet<>();
        uniqueIds = new HashSet<>();
        initializationDate = ZonedDateTime.now();
    }
    @Override
    public void setCollection(Set<Person> collection) {
        this.collection = collection;
    }

    @Override
    public Set<Person> getCollection() {
        return collection;
    }

    @Override
    public void setUniqueIds(Set<Integer> uniqueIds) {
        this.uniqueIds = uniqueIds;
    }

    @Override
    public Set<Integer> getUniqueIds() {
        return uniqueIds;
    }

    private int generateNextId (){
        if (uniqueIds.isEmpty()){
            uniqueIds.add(1);
            return 1;
        } else {
            int lastId = collection
                    .stream()
                    .mapToInt(Person::getId)
                    .max()
                    .orElse(Integer.MIN_VALUE) + 1;
            uniqueIds.add(lastId);
            return lastId;
        }
    }
    @Override
    public void add(Person person) {
        person.setId(generateNextId());
        collection.add(person);
    }

    @Override
    public void clear() {
        collection.clear();
        uniqueIds.clear();
    }

    @Override
    public String getInfo() {
        return ConsoleColor.MAGENTA_BOLD + "HashSet of Person" + ConsoleColor.RESET + "(HashSet<Person>) with" + ConsoleColor.MAGENTA_BOLD + " size of #" + ConsoleColor.RESET + collection.size() + ConsoleColor.MAGENTA_BOLD + " initialized in [" + ConsoleColor.RESET + initializationDate + ConsoleColor.MAGENTA_BOLD + "]" + ConsoleColor.RESET;
    }

    @Override
    public boolean updateById(Integer id, Person person) {
        Optional<Person> personToBeUpdated = collection
                .stream()
                .filter(p -> p.getId() == 0)
                .findAny();
        if (personToBeUpdated.isPresent()) {
            Person p = personToBeUpdated.get();
            p.setId(person.getId());
            p.setName(person.getName());
            p.setCoordinates(person.getCoordinates());
            p.setHeight(person.getHeight());
            p.setBirthday(person.getBirthday());
            p.setCreationDate(person.getCreationDate());
            p.setHairColor(person.getHairColor());
            p.setWeight(person.getWeight());
            p.setLocation(person.getLocation());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeById(Integer id) {
        return collection.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public boolean addIfMin(Person person) {
        Optional<Person> minPerson = collection
                .stream()
                .filter(p -> p.compareTo(person) > 0)
                .findAny();
        if (minPerson.isPresent()){
            add(person);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeGreater(Person person) {
        return collection.removeIf(p -> p.compareTo(person) > 0);
    }

    @Override
    public boolean removeLower(Person person) {
        return collection.removeIf(p -> p.compareTo(person) < 0);
    }

    @Override
    public boolean removeAllByHairColor(Color color) {
        List <Color> colorList = collection
                .stream()
                .map(Person::getHairColor)
                .filter(Objects::nonNull)
                .toList();
        if (colorList.contains(color)){
            return collection.removeIf(p -> p.getHairColor()!=null && p.getHairColor().name().equals(color.name()));
        }
        return false;
    }

    @Override
    public List<Person> filterByBirthday(Date birthday) {
        return collection
                .stream()
                .filter(p -> p.getBirthday()!=null && p.getBirthday().equals(birthday))
                .collect(Collectors.toList());
    }

    @Override
    public Set<Date> descendingBirthday() {
        Set<Person> sortedPerson = collection
                .stream()
                .filter(p -> p.getBirthday() != null)
                .sorted(Comparator.comparing(Person::getBirthday).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));
        Set<Date> descendingDate = sortedPerson
                .stream()
                .map(Person::getBirthday)
                .collect(Collectors.toSet());
        return descendingDate;
    }
}
