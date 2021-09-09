
package dados;

public class TipoQuarto {
    private int codigo;
    private String tipo;
    private int preco;
    private String camaExtra;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getCamaExtra() {
        return camaExtra;
    }

    public void setCamaExtra(String camaExtra) {
        this.camaExtra = camaExtra;
    }
    @Override
    public String toString(){
        return "codigo = "+codigo+", tipo = "+tipo+", preco = "+preco+", cama extra = "+camaExtra;
    }
}
