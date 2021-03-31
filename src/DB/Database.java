package DB;

import Appointments.Appointment;
import Appointments.AppointmentStatus;
import ClinicRelated.Medicine;
import ClinicRelated.Ward;
import LaboratoryTests.AdvancedTest;
import LaboratoryTests.EssentialTest;
import LaboratoryTests.TotalTest;
import Users.Admin;
import Users.Doctor;
import Users.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Database {
    static public Admin dbAdmin = new Admin();
    static public ArrayList<Doctor> dbDoctor = new ArrayList<>();
    static public ArrayList<Patient> dbPatient = new ArrayList<>();
    static public ArrayList<Ward> dbWard = new ArrayList<>();
    static public ArrayList<Medicine> dbMedicine = new ArrayList<>();
    static public ArrayList<Appointment> dbAppointment = new ArrayList<>();
    static public ArrayList<EssentialTest> dbTest = new ArrayList<>();

    static public void init() {

        Database.dbWard.add(new Ward(700, "Dermatology", 4));
        Database.dbWard.add(new Ward(701, "Cardiology", 8));
        Database.dbWard.add(new Ward(702, "Neurology", 12));
        Database.dbWard.add(new Ward(703, "Oncology", 3));


        String dateBirth = "02-22-1980";
        String dateHire = "01-18-2007";
        String dateAppointment = "10-12-2021";
        String dateAppointment2 = "11-12-2021";
        String dateAppointment3 = "12-12-2021";
        String patternDate = "MM-dd-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
        Date dateBirthformat = null;
        Date dateHireformat = null;
        Date dateAppointmentformat = null;
        Date dateAppointmentformat2 = null;
        Date dateAppointmentformat3 = null;
        try {
            dateBirthformat = dateFormat.parse(dateBirth);
            dateHireformat = dateFormat.parse(dateHire);
            dateAppointmentformat = dateFormat.parse(dateAppointment);
            dateAppointmentformat2 = dateFormat.parse(dateAppointment2);
            dateAppointmentformat3 = dateFormat.parse(dateAppointment3);

        } catch (ParseException exception) {
            System.out.println("Invalid birth date!");
        }
        Database.dbDoctor.add(new Doctor(100, "abcd", "Jack", "Bloom", dateBirthformat, "0751234567", "abc100@yahoo.com", "Bucharest", dateHireformat, 2000, Database.dbWard.get(0)));
        Database.dbDoctor.add(new Doctor(101, "abcd", "Daniel", "Bloom", dateBirthformat, "0712234567", "abc101@yahoo.com", "Bucharest", dateHireformat, 3000, Database.dbWard.get(1)));
        Database.dbDoctor.add(new Doctor(102, "abcd", "Elijah", "Bloom", dateBirthformat, "0739234567", "abc102@yahoo.com", "Bucharest", dateHireformat, 4500, Database.dbWard.get(2)));

        Database.dbPatient.add(new Patient(200, "abcd", "Mariah", "Jolie", dateBirthformat, "0739234567", "abc200@yahoo.com", "Bucharest", "AB4"));
        Database.dbPatient.add(new Patient(201, "abcd", "Jennifer", "Jolie", dateBirthformat, "0739234567", "abc201@yahoo.com", "Bucharest", "B3"));

        Database.dbAppointment.add(new Appointment(500, Database.dbPatient.get(0), Database.dbDoctor.get(0), dateAppointmentformat, AppointmentStatus.CONFIRMED));
        Database.dbAppointment.add(new Appointment(501, Database.dbPatient.get(0), Database.dbDoctor.get(1), dateAppointmentformat, AppointmentStatus.SCHEDULED));
        Database.dbAppointment.add(new Appointment(502, Database.dbPatient.get(1), Database.dbDoctor.get(0), dateAppointmentformat, AppointmentStatus.SCHEDULED));

        Database.dbTest.add(new EssentialTest(600, dbPatient.get(0), dateAppointmentformat, 1, 2, 3, 4));
        Database.dbTest.add(new AdvancedTest(601, dbPatient.get(0), dateAppointmentformat2, 1, 2, 3, 4, 5, 6, 7));
        Database.dbTest.add(new TotalTest(602, dbPatient.get(1), dateAppointmentformat3, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        Database.dbMedicine.add(new Medicine("Aspirin", 5, "companyX"));
        Database.dbMedicine.add(new Medicine("Ketamine", 20, "companyX"));
        Database.dbMedicine.add(new Medicine("Paracetamol", 3, "companyX"));
        Database.dbMedicine.add(new Medicine("Ibuprofen", 5, "companyX"));

    }
}
