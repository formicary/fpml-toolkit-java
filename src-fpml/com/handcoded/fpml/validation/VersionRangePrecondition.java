// Copyright (C),2005-2010 HandCoded Software Ltd.
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
			? new Version (minimum.getVersion ()) : null;
		maximumVersion = ((this.maximum = maximum) != null)
			? new Version (maximum.getVersion ()) : null;
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.5
	 */
	public boolean evaluate (NodeIndex nodeIndex)
	{
		boolean applicable = false;
		
		if (minimum != null) {
			String [] rootElements = minimum.getRootElements ();
			Version version;
			
			for (int index = 0; index < rootElements.length; ++index) {
				NodeList list = nodeIndex.getElementsByName (rootElements [index]);
				
				if (list.getLength() == 1) {
					applicable = true;
					Element	fpml = (Element) list.item (0);
				
					if (fpml.getLocalName().equals("FpML"))
						version = new Version (fpml.getAttribute ("version"));
					else
						version = new Version (fpml.getAttribute ("fpmlVersion"));
					
					if (version.compareTo (minimumVersion) < 0) return (false);
				}
			}
		}
		
		if (maximum != null) {
			String [] rootElements = maximum.getRootElements ();
			Version version;
			
			for (int index = 0; index < rootElements.length; ++index) {
				NodeList list = nodeIndex.getElementsByName (rootElements [index]);
				
				if (list.getLength() == 1) {
					applicable = true;
					Element	fpml = (Element) list.item (0);
				
					if (fpml.getLocalName().equals("FpML"))
						version = new Version (fpml.getAttribute ("version"));
					else
						version = new Version (fpml.getAttribute ("fpmlVersion"));
					
					if (version.compareTo (maximumVersion) > 0) return (false);
				}
			}
		}
		
		return (applicable);
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
