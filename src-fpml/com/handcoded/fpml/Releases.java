// Copyright (C),2005-2010 HandCoded Software Ltd.
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

package com.handcoded.fpml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.handcoded.fpml.meta.DTDRelease;
import com.handcoded.fpml.meta.SchemaRelease;
import com.handcoded.meta.Conversion;
import com.handcoded.meta.Release;
import com.handcoded.meta.Specification;

/**
 * The <CODE>Releases</CODE> class contains a set of static objects describing
 * the FpML specification and its various releases.
 * 
 * @author 	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class Releases
{
	/**
	 * A <CODE>Specification</CODE> instance representing FpML as a whole.
	 * @since	TFP 1.0
	 */
	public static Specification	FPML
		= Specification.forName ("FpML");
	
	/**
	 * A <CODE>DTDRelease</CODE> instance containing the details for the
	 * FpML 1-0 recommendation.
	 * @since	TFP 1.0
	 */
	public static DTDRelease	R1_0
		= (DTDRelease) FPML.getReleaseForVersion ("1-0");
	
	/**
	 * A <CODE>DTDRelease</CODE> instance containing the details for the
	 * FpML 2-0 recommendation.
	 * @since	TFP 1.0
	 */
	public static DTDRelease	R2_0
		= (DTDRelease) FPML.getReleaseForVersion ("2-0");

	/**
	 * A <CODE>DTDRelease</CODE> instance containing the details for the
	 * FpML 3-0 trial recommendation.
	 * @since	TFP 1.0
	 */
	public static DTDRelease	R3_0
		= (DTDRelease) FPML.getReleaseForVersion ("3-0");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-0 recommendation.
	 * @since	TFP 1.0
	 */
	public static SchemaRelease	R4_0
		= (SchemaRelease) FPML.getReleaseForVersion ("4-0");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-1 recommendation.
	 * @since	TFP 1.0
	 */
	public static SchemaRelease	R4_1
		= (SchemaRelease) FPML.getReleaseForVersion ("4-1");
			
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-2 recommendation.
	 * @since	TFP 1.0
	 */
	public static SchemaRelease	R4_2
		= (SchemaRelease) FPML.getReleaseForVersion ("4-2");
			
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-3 recommendation.
	 * @since	TFP 1.0
	 */
	public static SchemaRelease	R4_3
		= (SchemaRelease) FPML.getReleaseForVersion ("4-3");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-4 recommendation.
	 * @since	TFP 1.2
	 */
	public static SchemaRelease	R4_4
		= (SchemaRelease) FPML.getReleaseForVersion ("4-4");

	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-5 recommendation.
	 * @since	TFP 1.2
	 */
	public static SchemaRelease	R4_5
		= (SchemaRelease) FPML.getReleaseForVersion ("4-5");

	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-6 recommendation.
	 * @since	TFP 1.3
	 */
	public static SchemaRelease	R4_6
	= (SchemaRelease) FPML.getReleaseForVersion ("4-6");

	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-7 recommendation.
	 * @since	TFP 1.4
	 */
	public static SchemaRelease	R4_7
		= (SchemaRelease) FPML.getReleaseForVersion ("4-7");

	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-8 recommendation.
	 * @since	TFP 1.4
	 */
	public static SchemaRelease	R4_8
		= (SchemaRelease) FPML.getReleaseForVersion ("4-8");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-9 recommendation.
	 * @since	TFP 1.5
	 */
	public static SchemaRelease	R4_9
		= (SchemaRelease) FPML.getReleaseForVersion ("4-9");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 5-0 confirmation view working draft.
	 * @since	TFP 1.3
	 */
	public static SchemaRelease	R5_0_CONFIRMATION
		= (SchemaRelease) FPML.getReleaseForVersionAndNamespace ("5-0", "http://www.fpml.org/FpML-5/confirmation");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 5-0 reporting view working draft.
	 * @since	TFP 1.3
	 */
	public static SchemaRelease	R5_0_REPORTING
		= (SchemaRelease) FPML.getReleaseForVersionAndNamespace ("5-0", "http://www.fpml.org/FpML-5/reporting");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 5-1 confirmation view working draft.
	 * @since	TFP 1.5
	 */
	public static SchemaRelease	R5_1_CONFIRMATION
		= (SchemaRelease) FPML.getReleaseForVersionAndNamespace ("5-1", "http://www.fpml.org/FpML-5/confirmation");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 5-1 reporting view working draft.
	 * @since	TFP 1.5
	 */
	public static SchemaRelease	R5_1_REPORTING
		= (SchemaRelease) FPML.getReleaseForVersionAndNamespace ("5-1", "http://www.fpml.org/FpML-5/reporting");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 5-2 confirmation view working draft.
	 * @since	TFP 1.6
	 */
	public static SchemaRelease	R5_2_CONFIRMATION
		= (SchemaRelease) FPML.getReleaseForVersionAndNamespace ("5-2", "http://www.fpml.org/FpML-5/confirmation");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 5-2 reporting view working draft.
	 * @since	TFP 1.6
	 */
	public static SchemaRelease	R5_2_REPORTING
		= (SchemaRelease) FPML.getReleaseForVersionAndNamespace ("5-2", "http://www.fpml.org/FpML-5/reporting");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 5-2 record keeping view working draft.
	 * @since	TFP 1.6
	 */
	public static SchemaRelease	R5_2_RECORDKEEPING
		= (SchemaRelease) FPML.getReleaseForVersionAndNamespace ("5-2", "http://www.fpml.org/FpML-5/recordkeeping");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 5-2 transparency view working draft.
	 * @since	TFP 1.6
	 */
	public static SchemaRelease	R5_2_TRANSPARENCY
		= (SchemaRelease) FPML.getReleaseForVersionAndNamespace ("5-2", "http://www.fpml.org/FpML-5/transparency");
	
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 1-0 to 2-0
	 * transformation. The specific changes needed (other than basic DOCTYPE
	 * changes) are:
	 * <UL>
	 * <LI>The &lt;product&gt; container element was removed.</LI>
	 * <LI>Superfluous <CODE>type</CODE> attributes are removed.</LI>
	 * </UL> 
	 * @since	TFP 1.0
	 */
	public static final Conversion R1_0__R2_0
		= new Conversions.R1_0__R2_0 ();
		
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 2-0 to 3-0
	 * transformation. The specific changes needed (other than basic DOCTYPE
	 * changes) are:
	 * <UL>
	 * <LI><CODE>href</CODE> attributes are come <CODE>IDREF</CODE> strings
	 * rather then XLink expressions.</LI>
	 * <LI>Superfluous <CODE>type</CODE> attributes are removed.</LI>
	 * </UL> 
	 * @since	TFP 1.0
	 */
	public static final Conversion R2_0__R3_0
		= new Conversions.R2_0__R3_0 ();
			
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 3-0 to 4-0
	 * transformation. The specific changes needed are:
	 * <UL>
	 * <LI>The document is becomes XML schema referencing.</LI>
	 * <LI>Legacy documents become FpML DataDocument instances.</LI>
	 * <LI>The <b>dateRelativeTo</b> referencing mechanism is changed.</LI>
	 * <LI>The value set for &lt;fraDiscounting&gt; was modified.</LI>
	 * <LI>The element &lt;calculationAgentPartyReference&gt; was moved from
	 * the trade header into the trade structure.</LI>
	 * <LI>The &lt;informationSource&gt; element is renamed &lt;primaryRateSource&gt;
	 * within &lt;fxSpotRateSource&gt; elements.</LI>
	 * <LI>The structure of the <B>equityOption</B> element is changed.</li>
	 * <LI>SchemeDefaults are removed and non-defaulted schemes appear
	 * on referencing elements,</LI>
	 * </UL>
	 * @since	TFP 1.0
	 */
	public static final Conversion R3_0__R4_0
		= new Conversions.R3_0__R4_0 ();
	
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 4-0 to 4-1
	 * transformation. The specific changes needed are:
	 * <UL>
	 * <LI>The FpML XML schema namespace URI is updated.</LI>
	 * </UL>
	 * @since	TFP 1.0
	 */
	public static final Conversion R4_0__R4_1
		= new Conversions.R4_0__R4_1 ();
	
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 4-1 to 4-2
	 * transformation. The specific changes needed are:
	 * <UL>
	 * <LI>The FpML XML schema namespace URI is updated.</LI>
	 * </UL>
	 * @since	TFP 1.1
	 */
	public static final Conversion R4_1__R4_2
		= new Conversions.R4_1__R4_2 ();
	
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 4-2 to 4-3
	 * transformation. The specific changes needed are:
	 * <UL>
	 * <LI>The FpML XML schema namespace URI is updated.</LI>
	 * </UL>
	 * @since	TFP 1.2
	 */
	public static final Conversion R4_2__R4_3
		= new Conversions.R4_2__R4_3 ();
	
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 4-3 to 4-4
	 * transformation. The specific changes needed are:
	 * <UL>
	 * <LI>The FpML XML schema namespace URI is updated.</LI>
	 * </UL>
	 * @since	TFP 1.2
	 */
	public static final Conversion R4_3__R4_4
		= new Conversions.R4_3__R4_4 ();
	
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 4-4 to 4-5
	 * transformation. The specific changes needed are:
	 * <UL>
	 * <LI>The FpML XML schema namespace URI is updated.</LI>
	 * </UL>
	 * @since	TFP 1.2
	 */
	public static final Conversion R4_4__R4_5
		= new Conversions.R4_4__R4_5 ();
	
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 4-5 to 4-6
	 * transformation. The specific changes needed are:
	 * <UL>
	 * <LI>The FpML XML schema namespace URI is updated.</LI>
	 * </UL>
	 * @since	TFP 1.3
	 */
	public static final Conversion R4_5__R4_6
		= new Conversions.R4_5__R4_6 ();
	
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 4-7 to 5-0
	 * confirmation transformation. The specific changes needed are:
	 * <UL>
	 * <LI>The FpML XML schema namespace URI is updated.</LI>
	 * </UL>
	 * @since	TFP 1.4
	 */
	public static final Conversion R4_7__R5_0_CONFIRMATION
		= new Conversions.R4_7__R5_0_CONFIRMATION ();
	
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 4-7 to 5-0
	 * reporting transformation. The specific changes needed are:
	 * <UL>
	 * <LI>The FpML XML schema namespace URI is updated.</LI>
	 * </UL>
	 * @since	TFP 1.4
	 */
	public static final Conversion R4_7__R5_0_REPORTING
		= new Conversions.R4_7__R5_0_REPORTING ();
	
	/**
	 * Examines the provided <CODE>Document</CODE> to determine the associated
	 * FpML <CODE>Release</CODE> instance.
	 * 
	 * @param	document		A DOM <CODE>Document</CODE> instance.
	 * @return	The corresponding FpML <CODE>Release</CODE> or <CODE>null</CODE>
	 * 			if it cannot be determined.
	 * @since	TFP 1.0
	 */
	public static Release releaseFor (Document document)
	{
		Element		root = document.getDocumentElement ();
		String		version;
		
		if (root.getLocalName ().equals ("FpML")) {
			if ((version = root.getAttribute ("version")) != null)
				return (FPML.getReleaseForVersion (version));
		}
		
		if ((version = root.getAttribute ("fpmlVersion")) != null)
			return (FPML.getReleaseForVersionAndNamespace (version, root.getNamespaceURI ()));
		
		return (null);
	}

	/**
	 * Ensures that no instances can be constructed.
	 * @since	TFP 1.0
	 */
	private Releases ()
	{ }
}