package LaboratoryTests;

import Users.Patient;

import java.util.Date;

public class TotalTest extends AdvancedTest{

    private int triglycerides;
    private int sodium;
    private int potassium;

    public TotalTest(int testId, Patient patient, Date testDate, int cbc, int cholesterol, int tsh, int creatinine, int hdl, int ldl, int calcium, int triglycerides, int sodium, int potassium) {
        super(testId, patient, testDate, cbc, cholesterol, tsh, creatinine, hdl, ldl, calcium);
        this.triglycerides = triglycerides;
        this.sodium = sodium;
        this.potassium = potassium;
    }


    @Override
    public String toString() {
        return super.toString() + "\nTriglycerides: " + triglycerides + "\nSodium: " + sodium + "\nPotassium: " + potassium;
    }
}
