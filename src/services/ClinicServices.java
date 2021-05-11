package services;

import appointments.Appointment;
import clinicRelated.Medicine;
import clinicRelated.Ward;
import db.Database;
import users.Doctor;
import users.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClinicServices {

    Scanner scanner = new Scanner(System.in);
    CsvReaderWriter csvReaderWriter = CsvReaderWriter.getInstance();

    public void showAllWards() {
        for (Ward ward : Database.dbWard) {
            System.out.println(ward);
            System.out.println("*******************************");
        }
        csvReaderWriter.writeToAudit("Saw all wards");
    }

    public static Ward getWard(int id) {
        for (Ward ward : Database.dbWard) {
            if (ward.getWardId() == id) {
                return ward;
            }
        }
        return null;
    }

    public void addWard(){
        int id = Database.dbWard.size() + 700;
        System.out.println("Ward name: ");
        String name = scanner.next();
        System.out.println("The maximum number of patients per day: ");
        int noPatients = scanner.nextInt();

        Ward ward = new Ward(id,name,noPatients);
        Database.dbWard.add(ward);
        System.out.println("Success!");
        csvReaderWriter.writeToAudit("Added a ward");
        csvReaderWriter.writeToCsv("csv/Ward.csv",ward);

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
        csvReaderWriter.writeToAudit("Saw all medicine");
    }

    public Medicine getMedicine(String name) {
        for (Medicine medicine : Database.dbMedicine) {
            if (medicine.getMedicineName().toLowerCase().equals(name.toLowerCase())) {
                return medicine;
            }
        }
        return null;
    }

    public void addMedicine(){
        System.out.println("Medicine name: ");
        String name = scanner.next();
        System.out.println("Medicine price: ");
        int price = scanner.nextInt();
        System.out.println("Company name: ");
        String companyName = scanner.next();

        Medicine medicine = new Medicine(name,price,companyName);
        Database.dbMedicine.add(medicine);
        System.out.println("Success!");
        csvReaderWriter.writeToAudit("Added medicine");
        csvReaderWriter.writeToCsv("csv/Medicine.csv",medicine);

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

        Doctor doctor = new Doctor(id, password, firstName, lastName, dateBirthformat, phone, email, address, hireDateformat, salary, ward);
        Database.dbDoctor.add(doctor);
        System.out.println("Success!");
        csvReaderWriter.writeToAudit("Added a doctor");
        csvReaderWriter.writeToCsv("csv/Doctor.csv",doctor);
    }

    public void addPatient() {
        int id = Database.dbPatient.size() + 200;
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

        Patient patient = new Patient(id, password, firstName, lastName, dateBirthformat, phone, email, address, blood);
        Database.dbPatient.add(patient);
        System.out.println("Success!");
        csvReaderWriter.writeToAudit("Added a patient");
        csvReaderWriter.writeToCsv("csv/Patient.csv",patient);
    }

}
