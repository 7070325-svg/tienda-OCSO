import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class VentasEmpleados {

    public static int pedirEmpleados(Scanner sc) {
        int n;
        while (true) {
            System.out.print("Ingrese el número de empleados (1 a 3): ");
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n >= 1 && n <= 3) {
                    return n;
                } else {
                    System.out.println("Debe ser un número entre 1 y 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
            }
        }
    }

    public static Map<String, Double> pedirVentas(Scanner sc, int n) {
        Map<String, Double> ventas = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            while (true) {
                System.out.print("Ingrese la venta del empleado " + i + ": ");
                try {
                    double venta = Double.parseDouble(sc.nextLine());
                    ventas.put("Empleado " + i, venta);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Ingrese un valor numérico.");
                }
            }
        }
        return ventas;
    }

    public static void calcularYMostrar(Map<String, Double> ventas) {
        double total = 0;
        for (double v : ventas.values()) {
            total += v;
        }
        double promedio = total / ventas.size();

        String mayorEmpleado = null;
        String menorEmpleado = null;
        double mayorVenta = Double.MIN_VALUE;
        double menorVenta = Double.MAX_VALUE;

        int superiores = 0;

        for (Map.Entry<String, Double> entry : ventas.entrySet()) {
            String empleado = entry.getKey();
            double venta = entry.getValue();

            if (venta > mayorVenta) {
                mayorVenta = venta;
                mayorEmpleado = empleado;
            }
            if (venta < menorVenta) {
                menorVenta = venta;
                menorEmpleado = empleado;
            }
            if (venta > promedio) {
                superiores++;
            }
        }

        System.out.println("\n--- Resultados ---");
        for (Map.Entry<String, Double> entry : ventas.entrySet()) {
            System.out.printf("%s: %.2f\n", entry.getKey(), entry.getValue());
        }
        System.out.printf("Total de ventas: %.2f\n", total);
        System.out.printf("Promedio de ventas: %.2f\n", promedio);
        System.out.printf("Mayor venta: %s (%.2f)\n", mayorEmpleado, mayorVenta);
        System.out.printf("Menor venta: %s (%.2f)\n", menorEmpleado, menorVenta);
        System.out.println("Empleados que superaron el promedio: " + superiores);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = pedirEmpleados(sc);
        Map<String, Double> ventas = pedirVentas(sc, n);
        calcularYMostrar(ventas);
        sc.close();
    }
}
