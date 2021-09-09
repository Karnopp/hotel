
package persistencia;

import dados.Extra;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ExtraDAO {
    private static ExtraDAO extraDAO = null;
    private static PreparedStatement select;
    private static PreparedStatement delete;
    private static PreparedStatement update;
    private static PreparedStatement insert;
    private static PreparedStatement selectAll;
    
    private ExtraDAO() throws ClassNotFoundException, SQLException{
        select = DBConnection.getConexao().prepareStatement("select * from extra where codEstadia = ?");
        delete = DBConnection.getConexao().prepareStatement("delete from extra where codEstadia = ? and codServico = ? and data = ? and hora = ?");
        update = DBConnection.getConexao().prepareStatement("update extra set codServico = ?, data = ?, hora = ? where codEstadia = ? and codServico = ? and data = ? and hora = ?");
        insert = DBConnection.getConexao().prepareStatement("insert into extra values(?, ?, ?, ?)");
        selectAll = DBConnection.getConexao().prepareStatement("select * from extra order by codEstadia");
        
    }
    
    public static ExtraDAO getInstance() throws ClassNotFoundException, SQLException{
        if (extraDAO == null){
            extraDAO = new ExtraDAO();
        }
        return extraDAO;
    }
    
    public List<Extra> select(int codEstadia) throws SQLException{
        select.setInt(1, codEstadia);
        ResultSet rs = select.executeQuery();
        Extra e = null;
        List<Extra> listaExtraCodEstadia = new ArrayList<Extra>();
        while(rs.next()){
            e = new Extra();
            e.setCodEstadia(rs.getInt("codEstadia"));
            e.setCodServico(rs.getInt("codServico"));
            e.setData(rs.getDate("data"));
            e.setHora(rs.getTime("hora"));
            
            listaExtraCodEstadia.add(e);
        }
        return listaExtraCodEstadia;        
    }
    
    public void delete(Extra e) throws SQLException, ClassNotFoundException{
        delete.setInt(1, e.getCodEstadia());
        delete.setInt(2, e.getCodServico());
        delete.setDate(3, e.getData());
        delete.setTime(4, e.getHora());
        delete.executeUpdate();
    }
    
    public void update(Extra antigo, Extra novo) throws SQLException, ClassNotFoundException{
        update.setInt(1, novo.getCodServico());
        update.setDate(2, novo.getData());
        update.setTime(3, novo.getHora());
        update.setInt(4, antigo.getCodEstadia());
        update.setInt(5, antigo.getCodServico());
        update.setDate(6, antigo.getData());
        update.setTime(7, antigo.getHora());
        
        update.executeUpdate();       
        
    }
    
    public void insert(Extra e) throws SQLException, ClassNotFoundException{
        insert.setInt(1, e.getCodEstadia());
        insert.setInt(2, e.getCodServico());
        insert.setDate(3, e.getData());
        insert.setTime(4, e.getHora());
        insert.executeUpdate();    
    }
    
    public List<Extra> selectAll() throws SQLException{
        ResultSet rs = selectAll.executeQuery();
        Extra e = null;
        List<Extra> listaExtras = new ArrayList<Extra>();
        while(rs.next()){
            e = new Extra();
            e.setCodEstadia(rs.getInt("codEstadia"));
            e.setCodServico(rs.getInt("codServico"));
            e.setData(rs.getDate("data"));
            e.setHora(rs.getTime("hora"));
            
            listaExtras.add(e);
        }
        return listaExtras;        
    }
}