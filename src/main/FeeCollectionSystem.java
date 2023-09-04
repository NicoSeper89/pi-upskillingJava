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
                    break;
                //ACTUALIZAR INFORMACIÓN MIEMBRO
                case 2:
                    AppMenu.updateInfoMember();
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
                //ACTUALIZAR IMPORTE DE UNA CUOTA
                case 7:
                    AppMenu.updateFeeAmount();
                    break;
                //ACTUALIZAR CUOTA COMO PAGADA
                case 8:
                    AppMenu.payFee();
                    break;
                //OBTENER TODAS LAS CUOTAS
                case 9:
                    AppMenu.getAllFees();
                    break;
                //BUSCAR CUOTA POR ID
                case 10:
                    AppMenu.getFeeById();
                    break;
                //OBTENER TODAS LAS CUOTAS DE UN MIEMBRO
                case 11:
                    AppMenu.getAllMemberFees();
                    break;
                //ELIMINAR CUOTA POR ID
                case 12:
                    AppMenu.deleteFee();
                    break;
                //SALIR
                case 13:
                    exit = true;
                    break;
            }

        } while (!exit);

        System.out.println("Hasta la próxima");
    }
}