package es.etg.psp.lanzador;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lanzador {
    public final static String[] COMMANDO = { "ls", "grep e" };
    public final static String MSG_BUENO = "Ha ido bien";
    public final static String ERR_MALO = "No ha ido bien";

    public static void main(String[] args) {
        try {
            ejecutar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static  int ejecutar() {
        int exitVal = 0;
        StringBuilder build = new StringBuilder();
        String line = "";
        try {
            Process process = Runtime.getRuntime().exec(COMMANDO);
            BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = read.readLine()) != null) {
                build.append(line).append(System.lineSeparator());
            }

            if (exitVal == 0) {
                System.out.println(MSG_BUENO);
                System.out.println(build.toString());
            } else {
                System.out.println(ERR_MALO);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return exitVal;
    }
}