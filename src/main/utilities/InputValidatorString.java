package main.utilities;

import main.exceptions.ScanDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidatorString implements InputValidator<String> {

    @Override
    public boolean validate(String inputValue, String inputType) throws ScanDataException {

        //VALIDACIONES PARA DATOS DE TIPO STRING

        int length = inputValue.length();

        switch (inputType) {
            case "name", "surname" -> {
                if (length > 30 || inputValue.equals(""))
                    throw new ScanDataException("El campo no debe estar vacío o tener mas de 30(treinta) caracteres");
                return true;
            }
            case "category" -> {
                if (length > 1 || inputValue.equals(""))
                    throw new ScanDataException("El campo debe tener 1(un) carácter solo");
                return true;
            }
            case "address" -> {
                if (length > 50 || inputValue.equals(""))
                    throw new ScanDataException("El campo no debe estar vacío o tener mas de 50(cincuenta) caracteres");
                return true;
            }
            case "phone" -> {
                if (length > 40 || inputValue.equals(""))
                    throw new ScanDataException("El campo no debe estar vacío o tener mas de 40(cuarenta) caracteres");
                return true;
            }
            case "email" -> {
                String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(inputValue);
                if ((length > 40 || !matcher.matches()) && !inputValue.equals("")) throw new ScanDataException("El campo debe ser un email valido y no superar los 40(cuarenta) caracteres, o bien estar vacío");
                return true;
            }
            default -> throw new RuntimeException("%ERROR_INESPERADO: valor de inputType \""
                    + inputType
                    + "\" no reconocido: ");
        }
    }
}
