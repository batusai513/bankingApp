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
    
    public void createSocketThread(){
        ServerSocket server = null;
        try{
            server = new ServerSocket(PUERTO);
            while(true){
                Socket cliente = server.accept();
                new Thread(new HiloServidor(cliente)).start();
            }
        } catch (IOException exp) {
            exp.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
