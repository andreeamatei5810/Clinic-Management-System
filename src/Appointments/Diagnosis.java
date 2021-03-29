package Appointments;

public class Diagnosis {

    private String diagnosisName;
    private String details;
    private Severity severityLevel;

    public Diagnosis(String diagnosisName, String details, Severity severityLevel) {
        this.diagnosisName = diagnosisName;
        this.details = details;
        this.severityLevel = severityLevel;
    }

    @Override
    public String toString() {
        return "Name: " + diagnosisName + "\nDetails: " + details + "\nSeverity: "+ severityLevel;
    }
}
