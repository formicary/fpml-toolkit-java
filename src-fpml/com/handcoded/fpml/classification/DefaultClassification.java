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
 * 
 * @author 	BitWise
 * @version	$Id$
 * @since	TFP 1.6
 */
final class DefaultClassification
{
	public static Category getCategoryByName (final String name)
	{
		return (defaultClassification.getCategoryByName (name));
	}
	
	/**
	 * The default FpML Product classification.
	 * @since	TFP 1.6
	 */
	private static Classification	defaultClassification
		= ClassificationLoader.load ("files/fpml-classification.xml");
}