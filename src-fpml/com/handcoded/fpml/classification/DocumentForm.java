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
public final class DocumentForm
{
	/**
	 * A <CODE>Category</CODE> representing all documents forms.
	 * @since	TFP 1.6
	 */
	public static final Category	DOCUMENT_FORM
		= DefaultClassification.getCategoryByName ("DocumentForm");
	
	/**
	 * A <CODE>Category</CODE> representing all short form documents in which some
	 * details of the trade are defined outside the transaction record.
	 * @since	TFP 1.6
	 */
	public static final Category	SHORT_FORM
		= DefaultClassification.getCategoryByName ("ShortForm");

	/**
	 * A <CODE>Category</CODE> representing all long form documents in which all
	 * the details of the trade are defined within the transaction record.
	 * @since	TFP 1.6
	 */
	public static final Category	LONG_FORM
		= DefaultClassification.getCategoryByName ("LongForm");
	
	/**
	 * Prevents an instance being created.
	 * @since	TFP 1.6
	 */
	private DocumentForm ()
	{ }
}