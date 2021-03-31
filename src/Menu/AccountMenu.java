package Menu;

import Services.UserServices;

import java.util.Scanner;

public class AccountMenu {

    int userId;
    String role;
    UserServices us = new UserServices();
    Scanner scanner = new Scanner(System.in);

    public AccountMenu(int userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public void menu() {
        int option = -1;
        while (option != 0) {
            System.out.println("Write your choice.");
            System.out.println("0. Exit");
            System.out.println("1. Change your password");
            System.out.println("2. Change your phone number");
            option = scanner.nextInt();
            switch (option) {
                case 1: {
                    us.changePassword(role, userId);
                    break;
                }
                case 2: {
                    us.changePhoneNumber(role, userId);
                    break;
                }
                case 0: {
                    break;
                }
                default: {
                    System.out.println("Invalid option!");
                }
            }
        }
    }

}
