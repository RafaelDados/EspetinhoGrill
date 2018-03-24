package componente;

import javax.swing.JCheckBox;

public class CaixaSelecao extends JCheckBox implements MeuComponente{
    
    private String nome;
    private boolean obrigatorio;
    
    public CaixaSelecao(String nome, boolean obrigatorio){
        this.nome = nome;
        this.obrigatorio = obrigatorio;
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
        return false;
    }

    @Override
    public boolean eValido() {
        return true;
    }

    @Override
    public void limpar() {
        setSelected(false);
    }

    @Override
    public Object getValor() {
        return isSelected();
    }

    @Override
    public void setValor(Object objeto) {
        setSelected((boolean) objeto);
    } 
}
