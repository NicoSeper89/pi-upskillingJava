package main;

import main.controllers.AppMenu;

public class FeeCollectionSystem {
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