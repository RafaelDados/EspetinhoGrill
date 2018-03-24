package utilitarios;

import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import tela.TelaSistema;

public class Util {

    public static void setLookFeel() {
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Não foi possível carregar a skin");
        }
    }

    public static void centralizaTela(JInternalFrame tela) {
        Dimension tamanhoTela = tela.getSize();
        Dimension tamanhoJDesktopPane = TelaSistema.jdp.getSize();
        int x = (tamanhoJDesktopPane.width - tamanhoTela.width) / 2;
        int y = (tamanhoJDesktopPane.height - tamanhoTela.height) / 2;
        tela.setLocation(x, y);
    }

    public static String filtro(String colunas, String tabela, String condicao, String[] consulta, String texto, JComboBox jcb) {
        if (condicao.isEmpty()) {
            condicao = "WHERE";
        } else {
            condicao += " WHERE";
        }
        String sql = "SELECT " + colunas + " FROM " + tabela + " " + condicao + " ";
        for (int i = 0; i <= jcb.getItemCount(); i++) {
            if (i == jcb.getSelectedIndex()) {
                System.out.println(i);
                sql += tabela + "." + consulta[i] + " LIKE '" + texto + "%' ORDER BY " + tabela + "." + consulta[i] + "";
            }
        }
        if (texto.isEmpty()) {
            sql = "SELECT " + colunas + " FROM " + tabela;
        }
        return sql;
        /*if (!texto.isEmpty()) {
            switch (valor) {
                case 0: {
                    sql += consulta[0] + " LIKE '" + texto + "%' ORDER BY " + tabela + "." + consulta[0] + "";
                    break;
                }
                case 1: {
                    sql += consulta[1] + " LIKE '" + texto + "%' ORDER BY " + tabela + "." + consulta[1] + "";
                    break;
                }
                case 2: {
                    sql += consulta[2] + " LIKE '" + texto + "%' ORDER BY " + tabela + "." + consulta[2] + "";
                    break;
                }
                case 3: {
                    sql += consulta[3] + " LIKE '" + texto + "%' ORDER BY " + tabela + "." + consulta[3] + "";
                    break;
                }
                case 4: {
                    sql += consulta[4] + " LIKE '" + texto + "%' ORDER BY " + tabela + "." + consulta[4] + "";
                    break;
                }
                default:
                    sql += "SELECT " + colunas + " FROM " + tabela + " ORDER BY " + tabela + ".ID";
                    break;
            }
            return sql;
        } else {
            sql = "SELECT " + colunas + " FROM " + tabela;
            return sql;
        }
         */
    }
}
