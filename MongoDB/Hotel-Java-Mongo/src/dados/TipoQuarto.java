
package dados;

public class TipoQuarto {
    private String tipo;
    private String camaExtra;
    private int preco;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCamaExtra() {
        return camaExtra;
    }

    public void setCamaExtra(String camaExtra) {
        this.camaExtra = camaExtra;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
    
    @Override
    public String toString(){
        return "tipo = "+tipo+", camaExtra = "+camaExtra+", preco = "+preco;
    }
}
