package services;

import appointments.Appointment;
import appointments.AppointmentStatus;
import db.Database;
import laboratoryTests.EssentialTest;
import users.Patient;

public class PatientServices {

    AuditService auditService= AuditService.getInstance();
    CsvReaderWriter csvReaderWriter = CsvReaderWriter.getInstance();

    public void showAllPatients(){
        for(Patient patient : Database.dbPatient){
            System.out.println(patient);
            System.out.println("*******************************");
        }
        auditService.writeToAudit("Saw all patients");
    }

    public void showPatient(int id){
        for(Patient patient : Database.dbPatient){
            if(patient.getUserId() == id) {
                System.out.println(patient);
                break;
            }
        }
    }

    public static Patient getPatient(int id){
        for(Patient patient : Database.dbPatient){
            if(patient.getUserId() == id) {
                return patient;
            }
        }
        return null;
    }

    public Patient getPatientByEmail(String email){
        for(Patient patient : Database.dbPatient){
            if(patient.getEmail().equals(email)) {
                return patient;
            }
        }
        return null;
    }

    public EssentialTest getLabResults(int id){
        for(EssentialTest labTest : Database.dbTest){
            if(labTest.getTestId() == id) {
                return labTest;
            }
        }
        return null;
    }

    public void showFutureAppointments(int id){
        for(Appointment appointment : Database.dbAppointment){
            if(appointment.getPatient().getUserId() == id && (appointment.getStatus().equals(AppointmentStatus.CONFIRMED) || appointment.getStatus().equals(AppointmentStatus.SCHEDULED))) {
                System.out.println(appointment);
                System.out.println("*******************************");
            }
        }
        auditService.writeToAudit("Saw future appointments");
    }

}
