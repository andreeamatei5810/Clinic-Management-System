package LaboratoryTests;

import Users.Patient;

import java.util.Date;

public class EssentialTest implements Comparable <EssentialTest> {
    private int testId;
    private Patient patient;
    private Date testDate;
    private int cbc;
    private int cholesterol;
    private int tsh;
    private int creatinine;

    public EssentialTest(int testId, Patient patient,Date testDate, int cbc, int cholesterol, int tsh, int creatinine) {
        this.testId = testId;
        this.patient = patient;
        this.testDate = testDate;
        this.cbc = cbc;
        this.cholesterol = cholesterol;
        this.tsh = tsh;
        this.creatinine = creatinine;
    }

    @Override
    public String toString() {
        return "The date of the test: " + testDate.toString() + "\nResults:"
                + "\nCBC: " + cbc + "\nCholesterol: " + cholesterol + "\nTSH: " + tsh + "\nCreatinine: " + creatinine;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    @Override
    public int compareTo(EssentialTest o) {
        return -testDate.compareTo(o.testDate);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
