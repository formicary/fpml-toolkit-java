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

import com.handcoded.classification.Category;
import com.handcoded.classification.Classification;
import com.handcoded.classification.RefinableCategory;

/**
 * 
 * @author 	BitWise
 * @version	$Id$
 * @since	TFP 1.6
 */
final class InterpretedRefinableCategory extends RefinableCategory
{
	/**
	 * Construct an <CODE>AbstractCategory</CODE> with a given name.
	 * 
	 * @param	classification	The owning <CODE>Classification</CODE>.
	 * @param 	name			The name of the <CODE>Category</CODE>.
	 * @param	expression		The internal representation of the expression.
	 * @since	TFP 1.6
	 */
	public InterpretedRefinableCategory (Classification classification, final String name, final ExprNode expression)
	{
		super (classification, name);
		
		this.expression = expression;
	}
	
	/**
	 * Construct an <CODE>RefinableCategory</CODE> that is a sub-classification
	 * of other <CODE>Category</CODE> instances.
	 * 
	 * @param	classification	The owning <CODE>Classification</CODE>.
	 * @param 	name			The name of the <CODE>Category</CODE>.
	 * @param 	parents			The parent <CODE>Category</CODE> instances.
	 * @param	expression		The internal representation of the expression.
	 * @since	TFP 1.6
	 */
	public InterpretedRefinableCategory (Classification classification, final String name, Category [] parents, final ExprNode expression)
	{
		super (classification, name, parents);

		this.expression = expression;
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.6
	 */
	@Override
	protected boolean isApplicable (final Object value)
	{
		return (expression.evaluate (value));
	}
	
	/**
	 * The underlying expression used to differentiate.
	 * @since	TFP 1.6
	 */
	private final ExprNode	expression;
}