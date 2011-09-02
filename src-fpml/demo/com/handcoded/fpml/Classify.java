// Copyright (C),2005-2011 HandCoded Software Ltd.
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

package demo.com.handcoded.fpml;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.handcoded.classification.Category;
import com.handcoded.fpml.classification.FpMLProduct;
import com.handcoded.meta.Release;
import com.handcoded.meta.Specification;
import com.handcoded.xml.NodeIndex;
import com.handcoded.xml.XmlUtility;
import com.handcoded.xml.parser.DOMParser;

/**
 * This application demonstrates the classification components being used to
 * identify the type of product within an FpML document based on its structure.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public class Classify extends Application
{
	/**
	 * Creates an application instance and invokes its <CODE>run</CODE>
	 * method passing the command line arguments.
	 * 
	 * @param 	arguments		The command line arguments
	 * @since	TFP 1.0
	 */
	public static void main (String [] arguments)
	{   
		new Classify ().run (arguments);
	}

	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	protected void startUp ()
	{
		super.startUp ();
		
		if (getArguments ().length == 0) {
			logger.severe ("No files are present on the command line");
			System.exit (1);
		}
		
		XmlUtility.getDefaultSchemaSet ().getSchema ();
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	protected void execute ()
	{
		String []		arguments 	= getArguments ();
		Document		document;
		NodeIndex		nodeIndex;
		
		
		arguments = findFiles (arguments);

		try {
			//DOMParser		parser		= 
			//	new DOMParser (false, true, false, null, XmlUtility.getDefaultCatalog (), null);
			
			for (int index = 0; index < arguments.length; ++index) {
				String filename = arguments [index];
				document = XmlUtility.nonValidatingParse (new File (filename));
				//document = parser.parse (new File (filename));
				
				System.out.println (">> " + filename);
				
//				XmlUtility.dump (document);

				Release release = Specification.releaseForDocument (document);
				
				if (release != null) System.out.println ("= " + release);
				
				nodeIndex = new NodeIndex (document);
				classify (nodeIndex.getElementsByName ("trade"), "Trade");
				classify (nodeIndex.getElementsByName ("contract"), "Contract");
			}
		}
		catch (Exception error) {
			logger.log (Level.SEVERE, "Unexpected exception during processing", error);
		}
		
		setFinished (true);
	}
	
	/**
	 * {@inheritDoc} 
	 * @since	TFP 1.0
	 */
	protected String describeArguments ()
	{
		return (" files ...");
	}
	
	/**
	 * A <CODE>Logger</CODE> instance used to report serious errors.
	 * @since	TFP 1.0
	 */
	private static Logger	logger
		= Logger.getLogger ("demo.com.handcoded.fpml.Classify");

	/**
	 * Uses the predefined FpML product types to attempt to classify a
	 * product within the document.
	 *  
	 * @param 	list		A set of context elements to analyse.
	 * @param 	container	The type of product container for display.
	 * @since	TFP 1.0.1
	 */
	private void classify (final NodeList list, final String container)
	{
		for (int index = 0; index < list.getLength (); ++index) {
			Category	category = FpMLProduct.FPML_PRODUCT.classify ((Element) list.item (index));
			
			System.out.print (": " + container + "(");
			System.out.print ((category != null) ? category.toString () : "UNKNOWN");
			System.out.println (")");
		}
	}
}