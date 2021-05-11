package services;

import appointments.Appointment;
import appointments.AppointmentStatus;
import db.Database;
import users.Doctor;

public class DoctorServices {

    CsvReaderWriter csvReaderWriter = CsvReaderWriter.getInstance();

    public void showAllDoctors(){
        for(Doctor doctor : Database.dbDoctor){
            System.out.println(doctor);
            System.out.println("*******************************");
        }
        csvReaderWriter.writeToAudit("Saw all doctors");
    }

    public void showAllDoctorsByWard(int wardId){
        for(Doctor doctor : Database.dbDoctor){
            if(doctor.getWard().getWardId() == wardId) {
                System.out.println("Id: "+ doctor.getUserId() + ", Name: " + doctor.getFirstName() + " " + doctor.getLastName());
                System.out.println("*******************************");
            }
        }
    }

    public void showDoctor(int id){
        for(Doctor doctor : Database.dbDoctor){
            if(doctor.getUserId() == id) {
                System.out.println(doctor);
                break;
            }
        }
    }

    public static Doctor getDoctor(int id){
        for(Doctor doctor : Database.dbDoctor){
            if(doctor.getUserId() == id) {
                return doctor;
            }
        }
        return null;
    }

    public Doctor getDoctorByEmail(String email){
        for(Doctor doctor : Database.dbDoctor){
            if(doctor.getEmail().equals(email)) {
                return doctor;
            }
        }
        return null;
    }

    public void showFutureAppointments(int id){
        for(Appointment appointment : Database.dbAppointment){
            if(appointment.getDoctor().getUserId() == id && (appointment.getStatus().equals(AppointmentStatus.CONFIRMED) || appointment.getStatus().equals(AppointmentStatus.SCHEDULED))) {
                System.out.println(appointment);
                System.out.println("*******************************");
            }
        }
        csvReaderWriter.writeToAudit("Saw future appointments");
    }

}
