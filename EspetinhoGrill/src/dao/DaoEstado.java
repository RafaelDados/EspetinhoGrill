package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import pojo.Estado;

public class DaoEstado {

    private Estado estado;
    private final String SQL_INCLUIR
            = "INSERT INTO ESTADO VALUES (?, ?, ?, ?)";
    private final String SQL_CONSULTAR
            = "SELECT * FROM ESTADO WHERE ID = ?";
    private final String SQL_ALTERAR
            = "UPDATE ESTADO SET NOME = ?, SIGLA = ?, STATUS = ? WHERE ID = ?";
    private final String SQL_EXCLUIR
            = "DELETE FROM ESTADO WHERE ID = ?";
    public static final String SQL_PESQUISAR
            = "SELECT ID, NOME, SIGLA, STATUS FROM ESTADO ORDER BY NOME";
    public static final String SQL_COMBOBOX
            = "SELECT ID, NOME || ' - ' || SIGLA FROM ESTADO ORDER BY NOME";

    public DaoEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean incluir() {
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
            estado.setId(Conexao.getGenerator("GEN_ESTADO_ID"));
            ps.setInt(1, estado.getId());
            ps.setString(2, estado.getNome());
            ps.setString(3, estado.getSigla());
            ps.setString(4, estado.isStatus() ? "A" : "I");
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 335544665) {
                JOptionPane.showMessageDialog(null, "Erro! Já existe um estado cadastrado com este nome.");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a inclusão do estado.");
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean consultar() {
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
            ps.setInt(1, estado.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                estado.setNome(rs.getString(2));
                estado.setSigla(rs.getString(3));
                estado.setStatus(rs.getString(4).equals("A"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a consulta do estado.");
            return false;
        }
    }

    public boolean alterar() {
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
            ps.setString(1, estado.getNome());
            ps.setString(2, estado.getSigla());
            ps.setString(3, estado.isStatus() ? "A" : "I");
            ps.setInt(4, estado.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 335544665) {
                JOptionPane.showMessageDialog(null, "Erro! Já existe um estado cadastrado com este nome.");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a alteração do estado.");
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir() {
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
            ps.setInt(1, estado.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 335544466) {
                JOptionPane.showMessageDialog(null, "Erro! Este estado esta relacionado a outras telas.");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a exclusão do estado");
            }
            e.printStackTrace();
            return false;
        }
    }
}
