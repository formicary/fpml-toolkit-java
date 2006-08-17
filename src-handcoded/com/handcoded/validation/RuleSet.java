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

package com.handcoded.validation;

import java.util.Enumeration;
import java.util.Hashtable;

import com.handcoded.xml.NodeIndex;

/**
 * A <CODE>RuleSet</CODE> instance contains a collection of validation rules
 * that can be tested against a DOM <CODE>Document</CODE> in a single
 * operation. 
 *
 * @author	BitWise
 * @version	$Id$
 * @since 	TFP 1.0
 */
public final class RuleSet extends Validator
{
	/**
	 * Constructs an empty <CODE>RuleSet</CODE>.
	 * 
	 * @since 	TFP 1.0
	 */
	public RuleSet ()
	{ }
	
	/**
	 * Adds the indicated <CODE>Rule</CODE> instance to the <CODE>RuleSet
	 * </CODE>. A <CODE>Rule</CODE> may be referenced by several <CODE>
	 * RuleSet</CODE> instances simultaneously.
	 * <P>
	 * If the <CODE>Rule</CODE> has the same name as a previously added
	 * one then it will replace it. This feature can be used to overwrite
	 * standard rules with customized ones.
	 *
	 * @param 	rule			The <CODE>Rule</CODE> to be added.
	 * @since 	TFP 1.0
	 */
	public void add (Rule rule)
	{
		rules.put (rule.getName (), rule);
	}
	
	/**
	 * Adds the <CODE>Rule</CODE> instances that comprise another <CODE>
	 * RuleSet</CODE> to this one.
	 *
	 * @param 	ruleSet			The <CODE>RuleSet</CODE> to be added.
	 * @since 	TFP 1.0
	 */
	public void add (RuleSet ruleSet)
	{
		for (Enumeration keys = ruleSet.rules.keys (); keys.hasMoreElements ();)
			add ((Rule) ruleSet.rules.get (keys.nextElement ()));
	}
	
	/**
	 * Attempts to remove a <CODE>Rule</CODE> with the given name from the
	 * collection held by the <CODE>RuleSet</CODE>.
	 *
	 * @param 	name			The name of the <CODE>Rule</CODE> to be removed.
	 * @return 	The <CODE>Rule</CODE> instance removed from the collection
	 *			or <CODE>null</CODE> if there was no match.
	 * @since 	TFP 1.0
	 */
	public Rule remove (String name)
	{
		return ((Rule) rules.remove (name));
	}
	
	/**
	 * Attempts to remove a given <CODE>Rule</CODE> from the collection held
	 * by the <CODE>RuleSet</CODE>.
	 *
	 * @param 	rule			The <CODE>Rule</CODE> to be removed.
	 * @return 	The <CODE>Rule</CODE> instance removed from the collection
	 *			or <CODE>null</CODE> if there was no match.
	 * @since 	TFP 1.0
	 */
	public Rule remove (Rule rule)
	{
		return (remove (rule.getName ()));
	}
	
	/**
	 * Returns the current number of rules in the <CODE>RuleSet</CODE>
	 *
	 * @return	The number of rules in the <CODE>RuleSet</CODE>
	 * @since 	TFP 1.0
	 */
	public int size ()
	{
		return (rules.size ());
	}
	
	/**
	 * Converts the <CODE>RuleSet</CODE> to a string for debugging.
	 *
	 * @return 	A text description of the instance.
	 * @since 	TFP 1.0
	 */
	public String toString ()
	{
		return (getClass ().getName () + " [" + toDebug () + "]");
	}

	/**
	 * {@inheritDoc}
	 * @since 	TFP 1.0
	 */
	protected boolean validate (NodeIndex nodeIndex, ValidationErrorHandler errorHandler)
	{
		boolean			result = true;
		Hashtable		cache  = new Hashtable ();
	
		for (Enumeration keys = rules.keys (); keys.hasMoreElements ();) {
			Rule 			rule = (Rule) rules.get (keys.nextElement ());
			Precondition 	condition = rule.getPrecondition ();
			boolean 		applies;
			
			if (!cache.containsKey (condition)) {
				applies = condition.evaluate (nodeIndex);
				cache.put (condition, applies ? Boolean.TRUE : Boolean.FALSE);
			}
			else
				applies = (cache.get (condition) == Boolean.TRUE);
			
			if (applies) result &= rule.validate (nodeIndex, errorHandler);
		}
			
		return (result);
	}
		
	/**
	 * Produces a debugging string describing the state of the rule
	 * collection.
	 *
	 * @return 	The debugging string.
	 * @since 	TFP 1.0
	 */
	protected String toDebug ()
	{
		StringBuffer		buffer = new StringBuffer ();
		
		for (Enumeration keys = rules.keys (); keys.hasMoreElements ();) {
			if (buffer.length () > 0) buffer.append (',');
			buffer.append ('\"');
			buffer.append (((Rule) rules.get (keys.nextElement ())).getName ());
			buffer.append ('\"');
		}
		return ("rules=" + buffer);
	}
	
	/**
	 * The underlying collection of rules indexed by name.
	 * @since 	TFP 1.0
	 */
	private Hashtable		rules	= new Hashtable ();
}