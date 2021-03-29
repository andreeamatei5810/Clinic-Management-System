package Services;

import Appointments.Appointment;
import Appointments.AppointmentStatus;
import ClinicRelated.Ward;
import DB.Database;
import LaboratoryTests.EssentialTest;
import Users.Doctor;
import Users.Patient;

public class PatientServices {

    public void showAllPatients(){
        for(Patient patient : Database.dbPatient){
            System.out.println(patient);
            System.out.println("*******************************");
        }
    }

    public void showPatient(int id){
        for(Patient patient : Database.dbPatient){
            if(patient.getUserId() == id) {
                System.out.println(patient);
                break;
            }
        }
    }

    public Patient getPatient(int id){
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

    public void showLabResults(int id){
        for(EssentialTest labTest : Database.dbTest){
            if(labTest.getTestId() == id) {
                System.out.println(labTest);
                break;
            }
        }
    }

    public void showFutureAppointments(int id){
        for(Appointment appointment : Database.dbAppointment){
            if(appointment.getPatient().getUserId() == id && (appointment.getStatus().equals(AppointmentStatus.CONFIRMED) || appointment.getStatus().equals(AppointmentStatus.SCHEDULED))) {
                System.out.println(appointment);
                System.out.println("*******************************");
            }
        }
    }

}
