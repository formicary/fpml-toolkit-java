// Copyright (C),2007 HandCoded Software Ltd.
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

package com.handcoded.soapenvelope;

import com.handcoded.meta.SchemaRelease;
import com.handcoded.meta.Specification;

/**
 * The <CODE>Releases</CODE> class contains a set of static objects describing
 * the W3C SOAP 1.1 envelope specification.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.1
 */
public class Releases
{
	/**
	 * A <CODE>Specification</CODE> instance representing SOAP as a whole.
	 * @since	TFP 1.1
	 */
	public final static Specification	SOAP_ENVELOPE
		= new Specification ("SOAP");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * SOAP 1.1 recommendation.
	 * @since	TFP 1.`
	 */
	public final static SchemaRelease	R1_1
		= new SchemaRelease (SOAP_ENVELOPE, "1-1",
				"http://schemas.xmlsoap.org/soap/envelope/", "soap-envelope.xsd",
				"env", null);
}