package main.utilities;

import main.exceptions.ScanDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidatorInteger implements InputValidator<Integer> {

    @Override
    public boolean validate(Integer inputValue, String inputType) throws ScanDataException {

        switch (inputType) {
            case "id":
                if (inputValue <= 0) {
                    throw new ScanDataException("El campo debe ser igual o mayor a 1");
                }
                return true;
            case "amount":
                if (inputValue < 0) {
                    throw new ScanDataException("El campo debe ser igual o mayor a 0");
                }
                return true;
            default:
                throw new RuntimeException("%ERROR_INESPERADO%");
        }
    }
}
