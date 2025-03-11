package es.etg.psp.lanzador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Lanzador {

    public final static String MSG_BUENO = "Ha ido bien";
    public final static String ERR_MALO = "No ha ido bien";
    public final static String TERMINAL = "> ";
    public final static String PIPE = " | ";

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print(TERMINAL);
            String commando = scanner.nextLine().trim();

            System.out.print(TERMINAL);
            String commado2 = scanner.nextLine().trim();

            String[] lan = { commando, PIPE, commado2 };
            ejecutar(lan);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int ejecutar(String[] comand) {
        int exitVal = 0;
        StringBuilder build = new StringBuilder();
        String line = "";
        try {

            String [] comandoCompleto = unir(comand);
            Process process = Runtime.getRuntime().exec(comandoCompleto);
            BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = read.readLine()) != null) {
                build.append(line).append(System.lineSeparator());
            }

            if (exitVal == 0) {
                System.out.println(MSG_BUENO);
                System.out.println(build);
            } else {
                System.out.println(ERR_MALO);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        return exitVal;
    }

    public static String[] unir(String[] comand) {
        String comandoCompleto = String.join("", comand);
        return new String[] { comandoCompleto };
    }

}