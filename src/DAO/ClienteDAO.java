/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Cuenta;

/**
 *
 * @author andresroberto
 */
public class ClienteDAO {
    private static Connection connection;
    
    public ClienteDAO(){
        connection = Database.getConnection();
    }
    
    /**
     *
     * @param email
     * @return
     */
    public static Cliente buscarPorEmail(String email) {    
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from cliente inner join cuenta where cliente.clienteId = cuenta.clienteCuentaid and cliente.email = ?;");
            preparedStatement.setString(1, email);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                model.Cliente cliente = new model.Cliente();
                
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setClave(rs.getString("password"));
                cliente.setCuentaId(rs.getInt("numeroDeCuenta"));
                
                return cliente;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return null;
    }
    
    public static Cuenta buscarPorId(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from cuenta where numeroDeCuenta = ?");
            preparedStatement.setInt(1, id);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                Cuenta cuenta = new Cuenta();
                
                cuenta.setNumeroDeCuenta(rs.getInt("numeroDeCuenta"));
                cuenta.setSaldo(rs.getDouble("saldo"));
                
                return cuenta;
            }            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
