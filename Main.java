import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PatientService ps = new PatientService();
        DoctorService ds = new DoctorService();
        AppointmentService as = new AppointmentService();

        while (true) {
            System.out.println("\n==== Hospital Management System (MySQL) ====");
            System.out.println("1. Patient");
            System.out.println("2. Doctor");
            System.out.println("3. Appointment");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1:
                    ps.menu();
                    break;
                case 2:
                    ds.menu();
                    break;
                case 3:
                    as.menu();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
