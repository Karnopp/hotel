
package persistencia;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import dados.Cliente;
import dados.Endereco;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ClienteDAO {
    private static ClienteDAO clienteDAO = null;
    private static MongoClient mongoClient;
    
    public static ClienteDAO getInstance(){
        if (clienteDAO == null){
            clienteDAO = new ClienteDAO();
        }
        return clienteDAO;
    }
    
    public void insert(Cliente c) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection clientes = db.getCollection("cliente");
        DBObject data = new BasicDBObject();
        data.put("rg", c.getRg()); 
        data.put("nome", c.getNome()); 
        data.put("telefone", c.getTelefone()); 
        //data.put("endereco", c.getEndereco()); 
        Endereco e = new Endereco();
        e = c.getEndereco();
        Map<String, String> endereco = new HashMap<>();
        endereco.put("rua", e.getRua());
        endereco.put("bairro", e.getBairro());
        endereco.put("cidade", e.getCidade());
        endereco.put("estado", e.getEstado());
        data.put("endereco", endereco);
        clientes.insert(data);
    }
    
    public List<Cliente> selectAll() throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection clientes = db.getCollection("cliente");
        //BasicDBObject query = new BasicDBObject("nome", "Juvenal");
        //BasicDBObject allQuery = new BasicDBObject();
        //BasicDBObject fields = new BasicDBObject("nome", 1);
        //fields.put("nome", 1);

        DBCursor cursor = clientes.find();
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        Cliente c = null;
        Endereco e = null;
        try {
            while(cursor.hasNext()) {
               String temp = cursor.next().toString();
               int rg = Integer.parseInt(pegaInt(temp, "rg\" :"));
               String nome = pegaString(temp,"nome\" :");
               int telefone = Integer.parseInt(pegaInt(temp, "telefone\" :"));
               String rua = pegaString(temp,"rua\" :");
               String bairro = pegaString(temp,"bairro\" :");
               String cidade = pegaString(temp,"cidade\" :");
               String estado = pegaString(temp,"estado\" :");

                c = new Cliente();
                c.setRg(rg);
                c.setNome(nome);
                c.setTelefone(telefone);
                e = new Endereco();
                e.setRua(rua);
                e.setBairro(bairro);
                e.setCidade(cidade);
                e.setEstado(estado);
                c.setEndereco(e);

                listaClientes.add(c);
        
            }
        } finally {
            cursor.close();
        }
        return listaClientes;
    }
    
    public Cliente select(int rg_cliente) throws UnknownHostException{
        mongoClient = Connection.getConexao();
        DB db = mongoClient.getDB("hotel");
        DBCollection clientes = db.getCollection("cliente");
        BasicDBObject query = new BasicDBObject("rg", rg_cliente);
        DBCursor cursor = clientes.find(query);
        Cliente c = null;
        Endereco e = null;
        try {
            while(cursor.hasNext()) {
                String temp = cursor.next().toString();
                int rg = Integer.parseInt(pegaInt(temp, "rg\" :"));
                String nome = pegaString(temp,"nome\" :");
                int telefone = Integer.parseInt(pegaInt(temp, "telefone\" :"));
                String rua = pegaString(temp,"rua\" :");
                String bairro = pegaString(temp,"bairro\" :");
                String cidade = pegaString(temp,"cidade\" :");
                String estado = pegaString(temp,"estado\" :");
                
                c = new Cliente();
                c.setRg(rg);
                c.setNome(nome);
                c.setTelefone(telefone);
                e = new Endereco();
                e.setRua(rua);
                e.setBairro(bairro);
                e.setCidade(cidade);
                e.setEstado(estado);
                c.setEndereco(e);
                
            }
        } finally {
            cursor.close();
        }
        return c;
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
