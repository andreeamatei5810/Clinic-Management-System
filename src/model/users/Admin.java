package model.users;

import java.time.LocalDate;

public class Admin extends User {
    public Admin(int userId, String password, String firstName, String lastName, LocalDate birthDate, String phoneNumber, String email, String address) {
        super(userId, password, firstName, lastName, birthDate, phoneNumber, email, address);
    }

    public Admin() {
        userId = 1;
        email = "admin";
        password = "1234";
    }
}
