package main.utilities;

import java.util.InputMismatchException;

public interface InputValidator<T> {

    //Validar datos de tipo T para distintas circunstancias que dependen del inputType
    //devuelve true si pasa las validaciones y false o una exception si no pasa alguna.
    boolean validate(T inputValue, String inputType) throws InputMismatchException;

}
