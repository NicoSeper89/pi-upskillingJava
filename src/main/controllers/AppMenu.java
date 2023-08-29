package main.controllers;

import java.util.Scanner;

import main.dao.MemberDAO;
import main.dao.implH2.MemberDAOImpl;
import main.dao.dto.MemberDTO;

public class AppMenu {

    public static Integer showMenu() {
        System.out.println("------------MENU------------");
        System.out.println("---- Ingrese una opción ----");
        System.out.println("----------------------------");
        System.out.println("1 - Agregar Miembro");
        System.out.println("----------------------------");
        System.out.println("2 - Salir");

        return enterMenuOption();
    }

    private static Integer enterMenuOption() {

        Scanner scanner = new Scanner(System.in);

        Integer res = scanner.nextInt();

        return res;

    };

    public static void addMember() {

        Scanner scanner = new Scanner(System.in);

        MemberDAO dao = new MemberDAOImpl();

        System.out.println("Ingresar datos del miembro");

        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        System.out.print("Apellido: ");
        String surname = scanner.nextLine();

        System.out.print("Categoría: ");
        String category = scanner.nextLine();

        System.out.print("Dirección: ");
        String address = scanner.nextLine();

        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        MemberDTO member = new MemberDTO(name, surname, category, address, phone, email);

        dao.insert(member);

    }

}
