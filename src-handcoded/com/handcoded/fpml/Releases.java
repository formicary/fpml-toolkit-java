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

package com.handcoded.fpml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.handcoded.fpml.meta.DTDRelease;
import com.handcoded.fpml.meta.FpMLInstanceInitialiser;
import com.handcoded.fpml.meta.FpMLSchemaRecogniser;
import com.handcoded.fpml.meta.SchemaRelease;
import com.handcoded.fpml.meta.SchemeDefaults;
import com.handcoded.fpml.schemes.SchemeCollection;
import com.handcoded.meta.Conversion;
import com.handcoded.meta.InstanceInitialiser;
import com.handcoded.meta.Release;
import com.handcoded.meta.SchemaRecogniser;
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
	 * The <CODE>InstanceInitialiser</CODE> used to populate new documents.
	 * @since	TFP 1.1
	 */
	private static InstanceInitialiser	initialiser	= new FpMLInstanceInitialiser ();
	
	/**
	 * The <CODE>SchemaRecogniser</CODE> used to detect schema based documents.
	 * @since	TFP 1.1
	 */
	private static SchemaRecogniser		recogniser	= new FpMLSchemaRecogniser ();
		
	/**
	 * A <CODE>Specification</CODE> instance representing FpML as a whole.
	 * @since	TFP 1.0
	 */
	public static Specification	FPML
		= new Specification ("FpML");
	
	/**
	 * A <CODE>DTDRelease</CODE> instance containing the details for the
	 * FpML 1-0 recommendation.
	 * @since	TFP 1.0
	 */
	public static DTDRelease	R1_0
		= new DTDRelease (FPML, "1-0",
				"-//FpML//DTD Financial product Markup Language 1-0//EN",
				"fpml-dtd-1-0-2001-05-14.dtd", "FpML",
				new SchemeDefaults (
					new String [][] {
						{	"averagingMethodSchemeDefault",
							"http://www.fpml.org/spec/2000/averaging-method-1-0" },
						{	"businessCenterSchemeDefault",
							"http://www.fpml.org/spec/2000/business-center-1-0" },
						{	"businessDayConventionSchemeDefault",
							"http://www.fpml.org/spec/2000/business-day-convention-1-0" },
						{	"compoundingMethodSchemeDefault",
							"http://www.fpml.org/spec/2000/compounding-method-1-0" },
						{	"currencySchemeDefault",
							"http://www.fpml.org/ext/iso4217-2001-08-15" },
						{	"dateRelativeToSchemeDefault",
							"http://www.fpml.org/spec/2001/date-relative-to-2-0" },
						{	"dayCountFractionSchemeDefault",
							"http://www.fpml.org/spec/2000/day-count-fraction-1-0" },
						{	"dayTypeSchemeDefault",
							"http://www.fpml.org/spec/2000/day-type-1-0" },
						{	"discountingTypeSchemeDefault",
							"http://www.fpml.org/spec/2000/discounting-type-1-0" },
						{	"floatingRateIndexSchemeDefault",
							"http://www.fpml.org/ext/isda-2000-definitions" },
						{	"negativeInterestRateTreatmentSchemeDefault",
							"http://www.fpml.org/spec/2001/negative-interest-rate-treatment-1-0" },
						{	"partyIdSchemeDefault",
							"http://www.fpml.org/ext/iso9362" },
						{	"payRelativeToSchemeDefault",
							"http://www.fpml.org/spec/2000/pay-relative-to-1-0"	},
						{	"periodSchemeDefault",
							"http://www.fpml.org/spec/2000/period-1-0" },
						{	"rateTreatmentSchemeDefault",
							"http://www.fpml.org/spec/2000/rate-treatment-1-0" },
						{	"resetRelativeToSchemeDefault",
							"http://www.fpml.org/spec/2000/reset-relative-to-1-0" },
						{	"rollConventionSchemeDefault",
							"http://www.fpml.org/spec/2000/roll-convention-1-0" },
						{	"roundingDirectionSchemeDefault",
							"http://www.fpml.org/spec/2000/rounding-direction-1-0" },
						{	"stepRelativeToSchemeDefault",
							"http://www.fpml.org/spec/2000/step-relative-to-1-0" },
						{	"weeklyRollConventionSchemeDefault",
							"http://www.fpml.org/spec/2000/weekly-roll-convention-1-0"
						}
					},
					new String [][] {
						{ 	"averagingMethodScheme",
							"averagingMethodSchemeDefault" },
						{ 	"businessCenterScheme",
							"businessCenterSchemeDefault" },
						{ 	"businessDayConventionScheme",
							"businessDayConventionSchemeDefault" },
						{ 	"compoundingMethodScheme",
							"compoundingMethodSchemeDefault" },
						{ 	"currencyScheme",
							"currencySchemeDefault" },
						{ 	"dateRelativeToScheme",
							"dateRelativeToSchemeDefault" },
						{ 	"dayCountFractionScheme",
							"dayCountFractionSchemeDefault" },
						{ 	"dayTypeScheme",
							"dayTypeSchemeDefault" },
						{ 	"discountingTypeScheme",
							"discountingTypeSchemeDefault" },
						{ 	"floatingRateIndexScheme",
							"floatingRateIndexSchemeDefault" },
						{ 	"linkIdScheme",
							"linkIdSchemeDefault" },
						{ 	"negativeInterestRateTreatmentScheme",
							"negativeInterestRateTreatmentSchemeDefault" },
						{ 	"partyIdScheme",
							"partyIdSchemeDefault" },
						{ 	"paymentTypeScheme",
							"paymentTypeSchemeDefault" },
						{ 	"payRelativeToScheme",
							"payRelativeToSchemeDefault" },
						{ 	"periodScheme",
							"periodSchemeDefault" },
						{ 	"rateTreatmentScheme",
							"rateTreatmentSchemeDefault" },
						{ 	"resetRelativeToScheme",
							"resetRelativeToSchemeDefault" },
						{ 	"rollConventionScheme",
							"rollConventionSchemeDefault" },
						{ 	"roundingDirectionScheme",
							"roundingDirectionSchemeDefault" },
						{ 	"stepRelativeToScheme",
							"stepRelativeToSchemeDefault" },
						{ 	"tradeIdScheme",
							"tradeIdSchemeDefault" },
						{ 	"weeklyRollConventionScheme",
							"weeklyRollConventionSchemeDefault" }
					}),
					parseSchemes ("schemes1-0.xml"));

	
	/**
	 * A <CODE>DTDRelease</CODE> instance containing the details for the
	 * FpML 2-0 recommendation.
	 * @since	TFP 1.0
	 */
	public static DTDRelease	R2_0
		= new DTDRelease (FPML, "2-0",
				"-//FpML//DTD Financial product Markup Language 2-0//EN",
				"fpml-dtd-2-0-2003-05-05.dtd", "FpML",
				new SchemeDefaults (
						new String [][] {
							{	"averagingMethodSchemeDefault",
								"http://www.fpml.org/spec/2000/averaging-method-1-0" },
							{	"businessCenterSchemeDefault",
								"http://www.fpml.org/spec/2000/business-center-1-0" },
							{	"businessDayConventionSchemeDefault",
								"http://www.fpml.org/spec/2000/business-day-convention-1-0" },
							{	"calculationAgentPartySchemeDefault",
								"http://www.fpml.org/spec/2001/calculation-agent-party-1-0" },
							{	"compoundingMethodSchemeDefault",
								"http://www.fpml.org/spec/2000/compounding-method-1-0" },
							{	"currencySchemeDefault",
								"http://www.fpml.org/ext/iso4217-2001-08-15" },
							{	"dateRelativeToSchemeDefault",
								"http://www.fpml.org/spec/2001/date-relative-to-2-0" },
							{	"dayCountFractionSchemeDefault",
								"http://www.fpml.org/spec/2000/day-count-fraction-1-0" },
							{	"dayTypeSchemeDefault",
								"http://www.fpml.org/spec/2000/day-type-1-0" },
							{	"discountingTypeSchemeDefault",
								"http://www.fpml.org/spec/2000/discounting-type-1-0" },
							{	"floatingRateIndexSchemeDefault",
								"http://www.fpml.org/ext/isda-2000-definitions" },
							{	"informationProviderSchemeDefault",
								"http://www.fpml.org/spec/2001/information-provider-1-0" },
							{	"negativeInterestRateTreatmentSchemeDefault",
								"http://www.fpml.org/spec/2001/negative-interest-rate-treatment-1-0" },
							{	"partyIdSchemeDefault",
								"http://www.fpml.org/ext/iso9362" },
							{	"payerReceiverSchemeDefault",
								"http://www.fpml.org/spec/2001/payer-receiver-1-0" },
							{	"payRelativeToSchemeDefault",
								"http://www.fpml.org/spec/2000/pay-relative-to-1-0"	},
							{	"periodSchemeDefault",
								"http://www.fpml.org/spec/2000/period-1-0" },
							{	"quotationRateTypeSchemeDefault",
								"http://www.fpml.org/spec/2001/quotation-rate-type-1-0" },
							{	"rateTreatmentSchemeDefault",
								"http://www.fpml.org/spec/2000/rate-treatment-1-0" },
							{	"referenceBankIdSchemeDefault",
								"http://www.fpml.org/ext/iso9362" },
							{	"resetRelativeToSchemeDefault",
								"http://www.fpml.org/spec/2000/reset-relative-to-1-0" },
							{	"rollConventionSchemeDefault",
								"http://www.fpml.org/spec/2000/roll-convention-1-0" },
							{	"roundingDirectionSchemeDefault",
								"http://www.fpml.org/spec/2000/rounding-direction-1-0" },
							{	"stepRelativeToSchemeDefault",
								"http://www.fpml.org/spec/2000/step-relative-to-1-0" },
							{	"weeklyRollConventionSchemeDefault",
								"http://www.fpml.org/spec/2000/weekly-roll-convention-1-0"
							}
						},
						new String [][] {
							{ 	"averagingMethodScheme",
								"averagingMethodSchemeDefault" },
							{ 	"businessCenterScheme",
								"businessCenterSchemeDefault" },
							{ 	"businessDayConventionScheme",
								"businessDayConventionSchemeDefault" },
							{ 	"calculationAgentPartyScheme",
								"calculationAgentPartySchemeDefault" },
							{ 	"compoundingMethodScheme",
								"compoundingMethodSchemeDefault" },
							{ 	"currencyScheme",
								"currencySchemeDefault" },
							{ 	"dateRelativeToScheme",
								"dateRelativeToSchemeDefault" },
							{ 	"dayCountFractionScheme",
								"dayCountFractionSchemeDefault" },
							{ 	"dayTypeScheme",
								"dayTypeSchemeDefault" },
							{ 	"discountingTypeScheme",
								"discountingTypeSchemeDefault" },
							{ 	"floatingRateIndexScheme",
								"floatingRateIndexSchemeDefault" },
							{ 	"informationProviderScheme",
								"informationProviderSchemeDefault" },
							{ 	"linkIdScheme",
								"linkIdSchemeDefault" },
							{ 	"negativeInterestRateTreatmentScheme",
								"negativeInterestRateTreatmentSchemeDefault" },
							{ 	"partyIdScheme",
								"partyIdSchemeDefault" },
							{ 	"payRelativeToScheme",
								"payRelativeToSchemeDefault" },
							{ 	"payerReceiverScheme",
								"payerReceiverSchemeDefault" },
							{ 	"paymentTypeScheme",
								"paymentTypeSchemeDefault" },
							{ 	"periodScheme",
								"periodSchemeDefault" },
							{ 	"productTypeScheme",
								"productTypeSchemeDefault" },
							{ 	"quotationRateTypeScheme",
								"quotationRateTypeSchemeDefault" },
							{ 	"rateSourcePageScheme",
								"rateSourcePageSchemeDefault" },
							{ 	"rateTreatmentScheme",
								"rateTreatmentSchemeDefault" },
							{ 	"referenceBankIdScheme",
								"referenceBankIdSchemeDefault" },
							{ 	"resetRelativeToScheme",
								"resetRelativeToSchemeDefault" },
							{ 	"rollConventionScheme",
								"rollConventionSchemeDefault" },
							{ 	"roundingDirectionScheme",
								"roundingDirectionSchemeDefault" },
							{ 	"stepRelativeToScheme",
								"stepRelativeToSchemeDefault" },
							{ 	"tradeIdScheme",
								"tradeIdSchemeDefault" },
							{ 	"weeklyRollConventionScheme",
								"weeklyRollConventionSchemeDefault" }
						}),
						parseSchemes ("schemes2-0.xml"));

	/**
	 * A <CODE>DTDRelease</CODE> instance containing the details for the
	 * FpML 3-0 trial recommendation.
	 * @since	TFP 1.0
	 */
	public static DTDRelease	R3_0
		= new DTDRelease (FPML, "3-0",
				"-//FpML//DTD Financial product Markup Language 3-0//EN",
				"fpml-dtd-main-3-0.dtd", "FpML",
				new SchemeDefaults (
						new String [][] {
							{	"averagingMethodSchemeDefault",
								"http://www.fpml.org/spec/2000/averaging-method-1-0" },
							{	"businessCenterSchemeDefault",
								"http://www.fpml.org/spec/2000/business-center-1-0" },
							{	"businessDayConventionSchemeDefault",
								"http://www.fpml.org/spec/2000/business-day-convention-1-0" },
							{	"calculationAgentPartySchemeDefault",
								"http://www.fpml.org/spec/2001/calculation-agent-party-1-0" },
							{	"clearanceSystemIdSchemeDefault",
								"http://www.fpml.org/spec/2002/clearance-system-1-0" },
							{	"compoundingMethodSchemeDefault",
								"http://www.fpml.org/spec/2000/compounding-method-1-0" },
							{	"countrySchemeDefault",
								"http://www.fpml.org/ext/iso3166" },
							{	"currencySchemeDefault",
								"http://www.fpml.org/ext/iso4217" },
							{	"cutNameSchemeDefault",
								"http://www.fpml.org/spec/2002/cut-name-1-0" },
							{ 	"dateRelativeToSchemeDefault",
								"http://www.fpml.org/spec/2001/date-relative-to-3-0" },
							{ 	"dayCountFractionSchemeDefault",
								"http://www.fpml.org/spec/2000/day-count-fraction-1-0" },
							{ 	"dayTypeSchemeDefault",
								"http://www.fpml.org/spec/2000/day-type-1-0" },
							{ 	"definitionsToSchemeDefault",
								"http://www.fpml.org/spec/2002/contractual-definitions-scheme-1-0" },
							{ 	"discountingSchemeDefault",
								"http://www.fpml.org/spec/2000/discounting-type-1-0" },
							{	"exchangeIdSchemeDefault",
								"http://www.fpml.org/spec/2002/exchange-id-MIC-1-0" },
							{ 	"exerciseStyleSchemeDefault",
								"http://www.fpml.org/spec/2002/exercise-style-scheme-1-0" },
							{	"floatingRateIndexSchemeDefault",
								"http://www.fpml.org/ext/isda-2000-definitions" },
							{	"fxBarrierTypeSchemeDefault",
								"http://www.fpml.org/spec/2002/fx-barrier-type-1-0" },
							{	"governingLawSchemeDefault",
								"http://www.fpml.org/spec/2002/governing-law-1-0" },
							{	"informationProviderSchemeDefault",
								"http://www.fpml.org/spec/2001/information-provider-1-0" },
							{	"masterAgreementTypeSchemeDefault",
								"http://www.fpml.org/spec/2002/master-agreement-type-scheme-1-0" },
							{ 	"methodOfAdjustmentSchemeDefault",
								"http://www.fpml.org/spec/2002/method-of-adjustment-scheme-1-0" },
							{ 	"nationalisationOrInsolvencySchemeDefault",
								"http://www.fpml.org/spec/2002/nationalisation-or-insolvency-event-scheme-1-0" },
							{ 	"negativeInterestRateTreatmentSchemeDefault",
								"http://www.fpml.org/spec/2001/negative-interest-rate-treatment-scheme-1-0" },
							{	"partyIdSchemeDefault",
								"http://www.fpml.org/ext/iso9362" },
							{ 	"payRelativeToSchemeDefault",
								"http://www.fpml.org/spec/2000/pay-relative-to-1-0" },
							{ 	"payerReceiverSchemeDefault",
								"http://www.fpml.org/spec/2001/payer-receiver-1-0" },
							{ 	"payoutSchemeDefault",
								"http://www.fpml.org/spec/2002/payout-scheme-1-0" },
							{ 	"periodSchemeDefault",
								"http://www.fpml.org/spec/2000/period-1-0" },
							{ 	"premiumQuoteBasisSchemeDefault",
								"http://www.fpml.org/spec/2002/premium-quote-basis-scheme-1-0" },
							{ 	"quotationRateTypeSchemeDefault",
								"http://www.fpml.org/spec/2001/quotation-rate-type-scheme-1-0" },
							{ 	"quoteBasisSchemeDefault",
								"http://www.fpml.org/spec/2001/quote-basis-1-0" },
							{ 	"rateTreatmentSchemeDefault",
								"http://www.fpml.org/spec/2000/rate-treatment-1-0" },
							{ 	"resetRelativeToSchemeDefault",
								"http://www.fpml.org/spec/2000/reset-relative-to-1-0" },
							{ 	"rollConventionSchemeDefault",
								"http://www.fpml.org/spec/2000/roll-convention-1-0" },
							{ 	"roundingDirectionSchemeDefault",
								"http://www.fpml.org/spec/2000/rounding-direction-1-0" },
							{ 	"routingIdSchemeDefault",
								"http://www.fpml.org/spec/2002/routing-id-code-1-0" },
							{ 	"settlementMethodSchemeDefault",
								"http://www.fpml.org/spec/2002/settlement-method-1-0" },
							{ 	"settlementPriceSourceSchemeDefault",
								"http://www.fpml.org/spec/2002/settlement-price-source-1-0" },
							{	"settlementTypeSchemeDefault",
								"http://www.fpml.org/spec/2002/settlement-type-scheme-1-0" },
							{	"shareExtraordinaryEventSchemeDefault",
								"http://www.fpml.org/spec/2002/share-extraordinary-event-scheme-1-0" },
							{	"sideRateBasisSchemeDefault",
								"http://www.fpml.org/spec/2002/side-rate-basis-scheme-1-0" },
							{	"standardSettlementStyleSchemeDefault",
								"http://www.fpml.org/spec/2002/standard-settlement-style-scheme-1-0" },
							{	"stepRelativeToSchemeDefault",
								"http://ww.fpml.org/spec/2000/step-relative-to-1-0" },
							{	"strikeQuoteBasisSchemeDefault",
								"http://www.fpml.org/spec/2002/strike-quote-basis-scheme-1-0" },
							{	"timeTypeSchemeDefault",
								"http://www.fpml.org/spec/2002/time-type-scheme-1-0" },
							{	"touchConditionSchemeDefault",
								"http://www.fpml.org/spec/2002/touch-condition-scheme-1-0" },
							{	"triggerConditionSchemeDefault",
								"http://www.fpml.org/spec/2002/trigger-condition-scheme-1-0" },
							{	"weeklyRollConventionSchemeDefault",
								"http://wwww.fpml.org/spec/2000/weekly-roll-convention-1-0" }
						},
						new String [][] {
							{ 	"averagingMethodScheme",
								"averagingMethodSchemeDefault" },
							{ 	"businessCenterScheme",
								"businessCenterSchemeDefault" },
							{ 	"businessDayConventionScheme",
								"businessDayConventionSchemeDefault" },
							{ 	"calculationAgentPartyScheme",
								"calculationAgentPartySchemeDefault" },
							{ 	"clearanceSystemScheme",
								"clearanceSystemSchemeDefault" },
							{ 	"compoundingMethodScheme",
								"compoundingMethodSchemeDefault" },
							{ 	"countryScheme",
								"countrySchemeDefault" },
							{ 	"currencyScheme",
								"currencySchemeDefault" },
							{ 	"cutNameScheme",
								"cutNameSchemeDefault" },
							{ 	"dateRelativeToScheme",
								"dateRelativeToSchemeDefault" },
							{ 	"dayCountFractionScheme",
								"dayCountFractionSchemeDefault" },
							{ 	"dayTypeScheme",
								"dayTypeSchemeDefault" },
							{ 	"discountingTypeScheme",
								"discountingTypeSchemeDefault" },
							{ 	"exchangeIdScheme",
								"exchangeIdSchemeDefault" },
							{ 	"exerciseStyleScheme",
								"exerciseStyleSchemeDefault" },
							{ 	"floatingRateIndexScheme",
								"floatingRateIndexSchemeDefault" },
							{ 	"fxBarrierTypeScheme",
								"fxBarrierTypeSchemeDefault" },
							{ 	"governingLawScheme",
								"governingLawSchemeDefault" },
							{ 	"informationProviderScheme",
								"informationProviderSchemeDefault" },
							{ 	"linkIdScheme",
								"linkIdSchemeDefault" },
							{ 	"methodOfAdjustmentScheme",
								"methodOfAdjustmentSchemeDefault" },
							{ 	"nationalisationOrInsolvencyOrDelistingScheme",
								"nationalisationOrInsolvencyOrDelistingSchemeDefault" },
							{ 	"negativeInterestRateTreatmentScheme",
								"negativeInterestRateTreatmentSchemeDefault" },
							{ 	"partyIdScheme",
								"partyIdSchemeDefault" },
							{ 	"payerReceiverScheme",
								"payerReceiverSchemeDefault" },
							{ 	"paymentTypeScheme",
								"paymentTypeSchemeDefault" },
							{ 	"payoutScheme",
								"payoutSchemeDefault" },
							{ 	"payRelativeToScheme",
								"payRelativeToSchemeDefault" },
							{ 	"periodScheme",
								"periodSchemeDefault" },
							{ 	"portfolioNameScheme",
								"portfolioNameSchemeDefault" },
							{ 	"premiumQuoteBasisScheme",
								"premiumQuoteBasisSchemeDefault" },
							{ 	"productIdScheme",
								"productIdSchemeDefault" },
							{ 	"productTypeScheme",
								"productTypeSchemeDefault" },
							{ 	"quotationRateTypeScheme",
								"quotationRateTypeSchemeDefault" },
							{ 	"quoteBasisScheme",
								"quoteBasisSchemeDefault" },
							{ 	"rateSourcePageScheme",
								"rateSourcePageSchemeDefault" },
							{ 	"rateTreatmentScheme",
								"rateTreatmentSchemeDefault" },
							{ 	"referenceBankIdScheme",
								"referenceBankIdSchemeDefault" },
							{ 	"resetRelativeToScheme",
								"resetRelativeToSchemeDefault" },
							{ 	"rollConventionScheme",
								"rollConventionSchemeDefault" },
							{ 	"roundingDirectionScheme",
								"roundingDirectionSchemeDefault" },
							{ 	"routingIdScheme",
								"routingIdSchemeDefault" },
							{ 	"settlementMethodScheme",
								"settlementMethodSchemeDefault" },
							{ 	"settlementPriceSourceScheme",
								"settlementPriceSourceSchemeDefault" },
							{ 	"settlementTypeScheme",
								"settlementTypeSchemeDefault" },
							{ 	"shareExtraordinaryEventScheme",
								"shareExtraordinaryEventSchemeDefault" },
							{ 	"sideRateBasisScheme",
								"sideRateBasisSchemeDefault" },
							{ 	"standardSettlementStyleScheme",
								"standardSettlementStyleSchemeDefault" },
							{ 	"stepRelativeToScheme",
								"stepRelativeToSchemeDefault" },
							{ 	"strikeQuoteBasisScheme",
								"strikeQuoteBasisSchemeDefault" },
							{ 	"timeTypeScheme",
								"timeTypeSchemeDefault" },
							{ 	"touchConditionScheme",
								"touchConditionSchemeDefault" },
							{ 	"tradeIdScheme",
								"tradeIdSchemeDefault" },
							{ 	"triggerConditionScheme",
								"triggerConditionSchemeDefault" },
							{ 	"weeklyRollConventionScheme",
								"weeklyRollConventionSchemeDefault" }
						}),
						parseSchemes ("schemes3-0.xml"));
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-0 recommendation.
	 * @since	TFP 1.0
	 */
	public static SchemaRelease	R4_0
		= new SchemaRelease (FPML, "4-0",
				"http://www.fpml.org/2003/FpML-4-0", "fpml-main-4-0.xsd",
				"fpml", "fpml4-0", initialiser, recogniser, "FpML",
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
						parseSchemes ("schemes4-0.xml"));
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-1 recommendation.
	 * @since	TFP 1.0
	 */
	public static SchemaRelease	R4_1
		= new SchemaRelease (FPML, "4-1",
				"http://www.fpml.org/2004/FpML-4-1", "fpml-main-4-1.xsd",
				"fpml", "fpml4-1", initialiser, recogniser, "FpML",
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
						parseSchemes ("schemes4-1.xml"));
			
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-2 recommendation.
	 * @since	TFP 1.0
	 */
	public static SchemaRelease	R4_2
		= new SchemaRelease (FPML, "4-2",
				"http://www.fpml.org/2005/FpML-4-2", "fpml-main-4-2.xsd",
				"fpml", "fpml4-2", initialiser, recogniser, "FpML",
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
						parseSchemes ("schemes4-2.xml"));
			
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-3 working draft.
	 * @since	TFP 1.0
	 */
	public static SchemaRelease	R4_3
		= new SchemaRelease (FPML, "4-3",
				"http://www.fpml.org/2007/FpML-4-3", "fpml-main-4-3.xsd",
				"fpml", "fpml4-3", initialiser, recogniser, "FpML",
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
	 * A <CODE>Conversion</CODE> instance configured for FpML 1-0 to 2-0
	 * transformation. The specific changes needed (other than basic DOCTYPE
	 * changes) are:
	 * <UL>
	 * <LI>The &lt;product&gt; constainer element was removed.</LI>
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
	 * <LI>The FpML XMl schema namespace URI is updated.</LI>
	 * </UL>
	 * @since	TFP 1.0
	 */
	public static final Conversion R4_0__R4_1
		= new Conversions.R4_0__R4_1 ();
	
	/**
	 * A <CODE>Conversion</CODE> instance configured for FpML 4-0 to 4-1
	 * transformation. The specific changes needed are:
	 * <UL>
	 * <LI>The FpML XMl schema namespace URI is updated.</LI>
	 * </UL>
	 * @since	TFP 1.0
	 */
	public static final Conversion R4_1__R4_2
		= new Conversions.R4_1__R4_2 ();
	
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
		
		if (root.getLocalName ().equals ("FpML")) {
			String version = root.getAttribute ("version");
		
			if (version != null) return (FPML.getReleaseForVersion (version));
		}
		return (null);
	}

	/**
	 * Ensures that no instances can be constructed.
	 * @since	TFP 1.0
	 */
	private Releases ()
	{ }
	
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
	
	static {
		R4_0.addImport (com.handcoded.dsig.Releases.R1_0);
		R4_1.addImport (com.handcoded.dsig.Releases.R1_0);
		R4_2.addImport (com.handcoded.dsig.Releases.R1_0);
		R4_3.addImport (com.handcoded.dsig.Releases.R1_0);
	}
}