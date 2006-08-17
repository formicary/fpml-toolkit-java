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

import java.util.Hashtable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The <CODE>NodeIndex</CODE> class builds an index of the elements that
 * comprise a DOM tree to allow subsequent queries to be efficiently executed.
 * <P>
 * A DOM <CODE>Document</CODE> instance performs an expensive in-order
 * traversal of the DOM tree every time the <CODE>getElementsByName</CODE>
 * method is called. This class does a one off traversal on construction and
 * then uses the cached results to return <CODE>NodeList</CODE> instances.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class NodeIndex
{
	/**
	 * Constructs a <CODE>NodeIndex</CODE> that contains an index to all the
	 * elements in the given document.
	 *
	 * @param	document		The DOM <CODE>Document</CODE> to be indexed.
	 * @since	TFP 1.0
	 */
	public NodeIndex (Document document)
	{
		indexNodes (this.document = document);		
	}
	
	/**
	 * Provides access to the <CODE>Document</CODE> that this instance
	 * indexes.
	 *
	 * @return	The orginal XML <CODE>Document</CODE>
	 * @since	TFP 1.0
	 */
	public Document getDocument ()
	{
		return (document);
	}
	
	/**
	 * Creates a (possibly empty) <CODE>NodeList</CODE> containing all the
	 * element nodes identified by the given name string.
	 *
	 * @param	name			The required element name string.
	 * @return	A <CODE>NodeList</CODE> of corresponding nodes.
	 * @since	TFP 1.0
	 */
	public NodeList getElementsByName (final String name)
	{
		NodeList list = (NodeList) elements.get (name);
		
		return ((list != null) ? list : emptyList);
	}
	
	/**
	 * Creates a (possibly empty) <CODE>NodeList</CODE> containing all the
	 * element nodes identified by the given name strings.
	 *
	 * @param	names			An array of name strings for elements
	 * @return	A <CODE>NodeList</CODE> of corresponding nodes.
	 * @since	TFP 1.0
	 */
	public NodeList getElementsByName (final String [] names)
	{
		MutableNodeList		result = new MutableNodeList ();
		
		for (int index = 0; index < names.length; ++index)
			result.addAll (getElementsByName (names [index]));
			
		return (result);
	}

	/**
	 * Returns the <CODE>Element</CODE> in the indexed document that has
	 * an id attribute with the given value.
	 * 
	 * @param 	id				The require id attribute value.
	 * @return	The matching <CODE>Element</CODE> or <CODE>null</CODE> if
	 * 			none. 
	 * @since	TFP 1.0
	 */
	public Element getElementById (final String id)
	{
		return (document.getElementById (id));
	}
	
	/**
	 * An empty <CODE>MutableNodeList</CODE> used when a requested element is
	 * not present.
	 * @since	TFP 1.0
	 */
	private static final NodeList emptyList = new MutableNodeList ();
	
	/**
	 * The DOM <CODE>Document</CODE> from which this index is derived.
	 * @since	TFP 1.0
	 */
	private Document		document;
	
	/**
	 * A <CODE>Hashtable</CODE> containing <CODE>MutableNodeList</CODE>
	 * instances indexed by element name.
	 * @since	TFP 1.0
	 */
	private Hashtable		elements = new Hashtable (); 
	
	/**
	 * Recursively walks a DOM tree creating an index of the elements by
	 * their local name.
	 *
	 * @param	node 			The next node to be indexed.
	 * @since	TFP 1.0
	 */
	private void indexNodes (Node node)
	{
		switch (node.getNodeType ()) {
		case Node.DOCUMENT_NODE:
				indexNodes (((Document) node).getDocumentElement ());
				break;
				
		case Node.ELEMENT_NODE:
			{
				String name = node.getLocalName ();
				MutableNodeList list = (MutableNodeList) elements.get (name);
			
				if (list == null)
					elements.put (name, list = new MutableNodeList ());
					
				list.add (node);
				
				for (Node child = node.getFirstChild (); child != null;) {
					if (child.getNodeType () == Node.ELEMENT_NODE)
						indexNodes (child);
						
					child = child.getNextSibling ();
				}
				break;
			}
		}
	}
}