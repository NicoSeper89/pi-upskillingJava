package main.utilities;

import java.util.InputMismatchException;

public interface InputValidator<T> {

    boolean validate(T inputValue, String inputName) throws InputMismatchException;

}
