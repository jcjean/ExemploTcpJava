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
                
                if(msg.trim().equals("sair")){
                    byte[] buf = msg.getBytes(); // Obtendo a respresntação em bytes da mensagem
                    os.write(buf);
                    System.out.println("[OK]");
                    count++;
                }else{
                    byte[] buf = msg.getBytes(); // Obtendo a respresntação em bytes da mensagem
                    os.write(buf);
                    System.out.println("[OK]");
                    
                    byte[] buf2 = new byte[20];
                    is.read(buf2);
                    String resp = new String(buf2);
                    System.out.println("  ACK>> "+ resp);
                }
            }while(count==0);
            
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}