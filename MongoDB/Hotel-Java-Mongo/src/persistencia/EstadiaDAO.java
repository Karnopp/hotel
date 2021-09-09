
package persistencia;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import dados.Estadia;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class EstadiaDAO {
    private static EstadiaDAO estadiaDAO = null;
    private static MongoClient mongoClient;
    
    public static EstadiaDAO getInstance(){
        if (estadiaDAO == null){
            estadiaDAO = new EstadiaDAO();
        }
        return estadiaDAO;
    }
    
    public void insert(Estadia e) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection estadias = db.getCollection("estadia");
        DBObject data = new BasicDBObject();
        data.put("codigo", e.getCodigo()); 
        data.put("codQuarto", e.getCodQuarto()); 
        data.put("dataEntrada", e.getDataEntrada());
        data.put("dataSaida", e.getDataSaida());
        data.put("codReserva", e.getCodReserva());

        estadias.insert(data);
    }
    
    public List<Estadia> selectAll() throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection estadias = db.getCollection("estadia");

        DBCursor cursor = estadias.find();
        List<Estadia> listaEstadias = new ArrayList<Estadia>();
        Estadia e = null;

        try {
            while(cursor.hasNext()) {
               String temp = cursor.next().toString();
               int codigo = Integer.parseInt(pegaInt(temp, "codigo\" :"));
               int codQuarto = Integer.parseInt(pegaInt(temp, "codQuarto\" :"));
               int codReserva = Integer.parseInt(pegaInt(temp, "codReserva\" :"));
               String dataEntrada = pegaString(temp,"dataEntrada\" :");
               String dataSaida = pegaString(temp,"dataSaida\" :");

                e = new Estadia();
                e.setCodigo(codigo);
                e.setCodQuarto(codQuarto);
                e.setDataEntrada(dataEntrada);
                e.setDataSaida(dataSaida);
                e.setCodReserva(codReserva);

                listaEstadias.add(e);
        
            }
        } finally {
            cursor.close();
        }
        return listaEstadias;
    }
    
    public Estadia select(int codigoE) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection estadias = db.getCollection("estadia");
        BasicDBObject query = new BasicDBObject("codigo", codigoE);
        DBCursor cursor = estadias.find(query);
        Estadia e = null;

        try {
            while(cursor.hasNext()) {
                
               String temp = cursor.next().toString();
               int codigo = Integer.parseInt(pegaInt(temp, "codigo\" :"));
               int codQuarto = Integer.parseInt(pegaInt(temp, "codQuarto\" :"));
               int codReserva = Integer.parseInt(pegaInt(temp, "codReserva\" :"));

               String dataEntrada = pegaString(temp,"dataEntrada\" :");
               String dataSaida = pegaString(temp,"dataSaida\" :");

                e = new Estadia();
                e.setCodigo(codigo);
                e.setCodQuarto(codQuarto);
                e.setCodReserva(codReserva);
                e.setDataEntrada(dataEntrada);
                e.setDataSaida(dataSaida);

                
            }
        } finally {
            cursor.close();
        }
        return e;
    }
    public int codigoDisponivel() throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection estadias = db.getCollection("estadia");

        DBCursor cursor = estadias.find();
 
        Estadia e = null;
        int cont=1;
        try {
            while(cursor.hasNext()) {
               String temp = cursor.next().toString();
               cont++;
        
            }
        } finally {
            cursor.close();
        }
        return cont;
    }
    public String pegaInt(String string, String campo){
        String[] aux = string.split(campo);
        String[] correto = aux[1].split(",");
        if("}".equals(correto[0].substring(correto[0].length()-1, correto[0].length()-0))){
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

