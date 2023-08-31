package main;

import main.controllers.AppMenu;

import java.util.Scanner;

public class FeeCollectionSystem {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        Integer menuOption;
        Boolean exit = false;

        do {
            //Mostrar menu principal en consola, devuelve la opción ingresada.
            menuOption = AppMenu.showMenu();

            switch (menuOption) {
                //AGREGAR SOCIO
                case 1:
                    AppMenu.addMember();
                    System.out.println("---- Se agrego el Socio correctamente ----");
                    break;
                //ACTUALIZAR DATOS SOCIO    
                case 2:
                    System.out.println("---- Se agrego el miembro correctamente ----");
                    break;
                //SALIR    
                case 3:
                    exit = true;
                    break;
            }

        } while (!exit);

        System.out.println("Hasta la próxima");
    }
}