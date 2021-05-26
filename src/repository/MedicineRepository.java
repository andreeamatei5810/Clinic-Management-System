package repository;

import model.clinicRelated.Medicine;

import java.sql.*;
import java.util.HashSet;

public class MedicineRepository {

    private Connection connection;

    public MedicineRepository() {
        this.connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void insertMedicine(Medicine medicine) {

        String query = "INSERT INTO medicine VALUES(default,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, medicine.getMedicineName());
            statement.setInt(2, medicine.getPrice());
            statement.setString(3, medicine.getCompanyName());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public HashSet<Medicine> getAllMedicine() {
        HashSet<Medicine> medicine = new HashSet<>();
        String query = "SELECT * FROM medicine";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                String companyName = resultSet.getString(4);
                Medicine medicineDb = new Medicine(id,name,price,companyName);
                medicine.add(medicineDb);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return medicine;
    }

    public Medicine getMedicineByName(String medicineName) {
        Medicine medicine;
        String query = "SELECT * FROM medicine WHERE medicine_name=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, medicineName);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                String companyName = resultSet.getString(4);
                Medicine medicineDb = new Medicine(id,name,price,companyName);
                return medicineDb;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public void updateMedicinePrice(int id,int newPrice){
        String query = "UPDATE medicine SET price = ? WHERE medicine_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,newPrice);
            statement.setInt(2,id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteMedicine(int id){
        String query = "DELETE FROM medicine WHERE medicine_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
