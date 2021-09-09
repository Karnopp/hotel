
package persistencia;

import dados.Estadia;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstadiaDAO {
    private static EstadiaDAO estadiaDAO = null;
    private static PreparedStatement select;
    private static PreparedStatement delete;
    private static PreparedStatement update;
    private static PreparedStatement insert;
    private static PreparedStatement selectAll;
    
    private EstadiaDAO() throws ClassNotFoundException, SQLException{
        select = DBConnection.getConexao().prepareStatement("select * from estadia where codigo = ?");
        delete = DBConnection.getConexao().prepareStatement("delete from estadia where codigo = ?");
        update = DBConnection.getConexao().prepareStatement("update estadia set codQuarto = ?, dataEntrada = ?, dataSaida = ?, codReserva = ? where codigo = ?");
        insert = DBConnection.getConexao().prepareStatement("insert into estadia values(?, ?, ?, ?, ?)");
        selectAll = DBConnection.getConexao().prepareStatement("select * from estadia order by codigo");
        
    }
    
    public static EstadiaDAO getInstance() throws ClassNotFoundException, SQLException{
        if (estadiaDAO == null){
            estadiaDAO = new EstadiaDAO();
        }
        return estadiaDAO;
    }
    
    public Estadia select(int codigo) throws SQLException, ClassNotFoundException{
        select.setInt(1, codigo);
        ResultSet rs = select.executeQuery();
        Estadia e = null;
        if(rs.next()){
            e = new Estadia();
            e.setCodigo(rs.getInt("codigo"));
            e.setCodQuarto(rs.getInt("codQuarto"));
            e.setDataEntrada(rs.getDate("dataEntrada"));
            e.setDataSaida(rs.getDate("dataSaida"));
            e.setCodReserva(rs.getInt("codReserva"));
        }
        return e;        
    }
    
    public void delete(int codigo) throws SQLException, ClassNotFoundException{
        delete.setInt(1, codigo);
        delete.executeUpdate();
    }
    
    public void update(Estadia e) throws SQLException, ClassNotFoundException{
        update.setInt(1, e.getCodQuarto());
        update.setDate(2, e.getDataEntrada());
        update.setDate(3, e.getDataSaida());
        update.setInt(4, e.getCodReserva());
        update.setInt(5, e.getCodigo());
        
        update.executeUpdate();       
        
    }
    
    public void insert(Estadia e) throws SQLException, ClassNotFoundException{
        insert.setInt(1, e.getCodigo());
        insert.setInt(2, e.getCodQuarto());
        insert.setDate(3, e.getDataEntrada());
        insert.setDate(4, e.getDataSaida());
        insert.setInt(5, e.getCodReserva());
                        
        insert.executeUpdate();    
    }
    
    public List<Estadia> selectAll() throws SQLException{
        ResultSet rs = selectAll.executeQuery();
        Estadia e = null;
        List<Estadia> listaEstadias = new ArrayList<Estadia>();
        while(rs.next()){
            e = new Estadia();
            e.setCodigo(rs.getInt("codigo"));
            e.setCodQuarto(rs.getInt("codQuarto"));
            e.setDataEntrada(rs.getDate("dataEntrada"));
            e.setDataSaida(rs.getDate("dataSaida"));
            e.setCodReserva(rs.getInt("codReserva"));
            
            listaEstadias.add(e);
        }
        return listaEstadias;        
    }
}