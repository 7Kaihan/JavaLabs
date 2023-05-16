package org.collection;

import org.exceptions.InvalidDataException;
import org.modules.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public interface CollectionManager<T> {
    /**
     * Method for getting collection 's information
     * @return String containing collection 's information
     */
    String getInfo();
    /**
     * Method for generating unique ids
     * @return Integer number representing unique id for t
     */
    int autoId();

    /**
     * Method for adding new Element to the collection
     * @param t
     * Adds a new element to the collection (PriorityQueue)
     */
    void add(T t);

    /**
     * Method for updating the value of the collection element whose id is equal to the given one
     *
     * @param id representing given id
     * @return
     */
    boolean update(Integer id, T t);

    /**
     * Method for removing an element from the collection by its id
     *
     * @param id
     * @return
     */
    boolean removeById(Integer id)throws InvalidDataException;

    /**
     * Method for clearing the collection
     */
    void clear();


    /**
     * Method for returning collection 's head
     * @return Element representing collection 's head
     * @throws org.exceptions.InvalidDataException
     */
    T collectionHead ()throws InvalidDataException;


    /**
     * Method for adding a new element to the collection if its value is greater than the value of the largest element in this collection
     *
     * @param t Representing Object that it's values needs to be compared
     * @return
     */
    boolean addIfMax(T t);

    /**
     * Method for removing from the collection all elements greater than the given
     * @param t Representing object that needs to be compared
     */
    void removeGreater(T t);

    /**
     * Method for displaying number of elements whose expelledStudents field value is equal to the given one
     * @param expelled Representing
     * @return Integer representing number of elements whose expelledStudents field value is equal to the given one
     */
    int countByExpelledStudents(long expelled);

    /**
     * Method for returning the unique values of the formOfEducation field of all elements in the collection
     * @return HashSet containing unique values of the formOfEducation field of all elements in the collection
     */
   LinkedList<Integer> uniqueFormOfEducation();

    /**
     * Method for returning groupAdmin field values f all elements in descending order
     * @return ArrayList containing groupAdmin field values f all elements in descending order
     */
    ArrayList<Person> descendingGroupAdmin();

    /**
     * Method for converting collection into csv formatted String
     * @return String containing Serialized csv formatted collection
     */
    String csvSerializer() throws InvalidDataException;

    /**
     * Method for converting csv string into collection
     * @param csv String containing csv data
     * @return True if deserialization was successful, False if deserialization wasn't successful
     * @throws InvalidDataException
     */
    boolean csvDeserializer(String csv);

    Collection<T> getCollection();
}
