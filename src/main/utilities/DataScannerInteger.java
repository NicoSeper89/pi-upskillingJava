package main.utilities;

import main.exceptions.ScanDataException;

import java.util.InputMismatchException;

import static main.FeeCollectionSystem.SCANNER;

public class DataScannerInteger implements DataScanner<Integer> {

    @Override
    public Integer enter(String title, String inputType) {

        //Obtener dato Integer del usuario con Scanner
        //Pedirlo hasta que pase la validación de InputValidatorInteger()
        //Devolver dato.

        Boolean correct = false;
        Integer value = null;
        InputValidatorInteger inputValidator = new InputValidatorInteger();

        while (!correct){

            try {
                System.out.print(title + ": ");
                value = SCANNER.nextInt();
                correct = inputValidator.validate(value, inputType);
            } catch (ScanDataException | InputMismatchException e) {
                e.printStackTrace();
                System.out.println("");
                SCANNER.nextLine();
                correct = false;
                System.out.println("Ingrese un dato correcto");
            }

        }
        return value;
    }
}
