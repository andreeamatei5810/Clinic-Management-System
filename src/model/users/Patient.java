package model.users;

import model.laboratoryTests.EssentialTest;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class Patient extends User {

    private String bloodType;
    private Set<EssentialTest> labTestsHistory = new TreeSet<>();
    private int amountToPay;

    public Patient(int userId, String password, String firstName, String lastName, LocalDate birthDate, String phoneNumber, String email, String address, String bloodType) {
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

    public Set<EssentialTest> getLabTestsHistory() {
        return labTestsHistory;
    }

    public void setLabTestsHistory(TreeSet<EssentialTest> labTestsHistory) {
        this.labTestsHistory = labTestsHistory;
    }

    public int getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(int amountToPay) {
        this.amountToPay = amountToPay;
    }
}
