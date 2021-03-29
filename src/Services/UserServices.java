package Services;

import DB.Database;
import Users.Doctor;
import Users.Patient;
import Users.User;

import java.util.Scanner;

public class UserServices {

    Scanner scanner = new Scanner(System.in);

    public void changePassword(String role, int userId) {
        System.out.println("Enter your new password (at least 4 characters): ");
        String newPassword = scanner.nextLine();

        System.out.println("Confirm your new password: ");
        String confirmedPassword = scanner.nextLine();

        if (newPassword.equals(confirmedPassword) && newPassword.length() > 3) {
            if (role == "Doctor") {
                for(Doctor doctor : Database.dbDoctor){
                    if(doctor.getUserId() == userId){
                        doctor.setPassword(newPassword);
                        System.out.println("The password has been changed!");
                        break;
                    }
                }
            }
            else{
                for(Patient patient : Database.dbPatient){
                    if(patient.getUserId() == userId){
                        patient.setPassword(newPassword);
                        System.out.println("The password has been changed!");
                        break;
                    }
                }
            }
        }
        else{
            System.out.println("Invalid input!");
        }

    }



}
