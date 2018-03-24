package dao;

import banco.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pojo.Cidade;

public class DaoCidade {

    private Cidade cidade;
    private final String SQL_INCLUIR
            = "INSERT INTO CIDADE VALUES (?, ?, ?, ?)";
    private final String SQL_CONSULTAR
            = "SELECT * FROM CIDADE WHERE ID = ?";
    private final String SQL_ALTERAR
            = "UPDATE CIDADE SET NOME = ?, STATUS = ?, IDESTADO = ? WHERE ID = ?";
    private final String SQL_EXCLUIR
            = "DELETE FROM CIDADE WHERE ID = ?";
    public static final String SQL_PESQUISAR
            = "SELECT CIDADE.ID, CIDADE.NOME, CIDADE.STATUS, ESTADO.SIGLA FROM CIDADE INNER JOIN ESTADO ON CIDADE.IDESTADO = ESTADO.ID ORDER BY CIDADE.ID";

    public DaoCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public boolean incluir() {
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_INCLUIR);
            cidade.setId(Conexao.getGenerator("GEN_CIDADE_ID"));
            ps.setInt(1, cidade.getId());
            ps.setString(2, cidade.getNome());
            ps.setString(3, cidade.isStatus() ? "A" : "I");
            ps.setInt(4, cidade.getEstado().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 335544665) {
                JOptionPane.showMessageDialog(null, "Erro! Já existe esta cidade cadastrada neste estado.");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a inclusão da cidade.");
            }
            e.printStackTrace();
            return true;
        }
    }

    public boolean consultar() {
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_CONSULTAR);
            ps.setInt(1, cidade.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cidade.setNome(rs.getString(2));
                cidade.setStatus(rs.getString(3).equals("A"));
                cidade.getEstado().setId(rs.getInt(4));
                DaoEstado daoEstado = new DaoEstado(cidade.getEstado());
                daoEstado.consultar();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a consulta do cidade.");
            return false;
        }
    }

    public boolean alterar() {
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_ALTERAR);
            ps.setString(1, cidade.getNome());
            ps.setString(2, cidade.isStatus() ? "A" : "I");
            ps.setInt(3, cidade.getEstado().getId());
            DaoEstado daoEstado = new DaoEstado(cidade.getEstado());
            daoEstado.consultar();
            ps.setInt(4, cidade.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 335544665) {
                JOptionPane.showMessageDialog(null, "Erro! Já existe uma cidade cadastrada com este nome.");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a alteração da cidade.");
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir() {
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(SQL_EXCLUIR);
            ps.setInt(1, cidade.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 335544466) {
                JOptionPane.showMessageDialog(null, "Erro! Esta cidade esta relacionada a outras telas.");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a exclusão do estado");
            }
            return false;
        }
    }
}
