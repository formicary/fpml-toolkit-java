// Copyright (C),2005-2007 HandCoded Software Ltd.
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

package com.handcoded.xml;

import java.math.BigDecimal;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.handcoded.finance.Date;
import com.handcoded.finance.DateTime;
import com.handcoded.finance.Time;

/**
 * The <CODE>Types</CODE> class contains a set of functions for extracting
 * Java type values from XML <CODE>Element</CODE> content text strings.
 * <P>
 * Much of this code has been refactored from the <CODE>Logic</CODE> class.
 * 
 * @author 	BitWise
 * @version	$Id$
 * @since	TFP 1.1
 */
public abstract class Types
{
	/**
	 * Returns the value of the given <CODE>Node</CODE> as a string.
	 *
	 * @param 	node			The <CODE>Node</CODE> containing the value.
	 * @return	The value of the node as a Java datatype.
	 * @since	TFP 1.0
	 */
	public static String toString (final Node node)
	{
		return (DOM.getInnerText ((Element) node).trim ());
	}

	/**
	 * Returns the value of the given <CODE>Node</CODE> as an boolean.
	 * 
	 * @param 	node			The <CODE>Node</CODE> containing the value.
	 * @return	The value of the node as a Java datatype.
	 * @since	TFP 1.0
	 */
	public static boolean toBool (final Node node)
	{
		try {
			return (Boolean.parseBoolean (toString (node)));
		}
		catch (Exception error) {
			;
		}
		return (false);
	}
	
	/**
	 * Returns the value of the given <CODE>Node</CODE> as an integer.
	 * 
	 * @param 	node			The <CODE>Node</CODE> containing the value.
	 * @return	The value of the node as a Java datatype.
	 * @since	TFP 1.0
	 */
	public static int toInteger (final Node node)
	{
		try {
			return (Integer.parseInt (toString (node)));
		}
		catch (Exception error) {
			;
		}
		return (0);
	}

	/**
	 * Returns the value of the given <CODE>Node</CODE> as an double.
	 * 
	 * @param 	node			The <CODE>Node</CODE> containing the value.
	 * @return	The value of the node as a Java datatype.
	 * @since	TFP 1.0
	 */
	public static double toDouble (final Node node)
	{
		try {
			return (Double.parseDouble (toString (node)));
		}
		catch (Exception error) {
			;
		}
		return (0.0);
	}

	/**
	 * Returns the value of the given <CODE>Node</CODE> as a decimal.
	 * 
	 * @param	node			The <CODE>Node</CODE> containing the value.
	 * @return	The value of the node as a Java datatype.
	 * @since	TFP 1.0
	 */
	public static BigDecimal toDecimal (final Node node)
	{
		try {
            return (new BigDecimal (toString (node)));
		}
		catch (Exception error) {
			;
		}
		return (BigDecimal.ZERO);
	}
	
	/**
	 * Returns the value of the given <CODE>Node</CODE> as an <CODE>Date</CODE>.
	 * 
	 * @param 	node			The <CODE>Node</CODE> containing the value.
	 * @return	The value of the node as a Java datatype.
	 * @since	TFP 1.1
	 */
	public static Date toDate (final Node node)
	{
		try {
			return (Date.parse (toString (node)));
		}
		catch (Exception error) {
			;
		}
		return (null);
	}

	/**
	 * Returns the value of the given <CODE>Node</CODE> as an <CODE>DateTime</CODE>.
	 * 
	 * @param 	node			The <CODE>Node</CODE> containing the value.
	 * @return	The value of the node as a Java datatype.
	 * @since	TFP 1.1
	 */
	public static DateTime toDateTime (final Node node)
	{
		try {
			return (DateTime.parse (toString (node)));
		}
		catch (Exception error) {
			;
		}
		return (null);
	}

	/**
	 * Returns the value of the given <CODE>Node</CODE> as an <CODE>Time</CODE>.
	 * 
	 * @param 	node			The <CODE>Node</CODE> containing the value.
	 * @return	The value of the node as a Java datatype.
	 * @since	TFP 1.1
	 */
	public static Time toTime (final Node node)
	{
		try {
			return (Time.parse (toString (node)));
		}
		catch (Exception error) {
			;
		}
		return (null);
	}

	/**
	 * Rounds a monetary decimal value to a given number of places. 
	 *  
	 * @param 	value			The <CODE>BigDecimal</CODE> to round.
	 * @param	places			The number of places required.
	 * @return	The rounded value.
	 * @since	TFP 1.0
	 */
	public static BigDecimal round (BigDecimal value, int places)
	{
		return (new BigDecimal (value.movePointRight (places).toBigInteger ()).movePointLeft (places));
	}

	/**
	 * Constructs a <CODE>Types</CODE> instance.
	 * @since	TFP 1.1
	 */
	protected Types ()
	{ }
}