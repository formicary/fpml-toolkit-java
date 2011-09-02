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

package com.handcoded.fpml.validation;

import java.util.Hashtable;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.handcoded.fpml.util.Version;
import com.handcoded.meta.Release;
import com.handcoded.validation.Precondition;
import com.handcoded.xml.NodeIndex;

/**
 * A <CODE>VersionRangePrecondition</CODE> instance tests if the FpML version
 * of a documents lies between two limits. Either of the minimum or maximum
 * values can be omitted to make the range open ended.
 * 
 * @author 	BitWise
 * @version	$Id$
 * @since	TFP 1.5
 */
public class VersionRangePrecondition extends Precondition
{
	/**
	 * Constructs a <CODE>VersionRangePrecondition</CODE> using the two
	 * bounding release versions.
	 * 
	 * @param 	minimum			The minimum version accepted.
	 * @param 	maximum			The maximum version accepted.
	 * @since	TFP 1.5
	 */
	public VersionRangePrecondition (final Release minimum, final Release maximum)
	{
		minimumVersion = ((this.minimum = minimum) != null)
			? Version.parse (minimum.getVersion ()) : null;
		maximumVersion = ((this.maximum = maximum) != null)
			? Version.parse (maximum.getVersion ()) : null;
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.5
	 */
	@Override
	public boolean evaluate (final NodeIndex nodeIndex, Hashtable<Precondition, Boolean> cache)
	{
		Version 		version;
		
		// Find the document version
		NodeList list = nodeIndex.getElementsByName ("FpML");
		if (list.getLength () > 0)
			version = Version.parse (((Element) list.item (0)).getAttribute ("version"));
		else {
			list = nodeIndex.getAttributesByName ("fpmlVersion");
			if (list.getLength () > 0)
				version = Version.parse (((Attr) list.item (0)).getValue ());
			else
				return (false);
		}
		
//		System.err.print ("Range (Doc=" + version
//				+ " Min=" + ((minimumVersion != null) ? minimumVersion.toString () : "*")
//				+ " Max=" + ((maximumVersion != null) ? maximumVersion.toString () : "*"));

		boolean validMin = (minimumVersion != null) ? (version.compareTo (minimumVersion) >= 0) : true;
		boolean validMax = (maximumVersion != null) ? (version.compareTo (maximumVersion) <= 0) : true;
		
//		System.err.println (") => " + (validMin & validMax));

		return (validMin & validMax);
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.5
	 */
	public String toString ()
	{
		return ("minimum=" + ((minimum != null) ? minimum.toString () : "any") +
				" maximum=" + ((maximum != null) ? maximum.toString () : "any"));	
	}
	
	/**
	 * The minimum FpML release to match against or <CODE>null</CODE>.
	 * @since	TFP 1.5
	 */
	private final Release	minimum;
	
	/**
	 * The maximum FpML release to match against or <CODE>null</CODE>.
	 * @since	TFP 1.5
	 */
	private final Release	maximum;
	
	/**
	 * The minimum FpML version number.
	 * @since	TFP 1.5
	 */
	private final Version	minimumVersion;
	
	/**
	 * The maximum FpML version number.
	 * @since	TFP 1.5
	 */
	private final Version	maximumVersion;
}
