
package persistencia;

import dados.Reserva;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private static ReservaDAO reservaDAO = null;
    private static PreparedStatement select;
    private static PreparedStatement delete;
    private static PreparedStatement update;
    private static PreparedStatement insert;
    private static PreparedStatement selectAll;
    
    private ReservaDAO() throws ClassNotFoundException, SQLException{
        select = DBConnection.getConexao().prepareStatement("select * from reserva where codigo = ?");
        delete = DBConnection.getConexao().prepareStatement("delete from reserva where codigo = ?");
        update = DBConnection.getConexao().prepareStatement("update reserva set codCliente = ?, tipoQuarto = ?, codHotel = ?, dataEntrada = ?, dataSaida = ?, aceita = ? where codigo = ?");
        insert = DBConnection.getConexao().prepareStatement("insert into reserva values(?, ?, ?, ?, ?, ?, ?)");
        selectAll = DBConnection.getConexao().prepareStatement("select * from reserva order by codigo");
        
    }
    
    public static ReservaDAO getInstance() throws ClassNotFoundException, SQLException{
        if (reservaDAO == null){
            reservaDAO = new ReservaDAO();
        }
        return reservaDAO;
    }
    
    public Reserva select(int codigo) throws SQLException, ClassNotFoundException{
        select.setInt(1, codigo);
        ResultSet rs = select.executeQuery();
        Reserva r = null;
        if(rs.next()){
            r = new Reserva();
            r.setCodigo(rs.getInt("codigo"));
            r.setCodCliente(rs.getInt("codCliente"));
            r.setTipoQuarto(rs.getInt("tipoQuarto"));
            r.setCodHotel(rs.getInt("codHotel"));
            r.setDataEntrada(rs.getDate("dataEntrada"));
            r.setDataSaida(rs.getDate("dataSaida"));
            r.setAceita(rs.getString("aceita"));
        }
        return r;        
    }
    
    public void delete(int codigo) throws SQLException, ClassNotFoundException{
        delete.setInt(1, codigo);
        delete.executeUpdate();
    }
    
    public void update(Reserva r) throws SQLException, ClassNotFoundException{
        update.setInt(1, r.getCodCliente());
        update.setInt(2, r.getTipoQuarto());
        update.setInt(3, r.getCodHotel());
        update.setDate(4, r.getDataEntrada());
        update.setDate(5, r.getDataSaida());
        update.setString(6, r.getAceita());
        update.setInt(7, r.getCodigo());
        
        update.executeUpdate();       
        
    }
    
    public void insert(Reserva r) throws SQLException, ClassNotFoundException{
        insert.setInt(1, r.getCodigo());
        insert.setInt(2, r.getCodCliente());
        insert.setInt(3, r.getTipoQuarto());
        insert.setInt(4, r.getCodHotel());
        insert.setDate(5, r.getDataEntrada());
        insert.setDate(6, r.getDataSaida());
        insert.setString(7, r.getAceita());
                        
        insert.executeUpdate();    
    }
    
    public List<Reserva> selectAll() throws SQLException{
        ResultSet rs = selectAll.executeQuery();
        Reserva r = null;
        List<Reserva> listaReservas = new ArrayList<Reserva>();
        while(rs.next()){
            r = new Reserva();
            r.setCodigo(rs.getInt("codigo"));
            r.setCodCliente(rs.getInt("codCliente"));
            r.setTipoQuarto(rs.getInt("tipoQuarto"));
            r.setCodHotel(rs.getInt("codHotel"));
            r.setDataEntrada(rs.getDate("dataEntrada"));
            r.setDataSaida(rs.getDate("dataSaida"));
            r.setAceita(rs.getString("aceita"));
            
            listaReservas.add(r);
        }
        return listaReservas;        
    }
}