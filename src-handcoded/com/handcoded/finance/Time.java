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

/**
 * The <CODE>Time</CODE> class provides an immutable representation for a
 * simple time value accurate to seconds.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class Time implements Comparable, Serializable
{
	/**
	 * Parses a character string in the format HH:MM:SS and uses the extracted
	 * values to construct a <CODE>Time</CODE> instance.
	 *
	 * @param	text			The character string to be parsed.
	 * @return	A <CODE>Time</CODE> instance constructed from the parsed data.
	 *
	 * @throws	IllegalArgumentException If the character string is not in the
	 *			correct format. 
	 * @since	TFP 1.0
	 */
	public static Time parse (final String text)
	{
		if (text.matches ("[0-2]\\d:[0-5]\\d:[0-5]\\d")) {
			String [] parts = text.split (":");
			
			return (new Time (
				Integer.parseInt (parts [0]),
				Integer.parseInt (parts [1]),
				Integer.parseInt (parts [2])));
		}
		else
			throw new IllegalArgumentException ("Time is not in HH:MM:SS format");
	}
	
	/**
	 * Constructs a <CODE>Time</CODE> instance based on the supplied hour and
	 * minute values.
	 *
	 * @param	hours			The number of hours (0-23)
	 * @param	minutes			The number of minutes (0-59)
	 * @since	TFP 1.0
	 */
	public Time (int hours, int minutes)
	{
		this (hours, minutes, 0);	
	}
	
	/**
	 * Constructs a <CODE>Time</CODE> instance based on the supplied hour,
	 * minute and seconds values.
	 *
	 * @param	hours			The number of hours (0-23)
	 * @param	minutes			The number of minutes (0-59)
	 * @param 	seconds			The number of seconds (0-59)
	 * @since	TFP 1.0
	 */
	public Time (int hours, int minutes, int seconds)
	{
		time = 60 * (60 * hours + minutes) + seconds;
	}
	
	/**
	 * Provides access to the hours component of the time value.
	 *
	 * @return	The hours component of the time.
	 * @since	TFP 1.0
	 */
	public int hours ()
	{
		return ((time / 3600) % 24);
	}
	
	/**
	 * Provides access to the minutes component of the time value.
	 *
	 * @return	The minutes component of the time.
	 * @since	TFP 1.0
	 */
	public int minutes ()
	{
		return ((time / 60) % 60);
	}
	
	/**
	 * Provides access to the seconds component of the time value.
	 *
	 * @return	The seconds component of the time.
	 * @since	TFP 1.0
	 */
	public int seconds ()
	{
		return (time % 60);
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
		return (time);
	}
	
	/**
	 * Determines if this <CODE>Time</CODE> instance and another hold the same
	 * time.
	 *
	 * @param	other			The <CODE>Time</CODE> instance to compare with.
	 * @return	<CODE>true</CODE> if both instances represent the same date,
	 *			<CODE>false</CODE> otherwise.
	 * @since	TFP 1.0
	 */
	public boolean equals (final Object other)
	{
		return ((other instanceof Time) && (time == ((Time) other).time));
	}

	/**
	 * Returns the result of comparing this instance to another <CODE>Time
	 * </CODE>.
	 *
	 * @param	other			The <CODE>Time</CODE> instance to compare with.
	 * @return	An integer value indicating the relative ordering.
	 * @since	TFP 1.0
	 */
	public int compareTo (final Time other)
	{
		return (time - other.time);
	}
	
	/**
	 * Returns the result of comparing this instance to another <CODE>Object
	 * </CODE>.
	 *
	 * @param	other			The <CODE>Object</CODE> instance to compare with.
	 * @return	An integer value indicating the relative ordering.
	 * @throws	ClassCastException If the argument is not a <CODE>Time</CODE>
	 *			instance.
	 * @since	TFP 1.0
	 */
	public int compareTo (final Object other)
	{
		return (compareTo ((Time) other));
	}	
	
	/**
	 * Converts the value of this <CODE>Time</CODE> instance into a
	 * <CODE>String</CODE> for display.
	 *
	 * @return	A <CODE>String</CODE> in the format HH:MM:SS representing the
	 *			<CODE>Time</CODE> value.
	 * @since	TFP 1.0
	 */
	public String toString ()
	{
		int			hours	= hours ();
		int			minutes	= minutes ();
		int			seconds = seconds ();
		
		synchronized (buffer) {
			buffer.setLength (0);
			
			if (hours < 10)
				buffer.append ('0');
			buffer.append (hours);
			buffer.append (':');
			if (minutes < 10)
				buffer.append ('0');
			buffer.append (minutes);
			buffer.append (':');
			if (minutes < 10)
				buffer.append ('0');
			buffer.append (seconds);
			
			return (buffer.toString ());
		}
	}
	
	/**
	 * Serialization UID
	 * @since	TFP 1.0
	 */
	private static final long serialVersionUID = -1184154490188640058L;

	/**
	 * A static buffer used to produce the <CODE>toString</CODE> value. The
	 * buffer must be synchronized before use to ensure thread safety.
	 * @since	TFP 1.0
	 */
	private static final StringBuffer	buffer = new StringBuffer ();
	
	/**
	 * The time as a number of seconds from midnight.
	 * @since	TFP 1.0
	 */
	private int		time;
}