package componente;

import banco.Conexao;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CampoSelecaoBD extends JPanel implements MeuComponente {

    private String nome;
    private boolean obrigatorio;

    private JComboBox jcb = new JComboBox();
    private JButton botao = new JButton("...");
    private List<Object[]> dados;
    private String sql;

    public CampoSelecaoBD(String nome, boolean obrigatorio, String sql) {
        this.nome = nome;
        this.obrigatorio = obrigatorio;
        this.sql = sql;
        setLayout(new FlowLayout());
        add(jcb);
        add(botao);
        preencher();
        adicionaOuvintes();
    }

    public void preencher() {
        jcb.removeAllItems();
        jcb.addItem("Selecione...");
        dados = Conexao.consultarComboBox(sql);
        for (Object[] item : dados) {
            jcb.addItem(item[1]);
        }
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }

    @Override
    public void habilitar(boolean status) {
        jcb.setEnabled(status);
        botao.setEnabled(status);
    }

    @Override
    public boolean eVazio() {
        return (jcb.getSelectedIndex() <= 0);
    }

    @Override
    public boolean eValido() {
        return true;
    }

    @Override
    public void limpar() {
        jcb.setSelectedIndex(0);
    }

    @Override
    public Object getValor() {
        if (jcb.getSelectedIndex() > 0) {
            return (int) dados.get(jcb.getSelectedIndex() - 1)[0];
        } else {
            return -1;
        }
    }

    @Override
    public void setValor(Object objeto) {
        for (int i = 0; i < dados.size(); i++) {
            if (Integer.parseInt(objeto.toString()) == (int) dados.get(i)[0]) {
                jcb.setSelectedIndex(i + 1);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, getNome() + " não encontrado(a).");
    }

    public void adicionaOuvintes() {
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //adiconar metodo para abrir nova tela
            }
        });
        jcb.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e){
                preencher();
            }
        });
    }
}