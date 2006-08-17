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

package com.handcoded.framework;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * The <CODE>Application</CODE> class provides a basic application
 * framework. Derived classes extend its functionality and specialise
 * it to a particular task.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public abstract class Application
{
	/**
	 * Returns the current <CODE>Application</CODE> instance.
	 *
	 * @return	The <CODE>Application</CODE> instance.
	 * @since	TFP 1.0
	 */
	public static Application getApplication ()
	{
		return (application);
	}

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
	 * Causes the <CODE>Application</CODE> to process it's command line arguments
	 * and begin the execution cycle.
	 *
	 * @param	arguments		The array of command line arguments.
	 * @since	TFP 1.0
	 */
	public void run (
	String			arguments [])
	{
		this.arguments = Option.processArguments (arguments);
		
		startUp ();
		while (!finished)
			execute ();
		cleanUp ();
	}

	/**
	 * Provides access to the flag used to determine <CODE>Application</CODE>
	 * execution.
	 *
	 * @return	<CODE>true</CODE> if execution is finished, <CODE>false</CODE>
	 *			otherwise.
	 * @since	TFP 1.0
	 */
	public final boolean isFinished ()
	{
		return (finished);
	}

	/**
	 * Allows the caller to change the value of the finished flag.
	 *
	 * @param	finished			The new value for the flag.
	 * @since	TFP 1.0
	 */
	public final void setFinished (final boolean finished)
	{
		this.finished = finished;
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
	 * Converts the instance data members to a <CODE>String</CODE> representation
	 * that can be displayed for debugging purposes.
	 *
	 * @return 	The object's <CODE>String</CODE> representation.
	 * @since	TFP 1.0
	 */ 
	public String toString ()
	{
		return (getClass ().getName () + "[" + toDebug () + "]");
	}
	
	/**
	 * Constructs an <CODE>Application</CODE> instance and records it.
	 * @since	TFP 1.0
	 */
	protected Application ()
	{
		application = this;
	}

	/**
	 * Provides an <CODE>Application</CODE> with a chance to perform any
	 * initialisation. This implementation checks for the -help option and
	 * initialises the system and user preferences. Derived classes may
	 * extend the functionality.
	 * @since	TFP 1.0
	 */
	protected void startUp ()
	{
		String			root;
		
		if (helpOption.isPresent ()) {
			System.err.println ("Usage:\n    java " + this.getClass ().getName ()
					+ Option.listOptions () + describeArguments ());
			System.err.println ();
			System.err.println ("Options:");
			Option.describeOptions ();
			System.exit (1);
		}

		if ((root = getSystemPreferencesRoot ()) != null)
			systemPreferences = Preferences.systemRoot ().node (root);

		if ((root = getUserPreferencesRoot ()) != null)
			userPreferences = Preferences.userRoot ().node (root);
	}

	/**
	 * The <CODE>execute</CODE> method should perform one program execution
	 * cycle. The method is called repeatedly until the finished flag is set.
	 * @since	TFP 1.0
	 */
	protected abstract void execute ();

	/**
	 * Provides an <CODE>Application</CODE> with a chance to perform any
	 * closing actions. This implementation ensures that preference settings
	 * are flushed. Derived classes may extend the functionality.
	 * @since	TFP 1.0
	 */
	protected void cleanUp ()
	{
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
	 * Provides a text description of the arguments expected after the options
	 * (if any), for example "file ...". This method should be overridden in a
	 * derived class requiring a non-empty argument list.
	 * 
	 * @return	A description of the expected application arguments.
	 * @since	TFP 1.0
	 */
	protected String describeArguments ()
	{
		return ("");
	}

	/**
	 * Provides access to the command line arguments after any option processing
	 * has been applied.
	 *
	 * @return	The command line arguments.
	 * @since	TFP 1.0
	 */
	protected final String [] getArguments ()
	{
		return (arguments);
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
		
		buffer.append ("arguments=");
		if (arguments != null) {
			buffer.append ('[');
			for (int index = 0; index != arguments.length; ++index) {
				if (index != 0) buffer.append (',');
				
				if (arguments [index] != null)
					buffer.append ("\"" + arguments [index]+ "\"");
				else
					buffer.append ("null");
			}
			buffer.append (']');
		}
		else
			buffer.append ("null");
			
		buffer.append (",systemPreferences="
			+ ((systemPreferences != null) ? systemPreferences.toString () : "null"));
		buffer.append (",userPreferences="
			+ ((userPreferences   != null) ? userPreferences.toString () : "null"));
		buffer.append (",finished=" + finished);
		
		return (buffer.toString ());
	}

	/**
	 * A <CODE>Logger</CODE> instance used to report run-time errors.
	 * @since	TFP 1.0
	 */
	private static Logger		logger
		= Logger.getLogger ("com.handcoded.framework.Application");

	/**
	 * The one and only <CODE>Application</CODE> instance.
	 * @since	TFP 1.0
	 */
	private static Application	application 		= null;

	/**
	 * The <CODE>Option</CODE> instance used to detect <CODE>-help</CODE>
	 * @since	TFP 1.0
	 */
	private Option				helpOption
		= new Option ("-help",	"Displays help information");

	/**
	 * The command line arguments after processing.
	 * @since	TFP 1.0
	 */
	private String				arguments []		= null;
	
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

	/**
	 * A <CODE>boolean</CODE> flag to indicate that we are done
	 * @since	TFP 1.0
	 */
	private boolean				finished 			= false;
}