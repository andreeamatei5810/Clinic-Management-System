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
            System.out.println("3. Change maximum number of patients for a ward");
            System.out.println("4. Delete a ward");
            System.out.println("5. Show all medicines");
            System.out.println("6. Add medicine");
            System.out.println("7. Update medicine price");
            System.out.println("8. Delete medicine");
            System.out.println("9. Show all doctors");
            System.out.println("10. Add a doctor");
            System.out.println("11. Delete a doctor");
            System.out.println("12. Show all patients");
            System.out.println("13. Add a patient");
            System.out.println("14. Delete a patient");
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
                    cs.updateWardPatients();
                    break;
                }
                case 4: {
                    cs.deleteWard();
                    break;
                }
                case 5: {
                    cs.showAllMedicine();
                    break;
                }
                case 6: {
                    cs.addMedicine();
                    break;
                }
                case 7: {
                    cs.updateMedicinePrice();
                    break;
                }
                case 8: {
                    cs.deleteMedicine();
                    break;
                }
                case 9: {
                    ds.showAllDoctors();
                    break;
                }
                case 10: {
                    cs.addDoctor();
                    break;
                }
                case 11: {
                    cs.deleteDoctor();
                    break;
                }
                case 12: {
                    ps.showAllPatients();
                    break;
                }
                case 13: {
                    cs.addPatient();
                    break;
                }
                case 14: {
                    cs.deletePatient();
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
