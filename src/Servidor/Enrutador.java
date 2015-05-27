package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andresroberto
 */
public class Enrutador implements Runnable {
    
    Socket socketCliente;
    DataInputStream entrada;
    DataOutputStream salida;
    
    String mensaje;
    Controlador controlador;

    Enrutador(Socket socketCliente) {
        this.socketCliente = socketCliente;
        this.controlador = new Controlador();
    }
    

    private void obtenerFlujos() throws IOException{
        salida = new DataOutputStream(socketCliente.getOutputStream());
        salida.flush();
        
        entrada = new DataInputStream(socketCliente.getInputStream());
    }
    
    private void cerrarConexiones(){
        try {
            salida.close();
            entrada.close();
            socketCliente.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private String generarRespuesta(String entrada) {
        String[] arrayEntrada = entrada.split(":");
        
        switch(arrayEntrada[0]) {
            case "session/create" :
                String respuesta = controlador.crearSesion(arrayEntrada[1]);
                return respuesta;
            default:
                return "ERROR";
        }
    }
    
    private void procesarConexion() throws IOException{
        do{
            mensaje = entrada.readUTF();
            String salida  = generarRespuesta(mensaje);

            this.enviarMensaje(salida);
        }while(!mensaje.equals("EXIT"));
    }
    
    private void enviarMensaje(String mensaje) throws IOException{
        salida.writeUTF(mensaje);
        salida.flush();
    }
    
    @Override
    public void run() {   
        try {
            this.obtenerFlujos();
            this.procesarConexion();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch( Exception e){
            e.printStackTrace();      
        } finally{
            this.cerrarConexiones();
        }
    }
    
}
