
package dados;


public class Quarto {
    private int codigo;
    private int andar;
    private int numero;
    private int codHotel;
    private String ocupado;
    private TipoQuarto tipo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodHotel() {
        return codHotel;
    }

    public void setCodHotel(int codHotel) {
        this.codHotel = codHotel;
    }

    public String getOcupado() {
        return ocupado;
    }

    public void setOcupado(String ocupado) {
        this.ocupado = ocupado;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString(){
        return "* codigo = "+codigo+", andar = "+andar+", numero = "+numero+", codHotel = "+codHotel
                +", ocupado = "+ocupado+", "+tipo;
    }
}
