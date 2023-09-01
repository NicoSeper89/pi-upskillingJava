package main.utilities;

import main.exceptions.ScanDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidatorString implements InputValidator<String> {

    @Override
    public boolean validate(String inputValue, String inputType) throws ScanDataException {

        switch (inputType) {
            case "name":
                Integer length = inputValue.length();
                if (length > 20 || inputValue == "") {
                    throw new ScanDataException("El campo no debe estar vacio o tener mas de veinte(20) caracteres");
                }
                return true;
            case "surname":
                if (inputValue.length() >= 10 || inputValue == "")
                    throw new ScanDataException("El campo no debe estar vacio o tener mas de diez(10) caracteres");
                return true;
            case "category":
                if (inputValue.length() >= 2)
                    throw new ScanDataException("El campo debe tener un(1) caracteres");
                return true;
            case "address":
                if (inputValue.length() >= 50)
                    throw new ScanDataException("El campo no debe estar vacio o tener mas de cincuenta(50) caracteres");
                return true;
            case "phone":
                if (inputValue.length() >= 20 || inputValue == "")
                    throw new ScanDataException("El campo no debe estar vacio o tener mas de cincuenta(50) caracteres");
                return true;
            case "email":
                String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(inputValue);
                if (!matcher.matches()) throw new ScanDataException("El campo debe ser un email valido");
                return true;
            default:
                throw new RuntimeException("%ERROR_INESPERADO: valor de inputType \""
                        + inputType
                        + "\" no reconocido: " );
        }
    }
}
