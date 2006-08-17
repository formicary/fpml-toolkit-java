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

package com.handcoded.xml.resolver;

import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.handcoded.xml.parser.SAXParser;

/**
 * The <CODE>CatalogManager</CODE> maintains a cache of <CODE>Catalog</CODE>
 * instances created on their first access. Subsequent requests for a previously
 * processed catalog will always be satisified from the cache.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class CatalogManager
{
	/**
	 * Creates an <CODE>Catalog</CODE> instance from the definition held in
	 * the indicated URL. If the file has been requested previously then the
	 * cached object is returned.
	 *
	 * @param	url				The catalog's URI.
	 * @param	validate		Determines if the catalog should be validated.
	 * @return	The <CODE>Catalog</CODE> instance created from the URL.
	 * @throws 	SAXException If the catalog file could not be parsed correctly.
	 * @since	TFP 1.0
	 */
	public static Catalog find (String url, final boolean validate)
		throws SAXException
	{
		Catalog 			catalog;
		
		url = CatalogManager.class.getClassLoader().getResource (url).toString();

		if ((catalog = (Catalog) catalogs.get (url)) == null) {
			try {
				SAXParser parser = new SAXParser (validate, true, validate, false, null, null);

				catalog = new Catalog (url);

				parser.parse (
					new InputSource (url),
					new CatalogHandler (catalog));
			}
			catch (ParserConfigurationException error)
			{
				logger.log (Level.SEVERE, "No suitable JAXP implementation installed", error);
				System.exit (2);
			}
			catch (IOException error) {
				throw new SAXException ("I/O problem whilst parsing XML catalog", error);
			}

			catalogs.put (url, catalog);
		}
		return (catalog);
	}

	/**
	 * Creates an <CODE>Catalog</CODE> instance from the definition held in
	 * the indicated URL. If the file has been requested previously then the
	 * cached object is returned.
	 *
	 * @param	url				The catalog's URI.
	 * @return	The <CODE>Catalog</CODE> instance created from the URL.
	 * @throws 	SAXException If the catalog file could not be parsed correctly.
	 * @since	TFP 1.0
	 */
	public static Catalog find (final String url)
		throws SAXException
	{
		return (find (url, false));
	}
	
	/**
	 * Logging instance used to record runtime problems.
	 * @since	TFP 1.0
	 */
	private static Logger		logger
		= Logger.getLogger ("com.handcoded.xml.resolver.CatalogManager");
	
	/**
	 * The set of previously processed catalogs indexed by filename.
	 * @since	TFP 1.0
	 */
	private static Hashtable	catalogs = new Hashtable ();

	/**
	 * This <CODE>ContentHandler</CODE> instance is used to handle the parse
	 * events and update the underlying <CODE>LookupEntityResolver</CODE>
	 * @since	TFP 1.0
	 */
	private static class CatalogHandler	extends DefaultHandler
	{
		/**
		 * Constructs a <CODE>CatalogHandler</CODE> and records the details of
		 * the <CODE>Catalog</CODE> to be poplulated by the parse.
		 *
		 * @param	catalog			The <CODE>Catalog</CODE> to populate.
		 * @since	TFP 1.0
		 */
		public CatalogHandler (Catalog catalog)
		{
			this.catalog = catalog;
		}

		/**
		 * Validates the element tags as they are detected and extracts the
		 * mapping information.
		 *
		 * @param	namespace		The namespace of the parsed element.
		 * @param	localName		The name of the parsed element.
		 * @param	QName			The QName of the parsed element.
		 * @param	attributes		The set of attributes on the element.
		 * @throws 	SAXException If the element syntax is incorrect.
		 * @since 	TFP 1.0
		 */
		public void startElement (String namespace, String localName, String QName, Attributes attributes)
			throws SAXException
		{
			if (localName.equals ("catalog"))
				;
			else if (localName.equals ("group"))
				;
			else if (localName.equals ("public")) {
				String publicId 	= attributes.getValue ("publicId");
				String uri 			= attributes.getValue ("uri");
				String base			= attributes.getValue ("xml:base");

				catalog.addPublic (publicId, uri, base);
			}
			else if (localName.equals ("system")) {
				String systemId		= attributes.getValue ("systemId");
				String uri			= attributes.getValue ("uri");
				String base			= attributes.getValue ("xml:base");

				catalog.addSystem (systemId, uri, base);
			}
			else if (localName.equals ("rewriteSystem")) {
				String startString	= attributes.getValue ("systemIdStartString");
				String rewritePrefix= attributes.getValue ("rewritePrefix");

				catalog.addRewriteSystem (startString, rewritePrefix);
			}
			else if (localName.equals ("delegatePublic")) {
				String startString	= attributes.getValue ("publicIdStartString");
				String file			= attributes.getValue ("catalog");
				String base			= attributes.getValue ("xml:base");

				catalog.addDelegatePublic (startString, file, base);
			}
			else if (localName.equals ("delegateSystem")) {
				String startString	= attributes.getValue ("systemIdStartString");
				String file			= attributes.getValue ("catalog");
				String base			= attributes.getValue ("xml:base");

				catalog.addDelegateSystem (startString, file, base);
			}
			else if (localName.equals ("uri")) {
				String name			= attributes.getValue ("name");
				String uri			= attributes.getValue ("uri");
				String base			= attributes.getValue ("xml:base");
				
				catalog.addUri (name, uri, base);
			}
			else if (localName.equals ("rewriteUri")) {
				String startString	= attributes.getValue ("uriStartString");
				String rewritePrefix= attributes.getValue ("rewritePrefix");
				
				catalog.addRewriteUri (startString, rewritePrefix);
			}
			else if (localName.equals ("delegateUri")) {
				String startString	= attributes.getValue ("uriStartString");
				String file			= attributes.getValue ("catalog");
				String base			= attributes.getValue ("xml:base");

				catalog.addDelegateUri (startString, file, base);
			}
			else if (localName.equals ("nextCatalog")) {
				String file			= attributes.getValue ("catalog");
				String base			= attributes.getValue ("xml:base");

				catalog.addNextCatalog (file, base);
			}
			else
				throw new SAXException ("Unexpected element tag in XML catalog file");
		}

		/**
		 * The <CODE>Catalog</CODE> instance to populate from the XML file.
		 * @since 	TFP 1.0
		 */
		private Catalog		catalog;
	};

	/**
	 * Prevents an instance from being created.
	 * 
	 * @since	TFP 1.0
	 */
	private CatalogManager ()
	{ }
}