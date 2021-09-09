package persistencia;


import com.mongodb.MongoClient;

import java.net.UnknownHostException;



public class Connection {
	private static MongoClient client = null;
        
        public static MongoClient getConexao() throws UnknownHostException{
            client = new MongoClient("localhost", 27017);
            return client;
        }
	

}
