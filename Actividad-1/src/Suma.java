import java.util.Scanner;

/**
 * Este programa suma dos números enteros y muestra el resultado por consola.
 */
public class Suma {
    public static void main(String[] args) {
        try {
            //Declaración de scanner para ingreso de datos.
            Scanner sc = new Scanner(System.in);

            System.out.println("Ingresa el primer número: ");
            int numero1 = sc.nextInt();

            System.out.println("Ingresa el segundo número: ");
            int numero2 = sc.nextInt();

            int resultado = numero1 + numero2;

            System.out.println("La suma de " + numero1 + " y " + numero2 + " es: " + resultado);
        } catch (Exception e) {
            System.out.println("Error al ingresar los datos: " + e.toString() );
        }
    }
}