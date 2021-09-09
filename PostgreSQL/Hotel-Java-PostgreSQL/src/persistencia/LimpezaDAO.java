
package persistencia;

import dados.Limpeza;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LimpezaDAO {
    private static LimpezaDAO limpezaDAO = null;
    private static PreparedStatement select;
    private static PreparedStatement delete;
    private static PreparedStatement update;
    private static PreparedStatement insert;
    private static PreparedStatement selectAll;
    
    private LimpezaDAO() throws ClassNotFoundException, SQLException{
        select = DBConnection.getConexao().prepareStatement("select * from limpeza where data = ?");
        delete = DBConnection.getConexao().prepareStatement("delete from limpeza where data = ?");
        update = DBConnection.getConexao().prepareStatement("update limpeza set codQuarto = ?, codEmpregado = ? where data = ?");
        insert = DBConnection.getConexao().prepareStatement("insert into limpeza values(?, ?, ?)");
        selectAll = DBConnection.getConexao().prepareStatement("select * from limpeza order by data");
        
    }
    
    public static LimpezaDAO getInstance() throws ClassNotFoundException, SQLException{
        if (limpezaDAO == null){
            limpezaDAO = new LimpezaDAO();
        }
        return limpezaDAO;
    }
    
    public Limpeza select(Date data) throws SQLException, ClassNotFoundException{
        select.setDate(1, data);
        ResultSet rs = select.executeQuery();
        Limpeza l = null;
        if(rs.next()){
            l = new Limpeza();
            l.setData(rs.getDate("data"));
            l.setCodQuarto(rs.getInt("codQuarto"));
            l.setCodEmpregado(rs.getString("codEmpregado"));
            
        }
        return l;        
    }
    
    public void delete(Date data) throws SQLException, ClassNotFoundException{
        delete.setDate(1, data);
        delete.executeUpdate();
    }
    
    public void update(Limpeza l) throws SQLException, ClassNotFoundException{
        update.setInt(1, l.getCodQuarto());
        update.setString(2, l.getCodEmpregado());
        update.setDate(3, l.getData());
        
        update.executeUpdate();       
        
    }
    
    public void insert(Limpeza l) throws SQLException, ClassNotFoundException{
        insert.setDate(1, l.getData());
        insert.setInt(2, l.getCodQuarto());
        insert.setString(3, l.getCodEmpregado());
        insert.executeUpdate();    
    }
    
    public List<Limpeza> selectAll() throws SQLException{
        ResultSet rs = selectAll.executeQuery();
        Limpeza l = null;
        List<Limpeza> listaLimpezas = new ArrayList<Limpeza>();
        while(rs.next()){
            l = new Limpeza();
            l.setData(rs.getDate("data"));
            l.setCodQuarto(rs.getInt("codQuarto"));
            l.setCodEmpregado(rs.getString("codEmpregado"));
            
            listaLimpezas.add(l);
        }
        return listaLimpezas;        
    }
}