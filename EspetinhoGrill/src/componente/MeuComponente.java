package componente;

public interface MeuComponente {
    public String getNome();
    public boolean eObrigatorio();
    public void habilitar(boolean status);
    public boolean eVazio();
    public boolean eValido();
    public void limpar();
    public Object getValor();
    public void setValor(Object objeto);
}