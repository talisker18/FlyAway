package com.henz.joel.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import com.henz.joel.model.Flight;

//used to compare the departure times of different flights

public class FlightComparatorByDepartureTime implements Comparator<Flight>{

	@Override
	public int compare(Flight o1, Flight o2) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date depTimeFlight1 = null;
		Date depTimeFlight2 = null;
		try {
			depTimeFlight1 = format.parse(o1.getDepartureTime());
			depTimeFlight2 = format.parse(o2.getDepartureTime());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return depTimeFlight1.compareTo(depTimeFlight2);
	}
	

}
