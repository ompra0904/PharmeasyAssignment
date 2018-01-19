package pharmeasy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbUtil {
	private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
            	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           	 String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=PharmEasyDB";
           	connection =(Connection) DriverManager.getConnection(JDBC_URL,"sa","password");  //Here khetan is username of DB and Khetan@123 is password of DB
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            return connection;
        }

    }
}

