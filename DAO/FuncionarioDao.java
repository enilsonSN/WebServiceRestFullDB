/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author enilson
 */
public class FuncionarioDao {
      //conexão com o banco de dados
    private Connection connection;
    public FuncionarioDao(){
        this.connection = new ConnectionFactory().getConnection();
    }
public void adiciona(Funcionario funcionario){
    String sql = "INSERT INTO funcionario VALUES(?,?,?,?,?,?)";
    try {
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        //seta os valores
        stmt.setLong(1,funcionario.getNumat());
        stmt.setString(2,funcionario.getNome());
        stmt.setDouble(3,funcionario.getSalario());
        stmt.setString(4,funcionario.getSexo());
        stmt.setLong(5,funcionario.getNdepto());
        stmt.setLong(6,funcionario.getNsuper());
        
        stmt.executeUpdate();
        stmt.close();
    }
    catch (SQLException e){
        throw new RuntimeException(e);
    }
        
        
    }

public ArrayList<Funcionario> getLista(){
        String sql = "SELECT * from funcionario";
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNumat(rs.getInt("numat"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setNdepto(rs.getInt("ndepto"));
                funcionario.setNsuper(rs.getInt("nsuper"));
                
                
                funcionarios.add(funcionario);
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return funcionarios;
    }
public ArrayList<Funcionario> getListaSupervisores(){
        String sql = "SELECT * from funcionario "
                    + "WHERE ((numat=3334)OR (numat = 8886) OR (numat=9876)) ";
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNumat(rs.getInt("numat"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setNdepto(rs.getInt("ndepto"));
                funcionario.setNsuper(rs.getInt("nsuper"));
                
                
                funcionarios.add(funcionario);
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return funcionarios;
    }
    public Funcionario consulta(int numat) throws SQLException{
        String sql = "SELECT * FROM funcionario WHERE numat = ?";
        Funcionario funcionario = null;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1,numat);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                funcionario = new Funcionario();
                funcionario.setNumat(rs.getInt("numat"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setSexo(rs.getString("sexo"));
                funcionario.setNdepto(rs.getInt("ndepto"));
                funcionario.setNsuper(rs.getInt("nsuper"));
            }
            rs.close();
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        } 
        return funcionario;
    }
    public boolean remove(int id){
        String sql = "DELETE FROM funcionario WHERE numat = ?";
        int funcionariosRemovidos = 0;
        try{
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            //executeUpdate retorna a quantidade de linhas removidas
            funcionariosRemovidos = stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException  e){
            throw new RuntimeException(e);
        }
        return funcionariosRemovidos == 1;
    }
    public boolean atualiza(Funcionario funcionario){
        String sql = "UPDATE funcionario SET  nome = ?, salario = ?, sexo = ?, ndepto = ?, nsuper = ? WHERE numat = ?";
        int funcionariosAtualizados = 0;
        try{
            //prepared statement para inserção
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            //seta os valores
            
            stmt.setString(1,funcionario.getNome());
            stmt.setDouble(2,funcionario.getSalario());
            stmt.setString(3,funcionario.getSexo());
            stmt.setLong(4,funcionario.getNdepto());
            stmt.setLong(5,funcionario.getNsuper());
            stmt.setLong(6,funcionario.getNumat());
            //executeUpdate retorna a quantidade de linhas removidas
            funcionariosAtualizados = stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException  e){
            throw new RuntimeException(e);
        }
        return funcionariosAtualizados == 1;
    }
       
    
}
    

