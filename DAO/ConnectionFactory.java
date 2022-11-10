/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author enilson
 */
public class ConnectionFactory {
    
    public Connection getConnection(){
        try{
            Connection conexao= DriverManager.getConnection(
            "jdbc:derby://localhost:1527/empresa1",
            "adm",
            "adm");
            return conexao;
        }
        catch(SQLException ex){
            System.out.println("Falha de conexao ao banco de dados!");
        }
    return null;
    }
    
    
}
