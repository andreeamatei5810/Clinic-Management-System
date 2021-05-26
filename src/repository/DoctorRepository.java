package repository;

import model.clinicRelated.Ward;
import services.ClinicServices;
import model.users.Doctor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DoctorRepository {

    private Connection connection;

    public DoctorRepository() {
        this.connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void insertDoctor(Doctor doctor) {

        String query = "INSERT INTO doctor VALUES(default,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, doctor.getPassword());
            statement.setString(2, doctor.getFirstName());
            statement.setString(3, doctor.getLastName());
            statement.setDate(4, java.sql.Date.valueOf(doctor.getBirthDate()));
            statement.setString(5, doctor.getPhoneNumber());
            statement.setString(6, doctor.getEmail());
            statement.setString(7, doctor.getAddress());
            statement.setDate(8, java.sql.Date.valueOf(doctor.getHireDate()));
            statement.setInt(9, doctor.getSalary());
            statement.setInt(10, doctor.getWard().getWardId());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctor";
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
                LocalDate hireDate = LocalDate.parse(resultSet.getString(9));
                int salary = Integer.parseInt(resultSet.getString(10));
                Ward ward = ClinicServices.getWard(Integer.parseInt(resultSet.getString(11)));
                Doctor doctor = new Doctor(id,password,firstName,lastName,birthDate,phoneNumber,email,address,hireDate,salary,ward);
                doctors.add(doctor);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return doctors;
    }

    public ArrayList<Doctor> getAllDoctorsByWard(int wardId) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctor WHERE ward_id=?";
        try {
            //userId, password, firstName, lastName, birthDate, phoneNumber, email, address);hireDate;salary;ward;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,wardId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String password = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                LocalDate birthDate = LocalDate.parse(resultSet.getString(5));
                String phoneNumber = resultSet.getString(6);
                String email = resultSet.getString(7);
                String address = resultSet.getString(8);
                LocalDate hireDate = LocalDate.parse(resultSet.getString(9));
                int salary = Integer.parseInt(resultSet.getString(10));
                Ward ward = ClinicServices.getWard(Integer.parseInt(resultSet.getString(11)));
                Doctor doctor = new Doctor(id,password,firstName,lastName,birthDate,phoneNumber,email,address,hireDate,salary,ward);
                doctors.add(doctor);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return doctors;
    }

    public Doctor getDoctor(int doctorId) {
        Doctor doctor = null;
        String query = "SELECT * FROM doctor WHERE doctor_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, doctorId);
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
                LocalDate hireDate = LocalDate.parse(resultSet.getString(9));
                int salary = Integer.parseInt(resultSet.getString(10));
                Ward ward = ClinicServices.getWard(Integer.parseInt(resultSet.getString(11)));
                doctor = new Doctor(id,password,firstName,lastName,birthDate,phoneNumber,email,address,hireDate,salary,ward);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return doctor;
    }

    public Doctor getDoctorByEmail(String email) {
        Doctor doctor = null;
        String query = "SELECT * FROM doctor WHERE email=?";
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
                LocalDate hireDate = LocalDate.parse(resultSet.getString(9));
                int salary = Integer.parseInt(resultSet.getString(10));
                Ward ward = ClinicServices.getWard(Integer.parseInt(resultSet.getString(11)));
                doctor = new Doctor(id,password,firstName,lastName,birthDate,phoneNumber,newEmail,address,hireDate,salary,ward);
                return doctor;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public void updateDoctorPassword(int id,String password){
        String query = "UPDATE doctor SET password = ? WHERE doctor_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,password);
            statement.setInt(2,id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteDoctor(int id){
        String query = "DELETE FROM doctor WHERE doctor_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
