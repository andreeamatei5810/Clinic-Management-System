package Users;

import LaboratoryTests.EssentialTest;

import java.util.ArrayList;
import java.util.Date;

public class Patient extends User {

    private String bloodType;
    private ArrayList<EssentialTest> labTestsHistory;
    private int amountToPay;

    public Patient(int userId, String password, String firstName, String lastName, Date birthDate, String phoneNumber, String email, String address, String bloodType) {
        super(userId, password, firstName, lastName, birthDate, phoneNumber, email, address);
        this.bloodType = bloodType;
        this.amountToPay = 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\nBlood Type: " + bloodType;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public ArrayList<EssentialTest> getLabTestsHistory() {
        return labTestsHistory;
    }

    public void setLabTestsHistory(ArrayList<EssentialTest> labTestsHistory) {
        this.labTestsHistory = labTestsHistory;
    }

    public int getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(int amountToPay) {
        this.amountToPay = amountToPay;
    }
}
