package com.crm.GenericLibrary;

import java.util.Date;
import java.util.Random;

public class JavaUtility {
	/**
	 * This class consist of generic methods wrt java
	 * 
	 * @author User1
	 */

	public int getRandomNuumber() {
		Random ran = new Random();
		int rand = ran.nextInt(500);
		return rand;
	}

	/**
	 * This method will generate current system date and return it to user
	 *
	 */
	public String getSystemDate() {
		Date d = new Date();
		String date = d.toString();
		return date;
	}
	
    /**
     *  This method will generate current system date and return date in format
    */
	
	public String getSystemDateInFormat()
	{
		Date d = new Date();
		String d1 = d.toString();
		String[] date = d1.split(" ");
		
		String mon = date[1];
		String day = date[2];
		String time = date[3].replace(":", "-");
		String year = date[5];
		
		String DateFormat = day+"-"+mon+"-"+year+"-"+time;
		return DateFormat;
	}
}
