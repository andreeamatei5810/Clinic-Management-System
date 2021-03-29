package Services;

import Appointments.Appointment;
import ClinicRelated.Medicine;
import ClinicRelated.Ward;
import DB.Database;

import java.util.Date;

public class ClinicServices {

    public void showAllWards(){
        for(Ward ward : Database.dbWard){
            System.out.println(ward);
            System.out.println("*******************************");
        }
    }

    public Ward getWard(int id){
        for(Ward ward : Database.dbWard){
            if(ward.getWardId() == id) {
                return ward;
            }
        }
        return null;
    }

    public boolean checkWardAvailability(int wardId, Date date){
        Ward ward = getWard(wardId);
        int count = 0;
        for(Appointment appointment : Database.dbAppointment){
            if(appointment.getDoctor().getWard() == ward){
                count++;
            }
        }
        if(count < ward.getMaxPatientsPerDay()){
            return true;
        }
        else{
            return false;
        }
    }

    public void showAllMedicine(){
        for(Medicine medicine : Database.dbMedicine){
            System.out.println(medicine);
            System.out.println("*******************************");
        }
    }

    public Medicine getMedicine(String name){
        for(Medicine medicine : Database.dbMedicine){
            if(medicine.getMedicineName().equals(name)) {
                return medicine;
            }
        }
        return null;
    }

}
