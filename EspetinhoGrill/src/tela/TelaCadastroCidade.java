package tela;

import componente.CaixaSelecao;
import componente.CampoInteiro;
import componente.CampoSelecaoBD;
import componente.CampoTexto;
import dao.DaoCidade;
import dao.DaoEstado;
import javax.swing.JComboBox;
import pojo.Cidade;
import static utilitarios.Util.filtro;

public class TelaCadastroCidade extends TelaCadastro implements Metodos {

    private Cidade cidade = new Cidade();
    private DaoCidade daoCidade = new DaoCidade(cidade);
    private CampoInteiro codigo = new CampoInteiro("Código", true, false, 3, 10);
    private CampoTexto nome = new CampoTexto("Nome", true, true, 10, 99);
    private CaixaSelecao status = new CaixaSelecao("Status", true);
    private CampoSelecaoBD estado = new CampoSelecaoBD("Estado", true, DaoEstado.SQL_COMBOBOX);

    public TelaCadastroCidade(String titulo) {
        super(titulo);
        adicionaCampos();
        habilitaComponente(false);
        pack();
    }

    @Override
    public void adicionaCampos() {
        adicionaComponente(1, 1, 1, 1, codigo);
        adicionaComponente(3, 1, 1, 1, nome);
        adicionaComponente(3, 3, 1, 1, estado);
        adicionaComponente(3, 5, 1, 1, status);
    }

    @Override
    public void setPersistencia() {
        cidade.setId((int) codigo.getValor());
        cidade.setNome((String) nome.getValor());
        cidade.setStatus((boolean) status.getValor());
        cidade.getEstado().setId((int) estado.getValor());
    }

    @Override
    public void getPersistencia() {
        codigo.setValor(cidade.getId());
        nome.setValor(cidade.getNome());
        status.setValor(cidade.isStatus());
        estado.setValor(cidade.getEstado().getId());
    }

    @Override
    public boolean incluirBD() {
        setPersistencia();
        boolean retorno = daoCidade.incluir();
        getPersistencia();
        return retorno;
    }

    @Override
    public void consultar() {
        super.consultar();
        new TelaConsulta(this, "Consulta de Cidade", new String[]{"Código", "Nome", "Status", "Estado"}, new String[]{"Nome", "Código", "Status"}, DaoCidade.SQL_PESQUISAR);
    }
    
    @Override
    public boolean alterarBD(){
        setPersistencia();
        return daoCidade.alterar();
    }
    
    @Override
    public boolean excluirBD(){
        setPersistencia();
        return daoCidade.excluir();
    }
    
    @Override
    public void preencherDados(int pk){
        cidade.setId(pk);
        daoCidade.consultar();
        getPersistencia();
        super.preencherDados(pk);
    }
    
    @Override
    public String pesquisarBD(String texto, JComboBox jcb){
        String sql = filtro("CIDADE.ID, CIDADE.NOME, CIDADE.STATUS", "CIDADE", "INNER JOIN ESTADO ON ESTADO.ID = CIDADE.IDESTADO ", new String[]{"NOME","ID","STATUS"}, texto, jcb);
        return sql;
    }
}