// Copyright (C),2005-2006 HandCoded Software Ltd.
// All rights reserved.
//
// This software is licensed in accordance with the terms of the 'Open Source
// License (OSL) Version 3.0'. Please see 'license.txt' for the details.
//
// HANDCODED SOFTWARE LTD MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
// SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT
// LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
// PARTICULAR PURPOSE, OR NON-INFRINGEMENT. HANDCODED SOFTWARE LTD SHALL NOT BE
// LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
// OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.

package com.handcoded.finance;

import java.io.Serializable;
import java.util.Calendar;

/**
 * The <CODE>Date</CODE> class implements an immutable date value. The standard
 * Java date classes allow components (e.g. day, month, year) to be modified
 * making instances difficult to reuse safely.
 * <P>
 * The implementation of this class is based on the the C++ code in QuantLib
 * and only covers years between 1900 and 2099. The code emulates a bug in
 * Microsoft Excel that erroreously indicates 1900 as a leap year but this is
 * unlikely to be an issue in most applications.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class Date implements Comparable, Serializable
{
	/**
	 * A constant value indicating the weekday Sunday.
 	 * @since	TFP 1.0
	 */
	public static final int		SUNDAY		= 1;
	
	/**
	 * A constant value indicating the weekday Monday.
	 * @since	TFP 1.0
	 */
	public static final int		MONDAY		= 2;
	
	/**
	 * A constant value indicating the weekday Tuesday.
	 * @since	TFP 1.0
	 */
	public static final int		TUESDAY		= 3;
	
	/**
	 * A constant value indicating the weekday Wednesday.
	 * @since	TFP 1.0
	 */
	public static final int		WEDNESDAY	= 4;
	
	/**
	 * A constant value indicating the weekday Thursday.
	 * @since	TFP 1.0
	 */
	public static final int		THURSDAY	= 5;
	
	/**
	 * A constant value indicating the weekday Friday.
	 * @since	TFP 1.0
	 */
	public static final int		FRIDAY		= 6;
	
	/**
	 * A constant value indicating the weekday Saturday.
	 * @since	TFP 1.0
	 */
	public static final int		SATURDAY	= 7;
	
	/**
	 * The earliest possible date that can be correctly represented,
	 * @since	TFP 1.0
	 */
	public static final Date	MIN_VALUE	= new Date (367);
	
	/**
	 * The latest possible date that can be correctly represented.
	 * @since	TFP 1.0
	 */
	public static final Date	MAX_VALUE	= new Date (73050);
	
	/**
	 * Parses a <CODE>Date</CODE> instance from a character string in the
	 * format YYYY-MM-DD (as produced by <CODE>toString</CODE>).
	 *
	 * @param	text			The text string to be parsed.
	 * @return	A new <CODE>Date</CODE> instance containing the parsed data.
	 *
	 * @throws	IllegalArgumentException If the character string is not in the
	 *			correct format.
	 * @since	TFP 1.0
	 */
	public static Date parse (String text)
	{
		if ((text = text.trim ()).matches ("[1-2]\\d{3}-[0-1]\\d-[0-3]\\d")) {
			String [] parts = text.split ("-");
			
			return (new Date (
				Integer.parseInt (parts [2]),
				Integer.parseInt (parts [1]),
				Integer.parseInt (parts [0])));
		}
		else
			throw new IllegalArgumentException ("Date in not in YYYY-MM-DD format");
	}
	
	/**
	 * Creates a <CODE>Date</CODE> instance initialised with the current date.
	 * 
	 * @return	The current date.
	 * @since	TFP 1-0
	 */
	public static Date now ()
	{
		Calendar	calendar = Calendar.getInstance ();
	
		return (new Date (calendar.get (Calendar.DAY_OF_MONTH),
						  calendar.get (Calendar.MONTH) - Calendar.JANUARY + 1,
						  calendar.get (Calendar.YEAR)));
	}
		
	/**
	 * Constructs a <CODE>Date</CODE> instance given a day, month and year.
	 *
	 * @param	day				The day of the month (1-31).
	 * @param	month			The month of the year (1-12).
	 * @param	year			The year (1900-2099).
	 * @since	TFP 1.0
	 */
	public Date (final int day, final int month, final int year)
	{
		this (day + monthOffset (month, isLeapYear (year)) + yearOffset (year));
	}

	/**
	 * Determines if a year is a leap year.
	 *
	 * @param	year			The year to test (1900-2099)
	 * @return	<CODE>true</CODE> if the year is a leap year, <CODE>false</CODE>
	 *			otherwise.
	 * @since	TFP 1.0
	 */
	public static boolean isLeapYear (final int	year)
	{
		return (leapYears [year - 1900]);
	}
	
	/**
	 * Returns the number of days in the given month in a leap or non-leap
	 * year.
	 *
	 * @param	month			The month number (1-12).
	 * @param	leapYear		Flag to indicate a leap year.
	 * @return	The length of the given month in the indicated type of year.
	 * @since	TFP 1.0
	 */
	public static int monthLength (final int month, final boolean leapYear)
	{
		return (((leapYear) ? leapMonthLength : monthLength)[month - 1]);
	}
	
	/**
	 * Returns the number of days in the given month for the specified year.
	 *
	 * @param	month			The month number (1-12).
	 * @param	year			The year (1900-2099).
	 * @return	The length of the given month in the specified year.
	 * @since	TFP 1.0
	 */
	public static int monthLength (final int month, final int year)
	{
		return (monthLength (month, isLeapYear (year)));
	}
	
	/**
	 * Calculates the day of the week on which a date falls.
	 *
	 * @return	The weekday (Sunday = 1, Monday = 2, ..., Saturday = 7).
	 * @see 	#SUNDAY
	 * @see 	#MONDAY
	 * @see 	#TUESDAY
	 * @see 	#WEDNESDAY
	 * @see 	#THURSDAY
	 * @see 	#FRIDAY
	 * @see 	#SATURDAY
	 * @since	TFP 1.0
	 */
	public int weekday ()
	{
		return (1 + (date + 6) % 7);
	}
	
	/**
	 * Calculates the day of the month on which the <CODE>Date</CODE> falls.
	 *
	 * @return	The day of the month (1-31).
	 * @since	TFP 1.0
	 */
	public int dayOfMonth ()
	{
		return (dayOfYear () - monthOffset (month (), isLeapYear (year ())));
	}
	
	/**
	 * Calculates the number of the last day in the month in which the <CODE>
	 * Date</CODE> falls.
	 *
	 * @return 	The last day in the month (28-31)
	 * @since	TFP 1.0
	 */
	public int lastDayOfMonth ()
	{
		return (monthLength (month (), isLeapYear (year ())));
	}
	
	/**
	 * Determines in the <CODE>Date</CODE> falls on the last day of the month.
	 *
	 * @return	<CODE>true</CODE> if the <CODE>Date</CODE> represents the last
	 *			day in a month, <CODE>false</CODE> otherwise.
	 * @since	TFP 1.0
	 */
	public boolean isEndOfMonth ()
	{
		return (dayOfMonth () == lastDayOfMonth ());
	}
	
	/**
	 * Calculates the day of the year represented by the current <CODE>Date
	 * </CODE>.
	 *
	 * @return 	The day of the year (1-366).
	 * @since	TFP 1.0
	 */
	public int dayOfYear ()
	{
		return (date - yearOffset (year ()));
	}
	
	/**
	 * Calculates the month of the year in which the current <CODE>Date</CODE>
	 * falls.
	 *
	 * @return 	The month of the year (1-12).
	 * @since	TFP 1.0
	 */
	public int month ()
	{
		int			day 	= dayOfYear ();
		int			month 	= day / 30 + 1;
		boolean		leap	= isLeapYear (year ());
		
		while (day <= monthOffset (month, leap)) --month;
		while (day >  monthOffset (month + 1, leap)) ++month;
		
		return (month);
	}	
	
	/**
	 * Calculates the year in which the current <CODE>Date</CODE> falls.
	 *
	 * @return	The year (1900 - 2099).
	 * @since	TFP 1.0
	 */
	public int year ()
	{
		int			year 	= (date / 365) + 1900;
		
		if (date <= yearOffset (year)) --year;
		return (year);
	}
	
	/**
	 * Creates a new <CODE>Date</CODE> based on the current instance and a given
	 * number of days adjustment.
	 *
	 * @param	days			The number of days to adjust by.
	 * @return	The adjusted date.
	 * @since	TFP 1.0
	 */
	public Date plusDays (final int days)
	{
		return (new Date (date + days));
	}
	
	/**
	 * Creates a new <CODE>Date</CODE> based on the current instance and a given
	 * number of weeks adjustment.
	 *
	 * @param	weeks			The number of weeks to adjust by.
	 * @return	The adjusted date.
	 * @since	TFP 1.0
	 */
	public Date plusWeeks (final int weeks)
	{
		return (new Date (date + 7 * weeks));
	}
	
	/**
	 * Creates a new <CODE>Date</CODE> based on the current instance and a given
	 * number of months adjustment.
	 *
	 * @param	months			The number of months to adjust by.
	 * @return	The adjusted date.
	 * @since	TFP 1.0
	 */
	public Date plusMonths (final int months)
	{
		int			m		= month () + months;
		int			y		= year ();
		
		while (m < 1) {
			m += 12;
			y -= 1;
		}
		while (m > 12) {
			m -= 12;
			y += 1;
		}
		
		return (new Date (dayOfMonth (), m, y));
	}
	
	/**
	 * Creates a new <CODE>Date</CODE> based on the current instance and a given
	 * number of years adjustment.
	 *
	 * @param	years 			The number of years to adjust by.
	 * @return	The adjusted date.
	 * @since	TFP 1.0
	 */
	public Date plusYears (final int years)
	{
		return (new Date (dayOfMonth (), month (), year () + years));
	}
	
	/**
	 * Creates a new <CODE>Date</CODE> based on the current instance and a given
	 * <CODE>Interval</CODE>.
	 *
	 * @param	interval		The time <CODE>Interval</CODE>.
	 * @return	The adjusted date.
	 * @since	TFP 1.0
	 */
	public Date plus (Interval interval)
	{
		int			multiplier	= interval.getMultiplier ();
		Period		period 		= interval.getPeriod ();
		
		if (period == Period.DAY)
			return (plusDays (multiplier));
		else if (period == Period.WEEK)
			return (plusWeeks (multiplier));
		else if (period == Period.MONTH)
			return (plusMonths (multiplier));
		else if (period == Period.YEAR)
			return (plusYears (multiplier));
		else
			return (null);
	}
		
	/**
	 * Returns the hash value of the date for hash based data structures and
	 * algorithms.
	 *
	 * @return	The hash value for the date.
	 * @since	TFP 1.0
	 */
	public int hashCode ()
	{
		return (date);
	}
	
	/**
	 * Determines if this <CODE>Date</CODE> instance and another hold the same
	 * date.
	 *
	 * @param	other			The <CODE>Date</CODE> instance to compare with.
	 * @return	<CODE>true</CODE> if both instances represent the same date,
	 *			<CODE>false</CODE> otherwise.
	 * @since	TFP 1.0
	 */
	public boolean equals (final Object	other)
	{
		return ((other instanceof Date) && (date == ((Date) other).date));
	}
	
	/**
	 * Returns the result of comparing this instance to another <CODE>Date
	 * </CODE>.
	 *
	 * @param	other			The <CODE>Date</CODE> instance to compare with.
	 * @return	An integer value indicating the relative ordering.
	 * @since	TFP 1.0
	 */
	public int compareTo (final Date other)
	{
		return (date - other.date);
	}
	
	/**
	 * Returns the result of comparing this instance to another <CODE>Object
	 * </CODE>.
	 *
	 * @param	other			The <CODE>Object</CODE> instance to compare with.
	 * @return	An integer value indicating the relative ordering.
	 * @throws	ClassCastException If the argument is not a <CODE>Date</CODE>
	 *			instance.
	 * @since	TFP 1.0
	 */
	public int compareTo (final Object other)
	{
		return (compareTo ((Date) other));
	}
	
	/**
	 * Converts the instance data members to a <CODE>String</CODE> representation
	 * that can be displayed for debugging purposes.
	 *
	 * @return 	The object's <CODE>String</CODE> representation.
	 * @since	TFP 1.0
	 */
	public String toString ()
	{
		int				year	= year (); 
		int				month	= month ();
		int				day		= dayOfMonth ();
		
		synchronized (buffer) {
			buffer.setLength (0);
			
			buffer.append (year);
			buffer.append ('-');
			if (month < 10)
				buffer.append ('0');
			buffer.append (month);
			buffer.append ('-');
			if (day < 10)
				buffer.append ('0');
			buffer.append (day);
			
			return (buffer.toString ());
		}
	}
	
	/**
	 * Constructs a <CODE>Date</CODE> instance given a date number.
	 *
	 * @param	date			The initial date value.
	 * @since	TFP 1.0
	 */
	protected Date (final int date)
	{
		this.date = date;
	}
	
	/**
	 * Serialization UID
	 * @since	TFP 1.0
	 */
	private static final long serialVersionUID = -5790893719145834093L;

	/**
	 * A static buffer used to produce the <CODE>toString</CODE> value. The
	 * buffer must be synchronized before use to ensure thread safety.
	 * @since	TFP 1.0
	 */
	private static final StringBuffer	buffer = new StringBuffer ();
	
	/**
	 * Lookup table of leap years.
	 * @since	TFP 1.0
	 */
	private static final boolean leapYears [] = {
        // 1900-1909
		true,false,false,false, true,false,false,false, true,false,
        // 1910-1919
        false,false, true,false,false,false, true,false,false,false,
        // 1920-1929
        true,false,false,false, true,false,false,false, true,false,
        // 1930-1939
        false,false, true,false,false,false, true,false,false,false,
        // 1940-1949
        true,false,false,false, true,false,false,false, true,false,
        // 1950-1959
        false,false, true,false,false,false, true,false,false,false,
        // 1960-1969
        true,false,false,false, true,false,false,false, true,false,
        // 1970-1979
        false,false, true,false,false,false, true,false,false,false,
        // 1980-1989
        true,false,false,false, true,false,false,false, true,false,
        // 1990-1999
        false,false, true,false,false,false, true,false,false,false,
        // 2000-2009
        true,false,false,false, true,false,false,false, true,false,
        // 2010-2019
        false,false, true,false,false,false, true,false,false,false,
        // 2020-2029
        true,false,false,false, true,false,false,false, true,false,
        // 2030-2039
        false,false, true,false,false,false, true,false,false,false,
        // 2040-2049
        true,false,false,false, true,false,false,false, true,false,
        // 2050-2059
        false,false, true,false,false,false, true,false,false,false,
        // 2060-2069
        true,false,false,false, true,false,false,false, true,false,
        // 2070-2079
        false,false, true,false,false,false, true,false,false,false,
        // 2080-2089
        true,false,false,false, true,false,false,false, true,false,
        // 2090-2099
        false,false, true,false,false,false, true,false,false,false,
        // 2100
        false
    };
        
    /**
     * Normal year month lengths
	 * @since	TFP 1.0
     */
  	private static final int	monthLength [] = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    /**
     * Leap year month lengths
	 * @since	TFP 1.0
     */
	private static final int 	leapMonthLength [] = {
        31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };
    
    /**
     * Normal year month offsets
	 * @since	TFP 1.0
     */
  	private static final int 	monthOffset [] = {
          0,  31,  59,  90, 120, 151,   // Jan - Jun
       	181, 212, 243, 273, 304, 334,   // Jun - Dec
    	365     // used in dayOfMonth to bracket day
    };
    
    /**
     * Leap yeap month offsets
	 * @since	TFP 1.0
     */
    private static final int 	leapMonthOffset [] = {
          0,  31,  60,  91, 121, 152,   // Jan - Jun
        182, 213, 244, 274, 305, 335,   // Jun - Dec
     	366     // used in dayOfMonth to bracket day
    };
    
    /**
     * Loopup table of year offsets based on the day number of December 31st in
     * the preceding year.
     * <P>
     * e.g. for 1901 yearOffset[1] is 366, that is, December 31 1900
	 * @since	TFP 1.0
     */
    private static final int 	yearOffset [] = {
    	// 1900-1909
            0,  366,  731, 1096, 1461, 1827, 2192, 2557, 2922, 3288,
        // 1910-1919
         3653, 4018, 4383, 4749, 5114, 5479, 5844, 6210, 6575, 6940,
        // 1920-1929
         7305, 7671, 8036, 8401, 8766, 9132, 9497, 9862,10227,10593,
        // 1930-1939
        10958,11323,11688,12054,12419,12784,13149,13515,13880,14245,
        // 1940-1949
        14610,14976,15341,15706,16071,16437,16802,17167,17532,17898,
        // 1950-1959
        18263,18628,18993,19359,19724,20089,20454,20820,21185,21550,
        // 1960-1969
        21915,22281,22646,23011,23376,23742,24107,24472,24837,25203,
        // 1970-1979
        25568,25933,26298,26664,27029,27394,27759,28125,28490,28855,
        // 1980-1989
        29220,29586,29951,30316,30681,31047,31412,31777,32142,32508,
        // 1990-1999
        32873,33238,33603,33969,34334,34699,35064,35430,35795,36160,
        // 2000-2009
        36525,36891,37256,37621,37986,38352,38717,39082,39447,39813,
        // 2010-2019
        40178,40543,40908,41274,41639,42004,42369,42735,43100,43465,
        // 2020-2029
        43830,44196,44561,44926,45291,45657,46022,46387,46752,47118,
        // 2030-2039
        47483,47848,48213,48579,48944,49309,49674,50040,50405,50770,
        // 2040-2049
        51135,51501,51866,52231,52596,52962,53327,53692,54057,54423,
        // 2050-2059
        54788,55153,55518,55884,56249,56614,56979,57345,57710,58075,
        // 2060-2069
        58440,58806,59171,59536,59901,60267,60632,60997,61362,61728,
        // 2070-2079
        62093,62458,62823,63189,63554,63919,64284,64650,65015,65380,
        // 2080-2089
        65745,66111,66476,66841,67206,67572,67937,68302,68667,69033,
        // 2090-2099
        69398,69763,70128,70494,70859,71224,71589,71955,72320,72685,
        // 2100
        73050
    };

	/**
	 * The date expressed as the number of days since 1st Jan 1900
	 * @since	TFP 1.0
	 */
	private int					date;
	
	/**
	 * Returns the offset from the start of the year in days of the given month
	 * in a leap or non-leap year.
	 * <P>
	 * This function is not exposed publically as it reveals the implementation.
	 *
	 * @param	month			The month number (1-12).
	 * @param	leapYear		Flag to indicate a leap year.
	 * @return	The length of the given month in the indicated type of year.
	 * @since	TFP 1.0
	 */
	private static int monthOffset (final int month, final boolean leapYear)
	{
		return (((leapYear) ? leapMonthOffset : monthOffset)[month - 1]);
	}
	
	/**
	 * Returns the day count offset for the given year.
	 *
	 * @param	year			The year (1900-2099)
	 * @return 	The day offset value.
	 * @since	TFP 1.0
	 */
	private static int yearOffset (final int year)
	{
		return (yearOffset [year - 1900]);
	}
}