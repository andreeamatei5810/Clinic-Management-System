package services;

import model.appointments.*;
import model.clinicRelated.Medicine;
import repository.AppointmentRepository;
import model.users.Doctor;
import model.users.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AppointmentServices {

    DoctorServices ds = new DoctorServices();
    PatientServices ps = new PatientServices();
    ClinicServices cs = new ClinicServices();
    Scanner scanner = new Scanner(System.in);
    AuditService auditService= AuditService.getInstance();
    CsvReaderWriter csvReaderWriter = CsvReaderWriter.getInstance();
    AppointmentRepository appointmentRepository = new AppointmentRepository();

    public Appointment getAppointment(int id) {
        return appointmentRepository.getAppointment(id);
    }

    public void makeAppointment(String role, int userId) {
        Patient patient;
        Doctor doctor;
        int doctorId, patientId;
        if (role == "Patient") {
            cs.showAllWards();
            System.out.println("Please write the id of the ward!");
            int wardId = scanner.nextInt();
            if (cs.getWard(wardId) == null) {
                System.out.println("Wrong input!");
                return;
            } else {
                System.out.println("Here are the available doctors: ");
                ds.showAllDoctorsByWard(wardId);
                System.out.println("Please write the id of the desired doctor: ");
                doctorId = scanner.nextInt();
                if (ds.getDoctor(doctorId) == null || ds.getDoctor(doctorId).getWard() != cs.getWard(wardId)) {
                    System.out.println("Wrong input!");
                    return;
                }
                patientId = userId;
            }
        } else if (role == "Doctor") {
            System.out.println("Patient id: ");
            patientId = scanner.nextInt();
            if (ps.getPatient(patientId) == null) {
                System.out.println("Wrong input!");
                return;
            }
            doctorId = userId;
        } else {
            System.out.println("Patient id: ");
            patientId = scanner.nextInt();
            if (ps.getPatient(patientId) == null) {
                System.out.println("Wrong input!");
                return;
            }
            System.out.println("Doctor id: ");
            doctorId = scanner.nextInt();
            if (ds.getDoctor(doctorId) == null) {
                System.out.println("Wrong input!");
                return;
            }
        }
        System.out.println("The date of the appointment (format MM-dd-yyyy): ");
        String date = scanner.next();
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date appointmentDate = null;
        try {
            appointmentDate = dateFormat.parse(date);
        } catch (ParseException exception) {
            System.out.println("Invalid date!");
            return;
        }
        doctor = ds.getDoctor(doctorId);
        patient = ps.getPatient(patientId);
        if (cs.checkWardAvailability(doctor.getWard().getWardId(), appointmentDate)) {
            Appointment appointment = new Appointment(1, patient, doctor, appointmentDate, AppointmentStatus.SCHEDULED);
            appointmentRepository.insertAppointment(appointment);
            System.out.println("The appointment has been scheduled!");
            auditService.writeToAudit("Scheduled an appointment");
        //    csvReaderWriter.writeToCsv("csv/AppointmentWrite.csv",appointment);
        } else {
            System.out.println("Sorry! We are booked for that day!");
        }
    }

    public void seeDetails(int id) {
        Appointment appointment = getAppointment(id);
        if (appointment == null) {
            System.out.println("There is no appointment with this id!");
        } else {
            System.out.println(appointment.details());
            auditService.writeToAudit("Saw appointment details");
        }
        System.out.println("*************************");
    }

    public void changeStatus(int id) {
        Appointment appointment = getAppointment(id);
        AppointmentStatus status = null;
        while (status == null) {
            System.out.println("Choose one of the possible states of the appointment status");
            System.out.println("1. Confirmed\n2. Canceled\n3. Engaged\n4. Finished\n5. No Show");
            int option = scanner.nextInt();
            switch (option) {
                case 1: {
                    status = AppointmentStatus.CONFIRMED;
                    break;
                }
                case 2: {
                    status = AppointmentStatus.CANCELED;
                    break;
                }
                case 3: {
                    status = AppointmentStatus.ENGAGED;
                    break;
                }
                case 4: {
                    status = AppointmentStatus.FINISHED;
                    break;
                }
                case 5: {
                    status = AppointmentStatus.NO_SHOW;
                    break;
                }
                default: {
                    System.out.println("Invalid input!");
                }
            }
        }
        appointmentRepository.updateAppointmentStatus(id,status.toString());
        auditService.writeToAudit("Changed appointment status");
    }

    public void deleteAppointment() {
        System.out.println("Please write the id of the appointment!");
        int id = scanner.nextInt();
        Appointment appointment = getAppointment(id);
        if(appointment == null){
            System.out.println("There is no appointment with this id");
        }
        else{
            appointmentRepository.deleteAppointment(id);
            System.out.println("Success!");
        }

    }

    public void makeDiagnosis(int id) {
        Appointment appointment = getAppointment(id);
        if (appointment == null) {
            System.out.println("There isn't an appointment with this id");
            return;
        }
        System.out.println("Diagnosis name: ");
        String diagnosisName = scanner.next();
        System.out.println("Details of the diagnosis: ");
        String details = scanner.next();
        System.out.println("Here are different levels of severity. Choose one.");
        System.out.println("1. Minor\n2. Moderate\n3. Major\n4. Extreme");
        int option = scanner.nextInt();
        Severity status;
        switch (option) {
            case 1: {
                status = Severity.MINOR;
                break;
            }
            case 2: {
                status = Severity.MODERATE;
                break;
            }
            case 3: {
                status = Severity.MAJOR;
                break;
            }
            case 4: {
                status = Severity.EXTREME;
                break;
            }
            default: {
                System.out.println("Invalid input!");
                return;
            }
        }
        Diagnosis diagnosis = new Diagnosis(diagnosisName, details, status);
        appointment.setDiagnosis(diagnosis);
        auditService.writeToAudit("Made diagnosis");
    }

    public void prescribeTreatment(int id) {
        Appointment appointment = getAppointment(id);
        Treatment treatment = new Treatment();
        System.out.println("When you finish adding a medicine to the treatment, please write \"STOP\" if you want to stop the prescription");
        while (true) {
            System.out.println("Write the name of the medicine: ");
            String medicineName = scanner.next();
            if (medicineName.equals("STOP")) {
                break;
            }
            System.out.println("Write the quantity of the medicine");
            int quantity = scanner.nextInt();
            Medicine medicine = cs.getMedicine(medicineName);
            if (medicine == null) {
                System.out.println("We don't have a medicine with this name in the storage department!");
            } else {
                treatment.getTreatment().put(medicine, quantity);
            }
        }
        System.out.println("Write the details of the treatment");
        String details = scanner.next();
        treatment.setDetails(details);
        System.out.println("Write the duration of the treatment (in weeks)");
        int duration = scanner.nextInt();
        treatment.setDurationInWeeks(duration);
        treatment.calculateCost();
        appointment.setTreatment(treatment);
        auditService.writeToAudit("Prescribed treatment");
        appointment.getPatient().setAmountToPay(appointment.getPatient().getAmountToPay() + treatment.getCost());
    }

}
