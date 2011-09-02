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
public final class FpMLProduct
{
	/**
	 * A <CODE>Category</CODE> representing all product types.
	 * @since	TFP 1.0
	 */
	public static final Category	FPML_PRODUCT
		= DefaultClassification.getCategoryByName ("FpML");


	/**
	 * Prevents an instance being created.
	 * @since	TFP 1.6
	 */
	private FpMLProduct ()
	{ }
}