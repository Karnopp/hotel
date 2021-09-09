
package dados;

public class Reserva {
    private int codigo;
    private int codCliente;
    private String tipoQuarto;
    private int codHotel;
    private String dataEntrada;
    private String dataSaida;

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }
    private String estadia;
    private String ativo;

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public int getCodHotel() {
        return codHotel;
    }

    public void setCodHotel(int codHotel) {
        this.codHotel = codHotel;
    }


    

    public String getEstadia() {
        return estadia;
    }

    public void setEstadia(String estadia) {
        this.estadia = estadia;
    }
    
    @Override
    public String toString(){
        return "* codigo = "+codigo+", codCliente = "+codCliente+", tipoQuarto = "+tipoQuarto+", codHotel = "+codHotel+", dataEntrada = "+dataEntrada+", dataSaida = "+dataSaida+", \n\testadia = "+estadia+", ativo = "+ativo;
    }
}
