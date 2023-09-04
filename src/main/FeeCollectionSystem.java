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
                //OBTENER IMPORTE ADEUDADO DE MIEMBRO
                case 5:
                    AppMenu.getMemberDebt();
                    break;
                //BORRAR MIEMBRO
                case 6:
                    AppMenu.deleteMember();
                    break;
                //GENERAR CUOTA A MIEMBRO
                case 7:
                    AppMenu.generateMemberFee();
                    break;
                //ACTUALIZAR IMPORTE DE UNA CUOTA
                case 8:
                    AppMenu.updateFeeAmount();
                    break;
                //ACTUALIZAR CUOTA COMO PAGADA
                case 9:
                    AppMenu.payFee();
                    break;
                //OBTENER TODAS LAS CUOTAS
                case 10:
                    AppMenu.getAllFees();
                    break;
                //BUSCAR CUOTA POR ID
                case 11:
                    AppMenu.getFeeById();
                    break;
                //OBTENER TODAS LAS CUOTAS DE UN MIEMBRO
                case 12:
                    AppMenu.getAllMemberFees();
                    break;
                //ELIMINAR CUOTA POR ID
                case 13:
                    AppMenu.deleteFee();
                    break;
                //SALIR
                case 14:
                    exit = true;
                    break;
            }

        } while (!exit);

        System.out.println("Hasta la próxima");
    }
}