package services;

import appointments.Appointment;
import appointments.AppointmentStatus;
import clinicRelated.Medicine;
import clinicRelated.Ward;
import users.Doctor;
import users.Patient;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CsvReaderWriter {

    private static CsvReaderWriter instance = null;

    private CsvReaderWriter(){
    }

    public static CsvReaderWriter getInstance(){
        if(instance == null){
            instance = new CsvReaderWriter();
        }
        return instance;
    }

    public void writeToAudit(String action){
        try {
            File file = new File("csv/Audit.csv");
            FileWriter fileWriter = new FileWriter(file,true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fileWriter.write(action + "," + timestamp.toString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> ArrayList<T> readFromCsv(String path, String objectName){
        ArrayList<T> objects = new ArrayList<>();
        ArrayList<String> csvObjects = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = bufferedReader.readLine();
            String[] values;
            while(line != null){
                values = line.split(",");
                line = bufferedReader.readLine();
                switch (objectName){
                    case "appointment" -> {
                        int appointmentId = Integer.parseInt(values[0]);
                        int patientId = Integer.parseInt(values[1]);
                        int doctorId = Integer.parseInt(values[2]);
                        Date date = stringToDate(values[3]);
                        AppointmentStatus status = AppointmentStatus.valueOf(values[4]);
                        Appointment appointment = new Appointment(appointmentId,PatientServices.getPatient(patientId),DoctorServices.getDoctor(doctorId),date,status);
                        objects.add((T) appointment);
                    }
                    case "doctor" -> {
                        int doctorId = Integer.parseInt(values[0]);
                        String password = values[1];
                        String firstName = values[2];
                        String lastName = values[3];
                        Date birthDate = stringToDate(values[4]);
                        String phoneNumber = values[5];
                        String email = values[6];
                        String address = values[7];
                        Date hireDate = stringToDate(values[8]);
                        int salary = Integer.parseInt(values[9]);
                        Ward ward = ClinicServices.getWard(Integer.parseInt(values[10]));
                        Doctor doctor = new Doctor(doctorId,password,firstName,lastName,birthDate,phoneNumber,email,address,hireDate,salary,ward);
                        objects.add((T) doctor);
                    }
                    case "medicine" -> {
                        String name = values[0];
                        int price = Integer.parseInt(values[1]);
                        String companyName = values[2];
                        Medicine medicine = new Medicine(name,price,companyName);
                        objects.add((T) medicine);
                    }
                    case "patient" -> {
                        int patientId = Integer.parseInt(values[0]);
                        String password = values[1];
                        String firstName = values[2];
                        String lastName = values[3];
                        Date birthDate = stringToDate(values[4]);
                        String phoneNumber = values[5];
                        String email = values[6];
                        String address = values[7];
                        String bloodType = values[8];
                        Patient patient = new Patient(patientId,password,firstName,lastName,birthDate,phoneNumber,email,address,bloodType);
                        objects.add((T) patient);
                    }
                    case "ward" -> {
                        int wardId = Integer.parseInt(values[0]);
                        String name = values[1];
                        int noPatients = Integer.parseInt(values[2]);
                        Ward ward = new Ward(wardId,name,noPatients);
                        objects.add((T) ward);
                    }
                    default -> {
                        return null;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public <T> void writeToCsv(String path, T object){
        System.out.println("pe aici");
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            switch(object.getClass().getSimpleName()){
                case "Appointment" -> {
                    Appointment appointment = (Appointment) object;
                    fileWriter.write(String.valueOf(appointment.getAppointmentId()));
                    fileWriter.write(",");
                    fileWriter.write(String.valueOf(appointment.getPatient().getUserId()));
                    fileWriter.write(",");
                    fileWriter.write(String.valueOf(appointment.getDoctor().getUserId()));
                    fileWriter.write(",");
                    Date date = appointment.getDate();
                    fileWriter.write(dateToString(appointment.getDate()));
                    fileWriter.write(",");
                    fileWriter.write(appointment.getStatus().toString());
                    fileWriter.write("\n");
                }
                case "Doctor" -> {
                    Doctor doctor = (Doctor) object;
                    fileWriter.write(String.valueOf(doctor.getUserId()));
                    fileWriter.write(",");
                    fileWriter.write(doctor.getPassword());
                    fileWriter.write(",");
                    fileWriter.write(doctor.getFirstName());
                    fileWriter.write(",");
                    fileWriter.write(doctor.getLastName());
                    fileWriter.write(",");
                    fileWriter.write(dateToString(doctor.getBirthDate()));
                    fileWriter.write(",");
                    fileWriter.write(doctor.getPhoneNumber());
                    fileWriter.write(",");
                    fileWriter.write(doctor.getEmail());
                    fileWriter.write(",");
                    fileWriter.write(doctor.getAddress());
                    fileWriter.write(",");
                    fileWriter.write(dateToString(doctor.getHireDate()));
                    fileWriter.write(",");
                    fileWriter.write(String.valueOf(doctor.getSalary()));
                    fileWriter.write(",");
                    fileWriter.write(String.valueOf(doctor.getWard().getWardId()));
                    fileWriter.write("\n");
                }
                case "Medicine" -> {
                    Medicine medicine = (Medicine) object;
                    fileWriter.write(medicine.getMedicineName());
                    fileWriter.write(",");
                    fileWriter.write(String.valueOf(medicine.getPrice()));
                    fileWriter.write(",");
                    fileWriter.write(medicine.getCompanyName());
                    fileWriter.write("\n");
                }
                case "Patient" -> {
                    Patient patient = (Patient) object;
                    fileWriter.write(String.valueOf(patient.getUserId()));
                    fileWriter.write(",");
                    fileWriter.write(patient.getPassword());
                    fileWriter.write(",");
                    fileWriter.write(patient.getFirstName());
                    fileWriter.write(",");
                    fileWriter.write(patient.getLastName());
                    fileWriter.write(",");
                    fileWriter.write(dateToString(patient.getBirthDate()));
                    fileWriter.write(",");
                    fileWriter.write(patient.getPhoneNumber());
                    fileWriter.write(",");
                    fileWriter.write(patient.getEmail());
                    fileWriter.write(",");
                    fileWriter.write(patient.getAddress());
                    fileWriter.write(",");
                    fileWriter.write(patient.getBloodType());
                    fileWriter.write("\n");
                }
                case "Ward" -> {
                    Ward ward = (Ward) object;
                    fileWriter.write(String.valueOf(ward.getWardId()));
                    fileWriter.write(",");
                    fileWriter.write(ward.getWardName());
                    fileWriter.write(",");
                    fileWriter.write(String.valueOf(ward.getMaxPatientsPerDay()));
                    fileWriter.write("\n");
                }
                default -> {
                    break;
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Date stringToDate(String stringDate){
        String patternDate = "MM-dd-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
        Date date = null;
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private String dateToString(Date date){
        String patternDate = "MM-dd-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
        String result = dateFormat.format(date);
        return result;
    }

}
