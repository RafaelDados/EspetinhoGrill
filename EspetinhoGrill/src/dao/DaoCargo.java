package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pojo.Cargo;

public class DaoCargo {

    private Cargo cargo = new Cargo();

    private final String SQL_INCLUIR
            = "INSERT INTO CARGO VALUES (?, ?)";
    private final String SQL_CONSULTAR
            = "SELECT * FROM CARGO WHERE ID = ?";
    private final String SQL_ALTERAR
            = "UPDATE CARGO SET NOME = ? WHERE ID = ?";
    private final String SQL_EXCLUIR
            = "DELETE FROM CARGO WHERE ID = ?";

    public DaoCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    public boolean incluir(){
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
            cargo.setId(Conexao.getGenerator("GEN_CARGO_ID"));
            ps.setInt(1, cargo.getId());
            ps.setString(2, cargo.getNome());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 335544665) {
                JOptionPane.showMessageDialog(null, "Erro! Já existe um cargo cadastrado com este nome.");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a inclusão do cargo.");
            }
            e.printStackTrace();
            return false;
        }
    }   
    
    public boolean consultar(){
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DaoCargo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}