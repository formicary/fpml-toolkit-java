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

package com.handcoded.meta;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.DOMImplementation;

/**
 * The <CODE>SchemaRelease</CODE> class adds support for the <CODE>Schema</CODE>
 * interface to the base <CODE>Release</CODE> class.
 * 
 * @author 	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public class SchemaRelease extends Release implements Schema
{
	/**
	 * Constructs a <CODE>SchemaRelease</CODE> instance describing a schema
	 * based release of a particular <CODE>Specification</CODE>.
	 * <P>
	 * This constructor should be used when creating a description of a pure
	 * extension schema, i.e. one that contains no useable root elements.
	 * 
	 * @param 	specification	A reference to the owning specification.
	 * @param 	version			The version identifier for this release.
	 * @param 	namespaceUri	The namespace used to identify the schema.
	 * @param 	schemaLocation	The default schema location.
	 * @param 	preferredPrefix	The preferred prefix for the namespace.
	 * @param 	alternatePrefix	The alternate prefix for the namespace.
	 * @since	TFP 1.0
	 */
	public SchemaRelease (Specification specification, final String version,
			final String namespaceUri, final String schemaLocation,
			final String preferredPrefix, final String alternatePrefix)
	{
		this (specification, version, namespaceUri, schemaLocation,
				preferredPrefix, alternatePrefix, (String []) null);
	}
	
	/**
	 * Constructs a <CODE>SchemaRelease</CODE> instance describing a schema
	 * based release of a particular <CODE>Specification</CODE>.
	 * <P>
	 * This constructor should be used when creating a description of a
	 * schema that has only a single root element.
	 * 
	 * @param 	specification	A reference to the owning specification.
	 * @param 	version			The version identifier for this release.
	 * @param 	namespaceUri	The namespace used to identify the schema.
	 * @param 	schemaLocation	The default schema location.
	 * @param 	preferredPrefix	The preferred prefix for the namespace.
	 * @param 	alternatePrefix	The alternate prefix for the namespace.
	 * @param 	rootElement		The normal root element.
	 * @since	TFP 1.0
	 */
	public SchemaRelease (Specification specification, final String version,
			final String namespaceUri, final String schemaLocation,
			final String preferredPrefix, final String alternatePrefix,
			final String rootElement)
	{
		this (specification, version, namespaceUri, schemaLocation,
				preferredPrefix, alternatePrefix, new String [] { rootElement });
	}
	
	/**
	 * Constructs a <CODE>SchemaRelease</CODE> instance describing a schema
	 * based release of a particular <CODE>Specification</CODE>.
	 * <P>
	 * This constructor should be used when creating a description of a
	 * schema that has multiple root elements.
	 * 
	 * @param 	specification	A reference to the owning specification.
	 * @param 	version			The version identifier for this release.
	 * @param 	namespaceUri	The namespace used to identify the schema.
	 * @param 	schemaLocation	The default schema location.
	 * @param 	preferredPrefix	The preferred prefix for the namespace.
	 * @param 	alternatePrefix	The alternate prefix for the namespace.
	 * @param 	rootElements	The set of possible root elements.
	 * @since	TFP 1.0
	 */
	public SchemaRelease (Specification specification, final String version,
			final String namespaceUri, final String schemaLocation,
			final String preferredPrefix, final String alternatePrefix,
			final String [] rootElements)
	{
		super (specification, version, rootElements);
		
		this.namespaceUri    = namespaceUri;
		this.schemaLocation  = schemaLocation;
		this.preferredPrefix = preferredPrefix;
		this.alternatePrefix = alternatePrefix;
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public final String getNamespaceUri ()
	{
		return (namespaceUri);
	}

	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public final String getSchemaLocation ()
	{
		return (schemaLocation);
	}

	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public final String getPreferredPrefix ()
	{
		return (preferredPrefix);
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public final String getAlternatePrefix ()
	{
		return (alternatePrefix);
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public Document newInstance (final String rootElement)
	{
		DOMImplementation	impl = builder.getDOMImplementation ();
		Document		document = impl.createDocument (getNamespaceUri (), rootElement, null);
		Element			root	 = document.getDocumentElement ();
		
		root.setAttributeNS (NAMESPACES_URL, "xmlns", getNamespaceUri ());
		root.setAttributeNS (NAMESPACES_URL, "xmlns:xsi", INSTANCE_URL);
		
		return (document);
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0
	 */
	public boolean isInstance (Document document)
	{
		Element			root = document.getDocumentElement ();
		
		if (root != null) {
			String	namespace = root.getNamespaceURI ();
			String	localName = root.getLocalName ();
			
			if ((namespace != null) && namespace.equals (namespaceUri)) {
				String [] rootElements = getRootElements ();
			
				for (int index = 0; index < rootElements.length; ++index) {
					if (localName.equals(rootElements [index]))
						return (true);
				}
			}
		}
		return (false);
	}
	
	/**
	 * Creates a bi-directional reference between this <CODE>SchemaRelease</CODE>
	 * and the meta data for other instance that it imports.
	 * 
	 * @param 	release			The imported <CODE>SchemaRelease</CODE>.
	 * @since	TFP 1.0
	 */
	public final void addImport (SchemaRelease release)
	{
		this.imports.add (release);
		release.importedBy.add (this);
	}
	
	/**
	 * Breaks the bi-directional reference between this <CODE>SchemaRelease</CODE>
	 * and the indicated one.
	 * 
	 * @param 	release			The <CODE>SchemaRelease</CODE> no longer imported.
	 * @since	TFP 1.0
	 */
	public final void removeImport (SchemaRelease release)
	{
		this.imports.remove (release);
		release.importedBy.remove (this);
	}
	
	/**
	 * Java logging instance.
	 * @since 	TFP 1.0
	 */
	private static Logger		logger
		= Logger.getLogger ("com.handcoded.xml.SchemaRelease");
		
	/**
	 * The <CODE>DocumentBuilder</CODE> configured to create DTD based
	 * <CODE>Document</CODE> instances.
	 * @since	TFP 1.0
	 */
	private static DocumentBuilder builder	= null;

	/**
	 * The namespace URI string.
	 * @since	TFP 1.0
	 */
	private final String		namespaceUri;
	
	/**
	 * The schema location string.
	 * @since	TFP 1.0
	 */
	private final String		schemaLocation;
	
	/**
	 * The preferred prefix for the namespace.
	 * @since	TFP 1.0
	 */
	private final String		preferredPrefix;
	
	/**
	 * The altername prefix for the namespace.
	 * @since	TFP 1.0
	 */
	private final String		alternatePrefix;

	/**
	 * The set of other <CODE>SchemaRelease</CODE> instances imported into this
	 * one.
	 * @since	TFP 1.0
	 */
	private Vector				imports		= new Vector ();
	
	/**
	 * The set of other <CODE>SchemaRelease</CODE> instances that import thia
	 * one.
	 * @since	TFP 1.0
	 */
	private Vector				importedBy	= new Vector ();

	/**
	 * Constructs a document builder used to create new document instances.
	 * @since	TFP 1.0
	 */
	static {
		DocumentBuilderFactory	factory = DocumentBuilderFactory.newInstance ();
		
		factory.setNamespaceAware (false);
		factory.setValidating (false);
		
		try {
			builder = factory.newDocumentBuilder ();
		}
		catch (ParserConfigurationException error) {
			logger.log(Level.SEVERE, "No suitable JAXP DOM implementation for DTD construction", error);
		}
	}
}