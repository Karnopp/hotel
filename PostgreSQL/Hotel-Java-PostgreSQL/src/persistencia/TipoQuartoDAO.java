
package persistencia;

import dados.TipoQuarto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoQuartoDAO {
    private static TipoQuartoDAO tipoQuartoDAO = null;
    private static PreparedStatement select;
    private static PreparedStatement delete;
    private static PreparedStatement update;
    private static PreparedStatement insert;
    private static PreparedStatement selectAll;
    
    private TipoQuartoDAO() throws ClassNotFoundException, SQLException{
        select = DBConnection.getConexao().prepareStatement("select * from tipoQuarto where codigo = ?");
        delete = DBConnection.getConexao().prepareStatement("delete from tipoQuarto where codigo = ?");
        update = DBConnection.getConexao().prepareStatement("update tipoQuarto set tipo = ?, preco = ?, camaExtra = ? where codigo = ?");
        insert = DBConnection.getConexao().prepareStatement("insert into tipoQuarto values(?, ?, ?, ?)");
        selectAll = DBConnection.getConexao().prepareStatement("select * from tipoQuarto order by codigo");
        
    }
    
    public static TipoQuartoDAO getInstance() throws ClassNotFoundException, SQLException{
        if (tipoQuartoDAO == null){
            tipoQuartoDAO = new TipoQuartoDAO();
        }
        return tipoQuartoDAO;
    }
    
    public TipoQuarto select(int codigo) throws SQLException, ClassNotFoundException{
        select.setInt(1, codigo);
        ResultSet rs = select.executeQuery();
        TipoQuarto t = null;
        if(rs.next()){
            t = new TipoQuarto();
            t.setCodigo(rs.getInt("codigo"));
            t.setTipo(rs.getString("tipo"));
            t.setPreco(rs.getInt("preco"));
            t.setCamaExtra(rs.getString("camaExtra"));
            
        }
        return t;        
    }
    
    public void delete(int codigo) throws SQLException, ClassNotFoundException{
        delete.setInt(1, codigo);
        delete.executeUpdate();
    }
    
    public void update(TipoQuarto t) throws SQLException, ClassNotFoundException{
        update.setString(1, t.getTipo());
        update.setInt(2, t.getPreco());
        update.setString(3, t.getCamaExtra());
        update.setInt(4, t.getCodigo());
        
        update.executeUpdate();       
        
    }
    
    public void insert(TipoQuarto t) throws SQLException, ClassNotFoundException{
        insert.setInt(1, t.getCodigo());
        insert.setString(2, t.getTipo());
        insert.setInt(3, t.getPreco());
        insert.setString(4, t.getCamaExtra());
                        
        insert.executeUpdate();    
    }
    
    public List<TipoQuarto> selectAll() throws SQLException{
        ResultSet rs = selectAll.executeQuery();
        TipoQuarto t = null;
        List<TipoQuarto> listaTipoQuartos = new ArrayList<TipoQuarto>();
        while(rs.next()){
            t = new TipoQuarto();
            t.setCodigo(rs.getInt("codigo"));
            t.setTipo(rs.getString("tipo"));
            t.setPreco(rs.getInt("preco"));
            t.setCamaExtra(rs.getString("camaExtra"));
            
            listaTipoQuartos.add(t);
        }
        return listaTipoQuartos;        
    }
}