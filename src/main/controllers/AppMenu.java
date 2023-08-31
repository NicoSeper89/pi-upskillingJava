package main.controllers;

import main.dao.MemberDAO;
import main.dao.dto.MemberDTO;
import main.dao.implH2.MemberDAOImpl;
import main.exceptions.ScanDataException;
import main.utilities.DataScannerString;

import static main.FeeCollectionSystem.SCANNER;

public class AppMenu {

    public static Integer showMenu() {
        //MENU PRINCIPAL
        System.out.println("------------MENU------------");
        System.out.println("---- Ingrese una opción ----");
        System.out.println("----------------------------");
        System.out.println("1 - Agregar Miembro");
        System.out.println("2 - Actualizar Miembro");
        System.out.println("----------------------------");
        System.out.println("3 - Salir");
        
        return enterMenuOption();
    }

    private static Integer enterMenuOption() {

        //Pedir opción menu principal y validar que sea correcta.
        
        Integer res = null;

        while (res == null) {
            try {
                if (SCANNER.hasNextInt()) {
                    res = SCANNER.nextInt();
                    if (res < 1 || res > 3) {
                        throw new ScanDataException("Opción Invalida. Valores entre 1 - 3");
                    }
                } else {
                    throw new ScanDataException("Opción Invalida. Valor no numero.");
                }
            } catch (ScanDataException e) {
                res = null;
                e.printStackTrace();
                System.out.println("Ingrese una opción correcta.");
            } finally {
                SCANNER.nextLine();
            }
        }

        return res;
    };

    public static void addMember() {

        System.out.println("Ingresar datos del miembro");

        //Scanner de datos
        DataScannerString stringScanner = new DataScannerString();

        //Solicitar datos de Miembro a Usuario.
        String name = stringScanner.enter("Nombre", "name");
        String surname = stringScanner.enter("Apellido", "surname");
        String category = stringScanner.enter("Categoría", "category");
        String address = stringScanner.enter("Dirección", "address");
        String phone = stringScanner.enter("Teléfono", "phone");
        String email = stringScanner.enter("Email", "email");

        //Crear Miembro y almacenarlo en DB
        MemberDTO member = new MemberDTO(name, surname, category, address, phone, email);
        MemberDAO dao = new MemberDAOImpl();
        dao.insert(member);

    }

    public static void updateMember() {
    }
}
