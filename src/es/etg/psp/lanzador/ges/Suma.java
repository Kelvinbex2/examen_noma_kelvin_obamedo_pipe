package es.etg.psp.lanzador.ges;

public class Suma {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: Debes proporcionar dos números como argumentos.");
            return;
        }

        try {
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);
            new Suma().sumar(num1, num2);
        } catch (NumberFormatException e) {
            System.out.println("Error: Los argumentos deben ser números enteros.");
        }
    }

    public void sumar(int num1, int num2) {
        int total = 0;
        for (int i = num1; i <= num2; i++) {
            System.out.print(i + (i < num2 ? " + " : " "));
            total += i;
        }
        System.out.println("= " + total);
    }
}
