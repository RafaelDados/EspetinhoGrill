package componente;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class CampoData extends CampoTextoFormatado{
    
    public CampoData(String nome, boolean obrigatorio) {
        super(nome, obrigatorio);
        try {
            MaskFormatter mf = new MaskFormatter("##/##/####");
            mf.install(this);
            setColumns(10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public boolean eVazio(){
        return getText().equals("  /  /    ");
    }
    
    @Override
    public boolean eValido(){
        return false;  //redefinir o método domingo dia 11/02/2018
    }
}