
package dados;

import java.sql.Date;

public class Reserva {
    private int codigo;
    private int codCliente;
    private int tipoQuarto;
    private int codHotel;
    private Date dataEntrada;
    private Date dataSaida;
    private String aceita;

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

    public int getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(int tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public int getCodHotel() {
        return codHotel;
    }

    public void setCodHotel(int codHotel) {
        this.codHotel = codHotel;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getAceita() {
        return aceita;
    }

    public void setAceita(String aceita) {
        this.aceita = aceita;
    }
    
    @Override
    public String toString(){
        return "codigo = "+codigo+", codCliente = "+codCliente+", tipoQuarto = "+tipoQuarto+", codHotel = "+codHotel+", dataEntrada = "+dataEntrada+", dataSaida = "+dataSaida+", aceita = "+aceita;
    }
}
