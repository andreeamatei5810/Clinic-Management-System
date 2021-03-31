package Services;

import Appointments.Appointment;
import ClinicRelated.Medicine;
import ClinicRelated.Ward;
import DB.Database;
import Users.Doctor;
import Users.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class ClinicServices {

    Scanner scanner = new Scanner(System.in);

    public void showAllWards() {
        for (Ward ward : Database.dbWard) {
            System.out.println(ward);
            System.out.println("*******************************");
        }
    }

    public Ward getWard(int id) {
        for (Ward ward : Database.dbWard) {
            if (ward.getWardId() == id) {
                return ward;
            }
        }
        return null;
    }

    public Ward getWardByName(String name) {
        for (Ward ward : Database.dbWard) {
            if (ward.getWardName().toLowerCase().equals(name.toLowerCase())) {
                return ward;
            }
        }
        return null;
    }

    public boolean checkWardAvailability(int wardId, Date date) {
        Ward ward = getWard(wardId);
        int count = 0;
        for (Appointment appointment : Database.dbAppointment) {
            if (appointment.getDoctor().getWard() == ward) {
                count++;
            }
        }
        if (count < ward.getMaxPatientsPerDay()) {
            return true;
        } else {
            return false;
        }
    }

    public void showAllMedicine() {
        for (Medicine medicine : Database.dbMedicine) {
            System.out.println(medicine);
            System.out.println("*******************************");
        }
    }

    public Medicine getMedicine(String name) {
        for (Medicine medicine : Database.dbMedicine) {
            if (medicine.getMedicineName().toLowerCase().equals(name.toLowerCase())) {
                return medicine;
            }
        }
        return null;
    }

    public void addDoctor() {
        int id = Database.dbDoctor.size() + 100;
        String password = "abcd";

        System.out.println("First name:");
        String firstName = scanner.next();

        System.out.println("Last name:");
        String lastName = scanner.next();

        System.out.println("Date of birth (format mm-dd-yyyy):");
        String dateBirth = scanner.next();
        String patternDate = "MM-dd-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
        Date dateBirthformat = null;
        try {
            dateBirthformat = dateFormat.parse(dateBirth);
        } catch (ParseException exception) {
            System.out.println("Invalid birth date!");
        }
        if (dateBirthformat == null) {
            return;
        }

        System.out.println("Phone:");
        String phone = scanner.next();

        System.out.println("Email:");
        String email = scanner.next();

        System.out.println("Address:");
        String address = scanner.next();

        System.out.println("Hire date (format mm-dd-yyyy):");
        String hireDate = scanner.next();
        Date hireDateformat = null;
        try {
            hireDateformat = dateFormat.parse(hireDate);
        } catch (ParseException exception) {
            System.out.println("Invalid hire date!");
        }
        if (hireDateformat == null) {
            return;
        }

        System.out.println("Ward name:");
        String wardName = scanner.next();
        Ward ward = getWardByName(wardName);
        if (ward == null) {
            System.out.println("There is no ward with this name!");
            return;
        }

        System.out.println("Salary:");
        int salary = scanner.nextInt();

        Database.dbDoctor.add(new Doctor(id, password, firstName, lastName, dateBirthformat, phone, email, address, hireDateformat, salary, ward));
        System.out.println("Succes!");
    }

    public void addPatient() {
        int id = Database.dbDoctor.size() + 100;
        String password = "abcd";

        System.out.println("First name:");
        String firstName = scanner.next();

        System.out.println("Last name:");
        String lastName = scanner.next();

        System.out.println("Date of birth (format mm-dd-yyyy):");
        String dateBirth = scanner.next();
        String patternDate = "MM-dd-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
        Date dateBirthformat = null;
        try {
            dateBirthformat = dateFormat.parse(dateBirth);
        } catch (ParseException exception) {
            System.out.println("Invalid birth date!");
        }
        if (dateBirthformat == null) {
            return;
        }

        System.out.println("Phone:");
        String phone = scanner.next();

        System.out.println("Email:");
        String email = scanner.next();

        System.out.println("Address:");
        String address = scanner.next();

        System.out.println("Blood type:");
        String blood = scanner.next();


        Database.dbPatient.add(new Patient(id, password, firstName, lastName, dateBirthformat, phone, email, address, blood));
        System.out.println("Succes!");
    }

}
