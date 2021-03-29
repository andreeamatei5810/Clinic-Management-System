package Appointments;

import ClinicRelated.Medicine;

import java.util.HashMap;

public class Treatment {

    private int cost;
    private int durationInWeeks;
    private String details;
    private HashMap<Medicine,Integer> treatment;

    public Treatment(int cost, int durationInWeeks, String details, HashMap<Medicine, Integer> treatment) {
        this.cost = cost;
        this.durationInWeeks = durationInWeeks;
        this.details = details;
        this.treatment = treatment;
    }

    public Treatment() {
        treatment = new HashMap<>();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public HashMap<Medicine, Integer> getTreatment() {
        return treatment;
    }

    public void setTreatment(HashMap<Medicine, Integer> treatment) {
        this.treatment = treatment;
    }

    public void calculateCost(){
        for(Medicine medicine:treatment.keySet()){
            cost += medicine.getPrice() * treatment.get(medicine);
        }
    }
}
