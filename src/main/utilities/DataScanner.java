package main.utilities;

public interface DataScanner<T> {

    //Obtener datos del usuario de tipo T y devolverlo.
    //title para imprimir mensaje antes de solicitar. Ejemplo: "ID Persona".
    //inputName para saber qué validación aplicar dentro del método. Ejemplo: "id"
    T enter(String title, String inputName);

}
