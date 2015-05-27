/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import bankingapp.BankingApp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;

/**
 *
 * @author Richard
 */
public class HiloServidor implements Runnable {
    private static int numeroClientes = 0;
    Socket cliente;
    BankingApp aplicacion;
    DataInputStream entrada = null;
    DataOutputStream salida = null;
    HiloServidor(Socket cliente){
        this.cliente = cliente;
        this.aplicacion = new BankingApp();
    }
    @Override
    public void run() {
        numeroClientes++;    
        try {
            while(true){
                this.obtenerFlujos();
                String mensaje = entrada.readUTF();
                atender(mensaje);
                //System.out.println("mensaje: " + mensaje);
                //salida.writeUTF("recibido el mensaje :" + mensaje);
            }
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch( Exception e){
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();      
        } finally{
            this.cerrarConexiones();
        }
    }
    private String procesar(String mensaje){
        
        return null;
    }
    
    private void obtenerFlujos() throws IOException{
        salida = new DataOutputStream(cliente.getOutputStream());
        salida.flush();
        entrada = new DataInputStream(cliente.getInputStream());
    }
    private void cerrarConexiones(){
        try {
            salida.close();
            entrada.close();
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String atender(String mensaje) {
        String[] mensajeDividido = mensaje.split(":");
        
        switch(mensajeDividido[0]) {
            case "session/create" :
                return this.responder(this.aplicacion.crearSesion(mensajeDividido[1]));
            default:
                return "ERROR";
        }
    }

    private String responder(Object objeto) {
        if (objeto == null) {
            return "ERROR";
        } else {
            if (objeto instanceof String) {
                return (String)objeto;
            } else {
                return objeto.toString();
            }
        }
    }
    
}
