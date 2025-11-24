/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author gregory
 */
public class ProdutoDAO {
    public void create(Produto p) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "INSERT INTO produto(descricao, valor_unitario, quantidade) VALUES (?,?,?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getValorUnitario());
            stmt.setInt(3, p.getQuantidade());
            // Definição do valor do id de Produto (fk)
            
            // Executar a query
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");            
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar Produto. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<Produto> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> listaProdutos = new ArrayList();
        
        try {
            String query = "SELECT * FROM produto";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setDescricao(rs.getString("descricao"));
                p.setValorUnitario(rs.getDouble("valor_unitario"));
                p.setQuantidade(rs.getInt("quantidade"));
                
                listaProdutos.add(p);
            }
            
            return listaProdutos;
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar Produtos. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return null;
    }
    
    public Produto read(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM produto WHERE id_produto = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setDescricao(rs.getString("descricao"));
                p.setValorUnitario(rs.getDouble("valor_unitario"));
                p.setQuantidade(rs.getInt("quantidade"));
                
                return p;
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar Produto. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return null;
    }
    
    public void update(Produto p) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "UPDATE produto "
                    + "SET descricao = ?, valor_unitario = ?, quantidade = ?) "
                    + "WHERE id_produto = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getValorUnitario());
            stmt.setInt(3, p.getQuantidade());
            // Definição do valor do id de Produto (fk)
            
            stmt.setInt(5, p.getId());
            
            // Executar a query
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");            
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao editar Produto. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void destroy(Produto p) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "DELETE FROM produto WHERE id_produto = ?";
            stmt = con.prepareStatement(query);            
            stmt.setInt(1, p.getId());
            
            // Executar a query
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");            
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir Produto. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
