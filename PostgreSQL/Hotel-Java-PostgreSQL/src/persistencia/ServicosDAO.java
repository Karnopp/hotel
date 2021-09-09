
package persistencia;

import dados.Servicos;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServicosDAO {
    private static ServicosDAO servicosDAO = null;
    private static PreparedStatement select;
    private static PreparedStatement delete;
    private static PreparedStatement update;
    private static PreparedStatement insert;
    private static PreparedStatement selectAll;
    
    private ServicosDAO() throws ClassNotFoundException, SQLException{
        select = DBConnection.getConexao().prepareStatement("select * from servicos where codigo = ?");
        delete = DBConnection.getConexao().prepareStatement("delete from servicos where codigo = ?");
        update = DBConnection.getConexao().prepareStatement("update servicos set tipo = ?, preco = ? where codigo = ?");
        insert = DBConnection.getConexao().prepareStatement("insert into servicos values(?, ?, ?)");
        selectAll = DBConnection.getConexao().prepareStatement("select * from servicos order by codigo");
        
    }
    
    public static ServicosDAO getInstance() throws ClassNotFoundException, SQLException{
        if (servicosDAO == null){
            servicosDAO = new ServicosDAO();
        }
        return servicosDAO;
    }
    
    public Servicos select(int codigo) throws SQLException, ClassNotFoundException{
        select.setInt(1, codigo);
        ResultSet rs = select.executeQuery();
        Servicos s = null;
        if(rs.next()){
            s = new Servicos();
            s.setCodigo(rs.getInt("codigo"));
            s.setTipo(rs.getString("tipo"));
            s.setPreco(rs.getInt("preco"));
            
        }
        return s;        
    }
    
    public void delete(int codigo) throws SQLException, ClassNotFoundException{
        delete.setInt(1, codigo);
        delete.executeUpdate();
    }
    
    public void update(Servicos s) throws SQLException, ClassNotFoundException{
        update.setString(1, s.getTipo());
        update.setInt(2, s.getPreco());
        update.setInt(3, s.getCodigo());
        
        update.executeUpdate();       
        
    }
    
    public void insert(Servicos s) throws SQLException, ClassNotFoundException{
        insert.setInt(1, s.getCodigo());
        insert.setString(2, s.getTipo());
        insert.setInt(3, s.getPreco());
                        
        insert.executeUpdate();    
    }
    
    public List<Servicos> selectAll() throws SQLException{
        ResultSet rs = selectAll.executeQuery();
        Servicos s = null;
        List<Servicos> listaServicos = new ArrayList<Servicos>();
        while(rs.next()){
            s = new Servicos();
            s.setCodigo(rs.getInt("codigo"));
            s.setTipo(rs.getString("tipo"));
            s.setPreco(rs.getInt("preco"));
            
            listaServicos.add(s);
        }
        return listaServicos;        
    }
}