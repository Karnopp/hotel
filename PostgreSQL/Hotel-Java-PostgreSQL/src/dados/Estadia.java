
package dados;

import java.sql.Date;

public class Estadia {
    private int codigo;
    private int codQuarto;
    private Date dataEntrada;
    private Date dataSaida;
    private int codReserva;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodQuarto() {
        return codQuarto;
    }

    public void setCodQuarto(int codQuarto) {
        this.codQuarto = codQuarto;
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

    public int getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(int codReserva) {
        this.codReserva = codReserva;
    }
    
    @Override
    public String toString(){
        return "codigo = "+codigo+", codQuarto = "+codQuarto+", dataEntrada = "+dataEntrada+", dataSaida = "+dataSaida+", codReserva = "+codReserva;
    }
}
