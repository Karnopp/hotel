
package persistencia;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import dados.Reserva;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ReservaDAO {
    private static ReservaDAO reservaDAO = null;
    private static MongoClient mongoClient;
    
    public static ReservaDAO getInstance(){
        if (reservaDAO == null){
            reservaDAO = new ReservaDAO();
        }
        return reservaDAO;
    }
    
    public void insert(Reserva r) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection reservas = db.getCollection("reserva");
        DBObject data = new BasicDBObject();
        data.put("codigo", r.getCodigo()); 
        data.put("codCliente", r.getCodCliente()); 
        data.put("tipoQuarto", r.getTipoQuarto());
        data.put("codHotel", r.getCodHotel());
        data.put("dataEntrada", r.getDataEntrada());
        data.put("dataSaida", r.getDataSaida());
        data.put("estadia", r.getEstadia());
        data.put("ativo", r.getAtivo());
        

        reservas.insert(data);
    }
    
    public List<Reserva> selectAll() throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection reservas = db.getCollection("reserva");

        DBCursor cursor = reservas.find();
        List<Reserva> listaReservas = new ArrayList<Reserva>();
        Reserva r = null;

        try {
            while(cursor.hasNext()) {
               String temp = cursor.next().toString();
               int codigo = Integer.parseInt(pegaInt(temp, "codigo\" :"));
               int codCliente = Integer.parseInt(pegaInt(temp, "codCliente\" :"));
               int codHotel = Integer.parseInt(pegaInt(temp, "codHotel\" :"));
               
               String tipoQuarto = pegaString(temp,"tipoQuarto\" :");
               String dataEntrada = pegaString(temp,"dataEntrada\" :");
               String dataSaida = pegaString(temp,"dataSaida\" :");
               String estadia = pegaString(temp,"estadia\" :");
               String ativo = pegaString(temp,"ativo\" :");
                r = new Reserva();
                r.setCodigo(codigo);
                r.setCodHotel(codHotel);
                r.setCodCliente(codCliente);
                r.setTipoQuarto(tipoQuarto);
                r.setDataEntrada(dataEntrada);
                r.setDataSaida(dataSaida);
                r.setEstadia(estadia);
                r.setAtivo(ativo);

                listaReservas.add(r);
        
            }
        } finally {
            cursor.close();
        }
        return listaReservas;
    }
    
    public Reserva select(int codigoR) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection reservas = db.getCollection("reserva");
        BasicDBObject query = new BasicDBObject("codigo", codigoR);
        DBCursor cursor = reservas.find(query);
        Reserva r = null;

        try {
            while(cursor.hasNext()) {
                
               String temp = cursor.next().toString();
               int codigo = Integer.parseInt(pegaInt(temp, "codigo\" :"));
               int codCliente = Integer.parseInt(pegaInt(temp, "codCliente\" :"));
               int codHotel = Integer.parseInt(pegaInt(temp, "codHotel\" :"));
               
               String tipoQuarto = pegaString(temp,"tipoQuarto\" :");
               String dataEntrada = pegaString(temp,"dataEntrada\" :");
               String dataSaida = pegaString(temp,"dataSaida\" :");
               String estadia = pegaString(temp,"estadia\" :");
               String ativo = pegaString(temp,"ativo\" :");
                r = new Reserva();
                r.setCodigo(codigo);
                r.setCodHotel(codHotel);
                r.setCodCliente(codCliente);
                r.setTipoQuarto(tipoQuarto);
                r.setDataEntrada(dataEntrada);
                r.setDataSaida(dataSaida);
                r.setEstadia(estadia);
                r.setAtivo(ativo);
                
            }
        } finally {
            cursor.close();
        }
        return r;
    }
    
    public String pegaInt(String string, String campo){
        String[] aux = string.split(campo);
        String[] correto = aux[1].split(",");
        if("}".equals(correto[0].substring(correto[0].length()-2, correto[0].length()-1))){
            String[] agoraVai = correto[0].split("}");
            return agoraVai[0].trim();
        }
        else{
            return correto[0].trim();
        }  
    }
    
    public String pegaString(String string, String campo){
        String[] aux = string.split(campo);
        String[] aux2 = aux[1].split(",");
        String[] correto = aux2[0].split("\"");
        return correto[1].trim();
    }
    
}

