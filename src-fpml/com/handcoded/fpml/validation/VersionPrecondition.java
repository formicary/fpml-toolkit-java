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

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.handcoded.fpml.util.Version;
import com.handcoded.meta.Release;
import com.handcoded.validation.Precondition;
import com.handcoded.xml.NodeIndex;

/**
 * The <CODE>VersionPrecondition</CODE> class checks that the FpML root
 * element contains a specific version string.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class VersionPrecondition extends Precondition
{
	/**
	 * Constructs a <CODE>VersionPrecondition</CODE> that detects a specific
	 * version number.
	 * 
	 * @param 	release			The required FpML release.
	 * @since	TFP 1.1
	 */
	public VersionPrecondition (final Release release)
	{
		targetVersion = new Version ((this.release = release).getVersion ());	
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public boolean evaluate (NodeIndex nodeIndex)
	{
		Version 		version;
		
		// Find the document version
		NodeList list = nodeIndex.getElementsByName ("FpML");
		if (list.getLength () > 0)
			version = new Version (((Element) list.item (0)).getAttribute ("version"));
		else {
			list = nodeIndex.getAttributesByName ("fpmlVersion");
			if (list.getLength () > 0)
				version = new Version (((Attr) list.item (0)).getValue ());
			else
				return (false);
		}
		
		return (version.equals (targetVersion));
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public String toString ()
	{
		return ("release=" + release);
	}
	
	/**
	 * The specific FpML release to match against.
	 * @since	TFP 1.1
	 */
	private final Release	release;
	
	/**
	 * The target FpML version number.
	 * @since	TFP 1.5
	 */
	private final Version	targetVersion;
}