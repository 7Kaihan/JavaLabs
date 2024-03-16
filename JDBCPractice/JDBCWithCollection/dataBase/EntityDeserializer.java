package JDBC.JDBCWithCollection.dataBase;

import JDBC.JDBCWithCollection.colorizedConsole.ConsoleColor;
import JDBC.JDBCWithCollection.modules.Color;
import JDBC.JDBCWithCollection.modules.Coordinates;
import JDBC.JDBCWithCollection.modules.Location;
import JDBC.JDBCWithCollection.modules.Person;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public final class EntityDeserializer {
    private  Connection connection;
    private Set<Person> collection;
    private Set<Integer> uniqueIds;
    public EntityDeserializer (Connection connection){
            this.connection = connection;
            collection = new ConcurrentSkipListSet<>();
            uniqueIds = new ConcurrentSkipListSet<>();
    }

    public Set<Integer> getUniqueIds() {
        return uniqueIds;
    }

    public Set<Person> deserialize() {
    Set<Person> personSet = new TreeSet<>(Comparator.comparing(Person::getId));
    try (Statement statement = connection.createStatement()) {
        String query = "SELECT * FROM person";
        ResultSet resultSet = statement.executeQuery(query);
        if (!resultSet.next()) {
            return personSet;
        }
        do {
            Person person = getPerson(resultSet);
            if (person != null) {
                personSet.add(person);
                uniqueIds.add(person.getId());
            }
        } while (resultSet.next());
    } catch (SQLException exception) {
        System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage() + ConsoleColor.RESET);
    }
    return personSet;
}

    private Person getPerson(ResultSet resultSet) {
        try {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int coordinatesId = resultSet.getInt(3);
            Timestamp timestamp = resultSet.getTimestamp(4);
            Instant instant = timestamp.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant,zoneId);
            ZonedDateTime creationDate = zonedDateTime;
            Long height = resultSet.getLong(5);
            Date birthday = resultSet.getDate(6);
            Float weight = resultSet.getFloat(7);
            Color color = Color.valueOf(resultSet.getString(8));
            int locationId = resultSet.getInt(9);
            Coordinates coordinates = getCoordinates(coordinatesId);
            Location location = getLocation(locationId);
            Person person = new Person(name, coordinates, creationDate, height, birthday, weight, color, location);
            person.setId(id);
            return person;
        } catch (SQLException exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage() + ConsoleColor.RESET);
            return null;
        }
    }

    private Coordinates getCoordinates(int coordinatesId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Coordinates WHERE id = ?");
            preparedStatement.setInt(1, coordinatesId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Coordinates(resultSet.getLong(1), resultSet.getInt(2));
            }
        } catch (SQLException exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage() + ConsoleColor.RESET);
        }
        return null;
    }

    private Location getLocation(int locationId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Location WHERE id = ?");
            preparedStatement.setInt(1, locationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Location(resultSet.getInt(1), resultSet.getDouble(2), resultSet.getLong(3));
            }
        } catch (SQLException exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage() + ConsoleColor.RESET);
        }
        return null;
    }
}
