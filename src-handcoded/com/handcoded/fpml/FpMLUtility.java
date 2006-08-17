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

package com.handcoded.fpml;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.handcoded.fpml.validation.AllRules;
import com.handcoded.meta.SchemaRelease;
import com.handcoded.validation.RuleSet;
import com.handcoded.validation.ValidationErrorHandler;
import com.handcoded.xml.parser.DOMParser;
import com.handcoded.xml.resolver.Catalog;
import com.handcoded.xml.resolver.CatalogManager;

/**
 * The <CODE>FpMLUtility</b> class contains a set of methods for performing 
 * common operations on FpML document instances.
 * 
 * @author	BitWise
 * @since	TFP 1.0
 */
public final class FpMLUtility
{
	/**
	 * Provides access to the compile schema collection used by the parse
	 * functions.
	 * <P>
	 * Calling this function will force the runtime to load all the known
	 * FpML schemas into memory if it has not already been done.
	 * 
	 * @return	The default <CODE>Schema</CODE> collection.
	 * @since	TFP 1.0
	 */
	public static Schema getSchema ()
	{
		return (schema);
	}
	
	/**
	 * Parses the XML string provided passing any validation problems to
	 * the indicated <CODE>ErrorHandler</CODE>.
	 *
	 * @param	xml				The XML string to be parsed.
	 * @param	errorHandler	The <CODE>ErrorHandler</CODE> instance or <CODE>null</CODE>
	 * @return	A <CODE>Document</CODE> instance constructed from the XML document.
	 * @since	TFP 1.0
	 */
	public static Document parse (final String xml, ErrorHandler errorHandler)
	{
		try {
			DOMParser	parser = new DOMParser (false, true, null, getCatalog (), errorHandler);
			Document	document = parser.parse (xml);
			
			if ((document != null) && (document.getDoctype() != null)) {
				parser = new DOMParser (true, true, null, getCatalog (), errorHandler);
				document = parser.parse (xml);
			}
			else {
				DOMResult		result	= new DOMResult ();
				
				Validator validator = schema.newValidator ();
				validator.setErrorHandler (errorHandler);
				validator.validate (new DOMSource (document), result);
				
				document = (Document) result.getNode ();
			}			
			return (document);
		}
		catch (ParserConfigurationException error) {
			logger.severe ("JAXP failed to provided a XML parser");
		}
		catch (SAXException error) {
			logger.log (Level.SEVERE, "Unexpected SAX Exception", error);
		}
		catch (IOException error) {
			logger.log (Level.SEVERE, "Unexpected I/O error", error);
		}
		return (null);
	}

	/**
	 * Parses an XML document from the given <CODE>InputSource</CODE> passing
	 * any reported errors to the <CODE>ErrorHandler</CODE> instance.
	 *
	 * @param	source			The <CODE>InputSource</CODE> to process XML from.
	 * @param	errorHandler	The <CODE>ErrorHandler</CODE> instance or <CODE>null</CODE>
	 * @return	A <CODE>Document</CODE> instance constructed from the XML document.
	 * @since	TFP 1.0
	 */
	public static Document parse (InputSource source, ErrorHandler errorHandler)
	{
		try {
			DOMParser	parser = new DOMParser (false, true, null, getCatalog (), errorHandler);
			Document	document = parser.parse (source);
			
			if ((document != null) && (document.getDoctype() != null)) {
				parser = new DOMParser (true, true, null, getCatalog (), errorHandler);
				document = parser.parse (source);
			}
			else {
				DOMResult		result	= new DOMResult ();
				
				Validator validator = schema.newValidator ();
				validator.setErrorHandler (errorHandler);
				validator.validate (new DOMSource (document), result);
				
				document = (Document) result.getNode ();
			}			
			return (document);
		}
		catch (ParserConfigurationException error) {
			logger.severe ("JAXP failed to provided a XML parser");
		}
		catch (SAXException error) {
			logger.log (Level.SEVERE, "Unexpected SAX Exception", error);
		}
		catch (IOException error) {
			logger.log (Level.SEVERE, "Unexpected I/O error", error);
		}
		return (null);
	}
	
	/**
	 * Parses an XML document from the given <CODE>File</CODE> passing
	 * any reported errors to the <CODE>EerorHandler</CODE> instance.
	 *
	 * @param	file			The <CODE>File</CODE> to process XML from.
	 * @param	errorHandler	The <CODE>ErrorHandler</CODE> instance or <CODE>null</CODE>
	 * @return	A <CODE>Document</CODE> instance constructed from the XML document.
	 * @since	TFP 1.0
	 */
	public static Document parse (File file, ErrorHandler errorHandler)
	{
		try {
			DOMParser	parser = new DOMParser (false, true, null, getCatalog (), errorHandler);
			Document	document = parser.parse (file);
			
			if ((document != null) && (document.getDoctype() != null)) {
				parser = new DOMParser (true, true, null, getCatalog (), errorHandler);
				document = parser.parse (file);
			}
			else {
				DOMResult		result	= new DOMResult ();
				
				Validator validator = schema.newValidator ();
				validator.setErrorHandler (errorHandler);
				validator.validate (new DOMSource (document), result);
				
				document = (Document) result.getNode ();
			}			
			return (document);
		}
		catch (ParserConfigurationException error) {
			logger.severe ("JAXP failed to provided a XML parser");
		}
		catch (SAXException error) {
			logger.log (Level.SEVERE, "Unexpected SAX Exception", error);
		}
		catch (IOException error) {
			logger.log (Level.SEVERE, "Unexpected I/O error", error);
		}
		return (null);
	}
	
	/**
	 * Uses the given <CODE>RuleSet</CODE> to perform a semantic validation of
	 * the DOM <CODE>Document</CODE> and reports errors (if any).
	 *
	 * @param 	document		The <CODE>Document</CODE> to be validated.
	 * @param 	rules			The <CODE>RuleSet</CODE> to use.
	 * @param 	validationErrorHandler	The <CODE>ValidationErrorHandler</CODE> used to report issues.
	 * @return	<CODE>true</CODE> if the <CODE>Document</CODE> successfully passed
	 * 			all applicable rules, <CODE>false</CODE> if one or more rules
	 * 			failed.
	 * @since	TFP 1.0
	 */
	public static boolean validate (final Document document, final RuleSet rules, final ValidationErrorHandler validationErrorHandler)
	{
		return (rules.validate (document, validationErrorHandler));
	}

	/**
	 * Uses the default FpML  <CODE>RuleSet</CODE> to perform a semantic validation of
	 * the DOM <CODE>Document</CODE> and reports errors (if any).
	 *
	 * @param 	document		The <CODE>Document</CODE> to be validated.
	 * @param 	validationErrorHandler	The <CODE>ValidationErrorHandler</CODE> used to semantic report issues.
	 * @return	<CODE>true</CODE> if the <CODE>Document</CODE> successfully passed
	 * 			all applicable rules, <CODE>false</CODE> if one or more rules
	 * 			failed.
	 * @since	TFP 1.0
	 */
	public static boolean validate (final Document document, final ValidationErrorHandler validationErrorHandler)
	{
		return (validate (document, AllRules.getRules (), validationErrorHandler));
	}

	/**
	 * Attempts to parse an XML document from a string and then pass it through
	 * the specified validation rule set.
	 * <P>
	 * This function does not provide any access to the DOM <CODE>Document</CODE>
	 * created during the parsing process.
	 * 
	 * @param	xml				The XML string to be parsed.
	 * @param 	rules			The <CODE>RuleSet</CODE> used for validation.
	 * @param 	errorHandler	The <CODE>ErrorHandler</CODE> used to report parser errors.
	 * @param 	validationErrorHandler	The <CODE>ValidationErrorHandler</CODE> used to semantic report issues.
	 * @since	TFP 1.0
	 */
	public static boolean parseAndValidate (final String xml, RuleSet rules, ErrorHandler errorHandler, ValidationErrorHandler validationErrorHandler)
	{
		Document		document = parse (xml, errorHandler);

		return ((document != null) ? rules.validate (document, validationErrorHandler) : false);
	}

	/**
	 * Attempts to parse an XML document from the indicated <CODE>InputSource</CODE>
	 * and then pass it through the specified validation rule set.
	 * <P>
	 * This function does not provide any access to the DOM <CODE>Document</CODE>
	 * created during the parsing process.
	 * 
	 * @param	source			The <CODE>InputSource</CODE> to be parsed.
	 * @param 	rules			The <CODE>RuleSet</CODE> used for validation.
	 * @param 	errorHandler	The <CODE>ErrorHandler</CODE> used to report parser errors.
	 * @param 	validationErrorHandler	The <CODE>ValidationErrorHandler</CODE> used to semantic report issues.
	 * @since	TFP 1.0
	 */
	public static boolean parseAndValidate (InputSource source, RuleSet rules, ErrorHandler errorHandler, ValidationErrorHandler validationErrorHandler)
	{
		Document		document = parse (source, errorHandler);

		return ((document != null) ? rules.validate (document, validationErrorHandler) : false);
	}
	
	/**
	 * Attempts to parse an XML document from the indicated <CODE>File</CODE>
	 * and then pass it through the specified validation rule set.
	 * <P>
	 * This function does not provide any access to the DOM <CODE>Document</CODE>
	 * created during the parsing process.
	 * 
	 * @param	file			The <CODE>File</CODE> to be parsed.
	 * @param 	rules			The <CODE>RuleSet</CODE> used for validation.
	 * @param 	errorHandler	The <CODE>ErrorHandler</CODE> used to report parser errors.
	 * @param 	validationErrorHandler	The <CODE>ValidationErrorHandler</CODE> used to semantic report issues.
	 * @since	TFP 1.0
	 */
	public static boolean parseAndValidate (File file, RuleSet rules, ErrorHandler errorHandler, ValidationErrorHandler validationErrorHandler)
	{
		Document		document = parse (file, errorHandler);

		return ((document != null) ? rules.validate (document, validationErrorHandler) : false);
	}
	
	/**
	 * Attempts to parse an XML document from a string and then pass it through
	 * the default FpML validation rule set.
	 * <P>
	 * This function does not provide any access to the DOM <CODE>Document</CODE>
	 * created during the parsing process.
	 * 
	 * @param	xml				The XML string to be parsed.
	 * @param 	errorHandler	The <CODE>ErrorHandler</CODE> used to report parser errors.
	 * @param 	validationErrorHandler	The <CODE>ValidationErrorHandler</CODE> used to semantic report issues.
	 * @since	TFP 1.0
	 */
	public static boolean parseAndValidate (final String xml, ErrorHandler errorHandler, ValidationErrorHandler validationErrorHandler)
	{
		return (parseAndValidate (xml, AllRules.getRules (), errorHandler, validationErrorHandler));
	}

	/**
	 * Attempts to parse an XML document from the indicated <CODE>InputSource</CODE>
	 * and then pass it through the default FpML validation rule set.
	 * <P>
	 * This function does not provide any access to the DOM <CODE>Document</CODE>
	 * created during the parsing process.
	 * 
	 * @param	source			The <CODE>InputSource</CODE> to be parsed.
	 * @param 	errorHandler	The <CODE>ErrorHandler</CODE> used to report parser errors.
	 * @param 	validationErrorHandler	The <CODE>ValidationErrorHandler</CODE> used to semantic report issues.
	 * @since	TFP 1.0
	 */
	public static boolean parseAndValidate (InputSource source, ErrorHandler errorHandler, ValidationErrorHandler validationErrorHandler)
	{
		return (parseAndValidate (source, AllRules.getRules (), errorHandler, validationErrorHandler));
	}

	/**
	 * Attempts to parse an XML document from the indicated <CODE>File</CODE>
	 * and then pass it through the default FpML validation rule set.
	 * <P>
	 * This function does not provide any access to the DOM <CODE>Document</CODE>
	 * created during the parsing process.
	 * 
	 * @param	file			The <CODE>File</CODE> to be parsed.
	 * @param 	errorHandler	The <CODE>ErrorHandler</CODE> used to report parser errors.
	 * @param 	validationErrorHandler	The <CODE>ValidationErrorHandler</CODE> used to semantic report issues.
	 * @since	TFP 1.0
	 */
	public static boolean parseAndValidate (File file, ErrorHandler errorHandler, ValidationErrorHandler validationErrorHandler)
	{
		return (parseAndValidate (file, AllRules.getRules (), errorHandler, validationErrorHandler));
	}

	/**
	 * Provides access to the <CODE>Catalog</CODE> instance to be used for
	 * entity resolution. If the <CODE>-catalog</CODE> option was not specified
	 * then the result will be <CODE>null</CODE>
	 * 
	 * @return	The <CODE>Catalog</CODE> instance or <CODE>null</CODE>.
	 * @since	TFP 1.0
	 */
	protected static Catalog getCatalog ()
	{
		return (catalog);
	}
	
	/**
	 * A <CODE>Logger</CODE> instance used to report serious errors.
	 * @since	TFP 1.0
	 */
	private static Logger	logger
		= Logger.getLogger ("com.handcoded.fpml.FpMLUtility");

	/**
	 * The catalog used to resolve DTD and schema references.
	 * @since	TFP 1.0
	 */
	private static Catalog		catalog;

	/**
	 * The schema collection used to validate schema based documents.
	 * @since	TFp 1.0
	 */
	private static Schema		schema;
		
	/**
	 * Ensures no instances can be created
	 * @since	TFP 1.0
	 */
	private FpMLUtility()
	{ }
	
	/**
	 * This routine attempts to resolve a schemas target namespace through the
	 * catalog to its external location. A <CODE>StreamSource</CODE> is created for
	 * each schema add to the <CODE>sources</CODE> vector.
	 * 
	 * @param 	sources			A <CODE>Vector</CODE> schemas to process later.
	 * @param 	release			The <CODE>SchemaRelease</CODE> to resolve.
	 * @since	TFP 1.0
	 */
	private static void resolveSchema (Vector sources, SchemaRelease release)
	{
		try {
			StreamSource	source = catalog.resolveUri (release.getNamespaceUri());
			
			if (source != null) 
				sources.add (source);
			else {
				logger.severe ("Failed to resolve schema URI '" + release.getNamespaceUri() + "'");
				sources.add (new StreamSource (release.getSchemaLocation()));
			}
		}
		catch (SAXException error) {
			logger.log (Level.SEVERE, "Unexpected SAX exception creating schema source", error);
			System.exit (2);
		}		
	}
	
	/**
	 * Construct a catalog and schema collection when this class is first
	 * accessed.
	 */
	static {
		
		try {
			catalog = CatalogManager.find ("com/handcoded/fpml/catalog.xml");
		}
		catch (SAXException error) {
			logger.log (Level.SEVERE, "Unexpected SAX exception loading schema catalog", error);
			System.exit (2);
		}
	
		try {
			Vector sources	= new Vector ();

			resolveSchema (sources, com.handcoded.dsig.Releases.R1_0);
			resolveSchema (sources, com.handcoded.fpml.Releases.R4_0);
			resolveSchema (sources, com.handcoded.fpml.Releases.R4_1);
			resolveSchema (sources, com.handcoded.fpml.Releases.TR4_2);
			resolveSchema (sources, com.handcoded.acme.Releases.R1_0);
			
			Source [] sourceArray = new Source [sources.size()];
			sources.copyInto (sourceArray);

			schema = SchemaFactory.newInstance (XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema (sourceArray);
		}
		catch (SAXException error) {
			logger.log (Level.SEVERE, "Unexpected SAX exception compiling schemas", error);
			System.exit (2);
		}	
	}
}