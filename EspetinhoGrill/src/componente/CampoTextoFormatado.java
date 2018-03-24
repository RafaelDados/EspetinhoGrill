package componente;

import javax.swing.JFormattedTextField;

public class CampoTextoFormatado extends JFormattedTextField implements MeuComponente{

    private String nome;
    private boolean obrigatorio;

    public CampoTextoFormatado(String nome, boolean obrigatorio) {
        this.nome = nome;
        this.obrigatorio = obrigatorio;
    }    
    
    
    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public boolean eObrigatorio() {
        return false;
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
        setText("");
    }

    @Override
    public Object getValor() {
        return (String)getText();
    }

    @Override
    public void setValor(Object objeto) {
        setText((String)objeto);
    }   
}