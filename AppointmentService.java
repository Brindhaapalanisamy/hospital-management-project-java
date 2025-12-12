import java.util.*;
import java.sql.*;

public class AppointmentService {

    Scanner sc = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n--- Appointment Menu ---");
            System.out.println("1. Book Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Back");
            System.out.print("Choice: ");

            int ch = Integer.parseInt(sc.nextLine());

            if (ch == 5) return;

            switch (ch) {
                case 1: bookAppointment(); break;
                case 2: viewAppointments(); break;
                case 3: updateAppointment(); break;
                case 4: deleteAppointment(); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void bookAppointment() {
        try {
            System.out.print("Patient ID: ");
            int pid = Integer.parseInt(sc.nextLine());
            System.out.print("Doctor ID: ");
            int did = Integer.parseInt(sc.nextLine());
            System.out.print("Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO appointments(patient_id, doctor_id, date) VALUES (?, ?, ?)"
            );

            ps.setInt(1, pid);
            ps.setInt(2, did);
            ps.setString(3, date);

            ps.executeUpdate();
            System.out.println("Appointment booked.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void viewAppointments() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT a.id, p.name AS patient, d.name AS doctor, a.date " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.id " +
                "JOIN doctors d ON a.doctor_id = d.id"
            );

            ResultSet rs = ps.executeQuery();

            System.out.println("\nID | Patient | Doctor | Date");
            System.out.println("------------------------------");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("patient") + " | " +
                    rs.getString("doctor") + " | " +
                    rs.getString("date")
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void updateAppointment() {
        try {
            System.out.print("Enter Appointment ID to update: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("New Patient ID: ");
            int pid = Integer.parseInt(sc.nextLine());
            System.out.print("New Doctor ID: ");
            int did = Integer.parseInt(sc.nextLine());
            System.out.print("New Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE appointments SET patient_id=?, doctor_id=?, date=? WHERE id=?"
            );

            ps.setInt(1, pid);
            ps.setInt(2, did);
            ps.setString(3, date);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Appointment updated successfully.");
            else System.out.println("Appointment ID not found.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void deleteAppointment() {
        try {
            System.out.print("Enter Appointment ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM appointments WHERE id=?"
            );
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Appointment deleted successfully.");
            else System.out.println("Appointment ID not found.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
