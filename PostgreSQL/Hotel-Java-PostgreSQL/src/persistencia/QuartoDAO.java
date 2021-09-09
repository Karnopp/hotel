
package persistencia;

import dados.Quarto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuartoDAO {
    private static QuartoDAO quartoDAO = null;
    private static PreparedStatement select;
    private static PreparedStatement delete;
    private static PreparedStatement update;
    private static PreparedStatement insert;
    private static PreparedStatement selectAll;
    
    private QuartoDAO() throws ClassNotFoundException, SQLException{
        select = DBConnection.getConexao().prepareStatement("select * from quarto where codigo = ?");
        delete = DBConnection.getConexao().prepareStatement("delete from quarto where codigo = ?");
        update = DBConnection.getConexao().prepareStatement("update quarto set tipoQuarto = ?, andar = ?, numero = ?, codHotel = ? where codigo = ?");
        insert = DBConnection.getConexao().prepareStatement("insert into quarto values(?, ?, ?, ?, ?)");
        selectAll = DBConnection.getConexao().prepareStatement("select * from quarto order by codigo");
        
    }
    
    public static QuartoDAO getInstance() throws ClassNotFoundException, SQLException{
        if (quartoDAO == null){
            quartoDAO = new QuartoDAO();
        }
        return quartoDAO;
    }
    
    public Quarto select(int codigo) throws SQLException, ClassNotFoundException{
        select.setInt(1, codigo);
        ResultSet rs = select.executeQuery();
        Quarto q = null;
        if(rs.next()){
            q = new Quarto();
            q.setCodigo(rs.getInt("codigo"));
            q.setTipoQuarto(rs.getInt("tipoQuarto"));
            q.setAndar(rs.getInt("andar"));
            q.setNumero(rs.getInt("numero"));
            q.setCodHotel(rs.getInt("codHotel"));
        }
        return q;        
    }
    
    public void delete(int codigo) throws SQLException, ClassNotFoundException{
        delete.setInt(1, codigo);
        delete.executeUpdate();
    }
    
    public void update(Quarto q) throws SQLException, ClassNotFoundException{
        update.setInt(1, q.getTipoQuarto());
        update.setInt(2, q.getAndar());
        update.setInt(3, q.getNumero());
        update.setInt(4, q.getCodHotel());
        update.setInt(5, q.getCodigo());
        
        update.executeUpdate();       
        
    }
    
    public void insert(Quarto q) throws SQLException, ClassNotFoundException{
        insert.setInt(1, q.getCodigo());
        insert.setInt(2, q.getTipoQuarto());
        insert.setInt(3, q.getAndar());
        insert.setInt(4, q.getNumero());
        insert.setInt(5, q.getCodHotel());
                        
        insert.executeUpdate();    
    }
    
    public List<Quarto> selectAll() throws SQLException{
        ResultSet rs = selectAll.executeQuery();
        Quarto q = null;
        List<Quarto> listaQuartos = new ArrayList<Quarto>();
        while(rs.next()){
            q = new Quarto();
            q.setCodigo(rs.getInt("codigo"));
            q.setTipoQuarto(rs.getInt("tipoQuarto"));
            q.setAndar(rs.getInt("andar"));
            q.setNumero(rs.getInt("numero"));
            q.setCodHotel(rs.getInt("codHotel"));
            
            listaQuartos.add(q);
        }
        return listaQuartos;        
    }
}