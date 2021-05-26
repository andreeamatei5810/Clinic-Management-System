package model.users;

import model.clinicRelated.Ward;

import java.time.LocalDate;

public class Doctor extends User {

    private LocalDate hireDate;
    private int salary;
    private Ward ward;

    public Doctor(int userId, String password, String firstName, String lastName, LocalDate birthDate, String phoneNumber, String email, String address, LocalDate hireDate, int salary, Ward ward) {
        super(userId, password, firstName, lastName, birthDate, phoneNumber, email, address);
        this.hireDate = hireDate;
        this.salary = salary;
        this.ward = ward;
    }


    @Override
    public String toString() {
        return super.toString() + "\nSection: " + ward.getWardName() + "\nHire Date: " + hireDate.toString() + "\nSalary: " + salary + " euro";
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
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
