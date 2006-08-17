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

import java.io.PrintStream;
import java.io.PrintWriter;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Provides utility functions for processing XML.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class XmlUtility
{
	/**
	 * Recursively walks the DOM tree starting at the given <CODE>Node</CODE>
	 * printing the gory details of its construction to <CODE>System.out</CODE>.
	 * 
	 * @param 	node			The <CODE>Node</CODE> to start the dump at.
	 * @since	TFP 1.0
	 */
	public static void dump (final Node node)
	{
		doDump (System.out, node, 0);
	}
	
	/**
	 * Recursively walks the DOM tree starting at the given <CODE>Node</CODE>
	 * printing the gory details of its construction to the indicated
	 * <CODE>PrintStream</CODE>.
	 * 
	 * @param	out				The <CODE>PrintStream</CODE> for output.
	 * @param 	node			The <CODE>Node</CODE> to start the dump at.
	 * @since	TFP 1.0
	 */
	public static void dump (PrintStream out, final Node node)
	{
		doDump (out, node, 0);
		out.flush ();
	}
	
	/**
	 * Recursively walks the DOM tree starting at the given <CODE>Node</CODE>
	 * printing the gory details of its construction to the indicated
	 * <CODE>PrintWriter</CODE>.
	 * 
	 * @param	out				The <CODE>PrintWriter</CODE> for output.
	 * @param 	node			The <CODE>Node</CODE> to start the dump at.
	 * @since	TFP 1.0
	 */
	public static void dump (PrintWriter out, final Node node)
	{
		doDump (out, node, 0);
		out.flush ();
	}
	
	/**
	 * Ensures no instances can be constructed.
	 * @since	TFP 1.0
	 */
	private XmlUtility ()
	{ }
	
	/**
	 * Performs the actual recursive dumping of a DOM tree to a given
	 * <CODE>PrintStream</CODE>. Note that dump is intended to be a detailed
	 * debugging aid rather than pretty to look at. 
	 * 
	 * @param 	out				The <CODE>PrintStream</CODE> to write to.
	 * @param 	node			The <CODE>Node</CODE> under consideration.
	 * @param 	indent			The level of indentation.
	 * @see		#dump(Node)
	 * @see		#dump(PrintStream, Node)
	 * @since	TFP 1.0
	 */
	private static void doDump (PrintStream out, final Node node, int indent)
	{
		if (node != null) {
			for (int index = 0; index < indent; ++index)
				out.write (' ');
			
			switch (node.getNodeType ()) {
			case Node.DOCUMENT_NODE:
				{
					Document	document = (Document) node;
					
					out.println ("DOCUMENT:");
					
					doDump (out, document.getDoctype (), indent + 1);
					doDump (out, document.getDocumentElement (), indent + 1);
					break;
				}
			
			case Node.DOCUMENT_TYPE_NODE:
				{
					DocumentType	type = (DocumentType) node;
					
					out.println ("DOCTYPE: ["
						+ "name=" + format (type.getName ()) + ","
						+ "publicId=" + format (type.getPublicId ()) + ","
						+ "systemId=" + format (type.getSystemId ()) + "]");
					break;
				}
			
			case Node.ELEMENT_NODE:
				{
					Element		element = (Element) node;
					
					out.println ("ELEMENT: ["
						+ "ns=" + format (element.getNamespaceURI ()) + ","
						+ "name=" + format (element.getLocalName ()) + "]");
					
					NamedNodeMap attrs = element.getAttributes ();
					
					for (int index = 0; index < attrs.getLength (); ++index)
						doDump (out, attrs.item (index), indent + 1);
					
					for (Node child = element.getFirstChild (); child != null;) {
						doDump (out, child, indent + 1);
						child = child.getNextSibling ();
					}
					break;
				}
			case Node.ATTRIBUTE_NODE:
				{
					Attr		attr = (Attr) node;
					
					out.println ("ATTRIBUTE: ["
						+ "ns=" + format (attr.getNamespaceURI ()) + ","
						+ "prefix=" + format (attr.getPrefix ()) + ","
						+ "name=" + format (attr.getLocalName ()) + ","
						+ "value=" + format (attr.getNodeValue ()) + "]");
					break;
				}
				
			case Node.TEXT_NODE:
				{
					Text		text = (Text) node;
					
					out.println ("TEXT: [" + format (text.getNodeValue ()) + "]");

					for (Node child = text.getFirstChild (); child != null;) {
						doDump (out, child, indent + 1);
						child = child.getNextSibling ();
					}
					break;
				}
				
			case Node.CDATA_SECTION_NODE:
				{
					CDATASection data = (CDATASection) node;
				
					out.println ("CDATA: [" + format (data.getNodeValue ()) + "]");
					break;
				}
				
			case Node.COMMENT_NODE:
				{
					Comment		comm = (Comment) node;
					
					out.println ("COMMENT: [" + format (comm.getNodeValue ()) + "]");
					break;
				}
			
			default:
				out.println ("UNKNOWN: [type=" + node.getNodeType () + "]");
				break;
			}
		}
	}
	
	/**
	 * Performs the actual recursive dumping of a DOM tree to a given
	 * <CODE>PrintWriter</CODE>. Note that dump is intended to be a detailed
	 * debugging aid rather than pretty to look at. 
	 * 
	 * @param 	out				The <CODE>PrintWriter</CODE> to write to.
	 * @param 	node			The <CODE>Node</CODE> under consideration.
	 * @param 	indent			The level of indentation.
	 * @see		#dump(PrintWriter, Node)
	 * @since	TFP 1.0
	 */
	private static void doDump (PrintWriter out, final Node node, int indent)
	{
		if (node != null) {
			for (int index = 0; index < indent; ++index)
				out.write (' ');
			
			switch (node.getNodeType ()) {
			case Node.DOCUMENT_NODE:
				{
					Document	document = (Document) node;
					
					out.println ("DOCUMENT:");
					
					doDump (out, document.getDoctype (), indent + 1);
					doDump (out, document.getDocumentElement (), indent + 1);
					break;
				}
			
			case Node.DOCUMENT_TYPE_NODE:
				{
					DocumentType	type = (DocumentType) node;
					
					out.println ("DOCTYPE: ["
						+ "name=" + format (type.getName ()) + ","
						+ "publicId=" + format (type.getPublicId ()) + ","
						+ "systemId=" + format (type.getSystemId ()) + "]");
					break;
				}
			
			case Node.ELEMENT_NODE:
				{
					Element		element = (Element) node;
					
					out.println ("ELEMENT: ["
						+ "ns=" + format (element.getNamespaceURI ()) + ","
						+ "name=" + format (element.getLocalName ()) + "]");
					
					NamedNodeMap attrs = element.getAttributes ();
					
					for (int index = 0; index < attrs.getLength (); ++index)
						doDump (out, attrs.item (index), indent + 1);
					
					for (Node child = element.getFirstChild (); child != null;) {
						doDump (out, child, indent + 1);
						child = child.getNextSibling ();
					}
					break;
				}
			case Node.ATTRIBUTE_NODE:
				{
					Attr		attr = (Attr) node;
					
					out.println ("ATTRIBUTE: ["
						+ "ns=" + format (attr.getNamespaceURI ()) + ","
						+ "prefix=" + format (attr.getPrefix ()) + ","
						+ "name=" + format (attr.getLocalName ()) + ","
						+ "value=" + format (attr.getNodeValue ()) + "]");
					break;
				}
				
			case Node.TEXT_NODE:
				{
					Text		text = (Text) node;
					
					out.println ("TEXT: [" + format (text.getNodeValue ()) + "]");

					for (Node child = text.getFirstChild (); child != null;) {
						doDump (out, child, indent + 1);
						child = child.getNextSibling ();
					}
					break;
				}
				
			case Node.CDATA_SECTION_NODE:
				{
					CDATASection data = (CDATASection) node;
				
					out.println ("CDATA: [" + format (data.getNodeValue ()) + "]");
					break;
				}
				
			case Node.COMMENT_NODE:
				{
					Comment		comm = (Comment) node;
					
					out.println ("COMMENT: [" + format (comm.getNodeValue ()) + "]");
					break;
				}
			
			default:
				out.println ("UNKNOWN: [type=" + node.getNodeType () + "]");
				break;
			}
		}
	}
	
	/**
	 * Converts a <CODE>String</CODE> value to a displayable format for
	 * debugging. This entails wrapping non-null strings with quotes and
	 * replacing null strings with the word 'null'.
	 * 
	 * @param 	value			The <CODE>String</CODE> to be formatted.
	 * @return	The formatted <CODE>String</CODE> value.
	 * @since	TFP 1.0
	 */
	private static String format (final String value)
	{
		if (value != null)
			return ("\"" + value + "\"");
		else
			return ("null");
	}
}