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

package com.handcoded.fpml.schemes;

/**
 * The <CODE>Enumerable</CODE> interface defines an API for extracting a
 * complete list of the possible values of a <CODE>Scheme</CODE> instance.
 * <P>
 * <CODE>Enumerable</CODE> should only be supported by closed domains.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public interface Enumerable
{
	/**
	 * Creates an array containing all the possible <CODE>Value</CODE> instances
	 * supported by a <CODE>Scheme</CODE>. No particular ordering of the values
	 * is enforced.
	 *
	 * @return	An array containing all the <CODE>Value</CODE> instances.
	 */
	public Value [] values ();
}