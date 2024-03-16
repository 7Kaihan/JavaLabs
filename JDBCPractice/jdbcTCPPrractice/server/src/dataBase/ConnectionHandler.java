package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionHandler {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String hostName;
    private final String databaseName;
    private final String userName;
    private final String password;
    private Connection connection;
    public ConnectionHandler(String hostName, String databaseName, String userName, String password){
        this.hostName = hostName;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
        initialize(hostName,databaseName,userName,password);
    }
    private void initialize (String hostName,String databaseName,String userName,String password){
        try{
            Class.forName(JDBC_DRIVER);
            String url = "jdbc:postgresql://"+hostName+":5432/"+databaseName;
            connection = DriverManager.getConnection(url,userName,password);
        }catch (SQLException exception){
            System.err.println("Couldn't connect to database");
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't load the JDBC driver");
        }
    }
    public Connection getConnection (){
        return connection;
    }

}
