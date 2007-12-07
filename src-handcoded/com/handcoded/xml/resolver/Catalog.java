// Copyright (C),2005-2007 HandCoded Software Ltd.
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

package com.handcoded.xml.resolver;

import java.util.Stack;
import java.util.logging.Logger;

import javax.xml.transform.stream.StreamSource;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * The <CODE>Catalog</CODE> provides a configurable <CODE>EntityResolver
 * </CODE> for a SAX or DOM based XML parser.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class Catalog implements EntityResolver
{
	/**
	 * Returns the URL associated with this catalog.
	 *
	 * @return 	The URL of the catalog.
	 * @since	TFP 1.0
	 */
	public String getUrl ()
	{
		return (url);
	}
	
	/**
	 * Looks up a publicId and/or systemId in the dictionary to see if a local
	 * resource is available for it.
	 *
	 * @param	publicId		The public identifier of the external entity
	 *							being referenced, or null if none was supplied.
	 * @param	systemId		The system identifier of the external entity
	 *							being referenced.
	 * @return  An InputSource object describing the new input source, or
	 *			<CODE>null</CODE> to request that the parser open a regular
	 *			URI connection to the system identifier.
	 * @throws	SAXException If a error occurred creating the new <CODE>
	 *			InputSource</CODE>.
	 * @since	TFP 1.0
	 */
	public final InputSource resolveEntity (final String publicId, final String	systemId)
		throws SAXException
	{
//		logger.info ("\npublicId = " + ((publicId != null) ? publicId : "null") +
//					 "\nsystemId = " + ((systemId != null) ? systemId : "null"));

		String				result = definition.applyRules (publicId, systemId, new Stack ());
		
//		logger.info ("\nresult   = " + ((result   != null) ? result   : "null"));

		if (result != null) {
			if (result.startsWith ("file:")) {
				logger.info ("Opening " + result);
				return (new InputSource (result));
			}
			else {
				logger.info ("Opening " + Catalog.class.getClassLoader().getResource (result).toExternalForm());
				return (new InputSource (Catalog.class.getClassLoader().getResource (result).toExternalForm()));
			}
		}
		
		return (null);
	}

	/**
	 * Attempts to resolve a URI into a <CODE>SourceStream</CODE>
	 * using the rules within the <CODE>Catalog</CODE>.
	 * 
	 * @param 	uri			The URI to be resolved.
	 * @return 	A <CODE>StreamSource</CODE> connected to the resolved resource
	 * 			or <CODE>null</CODE> if the URI was not resolved.
	 * @since	TFP 1.0
	 */
	public final StreamSource resolveUri (final String uri)
		throws SAXException
	{
		String				result = resolve (uri);

		if (result != null) {
			if (result.startsWith ("file:"))
				return (new StreamSource (result));
			else {
				logger.info ("?? " + Catalog.class.getClassLoader().getResource (result).toExternalForm());
				return (new StreamSource (Catalog.class.getClassLoader().getResource (result).toExternalForm()));
			}
		}
		return (null);	
	}
	
	/**
	 * Attempts to resolve a URI.
	 * 
	 * @param 	uri			The URI to be resolved.
	 * @return 	A xxx <CODE>null</CODE> if the URI was not resolved.
	 * @since	TFP 1.0
	 */
	public final String resolve (final String uri)
		throws SAXException
	{
//		logger.info ("\nuri    = " + ((uri != null) ? uri : "null"));

		String				result = definition.applyRules (uri, new Stack ());

//		logger.info ("\nresult = " + ((result != null) ? result   : "null"));
		return (result);	
	}
	
	/**
	 * Constructs a new <CODE>Catalog</CODE> instance.
	 *
	 * @param	url				The URL of this <CODE>Catalog</CODE>.
	 * @param 	prefer			Optional <CODE>prefer</CODE> value.
	 * @param 	xmlbase			Optional <CODE>xml:base</CODE> value.
	 * @since	TFP 1.0
	 */
	protected Catalog (final String url, final String prefer, final String xmlbase)
	{
		this.url  = url;

		definition = new CatalogEntry (prefer, (xmlbase != null) ? xmlbase : url);
	}
	
	/**
	 * Converts the instance's member values to <CODE>String</CODE> representations
	 * and concatenates them all together. This function is used by toString and
	 * may be overriden in derived classes.
	 *
	 * @return	The object's <CODE>String</CODE> representation.
	 * @since	TFP 1.1
	 */
	protected String toDebug ()
	{
		return ("url=\"" + url + "\",definition={" + definition.toDebug() + "}");
	}
	
	/**
	 * Logging instance used to record runtime problems.
	 * @since	TFP 1.0
	 */
	private static Logger		logger
		= Logger.getLogger ("com.handcoded.xml.resolver.Catalog");

	/**
	 * The URL of this catalog.
	 * @since	TFP 1.0
	 */
	private final String		url;
	
	/**
	 * The component containing the catalog.
	 */
	private final CatalogEntry	definition;
	
	/**
	 * Provides access to the catalog definition.
	 * 
	 * @return	The <CODE>CatalogEntry</CODE> describing the catalog.
	 * @since	TFP 1.1
	 */
	CatalogEntry getDefinition ()
	{
		return (definition);
	}
}