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
import com.handcoded.classification.Classification;
import com.handcoded.classification.xml.ClassificationLoader;

/**
 * The <CODE>ISDATaxonomy</CODE> class provides easy access the to the
 * categories defined in the taxonomy provided by the ISDA product
 * working groups initiated in response to the Dodd-Frank Act.
 *
 * @author 	BitWise
 * @version	$Id$
 * @since	FTP 1.6
 */
public final class ISDATaxonomy
{
	/**
	 * The default FpML Product classification.
	 * @since	TFP 1.6
	 */
	private static Classification	defaultClassification
		= ClassificationLoader.load ("files/isda-classification.xml");
	
	/**
	 * A <CODE>Category</CODE> representing all product types.
	 * @since	TFP 1.0
	 */
	public static final Category	ISDA
		= defaultClassification.getCategoryByName ("{ISDA}");

	/**
	 * Prevents an instance being created.
	 * @since	TFP 1.6
	 */
	private ISDATaxonomy ()
	{ }
}