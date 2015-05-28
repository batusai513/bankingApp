/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Richard
 */
public class Cuenta implements Serializable{
    private int numeroDeCuenta;
    private Double saldo;
    private Date fechaCreacion;
    private Cliente cliente;

    public int getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(int numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String toString() {
        String cadena = "";
        
        cadena += "numeroDeCuenta=" + this.numeroDeCuenta;
        cadena += "&saldo=" + this.saldo;
        return cadena;
    }
    
}
