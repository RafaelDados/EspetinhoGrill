package componente;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

public class CampoTexto extends JTextField implements MeuComponente {

    public String nome;
    public boolean obrigatorio;
    public boolean podeHabilitar;

    public CampoTexto(String nome, boolean obrigatorio, boolean podeHabilitar, int tamanho, int qtdeCaracter) {
        this.nome = nome;
        this.obrigatorio = obrigatorio;
        this.podeHabilitar = podeHabilitar;
        setColumns(tamanho);
        setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String string, AttributeSet a) {
                try {
                    if (getLength() + string.length() > qtdeCaracter) {
                        return;
                    }
                    Pattern padrao = Pattern.compile("[aA-zZ﻿áâéíõúçãàêóôÁÂÉÍÕÚÇÃÀÊÓÔ,' ']");
                    Matcher matcher = padrao.matcher(string);
                    if (!matcher.find()) {
                        return;
                    }
                    super.insertString(offs, string.toUpperCase(), a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
        setEnabled(status && podeHabilitar);
    }

    @Override
    public boolean eVazio() {
        return getText().trim().isEmpty();
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
        return getText();
    }

    @Override
    public void setValor(Object objeto) {
        setText((String) objeto);
    }
}
