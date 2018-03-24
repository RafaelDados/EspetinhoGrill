package componente;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class CampoSelecao extends JComboBox implements MeuComponente {

    private String nome;
    private boolean obrigatorio;

    public CampoSelecao(String nome, boolean obrigatorio, String[] dados) {
        this.nome = nome;
        this.obrigatorio = obrigatorio;
        preencher(dados);
    }

    public void preencher(String[] dados) {
        removeAllItems();
        addItem("<< Seelecione >>");
        for (String item : dados) {
            addItem(item);
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
        setEnabled(status);
    }

    @Override
    public boolean eVazio() {
        return (getSelectedIndex() <= 0);
    }

    @Override
    public boolean eValido() {
        return true;
    }

    @Override
    public void limpar() {
        setSelectedIndex(0);
    }

    @Override
    public Object getValor() {
        if (getSelectedIndex() > 0) {
            return (int) getSelectedIndex();
        } else {
            return -1;
        }
    }

    @Override
    public void setValor(Object objeto) {
        if (getSelectedIndex() >= 0) {
            setSelectedIndex((int) objeto);
            return;
        }
        JOptionPane.showMessageDialog(null, getNome() + " não encontrado.");
    }
}
