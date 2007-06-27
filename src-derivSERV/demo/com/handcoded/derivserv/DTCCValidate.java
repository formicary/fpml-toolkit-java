// Copyright (C),2007 HandCoded Software Ltd.
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

package demo.com.handcoded.derivserv;

import java.io.File;
import java.util.logging.Logger;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.handcoded.fpml.FpMLUtility;
import com.handcoded.fpml.validation.FpMLRules;
import com.handcoded.validation.RuleSet;
import com.handcoded.xml.XPath;
import com.handcoded.xml.XmlUtility;
import com.handcoded.xml.resolver.CatalogManager;

/**
 * This simple application shows how to use the HandCoded Toolkit to validate
 * DTCC DerivSERV messages contained in files. The schemas and examples needed
 * to try this application are only available to DTCC DerivSERV participants
 * and are NOT distributed with this toolkit.
 * <P>
 * Run this command with a list of XML document file names on the command line.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.1
 */
public class DTCCValidate {
	public static void main (String [] arguments)
	{
		// Set up the XML catalog so we can access schemas
		try {
			XmlUtility.setDefaultCatalog (CatalogManager.find ("files/catalog-dtcc.xml"));
		}
		catch (SAXException error) {
			logger.severe ("Failed to parse XML catalog");
			System.exit (1);
		}

		// Configure parser to understand DTCC schemas and SOAP envelope
		XmlUtility.getDefaultSchemaSet ().add (com.handcoded.soapenvelope.Releases.R1_1);
		XmlUtility.getDefaultSchemaSet ().add (com.handcoded.derivserv.Releases.MATCHING_R7_0);
		
		// Create error handlers for both XML and business rule failures
		ParserErrorHandler		parserErrorHandler = new ParserErrorHandler ();
		ValidationErrorHandler	validationErrorHandler = new ValidationErrorHandler ();
		
		// Get a reference to the rule set to use for business rule validation
		RuleSet 				rules = FpMLRules.getRules ();
			
		// Process each of the filenames on the command line
		long start = System.currentTimeMillis();
		int  count = arguments.length;
		for (int index = 0; index < count; ++index) {
			System.out.println ("Parsing: " + arguments [index]);
			
			// Parse the file and then a test against the rule. Output error messages
			// if any problems are found.
			FpMLUtility.parseAndValidate (true, new File (arguments [index]), rules,
					parserErrorHandler, validationErrorHandler);
		}
		long end = System.currentTimeMillis ();
		
		System.err.println ("== Processed " + count + " files in "
			+ (end - start) + " milliseconds");
		System.err.println ("== " + ((1000.0 * count) / (end - start))
			+ " files/sec checking " + rules.size () + " rules");		
	}

	/**
	 * The <CODE>ParserErrorHandler</CODE> provides an implementation of
	 * the SAX <CODE>ErrorHandler</CODE> interface used to report errors
	 * during XML parsing. 
	 * 
	 * @since	TFP 1.1
	 */
	private static class ParserErrorHandler implements org.xml.sax.ErrorHandler
	{
		public void warning (SAXParseException error)
		{
			System.err.println (error.getMessage ());
		}	
		
		public void error (SAXParseException error)
		{
			System.err.println (error.getMessage ());
		}	

		public void fatalError (SAXParseException error)
		{
			System.err.println (error.getMessage ());
		}	
	}
	
	/**
	 * The <CODE>ValidationErrorHandler</CODE> implements the <CODE>ErrorHandler
	 * </CODE> interface used by the validation toolkit to report semantic errors.
	 * 
	 * @since	TFP 1.1
	 */
	private static class ValidationErrorHandler implements com.handcoded.validation.ValidationErrorHandler
	{
		public void error (String code, Node context, String description, String ruleName, String additionalData)
		{
			if (additionalData != null)
				System.err.println (ruleName + " " + XPath.forNode(context) + "\n" + description + " [" + additionalData + "]");
			else
				System.err.println (ruleName + " " + XPath.forNode(context) + "\n" + description);
		}
	}
	
	/**
	 * Logging instance used for error reporting.
	 * @since	TFP 1.1
	 */
	private static Logger		logger
		= Logger.getLogger ("demo.com.handcoded.derivserv.DTCCValidate");
}
