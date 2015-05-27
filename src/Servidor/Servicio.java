package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Servidor.Enrutador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andresroberto
 */
public class Servicio {
    private static final int PUERTO = 4536;
    private Socket socketCliente;
    private ServerSocket socketServidor;
    
    public void iniciar () {
        this.crearHilo();
    }
    
    private void esperarConexion() throws IOException{
        System.out.println("Sirviendo en " + socketServidor.getInetAddress().getHostAddress() + " puerto " + PUERTO);
        socketCliente = socketServidor.accept();
    }
    
    public void crearHilo(){
        try{
            socketServidor = new ServerSocket(PUERTO);
            
            while(true){
                this.esperarConexion();
                new Thread(new Enrutador(socketCliente)).start();
            }
        } catch (IOException exp) {
            exp.printStackTrace();
        } finally {
            try {
                socketServidor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
