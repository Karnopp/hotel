
package persistencia;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import dados.Extra;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ExtraDAO {
    private static ExtraDAO extraDAO = null;
    private static MongoClient mongoClient;
    
    public static ExtraDAO getInstance(){
        if (extraDAO == null){
            extraDAO = new ExtraDAO();
        }
        return extraDAO;
    }
    
    public void insert(Extra e) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection extras = db.getCollection("extra");
        DBObject data = new BasicDBObject();
        data.put("codigoEstadia", e.getCodigoEstadia()); 
        data.put("codServico", e.getCodServico()); 
        data.put("data", e.getData());
        data.put("hora", e.getHora());

        extras.insert(data);
    }
    
    public List<Extra> selectAll() throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection extras = db.getCollection("extra");

        DBCursor cursor = extras.find();
        List<Extra> listaExtras = new ArrayList<Extra>();
        Extra e = null;

        try {
            while(cursor.hasNext()) {
               String temp = cursor.next().toString();
               int codigoEstadia = Integer.parseInt(pegaInt(temp, "codigoEstadia\" :"));
               int codServico = Integer.parseInt(pegaInt(temp, "codServico\" :"));

               String data = pegaString(temp,"data\" :");
               String hora = pegaString(temp,"hora\" :");

                e = new Extra();
                e.setCodigoEstadia(codigoEstadia);
                e.setCodServico(codServico);
                e.setData(data);
                e.setHora(hora);

                listaExtras.add(e);
        
            }
        } finally {
            cursor.close();
        }
        return listaExtras;
    }
    
    public List<Extra> select(int codE) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection extras = db.getCollection("extra");
        BasicDBObject query = new BasicDBObject("codigoEstadia", codE);
        DBCursor cursor = extras.find(query);
        List<Extra> listaExtras = new ArrayList<Extra>();
        Extra e = null;

        try {
            while(cursor.hasNext()) {
               String temp = cursor.next().toString();
               int codigoEstadia = Integer.parseInt(pegaInt(temp, "codigoEstadia\" :"));
               int codServico = Integer.parseInt(pegaInt(temp, "codServico\" :"));

               String data = pegaString(temp,"data\" :");
               String hora = pegaString(temp,"hora\" :");

                e = new Extra();
                e.setCodigoEstadia(codigoEstadia);
                e.setCodServico(codServico);
                e.setData(data);
                e.setHora(hora);

                listaExtras.add(e);
        
            }
        } finally {
            cursor.close();
        }
        return listaExtras;
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

