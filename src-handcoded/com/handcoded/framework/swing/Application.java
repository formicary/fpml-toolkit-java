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

import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

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
	 * Determines if the Operating System is Microsoft Windows.
	 *
	 * @return 	<CODE>true</CODE> if the JVM is executing on Windows.
	 * @since	TFP 1.0
	 */
	public static boolean isWindows ()
	{
		return (System.getProperty ("os.name", "").startsWith ("Windows"));
	}

	/**
	 * Determines if the Operating System is Mac OS.
	 *
	 * @return 	<CODE>true</CODE> if the JVM is executing on Mac OS.
	 * @since	TFP 1.0
	 */
	public static boolean isMac ()
	{
		return (System.getProperty ("os.name", "").startsWith ("Mac"));
	}

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
	 * Provides access to the system preferences for this <CODE>Application
	 * </CODE>. For this function to work the derived class must override the
	 * implementation of <CODE>getSystemPreferencesRoot</CODE>.
	 *
	 * @return	The <CODE>Preferences</CODE> node for system settings.
	 * @since	TFP 1.0
	 */
	public final Preferences getSystemPreferences ()
	{
		return (systemPreferences);
	}

	/**
	 * Provides access to the system preferences for this <CODE>Application
	 * </CODE>. For this function to work the derived class must override either
	 * the implementation of <CODE>getSystemPreferencesRoot</CODE> or
	 * <CODE>getUserPreferencesRoot</CODE>.
	 *
	 * @return	The <CODE>Preferences</CODE> node for system settings.
	 * @since	TFP 1.0
	 */
	public final Preferences getUserPreferences ()
	{
		return (userPreferences);
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
	 * Provides an <CODE>Application</CODE> with a chance to perform any
	 * initialisation. This implementation initialises the system and user
	 * preferences. Derived classes may extend the functionality.
	 * @since	TFP 1.0
	 */
	protected void startUp ()
	{
		String			root;
		String			style 	= METAL;

		super.startUp ();

		if ((root = getSystemPreferencesRoot ()) != null)
			systemPreferences = Preferences.systemRoot ().node (root);

		if ((root = getUserPreferencesRoot ()) != null)
			userPreferences = Preferences.userRoot ().node (root);

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
	 * Provides an <CODE>Application</CODE> with a chance to perform any
	 * closing actions. This implementation ensures that preference settings
	 * are flushed. Derived classes may extend the functionality.
	 * @since	TFP 1.0
	 */
	protected void cleanUp ()
	{
		super.cleanUp ();

		try {
			if (systemPreferences != null)
				systemPreferences.flush ();
		}
		catch (BackingStoreException error) {
			logger.log (Level.SEVERE, "Failed to flush system preferences", error);
		}
		finally {
			systemPreferences = null;	
		}

		try {
			if (userPreferences != null)
				userPreferences.flush ();
		}
		catch (BackingStoreException error) {
			logger.log (Level.SEVERE, "Failed to flush user preferences", error);
		}
		finally {
			userPreferences = null;			
		}
	}

	/**
	 * Returns the path for the system preferences. If the value is <CODE>null
	 * </CODE> (as in this default implementation) then no preferences will be
	 * initialised.
	 *
	 * @return	The path name for system preferences, <CODE>null</CODE> if none
	 * 			are required.
	 * @since	TFP 1.0
	 */
	protected String getSystemPreferencesRoot ()
	{
		return (null);
	}
	
	/**
	 * Returns the path for the user preferences. If the value is <CODE>null
	 * </CODE> then no preferences will be initialised. The default
	 * implementation is to copy the system references path.
	 *
	 * @return	The path name for user preferences, <CODE>null</CODE> if none
	 * 			are required.
	 * @since	TFP 1.0
	 */
	protected String getUserPreferencesRoot ()
	{
		return (getSystemPreferencesRoot ());
	}
	
	/**
	 * Converts the instance's member values to <CODE>String</CODE> representations
	 * and concatenates them all together. This function is used by toString and
	 * may be overriden in derived classes.
	 *
	 * @return	The object's <CODE>String</CODE> representation.
	 * @since	TFP 1.0
	 */
	protected String toDebug ()
	{
		StringBuffer		buffer 	= new StringBuffer ();
		
		buffer.append (super.toDebug ());
		buffer.append (",systemPreferences="
			+ ((systemPreferences != null) ? systemPreferences.toString () : "null"));
		buffer.append (",userPreferences="
			+ ((userPreferences   != null) ? userPreferences.toString () : "null"));
		buffer.append (",finished=" + isFinished ());
		
		return (buffer.toString ());
	}

	/**
	 * Constant <CODE>String</CODE> containing the look and feel preference name.
	 */
	private static final String	LOOKANDFEEL		= "lookAndFeel";

	/**
	 * A <CODE>Logger</CODE> instance used to report run-time errors.
	 * @since	TFP 1.0
	 */
	private static Logger		logger
		= Logger.getLogger ("com.handcoded.framework.swing.Application");

	/**
	 * The set of active <CODE>Frame</CODE> windows.
	 */
	private Vector				frames = new Vector ();
	
	/**
	 * The system root <CODE>Preferences</CODE> node.
	 * @since	TFP 1.0
	 */
	private Preferences			systemPreferences	= null;

	/**
	 * The user root <CODE>Preferences</CODE> node.
	 * @since	TFP 1.0
	 */
	private Preferences			userPreferences		= null;
}