// Copyright (C),2005-2011 HandCoded Software Ltd.
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

package com.handcoded.fpml.classification;

import com.handcoded.classification.Category;

/**
 * 
 * @author 	BitWise
 * @version	$Id$
 * @since	FTP 1.6
 */
public final class InstrumentType
{
	/**
	 * A <CODE>Category</CODE> representing all instrument types.
	 * @since	TFP 1.6
	 */
	public static final Category	INSTRUMENT_TYPE
		= DefaultClassification.getCategoryByName ("InstrumentType");
	
	/**
	 * A <CODE>Category</CODE> representing all instrument types.
	 * @since	TFP 1.6
	 */
	public static final Category	SECURITIES
		= DefaultClassification.getCategoryByName ("Securities");
	
	/**
	 * A <CODE>Category</CODE> representing all instrument types.
	 * @since	TFP 1.6
	 */
	public static final Category	CASH
		= DefaultClassification.getCategoryByName ("Cash");
	
	/**
	 * A <CODE>Category</CODE> representing all instrument types.
	 * @since	TFP 1.6
	 */
	public static final Category	EXCHANGE_TRADED
		= DefaultClassification.getCategoryByName ("ExchangeTraded");
	
	/**
	 * A <CODE>Category</CODE> representing all instrument types.
	 * @since	TFP 1.6
	 */
	public static final Category	OTC_DERIVATIVE
		= DefaultClassification.getCategoryByName ("OTCDerivative");
	
	/**
	 * Prevents an instance being created.
	 * @since	TFP 1.6
	 */
	private InstrumentType ()
	{ }	
}