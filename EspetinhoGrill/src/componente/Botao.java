package componente;

import javax.swing.JButton;

public class Botao extends JButton implements MeuComponente{

    public Botao(String nome, String caminhoIcone) {
        setText(nome);
        if(!caminhoIcone.isEmpty()){
            this.setIcon(new javax.swing.ImageIcon(getClass().getResource(caminhoIcone)));
        }
    }

    @Override
    public String getNome() {
        return "";
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
        
    }

    @Override
    public Object getValor() {
        return new Object();
    }

    @Override
    public void setValor(Object objeto) {
        
    }    
}