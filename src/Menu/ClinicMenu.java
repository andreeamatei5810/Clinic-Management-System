package Menu;

import Services.ClinicServices;
import Services.DoctorServices;
import Services.PatientServices;

import java.util.Scanner;

public class ClinicMenu {

    Scanner scanner = new Scanner(System.in);
    ClinicServices cs = new ClinicServices();
    DoctorServices ds = new DoctorServices();
    PatientServices ps = new PatientServices();

    public void menu() {
        int option = -1;
        while (option != 0) {
            System.out.println("Write your choice");
            System.out.println("0. Exit");
            System.out.println("1. Show all wards");
            System.out.println("2. Show all medicines");
            System.out.println("3. Show all doctors");
            System.out.println("4. Add a doctor");
            System.out.println("5. Show all patients");
            System.out.println("6. Add a patient");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    cs.showAllWards();
                    break;
                }
                case 2: {
                    cs.showAllMedicine();
                    break;
                }
                case 3: {
                    ds.showAllDoctors();
                    break;
                }
                case 4: {
                    cs.addDoctor();
                    break;
                }
                case 5: {
                    ps.showAllPatients();
                    break;
                }
                case 6: {
                    cs.addPatient();
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
