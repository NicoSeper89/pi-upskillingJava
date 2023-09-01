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
                //AGREGAR MIEMBRO
                case 1:
                    AppMenu.addMember();
                    System.out.println("---- Se agrego el miembro correctamente ----");
                    break;
                //ACTUALIZAR DATOS MIEMBRO
                case 2:
                    AppMenu.updateInfoMember();
                    System.out.println("---- Se actualizo la información del miembro correctamente ----");
                    break;
                //VER LISTA DE TODOS LOS MIEMBROS
                case 3:
                    AppMenu.getAllMembers();
                    break;
                //BUSCAR MIEMBRO POR ID
                case 4:
                    AppMenu.getMemberById();
                    break;
                //BORRAR MIEMBRO
                case 5:
                    AppMenu.deleteMember();
                    break;
                //GENERAR CUOTA A MIEMBRO
                case 6:
                    AppMenu.generateMemberFee();
                    break;
                //SALIR
                case 7:
                    exit = true;
                    break;
            }

        } while (!exit);

        System.out.println("Hasta la próxima");
    }
}