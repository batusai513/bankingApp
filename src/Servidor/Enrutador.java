package Servidor;

import application.HiloServidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    DataInputStream inputStream;
    DataOutputStream outpuStream;
    
    String entrada;
    Controlador controlador;

    Enrutador(Socket socketCliente) {
        this.socketCliente = socketCliente;
        this.controlador = new Controlador();
    }
    
    private void obtenerFlujos() throws IOException{
        outpuStream = new DataOutputStream(socketCliente.getOutputStream());
        outpuStream.flush();
        
        inputStream = new DataInputStream(socketCliente.getInputStream());
    }
    
    private void cerrarConexiones(){
        try {
            outpuStream.close();
            inputStream.close();
            socketCliente.close();
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
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
    
    @Override
    public void run() {   
        try {
            while(true){
                obtenerFlujos();
                
                String entrada = inputStream.readUTF();
                String salida  = generarRespuesta(entrada);
                
                outpuStream.writeUTF(salida);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch( Exception e){
            e.printStackTrace();      
        } finally{
            this.cerrarConexiones();
        }
    }
    
}
