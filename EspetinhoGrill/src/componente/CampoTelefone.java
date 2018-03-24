package componente;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class CampoTelefone extends CampoTextoFormatado{
    
    public CampoTelefone(String nome, boolean obrigatorio) {
        super(nome, obrigatorio);
        try{
            MaskFormatter mf = new MaskFormatter("(##)#.####-####");
            mf.install(this);
            setColumns(10);
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean eVazio(){
        return getText().equals("(  ) .    -    ");
    }
}