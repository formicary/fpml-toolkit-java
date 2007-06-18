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

import org.xml.sax.SAXException;

/**
 * The <CODE>NextCatalog</CODE> class implements catalog chaining.
 * @since	TFP 1.0
 */
final class NextCatalogEntry extends RelativeEntry implements EntityRule, UriRule
{
	/**
	 * Constructs a <CODE>DelegatePublic</CODE> instance that will direct
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
	public String applyTo (
	final String		publicId,
	final String		systemId,
	Stack				catalogs)
		throws SAXException
	{
		return (CatalogManager.find (catalog).getDefinition ().applyRules (publicId, systemId, catalogs));
	}

	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public String applyTo (
	final String		uri,
	Stack				catalogs)
		throws SAXException
	{
		return (CatalogManager.find (catalog).getDefinition ().applyRules (uri, catalogs));
	}

	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	protected String toDebug ()
	{
		return ("catalog=\"" + catalog + "\"," + super.toDebug ());
	}

	/**
	 * The URI of the catalog to chain to.
	 * @since	TFP 1.0
	 */
	final String			catalog;
}