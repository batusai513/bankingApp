/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapp;

import model.Cliente;
import model.Cuenta;
import services.ClienteService;
import services.CuentaService;

/**
 *
 * @author Richard
 */
public class BankingApp {

    Cliente clienteActual;
    Cuenta cuentaActual;
    ClienteService servicioCliente;
    CuentaService servicioCuenta;
    
    public BankingApp(){
        this.servicioCliente = new ClienteService();
    }
    
    private String getParam(String str) {
        return str.substring(str.indexOf('=') + 1);
    }

    public String crearSesion(String datos) {
        String[] params = datos.split("&");
        
        String email = getParam(params[0]);
        String clave = getParam(params[1]);
        
        Cliente cliente = servicioCliente.obtenerCliente(email);
        
        if(cliente != null) {
            if (clave.equals(cliente.getClave())) {
                return "SUCCESS";
            }
        }
        
        return null;
    }
}
