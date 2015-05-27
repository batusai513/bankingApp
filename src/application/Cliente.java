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
import java.util.Scanner;
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
    private Scanner sc = new Scanner(System.in);
    
    public void crearConexion(){
        try {
            this.conectarAlServidor();
            int i = 0;
            while(true){
                this.obtenerFlujos();
                this.correrAplicacion();
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

    private void correrAplicacion() throws IOException {
        System.out.println("\n\nBienvenido");
        System.out.println("Escriba su correo");
        String email = sc.nextLine();
        System.out.println("Escriba su clave");
        String clave = sc.nextLine();
        
        salida.writeUTF("session/create:email=" + email + "&clave=" + clave);
        
        String respuesta = entrada.readUTF();
        
        if (respuesta.equals("SUCCESS")) {
            System.out.println("Seleccione una opcion");
            System.out.println("1. Ver saldo");
            System.out.println("2. Pagar servicios");
            System.out.println("3. Salir");
            
            String opcion = sc.nextLine();
            
            switch(opcion) {
                case "1":
                    System.out.println("No tiene saldo");
                    break;
                case "2":
                    System.out.println("No tiene servicios a pagar");
                    break;
                case "3":
                    System.out.println("Good Bye!");
                    break;
                default:
                    System.out.println("Opcion Invalida");
            }
        } else {
            System.out.println("Correo incorrecto o clave incorrecta");
        }
    }
}
