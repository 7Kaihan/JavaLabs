package collection;

import java.util.PriorityQueue;

public interface CollectionManager<T> {
    PriorityQueue<T> getCollection();
    void addElement(T element);
}
