package dataBase;


import collectionManager.PersonCollectionManager;
import modules.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public final class PersonDataBaseManager extends PersonCollectionManager {
    private Connection connection;
    private EntitySerializer serializer;
    public PersonDataBaseManager (Connection connection){
        this.connection = connection;
        serializer = new EntitySerializer(connection);
    }

    @Override
    public void add(Person person) {
        serializer.addPerson(person);
    }

    @Override
    public void clear() {
        try {
            clearCoordinates();
            clearLocation();
            String clearPerson = "DELETE FROM Person";
            Statement statement = connection.createStatement();
            int rowsEffected = statement.executeUpdate(clearPerson);
        }catch (SQLException exception){
            System.err.println("clearPerson");
        }
    }
    private void clearCoordinates (){
        try {
            String clearCoordinates = "DELETE FROM Coordinates";
            Statement statement = connection.createStatement();
            int rowsEffected = statement.executeUpdate(clearCoordinates);
        }catch (SQLException exception){
            //System.err.println("clearCoordinates");
        }
    }
    private void clearLocation (){
        try {
            String clearLocation = "DELETE FROM Location";
            Statement statement = connection.createStatement();
            int rowsEffected = statement.executeUpdate(clearLocation);
        }catch (SQLException exception){
            //System.err.println("clearLocation");
        }
    }

    @Override
    public boolean removeById(Integer id) {
        try{
            String query = "DELETE FROM Person WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            int rowsEffected = preparedStatement.executeUpdate();
            return true;
        }catch (SQLException exception){
            System.out.println("removePerson");
        }
        return false;
    }
}
