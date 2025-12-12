import java.util.*;
import java.sql.*;

public class DoctorService {

    Scanner sc = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. View Doctors");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Back");
            System.out.print("Choice: ");

            int ch = Integer.parseInt(sc.nextLine());

            if (ch == 5) return;

            switch (ch) {
                case 1: addDoctor(); break;
                case 2: viewDoctors(); break;
                case 3: updateDoctor(); break;
                case 4: deleteDoctor(); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addDoctor() {
        try {
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Specialization: ");
            String spec = sc.nextLine();

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO doctors(name, specialization) VALUES (?, ?)"
            );

            ps.setString(1, name);
            ps.setString(2, spec);

            ps.executeUpdate();
            System.out.println("Doctor added.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void viewDoctors() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM doctors");
            ResultSet rs = ps.executeQuery();

            System.out.println("\nID | Name | Specialization");
            System.out.println("---------------------------");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("specialization")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void updateDoctor() {
        try {
            System.out.print("Enter Doctor ID to update: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("New Name: ");
            String name = sc.nextLine();
            System.out.print("New Specialization: ");
            String spec = sc.nextLine();

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE doctors SET name=?, specialization=? WHERE id=?"
            );

            ps.setString(1, name);
            ps.setString(2, spec);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Doctor updated successfully.");
            else System.out.println("Doctor ID not found.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void deleteDoctor() {
        try {
            System.out.print("Enter Doctor ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM doctors WHERE id=?"
            );
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Doctor deleted successfully.");
            else System.out.println("Doctor ID not found.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
