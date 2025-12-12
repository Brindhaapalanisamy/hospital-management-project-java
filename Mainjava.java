import java.sql.Connection;

public class Mainjava {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("Connected to MySQL!");
        } else {
            System.out.println("Connection failed!");
        }
    }
}
