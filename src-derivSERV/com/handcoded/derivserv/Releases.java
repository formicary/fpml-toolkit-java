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

package com.handcoded.derivserv;

import com.handcoded.fpml.meta.FpMLInstanceInitialiser;
import com.handcoded.fpml.meta.FpMLSchemaRecogniser;
import com.handcoded.fpml.meta.SchemeDefaults;
import com.handcoded.fpml.schemes.SchemeCollection;
import com.handcoded.meta.SchemaRelease;
import com.handcoded.meta.Specification;

/**
 * This class holds a meta-description of the schemas used by the DTCC DerivSERV
 * messages.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.1
 */
public final class Releases
{
	/**
	 * A <CODE>Specification</CODE> instance representing the modified FpML
	 * schemas used by the DTCC.
	 * @since	TFP 1.1
	 */
	public static final Specification	DTCC_FPML
		= new Specification ("DTCC FpML Variant");
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * DTCC modified FpML 4-3 working draft.
	 * @since	TFP 1.1
	 */
	public static SchemaRelease	FPML_WD4_3
		= new com.handcoded.fpml.meta.SchemaRelease (DTCC_FPML, "4-3",
				"http://www.fpml.org/2007/FpML-4-3", "fpml-main-4-3.xsd",
				"fpml", "fpml4-3", new FpMLInstanceInitialiser (), new FpMLSchemaRecogniser (), "FpML",
				new SchemeDefaults (
						new String [][] {
							{	"additionalTermScheme",
								"http://www.fpml.org/spec/2003/additional-term-1-0" },
							{	"businessCenterScheme",
								"http://www.fpml.org/spec/2000/business-center-1-0" },
							{	"clearanceSystemIdScheme",
								"http://www.fpml.org/spec/2002/clearance-system-1-0" },
							{	"contractualDefinitionsScheme",
								"http://www.fpml.org/spec/2003/contractual-definitions-2-0" },
							{	"contractualSupplementScheme",
								"http://www.fpml.org/spec/2003/contractual-supplement-1-0" },
							{	"countryScheme",
								"http://www.fpml.org/ext/iso3166" },
							{	"currencyScheme",
								"http://www.fpml.org/ext/iso4217-2001-08-15" },
							{	"cutNameScheme",
								"http://www.fpml.org/spec/2002/cut-name-1-0" },
							{	"exchangeIdScheme",
								"http://www.fpml.org/spec/2002/exchange-id-MIC-1-0" },
							{	"floatingRateIndexScheme",
								"http://www.fpml.org/ext/isda-2000-definitions" },
							{	"fxFeatureTypeScheme",
								"http://www.fpml.org/spec/2003/fxFeatureType-1-0" },
							{	"governingLawScheme",
								"http://www.fpml.org/spec/2002/governing-law-1-0" },
							{	"informationProviderScheme",
								"http://www.fpml.org/spec/2003/information-provider-2-0" },
							{	"marketDisruptionScheme",
								"http://www.fpml.org/spec/2003/marketDisruption-1-0" },
							{	"masterAgreementTypeScheme",
								"http://www.fpml.org/spec/2002/master-agreement-type-1-0" },
							{	"masterConfirmationTypeScheme",
								"http://www.fpml.org/spec/2003/master-confirmation-type-1-0" },
							{	"partyIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{	"restructuringScheme",
								"http://www.fpml.org/spec/2003/restructuring-1-0" },
							{ 	"routingIdScheme",
								"http://www.fpml.org/spec/2002/routing-id-code-1-0" },
							{ 	"settlementMethodScheme",
								"http://www.fpml.org/spec/2002/settlement-method-1-0" },
							{ 	"settlementPriceSourceScheme",
								"http://www.fpml.org/spec/2002/settlement-price-source-1-0" }
						}),
						parseSchemes ("schemes4-3.xml"));
	
	/**
	 * A <CODE>Specification</CODE> instance representing the DTCC matching
	 * message schemas.
	 * @since	TFP 1.1
	 */
	public static final Specification	DTCC_MATCHING
		= new Specification ("DTCC OTC Matching");
	
	/**
	 * A <CODE>SchemaRelease</CODE> describing the basic properties of the
	 * DTCC 7.0 matching schema.
	 * @since	TFP 1.1 
	 */
	public static final SchemaRelease	MATCHING_R7_0
		= new SchemaRelease (DTCC_MATCHING, "7-0",
				"OTC_Matching_7-0", "OTC_Matching_7-0.xsd",
				"matching", null);

	/**
	 * A <CODE>Specification</CODE> instance representing the DTCC message
	 * wrapper schemas.
	 * @since	TFP 1.1
	 */
	public static final Specification	DTCC_RM
		= new Specification ("DTCC OTC RM");
	
	/**
	 * A <CODE>SchemaRelease</CODE> describing the basic properties of the
	 * DTCC 7.0 message wrapper schema.
	 * @since	TFP 1.1
	 */
	public static final SchemaRelease	RM_R7_0
		= new SchemaRelease (DTCC_RM, "7-0",
				"OTC_RM_7-0", "OTC_RM_7-0.xsd",
				"rm", null);
	
	/**
	 * Attempts to build a <CODE>SchemeCollection</CODE> instance for an
	 * indicated FpML release.
	 * @param 	suffix			Indicates which version of FpML.
	 * @return	A populated <CODE>SchemeCollection</CODE> instance.
	 */
	private static SchemeCollection parseSchemes (String suffix)
	{
		SchemeCollection	schemes = new SchemeCollection ();
		
		schemes.parse ("files/data/" + suffix);
		schemes.parse ("files/data/additionalDefinitions.xml");
		
		return (schemes);
	}
	
	/**
	 * Add inter-schema dependencies to the meta-description.
	 */
	static
	{
		FPML_WD4_3.addImport (com.handcoded.dsig.Releases.R1_0);
		
		RM_R7_0.addImport (FPML_WD4_3);
		
		MATCHING_R7_0.addImport (FPML_WD4_3);
		MATCHING_R7_0.addImport (RM_R7_0);
	}
}