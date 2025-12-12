import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    static final String url = "jdbc:mysql://localhost:3306/hospital_db";
    static final String user = "root";
    static final String pass = "";  // put your real password

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("DB Error: " + e.getMessage());
            return null;
        }
    }
}

