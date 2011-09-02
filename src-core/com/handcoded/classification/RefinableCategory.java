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

package com.handcoded.classification;

import java.util.HashSet;

/**
 * A <CODE>RefinableCategory</CODE> instance can be used to provide a 'catch-all'
 * category should its sub-categories fail to isolate a specific variant.
 *
 * @author 	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public abstract class RefinableCategory extends AbstractCategory
{
	/**
	 * Construct an <CODE>AbstractCategory</CODE> with a given name.
	 * 
	 * @param	classification	The owning <CODE>Classification</CODE>.
	 * @param 	name			The name of the <CODE>Category</CODE>.
	 * @since	TFP 1.0
	 */
	protected RefinableCategory (Classification classification, final String name)
	{
		super (classification, name);
	}
	
	/**
	 * Construct an <CODE>RefinableCategory</CODE> that is a sub-classification
	 * of another <CODE>Category</CODE>.
	 * 
	 * @param	classification	The owning <CODE>Classification</CODE>.
	 * @param 	name			The name of the <CODE>Category</CODE>.
	 * @param 	parent			The parent <CODE>Category</CODE>.
	 * @since	TFP 1.0
	 */
	protected RefinableCategory (Classification classification, final String name, Category parent)
	{
		super (classification, name, parent);
	}
	
	/**
	 * Construct an <CODE>RefinableCategory</CODE> that is a sub-classification
	 * of other <CODE>Category</CODE> instances.
	 * 
	 * @param	classification	The owning <CODE>Classification</CODE>.
	 * @param 	name			The name of the <CODE>Category</CODE>.
	 * @param 	parents			The parent <CODE>Category</CODE> instances.
	 * @since	TFP 1.0
	 */
	protected RefinableCategory (Classification classification, final String name, Category [] parents)
	{
		super (classification, name, parents);
	}

	/**
	 * {@inheritDoc}
	 * A <CODE>RefinableCategory</CODE> first determines if it (and its
	 * sub-categories) is applicable to the <CODE>Object</CODE> before
	 * attempting to classify it. If an applicable <CODE>Object</CODE> is
	 * not claimed by a sub-category then the <CODE>RefinableCategory</CODE>
	 * will 'generically' claim it.
	 * 
 	 * @since	TFP 1.0
	 */
	@Override
	protected Category classify (final Object value, HashSet<Category> visited)
	{
		Category		match	= null;

		visited.add (this);
		if (isApplicable (value)) {
			if ((match = super.classify (value, visited)) == null)
				match = this;
		}
		return (match);
	}

	/**
	 * Determines if this <CODE>RefinableCategory</CODE> (and its sub-categories)
	 * is applicable to the given <CODE>Object</CODE>.
	 *
	 * @param 	value			The <CODE>Object</CODE> to be tested.
	 * @return	<CODE>true</CODE> if the <CODE>Object</CODE> is applicable,
	 * 			<CODE>false</CODE> otherwise.
	 * @since	TFP 1.0
	 */
	protected abstract boolean isApplicable (final Object value);
}
