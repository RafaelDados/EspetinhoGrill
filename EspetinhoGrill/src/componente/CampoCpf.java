package componente;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class CampoCpf extends CampoTextoFormatado {

    public CampoCpf(String nome, boolean obrigatorio) {
        super(nome, obrigatorio);
        try {
            MaskFormatter mf = new MaskFormatter("###.###.###-##");
            mf.install(this);
            setColumns(13);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean eVazio() {
        return getText().equals("   .   .   -  ");
    }

    @Override
    public boolean eValido() {
        String cpf = getText();
        cpf = cpf.replace(".", "").replace("-", "");

        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
                || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999") || (cpf.length() != 11)) {
            return false;
        }
        char num10 = cpf.charAt(9), num11 = cpf.charAt(10);
        int soma, num, peso, resultado1, resultado2;
        soma = 0;
        peso = 10;
        for (int i = 0; i < 9; i++) {
            num = (int) (cpf.charAt(i) - 48);
            soma = soma + (num * peso);
            peso--;
        }
        resultado1 = 11 - (soma % 11);
        if ((resultado1 > 9)) {
            resultado1 = 0;
        }
        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            num = (int) (cpf.charAt(i) - 48);
            soma = soma + (num * peso);
            peso--;
        }
        resultado2 = 11 - (soma % 11);
        if (resultado2 > 9) {
            resultado2 = 0;
        }
        if ((resultado1 == (int) num10 - 48) && (resultado2 == (int) num11 - 48)) {
            return true;
        } else {
            return false;
        }
    }
}
