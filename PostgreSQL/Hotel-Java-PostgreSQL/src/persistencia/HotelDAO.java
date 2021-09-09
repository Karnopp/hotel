
package persistencia;

import dados.Hotel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private static HotelDAO hotelDAO = null;
    private static PreparedStatement select;
    private static PreparedStatement delete;
    private static PreparedStatement update;
    private static PreparedStatement insert;
    private static PreparedStatement selectAll;
    
    private HotelDAO() throws ClassNotFoundException, SQLException{
        select = DBConnection.getConexao().prepareStatement("select * from hotel where codigo = ?");
        delete = DBConnection.getConexao().prepareStatement("delete from hotel where codigo = ?");
        update = DBConnection.getConexao().prepareStatement("update hotel set nome = ?, telefone = ?, codEndereco = ? where codigo = ?");
        insert = DBConnection.getConexao().prepareStatement("insert into hotel values(?, ?, ?, ?)");
        selectAll = DBConnection.getConexao().prepareStatement("select * from hotel order by codigo");
        
    }
    
    public static HotelDAO getInstance() throws ClassNotFoundException, SQLException{
        if (hotelDAO == null){
            hotelDAO = new HotelDAO();
        }
        return hotelDAO;
    }
    
    public Hotel select(int codigo) throws SQLException, ClassNotFoundException{
        select.setInt(1, codigo);
        ResultSet rs = select.executeQuery();
        Hotel h = null;
        if(rs.next()){
            h = new Hotel();
            h.setCodigo(rs.getInt("codigo"));
            h.setNome(rs.getString("nome"));
            h.setTelefone(rs.getString("telefone"));
            h.setCodEndereco(rs.getInt("codEndereco"));
        }
        return h;        
    }
    
    public void delete(int codigo) throws SQLException, ClassNotFoundException{
        delete.setInt(1, codigo);
        delete.executeUpdate();
    }
    
    public void update(Hotel h) throws SQLException, ClassNotFoundException{
        update.setString(1, h.getNome());
        update.setString(2, h.getTelefone());
        update.setInt(3, h.getCodEndereco());
        update.setInt(4, h.getCodigo());
        
        update.executeUpdate();       
        
    }
    
    public void insert(Hotel h) throws SQLException, ClassNotFoundException{
        insert.setInt(1, h.getCodigo());
        insert.setString(2, h.getNome());
        insert.setString(3, h.getTelefone());
        insert.setInt(4, h.getCodEndereco());
                
        insert.executeUpdate();    
    }
    
    public List<Hotel> selectAll() throws SQLException{
        ResultSet rs = selectAll.executeQuery();
        Hotel h = null;
        List<Hotel> listaHoteis = new ArrayList<Hotel>();
        while(rs.next()){
            h = new Hotel();
           h.setCodigo(rs.getInt("codigo"));
            h.setNome(rs.getString("nome"));
            h.setTelefone(rs.getString("telefone"));
            h.setCodEndereco(rs.getInt("codEndereco"));
            
            listaHoteis.add(h);
        }
        return listaHoteis;        
    }
}