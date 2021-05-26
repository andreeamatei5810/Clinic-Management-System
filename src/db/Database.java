package db;

import model.appointments.Appointment;
import model.clinicRelated.Medicine;
import model.clinicRelated.Ward;
import model.laboratoryTests.EssentialTest;
import services.CsvReaderWriter;
import model.users.Admin;
import model.users.Doctor;
import model.users.Patient;

import java.util.ArrayList;

public class Database {
    static public Admin dbAdmin = new Admin();
    static public ArrayList<Doctor> dbDoctor = new ArrayList<>();
    static public ArrayList<Patient> dbPatient = new ArrayList<>();
    static public ArrayList<Ward> dbWard = new ArrayList<>();
    static public ArrayList<Medicine> dbMedicine = new ArrayList<>();
    static public ArrayList<Appointment> dbAppointment = new ArrayList<>();
    static public ArrayList<EssentialTest> dbTest = new ArrayList<>();
    static private CsvReaderWriter csvReaderWriter = CsvReaderWriter.getInstance();

    static public void init() {

//        dbWard = csvReaderWriter.readFromCsv("csv/Ward.csv","ward");
//        dbDoctor = csvReaderWriter.readFromCsv("csv/Doctor.csv","doctor");
//        dbPatient = csvReaderWriter.readFromCsv("csv/Patient.csv","patient");
//        dbAppointment = csvReaderWriter.readFromCsv("csv/Appointment.csv","appointment");
//        dbMedicine = csvReaderWriter.readFromCsv("csv/Medicine.csv","medicine");



//        String dateAppointment = "10-12-2021";
//        String dateAppointment2 = "11-12-2021";
//        String dateAppointment3 = "12-12-2021";
//        String patternDate = "MM-dd-yyyy";
//        SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
//        Date dateAppointmentformat = null;
//        Date dateAppointmentformat2 = null;
//        Date dateAppointmentformat3 = null;
//        try {
//            dateAppointmentformat = dateFormat.parse(dateAppointment);
//            dateAppointmentformat2 = dateFormat.parse(dateAppointment2);
//            dateAppointmentformat3 = dateFormat.parse(dateAppointment3);
//
//        } catch (ParseException exception) {
//            System.out.println("Invalid birth date!");
//        }
//
//        Database.dbTest.add(new EssentialTest(600, dbPatient.get(0), dateAppointmentformat, 1, 2, 3, 4));
//        Database.dbTest.add(new AdvancedTest(601, dbPatient.get(0), dateAppointmentformat2, 1, 2, 3, 4, 5, 6, 7));
//        Database.dbTest.add(new TotalTest(602, dbPatient.get(1), dateAppointmentformat3, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
//
  }
}
