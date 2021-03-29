package ClinicRelated;

import Users.Doctor;

public class Ward {

    private int wardId;
    private String wardName;
    private int maxPatientsPerDay;

    public Ward(int wardId, String wardName, int maxPatientsPerDay) {
        this.wardId = wardId;
        this.wardName = wardName;
        this.maxPatientsPerDay = maxPatientsPerDay;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public int getMaxPatientsPerDay() {
        return maxPatientsPerDay;
    }

    public void setMaxPatientsPerDay(int maxPatientsPerDay) {
        this.maxPatientsPerDay = maxPatientsPerDay;
    }

    @Override
    public String toString() {
        return "Id: " + wardId + ", Name: " + wardName;
    }
}
