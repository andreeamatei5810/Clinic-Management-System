package users;

import clinicRelated.Ward;
import java.util.Date;

public class Doctor extends User {

    private Date hireDate;
    private int salary;
    private Ward ward;

    public Doctor(int userId, String password, String firstName, String lastName, Date birthDate, String phoneNumber, String email, String address, Date hireDate, int salary, Ward ward) {
        super(userId, password, firstName, lastName, birthDate, phoneNumber, email, address);
        this.hireDate = hireDate;
        this.salary = salary;
        this.ward = ward;
    }


    @Override
    public String toString() {
        return super.toString() + "\nSection: " + ward.getWardName() + "\nHire Date: " + hireDate.toString() + "\nSalary: " + salary + " euro";
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }
}
