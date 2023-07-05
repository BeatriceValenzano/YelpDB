package it.polito.tdp.yelp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.yelp.model.Business;

public class YelpDAO {

	static Map<String, Business> businessIdMap = new HashMap<>();
//  la mappa deve essere persistente rispetto al metodo
//	mettiamo 'static' poichè viene direttamente condivisa tra tutte le istanze dello YelpDAO
	
	
	public List<Business> readBusinesses(Map<String, Business> businessIdMap/*MAPPA CHE MI CONSERVE L'ID DI UN OGGsETTO*/) {
		
//		ogni volta che troviamo un nuovo business oltre che aggiungerlo ad una lista lo metto nella mappa anche
		Connection conn;
		
		try {
			
			conn = DBConnect.getConnection(); //ogni volta che chiama getConnection() apro una nuova connessione dal DriverManager
			String sql = "SELECT * FROM business";
			List<Business> result = new ArrayList<>();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
//			Problema è cercare di non creare oggetti duplicati poichè non si ha un ottimizzazione della memoria, si spreca
//			Creo due volte lo stesso oggetto e avere lo stesso dato memorizzato in due oggetti diversi, se volessi modificarlo è un pasticcio
//			NON CREARE OGGETTI DUPLICATI PER OTTIMIZZARE LA MEMORIA E MIGLIORARE LA MODIFICA DEI DATI
			
			while(rs.next()) {
				String id = rs.getString("business_id");
				if(businessIdMap.containsKey(id)) {  //LA MAPPA MI PERMETTE DI EFFETTUARE PIù VELOCEMENTE LA RICERCA DI UN OGGETTO TRAMITE L'ID
					result.add(businessIdMap.get(id));
				} else {
					Boolean active = rs.getString("active").equals("true");  //Essendo una stringa nel DB dobbiamo pre-elaborarla
						
					Business b = new Business(id, rs.getString("full_address"), active, 
							rs.getString("categories"), rs.getString("city"), rs.getInt("review_count"), 
							rs.getString("business_name"), rs.getString("neighborhoods"), rs.getDouble("latitude"), 
							rs.getDouble("longitude"), rs.getString("state"), rs.getDouble("stars"));
					
					result.add(b);
					businessIdMap.put(b.getBusinessId(), b);
				}
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Double avarageStars(Business b){
		
		try {
			
			Connection conn = DBConnect.getConnection();
			
			String sql = "SELECT AVG(stars) AS stelle "
					+ "FROM reviews "
					+ "WHERE business_id = ?";
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, b.getBusinessId());
			ResultSet rs = st.executeQuery();
			rs.first(); 
//			mi aspetto che il result set abbia esattamente un a riga come risultato
//			non ha senso fare il ciclo while poichè ho una riga sola
			Double stelle = rs.getDouble("stelle");
			
			conn.close();  //Se dimenticassi di chiudere la connessione e quindi non la restituisco al pooling, 
//			il programma parte, ma si blocca poichè la mettiamo in attesa, destinata ad essere infinita, quindi dà errore
			
			return stelle;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
