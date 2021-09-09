
package dados;

public class Quarto {
    private int codigo;
    private int tipoQuarto;
    private int andar;
    private int numero;
    private int codHotel;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(int tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
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
    
    @Override
    public String toString(){
        return "codigo = "+codigo+", tipoQuarto = "+tipoQuarto+", andar = "+andar+", numero = "+numero+", codHotel = "+codHotel;
    }
}
