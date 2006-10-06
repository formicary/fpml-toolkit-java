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
import com.handcoded.xml.resolver.Catalog;
import com.handcoded.xml.resolver.CatalogManager;

/**
 * The <CODE>Application</CODE> contains some standard option handling
 * that is common to all FpML demonstration applications.
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
	 * A command line option that allows the default catalog to be overriden.
	 * @since	TFP 1.0
	 */
	private Option		catalogOption
		= new Option ("-catalog", "Use url to create an XML catalog for parsing", "url");

	/**
	 * The <CODE>Catalog</CODE> instance if specified by the options.
	 * @since	TFP 1.0
	 */
	private Catalog		catalog	= null;
}