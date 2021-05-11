package test;

import db.Database;
import menu.Menu;
import services.CsvReaderWriter;
import services.DoctorServices;
import services.PatientServices;
import users.Doctor;
import users.Patient;

import java.util.Scanner;

// Patient: email "abc200@yahoo.com", password "abcd"
// Doctor: email "abc100@yahoo.com", password "abcd"
// Admin: email "admin", password "1234"

public class Main {
    public static void main(String[] args) {
        Database.init();
        Scanner scanner = new Scanner(System.in);
        DoctorServices ds = new DoctorServices();
        PatientServices ps = new PatientServices();

        System.out.println("Please write your email");
        String email = scanner.nextLine();
        if (!email.equals("admin") && ds.getDoctorByEmail(email) == null && ps.getPatientByEmail(email) == null) {
            System.out.println("Sorry! Wrong email!");
        } else {
            System.out.println("Please write your password");
            String password = scanner.nextLine();
            if (email.equals("admin") && password.equals("1234")) {
                Menu adminMenu = new Menu(1, "Admin");
                adminMenu.menu();

            } else if (email.equals("admin") && !password.equals("1234")) {
                System.out.println("Wrong password for admin");
            } else if (ds.getDoctorByEmail(email) != null) {
                Doctor doctor = ds.getDoctorByEmail(email);
                if (doctor.getPassword().equals(password)) {
                    Menu doctorMenu = new Menu(doctor.getUserId(), "Doctor");
                    doctorMenu.menu();
                } else {
                    System.out.println("Wrong password!");
                }
            } else {
                Patient patient = ps.getPatientByEmail(email);
                if (patient.getPassword().equals(password)) {
                    Menu patientMenu = new Menu(patient.getUserId(), "Patient");
                    patientMenu.menu();
                } else {
                    System.out.println("Wrong password!");
                }
            }
        }


    }
}
