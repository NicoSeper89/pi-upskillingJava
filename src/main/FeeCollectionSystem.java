package main;

import main.controllers.AppMenu;

import java.util.Scanner;

public class FeeCollectionSystem {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {

        Integer menuOption = AppMenu.showMenu();
        Boolean exit = false;

        while (!exit) {
            switch (menuOption) {
                case 1:
                    AppMenu.addMember();
                    System.out.println("---- Se agrego el miembro correctamente ----");
                    break;
                case 2:
                    System.out.println("---- Se agrego el miembro correctamente ----");
                    break;
                case 3:
                    exit = true;
                    break;
            }

            if (!exit) {
                menuOption = AppMenu.showMenu();
            }
        }

        System.out.println("Hasta la próxima");
    }
}