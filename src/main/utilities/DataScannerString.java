package main.utilities;

import main.exceptions.ScanDataException;

import static main.FeeCollectionSystem.SCANNER;

public class DataScannerString implements DataScanner<String> {

    @Override
    public String enter(String title, String inputName) {

        Boolean correct = false;
        String value = null;
        InputValidator<String> inputValidator = new InputValidatorString();

        while (!correct){

            try {
                System.out.print(title + ": ");
                value = SCANNER.nextLine();
                correct = inputValidator.validate(value, inputName);
            } catch (ScanDataException e) {
                e.printStackTrace();
                System.out.println("");
                correct = false;
                System.out.println("Ingrese un dato correcto");
            }

        }

        return value;
    }
}
