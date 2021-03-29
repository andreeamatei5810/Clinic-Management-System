package Menu;

import Services.ClinicServices;

import java.util.Scanner;

public class ClinicMenu {

    Scanner scanner= new Scanner(System.in);
    ClinicServices cs = new ClinicServices();

    public void menu(){
        int option = -1;
        while(option!=0) {
            System.out.println("Write your choice");
            System.out.println("0. Exit");
            System.out.println("1. Show all wards");
            System.out.println("2. Show all medicines");
            option = scanner.nextInt();
            switch(option){
                case 1:{
                    cs.showAllWards();
                    break;
                }
                case 2:{
                    cs.showAllMedicine();
                    break;
                }
                case 0:{
                    break;
                }
                default:{
                    System.out.println("Invalid option!");
                }
            }
        }
    }

}
