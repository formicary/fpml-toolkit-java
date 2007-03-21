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

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.handcoded.xml.parser.DOMParser;
import com.handcoded.xml.resolver.Catalog;

/**
 * Provides utility functions for processing XML.
 * <P>
 * The new Java APIs for XML parsing and validation require that you know in
 * advance whether a file is DTD or schema based. If you don't know if
 * which kind of document you have then the safest approach seems to be
 * to do a non-validating parse and check for the presence of a DOCTYPE
 * node. Obviously this means that the validation is slower (as you do
 * it twice) and it also means that the input source must be reprocessable
 * (e.g. either a file or a string but not a stream). 
 *	 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class XmlUtility
{
	/**
	 * A constant value indicating that only DTD based documents are expected.
	 * @see #validatingParse(int, File, Schema, EntityResolver, ErrorHandler)
	 * @see #validatingParse(int, String, Schema, EntityResolver, ErrorHandler)
	 */
	public static final int	DTD_ONLY		= 1;
	
	/**
	 * A constant value indicating that only schema based documents are expected.
	 * @see #validatingParse(int, File, Schema, EntityResolver, ErrorHandler)
	 * @see #validatingParse(int, String, Schema, EntityResolver, ErrorHandler)
	 */
	public static final int	SCHEMA_ONLY		= 2;
	
	/**
	 * A constant value indicating that either a DTD or schema based documents
	 * could be provided.
	 * @see #validatingParse(int, File, Schema, EntityResolver, ErrorHandler)
	 * @see #validatingParse(int, String, Schema, EntityResolver, ErrorHandler)
	 */
	public static final int DTD_OR_SCHEMA	= 3;
	
	/**
	 * Provides access to the default <CODE>Catalog</CODE> instance to be used for
	 * entity resolution. If the <CODE>-catalog</CODE> option was not specified
	 * then the result will be <CODE>null</CODE>
	 * 
	 * @return	The <CODE>Catalog</CODE> instance or <CODE>null</CODE>.
	 * @since	TFP 1.0
	 */
	public static Catalog getDefaultCatalog ()
	{
		return (defaultCatalog);
	}
	
	/**
	 * 
	 * @param 	catalog
	 * @since	TFP 1.1
	 */
	public static void setDefaultCatalog (Catalog catalog)
	{
		defaultCatalog = catalog;
	}
	
	/**
	 * Provides access to the defailt schema set.
	 * 
	 * @return	The <CODE>SchemaSet</CODE> instance.
	 */
	public static SchemaSet getDefaultSchemaSet ()
	{
		return (defaultSchemaSet);
	}
		
	/**
	 * Performs a non-validating parse of the indicated XML string discarding any
	 * errors generated.
	 * 
	 * @param 	xml			The XML <CODE>String</CODE> to be processed.
	 * @return	A <CODE>Document</CODE> instance if the parse succeeded or
	 * 			<CODE>null</CODE> if it failed.
	 * @since	TFP 1.0
	 */
	public static Document nonValidatingParse (final String xml)
	{
		Document	document	= null;
		
		try {
			document = new DOMParser (false, true, null, null,
				new ErrorHandler ()
				{
					public void fatalError (SAXParseException notUsed)
					{  }
					
					public void error (SAXParseException notUsed)
					{  }
					
					public void warning (SAXParseException notUsed)
					{  }
				}).parse (xml);
		}
		catch (ParserConfigurationException error) {
			logger.severe ("JAXP failed to provided a XML parser");
		}
		catch (IOException error) {
			logger.log (Level.SEVERE, "Unexpected I/O error", error);
		}
		return (document);
	}
	
	/**
	 * Performs a non-validating parse of the indicated XML file discarding any
	 * errors generated.
	 * 
	 * @param 	file			The <CODE>File</CODE> to be processed.
	 * @return	A <CODE>Document</CODE> instance if the parse succeeded or
	 * 			<CODE>null</CODE> if it failed.
	 * @since	TFP 1.0
	 */
	public static Document nonValidatingParse (File file)
	{
		Document	document	= null;
		
		try {
			document = new DOMParser (false, true, null, null,
				new ErrorHandler ()
				{
					public void fatalError (SAXParseException notUsed)
					{  }
					
					public void error (SAXParseException notUsed)
					{  }
					
					public void warning (SAXParseException notUsed)
					{  }
				}).parse (file);
		}
		catch (ParserConfigurationException error) {
			logger.severe ("JAXP failed to provided a XML parser");
		}
		catch (IOException error) {
			logger.log (Level.SEVERE, "Unexpected I/O error", error);
		}
		return (document);
	}
	
	/**
	 * Performs a validating parse of the indicated XML <CODE>String<CODE> using the
	 * most optimal technique given the mode. If the type of grammar is unknown
	 * then a non-validating parse is done first and the document inspected to
	 * see if it references a DOCTYPE.
	 * 
	 * @param 	grammar			Indicates the type of grammar used by the document. 
	 * @param 	xml				The XML <CODE>String</CODE> to be processed.
	 * @param 	schema			A compiled <CODE>Schema</CODE> collection.
	 * @param 	entityResolver	The <CODE>EntityResolver</CODE>.
	 * @param 	errorHandler	The users <CODE>ErrorHandler</CODE>.
	 * @return	A <CODE>Document</CODE> instance if the parse succeeded or
	 * 			<CODE>null</CODE> if it failed.
	 * @since	TFP 1.0
	 */
	public static Document validatingParse (int grammar, final String xml, Schema schema,
			EntityResolver entityResolver, ErrorHandler errorHandler)
	{
		Document	document	= null;
		
		if (grammar == SCHEMA_ONLY) {
			try {
				return (new DOMParser (false, true, schema, entityResolver, errorHandler).parse (xml));
			}
			catch (ParserConfigurationException error) {
				logger.severe ("JAXP failed to provided a XML parser");
			}
			catch (IOException error) {
				logger.log (Level.SEVERE, "Unexpected I/O error", error);
			}
			return (null);
		}
		
		if (grammar == DTD_OR_SCHEMA) {
			if ((document = nonValidatingParse (xml)) == null) return (null);
			
			grammar = (document.getDoctype() != null) ? DTD_ONLY : SCHEMA_ONLY;
		}

		try {
			if (grammar == DTD_ONLY) {
				document = new DOMParser (true, true, null, entityResolver, errorHandler).parse (xml);
			}
			
			if (grammar == SCHEMA_ONLY) {
				DOMResult		result	= new DOMResult ();
				
				Validator validator = schema.newValidator ();
				validator.setErrorHandler (errorHandler);
				validator.validate (new DOMSource (document), result);
				
				document = (Document) result.getNode ();				
			}
		}
		catch (ParserConfigurationException error) {
			logger.severe ("JAXP failed to provided a XML parser");
		}
		catch (SAXException error) {
			logger.log (Level.SEVERE, "Unexpected SAX Exception", error);
		}
		catch (IOException error) {
			logger.log (Level.SEVERE, "Unexpected I/O error", error);
		}
		return (document);
	}
	
	/**
	 * Performs a validating parse of the indicated <CODE>File<CODE> using the
	 * most optimal technique given the mode. If the type of grammar is unknown
	 * then a non-validating parse is done first and the document inspected to
	 * see if it references a DOCTYPE.
	 * 
	 * @param 	grammar			Indicates the type of grammar used by the document. 
	 * @param 	file			The <CODE>File</CODE> to be processed.
	 * @param 	schema			A compiled <CODE>Schema</CODE> collection.
	 * @param 	entityResolver	The <CODE>EntityResolver</CODE>.
	 * @param 	errorHandler	The users <CODE>ErrorHandler</CODE>.
	 * @return	A <CODE>Document</CODE> instance if the parse succeeded or
	 * 			<CODE>null</CODE> if it failed.
	 * @since	TFP 1.0
	 */
	public static Document validatingParse (int grammar, File file, Schema schema,
			EntityResolver entityResolver, ErrorHandler errorHandler)
	{
		Document	document	= null;
		
		if (grammar == SCHEMA_ONLY) {
			try {
				return (new DOMParser (false, true, schema, entityResolver, errorHandler).parse (file));
			}
			catch (ParserConfigurationException error) {
				logger.severe ("JAXP failed to provided a XML parser");
			}
			catch (IOException error) {
				logger.log (Level.SEVERE, "Unexpected I/O error", error);
			}
			return (null);
		}
		
		if (grammar == DTD_OR_SCHEMA) {
			if ((document = nonValidatingParse (file)) == null) return (null);
			
			grammar = (document.getDoctype() != null) ? DTD_ONLY : SCHEMA_ONLY;
		}

		try {
			if (grammar == DTD_ONLY) {
				document = new DOMParser (true, true, null, entityResolver, errorHandler).parse (file);
			}
			
			if (grammar == SCHEMA_ONLY) {
				DOMResult		result	= new DOMResult ();
				
				Validator validator = schema.newValidator ();
				validator.setErrorHandler (errorHandler);
				validator.validate (new DOMSource (document), result);
				
				document = (Document) result.getNode ();				
			}
		}
		catch (ParserConfigurationException error) {
			logger.severe ("JAXP failed to provided a XML parser");
		}
		catch (SAXException error) {
			logger.log (Level.SEVERE, "Unexpected SAX Exception", error);
		}
		catch (IOException error) {
			logger.log (Level.SEVERE, "Unexpected I/O error", error);
		}
		return (document);
	}
	
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
	 * A <CODE>Logger</CODE> instance used to report serious errors.
	 * @since	TFP 1.0
	 */
	private static Logger	logger
		= Logger.getLogger ("com.handcoded.xml.XmlUtility");

	/**
	 * The default catalog used to resolve DTD and schema references.
	 * @since	TFP 1.0
	 */
	private static Catalog		defaultCatalog	= null;

	/**
	 * The default schema collection used to validate schema based documents.
	 * @since	TFp 1.0
	 */
	private static SchemaSet	defaultSchemaSet = new SchemaSet ();
		
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