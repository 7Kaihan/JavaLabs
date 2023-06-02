package collection;

import common.modules.Student;

import java.util.PriorityQueue;

public class StudentCollectionManager implements CollectionManager<Student>{
    private PriorityQueue<Student> collection;
    public StudentCollectionManager(){
        collection = new PriorityQueue<>();
    }
    @Override
    public PriorityQueue<Student> getCollection() {
        return collection;
    }

    @Override
    public boolean addElement(Student element) {
        return collection.add(element);
    }
}
