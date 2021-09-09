
package dados;

import java.sql.Date;
import java.sql.Time;

public class Extra {
    private int codEstadia;
    private int codServico;
    private Date data;
    private Time hora;

    public int getCodEstadia() {
        return codEstadia;
    }

    public void setCodEstadia(int codEstadia) {
        this.codEstadia = codEstadia;
    }

    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
    
    @Override
    public String toString(){
        return "CodEstadia = "+codEstadia+", codServico = "+codServico+", data = "+data+", hora = "+hora;
    }
}
