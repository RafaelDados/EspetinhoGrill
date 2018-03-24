package componente;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

public class CampoInteiro extends JTextField implements MeuComponente {

    private String nome;
    private boolean obrigatorio;
    private boolean podeHabilitar;

    public CampoInteiro(String nome, boolean obrigatorio, boolean podeHabilitar, int tamanho, int qtdeCaracter) {
        this.nome = nome;
        this.obrigatorio = obrigatorio;
        this.podeHabilitar = podeHabilitar;
        setColumns(tamanho);
        setHorizontalAlignment(JTextField.RIGHT);
        setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String string, AttributeSet a) {
                try {
                    if (getLength() + string.length() > qtdeCaracter) {
                        return;
                    }
                    Pattern padrao = Pattern.compile("[0123456789,' ']");
                    Matcher matcher = padrao.matcher(string);
                    if (!matcher.find()) {
                        return;
                    }
                    super.insertString(offs, string, a);
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
        return getText().trim().equals("");
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
        if (getText().trim().equals("")) {
            return 0;
        } else {
           return Integer.parseInt(getText());
        }
    }

    @Override
    public void setValor(Object objeto) {
        setText("" + (int) objeto);
    }
}