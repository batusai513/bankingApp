/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Richard
 */
public class Cliente implements Serializable{
    private int clienteId;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaCreacion;
    private ArrayList<Cuenta> cuenta;
    private String clave;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public ArrayList<Cuenta> getCuenta() {
        return cuenta;
    }

    public void setCuenta(ArrayList<Cuenta> cuenta) {
        this.cuenta = cuenta;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    private String getParam(String str) {
        return str.substring(str.indexOf('=') + 1);
    }
    
    public void toObject(String post) {
        String[] params = post.split("&");
        
        this.clienteId = Integer.parseInt(getParam(params[0]));
        this.nombre = getParam(params[1]);
        this.apellido = getParam(params[2]);
        this.email = getParam(params[3]);
    }
    
    public String toString() {
        String cadena = "";
        
        cadena += "id=" + this.clienteId;
        cadena += "&nombre=" + this.nombre;
        cadena += "&apellido=" + this.apellido;
        cadena += "&email=" + this.email;

        return cadena;
    }
}
