package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL non trouvé: " + e.getMessage());
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/primeur", "root", "");
        
    }
}
//java -cp ".;mysql-connector-java-8.0.30.jar"