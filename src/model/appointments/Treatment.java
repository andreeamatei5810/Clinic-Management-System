package model.appointments;

import model.clinicRelated.Medicine;

import java.util.HashMap;
import java.util.Map;

public class Treatment {

    private int cost;
    private int durationInWeeks;
    private String details;
    private Map<Medicine, Integer> treatment = new HashMap<>();

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

    public Map<Medicine, Integer> getTreatment() {
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

    @Override
    public String toString() {
        return "Cost: " + cost +"\nDuration: "+durationInWeeks+ "\nDetails: "+details+"\nMedicines: "+treatment;
    }
}
