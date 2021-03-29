package LaboratoryTests;

import java.util.Date;

public class AdvancedTest extends EssentialTest{

    private int hdl;
    private int ldl;
    private int calcium;

    public AdvancedTest(int testId, Date testDate, int cbc, int cholesterol, int tsh, int creatinine, int hdl, int ldl, int calcium) {
        super(testId, testDate, cbc, cholesterol, tsh, creatinine);
        this.hdl = hdl;
        this.ldl = ldl;
        this.calcium = calcium;
    }

    @Override
    public String toString() {
        return super.toString() + "\nHDL: " + hdl + "\nLDL: " + ldl + "\nCalcium: " + calcium;
    }

}
