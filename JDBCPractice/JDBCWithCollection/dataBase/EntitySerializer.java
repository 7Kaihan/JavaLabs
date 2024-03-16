package JDBC.JDBCWithCollection.dataBase;

import JDBC.JDBCWithCollection.colorizedConsole.ConsoleColor;
import JDBC.JDBCWithCollection.modules.Coordinates;
import JDBC.JDBCWithCollection.modules.Location;
import JDBC.JDBCWithCollection.modules.Person;
import java.sql.*;

public final class EntitySerializer {
    private final Connection connection;
    public EntitySerializer (Connection connection){
        this.connection = connection;
    }
    public void addPerson (Person person){
        try {
            int coordinatesId = addCoordinates(connection,person.getCoordinates());
            int locationId = addLocation(connection,person.getLocation());
            String addPersonQuery = "INSERT INTO Person (name,coordinates_id,creationDate,height,birthday,weight,hairColor,location_id) " +
                    "VALUES (?,?,?,?,?,?,?,?)";
            if (isEmptyTable(connection,"Person")) restartSequence(connection,"Person");
            PreparedStatement preparedStatement = connection.prepareStatement(addPersonQuery);
            preparedStatement.setString(1,person.getName());
            preparedStatement.setInt(2,coordinatesId);
            preparedStatement.setTimestamp(3,new Timestamp(person.getCreationDate().toInstant().toEpochMilli()));
            preparedStatement.setLong(4,person.getHeight());
            preparedStatement.setDate(5,new Date(person.getBirthday().getTime()));
            preparedStatement.setFloat(6,person.getWeight());
            preparedStatement.setString(7,person.getHairColor().name());
            preparedStatement.setInt(8,locationId);
            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected < 1) System.out.println(ConsoleColor.RED_BACKGROUND+"couldn't add Person to database"+ConsoleColor.RESET);
            else System.out.println(ConsoleColor.GREEN_BACKGROUND+"Person has been added to database"+ConsoleColor.RESET);
        }catch (SQLException exception){
            System.out.println(ConsoleColor.RED_BOLD+"Person"+ConsoleColor.RESET);
            System.out.println(ConsoleColor.RED_BACKGROUND+exception.getMessage()+ConsoleColor.RESET);
        }
    }
    private int addCoordinates (Connection connection, Coordinates coordinates){
        try {
            if (isEmptyTable(connection,"Coordinates")) restartSequence(connection,"Coordinates");
            String addCoordinatesQuery = "INSERT INTO Coordinates (x,y) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(addCoordinatesQuery,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,coordinates.getX());
            preparedStatement.setInt(2,coordinates.getY());
            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected < 1) System.out.println(ConsoleColor.RED_BACKGROUND+"couldn't add Coordinates to database"+ConsoleColor.RESET);
            else System.out.println(ConsoleColor.GREEN_BACKGROUND+"Coordinates has been added to database"+ConsoleColor.RESET);

           ResultSet resultSet = preparedStatement.getGeneratedKeys();
           resultSet.next();
           return resultSet.getInt(1);
        }catch (SQLException exception){
            System.out.println(ConsoleColor.RED_BOLD+"Coordinates"+ConsoleColor.RESET);
            System.out.println(ConsoleColor.RED_BACKGROUND+exception.getMessage()+ConsoleColor.RESET);
        }
        return 0;
    }
    private int addLocation(Connection connection, Location location){
        try{
            if (isEmptyTable(connection,"Location")) restartSequence(connection,"Location");
            String addLocationQuery = "INSERT INTO  Location (x,y,z) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(addLocationQuery,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,location.getX());
            preparedStatement.setDouble(2,location.getY());
            preparedStatement.setLong(3,location.getZ());
            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected < 1) System.out.println(ConsoleColor.RED_BACKGROUND+"couldn't add Location to database"+ConsoleColor.RESET);
            else System.out.println(ConsoleColor.GREEN_BACKGROUND+"Location has been added to database"+ConsoleColor.RESET);

            ResultSet resultSet =  preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        }catch (SQLException exception){
            System.out.println(ConsoleColor.RED_BOLD+"Location"+ConsoleColor.RESET);
            System.out.println(ConsoleColor.RED_BACKGROUND+exception.getMessage()+ConsoleColor.RESET);
        }
        return 0;
    }
    private boolean isEmptyTable (Connection connection,String tableName){
        try{
            String checkIfEmpty = "SELECT COUNT (*) FROM "+tableName.toLowerCase();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(checkIfEmpty);
            resultSet.next();
            return resultSet.getInt(1) == 0;
        }catch (SQLException exception){
            System.out.println(ConsoleColor.RED_BACKGROUND+exception.getMessage()+ConsoleColor.RESET);
        }
        return false;
    }
    private void restartSequence(Connection connection, String tableName){
        try{
            String sequence = tableName.toLowerCase()+"_id_seq";
            String query = "ALTER SEQUENCE "+sequence+" RESTART WITH 1";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException exception){
            System.out.println(ConsoleColor.RED_BACKGROUND+exception.getMessage()+ConsoleColor.RESET);
        }
    }
}
