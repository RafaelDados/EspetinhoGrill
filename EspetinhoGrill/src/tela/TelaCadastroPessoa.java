package tela;

import componente.CaixaSelecao;
import componente.CampoCnpj;
import componente.CampoCpf;
import componente.CampoData;
import componente.CampoInteiro;
import componente.CampoSelecaoBD;
import componente.CampoTelefone;
import componente.CampoTexto;
import componente.CampoTextoInteiro;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TelaCadastroPessoa extends TelaCadastro implements Metodos{

    private CampoInteiro codigo = new CampoInteiro("Código", true, false, 3, 10);
    private CampoTexto nome = new CampoTexto("Nome", true, true, 20, 99);
    private CampoTexto nomeUsuario = new CampoTexto("Usuário", false, true, 10, 99);
    private CampoTelefone telefone = new CampoTelefone("Telefone", false);
    private CampoTexto rua = new CampoTexto("Rua", false, true, 10, 99);
    private CampoTexto bairro = new CampoTexto("Bairro", false, true, 10, 99);
    private CampoTextoInteiro numero = new CampoTextoInteiro("Número", false, 10, 99);
    private CampoTexto cep = new CampoTexto("CEP", false, true, 10, 99);
    private CampoData dataNasc = new CampoData("Data de Nascimento", true);
    private CampoTexto rg = new CampoTexto("RG", false, true, 10, 19);  // Fazer campo rg
    private CampoCpf cpf = new CampoCpf("CPF", true);
    private CampoCnpj cnpj = new CampoCnpj("CNPJ", true);
    private CampoTexto carteiraTrab = new CampoTexto("Carteira de trabaho", false, true, 10, 19); // Fazer campo Carteira de trabalho
    private CaixaSelecao cliente = new CaixaSelecao("Cliente", false);
    private CaixaSelecao fornecedor = new CaixaSelecao("Fornecedor", false);
    private CaixaSelecao funcionario = new CaixaSelecao("Funcionário", false);
    private CaixaSelecao usuario = new CaixaSelecao("Usuário", false);
    private CaixaSelecao status = new CaixaSelecao("Status", true);
    private CampoSelecaoBD cidade = new CampoSelecaoBD("Cidade", true, "");
    private CampoSelecaoBD cargo = new CampoSelecaoBD("Cargo", true, "");
    private CampoSelecaoBD tipoPessoa = new CampoSelecaoBD("Tipo de Pessoa", true, "");

    public TelaCadastroPessoa(String titulo) {
        super(titulo);
        adicionaOuvintes();
        adicionaCampos();
        habilitaComponente(false);
        setSize(1000, 1000);
    }

    @Override
    public void adicionaCampos() {
        adicionaComponente(1, 1, 1, 3, codigo);
        adicionaComponente(2, 1, 1, 5, nome);
        adicionaComponente(2, 7, 1, 3, nomeUsuario);
        adicionaComponente(3, 1, 1, 1, cliente);
        adicionaComponente(3, 3, 1, 1, fornecedor);
        adicionaComponente(3, 5, 1, 1, funcionario);
        adicionaComponente(3, 7, 1, 1, usuario);
        adicionaComponente(4, 1, 1, 6, rua);
        adicionaComponente(4, 5, 1, 5, bairro);
        adicionaComponente(5, 1, 1, 6, cep);
        adicionaComponente(5, 5, 1, 5, numero);
        adicionaComponente(6, 1, 1, 6, telefone);
        adicionaComponente(6, 3, 1, 3, dataNasc);
        adicionaComponente(7, 1, 1, 6, rg);
        adicionaComponente(7, 5, 1, 3, cpf);
        adicionaComponente(11, 1, 1, 1, cnpj);
        adicionaComponente(11, 1, 1, 4, carteiraTrab);
        adicionaComponente(12, 1, 1, 4, status);
    }
    
    @Override
    public void setPersistencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getPersistencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void adicionaOuvintes() {
        usuario.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == e.SELECTED) {
                    nomeUsuario.setVisible(true);
                } else if (e.getStateChange() == e.DESELECTED) {
                    nomeUsuario.setVisible(false);
                }
            }
        });
    }   
}