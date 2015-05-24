/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Richard
 */
public class Servidor {
    private static int PUERTO = 4536;
    private static int NUMEROCLIENTES = 10;
    int conexiones = 0;
    Socket cliente;
    ServerSocket servidor;
    
    public Servidor(){
        this.createSocketThread();
    }
    
    public void createSocketThread(){
        int conexionesActivas = 0;
        try{
            servidor = new ServerSocket(PUERTO);
            while(conexionesActivas++ <= NUMEROCLIENTES || NUMEROCLIENTES == 0){
                this.esperarConexion();
                System.out.println("Cliente # "+conexiones+" Conectado");
                new Thread(new HiloServidor(cliente)).start();
            }
        } catch (IOException exp) {
            exp.printStackTrace();
        } finally {
            try {
                servidor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void esperarConexion() throws IOException{
        System.out.println("Esperando conexion...");
        cliente = servidor.accept();
        System.out.println("Conexion " + conexiones + " recibida de: " + cliente.getInetAddress().getHostAddress());
    }
    
    public static void main(String[] args) {
        new Servidor();
    }
}
