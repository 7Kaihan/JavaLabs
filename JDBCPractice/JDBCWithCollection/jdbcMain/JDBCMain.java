package JDBC.JDBCWithCollection.jdbcMain;

import JDBC.JDBCWithCollection.executor.Executor;

public class JDBCMain {
    public static void main(String[] args) {
        Executor executor = new Executor("localhost","person","postgres","1234");
        executor.execute();
    }
}
