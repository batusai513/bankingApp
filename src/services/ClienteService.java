/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import utilities.Db;

/**
 *
 * @author Richard
 */
public class ClienteService {
    
    private Connection connection;
    
    public ClienteService(){
        connection = Db.getConnection();
    }
    
    public void addCliente(Cliente cliente){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into cliente(nombre, apellido) values(?, ?,)");
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(1, cliente.getApellido());
        } catch (SQLException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
