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
import java.util.Vector;

import org.xml.sax.SAXException;

/**
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.1
 */
class GroupEntry extends RelativeEntry  
{
	/**
	 * Constructs a <CODE>GroupEntry</CODE> given its containing entry
	 * and attribute values.
	 * 
	 * @param 	parent			The containing element.
	 * @param 	prefer			Optional <CODE>prefer</CODE> value.
	 * @param 	xmlbase			Optional <CODE>xml:base</CODE> value.
	 * @since	TFP 1.1
	 */
	public GroupEntry (final GroupEntry parent, final String prefer, final String xmlbase)
	{
		super (parent, xmlbase);
		
		this.prefer	= prefer;
	}
	
	public final String getPrefer ()
	{
		if (prefer != null) return (prefer);
		
		CatalogComponent parent = getParent ();
		
		if (parent instanceof GroupEntry)
			return (((GroupEntry) parent).getPrefer());
		
		return (null);
	}
	
	/**
	 * Adds a rule to the catalog directing the given public identifier to
	 * the indicated URI.
	 *
	 * @param	publicId		The public identifier to be mapped.
	 * @param	uri				The corresponding URI.
	 * @param	xmlbase			Optional <CODE>xml:base</CODE> value.
	 * @since	TFP 1.0
	 */
	public final PublicEntry addPublic (final String publicId, final String uri, final String xmlbase)
	{
		PublicEntry		result = new PublicEntry (this, publicId, uri, xmlbase);
		
		rules.add (result);
		return (result);
	}
	
	/**
	 * Adds a rule to the catalog directing the given public identifier to
	 * the indicated URI.
	 *
	 * @param	publicId		The public identifier to be mapped.
	 * @param	uri				The corresponding URI.
	 * @since	TFP 1.0
	 */
	public final PublicEntry addPublic (final String publicId, final String uri)
	{
		return (addPublic (publicId, uri, null));
	}

	/**
	 * Adds a rule to the catalog directing the given system identifier to
	 * the indicated URI.
	 *
	 * @param	systemId		The system identifier to be mapped.
	 * @param	uri				The corresponding URI.
	 * @param	xmlbase			Optional xml:base value.
	 * @since	TFP 1.0
	 */
	public final SystemEntry addSystem (final String systemId, final String uri, final String xmlbase)
	{
		SystemEntry		result = new SystemEntry (this, systemId, uri, xmlbase);
		
		rules.add (result);
		return (result);
	}

	/**
	 * Adds a rule to the catalog directing the given system identifier to
	 * the indicated URI.
	 *
	 * @param	systemId		The system identifier to be mapped.
	 * @param	uri				The corresponding URI.
	 * @since	TFP 1.0
	 */
	public final SystemEntry addSystem (final String systemId, final String uri)
	{
		return (addSystem (systemId, uri, null));
	}

	/**
	 * Adds a rule to the catalog which will cause a matching system identifier
	 * to its starting prefix replaced.
	 *
	 * @param	startString		The system identifier prefix to match.
	 * @param	rewritePrefix	The new prefix to replace with.
	 * @since	TFP 1.0
	 */
	public final RewriteSystemEntry addRewriteSystem (final String startString, final String rewritePrefix)
	{
		RewriteSystemEntry	result = new RewriteSystemEntry (this, startString, rewritePrefix);
		
		rules.add (result);
		return (result);
	}

	/**
	 * Adds a rule to the catalog which will direct the resolution for any
	 * system identifier that starts with the given prefix to a sub-catalog
	 * file.
	 *
	 * @param	startString		The system identifier prefix to match
	 * @param	catalog			The URI of the sub-catalog.
	 * @param	xmlbase			Optional xml:base value.
	 * @since	TFP 1.0
	 */
	public final DelegateSystemEntry addDelegateSystem (final String startString, final String catalog,
			final String xmlbase)
	{
		DelegateSystemEntry	result  = new DelegateSystemEntry (this, startString, catalog, xmlbase);
		
		rules.add (result);
		return (result);
	}

	/**
	 * Adds a rule to the catalog which will direct the resolution for any
	 * system identifier that starts with the given prefix to a sub-catalog
	 * file.
	 *
	 * @param	startString		The system identifier prefix to match
	 * @param	catalog			The URI of the sub-catalog.
	 * @since	TFP 1.0
	 */
	public final DelegateSystemEntry addDelegateSystem (final String startString, final String catalog)
	{
		return (addDelegateSystem (startString, catalog, null));
	}

	/**
	 * Adds a rule to the catalog which will direct the resolution for any
	 * public identifier that starts with the given prefix to a sub-catalog
	 * file.
	 *
	 * @param	startString		The public identifier prefix to match
	 * @param	catalog			The URI of the sub-catalog.
	 * @param	xmlbase			The optional xml:base URI
	 * @since	TFP 1.0
	 */
	public final DelegatePublicEntry addDelegatePublic (final String startString, final String catalog,
			final String xmlbase)
	{
		DelegatePublicEntry	result  = new DelegatePublicEntry (this, startString, catalog, xmlbase);
		
		rules.add (result);
		return (result);
	}

	/**
	 * Adds a rule to the catalog which will direct the resolution for any
	 * public identifier that starts with the given prefix to a sub-catalog
	 * file.
	 *
	 * @param	startString		The public identifier prefix to match
	 * @param	catalog			The URI of the sub-catalog.
	 * @since	TFP 1.0
	 */
	public final DelegatePublicEntry addDelegatePublic (final String startString, final String catalog)
	{
		return (addDelegatePublic (startString, catalog, null));
	}
	
	public final UriEntry addUri (final String name, final String uri, final String xmlbase)
	{
		UriEntry	result  = new UriEntry (this, name, uri, xmlbase);
		
		rules.add (result);
		return (result);
	}
	
	public final UriEntry addUri (final String name, final String uri)
	{
		return (addUri (name, uri, null));
	}
	
	public final RewriteUriEntry addRewriteUri (final String startString, final String rewritePrefix)
	{
		RewriteUriEntry		result = new RewriteUriEntry (this, startString, rewritePrefix);
		
		rules.add (result);
		return (result);
	}
	
	public final DelegateUriEntry addDelegateUri (final String startString, final String catalog, final String xmlbase)
	{
		DelegateUriEntry	result = new DelegateUriEntry (this, startString, catalog, xmlbase);
		
		rules.add (result);
		return (result);
	}
	
	public final DelegateUriEntry addDelegateUri (final String startString, final String catalog)
	{
		return (addDelegateUri (startString, catalog, null));
	}
	
	/**
	 * Adds a rule which will cause resolution to chain to another catalog
	 * if no match can be found in this one.
	 * <P>
	 * This method is only available to the <CODE>XmlCatalogManager</CODE>
	 * class.
	 *
	 * @param	catalog			The URI of the catalog to chain to.
	 * @param	xmlbase			The optional xml:base URI
	 * @since	TFP 1.0
	 */
	public final NextCatalogEntry addNextCatalog (final String catalog, final String xmlbase)
	{
		NextCatalogEntry	result = new NextCatalogEntry (this, catalog, xmlbase);
		
		rules.add (result);
		return (result);
	}

	/**
	 * Adds a rule which will cause resolution to chain to another catalog
	 * if no match can be found in this one.
	 * <P>
	 * This method is only available to the <CODE>CatalogManager</CODE>
	 * class.
	 *
	 * @param	catalog			The URI of the catalog to chain to.
	 * @since	TFP 1.0
	 */
	public final NextCatalogEntry addNextCatalog (final String catalog)
	{
		return (addNextCatalog (catalog, null));
	}

	/**
	 * The set of all rules in order of definition.
	 * @since	TFP 1.0
	 */
	protected Vector			rules		= new Vector ();

	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	protected String toDebug ()
	{
		StringBuffer		buffer 	= new StringBuffer ();
		
		buffer.append ("prefer=" + ((prefer == null) ? "null" : ("\"" + prefer + "\"")) + ",");
		buffer.append ("rules={");
		for (int index = 0; index < rules.size (); ++index) {
			if (index != 0) buffer.append (',');
			
			buffer.append (rules.elementAt (index).toString ());
		}
		buffer.append ('}');
		buffer.append (super.toDebug());
		
		return (buffer.toString ());
	}
	
	/**
	 * Implements the OASIS search rules for entity resources.
	 *
	 * @param	publicId		The public identifier of the external entity
	 *							being referenced, or null if none was supplied.
	 * @param	systemId		The system identifier of the external entity
	 *							being referenced.
	 * @param	catalogs		A stack of catalogs being processed used to
	 *							detect circular dependency.
	 * @return  The URI of the resolved entity or <CODE>null</CODE>.
	 * @throws	SAXException If an error occurs during processing.
	 * @since	TFP 1.0
	 */
	protected String applyRules (final String publicId, final String systemId, Stack catalogs)
		throws SAXException
	{
		String				result = null;

		if (catalogs.contains (this))
			throw new SAXException ("Circular dependency in the XML Catalogs");

		catalogs.push (this);

		// If a system identifier is provided then try to match it explicitly
		// or through a rewriting rule or delegation.
		if ((systemId != null) && (systemId.length () > 0)) {
			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof SystemEntry) {
					if ((result = ((EntityRule) rules.elementAt (index))
							.applyTo (publicId, systemId, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}

			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof RewriteSystemEntry) {
					if ((result = ((EntityRule) rules.elementAt (index))
							.applyTo (publicId, systemId, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}

			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof DelegateSystemEntry) {
					if ((result = ((EntityRule) rules.elementAt (index))
							.applyTo (publicId, systemId, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}
		}

		// If a public identifier is provided then try to match it explicity
		// or through delegation.
		if ((publicId != null) && (publicId.length () > 0)) {
			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof PublicEntry) {
					if ((result = ((EntityRule) rules.elementAt (index))
							.applyTo (publicId, systemId, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}

			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof DelegatePublicEntry) {
					if ((result = ((EntityRule) rules.elementAt (index))
							.applyTo (publicId, systemId, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}
		}

		// Finally try any other chained catalogs
		for (int index = 0; index < rules.size (); ++index) {
			if (rules.elementAt (index) instanceof NextCatalogEntry) {
				if ((result = ((EntityRule) rules.elementAt (index))
						.applyTo (publicId, systemId, catalogs)) != null) {

					catalogs.pop ();
					return (result);
				}
			}
		}

		catalogs.pop ();
		return (null);
	}

	/**
	 * Implements OASIS search rules for URI based resources.
	 * 
	 * @param 	uri				The URI of the required resource.
	 * @param	catalogs		A stack of catalogs being processed used to
	 *							detect circular dependency.
	 * @return  The URI of the resolved entity or <CODE>null</CODE>.
	 * @throws	SAXException If an error occurs during processing.
	 */
	protected String applyRules (final String uri, Stack catalogs)
		throws SAXException
	{
		String				result = null;

		if (catalogs.contains (this))
			throw new SAXException ("Circular dependency in the XML Catalogs");

		catalogs.push (this);

		if ((uri != null) && (uri.length () > 0)) {
			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof UriEntry) {
					if ((result = ((UriRule) rules.elementAt (index))
							.applyTo (uri, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}

			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof RewriteUriEntry) {
					if ((result = ((UriRule) rules.elementAt (index))
							.applyTo (uri, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}

			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof DelegateUriEntry) {
					if ((result = ((UriRule) rules.elementAt (index))
							.applyTo (uri, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}
		}

		// Finally try any other chained catalogs
		for (int index = 0; index < rules.size (); ++index) {
			if (rules.elementAt (index) instanceof NextCatalogEntry) {
				if ((result = ((UriRule) rules.elementAt (index))
						.applyTo (uri, catalogs)) != null) {

					catalogs.pop ();
					return (result);
				}
			}
		}

		catalogs.pop ();
		return (null);
	}

	/**
	 * The value of the prefer attribute.
	 * @since	TFP 1.1
	 */
	private final String		prefer;
}