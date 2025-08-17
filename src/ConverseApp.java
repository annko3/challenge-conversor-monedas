import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class ConverseApp extends Converse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int elecion;
        double valor;
        String moneda;
        boolean continuar = true;

        do {
            System.out.println("""
                Sea bienvenido al conversor de monedas
                
                 1) Dólar -> Peso Argentino
                 2) Peso Argentino -> Dólar
                 3) Dolar -> Real Brazileño
                 4) Real Brazileño -> Dólar
                 5) Dólar -> Sol Peruano
                 6) Sol Peruano -> Dólar
                 7) Salir
                
                 Elija una opción valida""");
            elecion = Integer.parseInt(scanner.nextLine());

            if (elecion == 7){
                System.out.println("¡Gracias por usar el conversor de monedas!");
                continuar = false;
            } else if (elecion >=1 && elecion <= 6) {
                if (elecion == 1){
                    moneda = "ARS";
                } else if (elecion == 2) {
                    moneda = "ARS";
                } else if (elecion == 3) {
                    moneda = "BRL";
                } else if (elecion == 4) {
                    moneda = "BRL";
                } else if (elecion == 5) {
                    moneda = "PEN";
                } else {
                    moneda = "PEN";
                }

                System.out.println("Ingresa el valor que deseas convertir:");
                valor = Double.parseDouble(scanner.nextLine());

                try {
                    Map<String, Double> tasasDeCambio = obtenerTasasDeCambio();
                    double resultado = convertirMoneda(elecion, valor, tasasDeCambio);
                    System.out.println("El valor " + valor + " " + moneda + " corresponde al valor final de " + resultado);
                } catch (IOException e) {
                    System.out.println("No se pudieron obtener las tasas de cambio. Inténtalo de nuevo más tarde.");
                }

            }else {
                System.out.println("Opción inválida. Por favor, elige una opción entre 1 y 7.");
            }

        }while (continuar);

    }
}