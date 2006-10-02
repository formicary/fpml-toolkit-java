// Copyright (C),2006 HandCoded Software Ltd.
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

package com.handcoded.xml;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

/**
 * The <CODE>SchemaSet</CODE> class hold a collection of <CODE>StreamSource</CODE>
 * instance that reference schemas known to the application. The collection can
 * be compiled into a <CODE>Schema</CODE> instance for use in the JAXP validation
 * framework.
 * 
 * @author 	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class SchemaSet
{
	/**
	 * Constructs a <CODE>SchemaSet</CODE>.
	 * @since	TFP 1.0
	 */
	public SchemaSet ()
	{ }

	/**
	 * Adds a new <CODE>StreamSource</CODE> to the set. Any previously compiled
	 * representation is discarded.
	 * 
	 * @param 	source			The <CODE>StreamSource</CODE> to be added.
	 * @since	TFP 1.0
	 */
	public void add (StreamSource source)
	{
		sources.add (source);
	}
	
	/**
	 * Returns the compiled representation of the schemas, if neccesary compiling
	 * them from thier source streams.
	 * 
	 * @return	The compiled schema representation for the set.
	 * @since	TFP 1.0
	 */
	public Schema getSchema ()
	{
		if (schema == null) {
			Source [] sourceArray = new Source [sources.size()];
			sources.copyInto (sourceArray);

			try {
				schema = SchemaFactory.newInstance (XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema (sourceArray);
			}
			catch (SAXException error) {
				logger.log (Level.SEVERE, "Unexpected SAX Exception", error);
			}
		}
		return (schema);
	}

	/**
	 * A <CODE>Logger</CODE> instance used to report serious errors.
	 * @since	TFP 1.0
	 */
	private static Logger	logger
		= Logger.getLogger ("com.handcoded.xml.SchemaSet");

	/**
	 * The set of <CODE>StreamSource</CODE> instances for the schemas.
	 */
	private Vector			sources		= new Vector ();
	
	/**
	 * The compiled schema representation of the schemas.
	 */
	private Schema			schema		= null;	
}