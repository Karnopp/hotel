
package persistencia;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import dados.Servico;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ServicoDAO {
    private static ServicoDAO servicoDAO = null;
    private static MongoClient mongoClient;
    
    public static ServicoDAO getInstance(){
        if (servicoDAO == null){
            servicoDAO = new ServicoDAO();
        }
        return servicoDAO;
    }
    
    
    public List<Servico> selectAll() throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection servicos = db.getCollection("servicos");

        DBCursor cursor = servicos.find();
        List<Servico> listaServicos = new ArrayList<Servico>();
        Servico s = null;

        try {
            while(cursor.hasNext()) {
               String temp = cursor.next().toString();
               int codigo = Integer.parseInt(pegaInt(temp, "codigo\" :"));
               int preco = Integer.parseInt(pegaInt(temp, "preco\" :"));
               String tipo = pegaString(temp,"tipo\" :");

                s = new Servico();
                s.setCodigo(codigo);
                s.setTipo(tipo);
                s.setPreco(preco);

                listaServicos.add(s);
        
            }
        } finally {
            cursor.close();
        }
        return listaServicos;
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

