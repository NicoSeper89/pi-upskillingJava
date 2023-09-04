package main.controllers;

import main.config.JdbcConfiguration;
import main.dao.FeeDAO;
import main.dao.MemberDAO;
import main.dao.dto.FeeDTO;
import main.dao.dto.MemberDTO;
import main.dao.implH2.FeeDAOImpl;
import main.dao.implH2.MemberDAOImpl;
import main.utilities.DataScannerInteger;
import main.utilities.DataScannerString;

import java.util.List;

import static main.FeeCollectionSystem.SCANNER;

public class AppMenu {

    public static Integer showMenu() {
        //MENU PRINCIPAL
        System.out.println("----------------MENU----------------");
        System.out.println("-------- Ingrese una opción --------");
        System.out.println("------------------------------------");
        System.out.println("1 - Agregar Miembro");
        System.out.println("2 - Actualizar Información de Miembro");
        System.out.println("3 - Ver lista de Miembros");
        System.out.println("4 - Buscar Miembro");
        System.out.println("5 - Obtener importe adeudado de Miembro");
        System.out.println("6 - Eliminar Miembro");
        System.out.println("7 - Generar Cuota a Miembro");
        System.out.println("8 - Actualizar Información de Cuota");
        System.out.println("9 - Actualizar Cuota como pagada");
        System.out.println("10 - Ver lista de Cuotas");
        System.out.println("11 - Buscar Cuota");
        System.out.println("12 - Ver lista de Cuotas de un Miembro");
        System.out.println("13 - Eliminar Cuota");
        System.out.println("14 - Salir");
        System.out.println("------------------------------------");

        return enterMenuOption();
    }

    private static Integer enterMenuOption() {

        //Pedir opción menu principal y validar que sea correcta.

        Integer res = null;

        //Scanner de Integer con
        DataScannerInteger integerScanner = new DataScannerInteger();
        res = integerScanner.enter("Op", "menuOption");

        SCANNER.nextLine();

        return res;
    }

    public static void addMember() {

        try {

            System.out.println("Ingresar datos del miembro");

            //Scanner de datos
            DataScannerString stringScanner = new DataScannerString();

            //Solicitar datos de nuevo Miembro.
            String name = stringScanner.enter("Nombre", "name");
            String surname = stringScanner.enter("Apellido", "surname");
            String category = stringScanner.enter("Categoría", "category");
            String address = stringScanner.enter("Dirección", "address");
            String phone = stringScanner.enter("Teléfono", "phone");
            String email = stringScanner.enter("Email", "email");

            //Crear Miembro y almacenarlo en DB
            MemberDTO member = new MemberDTO(name, surname, category, address, phone, email);
            MemberDAO dao = new MemberDAOImpl(JdbcConfiguration.getDBConnection());
            dao.insert(member);

            System.out.println("---- Se agrego el Miembro correctamente ----");

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }


    }

    public static void updateInfoMember() {

        try {

            System.out.println("Ingresar el ID del Miembro que desea actualizar");

            //Scanner de datos Integer
            DataScannerInteger integerScanner = new DataScannerInteger();

            // Solicitar ID de Miembro y buscarlo en DB.
            Integer id = integerScanner.enter("ID Miembro", "id");
            MemberDAO dao = new MemberDAOImpl(JdbcConfiguration.getDBConnection());
            MemberDTO member = dao.getByID(id);

            System.out.println(member);
            SCANNER.nextLine();

            //Scanner de datos String
            DataScannerString stringScanner = new DataScannerString();

            //Solicitar datos de Miembro
            String name = stringScanner.enter("Nombre", "name");
            String surname = stringScanner.enter("Apellido", "surname");
            String category = stringScanner.enter("Categoría", "category");
            String address = stringScanner.enter("Dirección", "address");
            String phone = stringScanner.enter("Teléfono", "phone");
            String email = stringScanner.enter("Email", "email");

            //Actualizar instancia MiembroDTO con la nueva información
            member.setName(name);
            member.setSurname(surname);
            member.setCategory(category);
            member.setAddress(address);
            member.setPhone(phone);
            member.setEmail(email);

            dao.update(member);

            System.out.println("---- Se actualizo la información del miembro correctamente ----");

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void getAllMembers() {

        try {

            //Obtener todos los Miembros
            MemberDAO dao = new MemberDAOImpl(JdbcConfiguration.getDBConnection());

            //Instancia Lista de MiembrosDTO obtenidos de DB
            List<MemberDTO> memberList = dao.getAll();

            if (memberList.size() == 0) {
                System.out.println("---- No se encontraron Miembros ----");
                return;
            }

            System.out.println("---- Lista de Miembros ----");

            //Recorrer lista de MiembrosDTO imprimiendo sus datos.
            for (MemberDTO m : memberList) {
                System.out.println(m);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void getMemberById() {

        try {

            //Scanner de datos Integer
            DataScannerInteger integerScanner = new DataScannerInteger();

            // Solicitar ID de Miembro y buscarlo en DB.
            Integer id = integerScanner.enter("ID Miembro", "id");
            MemberDAO dao = new MemberDAOImpl(JdbcConfiguration.getDBConnection());
            MemberDTO member = dao.getByID(id);

            System.out.println("---- Miembro: ----");
            System.out.println(member);

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getMemberDebt() {

        //Scanner de datos
        DataScannerInteger integerScanner = new DataScannerInteger();

        // Solicitar ID de Miembro.
        Integer id = integerScanner.enter("ID Miembro", "id");

        //Verificar que exista el Miembro.
        //getByID arroja una exception RuntimeException si no lo encuentra.
        MemberDAO memberDAO = new MemberDAOImpl(JdbcConfiguration.getDBConnection());
        MemberDTO member = memberDAO.getByID(id);

        //Obtener Cuotas de Miembro de DB
        FeeDAO feeDAO = new FeeDAOImpl(JdbcConfiguration.getDBConnection());
        List<FeeDTO> feeList = feeDAO.getAllMemberFees(member.getId());

        //Verificar que existan Cuotas del Miembro
        if (feeList.size() == 0) {
            System.out.println("---- Miembro sin Cuotas ----");
            return;
        }

        //Filtrar cuotas impagas (con propiedad paid en false)
        //Sumar importe de todas las cuotas impagas e imprimir el total.
        Integer totalDebt = feeList.stream()
                .filter(f -> !f.getPaid())
                .mapToInt(FeeDTO::getAmount)
                .sum();

        System.out.println("---- El Miembro debe: $" + totalDebt + " ----");

    }

    public static void deleteMember() {

        try {

            System.out.println("Ingresar el ID del Miembro que desea eliminar");

            //Scanner de datos Integer
            DataScannerInteger integerScanner = new DataScannerInteger();

            // Solicitar ID de Miembro.
            Integer memberId = integerScanner.enter("ID Miembro", "id");

            //Busco el Miembro en DB para ver si existe.
            //Si no existe getByID() tira una exception RuntimeException.
            MemberDAO memberDao = new MemberDAOImpl(JdbcConfiguration.getDBConnection());
            memberDao.getByID(memberId);

            //Busco si el miembro tiene Cuotas en DB
            FeeDAO feeDao = new FeeDAOImpl(JdbcConfiguration.getDBConnection());
            List<FeeDTO> feesList = feeDao.getAllMemberFees(memberId);

            if (feesList.size() > 0) {
                //Determino si hay Cuotas impagas.
                Boolean unpaidAmounts = feesList.stream()
                        .anyMatch(m -> !m.getPaid());

                if (unpaidAmounts)
                    //Hay cuotas impagas, no se puede borrar el miembro
                    throw new RuntimeException("El Miembro tiene Cuotas impagas, debe abonarlas antes de eliminarlo");
                else {
                    //No hay cuotas impagas, se eliminan las cuotas del miembro y luego el miembro de DB.
                    feeDao.deleteAllMemberFees(memberId);
                    memberDao.delete(memberId);
                }
            } else {
                memberDao.delete(memberId);
            }

            System.out.println("---- Miembro Eliminado ----");

        } catch (
                RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void generateMemberFee() {

        try {

            System.out.println("Ingresar el ID del Miembro al que desea generar Cuota");

            //Scanner de datos Integer
            DataScannerInteger integerScanner = new DataScannerInteger();

            // Solicitar ID Miembro y buscarlo en DB.
            Integer id = integerScanner.enter("ID Miembro", "id");
            MemberDAO memberDao = new MemberDAOImpl(JdbcConfiguration.getDBConnection());
            MemberDTO member = memberDao.getByID(id);

            System.out.println(member);
            System.out.println("-------------------------------");
            System.out.println("Ingresar el importe de la cuota");

            // Solicitar Importe de la Cuota a crear e instanciar su DTO con el importe.
            Integer amount = integerScanner.enter("Importe de Cuota", "amount");
            FeeDAO feeDao = new FeeDAOImpl(JdbcConfiguration.getDBConnection());
            FeeDTO fee = new FeeDTO(amount, member);

            //Guardar nueva Cuota asociada al Miembro en DB.
            feeDao.insert(fee);

            System.out.println("---- Cuota de Miembro generada ----");

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateFeeAmount() {

        try {

            System.out.println("Ingresar el ID de la Cuota que desea actualizar el importe");

            //Scanner de datos
            DataScannerInteger integerScanner = new DataScannerInteger();

            // Solicitar ID de Cuota y buscarla en DB.
            Integer id = integerScanner.enter("ID Cuota", "id");
            FeeDAO dao = new FeeDAOImpl(JdbcConfiguration.getDBConnection());
            FeeDTO fee = dao.getByID(id);

            System.out.println(fee);
            SCANNER.nextLine();

            //Solicitar importe nuevo para la Cuota Obtenida de DB.
            Integer amount = integerScanner.enter("Importe nuevo", "amount");

            //Actualizar importe de la Cuota
            fee.setAmount(amount);

            //Actualizar la Cuota en DB.
            dao.update(fee);

            System.out.println("---- Cuota de Miembro actualizada ----");

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void payFee() {

        try {

            System.out.println("Ingresar el ID de la Cuota que desea marcar como pagada");

            //Scanner de datos
            DataScannerInteger integerScanner = new DataScannerInteger();

            // Solicitar ID de Cuota y buscarla en DB.
            Integer id = integerScanner.enter("ID Cuota", "id");
            FeeDAO dao = new FeeDAOImpl(JdbcConfiguration.getDBConnection());
            FeeDTO fee = dao.getByID(id);

            System.out.println(fee);
            SCANNER.nextLine();

            //Actualizar valor de propiedad paid de DTO como true (pagada).
            fee.setPaid(true);

            //Actualizar la Cuota en DB.
            dao.update(fee);

            System.out.println("---- Cuota de Miembro PAGADA ----");

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getAllFees() {

        try {
            //Obtener todas las Cuotas de DB.
            FeeDAO dao = new FeeDAOImpl(JdbcConfiguration.getDBConnection());

            //Instancia Lista de DTO de Cuotas y guardar todas las obtenidas de DB.
            List<FeeDTO> feeList = dao.getAll();

            if (feeList.size() == 0) {
                System.out.println("---- No hay Cuotas almacenadas ----");
                return;
            }

            System.out.println("---- Lista de Cuotas ----");

            //Recorrer lista de Cuotas imprimiendo sus datos.
            for (FeeDTO f : feeList) {
                System.out.println(f);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getFeeById() {

        System.out.println("Ingresar el ID de la Cuota que desea obtener");

        try {
            //Scanner de datos
            DataScannerInteger integerScanner = new DataScannerInteger();

            // Solicitar ID de Cuota y buscarla en DB.
            Integer id = integerScanner.enter("ID Cuota", "id");
            FeeDAO dao = new FeeDAOImpl(JdbcConfiguration.getDBConnection());
            FeeDTO fee = dao.getByID(id);

            System.out.println("---- Cuota: ----");
            System.out.println(fee);

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getAllMemberFees() {

        System.out.println("Ingresar el ID del Miembro al que quiere listar sus Cuotas");

        try {

            //Scanner de datos
            DataScannerInteger integerScanner = new DataScannerInteger();

            // Solicitar ID de Miembro.
            Integer id = integerScanner.enter("ID Miembro", "id");

            //Verificar que exista el Miembro.
            // getByID arroja una exception RuntimeException si no lo encuentra.
            MemberDAO memberDAO = new MemberDAOImpl(JdbcConfiguration.getDBConnection());
            MemberDTO member = memberDAO.getByID(id);

            //Obtener Cuotas de Miembro de DB
            FeeDAO feeDAO = new FeeDAOImpl(JdbcConfiguration.getDBConnection());
            List<FeeDTO> feeList = feeDAO.getAllMemberFees(member.getId());

            //Verificar que existan Cuotas del Miembro
            if (feeList.size() == 0) {
                System.out.println("---- Miembro sin Cuotas ----");
                return;
            }

            System.out.println("---- Cuotas del Miembro: ----");

            for (FeeDTO f : feeList) {
                System.out.println(f);
            }

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void deleteFee() {

        try {

            System.out.println("Ingresar el ID de la Cuota que desea eliminar");

            //Scanner de datos Integer
            DataScannerInteger integerScanner = new DataScannerInteger();

            // Solicitar ID de Cuota.
            Integer id = integerScanner.enter("ID Cuota", "id");
            FeeDAO dao = new FeeDAOImpl(JdbcConfiguration.getDBConnection());

            FeeDTO fee = dao.getByID(id);

            dao.delete(id);

            System.out.println("---- Cuota eliminada ----");

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }

    }
}
