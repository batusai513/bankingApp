/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import Database.Database;

/**
 *
 * @author Richard
 */
public class ClienteService {
    
    private final Connection connection;
    
    public ClienteService(){
        connection = Database.getConnection();
    }
    
    public Cliente obtenerCliente(String email) {
        Cliente cliente = new Cliente();
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from cliente where email=?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
                cliente.setClave(rs.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return cliente;
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
