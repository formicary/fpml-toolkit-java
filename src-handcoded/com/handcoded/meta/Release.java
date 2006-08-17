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
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author 	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public abstract class Release implements Grammar
{
	/**
	 * Returns the owning <CODE>Specification</CODE> instance for this
	 * <CODE>Release</CODE>.
	 * 
	 * @return	The owning <CODE>Specification</CODE>.
	 * @since	TFP 1.0
	 */
	public final Specification getSpecification ()
	{
		return (specification);
	}
	
	/**
	 * Returns the version string associated with this <CODE>Release</CODE>.
	 * 
	 * @return	The version identifier string.
	 * @since	TFP 1.0
	 */
	public final String getVersion ()
	{
		return (version);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 	TFP 1.0
	 */
	public final String [] getRootElements ()
	{
		return (rootElements);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 	TFP 1.0
	 */
	public abstract Document newInstance (final String rootElement);
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public abstract boolean isInstance (Document document);
	
	/**
	 * Constructs a <CODE>Release</CODE> instance and associates it with the
	 * indicated <CODE>Specification</CODE>.
	 * 
	 * @param 	specification	A reference to the owning specification.
	 * @param 	version			The version identifier for this release.
	 * @param	rootElements	The possible root element names.
	 * @since 	TFP 1.0
	 */
	protected Release (Specification specification, String version, String [] rootElements)
	{
		this.specification 	= specification;
		this.version 		= version;
		this.rootElements	= rootElements;
		
		specification.add (this);
	}
	
	/**
	 * Performs any post-creation initialisation required for a new
	 * <CODE>Document</CODE> instance (such as setting required attributes).
	 * 
	 * @param 	document		The new <CODE>Document</CODE> instance.
	 * @param 	root			The root <CODE>Element</CODE>.
	 * @since	TFP 1.0
	 */
	protected void initialize (Document document, Element root)
	{ }
	
	/**
	 * Adds the indicated <CODE>Conversion</CODE> to the set of conversions
	 * that take this <CODE>Release</CODE> as the source format.
	 * 
	 * @param 	conversion		The <CODE>Conversion</CODE> to be added.
	 * @since	TFP 1.0
	 */
	protected void addSourceConversion (Conversion conversion)
	{
		sourceConversions.add (conversion);
	}
	
	/**
	 * Adds the indicated <CODE>Conversion</CODE> to the set of conversions
	 * that take this <CODE>Release</CODE> as the target format.
	 * 
	 * @param 	conversion		The <CODE>Conversion</CODE> to be added.
	 * @since	TFP 1.0
	 */
	protected void addTargetConversion (Conversion conversion)
	{
		targetConversions.add (conversion);
	}
	
	/**
	 * Returns an <CODE>Enumeration</CODE> that iterates through the set
	 * of conversions that take this <CODE>Release</CODE> as the source
	 * format.
	 * 
	 * @return	An <CODE>Enumeration</CODE> instance.
	 * @since	TFP 1.0
	 */
	protected Enumeration getSourceConversions ()
	{
		return (sourceConversions.elements ());
	}
	
	/**
	 * Returns an <CODE>Enumeration</CODE> that iterates through the set
	 * of conversions that take this <CODE>Release</CODE> as the target
	 * format.
	 * 
	 * @return	An <CODE>Enumeration</CODE> instance.
	 * @since	TFP 1.0
	 */
	protected Enumeration getTargetConversions ()
	{
		return (targetConversions.elements ());
	}
	
	/**
	 * The owning <CODE>Specification</CODE> instance.
	 * @since	TFP 1.0
	 */
	private final Specification	specification;
	
	/**
	 * The version identifier string.
	 * @since	TFP 1.0
	 */
	private final String		version;
	
	/**
	 * The root element name
	 * @since	TFP 1.0
	 */
	private final String []		rootElements;
	
	/**
	 * The set of conversions for which this release is the source format.
	 * @since	TFP 1.0
	 */
	private Vector				sourceConversions = new Vector ();
	
	/**
	 * The set of conversions for which this release is the target format.
	 * @since	TFP 1.0
	 */
	private Vector				targetConversions = new Vector ();
}