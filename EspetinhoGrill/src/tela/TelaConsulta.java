package tela;

import static utilitarios.Util.centralizaTela;
import banco.Conexao;
import componente.Botao;
import componente.CampoTextoInteiro;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TelaConsulta extends JInternalFrame {

    private TelaCadastro telaChamadora;
    private TelaConsulta telaConsulta = this;
    private Botao sair = new Botao("Sair", "");
    private JPanel jpComponentes = new JPanel();
    private JPanel jpBotoes = new JPanel();
    private JLabel lPesquisa = new JLabel("Pesquisa:");
    private CampoTextoInteiro pesquisa = new CampoTextoInteiro("Pesquisa", false, 20, 99);
    private JLabel lFiltro = new JLabel("Filtro:");
    private JComboBox jcb = new JComboBox();
    private DefaultTableModel dtm = new DefaultTableModel();
    private JTable tabela = new JTable(dtm) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }

        @Override
        public Component prepareRenderer(TableCellRenderer render, int linha, int coluna) {
            Component c = super.prepareRenderer(render, linha, coluna);
            if (linha % 2 == 0) {
                c.setBackground(new Color(255, 160, 122));
            } else {
                c.setBackground(new Color(205, 201, 201));
            }
            if (isCellSelected(linha, coluna)) {
                c.setBackground(new Color(162, 181, 205));
            }
            return c;
        }
    };
    private JScrollPane jsp = new JScrollPane(tabela);

    public TelaConsulta(TelaCadastro telaChamadora, String titulo, String[] colunas, String[] dadosCombo, String sql) {
        super(titulo);
        this.telaChamadora = telaChamadora;
        adicionaComponentes();
        preencheCombo(dadosCombo);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);
        insereColunas(colunas);
        insereDados(sql);
        if (dtm.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Não existem dados cadastrados.");
            telaChamadora.pesquisaSemDados();
            return;
        }
        getContentPane().add(jsp);
        pack();
        setVisible(true);
        TelaSistema.jdp.add(this);
        TelaSistema.jdp.moveToFront(this);
        centralizaTela(this);
        adicionaOuvintes();
    }

    public void adicionaComponentes() {
        getContentPane().add("North", jpComponentes);
        getContentPane().add("South", jpBotoes);
        jpComponentes.setLayout(new FlowLayout());
        jpBotoes.setLayout(new GridLayout(1, 1));
        jpComponentes.add(lPesquisa);
        jpComponentes.add(pesquisa);
        jpComponentes.add(lFiltro);
        jpComponentes.add(jcb);
        jpBotoes.add(sair);
    }

    public void insereColunas(String[] colunas) {
        for (String item : colunas) {
            dtm.addColumn(item);
        }
    }

    public void insereDados(String sql) {
        limpaTabela();
        List<String[]> dados = Conexao.executaPesquisa(sql);
        for (int i = 0; i < dados.size(); i++) {
            dtm.addRow(dados.get(i));
        }
    }
    
    public void limpaTabela() {
        for (int i = dtm.getRowCount(); i != 0; i--) {
            dtm.removeRow(i - 1);
        }
    }

    public void preencheCombo(String[] dados) {
        for (String filtro : dados) {
            jcb.addItem(filtro);
        }
    }

    public void capturaTexto(String texto, JComboBox jcb) {
        String sql = telaChamadora.pesquisarBD(texto, jcb);
        insereDados(sql);
    }

    public void adicionaOuvintes() {
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    telaChamadora.preencherDados(Integer.parseInt((String) dtm.getValueAt(tabela.getSelectedRow(), 0)));
                    dispose();
                    TelaSistema.jdp.remove(telaConsulta);
                }
            }
        });

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == sair) {
                    dispose();
                }
            }
        });

        pesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                capturaTexto(pesquisa.getText(), jcb);
            }
        });
    }
}
