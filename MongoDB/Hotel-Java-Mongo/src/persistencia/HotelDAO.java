
package persistencia;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import dados.Endereco;
import dados.Hotel;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HotelDAO {
    private static HotelDAO hotelDAO = null;
    private static MongoClient mongoClient;
    
    public static HotelDAO getInstance(){
        if (hotelDAO == null){
            hotelDAO = new HotelDAO();
        }
        return hotelDAO;
    }
    
    public void insert(Hotel h) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection hoteis = db.getCollection("hotel");
        DBObject data = new BasicDBObject();
        data.put("codigo", h.getCodigo()); 
        data.put("nome", h.getNome()); 
        data.put("telefone", h.getTelefone()); 
        Endereco e = new Endereco();
        e = h.getEndereco();
        Map<String, String> endereco = new HashMap<>();
        endereco.put("rua", e.getRua());
        endereco.put("bairro", e.getBairro());
        endereco.put("cidade", e.getCidade());
        endereco.put("estado", e.getEstado());
        data.put("endereco", endereco);
        hoteis.insert(data);
    }
    
    public List<Hotel> selectAll() throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection hoteis = db.getCollection("hotel");

        DBCursor cursor = hoteis.find();
        List<Hotel> listaHoteis = new ArrayList<Hotel>();
        Hotel h = null;
        Endereco e = null;
        try {
            while(cursor.hasNext()) {
               String temp = cursor.next().toString();
               int codigo = Integer.parseInt(pegaInt(temp, "codigo\" :"));
               String nome = pegaString(temp,"nome\" :");
               int telefone = Integer.parseInt(pegaInt(temp, "telefone\" :"));
               String rua = pegaString(temp,"rua\" :");
               String bairro = pegaString(temp,"bairro\" :");
               String cidade = pegaString(temp,"cidade\" :");
               String estado = pegaString(temp,"estado\" :");

                h = new Hotel();
                h.setCodigo(codigo);
                h.setNome(nome);
                h.setTelefone(telefone);
                e = new Endereco();
                e.setRua(rua);
                e.setBairro(bairro);
                e.setCidade(cidade);
                e.setEstado(estado);
                h.setEndereco(e);

                listaHoteis.add(h);
        
            }
        } finally {
            cursor.close();
        }
        return listaHoteis;
    }
    
    public Hotel select(int codigoH) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection hoteis = db.getCollection("hotel");
        BasicDBObject query = new BasicDBObject("codigo", codigoH);
        DBCursor cursor = hoteis.find(query);
        Hotel h = null;
        Endereco e = null;
        try {
            while(cursor.hasNext()) {
                String temp = cursor.next().toString();
               int codigo = Integer.parseInt(pegaInt(temp, "codigo\" :"));
               String nome = pegaString(temp,"nome\" :");
               int telefone = Integer.parseInt(pegaInt(temp, "telefone\" :"));
               String rua = pegaString(temp,"rua\" :");
               String bairro = pegaString(temp,"bairro\" :");
               String cidade = pegaString(temp,"cidade\" :");
               String estado = pegaString(temp,"estado\" :");

                h = new Hotel();
                h.setCodigo(codigo);
                h.setNome(nome);
                h.setTelefone(telefone);
                e = new Endereco();
                e.setRua(rua);
                e.setBairro(bairro);
                e.setCidade(cidade);
                e.setEstado(estado);
                h.setEndereco(e);
                
            }
        } finally {
            cursor.close();
        }
        return h;
    }
    
    public String pegaInt(String string, String campo){
        String[] aux = string.split(campo);
        String[] correto = aux[1].split(",");
        return correto[0].trim();
    }
    
    public String pegaString(String string, String campo){
        String[] aux = string.split(campo);
        String[] aux2 = aux[1].split(",");
        String[] correto = aux2[0].split("\"");
        return correto[1].trim();
    }
    
}

