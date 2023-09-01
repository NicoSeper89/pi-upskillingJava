package main.utilities;

import main.exceptions.ScanDataException;

import static main.FeeCollectionSystem.SCANNER;

public class DataScannerInteger implements DataScanner<Integer> {

    @Override
    public Integer enter(String title, String inputType) {

        Boolean correct = false;
        Integer value = null;
        InputValidatorInteger inputValidator = new InputValidatorInteger();

        while (!correct){

            try {
                System.out.print(title + ": ");
                value = SCANNER.nextInt();
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
