package menu;

import services.ClinicServices;
import services.DoctorServices;
import services.PatientServices;

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
            System.out.println("2. Add a ward");
            System.out.println("3. Show all medicines");
            System.out.println("4. Add medicine");
            System.out.println("5. Show all doctors");
            System.out.println("6. Add a doctor");
            System.out.println("7. Show all patients");
            System.out.println("8. Add a patient");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    cs.showAllWards();
                    break;
                }
                case 2: {
                    cs.addWard();
                    break;
                }
                case 3: {
                    cs.showAllMedicine();
                    break;
                }
                case 4: {
                    cs.addMedicine();
                    break;
                }
                case 5: {
                    ds.showAllDoctors();
                    break;
                }
                case 6: {
                    cs.addDoctor();
                    break;
                }
                case 7: {
                    ps.showAllPatients();
                    break;
                }
                case 8: {
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
