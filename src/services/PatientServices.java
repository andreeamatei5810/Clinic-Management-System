package services;

import model.appointments.Appointment;
import model.appointments.AppointmentStatus;
import db.Database;
import model.laboratoryTests.EssentialTest;
import repository.PatientRepository;
import model.users.Patient;

public class PatientServices {

    AuditService auditService= AuditService.getInstance();
    CsvReaderWriter csvReaderWriter = CsvReaderWriter.getInstance();
    static PatientRepository patientRepository = new PatientRepository();

    public void showAllPatients(){
        for(Patient patient : patientRepository.getAllPatients()){
            System.out.println(patient);
            System.out.println("*******************************");
        }
        auditService.writeToAudit("Saw all patients");
    }

    public void showPatient(int id){
        System.out.println(patientRepository.getPatient(id));
    }

    public static Patient getPatient(int id){
        return patientRepository.getPatient(id);
    }

    public Patient getPatientByEmail(String email){
        return patientRepository.getPatientByEmail(email);
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
        auditService.writeToAudit("Saw future model.appointments");
    }

}
