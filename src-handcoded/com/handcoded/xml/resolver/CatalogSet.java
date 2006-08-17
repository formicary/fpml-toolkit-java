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

import java.util.Vector;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * A <CODE>XmlCatalogSet</CODE> instance contains a collection of <CODE>
 * XmlCatalog</CODE> instances that can be used to resolve XML entity
 * references. The <CODE>XmlCatalogSet</CODE> passes each resolution request
 * to each constituent <CODE>XmlCatalog</CODE> until either a match is found
 * or all the catalogs have been tried.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class CatalogSet implements EntityResolver
{
	/**
	 * Constructs a <CODE>CatalogSet</CODE> containing an empty catalog
	 * collection.
	 * 
	 * @since	TFP 1.0
	 */
    public CatalogSet()
    {
        catalogs = new Vector();
    }

	/**
	 * Adds a <CODE>Catalog</CODE> to the collection.
	 *
	 * @param	catalog			The <CODE>Catalog</CODE> to add.
	 * @since	TFP 1.0
	 */
	public void addCatalog (final Catalog catalog)
	{
		catalogs.addElement (catalog);
	}

	/**
	 * Removes a <CODE>Catalog</CODE> from the collection.
	 *
	 * @param	catalog			The <CODE>Catalog</CODE> to remove.
	 * @since	TFP 1.0
	 */
	public void removeCatalog (
	final Catalog	catalog)
	{
		catalogs.removeElement (catalog);
	}

	/**
	 * Looks up a publicId and/or systemId in the dictionary to see if a local
	 * resource is available for it. Each catalog in the set is tried in turn
	 * until a match is found or the set is exhausted.
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
    public final InputSource resolveEntity(final String publicId, final String systemId)
        throws SAXException
    {
        InputSource			inputSource = null;
        
        for (int index = 0; index < catalogs.size(); ++index)
        	if ((inputSource = ((Catalog) catalogs.elementAt (index)).resolveEntity (publicId, systemId)) != null)
        		break;
        		
        return (inputSource);
    }
    
	/**
	 * Converts the instance data members to a <CODE>String</CODE> representation
	 * that can be displayed for debugging purposes.
	 *
	 * @return 	The object's <CODE>String</CODE> representation.
	 * @since	TFP 1.0
	 */ 
	public String toString ()
	{
		return (getClass ().getName () + " [" + toDebug () + "]");
	}
	
	/**
	 * Converts the instance's member values to <CODE>String</CODE> representations
	 * and concatenates them all together. This function is used by toString and
	 * may be overriden in derived classes.
	 *
	 * @return	The object's <CODE>String</CODE> representation.
	 * @since	TFP 1.0
	 */
	protected String toDebug ()
	{
		StringBuffer		buffer 	= new StringBuffer ();

		buffer.append ("catalogs=[");
		for (int index = 0; index < catalogs.size (); ++index) {
			if (index != 0) buffer.append (',');
			
			buffer.append (catalogs.elementAt (index).toString ());
		}
		buffer.append (']');		
		
		return (buffer.toString ());
	}

	/**
	 * The <CODE>Catalog</CODE> instances that comprise the set.
	 * @since	TFP 1.0
	 */
    private Vector catalogs;
}