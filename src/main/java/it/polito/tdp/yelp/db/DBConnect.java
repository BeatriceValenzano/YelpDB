package it.polito.tdp.yelp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class DBConnect {
//	public static Connection getConnection() throws SQLException {
//		String jdbcURL = "jdbc:mariadb://localhost/yelp?user=root&password=root" ;
//		Connection conn = DriverManager.getConnection(jdbcURL) ;
//		return conn;
//	}
	
//	VEDIAMO COSA SUCCEDE AL TEMPO SE INVECE DI CHIAMARE 
//	OGNI VOLTA LA getConnection() usiamo la connection pooling
	
	
//	SI USA QUESTO, NON IL DRIVER MANAGER
	
	static HikariDataSource dataSource;  //singleton ossia un oggetto di cui può esisterne un'istanza sola
//	devo far sì che non si possa fare due volte una new della dataSource
//	usiamo la libreria HikariCP che a livello di codice funziona in modo molto semplice
//	si ha un oggeto di tipo dataSource da creare ed inizializzare con i dati della connessione 
//	Una volta creato il dataSource (fatto una volta sola) posso chiamare quante volte voglio getConnection()
	
	public static Connection getConnection() throws SQLException{
		if(dataSource == null) {
//			crea la data source
			dataSource = new HikariDataSource();
			dataSource.setJdbcUrl("jdbc:mariadb://localhost/yelp");
			dataSource.setUsername("root");
			dataSource.setPassword("Valenzano11!");
		}
		
		return dataSource.getConnection();
		
//		L'abbiamo fatto perchè riduce draticamente il tempo di risoluzione della query
//		Siamo scesi da 11000 a 2000 ms per il tempo di esecuzione della query
//		ciò vuol dire che degli 11000 ms, 9000 ms venivano usati per fare open e close di connessioni verso il db
//		e 2000 ms venivano effettivamente utilizzati per fare l'esecuzione delle query

		
	}
}
