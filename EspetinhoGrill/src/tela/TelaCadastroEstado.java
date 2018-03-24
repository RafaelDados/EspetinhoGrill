package tela;

import static utilitarios.Util.filtro;
import componente.CaixaSelecao;
import componente.CampoInteiro;
import componente.CampoTexto;
import dao.DaoEstado;
import javax.swing.JComboBox;
import pojo.Estado;

public class TelaCadastroEstado extends TelaCadastro implements Metodos {

    private Estado estado = new Estado();
    private DaoEstado daoEstado = new DaoEstado(estado);
    private CampoInteiro codigo = new CampoInteiro("Código", true, false, 3, 10);
    private CampoTexto nome = new CampoTexto("Nome", true, true, 10, 99);
    private CampoTexto sigla = new CampoTexto("Sigla", true, true, 10, 2);
    private CaixaSelecao status = new CaixaSelecao("Status", true);

    public TelaCadastroEstado(String titulo) {
        super(titulo);
        adicionaCampos();
        habilitaComponente(false);
        pack();
        //setSize(500, 600);
    }

    @Override
    public void adicionaCampos() {
        adicionaComponente(1, 1, 1, 1, codigo);
        adicionaComponente(3, 1, 1, 1, nome);
        adicionaComponente(3, 3, 1, 1, sigla);
        adicionaComponente(5, 1, 1, 1, status);
    }

    @Override
    public void setPersistencia() {
        estado.setId((int) codigo.getValor());
        estado.setNome((String) nome.getValor());
        estado.setSigla((String) sigla.getValor());
        estado.setStatus((boolean) status.getValor());
    }

    @Override
    public void getPersistencia() {
        codigo.setValor(estado.getId());
        nome.setValor(estado.getNome());
        sigla.setValor(estado.getSigla());
        status.setValor(estado.isStatus());
    }

    @Override
    public boolean incluirBD() {
        setPersistencia();
        boolean retorno = daoEstado.incluir();
        getPersistencia();
        return retorno;
    }

    @Override
    public void consultar() {
        super.consultar();
        new TelaConsulta(this, "Consulta de Estado", new String[]{"Código", "Nome", "Sigla", "Status"}, new String[]{"Nome", "Código", "Status"}, DaoEstado.SQL_PESQUISAR);
    }

    @Override
    public boolean alterarBD() {
        setPersistencia();
        return daoEstado.alterar();
    }

    @Override
    public boolean excluirBD() {
        setPersistencia();
        return daoEstado.excluir();
    }

    @Override
    public void preencherDados(int pk) {
        estado.setId(pk);
        daoEstado.consultar();
        getPersistencia();
        super.preencherDados(pk);
    }

    @Override
    public String pesquisarBD(String texto, JComboBox jcb) {
        String sql = filtro("ID, NOME, SIGLA, STATUS", "ESTADO", "", new String[]{"NOME", "ID", "STATUS"}, texto, jcb);
        return sql;
    }
}
