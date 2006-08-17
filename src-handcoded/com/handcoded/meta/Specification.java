// Copyright (C),2005-2006 HandCoded Software Ltd.
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

package com.handcoded.meta;

import java.util.Enumeration;
import java.util.Hashtable;

import org.w3c.dom.Document;

/**
 * Instances of the <CODE>Specification</CODE> class represent XML based
 * data models such as those for the standards FpML and FixML.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since 	TFP 1.0
 */
public final class Specification
{
	/**
	 * Constructs a <CODE>Specification</CODE> with the given name.
	 * 
	 * @param 	name			The specification name.
	 * @since	TFP 1.0
	 */
	public Specification (String name)
	{
		extent.put (this.name = name, this);
	}
	
	/**
	 * Attempts to locate a <CODE>Specification</CODE> instance with the given
	 * name.
	 * 
	 * @param 	name			The target <CODE>Specification</CODE> name.
	 * @return	The corresponding <CODE>Specification</CODE> or <CODE>null
	 * 			</CODE> if no matching instance exists.
	 * @since	TFP 1.0
	 */
	public static Specification forName (final String name)
	{
		return ((Specification) extent.get (name));
	}
	
	/**
	 * Attempts to locate the <CODE>Specification</CODE> instance corresponding
	 * to the given <CODE>Document</CODE>.
	 * 
	 * @param	document			The <CODE>Document</CODE> to examine.
	 * @return	The <CODE>Specification</CODE> instance corresponding to the
	 * 			<CODE>Document</CODE> or <CODE>null</CODE> if its not
	 * 			recognized.
	 * @since	TFP 1.0
	 */
	public static Specification forDocument (Document document)
	{
		Release			release = releaseForDocument (document);
		
		return ((release != null) ? release.getSpecification () : null);
	}
	
	/**
	 * Attempts to locate the <CODE>Release</CODE> instance corresponding
	 * to the given <CODE>Document</CODE>.
	 * 
	 * @param	document			The <CODE>Document</CODE> to examine.
	 * @return	The <CODE>Release</CODE> instance corresponding to the
	 * 			<CODE>Document</CODE> or <CODE>null</CODE> if its not
	 * 			recognized.
	 * @since	TFP 1.0
	 */
	public static Release releaseForDocument (Document document)
	{
		Enumeration		cursor	= extent.keys ();
		
		while (cursor.hasMoreElements ()) {
			Release	release = ((Specification) extent.get (cursor.nextElement ()))
									.getReleaseForDocument (document);
			
			if (release != null) return (release);
		}
		return (null);
	}
		
	/**
	 * Provides access to the specification name.
	 * 
	 * @return	The specification name.
	 * @since	TFP 1.0
	 */
	public String getName ()
	{
		return (name);
	}
	
	/**
	 * Determines if the given <CODE>Document</CODE> is an instance of some
	 * <CODE>Release</CODE> of this <CODE>Specification</CODE>.
	 * 
	 * @param	document		The <CODE>Document</CODE> to be examined.
	 * @return	<CODE>true</CODE> if this document is an instance of the
	 * 			<CODE>Specification</CODE>, <CODE>false</CODE> otherwise.
	 * @since 	TFP 1.0
	 */
	public boolean isInstance (Document document)
	{
		return (getReleaseForDocument (document) != null);
	}
	
	/**
	 * Attempts to locate a <CODE>Release</CODE> of the current
	 * <CODE>Specification</CODE> that is compatible with the given
	 * <CODE>Document</CODE>.
	 * 
	 * @param 	document		The <CODE>Document</CODE> to be examined.
	 * @return	The corresponding <CODE>Release</CODE> or <CODE>null</CODE>
	 * 			if a match could not be found.
	 * @since	TFP 1.0
	 */
	public Release getReleaseForDocument (Document document)
	{
		Enumeration		cursor = releases.keys ();
		
		while (cursor.hasMoreElements ()) {
			Release	release = (Release) releases.get(cursor.nextElement ());
			if (release.isInstance (document)) return (release);
		}
		return (null);
	}

	/**
	 * Attempts to locate a <CODE>Release</CODE> associated with this
	 * <CODE>Specification</CODE> with the indicated version identifier.
	 * 
	 * @param 	version			The target version identifier.
	 * @return	The corresponding <CODE>Release</CODE> instance or <CODE>null
	 * 			</CODE> if none exists.
	 * @since	TFP 1.0
	 */
	public Release getReleaseForVersion (final String version)
	{
		return ((Release) releases.get (version));
	}
	
	/**
	 * Adds the indicated <CODE>Release</CODE> instance to the set managed
	 * by the specification. If a <CODE>Release</CODE> instance with the same
	 * version identifier is already in the set it will be displaced by the
	 * new instance.
	 * 
	 * @param 	release			The <CODE>Release</CODE> instance to add.
	 * @return	The displaced <CODE>Release</CODE> instance or <CODE>null</CODE>
	 * 			if none.
	 * @since	TFP 1.0 
	 */
	public Release add (Release release)
	{
		if (release.getSpecification () != this)
			throw new AssertionError ("The provided release is for a different specification");
				
		return ((Release) releases.put (release.getVersion (), release));
	}
	
	/**
	 * Removes the indicated <CODE>Release</CODE> instance from the set managed
	 * by the <CODE>Specification</CODE>.
	 * 
	 * @param 	release			The <CODE>Release</CODE> to be removed.
	 * @return	A reference to the removed <CODE>Release</CODE> instance or
	 * 			<CODE>null</CODE> if it was not in the set.
	 * @since	TFP 1.0
	 */
	public Release remove (Release release)
	{
		if (release.getSpecification () != this)
			throw new AssertionError ("The provided release is for a different specification");

		return ((Release) releases.remove(release.getVersion ()));
	}
	
	/**
	 * Returns an <CODE>Enumeration</CODE> that can be used to iterate through
	 * the <CODE>Release</CODE> instances for this <CODE>Specification</CODE>.
	 * 
	 * @return	An <CODE>Enumeration</CODE> instance.
	 * @since	TFP 1.0
	 */
	public Enumeration releases ()
	{
		return (releases.elements ());
	}
	
	/**
	 * Returns a hash code for this instance based on its name.
	 * 
	 * @return	The hash code for this instance.
	 * @since	TFP 1.0 
	 */
	public int hashCode ()
	{
		return (name.hashCode ());
	}
	
	/**
	 * Converts the <CODE>Specification</CODE> to a string for debugging.
	 *
	 * @return 	A text description of the instance.
	 * @since	TFP 1.0
	 */
	public String toString ()
	{
		return (getClass ().getName () + " [" + toDebug () + "]");
	}

	/**
	 * Produces a debugging string describing the state of the instance.
	 *
	 * @return 	The debugging string.
	 * @since	TFP 1.0
	 */
	protected String toDebug ()
	{
		StringBuffer		buffer = new StringBuffer ();
		
		buffer.append ("name=\"");
		buffer.append (name);
		buffer.append ("\", releases={");
		
		Enumeration cursor = releases.keys ();
		boolean		first  = true;
		
		while (cursor.hasMoreElements()) {
			if (!first) buffer.append (',');

			buffer.append ('"');
			buffer.append ((String) cursor.nextElement ());
			buffer.append ('"');
			first = false;
		}
		buffer.append ("}");
		
		return (buffer.toString ());
	}
	
	/**
	 * The extent set of all <CODE>Specification</CODE> instances.
	 * @since	TFP 1.0
	 */
	private static Hashtable	extent	= new Hashtable ();
	
	/**
	 * The unique name of this <CODE>Specification</CODE>.
	 * @since 	TFP 1.0
	 */
	private final String 		name;
	
	/**
	 * The set of <CODE>Release</CODE> instances associated with this
	 * <CODE>Specification</CODE>.
	 * @since	TFP 1.0
	 */
	private Hashtable			releases	= new Hashtable ();
}