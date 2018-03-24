package componente;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class CampoCnpj extends CampoTextoFormatado {

    public CampoCnpj(String nome, boolean obrigatorio) {
        super(nome, obrigatorio);
        try {
            MaskFormatter mf = new MaskFormatter("##.###.###/####-##");
            mf.install(this);
            setColumns(10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean eVazio() {
        return getText().equals("  .   .   /    -  ");
    }

    @Override
    public boolean eValido() {
        String cnpj = getText();
        cnpj = cnpj.replace(".", "").replace("/", "").replace("-", "");

        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")
                || (cnpj.length() != 14)) {
            return false;
        }

        char num13 = cnpj.charAt(12), num14 = cnpj.charAt(13);
        int num = 0, soma = 0, resultado1, resultado2, peso = 0;

        soma = 0;
        peso = 2;

        for (int i = 11; i >= 0; i--) {
            num = (int) cnpj.charAt(i) - 48;
            soma = soma + (num * peso);
            peso++;
            if (peso == 10) {
                peso = 2;
            }
        }
        resultado1 = soma % 11;

        if ((resultado1 == 0) || (resultado1 == 1)) {
            resultado1 = 0;
        } else {
            resultado1 = 11 - resultado1;
        }

        soma = 0;
        num = 0;
        peso = 2;

        for (int i = 12; i >= 0; i--) {
            num = (int) cnpj.charAt(i) - 48;
            soma = soma + (num * peso);
            peso++;
            if (peso == 10) {
                peso = 2;
            }
        }

        resultado2 = soma % 11;
        if ((resultado2 == 0) || (resultado2 == 1)) {
            resultado2 = 0;
        } else {
            resultado2 = 11 - resultado2;
        }

        if ((resultado1 == (int) num13 - 48) && (resultado2 == (int) num14 - 48)) {
            return true;
        } else {
            return false;
        }
    }
}
