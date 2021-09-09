
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conexao = null;
    
    private DBConnection(){
        
    }
    public static Connection getConexao() throws ClassNotFoundException, SQLException{
        if (conexao == null){
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://127.0.0.1:5432/hotel";
            String usuario = "postgres";
            String senha = "postgres";
            conexao = DriverManager.getConnection(url, usuario, senha);
        }
        return conexao;
    }
}
