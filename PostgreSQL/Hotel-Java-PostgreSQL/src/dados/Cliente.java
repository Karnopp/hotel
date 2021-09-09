
package dados;

public class Cliente {
    private String rg;
    private String nome;
    private String telefone;
    private int codEndereco;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }
    
    @Override
    public String toString(){
        return "Rg = "+rg+", nome = "+nome+", telefone = "+telefone+", codEndereco = "+codEndereco;
    }
}
