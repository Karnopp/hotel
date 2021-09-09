
package persistencia;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import dados.Quarto;
import dados.TipoQuarto;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class QuartoDAO {
    private static QuartoDAO quartoDAO = null;
    private static MongoClient mongoClient;
    
    public static QuartoDAO getInstance(){
        if (quartoDAO == null){
            quartoDAO = new QuartoDAO();
        }
        return quartoDAO;
    }
    
    public void insert(Quarto q) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection quartos = db.getCollection("quarto");
        DBObject data = new BasicDBObject();
        data.put("codigo", q.getCodigo()); 
        data.put("andar", q.getAndar()); 
        data.put("numero", q.getNumero());
        data.put("codHotel", q.getCodHotel());
        data.put("ocupado", q.getOcupado());
        //data.put("endereco", c.getEndereco()); 
        TipoQuarto t = new TipoQuarto();
        t = q.getTipo();
        Map<String, String> tipoS = new HashMap<>();
        tipoS.put("tipo", t.getTipo());
        tipoS.put("camaExtra", t.getCamaExtra());
        data.put("tipoQuarto", tipoS);
        quartos.insert(data);
        
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("tipoQuarto.preco", t.getPreco()));

        BasicDBObject searchQuery = new BasicDBObject().append("codigo", q.getCodigo());

        quartos.update(searchQuery, newDocument);
        
        
    }
    
    public List<Quarto> selectAll() throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection quartos = db.getCollection("quarto");

        DBCursor cursor = quartos.find();
        List<Quarto> listaQuartos = new ArrayList<Quarto>();
        Quarto q = null;
        TipoQuarto t = null;
        try {
            while(cursor.hasNext()) {
               String temp = cursor.next().toString();
               int codigo = Integer.parseInt(pegaInt(temp, "codigo\" :"));
               int andar = Integer.parseInt(pegaInt(temp, "andar\" :"));
               int numero = Integer.parseInt(pegaInt(temp, "numero\" :"));
               int codHotel = Integer.parseInt(pegaInt(temp, "codHotel\" :"));
               
               String ocupado = pegaString(temp,"ocupado\" :");
               String tipo = pegaString(temp,"tipo\" :");
               String camaExtra = pegaString(temp,"camaExtra\" :");
               int preco = Integer.parseInt(pegaInt(temp, "preco\" :"));

                q = new Quarto();
                q.setCodigo(codigo);
                q.setAndar(andar);
                q.setNumero(numero);
                q.setCodHotel(codHotel);
                q.setOcupado(ocupado);
                t = new TipoQuarto();
                t.setTipo(tipo);
                t.setCamaExtra(camaExtra);
                t.setPreco(preco);
                q.setTipo(t);

                listaQuartos.add(q);
        
            }
        } finally {
            cursor.close();
        }
        return listaQuartos;
    }
    
    public Quarto select(int codigoQ) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection quartos = db.getCollection("quarto");
        BasicDBObject query = new BasicDBObject("codigo", codigoQ);
        DBCursor cursor = quartos.find(query);
        Quarto q = null;
        TipoQuarto t = null;
        try {
            while(cursor.hasNext()) {
                
               String temp = cursor.next().toString();
               int codigo = Integer.parseInt(pegaInt(temp, "codigo\" :"));
               int andar = Integer.parseInt(pegaInt(temp, "andar\" :"));
               int numero = Integer.parseInt(pegaInt(temp, "numero\" :"));
               int codHotel = Integer.parseInt(pegaInt(temp, "codHotel\" :"));
               
               String ocupado = pegaString(temp,"ocupado\" :");
               String tipo = pegaString(temp,"tipo\" :");
               String camaExtra = pegaString(temp,"camaExtra\" :");
               int preco = Integer.parseInt(pegaInt(temp, "preco\" :"));

                q = new Quarto();
                q.setCodigo(codigo);
                q.setAndar(andar);
                q.setNumero(numero);
                q.setCodHotel(codHotel);
                q.setOcupado(ocupado);
                t = new TipoQuarto();
                t.setTipo(tipo);
                t.setCamaExtra(camaExtra);
                t.setPreco(preco);
                q.setTipo(t);
                
            }
        } finally {
            cursor.close();
        }
        return q;
        
    }
    public void setQuartoOcupado(Quarto q) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection quartos = db.getCollection("quarto");
        

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("ocupado", "sim"));

        BasicDBObject searchQuery = new BasicDBObject().append("codigo", q.getCodigo());

        quartos.update(searchQuery, newDocument);
    }
    public Quarto quartoLivre(String tipoQ, int codH) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection quartos = db.getCollection("quarto");
        BasicDBObject query = new BasicDBObject("tipoQuarto.tipo", tipoQ);
        DBCursor cursor = quartos.find(query);
        Quarto q = null;
        TipoQuarto t = null;
        
        try {
            while(cursor.hasNext()) {
                String temp = cursor.next().toString();
                if(codH==Integer.parseInt(pegaInt(temp, "codHotel\" :")) && pegaString(temp,"ocupado\" :")!="sim" ){
                    
                    int codigo = Integer.parseInt(pegaInt(temp, "codigo\" :"));
                    int andar = Integer.parseInt(pegaInt(temp, "andar\" :"));
                    int numero = Integer.parseInt(pegaInt(temp, "numero\" :"));
                    int codHotel = Integer.parseInt(pegaInt(temp, "codHotel\" :"));

                    String ocupado = pegaString(temp,"ocupado\" :");
                    String tipo = pegaString(temp,"tipo\" :");
                    String camaExtra = pegaString(temp,"camaExtra\" :");
                    int preco = Integer.parseInt(pegaInt(temp, "preco\" :"));

                     q = new Quarto();
                     q.setCodigo(codigo);
                     q.setAndar(andar);
                     q.setNumero(numero);
                     q.setCodHotel(codHotel);
                     q.setOcupado(ocupado);
                     t = new TipoQuarto();
                     t.setTipo(tipo);
                     t.setCamaExtra(camaExtra);
                     t.setPreco(preco);
                     q.setTipo(t);  
                }
            }
        } finally {
            cursor.close();
        }
        return q; 
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

