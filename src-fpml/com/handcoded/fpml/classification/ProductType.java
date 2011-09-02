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
public class ProductType
{
	/**
	 * A <CODE>Category</CODE> representing any product type.
	 * @since	TFP 1.6
	 */
	public static final Category	PRODUCT_TYPE
		= DefaultClassification.getCategoryByName ("ProductType");
	
	/**
	 * A <CODE>Category</CODE> representing any structure product.
	 * @since	TFP 1.6
	 */
	public static final Category	STRUCTURED_PRODUCT
		= DefaultClassification.getCategoryByName ("StructuredProduct");
	
	/**
	 * A <CODE>Category</CODE> representing any option strategy.
	 * @since	TFP 1.6
	 */
	public static final Category	OPTION_STRATEGY
		= DefaultClassification.getCategoryByName ("OptionStrategy");
	
	/**
	 * A <CODE>Category</CODE> representing a simple exchange of one thing
	 * for another.
	 * @since	TFP 1.6
	 */
	public static final Category	EXCHANGE
		= DefaultClassification.getCategoryByName ("Exchange");
	
	/**
	 * A <CODE>Category</CODE> representing any type of derivative.
	 * @since	TFP 1.6
	 */
	public static final Category	DERIVATIVE
		= DefaultClassification.getCategoryByName ("Derivative");
	
	/**
	 * A <CODE>Category</CODE> representing any future.
	 * @since	TFP 1.6
	 */
	public static final Category	FUTURE
		= DefaultClassification.getCategoryByName ("Future");
	
	/**
	 * A <CODE>Category</CODE> representing any forward.
	 * @since	TFP 1.6
	 */
	public static final Category	FORWARD
		= DefaultClassification.getCategoryByName ("Forward");
	
	/**
	 * A <CODE>Category</CODE> representing any option.
	 * @since	TFP 1.6
	 */
	public static final Category	OPTION
		= DefaultClassification.getCategoryByName ("Option");
	
	/**
	 * A <CODE>Category</CODE> representing any compound option.
	 * @since	TFP 1.6
	 */
	public static final Category	COMPOUND_OPTION
		= DefaultClassification.getCategoryByName ("CompoundOption");
	
	/**
	 * A <CODE>Category</CODE> representing any option strip.
	 * @since	TFP 1.6
	 */
	public static final Category	OPTION_STRIP
		= DefaultClassification.getCategoryByName ("OptionStrip");
	
	/**
	 * A <CODE>Category</CODE> representing any cap.
	 * @since	TFP 1.6
	 */
	public static final Category	CAP
		= DefaultClassification.getCategoryByName ("Cap");
	
	/**
	 * A <CODE>Category</CODE> representing any floor.
	 * @since	TFP 1.6
	 */
	public static final Category	FLOOR
		= DefaultClassification.getCategoryByName ("Floor");
	
	/**
	 * A <CODE>Category</CODE> representing any collar.
	 * @since	TFP 1.6
	 */
	public static final Category	COLLAR
		= DefaultClassification.getCategoryByName ("Collar");
	
	/**
	 * A <CODE>Category</CODE> representing any swap.
	 * @since	TFP 1.6
	 */
	public static final Category	SWAP
		= DefaultClassification.getCategoryByName ("Swap");
	
	/**
	 * A <CODE>Category</CODE> representing any swaption.
	 * @since	TFP 1.6
	 */
	public static final Category	SWAPTION
		= DefaultClassification.getCategoryByName ("Swaption");
	
	/**
	 * Prevents an instance being created.
	 * @since	TFP 1.6
	 */
	private ProductType ()
	{ }	
}