package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe utilitaire pour gérer la connexion à la base de données
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
//javac -cp ".;mysql-connector-java-8.0.30.jar" DAO\*.java Controller\*.java Model\*.java view\*.java Main.java