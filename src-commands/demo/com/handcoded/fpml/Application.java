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

package demo.com.handcoded.fpml;

import java.util.logging.Logger;

import org.xml.sax.SAXException;

import com.handcoded.framework.Option;
import com.handcoded.xml.parser.DOMParser;
import com.handcoded.xml.resolver.Catalog;
import com.handcoded.xml.resolver.CatalogManager;

/**
 * The <CODE>Application</CODE> contains some standard option handling
 * and Xerces XML parser configuration settings that are common to all FpML
 * demonstration applications.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public abstract class Application extends com.handcoded.framework.Application
{
	/**
	 * Constructs a <CODE>Application</CODE> instance.
	 * @since	TFP 1.0
	 */
	protected Application ()
	{ }
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	protected void startUp ()
	{
		super.startUp();
		
		if (catalogOption.isPresent ()) {
			if (catalogOption.getValue() != null) {
				try {
					catalog = CatalogManager.find (catalogOption.getValue ());
				}
				catch (SAXException error) {
					logger.severe ("Failed to parse XML catalog");
					System.exit (1);
				}
			}
			else
				logger.severe ("Missing argument for -catalog option");
		}
	}
	
	/**
	 * Provides access to the <CODE>Catalog</CODE> instance to be used for
	 * entity resolution. If the <CODE>-catalog</CODE> option was not specified
	 * then the result will be <CODE>null</CODE>
	 * 
	 * @return	The <CODE>Catalog</CODE> instance or <CODE>null</CODE>.
	 * @since	TFP 1.0
	 */
	protected final Catalog getCatalog ()
	{
		return (catalog);
	}
	
	/**
	 * Logging instance used for error reporting.
	 * @since	TFP 1.0
	 */
	private static Logger		logger
		= Logger.getLogger ("demo.com.handcoded.fpml");
	
	/**
	 * A string constant used to reference the Xerces property related to
	 * parser configuration.
	 * @since	TFP 1.0
	 */
	private static final String	XML_PARSER_CONFIGURATION
		= "org.apache.xerces.xni.parser.XMLParserConfiguration";
	
	/**
	 * A string constant used to configure Xerces to use its grammar caching
	 * configuration.
	 * @since	TFP 1.0
	 */
	private static final String XML_GRAMMAR_CACHING_CONFIGURATION
		= "org.apache.xerces.parsers.XMLGrammarCachingConfiguration";
	
	/**
	 * A string constant used to reference a Xerces parser property that allows
	 * the value of the <CODE>schemaLocation</CODE> attribute in an instance
	 * document to be overriden.
	 * @since	TFP 1.0
	 */
	private static final String	EXTERNAL_SCHEMALOCATION_PROPERTY
		= "http://apache.org/xml/properties/schema/external-schemaLocation";
	
	/**
	 * TODO
	 */
	private Option		catalogOption
		= new Option ("-catalog", "Use url to create an XML catalog for parsing", "url");

	/**
	 * The <CODE>Catalog</CODE> instance if specified by the options.
	 * @since	TFP 1.0
	 */
	private Catalog		catalog	= null;
	
	/**
	 * This initialiser configures the Xerces XML parser to use schema caching
	 * as standard and sets up <CODE>schemaLocation</CODE> mapping for known
	 * FpML and DSIG releases.
	 * @since	TFP 1.0
	 */
	static {
		System.setProperty (XML_PARSER_CONFIGURATION, XML_GRAMMAR_CACHING_CONFIGURATION);
		
		if (!DOMParser.setAttribute (EXTERNAL_SCHEMALOCATION_PROPERTY,
				com.handcoded.fpml.Releases.R4_0.getNamespaceUri ()  + " urn:tfp:fpml-4-0 " +
				com.handcoded.fpml.Releases.R4_1.getNamespaceUri ()  + " urn:tfp:fpml-4-1 " +
				com.handcoded.fpml.Releases.TR4_2.getNamespaceUri () + " urn:tfp:fpml-4-2 " +
				com.handcoded.dsig.Releases.R1_0.getNamespaceUri ()  + " urn:tfp:dsig")) {
			logger.severe ("JAXP implementation does not support schemaLocation mapping");
			System.exit (2);
		}
	}
}