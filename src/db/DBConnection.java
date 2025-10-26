package db;
import java.sql.*;

public class DBConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bankdb",
                    "root", // your MySQL username
                    ""      // your MySQL password
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
