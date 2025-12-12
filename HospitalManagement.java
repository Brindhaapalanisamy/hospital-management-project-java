import java.util.*;

class Patient {
    int id;
    String name;
    int age;
    String disease;

    public Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public String toString() {
        return id + " | " + name + " | " + age + " | " + disease;
    }
}

class Doctor {
    int id;
    String name;
    String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public String toString() {
        return id + " | " + name + " | " + specialization;
    }
}

class Appointment {
    int patientId;
    int doctorId;
    String date;

    public Appointment(int p, int d, String date) {
        this.patientId = p;
        this.doctorId = d;
        this.date = date;
    }

    public String toString() {
        return "Patient: " + patientId + ", Doctor: " + doctorId + ", Date: " + date;
    }
}

public class HospitalManagement {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n==== Hospital Management System ====");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Appointment Management");
            System.out.println("4. Billing");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: patientMenu(); break;
                case 2: doctorMenu(); break;
                case 3: appointmentMenu(); break;
                case 4: billingMenu(); break;
                case 5: return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // -------------------- PATIENT MODULE --------------------
    static void patientMenu() {
        System.out.println("\n--- Patient Menu ---");
        System.out.println("1. Add Patient");
        System.out.println("2. View Patients");
        System.out.println("3. Search Patient");
        System.out.println("4. Delete Patient");
        System.out.print("Choice: ");

        int ch = sc.nextInt();
        switch (ch) {
            case 1: addPatient(); break;
            case 2: viewPatients(); break;
            case 3: searchPatient(); break;
            case 4: deletePatient(); break;
            default: System.out.println("Invalid!");
        }
    }

    static void addPatient() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Disease: ");
        String d = sc.nextLine();

        patients.add(new Patient(id, name, age, d));
        System.out.println("Patient Added.");
    }

    static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No Patients.");
            return;
        }
        for (Patient p : patients) System.out.println(p);
    }

    static void searchPatient() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (Patient p : patients) {
            if (p.id == id) {
                System.out.println(p);
                return;
            }
        }
        System.out.println("Patient not found.");
    }

    static void deletePatient() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Iterator<Patient> it = patients.iterator();
        while (it.hasNext()) {
            if (it.next().id == id) {
                it.remove();
                System.out.println("Patient deleted.");
                return;
            }
        }
        System.out.println("Not found.");
    }

    // -------------------- DOCTOR MODULE --------------------
    static void doctorMenu() {
        System.out.println("\n--- Doctor Menu ---");
        System.out.println("1. Add Doctor");
        System.out.println("2. View Doctors");
        System.out.println("3. Search Doctor");
        System.out.println("4. Delete Doctor");
        System.out.print("Choice: ");

        int ch = sc.nextInt();
        switch (ch) {
            case 1: addDoctor(); break;
            case 2: viewDoctors(); break;
            case 3: searchDoctor(); break;
            case 4: deleteDoctor(); break;
            default: System.out.println("Invalid!");
        }
    }

    static void addDoctor() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Specialization: ");
        String sp = sc.nextLine();

        doctors.add(new Doctor(id, name, sp));
        System.out.println("Doctor Added.");
    }

    static void viewDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No Doctors.");
            return;
        }
        for (Doctor d : doctors) System.out.println(d);
    }

    static void searchDoctor() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (Doctor d : doctors) {
            if (d.id == id) {
                System.out.println(d);
                return;
            }
        }
        System.out.println("Doctor not found.");
    }

    static void deleteDoctor() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Iterator<Doctor> it = doctors.iterator();
        while (it.hasNext()) {
            if (it.next().id == id) {
                it.remove();
                System.out.println("Doctor deleted.");
                return;
            }
        }
        System.out.println("Not found.");
    }

    // -------------------- APPOINTMENT MODULE --------------------
    static void appointmentMenu() {
        System.out.println("\n--- Appointment Menu ---");
        System.out.println("1. Create Appointment");
        System.out.println("2. View Appointments");
        System.out.println("3. Cancel Appointment");
        System.out.print("Choice: ");

        int ch = sc.nextInt();
        switch (ch) {
            case 1: createAppointment(); break;
            case 2: viewAppointments(); break;
            case 3: cancelAppointment(); break;
            default: System.out.println("Invalid!");
        }
    }

    static void createAppointment() {
        System.out.print("Patient ID: ");
        int pid = sc.nextInt();
        System.out.print("Doctor ID: ");
        int did = sc.nextInt();
        sc.nextLine();
        System.out.print("Date (DD-MM-YYYY): ");
        String date = sc.nextLine();

        appointments.add(new Appointment(pid, did, date));
        System.out.println("Appointment Created.");
    }

    static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No Appointments.");
            return;
        }
        for (Appointment a : appointments) System.out.println(a);
    }

    static void cancelAppointment() {
        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();

        Iterator<Appointment> it = appointments.iterator();
        while (it.hasNext()) {
            if (it.next().patientId == pid) {
                it.remove();
                System.out.println("Appointment Cancelled.");
                return;
            }
        }
        System.out.println("Not found.");
    }

    // -------------------- BILLING MODULE --------------------
    static void billingMenu() {
        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();

        System.out.print("Consultation Fee: ");
        double c = sc.nextDouble();
        System.out.print("Test Fee: ");
        double t = sc.nextDouble();
        System.out.print("Room Charge: ");
        double r = sc.nextDouble();

        double total = c + t + r;

        System.out.println("\n--- BILLING INVOICE ---");
        System.out.println("Patient ID: " + pid);
        System.out.println("Total Amount: " + total);
    }
}
