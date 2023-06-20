package com.generic.utilities;

import java.util.Date;
import java.util.Random;

public class JavaUtility {

	/**
	 * This method generate random number & return it to caller
	 * 
	 * @return
	 */
	public int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(1000);
	}

	/**
	 * This method get system date
	 * 
	 * @return
	 */
	public String getSystemDate() {
		Date date = new Date();
		return date.toString();
	}

	/**
	 * This method will generate system date in required format
	 * 
	 * @return
	 */
	public String getSystemDateInFormate() {
		Date d = new Date();
		String[] split = d.toString().split(" ");
		String date = split[2];
		String month = split[1];
		String year = split[5];
		String time = split[3].replace(":", "-");
		return date + " " + month + " " + year + " " + time;
	}

}
