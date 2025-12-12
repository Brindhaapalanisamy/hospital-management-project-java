import java.util.*;
import java.sql.*;

public class PatientService {

    Scanner sc = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Back");
            System.out.print("Choice: ");

            int ch = Integer.parseInt(sc.nextLine());

            if (ch == 5) return;

            switch (ch) {
                case 1: addPatient(); break;
                case 2: viewPatients(); break;
                case 3: updatePatient(); break;
                case 4: deletePatient(); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addPatient() {
        try {
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Gender: ");
            String gender = sc.nextLine();

            System.out.print("Disease: ");
            String disease = sc.nextLine();

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO patients(name, age, gender, disease) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, disease);

            ps.executeUpdate();
            System.out.println("Patient added successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void viewPatients() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM patients");
            ResultSet rs = ps.executeQuery();

            System.out.println("\nID | Name | Age | Gender | Disease");
            System.out.println("--------------------------------------");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getInt("age") + " | " +
                    rs.getString("gender") + " | " +
                    rs.getString("disease")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void updatePatient() {
        try {
            System.out.print("Enter Patient ID to update: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("New Name: ");
            String name = sc.nextLine();

            System.out.print("New Age: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("New Gender: ");
            String gender = sc.nextLine();

            System.out.print("New Disease: ");
            String disease = sc.nextLine();

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE patients SET name=?, age=?, gender=?, disease=? WHERE id=?"
            );

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, disease);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Patient updated successfully.");
            else System.out.println("Patient ID not found.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void deletePatient() {
        try {
            System.out.print("Enter Patient ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM patients WHERE id=?"
            );
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Patient deleted successfully.");
            else System.out.println("Patient ID not found.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
