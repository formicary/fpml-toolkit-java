// Copyright (C),2005-2008 HandCoded Software Ltd.
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
import com.handcoded.meta.Release;

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
		this.release = release;
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public boolean evaluate (NodeIndex nodeIndex)
	{
		String []		rootElements = release.getRootElements ();
		
		for (int index = 0; index < rootElements.length; ++index) {
			NodeList list = nodeIndex.getElementsByName (rootElements [index]);
			
			if (list.getLength() == 1) {
				Element	fpml = (Element) list.item (0);
			
				if (fpml.getLocalName().equals("FpML"))
					return (fpml.getAttribute ("version").equals (release.getVersion ()));
				else
					return (fpml.getAttribute ("fpmlVersion").equals (release.getVersion ()));					
			}
		}
		return (false);
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

}