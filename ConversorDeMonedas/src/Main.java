import service.ApiService;
import util.CurrencyConverter;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // Define tu API Key aquí
    private static final String API_KEY = "1ebda7741483098756e09505"; // API Key

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ApiService apiService = new ApiService(API_KEY);

        // Bucle del menú
        boolean exit = false;
        while (!exit) {
            System.out.println("\nSeleccione una opción de conversión:");
            System.out.println("1) Dólar a Peso Argentino");
            System.out.println("2) Peso Argentino a Dólar");
            System.out.println("3) Dólar a Real Brasileño");
            System.out.println("4) Real Brasileño a Dólar");
            System.out.println("5) Dólar a Peso Mexicano");
            System.out.println("6) Peso Mexicano a Dólar");
            System.out.println("7) Dólar a Peso Uruguayo");
            System.out.println("8) Peso Uruguayo a Dólar");
            System.out.println("9) Dólar a Euro");
            System.out.println("10) Euro a Dólar");
            System.out.println("11) Salir");
            System.out.print("Opción: ");
            try {
                int option = scanner.nextInt();

                if (option == 11) {
                    System.out.println("¡Gracias por usar el conversor de monedas!");
                    exit = true;
                    continue;
                }

                // Monedas de origen y destino según la opción
                String baseCurrency = "";
                String targetCurrency = "";

                switch (option) {
                    case 1 -> {
                        baseCurrency = "USD";
                        targetCurrency = "ARS";
                    }
                    case 2 -> {
                        baseCurrency = "ARS";
                        targetCurrency = "USD";
                    }
                    case 3 -> {
                        baseCurrency = "USD";
                        targetCurrency = "BRL";
                    }
                    case 4 -> {
                        baseCurrency = "BRL";
                        targetCurrency = "USD";
                    }
                    case 5 -> {
                        baseCurrency = "USD";
                        targetCurrency = "MXN";
                    }
                    case 6 -> {
                        baseCurrency = "MXN";
                        targetCurrency = "USD";
                    }
                    case 7 -> {
                        baseCurrency = "USD";
                        targetCurrency = "UYU";
                    }
                    case 8 -> {
                        baseCurrency = "UYU";
                        targetCurrency = "USD";
                    }
                    case 9 -> {
                        baseCurrency = "USD";
                        targetCurrency = "EUR";
                    }
                    case 10 -> {
                        baseCurrency = "EUR";
                        targetCurrency = "USD";
                    }
                    default -> {
                        System.out.println("Opción no válida. Intente de nuevo.");
                        continue;
                    }
                }

                // Pedir monto a convertir
                double amount = -1;
                while (amount < 0) {
                    try {
                        System.out.print("Ingrese el monto a convertir: ");
                        amount = scanner.nextDouble();
                        if (amount < 0) {
                            System.out.println("El monto no puede ser negativo. Intente de nuevo.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida. Por favor, ingrese solo números.");
                        scanner.next(); // Limpiar entrada no válida
                    }
                }

                // Realizar la conversión
                try {
                    String jsonRates = apiService.getRates(baseCurrency);
                    double convertedAmount = CurrencyConverter.convert(jsonRates, targetCurrency, amount);
                    System.out.println("Resultado: " + amount + " " + baseCurrency + " = " + convertedAmount + " " + targetCurrency);
                } catch (IOException e) {
                    System.err.println("Error de red o con la API: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Error inesperado: " + e.getMessage());
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese solo números.");
                scanner.next(); // Limpiar entrada no válida
            }
        }
    }
}
