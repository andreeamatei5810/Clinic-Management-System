package Menu;

import Appointments.Appointment;
import Services.PatientServices;

import java.util.Scanner;

public class Menu {
    int userId;
    String role;
    AccountMenu accountMenu;
    AppointmentMenu appointmentMenu;
    ClinicMenu clinicMenu;
    PatientServices ps = new PatientServices();
    Scanner scanner = new Scanner(System.in);

    public Menu(int userId, String role) {
        this.userId = userId;
        this.role = role;
        this.accountMenu = new AccountMenu(userId,role);
        this.appointmentMenu = new AppointmentMenu(userId,role);
        this.clinicMenu = new ClinicMenu();
    }

    public void showOptions(){
        System.out.println("Write your choice:");
        System.out.println("0. Exit");
        System.out.println("1. Account Settings");
        System.out.println("2. Appointments");
        System.out.println("3. Details of a Lab Result");
        if(role.equals("Admin")){
            System.out.println("4. Clinic services");
        }
    }

    public void menu(){
        int option = -1;
        while(option!=0) {
            showOptions();
            option = scanner.nextInt();
            switch(option){
                case 1:{
                    accountMenu.menu();
                    break;
                }
                case 2:{
                    if(role.equals("Patient")){
                        appointmentMenu.patientMenu();
                    }
                    else if(role.equals("Doctor")){
                        appointmentMenu.doctorMenu();
                    }
                    else{
                        appointmentMenu.adminMenu();
                    }
                    break;
                }
                case 3:{
                    System.out.println("Write the id of the lab test");
                    int id = scanner.nextInt();
                    ps.showLabResults(id);
                    break;
                }
                case 4:{
                    if (role.equals("Admin")) {
                        clinicMenu.menu();
                    }
                    else{
                        System.out.println("Invalid input!");
                    }
                }
                case 0:{
                    System.out.println("Thank you for using this app!");
                    break;
                }
                default:{
                    System.out.println("Invalid option!");
                }
            }
        }
    }
}
