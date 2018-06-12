package tela;

import componente.CampoInteiro;
import componente.CampoTexto;
import dao.DaoCargo;
import pojo.Cargo;

public class TelaCadastroCargo extends TelaCadastro implements Metodos {

    private Cargo cargo = new Cargo();
    private DaoCargo daoCargo = new DaoCargo(cargo);
    private CampoInteiro codigo = new CampoInteiro("Codigo", true, false, 3, 99);
    private CampoTexto nome = new CampoTexto("Nome", true, true, 10, 99);

    public TelaCadastroCargo(String titulo) {
        super(titulo);
        adicionaCampos();
        habilitaComponente(false);
        pack();
    }
    
    @Override
    public void adicionaCampos() {
        adicionaComponente(1, 1, 1, 1, codigo);
        adicionaComponente(3, 1, 1, 1, nome);
    }

    @Override
    public void setPersistencia() {
        cargo.setId((int) codigo.getValor());
        cargo.setNome((String) nome.getValor());
    }

    @Override
    public void getPersistencia() {
        codigo.setValor(cargo.getId());
        nome.setValor(cargo.getNome());
    }
    
    @Override
    public boolean incluirBD(){
        setPersistencia();
        boolean retorno = daoCargo.incluir();
        getPersistencia();
        return retorno;
    }

}