package es.etg.psp.lanzador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Lanzador {

    public final static String MSG_BUENO = "Ha ido bien";
    public final static String ERR_MALO = "No ha ido bien";
    public final static String TERMINAL = "> ";
    public final static String PIPE = "|";


    public static void main(String[] args) throws  RuntimeException {

        try (Scanner scanner = new Scanner(System.in)){
            System.out.print(TERMINAL);
            String commando = scanner.nextLine().trim();

            
            ejecutar(commando);
        } catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }

    }

    public static int ejecutar(String comand)  throws RuntimeException, InterruptedException{
        int exitVal = 0;
        StringBuilder build = new StringBuilder();
        String line;
        try {

           String [] comandoCompleto = unir(comand) ;

           String primer = comandoCompleto[0];
           String second = comandoCompleto[1];


            Process process = Runtime.getRuntime().exec(primer);
            BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = read.readLine()) != null) {
                build.append(line).append("\n");
             
            }




            exitVal = process.waitFor();


            Process process2 = Runtime.getRuntime().exec(second);
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(process2.getOutputStream()));
            wr.write(build.toString());
            wr.close();



            BufferedReader read2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));
            while ((line = read2.readLine()) != null) {
                System.out.println(line);
             
            }



            exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println(MSG_BUENO);
            } else {
                System.out.println(ERR_MALO);
            }

        } catch (IOException e) {

            throw new RuntimeException(e.getMessage());
        }
        return exitVal;
    }

    public static String[] unir(String comand) {
        return comand.split("\\|");

    }

}