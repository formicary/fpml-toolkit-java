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
package com.handcoded.fpml.validation;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
	 * @param 	version			The required version number.
	 */
	public VersionPrecondition (final String version)
	{
		this.version = version;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean evaluate (NodeIndex nodeIndex)
	{
		NodeList list = nodeIndex.getElementsByName ("FpML");
		
		if (list.getLength() == 1) {
			Element	fpml = (Element) list.item (0);
		
			return (fpml.getAttribute ("version").equals(version));
		}
		return (false);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString ()
	{
		return ("version==" + version);
	}
	
	/**
	 * The specific FpML version to match against.
	 */
	private final String		version;
}