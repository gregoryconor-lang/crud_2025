package conexao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gregory
 */
public class Conexao {
    //private static final String URL = "jdbc:SGBD://SERVIDOR:PORTA/NOME_DA_BASE";
    private static final String URL = "jdbc:mysql://localhost:3306/bd_estoque";
    private static final String USUARIO = "gregory";
    private static final String SENHA = "qwe123";
    
    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("erro na conexão com o BD");
        } 
    }
    
    public static void fecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharConexao(Connection con, PreparedStatement stmt) {
        fecharConexao(con);
        
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
        fecharConexao(con,stmt);
        
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
