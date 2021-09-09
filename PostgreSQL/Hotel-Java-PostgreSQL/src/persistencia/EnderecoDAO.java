
package persistencia;

import dados.Endereco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    private static EnderecoDAO enderecoDAO = null;
    private static PreparedStatement select;
    private static PreparedStatement delete;
    private static PreparedStatement update;
    private static PreparedStatement insert;
    private static PreparedStatement selectAll;
    
    private EnderecoDAO() throws ClassNotFoundException, SQLException{
        select = DBConnection.getConexao().prepareStatement("select * from endereco where codigo = ?");
        delete = DBConnection.getConexao().prepareStatement("delete from endereco where codigo = ?");
        update = DBConnection.getConexao().prepareStatement("update endereco set rua = ?, bairro = ?, cidade = ?, estado = ? where codigo = ?");
        insert = DBConnection.getConexao().prepareStatement("insert into endereco values(?, ?, ?, ?, ?)");
        selectAll = DBConnection.getConexao().prepareStatement("select * from endereco order by codigo");
        
    }
    
    public static EnderecoDAO getInstance() throws ClassNotFoundException, SQLException{
        if (enderecoDAO == null){
            enderecoDAO = new EnderecoDAO();
        }
        return enderecoDAO;
    }
    
    public Endereco select(int codigo) throws SQLException, ClassNotFoundException{
        select.setInt(1, codigo);
        ResultSet rs = select.executeQuery();
        Endereco e = null;
        if(rs.next()){
            e = new Endereco();
            e.setCodigo(rs.getInt("codigo"));
            e.setRua(rs.getString("rua"));
            e.setBairro(rs.getString("bairro"));
            e.setCidade(rs.getString("cidade"));
            e.setEstado(rs.getString("estado"));
        }
        return e;        
    }
    
    public void delete(int codigo) throws SQLException, ClassNotFoundException{
        delete.setInt(1, codigo);
        delete.executeUpdate();
    }
    
    public void update(Endereco e) throws SQLException, ClassNotFoundException{
        update.setString(1, e.getRua());
        update.setString(2, e.getBairro());
        update.setString(3, e.getCidade());
        update.setString(4, e.getEstado());
        update.setInt(5, e.getCodigo());
        
        update.executeUpdate();       
        
    }
    
    public void insert(Endereco e) throws SQLException, ClassNotFoundException{
        insert.setInt(1, e.getCodigo());
        insert.setString(2, e.getRua());
        insert.setString(3, e.getBairro());
        insert.setString(4, e.getCidade());
        insert.setString(5, e.getEstado());
                        
        insert.executeUpdate();    
    }
    
    public List<Endereco> selectAll() throws SQLException{
        ResultSet rs = selectAll.executeQuery();
        Endereco e = null;
        List<Endereco> listaClientes = new ArrayList<Endereco>();
        while(rs.next()){
            e = new Endereco();
            e.setCodigo(rs.getInt("codigo"));
            e.setRua(rs.getString("rua"));
            e.setBairro(rs.getString("bairro"));
            e.setCidade(rs.getString("cidade"));
            e.setCidade(rs.getString("estado"));
            
            listaClientes.add(e);
        }
        return listaClientes;        
    }
}