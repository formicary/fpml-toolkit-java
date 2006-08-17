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

package com.handcoded.fpml.validation;

import com.handcoded.validation.Rule;
import com.handcoded.validation.RuleSet;

/**
 * The <CODE>SchemeRules</CODE> class contains a <CODE>RuleSet</CODE>
 * initialised with validation rules for scheme values.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class SchemeRules
{
	// FpML 1.0 ----------------------------------------------------------------
	
	/**
	 * Rule 1: The value of any <CODE>averagingMethod</CODE> element must be
	 * valid within the domain defined by its <CODE>@averagingMethodScheme</CODE>
	 * attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE01
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-1", "averagingMethod", "averagingMethodScheme");

	/**
	 * Rule 2: The value of any <CODE>businessCenter</CODE> element must be valid
	 * within the domain defined by its <CODE>businessCenterScheme</CODE> attribute.
	 * <P>
	 * Applies to all FpML releases.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE02
		= new SchemeRule ("scheme-2", "businessCenter", "businessCenterScheme");
	
	/**
	 * Rule 3: The value of any <CODE>businessDayConvention</CODE> element must
	 * be valid within the domain defined by its <CODE>@businessDayConventionScheme
	 * </CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE03
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-3", "businessDayConvention", "businessDayConventionScheme");

	/**
	 * Rule 4: The value of any <CODE>compoundingMethod</CODE> element must be
	 * valid within the domain defined by its <CODE>@compoundingMethodScheme</CODE>
	 * attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE04
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-4", "compoundingMethod", "compoundingMethodScheme");

	/**
	 * Rule 5: The value of any <CODE>Currency</CODE> type element must be valid
	 * within the domain defined by its <CODE>@currencyScheme</CODE> attribute.
	 * <P>
	 * Applies to all FpML releases.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE05
		= new SchemeRule ("scheme-5",
		new String [] {
						  "currency", "settlementCurrency", "referenceCurrency",
						  "cashSettlementCurrency", "payoutCurrency", "optionOnCurrency",
						  "faceOnCurrency", "baseCurrency", "currency1", "currency2"
					  }, "currencyScheme");
	
	/**
	 * Rule 6: The value of any <CODE>dateRelativeTo</CODE> element must be valid
	 * within the domain defined by its <CODE>@dateRelativeToScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE06
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-6", "dateRelativeTo", "dateRelativeToScheme");

	/**
	 * Rule 7: The value of any <CODE>dayCountFraction</CODE> element must be valid
	 * within the domain defined by its <CODE>@dayCountFractionScheme</CODE> attribute.
	 * <P>
	 * Applies to all FpML releases EXCEPT 4-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE07
		= new SchemeRule (Preconditions.NOT_R4_0, "scheme-7", "dayCountFraction", "dayCountFractionScheme");

	/**
	 * Rule 8: The value of any <CODE>dayType</CODE> element must be valid
	 * within the domain defined by its <CODE>@dayTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE08
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-8", "dayType", "dayTypeScheme");

	/**
	 * Rule 9: The value of any <CODE>discountingType</CODE> element must be valid
	 * within the domain defined by its <CODE>@discountingTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE09
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-9", "discountingType", "discountingTypeScheme");

//	/**
//	 * Rule 10: The value of any <CODE>floatingRateIndex</CODE> type element must
//	 * be valid within the domain defined by its <CODE>@floatingRateIndexScheme</CODE> attribute.
//	 * <P>
//	 * Applies to all FpML releases.
//	 * @since	TFP 1.0	
//	 */
//	public static final Rule	RULE10
//		= new SchemeRule ("scheme-10", "floatingRateIndex", "floatingRateIndexScheme");

	/**
	 * Rule 11: The value of any <CODE>negativeInterestRateTreatment</CODE> element must be valid
	 * within the domain defined by its <CODE>@negativeInterestRateTreatmentScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE11
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-11", "negativeInterestRateTreatment", "negativeInterestRateTreatmentScheme");

	/**
	 * Rule 12: The value of any <CODE>payRelativeTo</CODE> element must be valid
	 * within the domain defined by its <CODE>@payRelativeToScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE12
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-12", "payRelativeTo", "payRelativeToScheme");

	/**
	 * Rule 13: The value of any <CODE>period</CODE> element must be valid
	 * within the domain defined by its <CODE>@periodScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE13
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-13", "period", "periodScheme");

	/**
	 * Rule 14: The value of any <CODE>rateTreatment</CODE> element must be valid
	 * within the domain defined by its <CODE>@rateTreatmentScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE14
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-14", "rateTreatment", "rateTreatmentScheme");

	/**
	 * Rule 15: The value of any <CODE>resetRelativeTo</CODE> element must be valid
	 * within the domain defined by its <CODE>@resetRelativeToScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE15
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-15", "resetRelativeTo", "resetRelativeToScheme");

	/**
	 * Rule 16: The value of any <CODE>rollConvention</CODE> element must be valid
	 * within the domain defined by its <CODE>@rollConventionScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE16
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-16", "rollConvention", "rollConventionScheme");

	/**
	 * Rule 17: The value of any <CODE>roundingDirection</CODE> element must be valid
	 * within the domain defined by its <CODE>@roundingDirectionScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE17
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-17", "roundingDirection", "roundingDirectionScheme");

	/**
	 * Rule 18: The value of any <CODE>stepRelativeTo</CODE> element must be valid
	 * within the domain defined by its <CODE>@stepRelativeToScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE18
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-18", "stepRelativeTo", "stepRelativeToScheme");

	/**
	 * Rule 19: The value of any <CODE>weeklyRollConvention</CODE> element must be valid
	 * within the domain defined by its <CODE>@weeklyRollConventionScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 1-0, 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE19
		= new SchemeRule (Preconditions.R1_0__TR3_0, "scheme-19", "weeklyRollConvention", "weeklyRollConventionScheme");

	// FpML 2.0 ------------------------------------------------------------

	/**
	 * Rule 20: The value of any <CODE>calculationAgentParty</CODE> element must be valid
	 * within the domain defined by its <CODE>@calculationAgentPartyScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE20
		= new SchemeRule (Preconditions.R2_0__TR3_0, "scheme-20", "calculationAgentParty", "calculationAgentPartyScheme");

	/**
	 * Rule 21: The value of any <CODE>rateSource</CODE> element must be valid
	 * within the domain defined by its <CODE>@informationProviderScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 2-0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE21
		= new SchemeRule (Preconditions.R2_0__LATER, "scheme-21", "rateSource", "informationProviderScheme");

	/**
	 * Rule 22: The value of any <CODE>buyer</CODE> or <CODE>seller</CODE> element
	 * must be valid within the domain defined by its <CODE>@payerReceiverScheme
	 * </CODE> attribute.
	 * <P>
	 * Applies to FpML 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE22
		= new SchemeRule (Preconditions.R2_0__TR3_0, "scheme-22",
		new String [] { "buyer", "seller" }, "payerReceiverScheme");

	/**
	 * Rule 23: The value of any <CODE>quotationRateType</CODE> element must be
	 * valid within the domain defined by its <CODE>@quotationRateTypeScheme</CODE>
	 * attribute.
	 * <P>
	 * Applies to FpML 2-0 and 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE23
		= new SchemeRule (Preconditions.R2_0__TR3_0, "scheme-23", "quotationRateType", "quotationRateTypeScheme");

	// FpML 3.0 ------------------------------------------------------------

	/**
	 * Rule 24A: The value of any <CODE>clearanceSystem</CODE> element must be valid
	 * within the domain defined by its <CODE>clearanceSystemIdScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE24A
		= new SchemeRule (Preconditions.TR3_0__R4_0, "scheme-24a", "clearanceSystem", "clearanceSystemScheme");
	
	/**
	 * Rule 24B: The value of any <CODE>clearanceSystem</CODE> element must be valid
	 * within the domain defined by its <CODE>clearanceSystemScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE24B
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-24b", "clearanceSystem", "clearanceSystemScheme");
	
	/**
	 * Rule 25: The value of any <CODE>contractualDefinitions</CODE> element must
	 * be valid within the domain defined by its <CODE>@contractualDefinitionsScheme</CODE>
	 * attribute.
	 * <P>
	 * Applies to FpML 3-0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE25
		= new SchemeRule (Preconditions.TR3_0__LATER, "scheme-25", "contractualDefinitions", "contractualDefinitionsScheme");

	/**
	 * Rule 26: The value of any <CODE>country</CODE> element must be valid
	 * within the domain defined by its <CODE>@countryScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE26
		= new SchemeRule (Preconditions.TR3_0__LATER, "scheme-26", "country", "countryScheme");

	/**
	 * Rule 27: The value of any <CODE>cutName</CODE> element must be valid
	 * within the domain defined by its <CODE>@cutNameScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE27
		= new SchemeRule (Preconditions.TR3_0__LATER, "scheme-27", "cutName", "cutNameScheme");
	
	/**
	 * Rule 28: The value of any <CODE>exerciseStyle</CODE> element must be valid
	 * within the domain defined by its <CODE>@exerciseStyleScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE28
		= new SchemeRule (Preconditions.TR3_0, "scheme-28", "exerciseStyle", "exerciseStyleScheme");
	
	/**
	 * Rule 29: The value of any <CODE>fxBarrierType</CODE> element must be valid
	 * within the domain defined by its <CODE>@fxBarrierTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE29
		= new SchemeRule (Preconditions.TR3_0, "scheme-29", "fxBarrierType", "fxBarrierTypeScheme");
	
	/**
	 * Rule 30: The value of any <CODE>governingLaw</CODE> element must be valid
	 * within the domain defined by its <CODE>governingLawScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE30
		= new SchemeRule (Preconditions.TR3_0__LATER, "scheme-30", "governingLaw", "governingLawScheme");
	
	/**
	 * Rule 31: The value of any <CODE>masterAgreementType</CODE> element must be valid
	 * within the domain defined by its <CODE>masterAgreementTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE31
		= new SchemeRule (Preconditions.TR3_0__LATER, "scheme-31", "masterAgreementType", "masterAgreementTypeScheme");
	
	/**
	 * Rule 32: The value of any <CODE>methodOfAdjustment</CODE> element must be valid
	 * within the domain defined by its <CODE>@methodOfAdjustmentScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE32
		= new SchemeRule (Preconditions.TR3_0, "scheme-32", "methodOfAdjustment", "methodOfAdjustmentScheme");
	
	/**
	 * Rule 33: The value of any <CODE>nationalisationOrInsolvency</CODE> element must be valid
	 * within the domain defined by its <CODE>@nationalisationOrInsolvencyOrDelistingEventScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE33
		= new BrokenSchemeRule (Preconditions.TR3_0, "scheme-33",
				new String [] {
					"nationalisationOrInsolvency", "delisting" },
				"nationalisationOrInsolvencyOrDelistingScheme");
	
	/**
	 * Rule 34: The value of any <CODE>optionType</CODE> element must be valid
	 * within the domain defined by its <CODE>@optionTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE34
		= new BrokenSchemeRule (Preconditions.TR3_0, "scheme-34", "optionType", "optionTypeScheme");
	
	/**
	 * Rule 35: The value of any <CODE>partyContactDetails</CODE> element must be valid
	 * within the domain defined by its <CODE>@partyContactDetailsScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE35
		= new SchemeRule (Preconditions.TR3_0, "scheme-35", "partyContactDetails", "partyContactDetailsScheme");
	
	/**
	 * Rule 36: The value of any <CODE>payout</CODE> element must be valid
	 * within the domain defined by its <CODE>@payoutScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE36
		= new SchemeRule (Preconditions.TR3_0, "scheme-36", "payoutStyle", "payoutScheme");

	/**
	 * Rule 37: The value of any <CODE>premiumQuoteBasis</CODE> element must be valid
	 * within the domain defined by its <CODE>@premiumQuoteBasisScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE37
		= new SchemeRule (Preconditions.TR3_0, "scheme-37", "premiumQuoteBasis", "premiumQuoteBasisScheme");

	/**
	 * Rule 38: The value of any <CODE>quoteBasis</CODE> element must be valid
	 * within the domain defined by its <CODE>@quoteBasisScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE38
		= new SchemeRule (Preconditions.TR3_0, "scheme-38", "quoteBasis", "quoteBasisScheme");

	/**
	 * Rule 39: The value of any <CODE>routingCodeId</CODE> element must be valid
	 * within the domain defined by its <CODE>routingCodeIdScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE39
		= new SchemeRule (Preconditions.TR3_0__LATER, "scheme-39", "routingCodeId", "routingCodeIdScheme");
	
	/**
	 * Rule 40: The value of any <CODE>settlementMethod</CODE> element must be valid
	 * within the domain defined by its <CODE>settlementScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE40
		= new SchemeRule (Preconditions.TR3_0__LATER, "scheme-40", "settlementMethod", "settlementMethodScheme");
	
	/**
	 * Rule 41: The value of any <CODE>settlementPriceSource</CODE> element must be valid
	 * within the domain defined by its <CODE>settlementPriceSourceScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE41
		= new SchemeRule (Preconditions.TR3_0__LATER, "scheme-41", "settlementPriceSource", "settlementPriceSourceScheme");
	
	/**
	 * Rule 42: The value of any <CODE>settlementType</CODE> element must be valid
	 * within the domain defined by its <CODE>@settlementTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE42
		= new SchemeRule (Preconditions.TR3_0, "scheme-42", "settlementType", "settlementTypeScheme");

	/**
	 * Rule 43: The value of any <CODE>shareExtraordinaryEvent</CODE> element must be valid
	 * within the domain defined by its <CODE>@shareExtraordinaryEventScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE43
		= new SchemeRule (Preconditions.TR3_0, "scheme-43", "shareExtraordinaryEvent", "shareExtraordinaryEventScheme");

	/**
	 * Rule 44: The value of any <CODE>sideRateBasis</CODE> element must be valid
	 * within the domain defined by its <CODE>@sideRateBasisScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE44
		= new SchemeRule (Preconditions.TR3_0, "scheme-44", "sideRateBasis", "sideRateBasisScheme");

	/**
	 * Rule 45: The value of any <CODE>standardSettlementStyle</CODE> element must be valid
	 * within the domain defined by its <CODE>@standardSettlementStyleScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE45
		= new SchemeRule (Preconditions.TR3_0, "scheme-45", "standardSettlementStyle", "standardSettlementStyleScheme");

	/**
	 * Rule 46: The value of any <CODE>strikeQuoteBasis</CODE> element must be valid
	 * within the domain defined by its <CODE>@strikeQuoteBasisScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE46
		= new SchemeRule (Preconditions.TR3_0, "scheme-46", "strikeQuoteBasis", "strikeQuoteBasisScheme");

	/**
	 * Rule 47: The value of any <CODE>timeType</CODE> element must be valid
	 * within the domain defined by its <CODE>@timeTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE47
		= new SchemeRule (Preconditions.TR3_0, "scheme-47",
				new String [] {
					  "latestExerciseTimeType", "equityExpirationTimeType", "valuationTimeType" },
				"timeTypeScheme");

	/**
	 * Rule 48: The value of any <CODE>touchCondition</CODE> element must be valid
	 * within the domain defined by its <CODE>@touchConditionScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE48
		= new SchemeRule (Preconditions.TR3_0, "scheme-48", "touchCondition", "touchConditionScheme");

	/**
	 * Rule 49: The value of any <CODE>triggerCondition</CODE> element must be valid
	 * within the domain defined by its <CODE>@triggerConditionScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 3-0.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE49
		= new SchemeRule (Preconditions.TR3_0, "scheme-49", "triggerCondition", "triggerConditionScheme");

	// FpML 4.0 ------------------------------------------------------------

	/**
	 * Rule 50: The value of any <CODE>additionalTerm</CODE> element must be valid
	 * within the domain defined by its <CODE>@additionalTermScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE50
		= new SchemeRule (Preconditions.R4_0__LATER, "scheme-50", "additionalTerm", "additionalTermScheme");

	/**
	 * Rule 51: The value of any <CODE>contractualSupplement</CODE> element must
	 * be valid within the domain defined by its <CODE>contractualSupplementScheme</CODE>
	 * attribute.
	 * <P>
	 * Applies to FpML 4.0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE51
		= new SchemeRule (Preconditions.R4_0__LATER, "scheme-51", "contractualSupplement", "contractualSupplementScheme");
	
	/**
	 * Rule 52: The value of any <CODE>fxFeatureType</CODE> element must be valid
	 * within the domain defined by its <CODE>@fxFeatureTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4-0 only.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE52
		= new SchemeRule (Preconditions.R4_0, "scheme-52", "fxFeatureType", "fxFeatureTypeScheme");
	
	/**
	 * Rule 53: The value of any <CODE>marketDisruption</CODE> element must be valid
	 * within the domain defined by its <CODE>marketDisruptionScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE53
		= new SchemeRule (Preconditions.R4_0__LATER, "scheme-53", "marketDisruption", "marketDisruptionScheme");
	
	/**
	 * Rule 54: The value of any <CODE>masterConfirmationType</CODE>b> element must be valid
	 * within the domain defined by its <CODE>masterConfirmationTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE54
		= new SchemeRule (Preconditions.R4_0__LATER, "scheme-54", "masterConfirmationType", "masterConfirmationTypeScheme");
	
	/**
	 * Rule 55: The value of any <CODE>restructuringType</CODE> element must be valid
	 * within the domain defined by its <CODE>restructuringTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.0 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE55
		= new SchemeRule (Preconditions.R4_0__LATER, "scheme-55", "restructuringType", "restructuringScheme");

	// FpML 4.1 ------------------------------------------------------------

	/**
	 * Rule 56: The value of any <CODE>assetMeasure</CODE> element must be valid
	 * within the domain defined by its <CODE>assetMeasureScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE56
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-56", "measureType", "assetMeasureScheme");
	
	/**
	 * Rule 57: The value of any <CODE>brokerConfirmationType</CODE> element must be valid
	 * within the domain defined by its <CODE>brokerConfirmationTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE57
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-57", "brokerConfirmationType", "brokerConfirmationTypeScheme");
	
	/**
	 * Rule 58: The value of any <CODE>compoundingFrequency</CODE> element must be valid
	 * within the domain defined by its <CODE>compoundingFrequencyScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE58
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-58", "compoundingFrequency", "compoundingFrequencyScheme");
	
	/**
	 * Rule 59: The value of any <CODE>couponType</CODE> element must be valid
	 * within the domain defined by its <CODE>couponTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE59
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-59", "couponType", "couponTypeScheme");
	
	/**
	 * Rule 60: The value of any <CODE>creditSeniority</CODE> element must be valid
	 * within the domain defined by its <CODE>creditSeniorityScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE60
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-60", "creditSeniority", "creditSeniorityScheme");
	
	/**
	 * Rule 61: The value of any <CODE>indexAnnexSource</CODE> element must be valid
	 * within the domain defined by its <CODE>indexAnnexSourceScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE61
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-61", "indexAnnexSource", "indexAnnexSourceScheme");
	
	/**
	 * Rule 62: The value of any <CODE>interpolationMethod</CODE> element must be valid
	 * within the domain defined by its <CODE>interpolationMethodScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE62
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-62", "interpolationMethod", "interpolationMethodScheme");
	
	/**
	 * Rule 63: The value of any <CODE>matrixTerm</CODE> element must be valid
	 * within the domain defined by its <CODE>matrixTermScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE63
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-63", "matrixTerm", "matrixTermScheme");
	
	/**
	 * Rule 64: The value of any <CODE>perturbationType</CODE> element must be valid
	 * within the domain defined by its <CODE>perturbationTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE64
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-64", "perturbationType", "perturbationTypeScheme");
	
	/**
	 * Rule 65: The value of any <CODE>priceQuoteUnit</CODE> element must be valid
	 * within the domain defined by its <CODE>priceQuoteUnitScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE65
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-65", "priceQuoteUnit", "priceQuoteUnitScheme");
	
	/**
	 * Rule 66: The value of any <CODE>pricingInputType</CODE> element must be valid
	 * within the domain defined by its <CODE>pricingInputTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE66
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-66", "pricingInputType", "pricingInputTypeScheme");
	
	/**
	 * Rule 67: The value of any <CODE>queryParameterOperator</CODE> element must be valid
	 * within the domain defined by its <CODE>queryParameterOperatorScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE67
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-67", "queryParameterOperator", "queryParameterOperatorScheme");
	
	/**
	 * Rule 68: The value of any <CODE>quoteTiming</CODE> element must be valid
	 * within the domain defined by its <CODE>quoteTimingScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE68
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-68", "quoteTiming", "quoteTimingScheme");
	
	/**
	 * Rule 69: The value of any <CODE>valuationSetDetail</CODE> element must be valid
	 * within the domain defined by its <CODE>valuationSetDetailScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.1 and later.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE69
		= new SchemeRule (Preconditions.R4_1__LATER, "scheme-69", "valuationSetDetail", "valuationSetDetailScheme");
	
	// FpML 4.2 ------------------------------------------------------------

	/**
	 * Rule 70: The value of any <CODE>creditSeniorityTrading</CODE> element must be valid
	 * within the domain defined by its <CODE>creditSeniorityTradingScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.2.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE70
		= new SchemeRule (Preconditions.TR4_2, "scheme-70", "creditSeniorityTrading", "creditSeniorityTradingScheme");
	
	/**
	 * Rule 71: The value of any <CODE>derivativeCalculationMethod</CODE> element must be valid
	 * within the domain defined by its <CODE>derivativeCalculationMethodScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.2.
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE71
		= new SchemeRule (Preconditions.TR4_2, "scheme-71", "derivativeCalculationMethod", "derivativeCalculationMethodScheme");
	
	/**
	 * Rule 72: The value of any <CODE>matrixType</CODE> element must be valid
	 * within the domain defined by its <CODE>matrixTypeScheme</CODE> attribute.
	 * <P>
	 * Applies to FpML 4.2
	 * @since	TFP 1.0	
	 */
	public static final Rule	RULE72
		= new SchemeRule (Preconditions.TR4_2, "scheme-72", "matrixType", "matrixTypeScheme");
	
	/**
	 * Provides access to the scheme validation rule set.
	 * 
	 * @return	The scheme validation rule set.
	 * @since	TFP 1.0
	 */
	public static RuleSet getRules ()
	{
		return (rules);
	}
	
	/**
	 * A <CODE>RuleSet</CODE> containing validation rules for scheme values.
	 * @since	TFP 1.0	
	 */
	private static final RuleSet	rules = new RuleSet ();
	
	/**
	 * Ensures no instances can be created.
	 * 
	 * @since	TFP 1.0	
	 */
	private SchemeRules ()
	{ }
	
	/**
	 * Initialises the <CODE>RuleSet</CODe> by adding the individually defined
	 * validation rules.
	 * 
	 * @since	TFP 1.0	
	 */
	static {
		rules.add (RULE01);
		rules.add (RULE02);
		rules.add (RULE03);
		rules.add (RULE04);
		rules.add (RULE05);
		rules.add (RULE06);
		rules.add (RULE07);
		rules.add (RULE08);
		rules.add (RULE09);
		// rules.add (RULE10);
		rules.add (RULE11);
		rules.add (RULE12);
		rules.add (RULE13);
		rules.add (RULE14);
		rules.add (RULE15);
		rules.add (RULE16);
		rules.add (RULE17);
		rules.add (RULE18);
		rules.add (RULE19);
		rules.add (RULE20);
		rules.add (RULE21);
		rules.add (RULE22);
		rules.add (RULE23);
		rules.add (RULE24A);
		rules.add (RULE24B);
		rules.add (RULE25);
		rules.add (RULE26);
		rules.add (RULE27);
		rules.add (RULE28);
		rules.add (RULE29);
		rules.add (RULE30);
		rules.add (RULE31);
		rules.add (RULE32);
		rules.add (RULE33);
		rules.add (RULE34);
		rules.add (RULE35);
		rules.add (RULE36);
		rules.add (RULE37);
		rules.add (RULE38);
		rules.add (RULE39);
		rules.add (RULE40);
		rules.add (RULE41);
		rules.add (RULE42);
		rules.add (RULE43);
		rules.add (RULE44);
		rules.add (RULE45);
		rules.add (RULE46);
		rules.add (RULE47);
		rules.add (RULE48);
		rules.add (RULE49);
		rules.add (RULE50);
		rules.add (RULE51);
		rules.add (RULE52);
		rules.add (RULE53);
		rules.add (RULE54);
		rules.add (RULE55);
		rules.add (RULE56);
		rules.add (RULE57);
		rules.add (RULE58);
		rules.add (RULE59);
		rules.add (RULE60);
		rules.add (RULE61);
		rules.add (RULE62);
		rules.add (RULE63);
		rules.add (RULE64);
		rules.add (RULE65);
		rules.add (RULE66);
		rules.add (RULE67);
		rules.add (RULE68);
		rules.add (RULE69);
		rules.add (RULE70);
		rules.add (RULE71);
		rules.add (RULE72);
	}
}