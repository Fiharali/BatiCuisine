package config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = System.getenv("db.url");
    private static final String USERNAME = System.getenv("db.user");
    private static final String PASSWORD = System.getenv("db.password");
    private static final String DB_NAME = System.getenv("db.name");
    private static final String DRIVER = System.getenv("db.driver");
    private static Connection connection;

    public static Connection getConnection() {
     //   if (connection == null) {
      //      synchronized (DBConnection.class) {
                    try {
                        Class.forName(DRIVER);
                        connection = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD);

                    } catch (ClassNotFoundException | SQLException e) {
                        throw new RuntimeException("Failed to connect to the database", e);
                    }

       //     }
      //  }

        return connection;
    }

}
