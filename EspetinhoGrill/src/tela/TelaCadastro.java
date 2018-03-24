package tela;

import componente.Botao;
import componente.MeuComponente;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TelaCadastro extends JInternalFrame implements ActionListener {

    private final int PADRAO = 0;
    private final int INCLUINDO = 1;
    private final int ALTERANDO = 2;
    private final int EXCLUINDO = 3;
    private final int CONSULTANDO = 4;
    public int estadoTela = PADRAO;
    public boolean temDadosNaTela = false;
    public List<MeuComponente> componentes = new ArrayList();

    public JPanel jpBotoes = new JPanel();
    public JPanel jpComponentes = new JPanel();
    public Botao incluir = new Botao("Incluir", "/imagem/incluir.png");
    public Botao alterar = new Botao("Alterar", "");
    public Botao excluir = new Botao("Excluir", "");
    public Botao consultar = new Botao("Consultar", "");
    public Botao confirmar = new Botao("Confirmar", "");
    public Botao cancelar = new Botao("Cancelar", "");
    public Botao sair = new Botao("Sair", "");

    public TelaCadastro(String titulo) {
        super(titulo, false, false, false, true);
        getContentPane().add("Center", jpComponentes);
        getContentPane().add("South", jpBotoes);
        jpComponentes.setLayout(new GridBagLayout());
        jpBotoes.setLayout(new GridLayout(1, 7));
        addBotao();
        habilitaBotoes();
        setVisible(true);
        pack();
    }

    public void adicionaBotao(JButton botao) {
        jpBotoes.add(botao);
        botao.addActionListener(this);
    }

    public void addBotao() {
        adicionaBotao(incluir);
        adicionaBotao(consultar);
        adicionaBotao(alterar);
        adicionaBotao(excluir);
        adicionaBotao(confirmar);
        adicionaBotao(cancelar);
        adicionaBotao(sair);
    }

    public void habilitaBotoes() {
        incluir.setEnabled(estadoTela == PADRAO);
        alterar.setEnabled(estadoTela == PADRAO && temDadosNaTela);
        excluir.setEnabled(estadoTela == PADRAO && temDadosNaTela);
        consultar.setEnabled(estadoTela == PADRAO);
        confirmar.setEnabled(estadoTela != PADRAO && estadoTela != CONSULTANDO);
        cancelar.setEnabled(estadoTela != PADRAO && estadoTela != CONSULTANDO);
        sair.setEnabled(estadoTela == PADRAO);
    }

    public void adicionaComponente(int linha, int coluna, int linhas, int colunas, JComponent componente) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = coluna;
        gbc.gridy = linha;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(1, 7, 1, 7);
        if (!((MeuComponente) componente instanceof JButton)) {
            String nome = "<html><body>" + ((MeuComponente) componente).getNome();
            if ((MeuComponente) componente instanceof MeuComponente) {
                if (((MeuComponente) componente).eObrigatorio()) {
                    nome += "<font color = red>*</font>";
                }
                nome += "<strong>:</strong></body></html>";
                JLabel label = new JLabel(nome);
                jpComponentes.add(label, gbc);
                gbc.gridx++;
            }
            gbc.gridy++;
            gbc.gridx--;
            //gbc.anchor = GridBagConstraints.WEST;
            gbc.gridheight = linhas;
            gbc.gridwidth = colunas;
            componentes.add((MeuComponente) componente);
            jpComponentes.add(componente, gbc);
        }
    }

    public void habilitaComponente(boolean status) {
        for (int i = 0; i < componentes.size(); i++) {
            componentes.get(i).habilitar(status);
        }
    }

    public void limpaComponente() {
        for (int i = 0; i < componentes.size(); i++) {
            componentes.get(i).limpar();
        }
        temDadosNaTela = false;
    }

    public boolean validaComponente() {
        String errosObrigatorios = "";
        String errosInvalidos = "";
        String errosTotais = "";
        for (int i = 0; i < componentes.size(); i++) {
            if (componentes.get(i).eObrigatorio() && componentes.get(i).eVazio()) {
                errosObrigatorios += componentes.get(i).getNome() + "\n";
            }
            if (!componentes.get(i).eValido() && componentes.get(i).eObrigatorio()) {
                errosInvalidos += componentes.get(i).getNome() + "\n";
            }
        }
        if (!errosInvalidos.isEmpty()) {
            errosTotais = "Os campos a baixo são obrigatórios e não foram preenchidos \n\n" + errosObrigatorios;
            JOptionPane.showMessageDialog(null, errosTotais);
        }
        if (!errosInvalidos.isEmpty()) {
            errosTotais = "Os campos a baixo são invalídos\n\n" + errosInvalidos;
            JOptionPane.showMessageDialog(null, errosTotais);
        }
        return errosTotais.isEmpty();
    }

    public void incluir() {
        limpaComponente();
        estadoTela = INCLUINDO;
        habilitaComponente(true);
        habilitaBotoes();
    }

    public void consultar() {
        estadoTela = CONSULTANDO;
        temDadosNaTela = true;
    }

    public void alterar() {
        estadoTela = ALTERANDO;
        habilitaComponente(true);
        habilitaBotoes();
    }

    public void excluir() {
        estadoTela = EXCLUINDO;
        habilitaComponente(false);
        habilitaBotoes();
    }

    public void confirmar() {
        if (estadoTela == INCLUINDO) {
            if (!validaComponente() || !incluirBD()) { // rever 
                return;
            }
        } else if (estadoTela == ALTERANDO) {
            if (!validaComponente() || !alterarBD()) {
                return;
            }
        } else if (estadoTela == EXCLUINDO) {
            if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este registro?", "ALERTA", JOptionPane.YES_NO_OPTION) == 0) {
                if (!excluirBD()) {
                    return;
                } else {
                    limpaComponente();
                }
            } else {
                return;
            }
        }
        estadoTela = PADRAO;
        habilitaComponente(false);
        habilitaBotoes();
    }

    public void cancelar() {
        estadoTela = PADRAO;
        limpaComponente();
        habilitaComponente(false);
        habilitaBotoes();
    }

    public void sair() {
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == incluir) {
            incluir();
        } else if (e.getSource() == consultar) {
            consultar();
        } else if (e.getSource() == alterar) {
            alterar();
        } else if (e.getSource() == excluir) {
            excluir();
        } else if (e.getSource() == confirmar) {
            confirmar();
        } else if (e.getSource() == cancelar) {
            cancelar();
        } else if (e.getSource() == sair) {
            sair();
        }
    }

    public boolean incluirBD() {
        return true; //Método a ser redefinido nas sub-classes.
    }

    public boolean alterarBD() {
        return true; //Método a ser redefinido nas sub-classes.
    }

    public boolean excluirBD() {
        return true; //Método a ser redefinido nas sub-classes.
    }

    public String pesquisarBD(String texto, JComboBox jcb) {
        return ""; //Método a ser redefinido nas sub-classes.
    }

    public void preencherDados(int pk) {
        estadoTela = PADRAO;
        habilitaBotoes();
    }

    public void pesquisaSemDados() {
        estadoTela = PADRAO;
        temDadosNaTela = false;
        habilitaBotoes();
    }

    public void jcbDados(JComboBox jcb){
        
    }
}
