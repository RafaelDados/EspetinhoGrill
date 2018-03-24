package tela;

import static utilitarios.Util.centralizaTela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;

public class TelaSistema extends JFrame implements ActionListener {

    public static JDesktopPane jdp = new JDesktopPane();
    public JMenuBar jmb = new JMenuBar();

    public JMenu jmCadastros = new JMenu("Cadastros");
    public JMenu jmMovimentos = new JMenu("Movimentações");
    public JMenu jmRelatorios = new JMenu("Relatórios");
    public JMenu jmOpcoes = new JMenu("Opções");

    public JMenuItem jmiEstado = new JMenuItem("Estado");
    public JMenuItem jmiCidade = new JMenuItem("Cidade");
    public JMenuItem jmiPessoa = new JMenuItem("Pessoa");
    public JMenuItem jmiCargo = new JMenuItem("Cargo");
    public JMenuItem jmiSair = new JMenuItem("Sair");

    public TelaSistema() {
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Sabor da Brasa Espetinho Grill");
        getContentPane().add(jdp);
        adicionaMenu();
        adicionaItemMenu();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void adicionaMenu() {
        setJMenuBar(jmb);
        jmb.add(jmCadastros);
        jmb.add(jmMovimentos);
        jmb.add(jmRelatorios);
        jmb.add(jmOpcoes);
    }

    public void addJMenuItem(JMenu menu, JMenuItem item) {
        menu.add(item);
        item.addActionListener(this);
    }

    public void adicionaItemMenu() {
        addJMenuItem(jmCadastros, jmiEstado);
        addJMenuItem(jmCadastros, jmiCidade);
        addJMenuItem(jmCadastros, jmiPessoa);
        addJMenuItem(jmCadastros, jmiCargo);
        addJMenuItem(jmOpcoes, jmiSair);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jmiEstado) {
            TelaCadastroEstado telaEstado = new TelaCadastroEstado("Cadastro de Estado");
            jdp.add(telaEstado);
            centralizaTela(telaEstado);
        }
        if(e.getSource() == jmiCidade){
            TelaCadastroCidade telaCadastroCidade = new TelaCadastroCidade("Cadastro de Cidade");
            jdp.add(telaCadastroCidade);
            centralizaTela(telaCadastroCidade);
        }
        if (e.getSource() == jmiPessoa) {
            TelaCadastroPessoa telaPessoa = new TelaCadastroPessoa("Cadastro de Pessoa");
            jdp.add(telaPessoa);
            centralizaTela(telaPessoa);
        }
        if(e.getSource() == jmiCargo){
            TelaCadastroCargo telaCadastroCargo = new TelaCadastroCargo("Cadastro de Cargo");
            jdp.add(telaCadastroCargo);
            centralizaTela(telaCadastroCargo);
        }
        if (e.getSource() == jmiSair) {
            System.exit(0);
        }
    }

}
