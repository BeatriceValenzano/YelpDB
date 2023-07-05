package it.polito.tdp.yelp.db;

import java.util.*;

import it.polito.tdp.yelp.model.Business;

public class TestDAO {

	public static void main(String[] args) {
		YelpDAO dao = new YelpDAO();
		
		Map<String, Business> businessIdMap = new HashMap<>();
		List<Business> businesses = dao.readBusinesses(businessIdMap);
		
		System.out.println(businesses.size());
		System.out.println(businesses.get(0).toString());
	
		List<Double> stelle = new ArrayList<>();
		long startTime = System.nanoTime();
		for(Business b : businesses) {
			Double num = dao.avarageStars(b); //ogni volta che chiama averageStar() chiama la getConncetion();
			stelle.add(num);
		}
		long endTime = System.nanoTime();
		System.out.println(stelle.size());
		System.out.println(stelle.get(0));
		System.out.println("Time = " + (endTime - startTime)/1e6 + " ms");
	}

}