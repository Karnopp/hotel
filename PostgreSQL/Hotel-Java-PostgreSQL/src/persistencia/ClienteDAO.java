
package persistencia;

import dados.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static ClienteDAO clienteDAO = null;
    private static PreparedStatement select;
    private static PreparedStatement delete;
    private static PreparedStatement update;
    private static PreparedStatement insert;
    private static PreparedStatement selectAll;
    
    private ClienteDAO() throws ClassNotFoundException, SQLException{
        select = DBConnection.getConexao().prepareStatement("select * from cliente where rg = ?");
        delete = DBConnection.getConexao().prepareStatement("delete from cliente where rg = ?");
        update = DBConnection.getConexao().prepareStatement("update cliente set nome = ?, telefone = ?, codEndereco = ? where rg = ?");
        insert = DBConnection.getConexao().prepareStatement("insert into cliente values(?, ?, ?, ?)");
        selectAll = DBConnection.getConexao().prepareStatement("select * from cliente order by rg");
        
    }
    
    public static ClienteDAO getInstance() throws ClassNotFoundException, SQLException{
        if (clienteDAO == null){
            clienteDAO = new ClienteDAO();
        }
        return clienteDAO;
    }
    
    public Cliente select(String rg) throws SQLException, ClassNotFoundException{
        select.setString(1, rg);
        ResultSet rs = select.executeQuery();
        Cliente c = null;
        if(rs.next()){
            c = new Cliente();
            c.setRg(rs.getString("rg"));
            c.setNome(rs.getString("nome"));
            c.setTelefone(rs.getString("telefone"));
            c.setCodEndereco(rs.getInt("codEndereco"));
        }
        return c;        
    }
    
    public void delete(String rg) throws SQLException, ClassNotFoundException{
        delete.setString(1, rg);
        delete.executeUpdate();
    }
    
    public void update(Cliente c) throws SQLException, ClassNotFoundException{
        update.setString(1, c.getNome());
        update.setString(2, c.getTelefone());
        update.setInt(3, c.getCodEndereco());
        update.setString(4, c.getRg());
        
        update.executeUpdate();       
        
    }
    
    public void insert(Cliente c) throws SQLException, ClassNotFoundException{
        insert.setString(1, c.getRg());
        insert.setString(2, c.getNome());
        insert.setString(3, c.getTelefone());
        insert.setInt(4, c.getCodEndereco());
                
        insert.executeUpdate();    
    }
    
    public List<Cliente> selectAll() throws SQLException{
        ResultSet rs = selectAll.executeQuery();
        Cliente c = null;
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        while(rs.next()){
            c = new Cliente();
            c.setRg(rs.getString("rg"));
            c.setNome(rs.getString("nome"));
            c.setTelefone(rs.getString("telefone"));
            c.setCodEndereco(rs.getInt("codEndereco"));
            
            listaClientes.add(c);
        }
        return listaClientes;        
    }
}