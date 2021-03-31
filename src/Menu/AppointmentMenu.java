package Menu;

import Services.AppointmentServices;
import Services.DoctorServices;
import Services.PatientServices;

import java.util.Scanner;

public class AppointmentMenu {
    int userId;
    String role;
    Scanner scanner = new Scanner(System.in);
    AppointmentServices as = new AppointmentServices();
    PatientServices ps = new PatientServices();
    DoctorServices ds = new DoctorServices();

    public AppointmentMenu(int userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public void adminMenu() {
        int option = -1;
        while (option != 0) {
            System.out.println("Write your choice");
            System.out.println("0. Exit");
            System.out.println("1. Make an appointment");
            System.out.println("2. Change the status of an appointment");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    as.makeAppointment(role, userId);
                    break;
                }
                case 2: {
                    System.out.println("Write the appointment id");
                    int appointmentId = scanner.nextInt();
                    as.changeStatus(appointmentId);
                    break;
                }
                case 0: {
                    break;
                }
                default: {
                    System.out.println("Invalid option!");
                }
            }
        }
    }

    public void patientMenu() {
        int option = -1;
        while (option != 0) {
            System.out.println("Write your choice");
            System.out.println("0. Exit");
            System.out.println("1. Make an appointment");
            System.out.println("2. See future appointments");
            System.out.println("3. See details of an appointment");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    as.makeAppointment(role, userId);
                    break;
                }
                case 2: {
                    ps.showFutureAppointments(userId);
                    break;
                }
                case 3: {
                    System.out.println("Write the appointment id");
                    int id = scanner.nextInt();
                    as.seeDetails(id);
                    break;
                }
                case 0: {
                    break;
                }
                default: {
                    System.out.println("Invalid option!");
                }
            }
        }
    }

    public void doctorMenu() {
        int option = -1;
        while (option != 0) {
            System.out.println("Write your choice");
            System.out.println("0. Exit");
            System.out.println("1. Make an appointment");
            System.out.println("2. See future appointments");
            System.out.println("3. See details of an appointment");
            System.out.println("4. Change the status of an appointment");
            System.out.println("5. Write a diagnosis");
            System.out.println("6. Prescribe a treatment");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    as.makeAppointment(role, userId);
                    break;
                }
                case 2: {
                    ds.showFutureAppointments(userId);
                    break;
                }
                case 3: {
                    System.out.println("Write the appointment id");
                    int id = scanner.nextInt();
                    as.seeDetails(id);
                    break;
                }
                case 4: {
                    System.out.println("Write the appointment id");
                    int appointmentId = scanner.nextInt();
                    as.changeStatus(appointmentId);
                    break;
                }
                case 5: {
                    System.out.println("Write the appointment id");
                    int appointmentId = scanner.nextInt();
                    as.makeDiagnosis(appointmentId);
                    break;
                }
                case 6: {
                    System.out.println("Write the appointment id");
                    int appointmentId = scanner.nextInt();
                    as.prescribeTreatment(appointmentId);
                    break;
                }
                case 0: {
                    break;
                }
                default: {
                    System.out.println("Invalid option!");
                }
            }
        }
    }


}
