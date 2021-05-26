package model.laboratoryTests;

import model.users.Patient;

import java.util.Date;

public class AdvancedTest extends EssentialTest{

    private int hdl;
    private int ldl;
    private int calcium;

    public AdvancedTest(int testId, Patient patient, Date testDate, int cbc, int cholesterol, int tsh, int creatinine, int hdl, int ldl, int calcium) {
        super(testId, patient,testDate, cbc, cholesterol, tsh, creatinine);
        this.hdl = hdl;
        this.ldl = ldl;
        this.calcium = calcium;
    }

    @Override
    public String toString() {
        return super.toString() + "\nHDL: " + hdl + "\nLDL: " + ldl + "\nCalcium: " + calcium;
    }

}
