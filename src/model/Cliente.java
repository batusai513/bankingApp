/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import utilities.Parser;

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
    private int cuentaId;

    public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

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
        Hashtable<String, String> params = Parser.getParams(post);
        
        this.clienteId = Integer.parseInt(params.get("id"));
        this.nombre = params.get("nombre");
        this.apellido = params.get("apellido");
        this.email = params.get("email");
        this.cuentaId = Integer.parseInt(params.get("cuenta"));
    }
    
    public String toString() {
        String cadena = "";
        
        cadena += "id=" + this.clienteId;
        cadena += "&nombre=" + this.nombre;
        cadena += "&apellido=" + this.apellido;
        cadena += "&email=" + this.email;
        cadena += "&cuenta=" + this.getCuentaId();

        return cadena;
    }
}
