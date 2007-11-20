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

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import com.handcoded.fpml.Releases;
import com.handcoded.fpml.meta.SchemeAccess;
import com.handcoded.fpml.schemes.Scheme;
import com.handcoded.fpml.schemes.SchemeCollection;
import com.handcoded.meta.Specification;
import com.handcoded.validation.Precondition;
import com.handcoded.validation.Rule;
import com.handcoded.validation.ValidationErrorHandler;
import com.handcoded.xml.DOM;
import com.handcoded.xml.NodeIndex;
import com.handcoded.xml.MutableNodeList;

/**
 * The <CODE>SchemeRule</CODE> class provides the logic to attempt the 
 * validation of a scheme value against a set of acceptable values. The class
 * understands the FpML conventions for locally overriding a default scheme
 * URI.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public class SchemeRule extends Rule
{
	/**
	 * Constructs a <CODE>SchemeRule</CODE> with a given name that applies in the
	 * circumstances defined by its <CODE>Precondition</CODE>.
	 *
	 * @param	precondition	A <CODE>Precondition</CODE> instance.
	 * @param	name			The unique name for the rule.
	 * @param	parentNames		The local names of the parent elements or <CODE>null</CODE>.
	 * @param	elementNames  	The local names of the <CODE>Element</CODE> instances to test.
	 * @param	attributeName 	The name of attribute containing any overriding URI.
	 * @since	TFP 1.0	
	 */
	public SchemeRule (final Precondition precondition, final String name,
			final String [] parentNames, final String [] elementNames, final String attributeName)
	{
		super (precondition, name);	 
		
		this.parentNames	= parentNames;
		this.elementNames 	= elementNames;
		this.attributeName 	= attributeName;
	}
	
	/**
	 * Constructs a <CODE>SchemeRule</CODE> with a given name that applies in the
	 * circumstances defined by its <CODE>Precondition</CODE>.
	 *
	 * @param	precondition	A <CODE>Precondition</CODE> instance.
	 * @param	name			The unique name for the rule.
	 * @param	elementNames  	The local names of the <CODE>Element</CODE> instances to test.
	 * @param	attributeName 	The name of attribute containing any overriding URI.
	 * @since	TFP 1.0	
	 */
	public SchemeRule (final Precondition precondition, final String name, final String [] elementNames, final String attributeName)
	{
		this (precondition, name, null, elementNames, attributeName);
	}
	
	/**
	 * Constructs a <CODE>SchemeRule</CODE> that validates a single element
	 *
	 * @param	precondition	A <CODE>Precondition</CODE> instance.
	 * @param	name			The unique name for the rule.
	 * @param	elementName		The local name of the <CODE>Element</CODE> to test.
	 * @param	attributeName 	The name of attribute containing any overriding URI.
	 * @since	TFP 1.0	
	 */
	public SchemeRule (final Precondition precondition, final String name, final String elementName, final String attributeName)
	{
		this (precondition, name, null, new String [] { elementName }, attributeName);
	}
	
	/**
	 * Constructs a <CODE>SchemeRule</CODE> that validates a single element
	 *
	 * @param	precondition	A <CODE>Precondition</CODE> instance.
	 * @param	name			The unique name for the rule.
	 * @param	parentName		The local name of the parent <CODE>Element</CODE>.
	 * @param	elementName		The local name of the <CODE>Element</CODE> to test.
	 * @param	attributeName 	The name of attribute containing any overriding URI.
	 * @since	TFP 1.0	
	 */
	public SchemeRule (final Precondition precondition, final String name,
			final String parentName, final String elementName, final String attributeName)
	{
		this (precondition, name, new String [] { parentName }, new String [] { elementName }, attributeName);
	}
	
	/**
	 * Constructs a <CODE>SchemeRule</CODE> that applies to any document.
	 *
	 * @param	name			The unique name for the rule.
	 * @param	elementName		The local name of the <CODE>Element</CODE> to test.
	 * @param	attributeName 	The name of attribute containing any overriding URI.
	 * @since	TFP 1.0	
	 */
	public SchemeRule (final String name, final String elementName, final String attributeName)
	{
		this (Precondition.ALWAYS, name, null, new String [] { elementName }, attributeName);
	}
	
	/**
	 * Constructs a <CODE>SchemeRule</CODE> that applies to any document.
	 *
	 * @param	name			The unique name for the rule.
	 * @param	parentName		The local name of the parent <CODE>Element</CODE>.
	 * @param	elementName		The local name of the <CODE>Element</CODE> to test.
	 * @param	attributeName 	The name of attribute containing any overriding URI.
	 * @since	TFP 1.0	
	 */
	public SchemeRule (final String name, final String parentName, final String elementName, final String attributeName)
	{
		this (Precondition.ALWAYS, name, new String [] { parentName }, new String [] { elementName }, attributeName);
	}
	
	/**
	 * Constructs a <CODE>SchemeRule</CODE> that applies to any document.
	 * elements
	 *
	 * @param	name			The rule name.
	 * @param	elementNames  	The local names of the <CODE>Element</CODE> instances to test.
	 * @param	attributeName 	The name of attribute containing any overriding URI.
	 * @since	TFP 1.0	
	 */
	public SchemeRule (final String name, final String [] elementNames, final String attributeName)
	{
		this (Precondition.ALWAYS, name, elementNames, attributeName);	 
	}
	
	/**
	 * {@inheritDoc}
	 * @since	TFP 1.0	
	 */
	public boolean validate (NodeIndex nodeIndex, ValidationErrorHandler errorHandler)
	{
		boolean		result = true;
		
		for (int index = 0; index < elementNames.length; ++index) {
			NodeList	list = nodeIndex.getElementsByName (elementNames [index]);
			
			if (parentNames == null)
				result &= validate (list, errorHandler);
			else {
				MutableNodeList		targets = new MutableNodeList ();
				
				for (int count = 0; count < list.getLength (); ++count) {
					Element context = (Element) list.item (count);
					Node    parent  = context.getParentNode ();
					
					if ((parent.getNodeType () == Node.ELEMENT_NODE) &&
							parent.getLocalName().equals (parentNames [index]))
						targets.add (context);
				}
				result &= validate (targets, errorHandler);
			}
		}
	
		return (result);
	}	 
	
	/**
	 * Performs the validation of all the elements in the provided <CODE>NodeList
	 * </CODE>. 
	 * @since	TFP 1.0	
	 */
	protected boolean validate (NodeList list, ValidationErrorHandler errorHandler)
	{
		boolean		result 	= true;
		
		if (list.getLength () > 0) {
			Element fpml	= DOM.getParent ((Element) list.item(0));
			String version	= null;
			
			// Find the FpML root node
			while ((fpml != null) &&  !fpml.getLocalName().equals("FpML"))
				fpml = DOM.getParent (fpml);
				
			if (fpml != null) version = fpml.getAttribute ("version");
			
			SchemeCollection 	schemes =
				((SchemeAccess) (Releases.FPML.getReleaseForVersion (version))).getSchemeCollection ();
		
			for (int index = 0; index < list.getLength (); ++index) {
				Element	context = (Element) list.item (index);
	
				// If there is no local override then look for a default on the FpML
				// element in pre 3-0 versions.
				String uri = context.getAttribute (attributeName);
				if (((uri == null) || (uri.length () == 0)) && (version != null)) {
					String [] components = version.split ("-");
					if ((components.length > 1) && (components [0].compareTo ("4") < 0)) {
						SchemeAccess provider
							= (SchemeAccess) Specification.releaseForDocument (context.getOwnerDocument ());
	
						String name = provider.getSchemeDefaults ().getDefaultAttributeForScheme (attributeName);
						if (name != null) uri = fpml.getAttribute (name);
					}
				}
	
				if ((uri == null) || (uri.length () == 0)) {
					errorHandler.error ("305", context,
						"A qualifying scheme URI has not been defined for this element",
						getName (), context.getLocalName ());
	
					result = false;
					continue;
				}
	
				Scheme scheme = schemes.findSchemeForUri (uri);
				if (scheme == null) {
					errorHandler.error ("305", context,
						"An unrecognized scheme URI has been used as a qualifier",
						getName (), uri);
	
					result = false;
					continue;
				}
				
				String value = DOM.getInnerText (context).trim ();
				if (scheme.isValid (value)) continue;
	
				errorHandler.error ("305", context,
					"The code value '" + value + "' is not valid in scheme '" + scheme.getUri () + "'",
					getName (), value);
	
				result = false;
			}
		}
		return (result);
	}

	/**
	 * A list of the local parent element names corresponding to the
	 * <CODE>elementNames</CODE>. If the array has a <CODE>null</CODE> value
	 * then no parent element qualification is performed.
	 * @since	TFP 1.1 
	 */
	private final String []	parentNames;
	
	/**
	 * A list of local element names that this rule will validate.
	 * @since	TFP 1.0	
	 */
	private final String []	elementNames;
	
	/**
	 * The name of the attribute containing the scheme URI.
	 * @since	TFP 1.0	
	 */
	private final String	attributeName;
}