package repository;

import model.users.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PatientRepository {

    private Connection connection;

    public PatientRepository() {
        this.connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void insertPatient(Patient patient) {

        String query = "INSERT INTO patient VALUES(default,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, patient.getPassword());
            statement.setString(2, patient.getFirstName());
            statement.setString(3, patient.getLastName());
            statement.setDate(4, java.sql.Date.valueOf(patient.getBirthDate()));
            statement.setString(5, patient.getPhoneNumber());
            statement.setString(6, patient.getEmail());
            statement.setString(7, patient.getAddress());
            statement.setString(8, patient.getBloodType());
            statement.setInt(9,0);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patient";
        try {
            //userId, password, firstName, lastName, birthDate, phoneNumber, email, address);hireDate;salary;ward;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String password = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                LocalDate birthDate = LocalDate.parse(resultSet.getString(5));
                String phoneNumber = resultSet.getString(6);
                String email = resultSet.getString(7);
                String address = resultSet.getString(8);
                String bloodType = resultSet.getString(9);
                Patient patient = new Patient(id,password,firstName,lastName,birthDate,phoneNumber,email,address,bloodType);
                patients.add(patient);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return patients;
    }

    public Patient getPatient(int patientId) {
        Patient patient = null;
        String query = "SELECT * FROM patient WHERE patient_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String password = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                LocalDate birthDate = LocalDate.parse(resultSet.getString(5));
                String phoneNumber = resultSet.getString(6);
                String email = resultSet.getString(7);
                String address = resultSet.getString(8);
                String bloodType = resultSet.getString(9);
                patient = new Patient(id,password,firstName,lastName,birthDate,phoneNumber,email,address,bloodType);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return patient;
    }

    public Patient getPatientByEmail(String email) {
        Patient patient = null;
        String query = "SELECT * FROM patient WHERE email=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String password = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                LocalDate birthDate = LocalDate.parse(resultSet.getString(5));
                String phoneNumber = resultSet.getString(6);
                String newEmail = resultSet.getString(7);
                String address = resultSet.getString(8);
                String bloodType = resultSet.getString(9);
                patient = new Patient(id,password,firstName,lastName,birthDate,phoneNumber,newEmail,address,bloodType);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return patient;
    }

    public void updatePatientPassword(int id,String password){
        String query = "UPDATE patient SET password = ? WHERE patient_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,password);
            statement.setInt(2,id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void updatePatientPhone(int id,String phone){
        String query = "UPDATE patient SET phone = ? WHERE patient_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,phone);
            statement.setInt(2,id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deletePatient(int id){
        String query = "DELETE FROM patient WHERE patient_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
