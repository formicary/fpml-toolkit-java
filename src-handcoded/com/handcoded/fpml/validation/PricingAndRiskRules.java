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

package com.handcoded.fpml.validation;

import org.w3c.dom.NodeList;

import com.handcoded.validation.Rule;
import com.handcoded.validation.RuleSet;
import com.handcoded.validation.ValidationErrorHandler;
import com.handcoded.xml.Logic;
import com.handcoded.xml.NodeIndex;

/**
 * The <CODE>PricingAndRiskRules</CODE> class contains a <CODE>RuleSet</CODE>
 * initialised with FpML defined validation rules for pricing and risk documents.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.1
 */
public final class PricingAndRiskRules extends Logic
{
	/**
	 * A <CODE>Rule</CODE> that ensures the @href attribute must match the @id
	 * attribute of an element of type Asset.
	 * <P>
	 * Applies to FpML 4.0 and later.
	 * @since	TFP 1.0
	 */
	public static final Rule	RULE01
		= new Rule (Preconditions.R4_0__LATER, "pr-1")
		{
			public boolean validate (NodeIndex nodeIndex, ValidationErrorHandler errorHandler)
			{
				boolean		result 	= true;
				NodeList	list 	= nodeIndex.getElementsByName ("firstPeriodStartDate");
				
				for (int index = 0; index < list.getLength (); ++index) {
					// TODO
				}
				
				return (result);
			}
		};
		
		/**
		 * A <CODE>Rule</CODE> that ensures the @href attribute must match the @id
		 * attribute of an element of type PricingStructure.
		 * <P>
		 * Applies to FpML 4.0 and later.
		 * @since	TFP 1.0
		 */
		public static final Rule	RULE02
			= new Rule (Preconditions.R4_0__LATER, "pr-2")
			{
				public boolean validate (NodeIndex nodeIndex, ValidationErrorHandler errorHandler)
				{
					boolean		result 	= true;
					NodeList	list 	= nodeIndex.getElementsByName ("firstPeriodStartDate");
					
					for (int index = 0; index < list.getLength (); ++index) {
						// TODO
					}
					
					return (result);
				}
			};

	/**
	 * Provides access to the pricing and risk validation rule set.
	 * 
	 * @return	The pricing and risk validation rule set.
	 * @since	TFP 1.1
	 */
	public static RuleSet getRules ()
	{
		return (rules);
	}
	
	/**
	 * A <CODE>RuleSet</CODE> containing all the standard FpML defined
	 * validation rules for pricing and risk documents.
	 * @since	TFP 1.1
	 */
	private static final RuleSet	rules = new RuleSet ();
	
	/**
	 * Ensures no instances can be created.
	 * @since	TFP 1.1
	 */
	private PricingAndRiskRules ()
	{ }
}
