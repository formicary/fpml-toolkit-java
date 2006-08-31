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

package com.handcoded.fpml;

import java.util.Hashtable;
import java.util.Vector;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.handcoded.meta.DirectConversion;
import com.handcoded.meta.Helper;
import com.handcoded.meta.Schema;
import com.handcoded.xml.DOM;
import com.handcoded.xml.XPath;

/**
 * The <CODE>Conversions</CODE> class contains the <CODE>Conversion</CODE>
 * instances used to migrate FpML documents between the different releases
 * of the specification.
 * <P>
 * Currently only conversions from earlier releases to later ones are
 * supported.
 * 
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class Conversions
{
	/**
	 * The <CODE>R1_0__R2_0</CODE> class contains the logic to migrate a
	 * FpML 1-0 instance document to 2-0. The specific changes needed (other
	 * than basic DOCTYPE changes) are:
	 * <UL>
	 * <LI>The &lt;product&gt; constainer element was removed.</LI>
	 * <LI>Superfluous <CODE>type</CODE> attributes are removed.</LI>
	 * </UL> 
	 * @since	TFP 1.0
	 */
	public static class R1_0__R2_0 extends DirectConversion
	{
		public R1_0__R2_0 ()
		{
			super  (Releases.R1_0, Releases.R2_0);
		}
		
		/**
		 * {@inheritDoc}
		 * @since	TFP 1.0
		 */
		public Document convert (Document source, Helper helper)
		{
			Document		target 	= getTargetRelease ().newInstance ("FpML");
			Element			oldRoot = source.getDocumentElement ();
			Element			newRoot	= target.getDocumentElement ();
			NamedNodeMap	attrs  	= oldRoot.getAttributes();;
			
			// Transfer the scheme default attributes
			for (int index = 0; index < attrs.getLength (); ++index) {
				Attr attr = (Attr) attrs.item (index);
				if (attr.getName ().endsWith ("SchemeDefault")) {
					String name  = attr.getName ();
					String value = attr.getValue ();
					
					if (Releases.R1_0.getSchemeDefaults ().getDefaultUriForAttribute (name).equals (value))
						value = Releases.R2_0.getSchemeDefaults ().getDefaultUriForAttribute (name);
					
					if (value != null) newRoot.setAttributeNS (null, name, value);
				}
			}
			
			// Transcribe each of the first level child elements
			for (Node node = oldRoot.getFirstChild (); node != null;) {
				transcribe (node, target, newRoot);
				node = node.getNextSibling ();
			}

			return (target);
		}
		
		/**
		 * Recursively copies the structure of the old FpML 1-0 document
		 * into a new FpML 2-0 document adjusting the elements and attributes
		 * as necessary.
		 * 
		 * @param 	context			The <CODE>node</CODE> to be copied.
		 * @param 	document		The new <CODE>Document</CODE> instance.
		 * @param 	parent			The new parent <CODE>Node</CODE>.
		 * @since	TFP 1.0
		 */
		private void transcribe (Node context, Document document, Node parent)
		{
			switch (context.getNodeType ()) {
			case Node.ELEMENT_NODE:
				{
					Element		element = (Element) context;

					if (context.getLocalName ().equals ("product")) {
						// Replace this element with a copy of its children
						for (Node node = element.getFirstChild (); node != null;) {
							transcribe (node, document, parent);
							node = node.getNextSibling ();
						}
					}
					else {
						Element 		clone = document.createElementNS (null, element.getLocalName ());
						NamedNodeMap	attrs = element.getAttributes ();
						
						parent.appendChild (clone);
						
						// Transfer and update attributes
						for (int index = 0; index < attrs.getLength (); ++index) {
							Attr attr 	= (Attr) attrs.item (index);
							String name = attr.getName ();
							
							if (!(name.equals ("type") || name.equals ("base"))) {
								String value  = attr.getValue ();
								
								if (name.endsWith ("Scheme")) {
									String oldDefault = Releases.R1_0.getSchemeDefaults ().getDefaultAttributeForScheme (name);
									String newDefault = Releases.R2_0.getSchemeDefaults ().getDefaultAttributeForScheme (name);
									
									if (oldDefault != null && newDefault != null) {
										String 	defaultUri = Releases.R1_0.getSchemeDefaults ().getDefaultUriForAttribute (oldDefault); 
										if ((defaultUri != null) && defaultUri.equals (value))
											value = Releases.R2_0.getSchemeDefaults ().getDefaultUriForAttribute (newDefault);
									}
								}
								
								if (value != null) clone.setAttributeNS (null, name, value);
							}
						}
						
						// Recursively copy the child node across
						for (Node node = element.getFirstChild (); node != null;) {
							transcribe (node, document, clone);
							node = node.getNextSibling ();
						}
					}
					break;
				}
				
			default:
				parent.appendChild (document.importNode (context, false));
			}
		}
	};

	/**
	 * The <CODE>R2_0__R3_0</CODE> class contains the logic to migrate a
	 * FpML 2-0 instance document to 3-0. The specific changes needed (other
	 * than basic DOCTYPE changes) are:
	 * <UL>
	 * <LI><CODE>href</CODE> attributes are come <CODE>IDREF</CODE> strings
	 * rather then XLink expressions.</LI>
	 * <LI>Superfluous <CODE>type</CODE> attributes are removed.</LI>
	 * </UL> 
	 * @since	TFP 1.0
	 */
	public static class R2_0__TR3_0 extends DirectConversion
	{
		public R2_0__TR3_0 ()
		{
			super (Releases.R2_0, Releases.TR3_0);
		}
		
		/**
		 * {@inheritDoc}
		 * @since	TFP 1.0
		 */
		public Document convert (Document source, Helper helper)
		{
			Document		target 	= getTargetRelease ().newInstance ("FpML");
			Element			oldRoot = source.getDocumentElement ();
			Element			newRoot	= target.getDocumentElement ();
			NamedNodeMap	attrs  	= oldRoot.getAttributes();;
			
			// Transfer the scheme default attributes
			for (int index = 0; index < attrs.getLength (); ++index) {
				Attr attr = (Attr) attrs.item (index);
				if (attr.getName ().endsWith ("SchemeDefault")) {
					String name  = attr.getName ();
					String value = attr.getValue ();
					
					if (Releases.R2_0.getSchemeDefaults ().getDefaultUriForAttribute (name).equals (value))
						value = Releases.TR3_0.getSchemeDefaults ().getDefaultUriForAttribute (name);
					
					if (value != null) newRoot.setAttributeNS (null, name, value);
				}
			}
			
			// Transcribe each of the first level child elements
			Vector		parties = new Vector ();
			for (Node node = oldRoot.getFirstChild (); node != null;) {
				transcribe (node, target, newRoot, parties);
				node = node.getNextSibling ();
			}
	
			// Then append the saved party elements
			for (int index = 0; index < parties.size (); ++index)
				transcribe ((Node) parties.elementAt (index), target, newRoot, null);
	
			return (target);
		}
		
		/**
		 * Recursively copies the structure of the old FpML 2-0 document
		 * into a new FpML 3-0 document adjusting the elements and attributes
		 * as necessary.
		 * 
		 * @param 	context			The <CODE>node</CODE> to be copied.
		 * @param 	document		The new <CODE>Document</CODE> instance.
		 * @param 	parent			The new parent <CODE>Node</CODE>.
		 * @param	parties			A <CODE>Vector</CODE> used to collect elements.
		 * @since	TFP 1.0
		 */
		private void transcribe (Node context, Document document, Node parent, Vector parties)
		{
			switch (context.getNodeType ()) {
			case Node.ELEMENT_NODE:
				{
					Element		element = (Element) context;
		
					// If this is the first pass thru the tree then save
					// party elements instead of processing them.
					if ((parties != null) && element.getNodeName ().equals ("party")) {
						parties.add (element);
						return;
					}
					
					Element 		clone = document.createElementNS (null, element.getLocalName ());
					NamedNodeMap	attrs = element.getAttributes ();
					
					parent.appendChild (clone);
					
					// Transfer and update attributes converting hrefs
					for (int index = 0; index < attrs.getLength (); ++index) {
						Attr attr 	= (Attr) attrs.item (index);
						String name = attr.getName ();
						
						if (!(name.equals ("type") || name.equals ("base"))) {
							String value  = attr.getValue ();
							
							if (name.equals ("href")) {
								if (value.startsWith ("#"))
									value = value.substring (1);
							}
							else if (name.endsWith ("Scheme")) {
								String oldDefault = Releases.R2_0.getSchemeDefaults ().getDefaultAttributeForScheme (name);
								String newDefault = Releases.TR3_0.getSchemeDefaults ().getDefaultAttributeForScheme (name);
								
								if (oldDefault != null && newDefault != null) {
									String 	defaultUri = Releases.R2_0.getSchemeDefaults ().getDefaultUriForAttribute (oldDefault); 
									if ((defaultUri != null) && defaultUri.equals (value))
										value = Releases.TR3_0.getSchemeDefaults ().getDefaultUriForAttribute (newDefault);
								}
							}
							
							if (value != null) clone.setAttributeNS (null, name, value);
						}
					}
					
					// Recursively copy the child node across
					for (Node node = element.getFirstChild (); node != null;) {
						transcribe (node, document, clone, parties);
						node = node.getNextSibling ();
					}
					break;
				}
				
			default:
				parent.appendChild (document.importNode (context, false));
			}
		}
	};

	/**
	 * The <CODE>R3_0__R4_0</CODE> class contains the logic to migrate a
	 * FpML 3-0 instance document to 4-0. The specific changes needed (other
	 * than basic DOCTYPE changes) are:
	 * <UL>
	 * <LI>The document is becomes XML schema referencing.</LI>
	 * <LI>Legacy documents become FpML DataDocument instances.</LI>
	 * <LI>The <b>dateRelativeTo</b> referencing mechanism is changed.</LI>
	 * <LI>The value set for &lt;fraDiscounting&gt; was modified.</LI>
	 * <LI>The element &lt;calculationAgentPartyReference&gt; was moved from
	 * the trade header into the trade structure.</LI>
	 * <LI>The &lt;informationSource&gt; element is renamed &lt;primaryRateSource&gt;
	 * within &lt;fxSpotRateSource&gt; elements.</LI>
	 * <LI>The structure of the <B>equityOption</B> element is changed.</li>
	 * <LI>SchemeDefaults are removed and non-defaulted schemes appear
	 * on referencing elements,</LI>
	 * </UL>
	 * @since	TFP 1.0
	 */
	public static class TR3_0__R4_0 extends DirectConversion 
	{
		public TR3_0__R4_0 ()
		{
			super (Releases.TR3_0, Releases.R4_0);
		}
		
		/**
		 * {@inheritDoc}
		 * @since	TFP 1.0
		 */
		public Document convert (Document source, Helper helper)
		{
			Document	target = getTargetRelease ().newInstance ("FpML");
			Element			oldRoot = source.getDocumentElement ();
			Element			newRoot	= target.getDocumentElement ();
			Hashtable		cache	= new Hashtable ();
			
			newRoot.setAttributeNS (Schema.INSTANCE_URL, "xsi:type", "DataDocument");
			
			// Transcribe each of the first level child elements
			for (Node node = oldRoot.getFirstChild (); node != null;) {
				transcribe (node, target, newRoot, cache, true);
				node = node.getNextSibling ();
			}
	
			return (target);
		}

		/**
		 * Recursively copies the structure of the old FpML 3-0 document
		 * into a new FpML 4-0 document adjusting the elements and attributes
		 * as necessary.
		 * 
		 * @param 	context			The <CODE>node</CODE> to be copied.
		 * @param 	document		The new <CODE>Document</CODE> instance.
		 * @param 	parent			The new parent <CODE>Node</CODE>.
		 * @param	cache			Hashtable used to store items during conversion.
		 * @param	caching			Indicates that items should be cached.
		 * @since	TFP 1.0
		 */
		private void transcribe (Node context, Document document, Node parent,
				Hashtable cache, boolean caching)
		{
			switch (context.getNodeType ()) {
			case Node.ELEMENT_NODE:
				{
					Element			element = (Element) context;
					Element 		clone 	= document.createElementNS (null, element.getLocalName ());
					
					// Cache the <calculationAgentPartyReference> element
					if (caching &&
						element.getLocalName ().equals ("calculationAgentPartyReference") &&
						element.getParentNode ().getLocalName ().equals ("tradeHeader")) {
						cache.put ("calculationAgentPartyReference", element);
						return;
					}

					// Handle the restructuring of the equityOption element components
					if (element.getLocalName ().equals ("buyerParty")) {
						clone = document.createElement ("buyerPartyReference");
						
						clone.setAttribute ("href", DOM.getElementByLocalName (element, "partyReference").getAttribute ("href"));
						parent.appendChild (clone);
						break;
					}
					if (element.getLocalName ().equals ("sellerParty")) {
						clone = document.createElement ("sellerPartyReference");
							
						clone.setAttribute ("href", DOM.getElementByLocalName (element, "partyReference").getAttribute ("href"));
						parent.appendChild (clone);
						break;
					}
					if (element.getLocalName ().equals ("underlying")) {
						clone = document.createElement ("underlyer");
						Element singleUnderlyer = document.createElement ("singleUnderlyer");
						Element underlyingAsset;

						if (element.getElementsByTagName ("extraordinaryEvents").getLength () == 0)
							underlyingAsset = document.createElement ("index");
						else
							underlyingAsset = document.createElement ("equity");
		
						NodeList	list = element.getElementsByTagName ("instrumentId");
						for (int index = 0; index < list.getLength (); ++index)
							transcribe ((Element) list.item (index), document, underlyingAsset, cache, caching);

						Element description = document.createElement ("description");
						DOM.setInnerText(description, DOM.getInnerText(XPath.path (element, "description")));
						underlyingAsset.appendChild (description);

						Element optional;

						if ((optional = XPath.path (element, "currency")) != null)
							transcribe (optional, document, underlyingAsset, cache, caching);

						if ((optional = XPath.path (element, "exchangeId")) != null)
							transcribe (optional, document, underlyingAsset, cache, caching);

						if ((optional = XPath.path (element, "clearanceSystem")) != null)
							transcribe (optional, document, underlyingAsset, cache, caching);

						singleUnderlyer.appendChild (underlyingAsset);
						clone.appendChild (singleUnderlyer);
						parent.appendChild (clone);
						break;
					}
					
					if (element.getLocalName ().equals ("settlementDate")) {
						clone = document.createElement ("settlementDate");
						Element relativeDate = document.createElement ("relativeDate");

						NodeList list = element.getChildNodes ();
						for (int index = 0; index < list.getLength (); ++index)
							transcribe (list.item (index), document, relativeDate, cache, caching);

						clone.appendChild (relativeDate);
						parent.appendChild (clone);
						break;
					}
					
					// Handle restructuring of FX components
					if (element.getLocalName ().equals ("fixing")) {
						clone = document.createElement ("fixing");

						Element target;

						if ((target = XPath.path (element, "primaryRateSource")) != null) 
							transcribe (target, document, clone, cache, caching);
						if ((target = XPath.path (element, "secondaryRateSource")) != null)
							transcribe (target, document, clone, cache, caching);
						if ((target = XPath.path (element, "fixingTime")) != null)
							transcribe (target, document, clone, cache, caching);
						if ((target = XPath.path (element, "quotedCurrencyPair")) != null)
							transcribe (target, document, clone, cache, caching);
						if ((target = XPath.path (element, "fixingDate")) != null)
							transcribe (target, document, clone, cache, caching);

						parent.appendChild (clone);
						break;
					}

					// Handle elements that get renamed
					if (element.getLocalName ().equals ("informationSource") &&
						element.getParentNode ().getLocalName ().equals ("fxSpotRateSource"))
						clone = document.createElement ("primaryRateSource");
					else
						clone = document.createElement (element.getLocalName ());

					// Generate the <calculationAgentPartyReference> before any peer element
					if (element.getLocalName ().equals ("calculationAgentBusinessCenter") ||
						element.getLocalName ().equals ("governingLaw") ||
						element.getLocalName ().equals ("documentation")) {
						Element agent = (Element) cache.get ("calculationAgentPartyReference");

						if (agent != null) {
							Element container = document.createElement ("calculationAgent");

							clone.appendChild (container);
							transcribe (agent, document, parent, cache, false);
							cache.remove ("calculationAgentPartyReference");
						}
					}

					parent.appendChild (clone);

					// Change the data value of fraDiscounting
					if (element.getLocalName ().equals ("fraDiscounting")) {
						if (DOM.getInnerText (element).trim ().equals ("true"))
							DOM.setInnerText (clone, "ISDA");
						else
							DOM.setInnerText (clone, "NONE");
						
						break;
					}

					// Handle scheme values which changed capitalisation
					if (element.getLocalName ().equals ("quoteBasis")) {
						String	value	= DOM.getInnerText (element).trim ().toUpperCase ();

						if (value.equals ("CURRENCY1PERCURRENCY2"))
							DOM.setInnerText (clone, "Currency1PerCurrency2");
						else if (value.equals ("CURRENCY2PERCURRENCY1"))
							DOM.setInnerText (clone, "Currency2PerCurrency1");
						else
							DOM.setInnerText (clone, DOM.getInnerText (element).trim ());
						
						break;
					}
					if (element.getLocalName ().equals ("sideRateBasis")) {
						String	value	= DOM.getInnerText (element).trim ().toUpperCase ();

						if (value.equals ("CURRENCY1PERBASECURRENCY"))
							DOM.setInnerText (clone, "Currency1PerBaseCurrency");
						else if (value.equals ("BASECURRENCYPERCURRENCY1"))
							DOM.setInnerText (clone, "BaseCurrencyPerCurrency1");
						else if (value.equals ("CURRENCY2PERBASECURRENCY"))
							DOM.setInnerText (clone, "Currency2PerBaseCurrency");
						else if (value.equals ("BASECURRENCYPERCURRENCY2"))
							DOM.setInnerText (clone, "BaseCurrencyPerCurrency2");
						else
							DOM.setInnerText (clone, DOM.getInnerText (element).trim ());
						
						break;
					}
					if (element.getLocalName ().equals ("premiumQuoteBasis")) {
						String	value	= DOM.getInnerText (element).trim ().toUpperCase ();

						if (value.equals ("PERCENTAGEOFCALLCURRENCYAMOUNT"))
							DOM.setInnerText (clone, "PercentageOfCallCurrencyAmount");
						else if (value.equals ("PERCENTAGEOFPUTCURRENCYAMOUNT"))
							DOM.setInnerText (clone, "PercentageOfPutCurrencyAmount");
						else if (value.equals ("CALLCURRENCYPERPUTCURRENCY"))
							DOM.setInnerText (clone, "CallCurrencyPerPutCurrency");
						else if (value.equals ("PUTCURRENCYPERCALLCURRENCY"))
							DOM.setInnerText (clone, "PutCurrencyPerCallCurrency");
						else if (value.equals ("EXPLICIT"))
							DOM.setInnerText (clone, "Explicit");
						else
							DOM.setInnerText (clone, DOM.getInnerText (element).trim ());

						break;
					}
					if (element.getLocalName ().equals ("strikeQuoteBasis") ||
						element.getLocalName ().equals ("averageRateQuoteBasis")) {
						String	value	= DOM.getInnerText (element).trim ().toUpperCase ();

						if (value.equals ("CALLCURRENCYPERPUTCURRENCY"))
							DOM.setInnerText (clone, "CallCurrencyPerPutCurrency");
						else if (value.equals ("PUTCURRENCYPERCALLCURRENCY"))
							DOM.setInnerText (clone, "PutCurrencyPerCallCurrency");
						else
							DOM.setInnerText (clone, DOM.getInnerText (element).trim ());
						
						break;
					}
					if (element.getLocalName ().equals ("fxBarrierType")) {
						String	value	= DOM.getInnerText (element).trim ().toUpperCase ();

						if (value.equals ("KNOCKIN"))
							DOM.setInnerText (clone, "Knockin");
						else if (value.equals ("KNOCKOUT"))
							DOM.setInnerText (clone, "Knockout");
						else if (value.equals ("REVERSEKNOCKIN"))
							DOM.setInnerText (clone, "ReverseKnockin");
						else if (value.equals ("REVERSEKNOCKOUT"))
							DOM.setInnerText (clone, "ReverseKnockout");
						else
							DOM.setInnerText (clone, DOM.getInnerText (element).trim ());

						break;
					}

					// Handle elements which changed from schemes to enumerations
					if (element.getLocalName ().equals ("optionType")) {
						DOM.setInnerText (clone, DOM.getInnerText (element).trim ());
						break;
					}
					if (element.getLocalName ().equals ("nationalisationOrInsolvency")) {
						DOM.setInnerText (clone, DOM.getInnerText (element).trim ());
						break;
					}
					if (element.getLocalName ().equals ("delisting")) {
						DOM.setInnerText (clone, DOM.getInnerText (element).trim ());
						break;
					}

					NamedNodeMap	attrs = element.getAttributes ();
					for (int index = 0; index < attrs.getLength(); ++index) {
						Attr		attr = (Attr) attrs.item (index);
						String		name = attr.getLocalName ();

						if (!(name.equals ("type") || name.equals ("base")))
							clone.setAttribute (name, attr.getValue ());
					}

					// Ignore text string under dateRelativeTo
					if (element.getLocalName ().equals ("dateRelativeTo")) break;

					// Fix up id and href on cash settlement dateRelativeTo definitions
					if (element.getLocalName ().equals ("cashSettlementPaymentDate") &&
						element.getParentNode ().getParentNode ().getLocalName ().equals ("optionalEarlyTermination")) {
						String		id;

						for (int count = 1;;) {
							id = "AutoRef" + (count++);
							if (document.getElementById (id) == null) break;
						}

						clone.setAttribute ("id", id);
						
						XPath.path ((Element) parent, "cashSettlementValuationDate", "dateRelativeTo").setAttribute ("href", id);
					}
					
					// Recursively copy the child node across
					NodeList	list = element.getChildNodes ();
					for (int index = 0; index < list.getLength (); ++index)
						transcribe (list.item (index), document, clone, cache, caching);

					// Generate <calculationAgentPartyReference> at the end of trade
					// if no peer element.
					if (element.getLocalName ().equals ("trade")) {
						Element agent = (Element) cache.get ("calculationAgentPartyReference");

						if (agent != null) {
							Element container = document.createElement ("calculationAgent");

							clone.appendChild (container);
							transcribe (agent, document, container, cache, false);
							cache.remove ("calculationAgentPartyReference");
						}
					}
					break;
				}
		
			default:
				parent.appendChild (document.importNode (context, false));
				break;
			}
		}		
	};
	
	/**
	 * The <CODE>R4_0__R4_1</CODE> class contains the logic to migrate
	 * the content of a FpML 4.0 schema based document to 4.1 
	 * 
	 * @since	TFP 1.0
	 */
	public static class R4_0__R4_1 extends DirectConversion 
	{
		public R4_0__R4_1 ()
		{
			super (Releases.R4_0, Releases.R4_1);
		}
		
		/**
		 * {@inheritDoc}
		 * @since	TFP 1.0
		 */
		public Document convert (Document source, Helper helper)
		{
			Document	target = getTargetRelease ().newInstance ("FpML");
			Element			oldRoot = source.getDocumentElement ();
			Element			newRoot	= target.getDocumentElement ();
			
			// Transfer the attributes
			newRoot.setAttributeNS (Schema.INSTANCE_URL, "xsi:type", "DataDocument");
			
			// Transcribe each of the first level child elements
			for (Node node = oldRoot.getFirstChild (); node != null;) {
				transcribe (node, target, newRoot);
				node = node.getNextSibling ();
			}
	
			return (target);
		}

		/**
		 * Recursively copies the structure of the old FpML 4-0 document
		 * into a new FpML 4-1 document adjusting the elements and attributes
		 * as necessary.
		 * 
		 * @param 	context			The <CODE>node</CODE> to be copied.
		 * @param 	document		The new <CODE>Document</CODE> instance.
		 * @param 	parent			The new parent <CODE>Node</CODE>.
		 * @since	TFP 1.0
		 */
		private void transcribe (Node context, Document document, Node parent)
		{
			switch (context.getNodeType ()) {
			case Node.ELEMENT_NODE:
				{
					Element			element = (Element) context;
					Element 		clone = document.createElementNS (null, element.getLocalName ());

					// Ignore failureToDeliverApplication
					if (element.getLocalName ().equals ("failureToDeliverApplicable"))
						break;
				
					// Handle elements that get renamed.
					if (element.getLocalName ().equals ("equityOptionFeatures"))
						clone = document.createElement ("equityFeatures");
					else if (element.getLocalName ().equals ("automaticExerciseApplicable"))
						clone = document.createElement ("automaticExercise");
					else if (element.getLocalName ().equals ("equityBermudanExercise"))
						clone = document.createElement ("equityBermudaExercise");
					else if (element.getLocalName ().equals ("bermudanExerciseDates"))
						clone = document.createElement ("bermudaExerciseDates");
					else if (element.getLocalName ().equals ("fxSource") ||
							 element.getLocalName ().equals ("fxDetermination"))
						clone = document.createElement ("fxSpotRateSource");
					else if (element.getLocalName ().equals ("futuresPriceValuationApplicable"))
						clone = document.createElement ("futuresPriceValuation");
					else if (element.getLocalName ().equals ("equityValuationDate"))
						clone = document.createElement ("valuationDate");
					else if (element.getLocalName ().equals ("equityValuationDates"))
						clone = document.createElement ("valuationDates");
					else if (element.getLocalName ().equals ("fxTerms"))
						clone = document.createElement ("fxFeature");
					else
						clone = document.createElement (element.getLocalName ());

					parent.appendChild (clone);
					
					// Handle renaming for clearanceSystemIdScheme attribute
					if (element.getLocalName ().equals ("clearanceSystem")) {
						clone.setAttribute ("clearanceSystemScheme", element.getAttribute ("clearanceSystemIdScheme"));
						DOM.setInnerText(clone, DOM.getInnerText (element));
						break;
					}

					// Handle renaming for routingIdScheme attribute
					if (element.getLocalName ().equals ("routingId")) {
						clone.setAttribute ("routingIdCodeScheme", element.getAttribute ("routingIdScheme"));
						DOM.setInnerText(clone, DOM.getInnerText (element));
						break;
					}

					NamedNodeMap	attrs	= element.getAttributes ();
					for (int index = 0; index < attrs.getLength (); ++index) {
						Attr		attr 	= (Attr) attrs.item (index);
						
						clone.setAttribute (attr.getName (), attr.getValue ());
					}
					
					// Handle the restructuring of equityOption
					if (element.getLocalName ().equals ("equityOption")) {
						Element target;

						Element premium	= document.createElement ("equityPremium");
						Element payer	= document.createElement ("payerPartyReference");
						Element receiver	= document.createElement ("receiverPartyReference");

						if ((target = XPath.path (element, "buyerPartyReference")) != null) {
							transcribe (target, document, clone);
							payer.setAttribute ("href", target.getAttribute ("href"));
						}
						if ((target = XPath.path (element, "sellerPartyReference")) != null) {
							transcribe (target, document, clone);
							receiver.setAttribute ("href", target.getAttribute ("href"));
						}
						if ((target = XPath.path (element, "optionType")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "equityEffectiveDate")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "underlyer")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "notional")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "equityExercise")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "fxFeature")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "methodOfAdjustment")) != null)
							transcribe (target, document, clone);

						if ((target = XPath.path (element, "extraordinaryEvents")) != null)
							transcribe (target, document, clone);
						else {
							Element	child = document.createElement ("extraordinaryEvents");
							Element	failure = document.createElement ("failureToDeliver");
						
							if ((target = XPath.path (element, "equityExercise", "failureToDeliverApplicable")) != null)
								DOM.setInnerText (failure, DOM.getInnerText (target));
							else
								DOM.setInnerText (failure, "false");

							child.appendChild (failure);
							clone.appendChild (child);
						}
						
						if ((target = XPath.path (element, "equityOptionFeatures")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "strike")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "spot")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "numberOfOptions")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "optionEntitlement")) != null)
							transcribe (target, document, clone);

						premium.appendChild (payer);
						premium.appendChild (receiver);			
						clone.appendChild (premium);

						break;
					}

					// Handle restructuring of swaption
					if (element.getLocalName ().equals ("swaption")) {
						NodeList list;
						Element target;
						Element agent = document.createElement ("calculationAgent");

						if ((target = XPath.path (element, "buyerPartyReference")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "sellerPartyReference")) != null)
							transcribe (target, document, clone);

						list = element.getElementsByTagName ("premium");
						for (int index = 0; index < list.getLength (); ++index)
							transcribe (list.item (index), document, clone);

						if ((target = XPath.path (element, "americanExercise")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "bermudaExercise")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "europeanExercise")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "exerciseProcedure")) != null)
							transcribe (target, document, clone);
	
						clone.appendChild (agent);

						list = element.getElementsByTagName ("calculationAgentPartyReference");
						for (int index = 0; index < list.getLength (); ++index)
							transcribe (list.item (index), document, agent);

						if ((target = XPath.path (element, "cashSettlement")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "swaptionStraddle")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "swaptionAdjustedDates")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "swap")) != null)
							transcribe (target, document, clone);

						break;
					}

					// Handle restructuring of fxFeature
					if (element.getLocalName ().equals ("fxFeature")) {
						Element	child;
						Element	target;
						Element	rccy	= document.createElement ("referenceCurrency");

						DOM.setInnerText (rccy, "???");
						clone.appendChild (rccy);

						if (DOM.getInnerText (XPath.path (element, "fxFeatureType")).trim ().toUpperCase ().equals ("COMPOSITE")) {
							child = document.createElement ("composite");

							if ((target = XPath.path (element, "fxSource")) != null)
								transcribe (target, document, child);
						}
						else {
							child = document.createElement ("quanto");

							Comment	note	= document.createComment ("Note: Manual enrichment required here");

							Element	pair	= document.createElement ("quotedCurrencyPair");
							Element	ccy1	= document.createElement ("currency1");
							Element	ccy2	= document.createElement ("currency2");
							Element	basis	= document.createElement ("quoteBasis");
							Element	rate	= document.createElement ("fxRate");
							Element	value	= document.createElement ("rate");

							DOM.setInnerText (ccy1, "???");
							DOM.setInnerText (ccy2, "???");
							DOM.setInnerText (basis, "Currency1PerCurrency2");

							pair.appendChild (ccy1);
							pair.appendChild (ccy2);
							pair.appendChild (basis);

							if ((target = XPath.path (element, "fxRate")) != null)
								DOM.setInnerText (value, DOM.getInnerText (target));
							else
								DOM.setInnerText (value, "0.0000");

							rate.appendChild (pair);
							rate.appendChild (value);

							child.appendChild (note);
							child.appendChild (rate);

							if ((target = XPath.path (element, "fxSource")) != null)
								transcribe (target, document, child);
						}
						clone.appendChild (child);
				
						break;
					}

					// Handle restucturing of fxTerms
					if (element.getLocalName ().equals ("fxTerms")) {
						Element	kind;
						Element	child;

						if ((kind = XPath.path (element, "quanto")) != null) {
							transcribe (XPath.path (kind, "referenceCurrency"), document, clone);

							child = document.createElement ("quanto");

							NodeList	list = kind.getElementsByTagName ("fxRate");
							for (int index = 0; index < list.getLength(); ++index)
								transcribe (list.item (index), document, child);

							clone.appendChild (child);
						}
						if ((kind = XPath.path (element, "compositeFx")) != null) {
							Element	target;

							transcribe (XPath.path (kind, "referenceCurrency"), document, clone);

							child = document.createElement ("composite");

							if ((target = XPath.path (kind, "determinationMethod")) != null)
								transcribe (target, document, child);
							if ((target = XPath.path (kind, "relativeDate")) != null)
								transcribe (target, document, child);
							if ((target = XPath.path (kind, "fxDetermination")) != null)
								transcribe (target, document, child);

							clone.appendChild (child);
						}
						break;
					}

					// Handle new buyer/seller party references in equity swap
					if (element.getLocalName ().equals ("equitySwap")) {
						NodeList	list;
						Element		target;

						if ((target = XPath.path (element, "productType")) != null)
							transcribe (target, document, clone);
	
						list = element.getElementsByTagName ("productId");
						for (int index = 0; index < list.getLength (); ++index)
							transcribe (list.item (index), document, clone);

						Element	firstLeg = (Element) element.getElementsByTagName ("equityLeg").item (0);

						Element	buyer	= document.createElement ("buyerPartyReference");
						Element	seller	= document.createElement ("sellerPartyReference");

						buyer.setAttribute ("href", XPath.path (firstLeg, "payerPartyReference").getAttribute ("href"));
						seller.setAttribute ("href", XPath.path (firstLeg, "receiverPartyReference").getAttribute ("href"));

						clone.appendChild (buyer);
						clone.appendChild (seller);

						
						list = element.getElementsByTagName ("equityLeg");
						for (int index = 0; index < list.getLength (); ++index)
							transcribe (list.item (index), document, clone);
						list = element.getElementsByTagName ("interestLeg");
						for (int index = 0; index < list.getLength (); ++index)
							transcribe (list.item (index), document, clone);

						if ((target = XPath.path (element, "principalExchangeFeatures")) != null)
							transcribe (target, document, clone);

						list = element.getElementsByTagName ("additionalPayment");
						for (int index = 0; index < list.getLength (); ++index)
							transcribe (list.item (index), document, clone);
						list = element.getElementsByTagName ("earlyTermination");
						for (int index = 0; index < list.getLength (); ++index)
							transcribe (list.item (index), document, clone);

						break;
					}

					// Handle restructuring of initialPrice and valuationPriceFinal
					if (element.getLocalName ().equals ("initialPrice") ||
						element.getLocalName ().equals ("valuationPriceFinal")) {
						Element	target;

						if ((target = XPath.path (element, "commission")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "determinationMethod")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "amountRelativeTo")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "grossPrice")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "netPrice")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "accruedInterestPrice")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "fxConversion")) != null)
							transcribe (target, document, clone);

						Element	valuation = document.createElement ("equityValuation");

						if ((target = XPath.path (element, "equityValuationDate")) != null)
							transcribe (target, document, valuation);
						if ((target = XPath.path (element, "valuationTimeType")) != null)
							transcribe (target, document, valuation);
						if ((target = XPath.path (element, "valuationTime")) != null)
							transcribe (target, document, valuation);

						clone.appendChild (valuation);
						break;
					}

					// Handle restructuring of valuationPriceInterim
					if (element.getLocalName ().equals ("valuationPriceInterim")) {
						Element	target;

						if ((target = XPath.path (element, "commission")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "determinationMethod")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "amountRelativeTo")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "grossPrice")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "netPrice")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "accruedInterestPrice")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "fxConversion")) != null)
							transcribe (target, document, clone);

						Element	valuation = document.createElement ("equityValuation");

						if ((target = XPath.path (element, "equityValuationDates")) != null)
							transcribe (target, document, valuation);
						if ((target = XPath.path (element, "valuationTimeType")) != null)
							transcribe (target, document, valuation);
						if ((target = XPath.path (element, "valuationTime")) != null)
							transcribe (target, document, valuation);

						clone.appendChild (valuation);
						break;
					}
					// Handle new optionality in constituentWeight
					if (element.getLocalName ().equals ("constituentWeight")) {
						Element	target = XPath.path (element, "basketPercentage");

						if (target != null)
							transcribe (target, document, clone);
						else
							transcribe (XPath.path (element, "openUnits"), document, clone);

						break;
					}

					// Handle transfer of failure to deliver into extraordinary events
					if (element.getLocalName ().equals ("extraordinaryEvents")) {
						Element	target;

						if ((target = XPath.path (element, "mergerEvents")) != null)
							transcribe (target, document, clone);
						
						Element	failure = document.createElement ("failureToDeliver");
						
						if ((target = XPath.path (element, "..", "equityExercise", "failureToDeliverApplicable")) != null)
							DOM.setInnerText (failure, DOM.getInnerText (target));
						else
							DOM.setInnerText (failure, "false");

						clone.appendChild (failure);
						
						if ((target = XPath.path (element, "nationalisationOrInsolvency")) != null)
							transcribe (target, document, clone);
						if ((target = XPath.path (element, "delisting")) != null)
							transcribe (target, document, clone);

						break;
					}
					
					// Recursively copy the child node across 
					for (Node node = element.getFirstChild (); node != null;) {
						transcribe (node, document, clone);
						node = node.getNextSibling ();
					}
					break;
				}
				
			default:
				parent.appendChild (document.importNode (context, false));
			}
		}
	};

	/**
	 * The <CODE>R4_1__TR4_2</CODE> class contains the logic to migrate
	 * the content of a FpML 4.1 schema based document to 4.2 
	 * 
	 * @since	TFP 1.0
	 */
	public static class R4_1__TR4_2 extends DirectConversion 
	{
		public R4_1__TR4_2 ()
		{
			super (Releases.R4_1, Releases.TR4_2);
		}
		
		/**
		 * {@inheritDoc}
		 * @since	TFP 1.0
		 */
		public Document convert (Document source, Helper helper)
		{
			Document	target = getTargetRelease ().newInstance ("FpML");
			Element			oldRoot = source.getDocumentElement ();
			Element			newRoot	= target.getDocumentElement ();
			
			// Transfer the attributes
			newRoot.setAttributeNS (Schema.INSTANCE_URL, "xsi:type", "DataDocument");
			
			// Transcribe each of the first level child elements
			for (Node node = oldRoot.getFirstChild (); node != null;) {
				transcribe (node, target, newRoot);
				node = node.getNextSibling ();
			}
	
			return (target);
		}
		
		/**
		 * Recursively copies the structure of the old FpML 4-1 document
		 * into a new FpML 4-2 document adjusting the elements and attributes
		 * as necessary.
		 * 
		 * @param 	context			The <CODE>node</CODE> to be copied.
		 * @param 	document		The new <CODE>Document</CODE> instance.
		 * @param 	parent			The new parent <CODE>Node</CODE>.
		 * @since	TFP 1.0
		 */
		private void transcribe (Node context, Document document, Node parent)
		{
			switch (context.getNodeType ()) {
			case Node.ELEMENT_NODE:
				{
					Element		element = (Element) context;
					Element		clone;
					
					clone = document.createElementNS (null, element.getLocalName ());
					
					parent.appendChild (clone);
				
					NamedNodeMap	attrs = element.getAttributes ();
					for (int index = 0; index < attrs.getLength (); ++index) {
						Attr attr 	= (Attr) attrs.item (index);
						
						clone.setAttribute (attr.getName (), attr.getValue ());
					}
					
					// Recursively copy the child node across
					for (Node node = element.getFirstChild (); node != null;) {
						transcribe (node, document, clone);
						node = node.getNextSibling ();
					}
					break;
				}
				
			default:
				parent.appendChild (document.importNode (context, false));
			}
		}
	}
	
	/**
	 * Ensures no instances can be constructed.
	 * @since	TFP 1.0
	 */
	private Conversions ()
	{ }
}
