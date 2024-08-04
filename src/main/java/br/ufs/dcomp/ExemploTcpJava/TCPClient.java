/**
 * @author Tarcisio da Rocha (Prof. DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.util.Scanner;
import java.io.*;
public class TCPClient{
    public static void main(String[] args){
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("[ Conectando com o Servidor TCP    ..................  ");
            Socket sock = new Socket("127.0.0.1", 3300);
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); // Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); // Canal de saída de dados
            int count=0;

            do{
                String msg = scan.nextLine();
                
                if(msg.trim().equalsIgnoreCase("sair")){
                    byte[] buf = msg.getBytes(); // Obtendo a respresntação em bytes da mensagem
                    os.write(buf);
                    os.flush();
                    System.out.println("[ Encerrando conversa... ]");
                    count++;
                }else{
                    byte[] buf = msg.getBytes(); // Obtendo a respresntação em bytes da mensagem
                    os.write(buf);
                    os.flush();
                    System.out.println("[ Mensagem Enviada! ]");
                    
                    byte[] buf2 = new byte[32];
                    int bytesRead = is.read(buf2);
                    String resp = new String(buf2, 0, bytesRead);
                    System.out.println("  Mensagem recebida: "+ resp);
                }
            }while(count==0);
            
            sock.close();
            scan.close();
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}