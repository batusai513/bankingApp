/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import DAO.ClienteDAO;
import java.util.Hashtable;
import model.Cliente;

/**
 *
 * @author andresroberto
 */
public class Controlador {
    ClienteDAO dao;
    
    public Controlador(){
        dao = new ClienteDAO();
    }
    
    private Hashtable<String, String> getParams(String cadena) {
        Hashtable<String, String> params = new Hashtable<>();
        
        String[] paramsArray = cadena.split("&");
        
        for (int i = 0; i < paramsArray.length; i++) {
            String[] attrArray = paramsArray[i].split("=");
            params.put(attrArray[0], attrArray[1]);
        }
        
        return params;
    }

    public String crearSesion(String datos) {
        Hashtable<String, String> params = getParams(datos);
        
        String email = params.get("email");
        String password = params.get("password");
        
        Cliente cliente = dao.buscarPorEmail(email);
        
        if (cliente != null && cliente.getClave().equals(password)) {
            return "SUCCESS:" + cliente.toString();
        } else {
            return "ERROR";
        }
    }
    
}
