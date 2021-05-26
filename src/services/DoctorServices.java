package services;

import model.appointments.Appointment;
import model.appointments.AppointmentStatus;
import db.Database;
import repository.DoctorRepository;
import model.users.Doctor;

public class DoctorServices {

    AuditService auditService= AuditService.getInstance();
    CsvReaderWriter csvReaderWriter = CsvReaderWriter.getInstance();
    static DoctorRepository doctorRepository = new DoctorRepository();

    public void showAllDoctors(){
        for(Doctor doctor : doctorRepository.getAllDoctors()){
            System.out.println(doctor);
            System.out.println("*******************************");
        }
        auditService.writeToAudit("Saw all doctors");
    }

    public void showAllDoctorsByWard(int wardId){
        for(Doctor doctor : doctorRepository.getAllDoctorsByWard(wardId)){
            if(doctor.getWard().getWardId() == wardId) {
                System.out.println("Id: "+ doctor.getUserId() + ", Name: " + doctor.getFirstName() + " " + doctor.getLastName());
                System.out.println("*******************************");
            }
        }
    }

    public void showDoctor(int id){
        System.out.println(doctorRepository.getDoctor(id));
    }

    public static Doctor getDoctor(int id){
        return doctorRepository.getDoctor(id);
    }

    public Doctor getDoctorByEmail(String email){
        return doctorRepository.getDoctorByEmail(email);
    }

    public void showFutureAppointments(int id){
        for(Appointment appointment : Database.dbAppointment){
            if(appointment.getDoctor().getUserId() == id && (appointment.getStatus().equals(AppointmentStatus.CONFIRMED) || appointment.getStatus().equals(AppointmentStatus.SCHEDULED))) {
                System.out.println(appointment);
                System.out.println("*******************************");
            }
        }
        auditService.writeToAudit("Saw future model.appointments");
    }

}
