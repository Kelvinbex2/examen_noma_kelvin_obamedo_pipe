package es.etg.psp.lanzador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Lanzador {
    public final static String[] COMMANDO = {"/bin/sh", "-c"};
    public final static String MSG_BUENO = "Ha ido bien";
    public final static String ERR_MALO = "No ha ido bien";
    private static final String LS = "ls";
    
        public static void main(String[] args) {
    
            int exitVal = 0;
            StringBuilder build = new StringBuilder();
            String line = "";
            try {
                
            
            Process process = Runtime.getRuntime().exec(args);
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

        } catch (Exception e) {

            e.printStackTrace();
        }

        
    }

    
}