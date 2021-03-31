package Appointments;

import java.util.Date;

import Users.Doctor;
import Users.Patient;

public class Appointment {
    private int appointmentId;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private AppointmentStatus status;
    private Diagnosis diagnosis;
    private Treatment treatment;

    public Appointment(int appointmentId, Patient patient, Doctor doctor, Date date, AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "Id: " + appointmentId + "\nPatient Info\n" + patient.toString() + "\nDoctor Info\nName: " + doctor.getLastName() + " " + doctor.getFirstName() + "\nDate: "
                + date.toString() + "\nWard: " + doctor.getWard().getWardName();
    }

    public String details() {
        return "\nPatient Info\n" + patient.toString() + "\nDoctor Info\nName: " + doctor.getLastName() + " " + doctor.getFirstName() + "\nDate: "
                + date.toString() + "\nWard: " + doctor.getWard().getWardName() + "\nStatus: " + status + "\nDiagnosis: \n" + diagnosis
                + "\nTreatment: \n" + treatment;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
}
