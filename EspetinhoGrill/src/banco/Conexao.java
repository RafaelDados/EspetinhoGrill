package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Conexao {

    private static Connection conexao;

    public static Connection getConexao() {
        if (conexao != null) {
            return conexao;
        } else {
            try {
                if (conexao == null) {
                    //System.out.println(System.getProperty("user.dir"));
                    Class.forName("org.firebirdsql.jdbc.FBDriver");
                    conexao = DriverManager.getConnection("jdbc:firebirdsql:"
                            + "//localhost:3050/"
                            + System.getProperty("user.dir")
                            + "/SABORDABRASA.FDB?defaultResultSetHoldable=True", "SYSDBA", "masterkey");
                }
                return conexao;
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro no drive JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao carregar o banco de dados");
            }
        }
        return conexao;
    }

    public static int getGenerator(String nomeGenerator) {
        String sql = "SELECT GEN_ID(" + nomeGenerator + ", 1) FROM RDB$DATABASE";
        try {
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível obter o generator " + nomeGenerator);
            return -1;
        }
    }

    public static List<String[]> executaPesquisa(String sql) {
        try {
            List<String[]> dados = new ArrayList();
            Statement st = Conexao.getConexao().createStatement();
            ResultSet rs = st.executeQuery(sql);
            int numeroColunas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                String[] linha = new String[numeroColunas];
                for (int i = 1; i <= numeroColunas; i++) {
                    linha[i - 1] = rs.getString(i);
                }
                dados.add(linha);
            }
            return dados;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a pesquisa no banco de dados");
            return new ArrayList();
        }
    }

    public static List<Object[]> consultarComboBox(String sql) {
        try {
            List<Object[]> dados = new ArrayList();
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] linha = new Object[2];
                linha[0] = rs.getInt(1);
                linha[1] = rs.getString(2);
                dados.add(linha);
            }
            return dados;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a consulta dos dados para o ComboBox.");
            return null;
        }
    }
}
