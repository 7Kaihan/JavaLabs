package dataBase;

import colorizedConsole.ConsoleColor;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class TablesCreater {

    public void createPersonTable(Connection connection) {
        try {
            createCoordinates(connection);
            createLocation(connection);
            String createPerson = "CREATE TABLE IF NOT EXISTS Person(" +
                    "id SERIAL NOT NULL, " +
                    "name TEXT NOT NULL, " +
                    "coordinates_id INT," +
                    "creationDate TIMESTAMP WITH TIME ZONE NOT NULL," +
                    "height BIGINT," +
                    "birthday DATE," +
                    "weight FLOAT," +
                    "hairColor TEXT," +
                    "location_id INT," +
                    " FOREIGN KEY (coordinates_id) REFERENCES Coordinates(id) ON DELETE CASCADE," +
                    " FOREIGN KEY (location_id) REFERENCES Location(id) ON DELETE CASCADE)";
            try (Statement statement = connection.createStatement()) {
                int databaseEffected = statement.executeUpdate(createPerson);
                if (databaseEffected < 1)
                    System.out.println(ConsoleColor.YELLOW_BACKGROUND + " Person table exists" + ConsoleColor.RESET + "\n");
                else
                    System.out.println(ConsoleColor.GREEN_BACKGROUND + "Person table has been created successfully" + ConsoleColor.RESET + "\n");
            }

        } catch (SQLException exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage()+ConsoleColor.RESET);
        }
    }

    private void createCoordinates(Connection connection) {
        try {
            String createCoordinates = "CREATE TABLE IF NOT EXISTS Coordinates (" +
                    "id SERIAL PRIMARY KEY," +
                    "x BIGINT NOT NULL," +
                    "y INT NOT NULL )";
            try (Statement statement = connection.createStatement()) {
                int databaseEffected = statement.executeUpdate(createCoordinates);
                if (databaseEffected < 1)
                    System.out.println(ConsoleColor.YELLOW_BACKGROUND + " Coordinates table exists" + ConsoleColor.RESET + "\n");
                else
                    System.out.println(ConsoleColor.GREEN_BACKGROUND + "Coordinates table has been created successfully" + ConsoleColor.RESET + "\n");
            }
        } catch (SQLException exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage()+ConsoleColor.RESET);
        }
    }

    private void createLocation(Connection connection) {
        try {
            String createLocation = "CREATE TABLE IF NOT EXISTS Location (" +
                    "id SERIAL PRIMARY KEY, " +
                    "x INT NOT NULL, " +
                    "y DOUBLE PRECISION NOT NULL, " +
                    "z BIGINT NOT NULL )";
            try (Statement statement = connection.createStatement()) {
                int databaseEffected = statement.executeUpdate(createLocation);
                if (databaseEffected < 1)
                    System.out.println(ConsoleColor.YELLOW_BACKGROUND + " Location table exists" + ConsoleColor.RESET + "\n");
                else
                    System.out.println(ConsoleColor.GREEN_BACKGROUND + "Location table has been created successfully" + ConsoleColor.RESET + "\n");
            }
        } catch (SQLException exception) {
            System.out.println(ConsoleColor.RED_BACKGROUND + exception.getMessage()+ConsoleColor.RESET);
        }
    }

    public static TablesCreater getInstance() {
        return new TablesCreater() {
        };
    }
}
