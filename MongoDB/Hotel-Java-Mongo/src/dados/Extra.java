
package dados;

public class Extra {
    private int codigoEstadia;
    private int codServico;
    private String data;
    private String hora;

    public int getCodigoEstadia() {
        return codigoEstadia;
    }

    public void setCodigoEstadia(int codigoEstadia) {
        this.codigoEstadia = codigoEstadia;
    }

    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    @Override
    public String toString(){
        return "* codigoEstadia = "+codigoEstadia+", codServico = "+codServico+", data = "+data+", hora = "+hora;
    }
}
