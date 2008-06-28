// Copyright (C),2005-2008 HandCoded Software Ltd.
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

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.handcoded.validation.Rule;
import com.handcoded.validation.RuleSet;
import com.handcoded.validation.ValidationErrorHandler;
import com.handcoded.xml.DOM;
import com.handcoded.xml.NodeIndex;
import com.handcoded.xml.XPath;

/**
 * The <CODE>LoanRules</CODE> class contains a <CODE>RuleSet</CODE>
 * initialised with FpML defined validation rules for syndicated loan messages.
 * <P>
 * @author 	Goonie
 * @version	$Id: $
 * @since	TFP 1.2
 */

public final class LoanRules extends FpMLRuleSet 
{
	/**
	 * A <CODE>Rule</CODE> that ensures that the effective date of a loan contract 
	 * is not after the start date of the interest period.
	 * <P>
	 * Applies to FpML 4.4 and later.
	 * @since	TFP 1.2
	 */
	public static final Rule RULE01 = new Rule (Preconditions.R4_4__LATER, "ln-1") {
		
		public boolean validate (NodeIndex nodeIndex, ValidationErrorHandler errorHandler)
		{
			if (nodeIndex.hasTypeInformation()) 
				return (
					  validate (nodeIndex.getElementsByType (determineNamespace (nodeIndex), "LoanContract"), errorHandler));					
				
			return (
				  validate (nodeIndex.getElementsByName ("loanContract"), errorHandler));
		}
		
		private boolean validate (NodeList list, ValidationErrorHandler errorHandler)
		{
			boolean		result	= true;
			
			for (int index = 0; index < list.getLength(); ++index) {
				Element		context = (Element) list.item (index);
				Element		start	= XPath.path (context, "currentInterestRatePeriod", "startDate");
				Element		effective	= XPath.path (context, "effectiveDate");
				
				if ((start == null) || (effective == null) || 
					greaterOrEqual (toDate (start), toDate (effective))) continue;
									
				errorHandler.error ("305", context,
						"The effectiveDate must not be after the currentInterestRatePeriod/startDate",
						getName (), null);
				
				result = false;
			}
			
			return (result);
		}
		
	};
	
	/**
	 * A <CODE>Rule</CODE> that ensures that if the floating rate index contains the string 'PRIME' 
	 * then the rate fixing date must be equal to the effective date.
	 * <P>
	 * Applies to FpML 4.4 and later.
	 * @since	TFP 1.2
	 */
	public static final Rule RULE02 = new Rule (Preconditions.R4_4__LATER, "ln-2") {
		
		public boolean validate (NodeIndex nodeIndex, ValidationErrorHandler errorHandler)
		{
			if (nodeIndex.hasTypeInformation()) 
				return (
					  validate (nodeIndex.getElementsByType (determineNamespace (nodeIndex), "LoanContract"), errorHandler));					
				
			return (
				  validate (nodeIndex.getElementsByName ("loanContract"), errorHandler));
		}
		
		private boolean validate (NodeList list, ValidationErrorHandler errorHandler)
		{
			boolean		result	= true;
			
			for (int index = 0; index < list.getLength(); ++index) {
				Element		context = (Element) list.item (index);
				Element		effective	= XPath.path (context, "effectiveDate");
				Element		rateIndex	= XPath.path (context, "currentInterestRatePeriod", "floatingRateIndex");
				Element		fixingDate	= XPath.path (context, "currentInterestRatePeriod", "rateFixingDate");
				
				
				if ((fixingDate != null) && (effective != null) && (DOM.getInnerText (rateIndex).contains("PRIME"))) {
					if (notEqual (toDate (fixingDate) , toDate (effective))){
						errorHandler.error ("305", context,
								"If the floatingRateIndex contains the string 'PRIME' then the currentInterestRatePeriod/rateFixingDate must be the same as the effectiveDate",
								getName (), null);
						result = false;
					}
				}
			}
			
			return (result);
		}
		
	};
	
	/**
	 * A <CODE>Rule</CODE> that ensures that the rateFixingDate must not be after the startDate, 
	 * the startDate must not be after the endDate, and the rateFixingDate must not be after the endDate.
	 * <P>
	 * Applies to FpML 4.4 and later.
	 * @since	TFP 1.2
	 */
	public static final Rule RULE03 = new Rule (Preconditions.R4_4__LATER, "ln-3") {
		
		public boolean validate (NodeIndex nodeIndex, ValidationErrorHandler errorHandler)
		{
			if (nodeIndex.hasTypeInformation()) 
				return (
					  validate (nodeIndex.getElementsByType (determineNamespace (nodeIndex), "InterestRatePeriod"), errorHandler));					
				
			return (
				  validate (nodeIndex.getElementsByName ("currentInterestRatePeriod"), errorHandler));
		}
		
		private boolean validate (NodeList list, ValidationErrorHandler errorHandler)
		{
			boolean		result	= true;
			
			for (int index = 0; index < list.getLength(); ++index) {
				Element		context = (Element) list.item (index);
				Element		end	= XPath.path (context, "endDate");
				Element		start	= XPath.path (context, "startDate");
				Element		fixingDate	= XPath.path (context, "rateFixingDate");
			
				if ((start!= null) && (fixingDate != null) && (less (toDate (start), toDate (fixingDate)))){
					errorHandler.error ("305", context,
							"The rateFixingDate must not be after the startDate",
							getName (), null);
					result = false;
				}						
				if ((end != null) && (start !=null) && (less (toDate (end), toDate (start)))) {
				
					errorHandler.error ("305", context,
							"The startDate must not be after the endDate",
							getName (), null);
					result = false;
				}
				if ((end != null) && (fixingDate !=null) && (less (toDate (end), toDate (fixingDate)))) {
					
					errorHandler.error ("305", context,
							"The rateFixingDate must not be after the endDate",
							getName (), null);
					result = false;
				}
			}
			return (result);
		}
		
	};
	
	/**
	 * Provides access to the Loan validation rule set.
	 *
	 * @return	The Loan validation rule set.
	 * @since	TFP 1.2
	 */
	public static RuleSet getRules ()
	{
		return (rules);
	}

	/**
	 * A <CODE>RuleSet</CODE> containing all the standard FpML defined
	 * validation rules for loan messages.
	 * @since	TFP 1.2
	 */
	private static final RuleSet	rules = new RuleSet ();
	
	/**
	 * Initialises the <CODE>RuleSet</CODe> by adding the individually defined
	 * validation rules.
	 * @since	TFP 1.2
	 */
	static {
		rules.add (RULE01);
		rules.add (RULE02);
		rules.add (RULE03);
	}
}
