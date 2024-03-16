package JDBC.JDBCWithStudent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

public class JDBCPractice {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
            System.out.println("Connected to database successfully");
            while (true) {
                System.out.println("Enter a command = ");
                String command = new Scanner(System.in).nextLine();
                if (command.equals("exit")) break;
                if (getCommands().contains(command)) {
                    try {
                        if (command.equals("help")) help();
                        else {
                            Method method = JDBCPractice.class.getDeclaredMethod(command, Connection.class);
                            method.invoke(null, connection);
                        }
                    } catch (NoSuchMethodException e) {
                        System.err.println("couldn't invoke "+command);
                    } catch (IllegalAccessException e) {
                        System.err.println("Couldn't access method");
                    }
                }else System.err.println("No such command");
            }
        } catch (SQLException e) {
            System.err.println("Couldn't connect to database");
            System.err.println(e.getMessage());
        }  catch (InvocationTargetException e) {
            System.err.println(e.getMessage());
        }
    }

    private static List<String> getCommands() {
        return new ArrayList<>(Arrays.asList("show", "update", "remove", "help", "clear", "add"));
    }

    private static void show(Connection connection) throws SQLException {
        String showQuery = "SELECT * FROM student";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(showQuery);
        if (!resultSet.next()) {
            System.err.println("Database is empty");
            return;
        }
        do
            System.out.println("Id:" + resultSet.getInt(1) + "\n" + "Name:" + resultSet.getString(2) + "\n" + "LastName:" + resultSet.getString(3) + "\n" + "-".repeat(100));
        while (resultSet.next());
    }
    private static void update(Connection connection) throws SQLException {
        String name = "";
        int id = 0;
        String lastName = "";
        while (true) {
            System.out.println("Enter name that you want to change = ");
            name = new Scanner(System.in).nextLine();
            if (name.equals("")) System.err.println("Can't be empty");
            else break;
        }
        while (true) {
            System.out.println("Enter id = ");
            id = new Scanner(System.in).nextInt();
            if (id <= 0) System.err.println("Can't be zero or less");
            else break;
        }
        while (true) {
            System.out.println("Enter LastName : ");
            lastName = new Scanner(System.in).nextLine();
            if (lastName.equals("")) System.out.println("LastName can't be empty");
            else break;
        }
        update(connection, id, name, lastName);
    }

    private static void update(Connection connection, int id, String name, String lastName) throws SQLException {
        String updateQuery = "UPDATE student SET name = ? ,lastName = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(updateQuery);
        statement.setString(1, name);
        statement.setInt(3, id);
        statement.setString(2, lastName);
        if (statement.executeUpdate() < 1)
            throw new SQLException("Couldn't update the desired record");
        else System.out.println("Record has been successfully updated");
    }

    private static void remove(Connection connection) throws SQLException {
        int id = 0;
        while (true) {
            System.out.println("Enter # id # of student that you want to remove = ");
            id = new Scanner(System.in).nextInt();
            if (id <= 0) System.err.println("Can't be zero or less");
            else break;
        }
        remove(connection, id);
    }

    private static void remove(Connection connection, int id) throws SQLException {
        String removeQuery = "DELETE FROM student WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(removeQuery);
        statement.setInt(1, id);
        if (statement.executeUpdate() < 1) throw new SQLException("Couldn't remove the record");
        else System.out.println("Record with the id of # " + id + " # has been removed successfully");
    }

    private static void help() {
        List<String> helpList = new ArrayList<>(Arrays.asList("help: shows available commands", "add: adds new record to the database", "remove: removes record from the database with the given id", "clear: clears the database", "show: shows all records which are stored in the database"));
        helpList.forEach(System.out::println);
    }

    private static void clear(Connection connection) throws SQLException {
        if (isEmptyDatabase(connection)){
            System.err.println("Database is empty");
            return;
        }
        String clearQuery = "DELETE FROM student";
        Statement statement = connection.createStatement();
        if (statement.executeUpdate(clearQuery) < 1) System.err.println("Couldn't clear the database");
        else System.out.println("Database has been cleared successfully");
    }

    private static void add(Connection connection) throws SQLException {
        String name = "";
        String lastName = "";
        while (true) {
            System.out.println("Enter name : ");
            name = new Scanner(System.in).nextLine();
            if (name.equals("")) System.err.println("Can't be empty");
            else break;
        }
        while (true) {
            System.out.println("Enter lastName : ");
            lastName = new Scanner(System.in).nextLine();
            if (lastName.equals("")) System.err.println("Can't be empty");
            else break;
        }
        add(connection, name, lastName);
    }

    private static void add(Connection connection, String name, String lastName) throws SQLException {
        String addQuery = "INSERT INTO student (name,lastName) VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(addQuery);
        statement.setString(1, name);
        statement.setString(2, lastName);
         if (isEmptyDatabase(connection)) setSequence(connection);
        if (statement.executeUpdate() < 1) throw new SQLException("Couldn't add record to the database");
        else System.out.println("Record has been successfully added to the database");
    }

    private static boolean isEmptyDatabase(Connection connection) throws SQLException {
        String countQuery = "SELECT COUNT(*) FROM student";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(countQuery)) {
            resultSet.next();
            return resultSet.getInt(1) == 0;
        }
    }

    private static void setSequence(Connection connection) throws SQLException {
        String resetSequence = "ALTER SEQUENCE student_id_seq RESTART WITH 1";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(resetSequence);
        }
    }
}
