/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Richard
 */
public class Cliente {
    private String host = "localhost";
    private int puerto = 4536;
    private Socket socket = null;
    private DataOutputStream salida = null;
    private DataInputStream entrada = null;
    
    public void crearConexion(){
        try {
            this.conectarAlServidor();
            int i = 0;
            while(true){
                this.obtenerFlujos();
                salida.writeUTF("Enviando dato: " + i++);
                System.out.println(entrada.readUTF());
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void conectarAlServidor() throws IOException{
        socket = new Socket(host, puerto);
    }
    
    private void obtenerFlujos() throws IOException{
        salida = new DataOutputStream(socket.getOutputStream());
        salida.flush();
        
        entrada = new DataInputStream(socket.getInputStream());
    }
    
    public static void main(String[] args) {
        Cliente c = new Cliente();
        c.crearConexion();
    }
}
