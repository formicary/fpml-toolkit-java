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
import java.util.Vector;
import java.util.HashSet;

/**
 * The <CODE>Category</CODE> class represents a possible classification of an
 * <CODE>Object</CODE>. <CODE>Category</CODE> instances can be linked to each
 * other to create graphs of interrelated items, such as multiple inheritance
 * based structures.
 *
 * @author 	BitWise
 * @version $Id$
 * @since	TFP 1.0
 */
public abstract class Category
{
	/**
	 * Returns the name of this <CODE>Category</CODE>.
	 *
	 * @return	The <CODE>Category</CODE> name string.
	 * @since	TFP 1.0
	 */
	public final String getName ()
	{
		return (name);
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	@Override
	public final String toString ()
	{
		return (name);
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	@Override
	public final int hashCode ()
	{
		return (name.hashCode ());
	}

	/**
	 * Returns an <CODE>Enumeration</CODE> of super-categories.
	 *
	 * @return	An <CODE>Enumeration</CODE> of super-categories.
	 * @since	TFP 1.0
	 */
	public final Enumeration<Category> getSuperCategories ()
	{
		return (superCategories.elements ());
	}

	/**
	 * Returns an <CODE>Enumeration</CODE> of sub-categories.
	 *
	 * @return	An <CODE>Enumeration</CODE> of sub-categories.
	 * @since	TFP 1.0
	 */
	public final Enumeration<Category> getSubCategories ()
	{
		return (subCategories.elements ());
	}

	/**
	 * Determines if this <CODE>Category</CODE> is the same as or is a
	 * sub-category of another <CODE>Category</CODE> (e.g. a Swaption
	 * is-a Option).
	 *
	 * @param 	superCategory		The super category.
	 * @return	<CODE>true</CODE> if there is a 'is-a' relationship
	 * 			between the two categories.
	 */
	public final boolean isA (final Category superCategory)
	{
		if (this == superCategory) return (true);

		Enumeration<Category> cursor = superCategories.elements ();
		while (cursor.hasMoreElements ()) {
			Category parent = cursor.nextElement ();

			if (parent.isA (superCategory)) return (true);
		}
		return (false);
	}

	/**
	 * Determine if the given <CODE>Object</CODE> can be classified by the
	 * graph of <CODE>Category</CODE> instances related to this entry point.
	 *
	 * @param 	value			The <CODE>Object</CODE> to be classified.
	 * @return 	The matching <CODE>Category</CODE> for the <CODE>Object</CODE>
	 * 			or <CODE>null</CODE> if none could be determined.
	 * @since	TFP 1.0
	 */
	public final Category classify (final Object value)
	{
		return (classify (value, new HashSet<Category> ()));
	}

	/**
	 * <CODE>Category</CODE> instances that reference this instance.
	 *
	 * @since	TFP 1.0
	 */
	protected Vector<Category> superCategories	= new Vector<Category> ();

	/**
	 * <CODE>Category</CODE> instances referenced by this instance.
	 *
	 * @since	TFP 1.0
	 */
	protected Vector<Category> subCategories	= new Vector<Category> ();

	/**
	 * Construct a <CODE>Category</CODE> with a given name.
	 *
	 * @param 	name			The name of the <CODE>Category</CODE>.
	 * @since	TFP 1.0
	 */
	protected Category (final String name)
	{
		this.name = name;
	}

	/**
	 * Construct a <CODE>Category</CODE> with a given name.
	 *
	 * @param 	name			The name of the <CODE>Category</CODE>.
	 * @param 	parent			The parent <CODE>Category</CODE>.
	 * @since	TFP 1.0
	 */
	protected Category (final String name, Category parent)
	{
		this (name);

		this.superCategories.add (parent);
		parent.subCategories.add (this);
	}

	/**
	 * Construct a <CODE>Category</CODE> with a given name.
	 *
	 * @param 	name			The name of the <CODE>Category</CODE>.
	 * @param 	parents			The parent <CODE>Category</CODE> instances.
	 * @since	TFP 1.0
	 */
	protected Category (final String name, Category [] parents)
	{
		this (name);

		for (int index = 0; index < parents.length; ++index) {
			this.superCategories.add (parents [index]);
			parents [index].subCategories.add (this);
		}
	}
	
	/**
	 * Determine if the given <CODE>Object</CODE> can be classified by the
	 * graph of <CODE>Category</CODE> instances related to this entry point.
	 * 
	 * @param 	value			The <CODE>Object</CODE> to be classified.		
	 * @param 	visited			A <CODE>HashSet</CODE> used to track visited nodes.
	 * @return	The matching <CODE>Category</CODE> for the <CODE>Object</CODE>
	 * 			or <CODE>null</CODE> if none could be determined.
	 * @since	TFP 1.0
	 */
	protected abstract Category classify (final Object value, HashSet<Category> visited);

	/**
	 * The name of this <CODE>Category</CODE>.
	 *
	 * @since	TFP 1.0
	 */
	private final String	name;
}