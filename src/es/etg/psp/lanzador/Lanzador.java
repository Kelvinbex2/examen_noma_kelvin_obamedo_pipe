package es.etg.psp.lanzador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lanzador {

    public final static String MSG_BUENO = "Ha ido bien";
    public final static String ERR_MALO = "No ha ido bien";
    public final static String TERMINAL = "> ";
    public final static String [] PIPE = {"java","es.etg.psp.lanzador.ges.Suma", "5","6" };


    public static void main(String[] args) throws  RuntimeException, InterruptedException {

        File file = new File("t.txt");
        
        int exitVal = 0;
        StringBuilder build = new StringBuilder();
        String line;
        try (FileOutputStream ou = new FileOutputStream(file)){

            Process process = Runtime.getRuntime().exec(PIPE);
            BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = read.readLine()) != null) {
                build.append(line).append("\n");
                ou.write(line.getBytes());
            }

            exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println(MSG_BUENO);
                System.out.println(build.toString());
                
            } else {
                System.out.println(ERR_MALO);
            }

        } catch (IOException e) {

            throw new RuntimeException(e.getMessage());
        }
        
    }
       

    

    }

   
   

