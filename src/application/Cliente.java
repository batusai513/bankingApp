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
    private InputStream is = null;
    private OutputStream os = null;
    
    public void crearConexion(){
        try {
            socket = new Socket(host, puerto);
            os = socket.getOutputStream();
            is = socket.getInputStream();
            int i = 0;
            while(true){
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF("Enviando dato: " + i++);
                DataInputStream dis = new DataInputStream(is);
                System.out.println(dis.readUTF());
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        Cliente c = new Cliente();
        c.crearConexion();
    }
}
