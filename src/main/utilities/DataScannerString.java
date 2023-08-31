package main.utilities;

import main.exceptions.ScanDataException;

import static main.FeeCollectionSystem.SCANNER;

public class DataScannerString implements DataScanner<String> {

    @Override
    public String enter(String title, String inputType) {

        Boolean correct = false;
        String value = null;
        InputValidatorString inputValidator = new InputValidatorString();

        while (!correct){

            try {
                System.out.print(title + ": ");
                value = SCANNER.nextLine();
                correct = inputValidator.validate(value, inputType);
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
