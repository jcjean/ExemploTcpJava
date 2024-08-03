/**
 * @author Tarcisio da Rocha (Prof. DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.util.Scanner;
import java.io.*;
public class TCPServer{
    public static void main(String[] args){
        
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("[ Iniciando Servidor TCP    .........................  ");
            ServerSocket ss = new ServerSocket(3300, 5, InetAddress.getByName("127.0.0.1"));
            System.out.println("[OK] ]");
            
            System.out.print("[ Aquardando pedidos de conexão    ..................  ");
            Socket sock = ss.accept(); // Operação bloqueante (aguardando pedido de conexão)
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); //Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); //Canal de saída de dados
            byte[] buf = new byte[20]; // buffer de recepção
            //String aux = "0";
            int count=0;
            
            System.out.print("[ .............. ]");
            do{
                is.read(buf); // Operação bloqueante (aguardando chegada de dados)
            
                String msg = new String(buf); // Mapeando vetor de bytes recebido para String
                System.out.println("[OK] ]");
                System.out.println("  Mensagem recebida: "+ msg);
                
                if(msg.equals("sair")){
                    System.out.println("  Conversa encerrada>> "+ msg);
                    count++;
                }else{
                    String ack = scan.nextLine();
                    byte[] buf2 = ack.getBytes();
                    os.write(buf2);
                }
            }while(count==0);
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}