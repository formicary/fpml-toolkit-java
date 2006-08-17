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

package com.handcoded.framework.swing;

import javax.swing.JDialog;

/**
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public abstract class Dialog extends Window
{
	/**
	 * Provides access to the underlying <CODE>JDialog</CODE>.
	 * 
	 * @return	The underlying <CODE>JDialog</CODE>.
	 * @since	TFP 1.0
	 */
	public final JDialog getDialog ()
	{
		return (dialog);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void show ()
	{
		dialog.setVisible (true);
	}

	/**
	 * {@inheritDoc}
	 */
	public void hide ()
	{
		dialog.setVisible (false);
	}
	
	protected JDialog	dialog;
	
	/**
	 */
	protected Dialog (final String filename, final java.awt.Frame owner, final String key, boolean modal)
	{
		super (filename);
		
		dialog = createDialog (owner, key, modal);
	}
	
	/**
	 */
	protected Dialog (final String filename, final java.awt.Dialog owner, final String key, boolean modal)
	{
		super (filename);
		
		dialog = createDialog (owner, key, modal);
	}
	
	/**
	 * Centres the <CODE>Dialog</CODE> above the specified <CODE>Window</CODE> window
	 * 
	 * @param 	owner			The target <CODE>Window</CODE>
	 * @since	TFP 1.0
	 */
	protected void center (final java.awt.Window owner)
	{
		dialog.setLocation (
			owner.getX () + (owner.getWidth ()  - dialog.getWidth  ()) / 2,
			owner.getY () + (owner.getHeight () - dialog.getHeight ()) / 2);
	}
	
	/**
	 * Centers the <CODE>Dialog</CODE> above its owner <CODE>Window</CODE>.
	 * @since	TFP 1.0
	 */
	protected void center ()
	{
		center (dialog.getOwner());
	}
}