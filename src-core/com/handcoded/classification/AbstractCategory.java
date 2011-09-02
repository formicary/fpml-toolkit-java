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

import java.util.Enumeration;
import java.util.HashSet;

/**
 * An <CODE>AbstractCategory</CODE> is used to relate a set of sub-category
 * instances.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public class AbstractCategory extends Category
{
	/**
	 * Construct an <CODE>AbstractCategory</CODE> with a given name.
	 * 
	 * @param	classification	The owning <CODE>Classification</CODE>.
	 * @param 	name			The name of the <CODE>Category</CODE>.
	 * @since	TFP 1.0
	 */
	public AbstractCategory (Classification classification, final String name)
	{
		super (classification, name);
	}
	
	/**
	 * Construct an <CODE>AbstractCategory</CODE> that is a sub-classification
	 * of another <CODE>Category</CODE>.
	 * 
	 * @param	classification	The owning <CODE>Classification</CODE>.
	 * @param 	name			The name of the <CODE>Category</CODE>.
	 * @param 	parent			The parent <CODE>Category</CODE>.
	 * @since	TFP 1.0
	 */
	public AbstractCategory (Classification classification, final String name, Category parent)
	{
		super (classification, name, parent);
	}
	
	/**
	 * Construct an <CODE>AbstractCategory</CODE> that is a sub-classification
	 * of other <CODE>Category</CODE> instances.
	 * 
	 * @param	classification	The owning <CODE>Classification</CODE>.
	 * @param	name			The name of the <CODE>Category</CODE>.
	 * @param 	parents			The parent <CODE>Category</CODE> instances.
	 * @since	TFP 1.0
	 */
	public AbstractCategory (Classification classification, final String name, Category [] parents)
	{
		super (classification, name, parents);
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	@Override
	protected Category classify (final Object value, HashSet<Category> visited)
	{
		Category			result	= null;
		Enumeration<Category> cursor = subCategories.elements ();
		
		visited.add (this);
		while (cursor.hasMoreElements ()) {
			Category 			category = cursor.nextElement ();
			Category			match;

			if (!visited.contains (category) && (match = category.classify (value, visited)) != null) {
				if ((result != null) && (result != match)) {
					if (result.isA (match)) continue;
		
					throw new RuntimeException ("Object cannot be unambiguously classified ("
													+ result + " & " + match + ")");
				}
				result = match;
			}
		}
		return (result);
	}	
}