package repository;

import model.clinicRelated.Ward;

import java.sql.*;
import java.util.HashSet;

public class WardRepository {

    private Connection connection;

    public WardRepository() {
        this.connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void insertWard(Ward ward) {

        String query = "INSERT INTO ward VALUES(default,?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ward.getWardName());
            statement.setInt(2, ward.getMaxPatientsPerDay());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public HashSet<Ward> getAllWards() {
        HashSet<Ward> wards = new HashSet<>();

        String query = "SELECT * FROM ward";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int noPatients = resultSet.getInt(3);
                Ward ward = new Ward(id, name, noPatients);
                wards.add(ward);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return wards;
    }

    public Ward getWard(int wardId) {
        Ward ward;
        String query = "SELECT * FROM ward WHERE ward_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, wardId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int noPatients = resultSet.getInt(3);
                ward = new Ward(id, name, noPatients);
                return ward;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Ward getWardByName(String wardName) {
        Ward ward;
        String query = "SELECT * FROM ward WHERE ward_name=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, wardName);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int noPatients = resultSet.getInt(3);
                ward = new Ward(id, name, noPatients);
                return ward;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public void updateWardPatients(int id,int newNo){
        String query = "UPDATE ward SET no_patients = ? WHERE ward_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,newNo);
            statement.setInt(2,id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteWard(int id){
        String query = "DELETE FROM ward WHERE ward_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
