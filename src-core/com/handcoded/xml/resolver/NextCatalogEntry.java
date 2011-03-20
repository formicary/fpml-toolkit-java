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

package com.handcoded.xml.resolver;

import java.net.URI;
import java.util.Stack;
import java.net.URISyntaxException;

import org.xml.sax.SAXException;

/**
 * The <CODE>NextCatalog</CODE> class implements catalog chaining.
 * @since	TFP 1.0
 */
final class NextCatalogEntry extends RelativeEntry implements EntityRule, UriRule
{
	/**
	 * Constructs a <CODE>NextCatalogEntry</CODE> instance that will direct
	 * searches to another catalog.
	 *
	 * @param	catalog			The URI of the chained catalog.
	 * @param	xmlbase			The optional xml:base URI
	 * @since	TFP 1.0
	 */
	public NextCatalogEntry (final GroupEntry parent, final String catalog,
			final String xmlbase)
	{
		super (parent, xmlbase);
		
		this.catalog = catalog;
	}

	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public String applyTo (final String publicId, final String systemId,
			Stack<GroupEntry> catalogs)
		throws SAXException
	{
		try {
			return (CatalogManager.find (baseAsUri ().resolve (new URI (catalog)).toString ())
				.getDefinition ().applyRules (publicId, systemId, catalogs));
		}
		catch (URISyntaxException error) {
			return (null);
		}
	}

	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public String applyTo (final String uri, Stack<GroupEntry> catalogs)
		throws SAXException
	{
		try {
			return (CatalogManager.find (baseAsUri ().resolve (new URI (catalog)).toString ())
				.getDefinition ().applyRules (uri, catalogs));
		}
		catch (URISyntaxException error) {
			return (null);
		}
	}

	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	@Override
	protected String toDebug ()
	{
		return ("catalog=\"" + catalog + "\"," + super.toDebug ());
	}

	/**
	 * The URI of the catalog to chain to.
	 * @since	TFP 1.0
	 */
	private final String		catalog;
}