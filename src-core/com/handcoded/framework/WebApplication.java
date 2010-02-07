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

package com.handcoded.framework;

import java.io.InputStream;

/**
 * An instance of the <CODE>WebApplication</CODE> class represents an program
 * designed to run within a Java application server.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.4
 */
public abstract class WebApplication extends Application
{
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.4			
	 */
	public InputStream OpenStream (final String name)
	{
		return (getClass ().getResourceAsStream (name));
	}
	
	/**
	 * Constructs a <CODE>WebApplication</CODE> instance.
	 * @since	TFP 1.4
	 */
	protected WebApplication ()
	{ }
}