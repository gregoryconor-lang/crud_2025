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
import model.bean.TipoProduto;

/**
 *
 * @author gregory
 */
public class TipoProdutoDAO {
    public void create(TipoProduto tp) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "INSERT INTO tipo_produto(descricao) VALUES(?)";
            stmt = con.prepareStatement(query);
            stmt.setString(1, tp.getDescricao());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Tipo de Produto cadastrado com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar Tipo de Produto. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public ArrayList<TipoProduto> read() {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<TipoProduto> listaProdutos = new ArrayList();
        
        try {
            String query = "SELECT * FROM tipo_produto";
            stmt = con.prepareStatement(query);
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                TipoProduto tp = new TipoProduto();
                tp.setId(rs.getInt("id_tipo_produto"));
                tp.setDescricao(rs.getString("descricao"));
                
                listaProdutos.add(tp);
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao consultar tipos de produto. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return listaProdutos;
    }
    
    // Traz um objeto específico a partir do id
    public TipoProduto read(int id) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM tipo_produto WHERE id_tipo_produto = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();
            
            if(rs.next()) {
                TipoProduto tp = new TipoProduto();
                tp.setId(rs.getInt("id_tipo_produto"));
                tp.setDescricao(rs.getString("descricao"));
                
                return tp;
            }
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao buscar tipo de produto. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt, rs);
        }
        
        return null;
    }
    
    public void update(TipoProduto tp) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "UPDATE tipo_produto SET descricao = ? WHERE id_tipo_produto = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, tp.getDescricao());
            stmt.setInt(2, tp.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Tipo de Produto atualizado com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar Tipo de Produto. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
    
    public void destroy(TipoProduto tp) {
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        
        try {
            String query = "DELETE FROM tipo_produto WHERE id_tipo_produto = ?";
            stmt = con.prepareStatement(query);            
            stmt.setInt(1, tp.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Tipo de Produto excluído com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir Tipo de Produto. Erro: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao(con, stmt);
        }
    }
}
