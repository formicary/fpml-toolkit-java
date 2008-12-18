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

package com.handcoded.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * The <CODE>Builder</CODE> class extends <CODE>Browser</CODE> and adds methods
 * that allow the underlying DOM <CODE>Document</CODE> to be modified.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public class Builder extends Browser
{
	/**
	 * Constructs a <CODE>Builder</CODE> attached to the root <CODE>Element
	 * </CODE> of the given <CODE>Document</CODE>.
	 * 
	 * @param 	document		The <CODE>Document</CODE> to attach to.
	 * @since	TFP 1.0
	 */
	public Builder (Document document)
	{
		super (document);
	}
	
	/**
	 * Constructs a <CODE>Builder</CODE> attached to the specified <CODE>Element
	 * </CODE> of the given <CODE>Document</CODE>.
	 * 
	 * @param 	document		The <CODE>Document</CODE> to attach to.
	 * @param 	context			The initial context <CODE>Element</CODE>.
	 */
	public Builder (Document document, Element context)
	{
		super (document, context);
	}
	
	/**
	 * Creates and appends a new <CODE>Element</CODE> with the name indicated
	 * by the parameter <CODE>name</CODE> to the currenct context element.
	 *  
	 * @param 	name			The name of the new <CODE>Element</CODE>.
	 * @return	The <CODE>Element</CODE> node added for the new element tag.
	 * @since	TFP 1.0
	 */
	public Element appendElement (final String name)
	{
		return (appendElement (null, name));
	}
	
	/**
	 * Creates a new <CODE>Element</CODE> with the given name and namespace
	 * URI and appends it as a child of the context.
	 * 
	 * @param 	uri				The namespace URI or <CODE>null</CODE> if none.
	 * @param 	name			The name of the new <CODE>Element</CODE>.
	 * @return	The <CODE>Element</CODE> node added for the new element tag.
	 * @since	TFP 1.0
	 */
	public final Element appendElement (final String uri, final String name)
	{
		Element		element = document.createElementNS (uri, name);
		
		context.appendChild (element);
		return (context = element);
	}
	
	/**
	 * Makes the parent <CODE>Element</CODE> of the context the new context
	 * for future operations.
	 * @since	TFP 1.0
	 */
	public final void closeElement ()
	{
		Node		parent = context.getParentNode ();

		context = (parent instanceof Element) ? (Element) parent : null;
	}
	
	/**
	 * Sets the value of the named attribute to the given value.
	 * 
	 * @param 	name			The name of the attribute to set.
	 * @param 	value			The new value of the attribute
	 * @since	TFP 1.0	
	 */
	public void setAttribute (final String name, final String value)
	{
		setAttribute (null, name, value);
	}
	
	/**
	 * Sets the value of the named attribute in the indicated namespace
	 * to the given value.
	 * 
	 * @param 	uri				The namespace URI of the attribute.
	 * @param 	name			The name of the attribute to set.
	 * @param 	value			The new value of the attribute.
	 * @since	TFP 1.0
	 */
	public final void setAttribute (final String uri, final String name, final String value)
	{
		context.setAttributeNS (uri, name, value);
	}
	
	/**
	 * Creates a new <CODE>Text</CODE> node and appends it to the children
	 * of the context node.
	 * 
	 * @param 	text			The value of the new <CODE>Text</CODE> node.
	 * @since	TFP 1.0
	 */
	public final void appendText (final String text)
	{
		context.appendChild (document.createTextNode (text));
	}
	
	/**
	 * Creates a new <CODE>CDataSection</CODE> node and appends it to the
	 * children of the context element.
	 * 
	 * @param 	cdata			The value of the new <CODE>CDataSection</CODE> node.
	 * @since	TFP 1.0
	 */
	public final void appendCData (final String cdata)
	{
		context.appendChild (document.createCDATASection (cdata));
	}
	
	/**
	 * Creates a new <CODE>Comment</CODE> node and appends it to the
	 * children of the context element.
	 * 
	 * @param 	comment			The value of the new <CODE>Comment</CODE>.
	 */
	public final void appendComment (final String comment)
	{
		context.appendChild (document.createComment (comment));
	}
	
	/**
	 * Adds a new <CODE>Element</CODE> with the specified name to the current
	 * <CODE>Document</CODE> and then adds a child <CODE>Text</CODE> node
	 * containing the given text. The context remains unaffected by the
	 * operation making this a quick way of adding an element with the
	 * following XML pattern to the current document.
	 * <CODE>...<BR/>&lt;name&gt;text&lt;/name&gt;<BR/>...</CODE>
	 * 
	 * @param 	name			The name of the new element.
	 * @param 	text			The text to be added as a child.
	 * @since	TFP 1.0
	 */
	public void appendElementAndText (final String name, final String text)
	{
		appendElementAndText (null, name, text);
	}
	
	/**
	 * Adds a new <CODE>Element</CODE> with the specified namespace and name
	 * to the current <CODE>Document</CODE> and then adds a child <CODE>Text
	 * </CODE> node containing the given text. The context remains unaffected
	 * by the operation making this a quick way of adding an element with the
	 * following XML pattern to the current document.
	 * <CODE>...<BR/>&lt;prefix:name&gt;text&lt;/prefix:name&gt;<BR/>...</CODE>
	 * 
	 * @param 	uri				The namespace URI associated with the element.
	 * @param 	name			The name of the new element.
	 * @param 	text			The text to be added as a child.
	 * @since	TFP 1.0
	 */
	public final void appendElementAndText (final String uri, final String name, final String text)
	{
		appendElement (uri, name);
		appendText (text);
		closeElement ();
	}
}