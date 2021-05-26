package repository;

import model.appointments.Appointment;
import model.appointments.AppointmentStatus;
import model.users.Doctor;
import model.users.Patient;

import java.sql.*;
import java.util.ArrayList;

public class AppointmentRepository {

    private Connection connection;
    private PatientRepository patientRepository = new PatientRepository();
    private DoctorRepository doctorRepository = new DoctorRepository();

    public AppointmentRepository() {
        this.connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void insertAppointment(Appointment appointment) {
        String query = "INSERT INTO appointment VALUES(default,?,?,?,?)";
        try {//patient,doctor,date,status
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, appointment.getPatient().getUserId());
            statement.setInt(2, appointment.getDoctor().getUserId());
            statement.setDate(3, (Date) appointment.getDate());
            statement.setString(4, appointment.getStatus().toString());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointment";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int patientId = resultSet.getInt(2);
                int doctorId = resultSet.getInt(3);
                Date date = resultSet.getDate(4);
                AppointmentStatus status = AppointmentStatus.valueOf(resultSet.getString(5));
                Patient patient = patientRepository.getPatient(patientId);
                Doctor doctor = doctorRepository.getDoctor(doctorId);
                Appointment appointment = new Appointment(id,patient,doctor,date,status);
                appointments.add(appointment);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return appointments;
    }

    public Appointment getAppointment(int appointmentId) {
        Appointment appointment = null;
        String query = "SELECT * FROM appointment WHERE appointment_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, appointmentId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                int patientId = resultSet.getInt(2);
                int doctorId = resultSet.getInt(3);
                Date date = resultSet.getDate(4);
                AppointmentStatus status = AppointmentStatus.valueOf(resultSet.getString(5));
                Patient patient = patientRepository.getPatient(patientId);
                Doctor doctor = doctorRepository.getDoctor(doctorId);
                appointment = new Appointment(id,patient,doctor,date,status);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return appointment;
    }

    public void updateAppointmentStatus(int id,String status){
        String query = "UPDATE appointment SET status = ? WHERE appointment_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,status);
            statement.setInt(2,id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteAppointment(int id){
        String query = "DELETE FROM appointment WHERE appointment_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
