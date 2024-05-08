import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Lab10","root","qazwsx2005");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}