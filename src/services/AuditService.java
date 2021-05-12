package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class AuditService {

    private static AuditService instance = null;

    private AuditService(){}

    public static AuditService getInstance(){
        if(instance == null){
            instance = new AuditService();
        }
        return instance;
    }

    public void writeToAudit(String action){
        try {
            File file = new File("csv/Audit.csv");
            FileWriter fileWriter = new FileWriter(file,true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            fileWriter.write(action + "," + timestamp.toString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
