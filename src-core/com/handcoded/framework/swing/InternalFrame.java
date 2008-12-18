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

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import javax.swing.JInternalFrame;

/**
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public abstract class InternalFrame extends Window
{
	/**
	 * {@inheritDoc}
	 */
	public void show ()
	{
		frame.setVisible (true);
	}

	/**
	 * {@inheritDoc}
	 */
	public void hide ()
	{
		frame.setVisible (true);
	}

	/**
	 * Returns the associated <CODE>JInternalFrame</CODE> instance.
	 * 
	 * @return	The associated <CODE>JInternalFrame</CODE> instance.
	 * @since	TFP 1.0
	 */
	public JInternalFrame getFrame ()
	{
		return (frame);
	}

	/**
	 * The associated SWING <CODE>JInternalFrame</CODE> component.
	 * @since	TFP 1.
	 */
	protected JInternalFrame		frame
		= createInternalFrame ("frame");

	/**
	 * Constructs an <CODE>InternalFrame</CODE> instance. The frame will
	 * automatically unmange any common menu items it uses when it closes.
	 * 
	 * @param	filename			Base name of the properties file.
	 * @since	TFP 1.0
	 */
	protected InternalFrame (
	final String		filename)
	{
		super (filename);
		
		frame.addInternalFrameListener (
			new InternalFrameAdapter () {
				public void internalFrameClosed (InternalFrameEvent event)
				{
					MenuManager.unmanage (frame);
				}
			});
	}
}