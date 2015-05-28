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
import java.util.Hashtable;
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
    private Boolean running = true;
    private model.Cliente clienteActual;
    
    public void crearConexion(){
        try {
            do{
                this.conectarAlServidor();
                this.obtenerFlujos();
                this.correrAplicacion();
            }while(running);

        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.cerrarConexiones();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    public void cerrarConexiones() throws IOException{
        entrada.close();
        salida.close();
        socket.close();
    }
    
    public static void main(String[] args) {
        Cliente c = new Cliente();
        c.crearConexion();
    }

    private void correrAplicacion() throws IOException {
        System.out.println("\n\nBanco");
        System.out.println("Escriba su correo");
        String email = sc.nextLine();
        System.out.println("Escriba su clave");
        String clave = sc.nextLine();
        
        this.enviarMensaje("session/create:email=" + email + "&password=" + clave);
        
        String respuesta = entrada.readUTF();
        String[] arrayRespuesta = respuesta.split(":");
        String estado = arrayRespuesta[0];
        
        if (estado.equals("SUCCESS")) {
            String datos = arrayRespuesta[1];
            this.renderWelcome(datos);
            this.renderMemu();
        } else {
            System.out.println("Error de autenticacion");
        }
    }
    
    private void enviarMensaje(String mensaje) throws IOException{
        salida.writeUTF(mensaje);
        salida.flush();
    }
    
    public void renderWelcome(String datos){
        this.clienteActual = new model.Cliente();
        this.clienteActual.toObject(datos);

        System.out.println("Bienvenido, " + this.clienteActual.getNombre());
    }
    
    public void mostrarSaldo(String respuesta) {
        String[] arrayRespuesta = respuesta.split(":");
        String estado = arrayRespuesta[0];

        if (estado.equals("SUCCESS")) {
            String datos = arrayRespuesta[1];
            
            Hashtable<String, String> parsedData = utilities.Parser.getParams(datos);
            System.out.println("Su saldo es " + parsedData.get("saldo"));
        } else {
            System.out.println("\n\nHa ocurrido un error.\n\n");
        }
    }
    
    public void renderMemu() throws IOException{

        boolean exit=false;
        do{
            System.out.println("1. Ver saldo");
            System.out.println("2. Pagar servicios");
            System.out.println("3. Salir");

            int opcion = sc.nextInt();

            switch(opcion) {
                case 1:
                    this.enviarMensaje("balance/show:cuenta=" + clienteActual.getCuentaId());
                    mostrarSaldo(entrada.readUTF());
                    break;
                case 2:
                    System.out.println("No tiene servicios a pagar");
                    break;
                case 3:
                    System.out.println("Good Bye!");
                    this.enviarMensaje("EXIT");
                    exit = true;
                    running = false;
                    break;
                default:
                    System.out.println("Opcion Invalida");
            }
        }while(!exit);
    }
}
