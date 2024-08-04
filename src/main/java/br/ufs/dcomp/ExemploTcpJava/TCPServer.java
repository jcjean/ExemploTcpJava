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
            byte[] buf = new byte[32]; // buffer de recepção
            
            int bytesRead;
            
            System.out.println("[ Aguardando mensagens... ]");
            while(true){
                bytesRead = is.read(buf); // Operação bloqueante (aguardando chegada de dados)
                if(bytesRead==-1) break; //Verifica se o cliente encerrou a conversa
            
                String msg = new String(buf, 0, bytesRead); // Mapeando vetor de bytes recebido para String. (byte[], inicio, tamanho)
                
                if(msg.equalsIgnoreCase("sair")){
                    System.out.println("[  Conversa encerrada  ]");
                    break;
                }else{
                    System.out.println("  Mensagem recebida: "+ msg);
                    
                    String ack = scan.nextLine();
                    byte[] buf2 = ack.getBytes();
                    os.write(buf2);
                    os.flush();
                    System.out.println("[ Mensagem Enviada! ]");
                }
            }
            
            ss.close();
            scan.close();
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}