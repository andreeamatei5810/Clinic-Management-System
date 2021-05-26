package services;

import model.appointments.Appointment;
import model.clinicRelated.Medicine;
import model.clinicRelated.Ward;
import db.Database;
import repository.*;
import model.users.Doctor;
import model.users.Patient;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class ClinicServices {

    Scanner scanner = new Scanner(System.in);
    AuditService auditService= AuditService.getInstance();
    CsvReaderWriter csvReaderWriter = CsvReaderWriter.getInstance();
    static WardRepository wardRepository = new WardRepository();
    static MedicineRepository medicineRepository = new MedicineRepository();
    static AppointmentRepository appointmentRepository = new AppointmentRepository();
    static PatientRepository patientRepository = new PatientRepository();
    static DoctorRepository doctorRepository = new DoctorRepository();

    public void showAllWards() {
        for (Ward ward : wardRepository.getAllWards()) {
            System.out.println(ward);
            System.out.println("*******************************");
        }
        auditService.writeToAudit("Saw all wards");
    }

    public static Ward getWard(int id) {
        return wardRepository.getWard(id);
    }

    public void addWard(){
        System.out.println("Ward name: ");
        String name = scanner.next();
        System.out.println("The maximum number of patients per day: ");
        int noPatients = scanner.nextInt();
        Ward ward = new Ward(name,noPatients);
        wardRepository.insertWard(ward);
        System.out.println("Success!");
        auditService.writeToAudit("Added a ward");
   //     csvReaderWriter.writeToCsv("csv/WardWrite.csv",ward);

    }

    public Ward getWardByName(String name) {
        return wardRepository.getWardByName(name);
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

    public void updateWardPatients(){
        System.out.println("Ward name: ");
        String name = scanner.next();
        Ward ward = getWardByName(name);
        if(ward == null){
            System.out.println("There is no ward with this name");
        }
        else {
            System.out.println("New number of patients: ");
            int no = scanner.nextInt();
            wardRepository.updateWardPatients(ward.getWardId(),no);
            System.out.println("Success!");
            auditService.writeToAudit("Updated ward number of patients");
        }
    }

    public void deleteWard(){
        System.out.println("Ward name: ");
        String name = scanner.next();
        Ward ward = getWardByName(name);
        if(ward == null){
            System.out.println("There is no ward with this name");
        }
        else {

            wardRepository.deleteWard(ward.getWardId());
            System.out.println("Success!");
            auditService.writeToAudit("Deleted ward");
        }
    }

    public void showAllMedicine() {
        for (Medicine medicine : medicineRepository.getAllMedicine()) {
            System.out.println(medicine);
            System.out.println("*******************************");
        }
        auditService.writeToAudit("Saw all medicine");
    }

    public Medicine getMedicine(String name) {
        return medicineRepository.getMedicineByName(name);
    }

    public void addMedicine(){
        System.out.println("Medicine name: ");
        String name = scanner.next();
        System.out.println("Medicine price: ");
        int price = scanner.nextInt();
        System.out.println("Company name: ");
        String companyName = scanner.next();
        Medicine medicine = new Medicine(name,price,companyName);
        medicineRepository.insertMedicine(medicine);
        System.out.println("Success!");
        auditService.writeToAudit("Added medicine");
    //    csvReaderWriter.writeToCsv("csv/MedicineWrite.csv",medicine);
    }

    public void updateMedicinePrice(){
        System.out.println("Medicine name: ");
        String name = scanner.next();
        Medicine medicine = getMedicine(name);
        if(medicine == null){
            System.out.println("There is no medicine with this name");
        }
        else {
            System.out.println("Medicine new price: ");
            int price = scanner.nextInt();
            medicineRepository.updateMedicinePrice(medicine.getMedicineId(),price);
            System.out.println("Success!");
            auditService.writeToAudit("Updated medicine price");
        }
        //    csvReaderWriter.writeToCsv("csv/MedicineWrite.csv",medicine);
    }

    public void deleteMedicine(){
        System.out.println("Medicine name: ");
        String name = scanner.next();
        Medicine medicine = getMedicine(name);
        if(medicine == null){
            System.out.println("There is no medicine with this name");
        }
        else {

            medicineRepository.deleteMedicine(medicine.getMedicineId());
            System.out.println("Success!");
            auditService.writeToAudit("Deleted medicine");
        }
        //    csvReaderWriter.writeToCsv("csv/MedicineWrite.csv",medicine);
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
        LocalDate date = null;
        date = LocalDate.parse(dateBirth);
        if (date == null) {
            return;
        }

        System.out.println("Phone:");
        String phone = scanner.next();

        System.out.println("Email:");
        String email = scanner.next();

        System.out.println("Address:");
        String address = scanner.next();

        System.out.println("Hire date (format yyyy-mm-dd):");
        String hireDate = scanner.next();
        LocalDate hireDateformat = null;
        hireDateformat = LocalDate.parse(hireDate);
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

        Doctor doctor = new Doctor(id, password, firstName, lastName, date, phone, email, address, hireDateformat, salary, ward);
        doctorRepository.insertDoctor(doctor);
        System.out.println("Success!");
        auditService.writeToAudit("Added a doctor");
    //    csvReaderWriter.writeToCsv("csv/DoctorWrite.csv",doctor);
    }

    public void deleteDoctor() {
        System.out.println("Please write the id of the doctor!");
        int id = scanner.nextInt();
        Doctor doctor = doctorRepository.getDoctor(id);
        if(doctor == null){
            System.out.println("There is no doctor with this id");
        }
        else{
            doctorRepository.deleteDoctor(id);
            System.out.println("Success!");
        }

    }

    public void addPatient() {
        int id = Database.dbPatient.size() + 200;
        String password = "abcd";

        System.out.println("First name:");
        String firstName = scanner.next();

        System.out.println("Last name:");
        String lastName = scanner.next();

        System.out.println("Date of birth (format yyyy-mm-dd):");
        String dateBirth = scanner.next();
        LocalDate date = null;
        date = LocalDate.parse(dateBirth);
        if (date == null) {
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

        Patient patient = new Patient(id, password, firstName, lastName, date, phone, email, address, blood);
        patientRepository.insertPatient(patient);
        System.out.println("Success!");
        auditService.writeToAudit("Added a patient");
    //    csvReaderWriter.writeToCsv("csv/PatientWrite.csv",patient);
    }

    public void deletePatient() {
        System.out.println("Please write the id of the patient!");
        int id = scanner.nextInt();
        Patient patient = patientRepository.getPatient(id);
        if(patient == null){
            System.out.println("There is no doctor with this id");
        }
        else{
            patientRepository.deletePatient(id);
            System.out.println("Success!");
        }

    }

}
