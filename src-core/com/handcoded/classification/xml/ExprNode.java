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

package com.handcoded.classification.xml;

/**
 * An instance of a class derived from the abstract <CODE>ExprNode</CODE> class
 * represents part of an expression used to classify a business object.
 *  
 * @author 	BitWise
 * @version	$Id$
 * @since	TFP 1.6
 */
abstract class ExprNode
{
	/**
	 * Evaluates an criteria defined by this <CODE>ExprNode</CODE> against a
	 * selected context object.
	 * 
	 * @param 	context			The context for the evaluation.
	 * @return	A <CODE>boolean</CODE> indicating of the criteria was
	 * 			satisfied or not.
	 * @since	TFP 1.6
	 */
	public abstract boolean evaluate (final Object context);
}