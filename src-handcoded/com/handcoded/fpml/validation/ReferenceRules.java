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
import com.handcoded.xml.Logic;
import com.handcoded.xml.NodeIndex;

/**
 * The <CODE>ReferencesRules</CODE> class contains a <CODE>RuleSet</CODE>
 * initialised with FpML defined validation rules for ID/IDREF references.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.1
 */
public final class ReferenceRules extends Logic
{
	/**
	 * A <CODE>Rule</CODE> that ensures ...
	 * <P>
	 * Applies to FpML 4.0 and later.
	 * @since	TFP 1.0
	 */
	public static final Rule	RULE01
		= new Rule (Preconditions.R4_0__LATER, "ref-1")
		{
			public boolean validate (NodeIndex nodeIndex, ValidationErrorHandler errorHandler)
			{
				boolean		result 	= true;
				
				return (result);
			}
		};
		
	/**
	 * Provides access to the business process validation rule set.
	 * 
	 * @return	The busines process validation rule set.
	 * @since	TFP 1.1
	 */
	public static RuleSet getRules ()
	{
		return (rules);
	}
	
	/**
	 * A <CODE>RuleSet</CODE> containing all the standard FpML defined
	 * validation rules for business process messages.
	 * @since	TFP 1.1
	 */
	private static final RuleSet	rules = RuleSet.forName ("ReferenceRules");
	
	/**
	 * Ensures no instances can be created.
	 * @since	TFP 1.1
	 */
	private ReferenceRules ()
	{ }
}