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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public abstract class Frame extends Window
{
	/**
	 * Provides access to the underlying <CODE>JFrame</CODE>.
	 * 
	 * @return	The underlying <CODE>JFrame</CODE>.
	 * @since	TFP 1.0
	 */
	public final JFrame getFrame ()
	{
		return (frame);
	}
	
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
		frame.setVisible (false);
	}

	/**
	 * The Frame window itself.
	 */
	protected JFrame			frame
		= createFrame ("frame");

	/**
	 * The frame's menu bar instance.
	 */
	protected JMenuBar			menuBar
		= new JMenuBar ();

	/**
	 * The 'File' menu instance.
	 */
	protected JMenu				fileMenu
		= createMenu ("file");

	/**
	 * The 'File>Exit' menu item instance.
	 */
	protected JMenuItem			fileExitItem
		= createItem ("fileExit");

	/**
	 * The 'Help' menu instance.
	 */
	protected JMenu				helpMenu
		= createMenu ("help");

	/**
	 * The 'Help>About' menu instance.
	 */
	protected JMenuItem			helpAboutItem
		= createItem ("helpAbout");

	/**
	 * The <CODE>JPanel</CODE> containing the main GUI components.
	 */
	protected JPanel			contentPanel
		= new JPanel ();

	/**
	 */
	protected Frame (
	final String		filename)
	{
		super (filename);

		frame.setJMenuBar (menuBar);
		frame.getContentPane().setLayout(new BorderLayout ());
		frame.getContentPane().add (BorderLayout.CENTER, contentPanel);
		frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		
		frame.addWindowListener (new WindowAdapter ()
			{
				public void windowClosed (WindowEvent event)
				{
					MenuManager.unmanage (frame);
					Frame.this.destroy ();
				}
			});

		fileExitItem.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent event)
				{
					frame.dispose ();
				}
			});

		getApplication ().attach (this);
	}

	/**
	 * Removes this <CODE>Frame</CODE> from the set managed by the applicaton.
	 * @since	TFP 1.0
	 */
	protected void destroy ()
	{
		getApplication ().detach (this);
	}
	
	/**
	 * Centres the <CODE>Frame</CODE> within the screen.
	 * @since	TFP 1.0
	 */
	protected void center ()
	{
		Dimension	screen	= frame.getToolkit ().getScreenSize ();
		
		frame.setLocation (
			(screen.width  - frame.getWidth ())  / 2,
			(screen.height - frame.getHeight ()) / 2);
	}
}