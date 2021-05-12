package services;

import db.Database;
import users.Doctor;
import users.Patient;

import java.util.Scanner;

public class UserServices {

    Scanner scanner = new Scanner(System.in);
    DoctorServices ds = new DoctorServices();
    PatientServices ps = new PatientServices();
    AuditService auditService= AuditService.getInstance();
    CsvReaderWriter csvReaderWriter = CsvReaderWriter.getInstance();

    public void changePassword(String role, int userId) {
        System.out.println("Enter your new password (at least 4 characters): ");
        String newPassword = scanner.nextLine();

        System.out.println("Confirm your new password: ");
        String confirmedPassword = scanner.nextLine();

        if (newPassword.equals(confirmedPassword) && newPassword.length() > 3) {
            if (role.equals("Doctor")) {
                Doctor doctor = ds.getDoctor(userId);
                doctor.setPassword(newPassword);
                System.out.println("The password has been changed!");
            } else if (role.equals("Patient")) {
                Patient patient = ps.getPatient(userId);
                patient.setPassword(newPassword);
                System.out.println("The password has been changed!");
            } else {
                Database.dbAdmin.setPassword(newPassword);
                System.out.println("The password has been changed!");
            }
            auditService.writeToAudit("Changed user password");
        } else {
            System.out.println("Invalid input!");
        }

    }

    public void changePhoneNumber(String role, int id) {
        System.out.println("Enter your new phone: ");
        String newPhone = scanner.nextLine();
        if (role == "Doctor") {
            Doctor doctor = ds.getDoctor(id);
            doctor.setPhoneNumber(newPhone);
            System.out.println("The phone number has been changed!");
        } else if (role.equals("Patient")) {
            Patient patient = ps.getPatient(id);
            patient.setPhoneNumber(newPhone);
            System.out.println("The phone number has been changed!");
        } else {
            Database.dbAdmin.setPhoneNumber(newPhone);
            System.out.println("The phone number has been changed!");
        }
        auditService.writeToAudit("Changed user phone number");
    }


}
