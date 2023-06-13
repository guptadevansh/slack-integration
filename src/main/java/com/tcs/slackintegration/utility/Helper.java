package com.tcs.slackintegration.utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Helper {

	public Boolean timeUtil(String arr_time, String dep_time)
	{
		return Long.parseLong(dep_time) < Long.parseLong(arr_time);
	}
	
	public String dateFormatChanger(String time) {
		
		long tym=Long.parseLong(time);
		SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(TimeZone.getTimeZone("IST"));
        return sdf.format(new Date(tym));
	}
	
	public String pnrGenerator()
	{
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";

		StringBuilder sb = new StringBuilder(10);

		for (int i = 0; i < 10; i++) {
			int index= (int)(AlphaNumericString.length()* Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public Boolean seatsValidation(String[] seats_booked, String[] occupied) {
		
		for(String tempList: occupied)
		{
			for(String booked: seats_booked)
			{
				if(booked.equals(tempList)) 
					return false;
			}
		}
		
		return true;
	}

	public String[] updateVacantSeats(String[] seatsBooked, String[] vac_seats) {
		
		List<String> vac_seats1 = new ArrayList<String>(); 
		
		for(String str: vac_seats)
		{
			vac_seats1.add(str);
		}
		
		for(String str: seatsBooked)
		{
			vac_seats1.remove(str);
		}
		vac_seats = vac_seats1.toArray(new String[vac_seats1.size()]);
		
		return vac_seats;
	}
	
public String[] updateOccupiedSeats(String[] seatsBooked, String[] occ_seats) {
		
		List<String> occ_seats1 = new ArrayList<String>(); 
		
		for(String str: occ_seats)
		{
			occ_seats1.add(str);
		}
		
		for(String str: seatsBooked)
		{
			occ_seats1.add(str);
		}
		occ_seats = occ_seats1.toArray(new String[occ_seats1.size()]);
		
		return occ_seats;
	}
	
}
