package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/primeur", "root", "");
        
    }
}
//java -cp ".;mysql-connector-java-8.0.30.jar"