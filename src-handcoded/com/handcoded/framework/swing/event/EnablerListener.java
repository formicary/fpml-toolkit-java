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

package com.handcoded.framework.swing.event;

/**
 * The <CODE>MenuEnablerListener</CODE> interface provides a way to find out
 * the desired state of a component.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public interface EnablerListener
{
	/**
	 * Determines if the associated component should be enabled.
	 * 
	 * @return	<CODE>true</CODE> if the component should be enabled,
	 * 			<CODE>false</CODE> otherwise.
	 * @since	TFP 1.0
	 */
	public boolean isEnabled ();
}