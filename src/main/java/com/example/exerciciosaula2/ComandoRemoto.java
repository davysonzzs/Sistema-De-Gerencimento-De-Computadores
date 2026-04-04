package com.example.exerciciosaula2;

import java.io.IOException;
import java.io.InterruptedIOException;

public class ComandoRemoto {
    static final String psExecPath = "src/main/resources/tools/PsExec.exe";
    public static void bloquearTela(String ipOuNome){

        try {
            ProcessBuilder pB = new ProcessBuilder(psExecPath, "\\\\" + ipOuNome, "-s", "-accepteula", "rundll32.exe", "user32.dll,LockWorkStation");
            pB.redirectErrorStream(true);
            System.out.println("enviando para " + ipOuNome);
            Process processo = pB.start();
            int codigoDeSaida = processo.waitFor();
            if (codigoDeSaida == 0){
                System.out.println("Deu bom, codigo de saida: " + codigoDeSaida);
            } else {
                System.err.println("Falha ao enviar lock. Código: " + codigoDeSaida);
            }
        } catch (IOException e){
            System.err.println("Erro caminho não encontrado " + e.getMessage());
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("interropido");
        }
    }

    public static boolean estaBloqueado(String ipOuNome){
            try {
                ProcessBuilder pb = new ProcessBuilder(psExecPath, "\\\\" + ipOuNome, "-s", "-accepteula", "powershell.exe", "-Command", "(Get-Process LogonUI -ErrorAction SilentlyContinue).Count");
                pb.redirectErrorStream(true);
                Process pbStart = pb.start();
                java.io.BufferedReader reader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(pbStart.getInputStream(), "UTF-8")
                );

                String linha;
                int quatidadeDeLogonUI = 0;

                while ((linha = reader.readLine()) != null){
                    linha.trim();
                    if (!linha.isEmpty()){
                        try{
                            quatidadeDeLogonUI = Integer.parseInt(linha);
                        } catch (NumberFormatException ignored){}
                    }
                }
                pbStart.waitFor();
                boolean bloqueado = (quatidadeDeLogonUI > 0);
                return bloqueado;
            }catch (Exception e){
                return false;
            }
    }
}
