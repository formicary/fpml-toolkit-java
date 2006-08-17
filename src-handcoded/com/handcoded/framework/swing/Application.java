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

import java.util.Vector;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Extends the basic <CODE>Application</CODE> class to provide functionality
 * to manage a Swing based User Interface.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public abstract class Application extends com.handcoded.framework.Application
{
	/**
	 * A constant <CODE>String</CODE> for the Metal look and feel.
	 */
	public static final String		METAL	= "Metal";

	/**
	 * A constant <CODE>String</CODE> for the Windows look and feel.
	 */
	public static final String		WINDOWS	= "Windows";

	/**
	 * A constant <CODE>String</CODE> for the Macintosh look and feel.
	 */
	public static final String		MAC		= "Mac"; // TODO: Test

	/**
	 * A constant <CODE>String</CODE> for the Motif look and feel.
	 */
	public static final String		MOTIF	= "CDE/Motif";

	/**
	 * Returns the name of the current active Swing look and feel.
	 *
	 * @return	The current look and feel name.
	 */
	public final String getLookAndFeel ()
	{
		return (UIManager.getLookAndFeel ().getName ());
	}

	/**
	 * Attempts to change the Swing look and feel the one indicated. If any
	 * errors occur during the call then the look and feel will be unchanged.
	 *
	 * @param	lookAndFeel			The name of the desired look and feel.
	 * @return	<CODE>true</CODE> if the look and feel was changed,
	 */
	public final boolean setLookAndFeel (
	final String		lookAndFeel)
	{
		if (!getLookAndFeel ().equals (lookAndFeel)) {
			LookAndFeelInfo		info [] = UIManager.getInstalledLookAndFeels ();

			try {
				for (int index = 0; index < info.length; ++index) {
					if (info [index].getName ().equals (lookAndFeel)) {
						UIManager.setLookAndFeel (info [index].getClassName ());
						break;
					}
				}

				getUserPreferences ().put (LOOKANDFEEL, lookAndFeel);

			}
			catch (ClassNotFoundException error)
			{
				return (false);
			}
			catch (InstantiationException error)
			{
				return (false);
			}
			catch (IllegalAccessException error)
			{
				return (false);
			}
			catch (UnsupportedLookAndFeelException error)
			{
				return (false);
			}
		}
		return (true);
	}

	/**
	 * Adds a <CODE>Frame</CODE> to the set of windows under management.
	 *
	 * @param	frame			The <CODE>Frame</CODE> to be added.
	 */
	public void attach (
	final Frame		frame)
	{
		frames.add (frame);
	}

	/**
	 * Removes a <CODE>Frame</CODE> from the set of windows under management.
	 *
	 * @param	frame			The <CODE>Frame</CODE> to be removed.
	 */
	public void detach (
	final Frame		frame)
	{
		synchronized (frames) {
			frames.remove (frame);
			frames.notify ();
		}
	}

	/**
	 * Returns a copy of the set of active windows.
	 *
	 * @return	An <CODE>Enumeration</CODE> if the active <CODE>Frame</CODE>
	 *			instances
	 */
	public Enumeration getFrames ()
	{
		return (frames.elements ());
	}

	/**
	 * Constructs an <CODE>Application</CODE> instance.
	 */
	protected Application ()
	{ }

	/**
	 *
	 */
	protected void startUp ()
	{
		super.startUp ();

		String				style 	= METAL;

		if (isWindows ())
			style = WINDOWS;
		else if (isMac ())
			style = MAC;

		setLookAndFeel (getUserPreferences ().get (LOOKANDFEEL, style));
	}

	/**
	 * Waits until the frames set becomes empty and then causes the application
	 * to terminate.
	 */
	protected void execute ()
	{
		try {
			synchronized (frames) {
				frames.wait ();
			}
		}
		catch (InterruptedException error)
		{ }
		finally {
			setFinished (frames.size () == 0);
		}
	}

	/**
	 */
	protected void cleanUp ()
	{
		super.cleanUp ();
	}

	/**
	 * Constant <CODE>String</CODE> containing the look and feel preference name.
	 */
	private static final String	LOOKANDFEEL		= "lookAndFeel";

	/**
	 * The set of active <CODE>Frame</CODE> windows.
	 */
	private Vector			frames = new Vector ();
}