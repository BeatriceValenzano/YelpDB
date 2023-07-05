package it.polito.tdp.yelp.model;

import java.util.*;

import it.polito.tdp.yelp.db.YelpDAO;

public class Model {

	List<Business> businesses;  //per mantenere l'ordine
	Map<String, Business> businessIdMap;  //per facilitare l'accesso per chiave
	
	public Model() {
		YelpDAO dao = new YelpDAO();
		this.businessIdMap = new HashMap<>();
		this.businesses = dao.readBusinesses(businessIdMap);
		
		/*
		for(Business b : this.businesses) {
			businessIdMap.put(b.getBusinessId(), b);
		}*/
	}
}
