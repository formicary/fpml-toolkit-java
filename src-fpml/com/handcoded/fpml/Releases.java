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
							{ 	"definitionsSchemeDefault",
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
							{	"contractualDefinitionsScheme",
								"definitionsSchemeDefault" },
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
							{	"masterAgreementTypeScheme",
								"masterAgreementSchemeDefault" },
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
								"routingIdTypeSchemeDefault" },
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
							{	"assetMeasureScheme",
								"http://www.fpml.org/spec/2004/asset" },
							{	"brokerConfirmationTypeScheme",
								"http://www.fpml.org/spec/2004/broker-confirmation-type-2-0" },
							{	"businessCenterScheme",
								"http://www.fpml.org/spec/2004/business-center-1-0" },
							{	"clearanceSystemIdScheme",
								"http://www.fpml.org/spec/2002/clearance-system-1-0" },
							{	"compoundingFrequencyScheme",
								"http://www.fpml.org/spec/2004/compounding-frequency-1-0" },
							{	"contractualDefinitionsScheme",
								"http://www.fpml.org/spec/2003/contractual-definitions-3-0" },
							{	"contractualSupplementScheme",
								"http://www.fpml.org/spec/2003/contractual-supplement-3-0" },
							{	"countryScheme",
								"http://www.fpml.org/ext/iso3166" },
							{	"couponTypeScheme",
								"http://www.fpml.org/spec/2004/coupon-type-1-0" },
							{	"creditSeniorityScheme",
								"http://www.fpml.org/spec/2004/credit-seniority-1-0" },
							{	"currencyScheme",
								"http://www.fpml.org/ext/iso4217-2001-08-15" },
							{	"cutNameScheme",
								"http://www.fpml.org/spec/2002/cut-name-1-0" },
							{	"dayCountFractionScheme",
								"http://www.fpml.org/spec/2000/day-count-fraction-1-0" },
							{	"derivativeCalculationMethodScheme",
								"http://www.fpml.org/spec/2004/derivative-calculation-method-1-0" },
							{	"entityIdScheme",
								"http://www.fpml.org/spec/2003/entity-id-RED-1-0" },
							{	"entityNameScheme",
								"http://www.fpml.org/spec/2003/entity-name-RED-1-0" },
							{	"entityTypeScheme",
								"http://www.fpml.org/spec/2004/entity-type-1-0" },
							{	"exchangeIdScheme",
								"http://www.fpml.org/spec/2002/exchange-id-MIC-1-0" },
							{	"floatingRateIndexScheme",
								"http://www.fpml.org/ext/isda-2000-definitions" },
							{	"governingLawScheme",
								"http://www.fpml.org/spec/2002/governing-law-1-0" },
							{	"indexAnnexSourceScheme",
								"http://www.fpml.org/spec/2004/cdx-index-annex-source-1-0" },
							{	"informationProviderScheme",
								"http://www.fpml.org/spec/2003/information-provider-2-0" },
							{	"interpolationMethodScheme",
								"http://www.fpml.org/spec/2004/interpolation-method-1-0" },
							{	"marketDisruptionScheme",
								"http://www.fpml.org/spec/2004/marketDisruption-1-0" },
							{	"masterAgreementTypeScheme",
								"http://www.fpml.org/spec/2002/master-agreement-type-1-0" },
							{	"masterConfirmationTypeScheme",
								"http://www.fpml.org/spec/2004/master-confirmation-type-1-0" },
							{	"matrixTermScheme",
								"http://www.fpml.org/spec/2004/credit-matrix-transaction-type-2-0" },
							{	"matrixTypeScheme",
								"http://www.fpml.org/spec/2004/matrix-type-1-0" },
							{	"partyIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{	"perturbationTypeScheme",
								"http://www.fpml.org/spec/2004/perturbation-type-1-0" },
							{	"priceQuoteUnitsScheme",
								"http://www.fpml.org/spec/2004/price-quote-units-1-0" },
							{	"pricingInputTypeScheme",
								"http://www.fpml.org/spec/2004/pricing-input-type-1-0" },
							{	"queryParameterOperatorScheme",
								"http://www.fpml.org/spec/2004/query-parameter-operator-1-0" },
							{	"quoteTimingScheme",
								"http://www.fpml.org/spec/2004/quote-timing-1-0" },
							{	"referenceAmountScheme",
								"http://www.fpml.org/spec/2004/reference-amount-1-0" },
							{	"restructuringScheme",
								"http://www.fpml.org/spec/2003/restructuring-1-0" },
							{ 	"routingIdScheme",
								"http://www.fpml.org/spec/2002/routing-id-code-1-0" },
							{ 	"settlementMethodScheme",
								"http://www.fpml.org/spec/2002/settlement-method-1-0" },
							{ 	"settlementPriceSourceScheme",
								"http://www.fpml.org/spec/2002/settlement-price-source-1-0" },
							{ 	"typeScheme",
								"http://www.fpml.org/spec/2004/cash_flow-type-1-0" },
							{	"valuationSetDetailScheme",
								"http://www.fpml.org/spec/2004/valuation-set-detail-1-0" }
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
							{	"assetMeasureScheme",
								"http://www.fpml.org/coding-scheme/asset-measure-5-0" },
							{	"brokerConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/broker-confirmation-type-3-0" },
							{	"businessCenterScheme",
								"http://www.fpml.org/coding-scheme/business-center-6-0" },
							{	"cashflowTypeScheme",
								"http://www.fpml.org/coding-scheme/cashflow-type-2-0" },
							{	"clearanceSystemIdScheme",
								"http://www.fpml.org/coding-scheme/clearance-system-1-0" },
							{	"compoundingFrequencyScheme",
								"http://www.fpml.org/coding-scheme/compounding-frequency-1-0" },
							{	"contractualDefinitionsScheme",
								"http://www.fpml.org/coding-scheme/contractual-definitions-3-1" },
							{	"contractualSupplementScheme",
								"http://www.fpml.org/coding-scheme/contractual-supplement-6-2" },
							{	"countryScheme",
								"http://www.fpml.org/ext/iso3166" },
							{	"couponTypeScheme",
								"http://www.fpml.org/coding-scheme/coupon-type-1-0" },
							{	"creditSeniorityScheme",
								"http://www.fpml.org/coding-scheme/credit-seniority-1-0" },
							{	"currencyScheme",
								"http://www.fpml.org/ext/iso4217-2001-08-15" },
							{	"cutNameScheme",
								"http://www.fpml.org/coding-scheme/cut-name-1-0" },
							{	"dayCountFractionScheme",
								"http://www.fpml.org/coding-scheme/day-count-fraction-1-0" },
							{	"derivativeCalculationMethodScheme",
								"http://www.fpml.org/coding-scheme/derivative-calculation-method-1-0" },
							{	"entityIdScheme",
								"http://www.fpml.org/spec/2003/entity-id-RED-1-0" },
							{	"entityNameScheme",
								"http://www.fpml.org/spec/2003/entity-name-RED-1-0" },
							{	"entityTypeScheme",
								"http://www.fpml.org/coding-scheme/entity-type-1-0" },
							{	"exchangeIdScheme",
								"http://www.fpml.org/spec/2002/exchange-id-MIC-1-0" },
							{	"floatingRateIndexScheme",
								"http://www.fpml.org/coding-scheme/floating-rate-index-1-0" },
							{	"governingLawScheme",
								"http://www.fpml.org/coding-scheme/governing-law-1-0" },
							{	"indexAnnexSourceScheme",
								"http://www.fpml.org/coding-scheme/cdx-index-annex-source-1-0" },
							{	"informationProviderScheme",
								"http://www.fpml.org/coding-scheme/information-provider-2-0" },
							{	"interpolationMethodScheme",
								"http://www.fpml.org/coding-scheme/interpolation-method-1-0" },
							{	"mainPublicationScheme",
								"http://www.fpml.org/coding-scheme/inflation-main-publication-1-0" },
							{	"marketDisruptionScheme",
								"http://www.fpml.org/coding-scheme/marketDisruption-1-0" },
							{	"masterAgreementTypeScheme",
								"http://www.fpml.org/coding-scheme/master-agreement-type-1-0" },
							{	"masterConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/master-confirmation-type-5-1" },
							{	"matrixTermScheme",
								"http://www.fpml.org/coding-scheme/credit-matrix-transaction-type-2-0" },
							{	"matrixTypeScheme",
								"http://www.fpml.org/coding-scheme/matrix-type-1-0" },
							{	"partyIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{	"perturbationTypeScheme",
								"http://www.fpml.org/coding-scheme/perturbation-type-1-0" },
							{	"priceQuoteUnitsScheme",
								"http://www.fpml.org/coding-scheme/price-quote-units-1-0" },
							{	"pricingInputTypeScheme",
								"http://www.fpml.org/coding-scheme/pricing-input-type-1-0" },
							{	"productTypeScheme",
								"http://www.fpml.org/coding-scheme/product-type-simple-1-0" },
							{	"queryParameterOperatorScheme",
								"http://www.fpml.org/coding-scheme/query-parameter-operator-1-0" },
							{	"quoteTimingScheme",
								"http://www.fpml.org/coding-scheme/quote-timing-1-0" },
							{	"reasonCodeScheme",
								"http://www.fpml.org/coding-scheme/reason-code-1-0" },
							{	"restructuringScheme",
								"http://www.fpml.org/coding-scheme/restructuring-1-0" },
							{ 	"routingIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{ 	"scheduledDateTypeScheme",
								"http://www.fpml.org/coding-scheme/scheduled-date-type-1-0" },
							{ 	"settledEntityMatrixSourceScheme",
								"http://www.fpml.org/coding-scheme/settled-entity-matrix-source-1-0" },
							{ 	"settlementMethodScheme",
								"http://www.fpml.org/coding-scheme/settlement-method-1-0" },
							{ 	"settlementPriceSourceScheme",
								"http://www.fpml.org/coding-scheme/settlement-price-source-1-0" },
							{	"settlementRateOptionScheme",
								"http://www.fpml.org/coding-scheme/settlement-rate-option-2-1" },
							{ 	"spreadScheduleTypeScheme",
								"http://www.fpml.org/coding-scheme/spread-schedule-type-1-0" },
							{ 	"tradeCashflowsStatusScheme",
								"http://www.fpml.org/coding-scheme/trade-cashflows-status-1-0" }
						}),
						parseSchemes ("schemes4-2.xml"));
			
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-3 recommendation.
	 * @since	TFP 1.0
	 */
	public static SchemaRelease	R4_3
		= new SchemaRelease (FPML, "4-3",
				"http://www.fpml.org/2007/FpML-4-3", "fpml-main-4-3.xsd",
				"fpml", "fpml4-3", initialiser, recogniser, "FpML",
				new SchemeDefaults (
						new String [][] {
							{	"assetMeasureScheme",
								"http://www.fpml.org/coding-scheme/asset-measure-5-0" },
							{	"brokerConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/broker-confirmation-type-4-0" },
							{	"businessCenterScheme",
								"http://www.fpml.org/coding-scheme/business-center-6-4" },
							{	"cashflowTypeScheme",
								"http://www.fpml.org/coding-scheme/cashflow-type-2-0" },
							{	"clearanceSystemIdScheme",
								"http://www.fpml.org/coding-scheme/clearance-system-1-0" },
							{	"compoundingFrequencyScheme",
								"http://www.fpml.org/coding-scheme/compounding-frequency-1-0" },
							{	"contractualDefinitionsScheme",
								"http://www.fpml.org/coding-scheme/contractual-definitions-3-2" },
							{	"contractualSupplementScheme",
								"http://www.fpml.org/coding-scheme/contractual-supplement-6-5" },
							{	"countryScheme",
								"http://www.fpml.org/ext/iso3166" },
							{	"couponTypeScheme",
								"http://www.fpml.org/coding-scheme/coupon-type-1-0" },
							{	"creditSeniorityScheme",
								"http://www.fpml.org/coding-scheme/credit-seniority-1-0" },
							{	"currencyScheme",
								"http://www.fpml.org/ext/iso4217-2001-08-15" },
							{	"cutNameScheme",
								"http://www.fpml.org/coding-scheme/cut-name-1-0" },
							{	"dayCountFractionScheme",
								"http://www.fpml.org/coding-scheme/day-count-fraction-2-1" },
							{	"derivativeCalculationMethodScheme",
								"http://www.fpml.org/coding-scheme/derivative-calculation-method-1-0" },
							{	"entityIdScheme",
								"http://www.fpml.org/spec/2003/entity-id-RED-1-0" },
							{	"entityNameScheme",
								"http://www.fpml.org/spec/2003/entity-name-RED-1-0" },
							{	"entityTypeScheme",
								"http://www.fpml.org/coding-scheme/entity-type-1-0" },
							{	"exchangeIdScheme",
								"http://www.fpml.org/spec/2002/exchange-id-MIC-1-0" },
							{	"facilityTypeScheme",
								"http://www.fpml.org/coding-scheme/facility-type-1-0" },
							{	"floatingRateIndexScheme",
								"http://www.fpml.org/coding-scheme/floating-rate-index-2-0" },
							{	"governingLawScheme",
								"http://www.fpml.org/coding-scheme/governing-law-1-0" },
							{	"indexAnnexSourceScheme",
								"http://www.fpml.org/coding-scheme/cdx-index-annex-source-1-0" },
							{	"informationProviderScheme",
								"http://www.fpml.org/coding-scheme/information-provider-2-0" },
							{	"marketDisruptionScheme",
								"http://www.fpml.org/coding-scheme/marketDisruption-1-0" },
							{	"masterAgreementTypeScheme",
								"http://www.fpml.org/coding-scheme/master-agreement-type-1-0" },
							{	"masterConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/master-confirmation-type-5-6" },
							{	"matrixTermScheme",
								"http://www.fpml.org/coding-scheme/credit-matrix-transaction-type-3-0" },
							{	"matrixTypeScheme",
								"http://www.fpml.org/coding-scheme/matrix-type-1-0" },
							{	"mortgageSectorScheme",
								"http://www.fpml.org/coding-scheme/mortgage-sector-1-0" },
							{	"partyIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{	"perturbationTypeScheme",
								"http://www.fpml.org/coding-scheme/perturbation-type-1-0" },
							{	"positionStatusScheme",
								"http://www.fpml.org/coding-scheme/position-status-1-0" },
							{	"priceQuoteUnitsScheme",
								"http://www.fpml.org/coding-scheme/price-quote-units-1-1" },
							{	"pricingInputTypeScheme",
								"http://www.fpml.org/coding-scheme/pricing-input-type-1-0" },
							{	"productTypeScheme",
								"http://www.fpml.org/coding-scheme/product-type-simple-1-2" },
							{	"queryParameterOperatorScheme",
								"http://www.fpml.org/coding-scheme/query-parameter-operator-1-0" },
							{	"quoteTimingScheme",
								"http://www.fpml.org/coding-scheme/quote-timing-1-0" },
							{	"reasonCodeScheme",
								"http://www.fpml.org/coding-scheme/reason-code-1-0" },
							{	"restructuringScheme",
								"http://www.fpml.org/coding-scheme/restructuring-1-0" },
							{ 	"routingIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{ 	"scheduledDateTypeScheme",
								"http://www.fpml.org/coding-scheme/scheduled-date-type-1-0" },
							{ 	"settledEntityMatrixSourceScheme",
								"http://www.fpml.org/coding-scheme/settled-entity-matrix-source-1-0" },
							{ 	"settlementMethodScheme",
								"http://www.fpml.org/coding-scheme/settlement-method-1-0" },
							{ 	"settlementPriceSourceScheme",
								"http://www.fpml.org/coding-scheme/settlement-price-source-1-0" },
							{	"settlementRateOptionScheme",
								"http://www.fpml.org/coding-scheme/settlement-rate-option-2-1" },
							{ 	"spreadScheduleTypeScheme",
								"http://www.fpml.org/coding-scheme/spread-schedule-type-1-0" },
							{ 	"tradeCashflowsStatusScheme",
								"http://www.fpml.org/coding-scheme/trade-cashflows-status-1-0" }
						}),
						parseSchemes ("schemes4-3.xml"));
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-4 recommendation.
	 * @since	TFP 1.2
	 */
	public static SchemaRelease	R4_4
		= new SchemaRelease (FPML, "4-4",
				"http://www.fpml.org/2007/FpML-4-4", "fpml-main-4-4.xsd",
				"fpml", "fpml4-4", initialiser, recogniser, "FpML",
				new SchemeDefaults (
						new String [][] {
							{	"assetMeasureScheme",
								"http://www.fpml.org/coding-scheme/asset-measure-5-0" },
							{	"brokerConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/broker-confirmation-type-4-0" },
							{	"businessCenterScheme",
								"http://www.fpml.org/coding-scheme/business-center-6-5" },
							{	"cashflowTypeScheme",
								"http://www.fpml.org/coding-scheme/cashflow-type-2-0" },
							{	"clearanceSystemIdScheme",
								"http://www.fpml.org/coding-scheme/clearance-system-1-0" },
							{	"compoundingFrequencyScheme",
								"http://www.fpml.org/coding-scheme/compounding-frequency-1-0" },
							{	"contractualDefinitionsScheme",
								"http://www.fpml.org/coding-scheme/contractual-definitions-3-2" },
							{	"contractualSupplementScheme",
								"http://www.fpml.org/coding-scheme/contractual-supplement-6-7" },
							{	"countryScheme",
								"http://www.fpml.org/ext/iso3166" },
							{	"couponTypeScheme",
								"http://www.fpml.org/coding-scheme/coupon-type-1-0" },
							{	"creditSeniorityScheme",
								"http://www.fpml.org/coding-scheme/credit-seniority-1-0" },
							{	"currencyScheme",
								"http://www.fpml.org/ext/iso4217-2001-08-15" },
							{	"cutNameScheme",
								"http://www.fpml.org/coding-scheme/cut-name-1-0" },
							{	"dayCountFractionScheme",
								"http://www.fpml.org/coding-scheme/day-count-fraction-2-1" },
							{	"derivativeCalculationMethodScheme",
								"http://www.fpml.org/coding-scheme/derivative-calculation-method-1-0" },
							{	"entityIdScheme",
								"http://www.fpml.org/spec/2003/entity-id-RED-1-0" },
							{	"entityNameScheme",
								"http://www.fpml.org/spec/2003/entity-name-RED-1-0" },
							{	"entityTypeScheme",
								"http://www.fpml.org/coding-scheme/entity-type-1-0" },
							{	"exchangeIdScheme",
								"http://www.fpml.org/spec/2002/exchange-id-MIC-1-0" },
							{	"facilityTypeScheme",
								"http://www.fpml.org/coding-scheme/facility-type-1-0" },
							{	"floatingRateIndexScheme",
								"http://www.fpml.org/coding-scheme/floating-rate-index-2-0" },
							{	"governingLawScheme",
								"http://www.fpml.org/coding-scheme/governing-law-1-0" },
							{	"indexAnnexSourceScheme",
								"http://www.fpml.org/coding-scheme/cdx-index-annex-source-1-0" },
							{	"informationProviderScheme",
								"http://www.fpml.org/coding-scheme/information-provider-2-0" },
							{	"interpolationMethodScheme",
								"http://www.fpml.org/coding-scheme/interpolation-method-1-1" },
							{	"lienScheme",
								"http://www.fpml.org/coding-scheme/designated-priority-1-0" },
							{	"loanTrancheScheme",
								"http://www.fpml.org/coding-scheme/underlying-asset-tranche" },
							{	"mainPublicationScheme",
								"http://www.fpml.org/coding-scheme/inflation-main-publication-1-0" },
							{	"marketDisruptionScheme",
								"http://www.fpml.org/coding-scheme/market-disruption-1-0" },
							{	"masterAgreementTypeScheme",
								"http://www.fpml.org/coding-scheme/master-agreement-type-1-0" },
							{	"masterConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/master-confirmation-type-5-7" },
							{	"matrixTermScheme",
								"http://www.fpml.org/coding-scheme/credit-matrix-transaction-type-3-0" },
							{	"matrixTypeScheme",
								"http://www.fpml.org/coding-scheme/matrix-type-1-0" },
							{	"mortgageSectorScheme",
								"http://www.fpml.org/coding-scheme/mortgage-sector-1-0" },
							{	"partyIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{	"perturbationTypeScheme",
								"http://www.fpml.org/coding-scheme/perturbation-type-1-0" },
							{	"positionStatusScheme",
								"http://www.fpml.org/coding-scheme/position-status-1-0" },
							{	"priceQuoteUnitsScheme",
								"http://www.fpml.org/coding-scheme/price-quote-units-1-1" },
							{	"pricingInputTypeScheme",
								"http://www.fpml.org/coding-scheme/pricing-input-type-1-0" },
							{	"productTypeScheme",
								"http://www.fpml.org/coding-scheme/product-type-simple-1-2" },
							{	"queryParameterOperatorScheme",
								"http://www.fpml.org/coding-scheme/query-parameter-operator-1-0" },
							{	"quoteTimingScheme",
								"http://www.fpml.org/coding-scheme/quote-timing-1-0" },
							{	"reasonCodeScheme",
								"http://www.fpml.org/coding-scheme/reason-code-1-0" },
							{	"restructuringScheme",
								"http://www.fpml.org/coding-scheme/restructuring-1-0" },
							{ 	"routingIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{ 	"scheduledDateTypeScheme",
								"http://www.fpml.org/coding-scheme/scheduled-date-type-1-0" },
							{ 	"settledEntityMatrixSourceScheme",
								"http://www.fpml.org/coding-scheme/settled-entity-matrix-source-1-0" },
							{ 	"settlementMethodScheme",
								"http://www.fpml.org/coding-scheme/settlement-method-1-0" },
							{ 	"settlementPriceSourceScheme",
								"http://www.fpml.org/coding-scheme/settlement-price-source-1-0" },
							{ 	"settlementRateOptionScheme",
								"http://www.fpml.org/coding-scheme/settlement-rate-option-2-1" },
							{ 	"spreadScheduleTypeScheme",
								"http://www.fpml.org/coding-scheme/spread-schedule-type-1-0" },
							{ 	"tradeCashflowsStatusScheme",
								"http://www.fpml.org/coding-scheme/trade-cashflows-status-1-0" }
						}),
						parseSchemes ("schemes4-4.xml"));

	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-5 recommendation.
	 * @since	TFP 1.2
	 */
	public static SchemaRelease	R4_5
		= new SchemaRelease (FPML, "4-5",
				"http://www.fpml.org/2008/FpML-4-5", "fpml-main-4-5.xsd",
				"fpml", "fpml4-5", initialiser, recogniser, "FpML",
				new SchemeDefaults (
						new String [][] {
							{	"assetMeasureScheme",
								"http://www.fpml.org/coding-scheme/asset-measure" },
							{	"brokerConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/broker-confirmation-type" },
							{	"businessCenterScheme",
								"http://www.fpml.org/coding-scheme/business-center" },
							{	"cashflowTypeScheme",
								"http://www.fpml.org/coding-scheme/cashflow-type" },
							{	"clearanceSystemIdScheme",
								"http://www.fpml.org/coding-scheme/clearance-system" },
							{	"commodityBusinessCalendarScheme",
								"http://www.fpml.org/coding-scheme/commodity-business-calendar"	},
							{	"commodityFrequencyTypeScheme",
								"http://www.fpml.org/coding-scheme/commodity-frequency-type" },
							{	"commodityFxType",
								"http://www.fpml.org/coding-scheme/commodity-fx-type" },
							{	"commodityMarketDisruptionFallbackScheme",
								"http://www.fpml.org/coding-scheme/commodity-market-disruption-fallback" },
							{	"commodityMarketDisruptionScheme",
								"http://www.fpml.org/coding-scheme/commodity-market-disruption" },
							{	"compoundingFrequencyScheme",
								"http://www.fpml.org/coding-scheme/compounding-frequency" },
							{	"contractualDefinitionsScheme",
								"http://www.fpml.org/coding-scheme/contractual-definitions" },
							{	"contractualSupplementScheme",
								"http://www.fpml.org/coding-scheme/contractual-supplement" },
							{	"countryScheme",
								"http://www.fpml.org/ext/iso3166" },
							{	"couponTypeScheme",
								"http://www.fpml.org/coding-scheme/coupon-type" },
							{	"creditSeniorityScheme",
								"http://www.fpml.org/coding-scheme/credit-seniority" },
							{	"creditSupportAgreementTypeScheme",
								"http://www.fpml.org/coding-scheme/credit-support-agreement-type" },
							{	"currencyScheme",
								"http://www.fpml.org/ext/iso4217-2001-08-15" },
							{	"cutNameScheme",
								"http://www.fpml.org/coding-scheme/cut-name" },
							{	"dayCountFractionScheme",
								"http://www.fpml.org/coding-scheme/day-count-fraction" },
							{	"derivativeCalculationMethodScheme",
								"http://www.fpml.org/coding-scheme/derivative-calculation-method" },
							{	"entityIdScheme",
								"http://www.fpml.org/spec/2003/entity-id-RED" },
							{	"entityNameScheme",
								"http://www.fpml.org/spec/2003/entity-name-RED" },
							{	"entityTypeScheme",
								"http://www.fpml.org/coding-scheme/entity-type" },
							{	"exchangeIdScheme",
								"http://www.fpml.org/spec/2002/exchange-id-MIC" },
							{	"facilityTypeScheme",
								"http://www.fpml.org/coding-scheme/facility-type" },
							{	"floatingRateIndexScheme",
								"http://www.fpml.org/coding-scheme/floating-rate-index" },
							{	"governingLawScheme",
								"http://www.fpml.org/coding-scheme/governing-law" },
							{	"indexAnnexSourceScheme",
								"http://www.fpml.org/coding-scheme/cdx-index-annex-source" },
							{	"informationProviderScheme",
								"http://www.fpml.org/coding-scheme/information-provider" },
							{	"interpolationMethodScheme",
								"http://www.fpml.org/coding-scheme/interpolation-method" },
							{	"lienScheme",
								"http://www.fpml.org/coding-scheme/designated-priority" },
							{	"loanTrancheScheme",
								"http://www.fpml.org/coding-scheme/underlying-asset-tranche" },
							{	"mainPublicationScheme",
								"http://www.fpml.org/coding-scheme/inflation-main-publication" },
							{	"marketDisruptionScheme",
								"http://www.fpml.org/coding-scheme/market-disruption" },
							{	"masterAgreementTypeScheme",
								"http://www.fpml.org/coding-scheme/master-agreement-type" },
							{	"masterAgreementVersonScheme",
								"http://www.fpml.org/coding-scheme/master-agreement-version" },
							{	"masterConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/master-confirmation-type" },
							{	"matrixTermScheme",
								"http://www.fpml.org/coding-scheme/credit-matrix-transaction-type" },
							{	"matrixTypeScheme",
								"http://www.fpml.org/coding-scheme/matrix-type" },
							{	"mortgageSectorScheme",
								"http://www.fpml.org/coding-scheme/mortgage-sector" },
							{	"partyIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{	"perturbationTypeScheme",
								"http://www.fpml.org/coding-scheme/perturbation-type" },
							{	"positionStatusScheme",
								"http://www.fpml.org/coding-scheme/position-status" },
							{	"priceQuoteUnitsScheme",
								"http://www.fpml.org/coding-scheme/price-quote-units" },
							{	"pricingInputTypeScheme",
								"http://www.fpml.org/coding-scheme/pricing-input-type" },
							{	"productTypeScheme",
								"http://www.fpml.org/coding-scheme/product-type-simple" },
							{	"quantityFrequencyScheme",
								"http://www.fpml.org/coding-scheme/commodity-quantity-frequency" },
							{	"queryParameterOperatorScheme",
								"http://www.fpml.org/coding-scheme/query-parameter-operator" },
							{	"quoteTimingScheme",
								"http://www.fpml.org/coding-scheme/quote-timing" },
							{	"reasonCodeScheme",
								"http://www.fpml.org/coding-scheme/reason-code" },
							{	"restructuringScheme",
								"http://www.fpml.org/coding-scheme/restructuring" },
							{ 	"routingIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{ 	"scheduledDateTypeScheme",
								"http://www.fpml.org/coding-scheme/scheduled-date-type" },
							{ 	"settledEntityMatrixSourceScheme",
								"http://www.fpml.org/coding-scheme/settled-entity-matrix-source" },
							{ 	"settlementMethodScheme",
								"http://www.fpml.org/coding-scheme/settlement-method" },
							{ 	"settlementPriceSourceScheme",
								"http://www.fpml.org/coding-scheme/settlement-price-source" },
							{ 	"settlementRateOptionScheme",
								"http://www.fpml.org/coding-scheme/settlement-rate-option" },
							{ 	"spreadScheduleTypeScheme",
								"http://www.fpml.org/coding-scheme/spread-schedule-type" },
							{ 	"tradeCashflowsStatusScheme",
								"http://www.fpml.org/coding-scheme/trade-cashflows-status" }
						}),
						parseSchemes ("schemes4-5.xml"));

	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-6 recommendation.
	 * @since	TFP 1.3
	 */
	public static SchemaRelease	R4_6
		= new SchemaRelease (FPML, "4-6",
				"http://www.fpml.org/2009/FpML-4-6", "fpml-main-4-6.xsd",
				"fpml", "fpml4-6", initialiser, recogniser, "FpML",
				new SchemeDefaults (
						new String [][] {
							{	"assetMeasureScheme",
								"http://www.fpml.org/coding-scheme/asset-measure" },
							{	"brokerConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/broker-confirmation-type" },
							{	"bullionDeliveryLocation",
								"http://www.fpml.org/coding-scheme/bullion-delivery-location" },
							{	"businessCenterScheme",
								"http://www.fpml.org/coding-scheme/business-center" },
							{	"cashflowTypeScheme",
								"http://www.fpml.org/coding-scheme/cashflow-type" },
							{	"clearanceSystemIdScheme",
								"http://www.fpml.org/coding-scheme/clearance-system" },
							{	"commodityBusinessCalendarScheme",
								"http://www.fpml.org/coding-scheme/commodity-business-calendar"	},
							{	"commodityCoalProductSource",
								"http://www.fpml.org/coding-scheme/commodity-coal-product-source" },
							{	"commodityCoalProductTypeScheme",
								"http://www.fpml.org/coding-scheme/commodity-coal-product-type" },
							{	"commodityCoalQualityAdjustmentsScheme",
								"http://www.fpml.org/coding-scheme/commodity-coal-quality-adjustments" },
							{	"commodityCoalTransportationEquipmentScheme",
								"http://www.fpml.org/coding-scheme/commodity-coal-transportation-equipment" },
							{	"commodityFrequencyTypeScheme",
								"http://www.fpml.org/coding-scheme/commodity-frequency-type" },
							{	"commodityFxType",
								"http://www.fpml.org/coding-scheme/commodity-fx-type" },
							{	"commodityMarketDisruptionFallbackScheme",
								"http://www.fpml.org/coding-scheme/commodity-market-disruption-fallback" },
							{	"commodityMarketDisruptionScheme",
								"http://www.fpml.org/coding-scheme/commodity-market-disruption" },
							{	"commodityOilProductTypeScheme",
								"http://www.fpml.org/coding-scheme/commodity-oil-product-type" },
							{	"commodityPayRelativeToEventScheme",
								"http://www.fpml.org/coding-scheme/commodity-pay-relative-to-event" },
							{	"compoundingFrequencyScheme",
								"http://www.fpml.org/coding-scheme/compounding-frequency" },
							{	"contractualDefinitionsScheme",
								"http://www.fpml.org/coding-scheme/contractual-definitions" },
							{	"contractualSupplementScheme",
								"http://www.fpml.org/coding-scheme/contractual-supplement" },
							{	"countryScheme",
								"http://www.fpml.org/ext/iso3166" },
							{	"couponTypeScheme",
								"http://www.fpml.org/coding-scheme/coupon-type" },
							{	"creditSeniorityScheme",
								"http://www.fpml.org/coding-scheme/credit-seniority" },
							{	"creditSupportAgreementTypeScheme",
								"http://www.fpml.org/coding-scheme/credit-support-agreement-type" },
							{	"currencyScheme",
								"http://www.fpml.org/ext/iso4217-2001-08-15" },
							{	"cutNameScheme",
								"http://www.fpml.org/coding-scheme/cut-name" },
							{	"dayCountFractionScheme",
								"http://www.fpml.org/coding-scheme/day-count-fraction" },
							{	"deliveryRiskScheme",
								"http://www.fpml.org/coding-scheme/external/incoterms" },
							{	"derivativeCalculationMethodScheme",
								"http://www.fpml.org/coding-scheme/derivative-calculation-method" },
							{	"determinationMethodScheme",
								"http://www.fpml.org/determination-method" },
							{	"electricityTransmissionContingencyScheme",
								"http://www.fpml.org/coding-scheme/electricity-transmission-contingency" },
							{	"entityIdScheme",
								"http://www.fpml.org/spec/2003/entity-id-RED" },
							{	"entityNameScheme",
								"http://www.fpml.org/spec/2003/entity-name-RED" },
							{	"entityTypeScheme",
								"http://www.fpml.org/coding-scheme/entity-type" },
							{	"exchangeIdScheme",
								"http://www.fpml.org/spec/2002/exchange-id-MIC" },
							{	"facilityTypeScheme",
								"http://www.fpml.org/coding-scheme/facility-type" },
							{	"floatingRateIndexScheme",
								"http://www.fpml.org/coding-scheme/floating-rate-index" },
							{	"gasQualityScheme",
								"http://www.fpml.org/coding-scheme/commodity-gas-quality"	},
							{	"governingLawScheme",
								"http://www.fpml.org/coding-scheme/governing-law" },
							{	"indexAnnexSourceScheme",
								"http://www.fpml.org/coding-scheme/cdx-index-annex-source" },
							{	"informationProviderScheme",
								"http://www.fpml.org/coding-scheme/information-provider" },
							{	"interpolationMethodScheme",
								"http://www.fpml.org/coding-scheme/interpolation-method" },
							{	"lienScheme",
								"http://www.fpml.org/coding-scheme/designated-priority" },
							{	"loanTrancheScheme",
								"http://www.fpml.org/coding-scheme/underlying-asset-tranche" },
							{	"mainPublicationScheme",
								"http://www.fpml.org/coding-scheme/inflation-main-publication" },
							{	"marketDisruptionScheme",
								"http://www.fpml.org/coding-scheme/market-disruption" },
							{	"masterAgreementTypeScheme",
								"http://www.fpml.org/coding-scheme/master-agreement-type" },
							{	"masterAgreementVersonScheme",
								"http://www.fpml.org/coding-scheme/master-agreement-version" },
							{	"masterConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/master-confirmation-type" },
							{	"matrixTermScheme",
								"http://www.fpml.org/coding-scheme/credit-matrix-transaction-type" },
							{	"matrixTypeScheme",
								"http://www.fpml.org/coding-scheme/matrix-type" },
							{	"mortgageSectorScheme",
								"http://www.fpml.org/coding-scheme/mortgage-sector" },
							{	"partyIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{	"perturbationTypeScheme",
								"http://www.fpml.org/coding-scheme/perturbation-type" },
							{	"positionStatusScheme",
								"http://www.fpml.org/coding-scheme/position-status" },
							{	"priceQuoteUnitsScheme",
								"http://www.fpml.org/coding-scheme/price-quote-units" },
							{	"pricingInputTypeScheme",
								"http://www.fpml.org/coding-scheme/pricing-input-type" },
							{	"productTypeScheme",
								"http://www.fpml.org/coding-scheme/product-type-simple" },
							{	"quantityFrequencyScheme",
								"http://www.fpml.org/coding-scheme/commodity-quantity-frequency" },
							{	"quantityUnitScheme",
								"http://www.fpml.org/coding-scheme/price-quote-units" },
							{	"queryParameterOperatorScheme",
								"http://www.fpml.org/coding-scheme/query-parameter-operator" },
							{	"quoteTimingScheme",
								"http://www.fpml.org/coding-scheme/quote-timing" },
							{	"reasonCodeScheme",
								"http://www.fpml.org/coding-scheme/reason-code" },
							{	"restructuringScheme",
								"http://www.fpml.org/coding-scheme/restructuring" },
							{ 	"routingIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{ 	"scheduledDateTypeScheme",
								"http://www.fpml.org/coding-scheme/scheduled-date-type" },
							{ 	"settledEntityMatrixSourceScheme",
								"http://www.fpml.org/coding-scheme/settled-entity-matrix-source" },
							{ 	"settlementMethodScheme",
								"http://www.fpml.org/coding-scheme/settlement-method" },
							{ 	"settlementPriceSourceScheme",
								"http://www.fpml.org/coding-scheme/settlement-price-source" },
							{	"settlementRateOptionScheme",
								"http://www.fpml.org/coding-scheme/settlement-rate-option" },
							{ 	"spreadScheduleTypeScheme",
								"http://www.fpml.org/coding-scheme/spread-schedule-type" },
							{ 	"tradeCashflowsStatusScheme",
								"http://www.fpml.org/coding-scheme/trade-cashflows-status" },
							{	"tzLocationScheme",
								"http://www.fpml.org/coding-scheme/external/tzdatabase" }
						}),
						parseSchemes ("schemes4-6.xml"));

	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-7 recommendation.
	 * @since	TFP 1.4
	 */
	public static SchemaRelease	R4_7
		= new SchemaRelease (FPML, "4-7",
				"http://www.fpml.org/2009/FpML-4-7", "fpml-main-4-7.xsd",
				"fpml", "fpml4-7", initialiser, recogniser, "FpML",
				new SchemeDefaults (
						new String [][] {
							{	"assetMeasureScheme",
								"http://www.fpml.org/coding-scheme/asset-measure" },
							{	"brokerConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/broker-confirmation-type" },
							{	"bullionDeliveryLocation",
								"http://www.fpml.org/coding-scheme/bullion-delivery-location" },
							{	"businessCenterScheme",
								"http://www.fpml.org/coding-scheme/business-center" },
							{	"cashflowTypeScheme",
								"http://www.fpml.org/coding-scheme/cashflow-type" },
							{	"clearanceSystemIdScheme",
								"http://www.fpml.org/coding-scheme/clearance-system" },
							{	"commodityBusinessCalendarScheme",
								"http://www.fpml.org/coding-scheme/commodity-business-calendar"	},
							{	"commodityCoalProductSource",
								"http://www.fpml.org/coding-scheme/commodity-coal-product-source" },
							{	"commodityCoalProductTypeScheme",
								"http://www.fpml.org/coding-scheme/commodity-coal-product-type" },
							{	"commodityCoalQualityAdjustmentsScheme",
								"http://www.fpml.org/coding-scheme/commodity-coal-quality-adjustments" },
							{	"commodityCoalTransportationEquipmentScheme",
								"http://www.fpml.org/coding-scheme/commodity-coal-transportation-equipment" },
							{	"commodityFrequencyTypeScheme",
								"http://www.fpml.org/coding-scheme/commodity-frequency-type" },
							{	"commodityFxType",
								"http://www.fpml.org/coding-scheme/commodity-fx-type" },
							{	"commodityMarketDisruptionFallbackScheme",
								"http://www.fpml.org/coding-scheme/commodity-market-disruption-fallback" },
							{	"commodityMarketDisruptionScheme",
								"http://www.fpml.org/coding-scheme/commodity-market-disruption" },
							{	"commodityOilProductTypeScheme",
								"http://www.fpml.org/coding-scheme/commodity-oil-product-type" },
							{	"commodityPayRelativeToEventScheme",
								"http://www.fpml.org/coding-scheme/commodity-pay-relative-to-event" },
							{	"compoundingFrequencyScheme",
								"http://www.fpml.org/coding-scheme/compounding-frequency" },
							{	"contractualDefinitionsScheme",
								"http://www.fpml.org/coding-scheme/contractual-definitions" },
							{	"contractualSupplementScheme",
								"http://www.fpml.org/coding-scheme/contractual-supplement" },
							{	"countryScheme",
								"http://www.fpml.org/ext/iso3166" },
							{	"couponTypeScheme",
								"http://www.fpml.org/coding-scheme/coupon-type" },
							{	"creditSeniorityScheme",
								"http://www.fpml.org/coding-scheme/credit-seniority" },
							{	"creditSupportAgreementTypeScheme",
								"http://www.fpml.org/coding-scheme/credit-support-agreement-type" },
							{	"currencyScheme",
								"http://www.fpml.org/ext/iso4217-2001-08-15" },
							{	"cutNameScheme",
								"http://www.fpml.org/coding-scheme/cut-name" },
							{	"dayCountFractionScheme",
								"http://www.fpml.org/coding-scheme/day-count-fraction" },
							{	"deliveryRiskScheme",
								"http://www.fpml.org/coding-scheme/external/incoterms" },
							{	"derivativeCalculationMethodScheme",
								"http://www.fpml.org/coding-scheme/derivative-calculation-method" },
							{	"determinationMethodScheme",
								"http://www.fpml.org/determination-method" },
							{	"electricityTransmissionContingencyScheme",
								"http://www.fpml.org/coding-scheme/electricity-transmission-contingency" },
							{	"entityIdScheme",
								"http://www.fpml.org/spec/2003/entity-id-RED" },
							{	"entityNameScheme",
								"http://www.fpml.org/spec/2003/entity-name-RED" },
							{	"entityTypeScheme",
								"http://www.fpml.org/coding-scheme/entity-type" },
							{	"exchangeIdScheme",
								"http://www.fpml.org/spec/2002/exchange-id-MIC" },
							{	"facilityTypeScheme",
								"http://www.fpml.org/coding-scheme/facility-type" },
							{	"floatingRateIndexScheme",
								"http://www.fpml.org/coding-scheme/floating-rate-index" },
							{	"gasQualityScheme",
								"http://www.fpml.org/coding-scheme/commodity-gas-quality"	},
							{	"governingLawScheme",
								"http://www.fpml.org/coding-scheme/governing-law" },
							{	"indexAnnexSourceScheme",
								"http://www.fpml.org/coding-scheme/cdx-index-annex-source" },
							{	"informationProviderScheme",
								"http://www.fpml.org/coding-scheme/information-provider" },
							{	"interpolationMethodScheme",
								"http://www.fpml.org/coding-scheme/interpolation-method" },
							{	"lienScheme",
								"http://www.fpml.org/coding-scheme/designated-priority" },
							{	"loanTrancheScheme",
								"http://www.fpml.org/coding-scheme/underlying-asset-tranche" },
							{	"mainPublicationScheme",
								"http://www.fpml.org/coding-scheme/inflation-main-publication" },
							{	"marketDisruptionScheme",
								"http://www.fpml.org/coding-scheme/market-disruption" },
							{	"masterAgreementTypeScheme",
								"http://www.fpml.org/coding-scheme/master-agreement-type" },
							{	"masterAgreementVersonScheme",
								"http://www.fpml.org/coding-scheme/master-agreement-version" },
							{	"masterConfirmationAnnexTypeScheme",
								"http://www.fpml.org/coding-scheme/master-confirmation-annex-type" },
							{	"masterConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/master-confirmation-type" },
							{	"matrixTermScheme",
								"http://www.fpml.org/coding-scheme/credit-matrix-transaction-type" },
							{	"matrixTypeScheme",
								"http://www.fpml.org/coding-scheme/matrix-type" },
							{	"mortgageSectorScheme",
								"http://www.fpml.org/coding-scheme/mortgage-sector" },
							{	"partyIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{	"perturbationTypeScheme",
								"http://www.fpml.org/coding-scheme/perturbation-type" },
							{	"positionStatusScheme",
								"http://www.fpml.org/coding-scheme/position-status" },
							{	"priceQuoteUnitsScheme",
								"http://www.fpml.org/coding-scheme/price-quote-units" },
							{	"pricingInputTypeScheme",
								"http://www.fpml.org/coding-scheme/pricing-input-type" },
							{	"productTypeScheme",
								"http://www.fpml.org/coding-scheme/product-type-simple" },
							{	"quantityFrequencyScheme",
								"http://www.fpml.org/coding-scheme/commodity-quantity-frequency" },
							{	"quantityUnitScheme",
								"http://www.fpml.org/coding-scheme/price-quote-units" },
							{	"queryParameterOperatorScheme",
								"http://www.fpml.org/coding-scheme/query-parameter-operator" },
							{	"quoteTimingScheme",
								"http://www.fpml.org/coding-scheme/quote-timing" },
							{	"reasonCodeScheme",
								"http://www.fpml.org/coding-scheme/reason-code" },
							{	"restructuringScheme",
								"http://www.fpml.org/coding-scheme/restructuring" },
							{ 	"routingIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{ 	"scheduledDateTypeScheme",
								"http://www.fpml.org/coding-scheme/scheduled-date-type" },
							{ 	"settledEntityMatrixSourceScheme",
								"http://www.fpml.org/coding-scheme/settled-entity-matrix-source" },
							{ 	"settlementMethodScheme",
								"http://www.fpml.org/coding-scheme/settlement-method" },
							{ 	"settlementPriceSourceScheme",
								"http://www.fpml.org/coding-scheme/settlement-price-source" },
							{	"settlementRateOptionScheme",
								"http://www.fpml.org/coding-scheme/settlement-rate-option" },
							{ 	"spreadScheduleTypeScheme",
								"http://www.fpml.org/coding-scheme/spread-schedule-type" },
							{ 	"tradeCashflowsStatusScheme",
								"http://www.fpml.org/coding-scheme/trade-cashflows-status" },
							{	"tzLocationScheme",
								"http://www.fpml.org/coding-scheme/external/tzdatabase" }
						}),
						parseSchemes ("schemes4-7.xml"));

	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 4-8 recommendation.
	 * @since	TFP 1.4
	 */
	public static SchemaRelease	R4_8
		= new SchemaRelease (FPML, "4-8",
				"http://www.fpml.org/2010/FpML-4-8", "fpml-main-4-8.xsd",
				"fpml", "fpml4-8", initialiser, recogniser, "FpML",
				new SchemeDefaults (
						new String [][] {
							{	"assetMeasureScheme",
								"http://www.fpml.org/coding-scheme/asset-measure" },
							{	"brokerConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/broker-confirmation-type" },
							{	"bullionDeliveryLocation",
								"http://www.fpml.org/coding-scheme/bullion-delivery-location" },
							{	"businessCenterScheme",
								"http://www.fpml.org/coding-scheme/business-center" },
							{	"cashflowTypeScheme",
								"http://www.fpml.org/coding-scheme/cashflow-type" },
							{	"clearanceSystemIdScheme",
								"http://www.fpml.org/coding-scheme/clearance-system" },
							{	"commodityBusinessCalendarScheme",
								"http://www.fpml.org/coding-scheme/commodity-business-calendar"	},
							{	"commodityCoalProductSource",
								"http://www.fpml.org/coding-scheme/commodity-coal-product-source" },
							{	"commodityCoalProductTypeScheme",
								"http://www.fpml.org/coding-scheme/commodity-coal-product-type" },
							{	"commodityCoalQualityAdjustmentsScheme",
								"http://www.fpml.org/coding-scheme/commodity-coal-quality-adjustments" },
							{	"commodityCoalTransportationEquipmentScheme",
								"http://www.fpml.org/coding-scheme/commodity-coal-transportation-equipment" },
							{	"commodityFrequencyTypeScheme",
								"http://www.fpml.org/coding-scheme/commodity-frequency-type" },
							{	"commodityFxType",
								"http://www.fpml.org/coding-scheme/commodity-fx-type" },
							{	"commodityMarketDisruptionFallbackScheme",
								"http://www.fpml.org/coding-scheme/commodity-market-disruption-fallback" },
							{	"commodityMarketDisruptionScheme",
								"http://www.fpml.org/coding-scheme/commodity-market-disruption" },
							{	"commodityOilProductTypeScheme",
								"http://www.fpml.org/coding-scheme/commodity-oil-product-type" },
							{	"commodityPayRelativeToEventScheme",
								"http://www.fpml.org/coding-scheme/commodity-pay-relative-to-event" },
							{	"compoundingFrequencyScheme",
								"http://www.fpml.org/coding-scheme/compounding-frequency" },
							{	"contractualDefinitionsScheme",
								"http://www.fpml.org/coding-scheme/contractual-definitions" },
							{	"contractualSupplementScheme",
								"http://www.fpml.org/coding-scheme/contractual-supplement" },
							{	"countryScheme",
								"http://www.fpml.org/ext/iso3166" },
							{	"couponTypeScheme",
								"http://www.fpml.org/coding-scheme/coupon-type" },
							{	"creditSeniorityScheme",
								"http://www.fpml.org/coding-scheme/credit-seniority" },
							{	"creditSupportAgreementTypeScheme",
								"http://www.fpml.org/coding-scheme/credit-support-agreement-type" },
							{	"currencyScheme",
								"http://www.fpml.org/ext/iso4217-2001-08-15" },
							{	"cutNameScheme",
								"http://www.fpml.org/coding-scheme/cut-name" },
							{	"dayCountFractionScheme",
								"http://www.fpml.org/coding-scheme/day-count-fraction" },
							{	"deliveryRiskScheme",
								"http://www.fpml.org/coding-scheme/external/incoterms" },
							{	"derivativeCalculationMethodScheme",
								"http://www.fpml.org/coding-scheme/derivative-calculation-method" },
							{	"determinationMethodScheme",
								"http://www.fpml.org/determination-method" },
							{	"electricityTransmissionContingencyScheme",
								"http://www.fpml.org/coding-scheme/electricity-transmission-contingency" },
							{	"entityIdScheme",
								"http://www.fpml.org/spec/2003/entity-id-RED" },
							{	"entityNameScheme",
								"http://www.fpml.org/spec/2003/entity-name-RED" },
							{	"entityTypeScheme",
								"http://www.fpml.org/coding-scheme/entity-type" },
							{	"exchangeIdScheme",
								"http://www.fpml.org/spec/2002/exchange-id-MIC" },
							{	"facilityTypeScheme",
								"http://www.fpml.org/coding-scheme/facility-type" },
							{	"floatingRateIndexScheme",
								"http://www.fpml.org/coding-scheme/floating-rate-index" },
							{	"gasQualityScheme",
								"http://www.fpml.org/coding-scheme/commodity-gas-quality"	},
							{	"governingLawScheme",
								"http://www.fpml.org/coding-scheme/governing-law" },
							{	"indexAnnexSourceScheme",
								"http://www.fpml.org/coding-scheme/cdx-index-annex-source" },
							{	"informationProviderScheme",
								"http://www.fpml.org/coding-scheme/information-provider" },
							{	"interpolationMethodScheme",
								"http://www.fpml.org/coding-scheme/interpolation-method" },
							{	"lienScheme",
								"http://www.fpml.org/coding-scheme/designated-priority" },
							{	"loanTrancheScheme",
								"http://www.fpml.org/coding-scheme/underlying-asset-tranche" },
							{	"mainPublicationScheme",
								"http://www.fpml.org/coding-scheme/inflation-main-publication" },
							{	"marketDisruptionScheme",
								"http://www.fpml.org/coding-scheme/market-disruption" },
							{	"masterAgreementTypeScheme",
								"http://www.fpml.org/coding-scheme/master-agreement-type" },
							{	"masterAgreementVersonScheme",
								"http://www.fpml.org/coding-scheme/master-agreement-version" },
							{	"masterConfirmationAnnexTypeScheme",
								"http://www.fpml.org/coding-scheme/master-confirmation-annex-type" },
							{	"masterConfirmationTypeScheme",
								"http://www.fpml.org/coding-scheme/master-confirmation-type" },
							{	"matrixTermScheme",
								"http://www.fpml.org/coding-scheme/credit-matrix-transaction-type" },
							{	"matrixTypeScheme",
								"http://www.fpml.org/coding-scheme/matrix-type" },
							{	"mortgageSectorScheme",
								"http://www.fpml.org/coding-scheme/mortgage-sector" },
							{	"partyIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{	"perturbationTypeScheme",
								"http://www.fpml.org/coding-scheme/perturbation-type" },
							{	"positionStatusScheme",
								"http://www.fpml.org/coding-scheme/position-status" },
							{	"priceQuoteUnitsScheme",
								"http://www.fpml.org/coding-scheme/price-quote-units" },
							{	"pricingInputTypeScheme",
								"http://www.fpml.org/coding-scheme/pricing-input-type" },
							{	"productTypeScheme",
								"http://www.fpml.org/coding-scheme/product-type-simple" },
							{	"quantityFrequencyScheme",
								"http://www.fpml.org/coding-scheme/commodity-quantity-frequency" },
							{	"quantityUnitScheme",
								"http://www.fpml.org/coding-scheme/price-quote-units" },
							{	"queryParameterOperatorScheme",
								"http://www.fpml.org/coding-scheme/query-parameter-operator" },
							{	"quoteTimingScheme",
								"http://www.fpml.org/coding-scheme/quote-timing" },
							{	"reasonCodeScheme",
								"http://www.fpml.org/coding-scheme/reason-code" },
							{	"restructuringScheme",
								"http://www.fpml.org/coding-scheme/restructuring" },
							{ 	"routingIdScheme",
								"http://www.fpml.org/ext/iso9362" },
							{ 	"scheduledDateTypeScheme",
								"http://www.fpml.org/coding-scheme/scheduled-date-type" },
							{ 	"settledEntityMatrixSourceScheme",
								"http://www.fpml.org/coding-scheme/settled-entity-matrix-source" },
							{ 	"settlementMethodScheme",
								"http://www.fpml.org/coding-scheme/settlement-method" },
							{ 	"settlementPriceSourceScheme",
								"http://www.fpml.org/coding-scheme/settlement-price-source" },
							{	"settlementRateOptionScheme",
								"http://www.fpml.org/coding-scheme/settlement-rate-option" },
							{ 	"spreadScheduleTypeScheme",
								"http://www.fpml.org/coding-scheme/spread-schedule-type" },
							{ 	"tradeCashflowsStatusScheme",
								"http://www.fpml.org/coding-scheme/trade-cashflows-status" },
							{	"tzLocationScheme",
								"http://www.fpml.org/coding-scheme/external/tzdatabase" }
						}),
						parseSchemes ("schemes4-8.xml"));
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 5-0 confirmation view working draft.
	 * @since	TFP 1.0
	 */
	public static SchemaRelease	R5_0_CONFIRMATION
		= new SchemaRelease (FPML, "5-0",
				"http://www.fpml.org/FpML-5/confirmation", "fpml-main-5-0.xsd",
				"fpml", "fpml5-0", initialiser, recogniser,
				new String [] {
					// In all views
				  	"messageRejected",
				  	"requestTradeStatus",
				  	"tradeNotFound",
				  	"tradeStatus",
				  	"tradeAlreadyCancelled",
				 	"tradeAlreadySubmitted",
				 	"tradeAlreadyTerminated",
				 	// View specific
				  	"cancelTradeConfirmation",
				  	"confirmationCancelled",
				  	"confirmTrade",
				  	"modifyTradeConfirmation",
				  	"requestTradeConfirmation",
				 	"tradeAffirmation",
				 	"tradeAffirmed",
				  	"tradeAlreadyAffirmed",
				  	"tradeAlreadyConfirmed",
				 	"tradeConfirmed"
				},
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
						parseSchemes ("schemes5-0.xml"));
	
//	/**
//	 * A <CODE>SchemaRelease</CODE> instance containing the details for
//	 * FpML 5-0 pretrade view working draft.
//	 * @since	TFP 1.0
//	 */
//	public static SchemaRelease	R5_0_PRETRADE
//		= new SchemaRelease (FPML, "5-0",
//				"http://www.fpml.org/FpML-5/pretrade", "fpml-main-5-0.xsd",
//				"fpml", "fpml5-0", initialiser, recogniser,
//				new String [] {
//					// In all views
//				  	"messageRejected",
//				  	"requestTradeStatus",
//				  	"tradeNotFound",
//				  	"tradeStatus",
//				  	"tradeAlreadyCancelled",
//				 	"tradeAlreadySubmitted",
//				 	"tradeAlreadyTerminated",
//				 	// View specific
//				 	"acceptQuote",
//				    "quoteAcceptanceConfirmed",
//				    "quoteAlreadyExpired",
//				    "quoteUpdated",
//				    "requestQuote",
//				    "requestQuoteResponse",
//				},
//				new SchemeDefaults (
//						new String [][] {
//							{	"additionalTermScheme",
//								"http://www.fpml.org/spec/2003/additional-term-1-0" },
//							{	"businessCenterScheme",
//								"http://www.fpml.org/spec/2000/business-center-1-0" },
//							{	"clearanceSystemIdScheme",
//								"http://www.fpml.org/spec/2002/clearance-system-1-0" },
//							{	"contractualDefinitionsScheme",
//								"http://www.fpml.org/spec/2003/contractual-definitions-2-0" },
//							{	"contractualSupplementScheme",
//								"http://www.fpml.org/spec/2003/contractual-supplement-1-0" },
//							{	"countryScheme",
//								"http://www.fpml.org/ext/iso3166" },
//							{	"currencyScheme",
//								"http://www.fpml.org/ext/iso4217-2001-08-15" },
//							{	"cutNameScheme",
//								"http://www.fpml.org/spec/2002/cut-name-1-0" },
//							{	"exchangeIdScheme",
//								"http://www.fpml.org/spec/2002/exchange-id-MIC-1-0" },
//							{	"floatingRateIndexScheme",
//								"http://www.fpml.org/ext/isda-2000-definitions" },
//							{	"fxFeatureTypeScheme",
//								"http://www.fpml.org/spec/2003/fxFeatureType-1-0" },
//							{	"governingLawScheme",
//								"http://www.fpml.org/spec/2002/governing-law-1-0" },
//							{	"informationProviderScheme",
//								"http://www.fpml.org/spec/2003/information-provider-2-0" },
//							{	"marketDisruptionScheme",
//								"http://www.fpml.org/spec/2003/marketDisruption-1-0" },
//							{	"masterAgreementTypeScheme",
//								"http://www.fpml.org/spec/2002/master-agreement-type-1-0" },
//							{	"masterConfirmationTypeScheme",
//								"http://www.fpml.org/spec/2003/master-confirmation-type-1-0" },
//							{	"partyIdScheme",
//								"http://www.fpml.org/ext/iso9362" },
//							{	"restructuringScheme",
//								"http://www.fpml.org/spec/2003/restructuring-1-0" },
//							{ 	"routingIdScheme",
//								"http://www.fpml.org/spec/2002/routing-id-code-1-0" },
//							{ 	"settlementMethodScheme",
//								"http://www.fpml.org/spec/2002/settlement-method-1-0" },
//							{ 	"settlementPriceSourceScheme",
//								"http://www.fpml.org/spec/2002/settlement-price-source-1-0" }
//						}),
//						parseSchemes ("schemes5-0.xml"));
	
	/**
	 * A <CODE>SchemaRelease</CODE> instance containing the details for
	 * FpML 5-0 reporting view working draft.
	 * @since	TFP 1.0
	 */
	public static SchemaRelease	R5_0_REPORTING
		= new SchemaRelease (FPML, "5-0",
				"http://www.fpml.org/FpML-5/reporting", "fpml-main-5-0.xsd",
				"fpml", "fpml5-0", initialiser, recogniser,
				new String [] {
					// In all views
				  	"messageRejected",
				  	"requestTradeStatus",
				  	"tradeNotFound",
				  	"tradeStatus",
				  	"tradeAlreadyCancelled",
				 	"tradeAlreadySubmitted",
				 	"tradeAlreadyTerminated",
				 	// View specific
				 	"positionReport",
				 	"requestPositionReport",
				 	"requestValuationReport",
				 	"valuationReport",
				 	"cancelTradeCashflows",
				    "tradeCashflowsAsserted",
				    "tradeCashflowsMatchResult",
				    "positionsAcknowledged",
				    "positionsAsserted",
				    "positionsMatchResults",
				    "requestPortfolio"
				},
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
						parseSchemes ("schemes5-0.xml"));
	
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
	
//	/**
//	 * A <CODE>Conversion</CODE> instance configured for FpML 4-7 to 5-0
//	 * pre-trade transformation. The specific changes needed are:
//	 * <UL>
//	 * <LI>The FpML XML schema namespace URI is updated.</LI>
//	 * </UL>
//	 * @since	TFP 1.4
//	 */
//	public static final Conversion R4_7__R5_0_PRETRADE
//		= new Conversions.R4_7__R5_0_PRETRADE ();
	
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
	
	/**
	 * Ensures that schema releases are linked to the DSIG schema.
	 */
	static {
		R4_0.addImport (com.handcoded.dsig.Releases.R1_0);
		R4_1.addImport (com.handcoded.dsig.Releases.R1_0);
		R4_2.addImport (com.handcoded.dsig.Releases.R1_0);
		R4_3.addImport (com.handcoded.dsig.Releases.R1_0);
		R4_4.addImport (com.handcoded.dsig.Releases.R1_0);
		R4_5.addImport (com.handcoded.dsig.Releases.R1_0);
		R4_6.addImport (com.handcoded.dsig.Releases.R1_0);
		R4_7.addImport (com.handcoded.dsig.Releases.R1_0);

		R5_0_CONFIRMATION.addImport (com.handcoded.dsig.Releases.R1_0);
//		R5_0_PRETRADE.addImport (com.handcoded.dsig.Releases.R1_0);
		R5_0_REPORTING.addImport (com.handcoded.dsig.Releases.R1_0);
	}
}
