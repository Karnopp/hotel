
package dados;

import java.sql.Date;

public class Limpeza {
    private Date data;
    private int codQuarto;
    private String codEmpregado;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCodQuarto() {
        return codQuarto;
    }

    public void setCodQuarto(int codQuarto) {
        this.codQuarto = codQuarto;
    }

    public String getCodEmpregado() {
        return codEmpregado;
    }

    public void setCodEmpregado(String codEmpregado) {
        this.codEmpregado = codEmpregado;
    }
    
    @Override
    public String toString(){
        return "Data = "+data+", codQuarto = "+codQuarto+", codEmpregado = "+codEmpregado;
    }
    
    
}
