package collection;

import java.util.PriorityQueue;

public interface CollectionManager<T> {
    PriorityQueue<T> getCollection();
    boolean addElement(T element);
}
