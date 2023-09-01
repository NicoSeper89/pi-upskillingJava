package main.controllers;

import main.dao.FeeDAO;
import main.dao.MemberDAO;
import main.dao.dto.FeeDTO;
import main.dao.dto.MemberDTO;
import main.dao.implH2.FeeDAOImpl;
import main.dao.implH2.MemberDAOImpl;
import main.exceptions.ScanDataException;
import main.utilities.DataScannerInteger;
import main.utilities.DataScannerString;

import java.util.List;

import static main.FeeCollectionSystem.SCANNER;

public class AppMenu {

    public static Integer showMenu() {
        //MENU PRINCIPAL
        System.out.println("------------MENU------------");
        System.out.println("---- Ingrese una opción ----");
        System.out.println("----------------------------");
        System.out.println("1 - Agregar Miembro");
        System.out.println("2 - Actualizar Información de Miembro");
        System.out.println("3 - Ver lista de Miembros");
        System.out.println("4 - Buscar Miembro por ID");
        System.out.println("5 - Borrar Miembro");
        System.out.println("6 - Generar Cuota a Miembro");
        System.out.println("7 - Actualizar Información de Cuota");
        System.out.println("8 - Ver lista de cuotas");
        System.out.println("9 - Buscar Cuota por ID");
        System.out.println("----------------------------");
        System.out.println("10 - Salir");

        return enterMenuOption();
    }

    private static Integer enterMenuOption() {

        //Pedir opción menu principal y validar que sea correcta.

        Integer res = null;

        while (res == null) {
            try {
                if (SCANNER.hasNextInt()) {
                    res = SCANNER.nextInt();
                    if (res < 1 || res > 10) {
                        throw new ScanDataException("Opción Invalida. Valores entre 1 - 10");
                    }
                } else {
                    throw new ScanDataException("Opción Invalida. Valor no numérico.");
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

    public static void updateInfoMember() {

        System.out.println("Ingresar el ID del Miembro que desea actualizar");

        // Solicitar ID y buscar el miembro por ID para saber si existe en DB.
        Integer id = SCANNER.nextInt();
        MemberDAO dao = new MemberDAOImpl();
        MemberDTO member = dao.getByID(id);

        System.out.println(member);
        SCANNER.nextLine();

        //Scanner de datos
        DataScannerString stringScanner = new DataScannerString();

        //Solicitar datos de Miembro
        String name = stringScanner.enter("Nombre", "name");
        String surname = stringScanner.enter("Apellido", "surname");
        String category = stringScanner.enter("Categoría", "category");
        String address = stringScanner.enter("Dirección", "address");
        String phone = stringScanner.enter("Teléfono", "phone");
        String email = stringScanner.enter("Email", "email");

        //Crear DTO Miembro y almacenarlo en DB
        member.setName(name);
        member.setSurname(surname);
        member.setCategory(category);
        member.setAddress(address);
        member.setPhone(phone);
        member.setEmail(email);

        dao.update(member);

    }

    public static void getAllMembers() {

        //Obtener todos los Miembros
        MemberDAO dao = new MemberDAOImpl();

        List<MemberDTO> memberList = dao.getAll();

        for (MemberDTO m : memberList) {

            System.out.println(m);

        }

    }

    public static void getMemberById() {

        System.out.println("Ingresar el ID del Miembro que desea obtener");

        // Solicitar ID y buscar Miembro por ID en DB.
        Integer id = SCANNER.nextInt();
        MemberDAO dao = new MemberDAOImpl();
        MemberDTO member = dao.getByID(id);

        System.out.println(member);

    }

    public static void deleteMember() {

        System.out.println("Ingresar el ID del miembro que desea eliminar");

        Integer id = SCANNER.nextInt();
        MemberDAO dao = new MemberDAOImpl();
        dao.delete(id);

    }

    public static void generateMemberFee() {

        System.out.println("Ingresar el ID del Miembro al que desea generar Cuota");

        try {


            // Solicitar ID y buscar Miembro por ID para comprobar que exista.
            Integer id = SCANNER.nextInt();
            MemberDAO memberDao = new MemberDAOImpl();
            MemberDTO member = memberDao.getByID(id);

            System.out.println(member);
            System.out.println("-------------------------------");
            System.out.println("Ingresar el importe de la cuota");

            Integer amount = SCANNER.nextInt();
            FeeDAO feeDao = new FeeDAOImpl();
            FeeDTO fee = new FeeDTO(amount, member);

            feeDao.insert(fee);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void updateFeeAmount() {

        System.out.println("Ingresar el ID de la Cuota que desea actualizar el importe");

        //Scanner de datos
        DataScannerInteger integerScanner = new DataScannerInteger();

        // Solicitar ID y buscar Cuota por ID para saber si existe en DB.
        Integer id = integerScanner.enter("ID Cuota", "id");
        FeeDAO dao = new FeeDAOImpl();
        FeeDTO fee = dao.getByID(id);

        System.out.println(fee);
        SCANNER.nextLine();

        //Solicitar datos de Cuota para actualizar
        Integer amount = integerScanner.enter("Importe nuevo", "amount");

        //Crear DTO Miembro y almacenarlo en DB
        fee.setAmount(amount);

        dao.update(fee);

    }

    public static void getAllFees() {

        try {
            //Obtener todas las Cuotas
            FeeDAO dao = new FeeDAOImpl();

            List<FeeDTO> feeList = dao.getAll();

            for (FeeDTO f : feeList) {
                System.out.println(f);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void getFeeById() {

        System.out.println("Ingresar el ID de la Cuota que desea obtener");

        try {
            // Solicitar ID y buscar Cuota por ID en DB.
            Integer id = SCANNER.nextInt();
            FeeDAO dao = new FeeDAOImpl();
            FeeDTO fee = dao.getByID(id);

            System.out.println(fee);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
