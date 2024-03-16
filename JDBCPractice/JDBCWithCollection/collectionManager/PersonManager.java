package JDBC.JDBCWithCollection.collectionManager;

import JDBC.JDBCWithCollection.modules.Color;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface PersonManager<T, R> {
    void setCollection(Set<T> collection);

    Set<T> getCollection();

    void setUniqueIds(Set<R> uniqueIds);

    Set<R> getUniqueIds();

    void add(T t);

    String getInfo();
    void clear ();
    boolean updateById(R id, T t);

    boolean removeById(R id);

    boolean addIfMin(T t);

    boolean removeGreater(T t);

    boolean removeLower(T t);

    boolean removeAllByHairColor(Color color);

    List<T> filterByBirthday(Date birthday);

    Set<Date> descendingBirthday();


}
