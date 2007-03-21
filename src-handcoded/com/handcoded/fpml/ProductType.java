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

package com.handcoded.fpml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.handcoded.classification.AbstractCategory;
import com.handcoded.classification.Category;
import com.handcoded.classification.RefinableCategory;
import com.handcoded.xml.DOM;
import com.handcoded.xml.XPath;

/**
 * The <CODE>ProductType</CODE> class contains a set of <CODE>Category</CODE>
 * instance configured to classify standard FpML product types.
 * <P>
 * In addition to specific product types a number of 'abstract' categories
 * are defined such as <CODE>(OPTION)</CODE> and <CODE>(INTEREST RATE DERIVATIVE)
 * </CODE> to allow high level partitioning.
 * 
 * @author 	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class ProductType
{
	/**
	 * A <CODE>Category</CODE> representing all product types.
	 * @since	TFP 1.0
	 */
	public static final Category	PRODUCT_TYPE
		= new AbstractCategory ("(PRODUCT_TYPE)");
	
	/**
	 * A <CODE>Category</CODE> representing all swaps.
	 * @since	TFP 1.0
	 */
	public static final Category	SWAP
		= new AbstractCategory ("(SWAP)", PRODUCT_TYPE);

	/**
	 * A <CODE>Category</CODE> representing all options.
	 * @since	TFP 1.0
	 */
	public static final Category	OPTION
		= new AbstractCategory ("(OPTION)", PRODUCT_TYPE);

	/***
	 * A <CODE>Category</CODE> representing all forwards.
	 * @since	TFP 1.0
	 */
	public static final  Category	FORWARD
		= new AbstractCategory ("(FORWARD)", PRODUCT_TYPE);
	
	/**
	 * A <CODE>Category</CODE> representing all foreign exchange products.
	 * @since  	TFP 1.0
	 */
	public static final Category FOREIGN_EXCHANGE
		= new AbstractCategory ("(FOREIGN EXCHANGE)", PRODUCT_TYPE);
	
	/**
	 * A <CODE>Category</CODE> representing all foreign exchange spot/forward deals.
	 * @since  	TFP 1.0
	 */
	public static final Category FX_SPOT_FORWARD
		= new RefinableCategory ("FX SPOT/FORWARD", FOREIGN_EXCHANGE)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "fxSingleLeg") != null);
			}
		};
		
	/**
	 * A <CODE>Category</CODE> representing all foreign exchange spot deals.
	 * @since  	TFP 1.0
	 */
	public static final Category FX_SPOT
		= new RefinableCategory ("FX_SPOT", FX_SPOT_FORWARD)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "fxSingleLeg", "exchangeRate", "forwardPoints") == null);
			}
		};
	
	/**
	 * A <CODE>Category</CODE> representing all foreign exchange forward deals.
	 * @since  	TFP 1.0
	 */
	public static final Category FX_FORWARD
		= new RefinableCategory ("FX_FORWARD", 
				new Category [] { FX_SPOT_FORWARD, FORWARD })
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "fxSingleLeg", "exchangeRate", "forwardPoints") != null);
			}
		};
	
	/**
	 * A <CODE>Category</CODE> representing all foreign exchange options.
	 * @since  	TFP 1.0
	 */
	public static final Category FX_OPTION
		= new RefinableCategory ("FX_OPTION", 
				new Category [] { FOREIGN_EXCHANGE, OPTION })
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return ((XPath.path ((Element) value, "fxSimpleOption") != null) ||
						(XPath.path ((Element) value, "fxBarrierOption") != null));
			}
		};
	
	/**
	 * A <CODE>Category</CODE> representing all foreign exchange barrier options.
	 * @since  	TFP 1.0
	 */
	public static final Category FX_BARRIER_OPTION
		= new RefinableCategory ("FX_BARRIER OPTION", FX_OPTION)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "fxBarrierOption") != null);
			}
		};
		
	/**
	 * A <CODE>Category</CODE> representing all foreign exchange digital options.
	 * @since  	TFP 1.1
	 */
	public static final Category FX_DIGITAL_OPTION
		= new RefinableCategory ("FX_DIGITAL OPTION", FX_OPTION)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "fxDigitalOption") != null);
			}
		};
			
	/**
	 * A <CODE>Category</CODE> representing all bullet payments.
	 * @since 	TFP 1.0
	 */
	public static final Category BULLET_PAYMENT
		= new RefinableCategory ("BULLET PAYMENT", PRODUCT_TYPE)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable(final Object value)
			{
				return (XPath.path ((Element) value, "bulletPayment") != null);
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all interest rate derivatives.
	 * @since	TFP 1.0
	 */
	public static final Category	INTEREST_RATE_DERIVATIVE
		= new AbstractCategory ("(INTEREST RATE DERIVATIVE)", PRODUCT_TYPE);

	/**
	 * A <CODE>Category</CODE> representing all interest rate swaps.
	 * @since 	TFP 1.0
	 */
	public static final Category INTEREST_RATE_SWAP
		= new RefinableCategory ("INTEREST RATE SWAP", new Category[] { INTEREST_RATE_DERIVATIVE, SWAP })
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable(final Object value)
			{
				Document	document = ((Element) value).getOwnerDocument ();
				
				if (Releases.FPML.getReleaseForDocument (document) == Releases.R1_0)
					return (XPath.path ((Element) value, "product", "swap") != null);
				else
					return (XPath.path ((Element) value, "swap") != null);
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all cross currency interest rate swaps.
	 * @since 	TFP 1.0
	 */
	public static final Category CROSS_CURRENCY_SWAP
		= new RefinableCategory ("CROSS CURRENCY SWAP", INTEREST_RATE_SWAP)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable(final Object value)
			{
				Document	document = ((Element) value).getOwnerDocument ();
				NodeList	currencies;
				boolean		different	= false;
				
				if (Releases.FPML.getReleaseForDocument (document) == Releases.R1_0)
					currencies = XPath.paths ((Element) value,
							"product", "swap", "swapStream", "calculationPeriodAmount", "calculation",
							"notionalSchedule", "notionalStepSchedule",	"currency");
				else
					currencies = XPath.paths ((Element) value,
							"swap", "swapStream", "calculationPeriodAmount", "calculation",
							"notionalSchedule", "notionalStepSchedule",	"currency");
				
				for (int index = 1; index < currencies.getLength (); ++index) {
					Element		ccy1	= (Element) currencies.item (index - 1);
					Element		ccy2	= (Element) currencies.item (index);
					
					if (!DOM.getInnerText (ccy1).equals (DOM.getInnerText (ccy2))) {
						different = true;
						break;
					}
				}
				return (different);
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all inflation swap.
	 * @since	TFP 1.0
	 */
	public static final Category	INFLATION_SWAP
		= new RefinableCategory ("INFLATION SWAP", SWAP)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				// TODO: Finish
				return (false);
			}		
		};
			
	/**
	 * A <CODE>Category</CODE> representing all term deposits.
	 * @since	TFP 1.0
	 */
	public static final Category	TERM_DEPOSIT
		= new RefinableCategory ("TERM DEPOSIT", PRODUCT_TYPE)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "termDeposit") != null);
			}		
		};
				
	/**
	 * A <CODE>Category</CODE> representing all interest rate caps and floors.
	 * @since 	TFP 1.0
	 */
	public static final Category INTEREST_RATE_CAP_FLOOR
		= new RefinableCategory ("INTEREST RATE CAP/FLOOR", INTEREST_RATE_DERIVATIVE)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable(final Object value)
			{
				return (XPath.path ((Element) value, "capFloor") != null);
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all interest rate caps.
	 * @since 	TFP 1.0
	 */
	public static final Category INTEREST_RATE_CAP
		= new RefinableCategory ("INTEREST RATE CAP", INTEREST_RATE_CAP_FLOOR)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable(final Object value)
			{
				Element floatingRateCalculation = XPath.path ((Element) value, "capFloor", "capFloorStream", "calculationPeriodAmount", "calculation", "floatingRateCalculation");
				Element capRateSchedule = XPath.path (floatingRateCalculation, "capRateSchedule");
				Element floorRateSchedule = XPath.path (floatingRateCalculation, "floorRateSchedule");
				return ((capRateSchedule != null) && (floorRateSchedule == null));
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all interest rate floors.
	 * @since 	TFP 1.0
	 */
	public static final Category INTEREST_RATE_FLOOR
		= new RefinableCategory ("INTEREST RATE FLOOR", INTEREST_RATE_CAP_FLOOR)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable(final Object value)
			{
				Element floatingRateCalculation = XPath.path ((Element) value, "capFloor", "capFloorStream", "calculationPeriodAmount", "calculation", "floatingRateCalculation");
				Element capRateSchedule = XPath.path (floatingRateCalculation, "capRateSchedule");
				Element floorRateSchedule = XPath.path (floatingRateCalculation, "floorRateSchedule");
				return ((capRateSchedule == null) && (floorRateSchedule != null));
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all interest rate collars.
	 * @since 	TFP 1.0
	 */
	public static final Category INTEREST_RATE_COLLAR
		= new RefinableCategory ("INTEREST RATE COLLAR", INTEREST_RATE_CAP_FLOOR)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable(final Object value)
			{
				Element floatingRateCalculation = XPath.path ((Element) value, "capFloor", "capFloorStream", "calculationPeriodAmount", "calculation", "floatingRateCalculation");
				Element capRateSchedule = XPath.path (floatingRateCalculation, "capRateSchedule");
				Element floorRateSchedule = XPath.path (floatingRateCalculation, "floorRateSchedule");
				return ((capRateSchedule != null) && (floorRateSchedule != null));
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all interest rate swaptions.
	 * @since 	TFP 1.0
	 */
	public static final Category INTEREST_RATE_SWAPTION
		= new RefinableCategory ("INTEREST RATE SWAPTION",
				new Category[] { INTEREST_RATE_DERIVATIVE, OPTION })
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable(final Object value)
			{
				return (XPath.path ((Element) value, "swaption") != null);
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all equity derivatives.
	 * @since	TFP 1.0
	 */
	public static final Category	EQUITY_DERIVATIVE
		= new AbstractCategory ("(EQUITY DERIVATIVE)", PRODUCT_TYPE);

	/**
	 * A <CODE>Category</CODE> representing all equity forwards.
	 * @since	TFP 1.0
	 */
	public static final Category	EQUITY_FORWARD
		= new RefinableCategory ("EQUITY FORWARD", 
				new Category [] { EQUITY_DERIVATIVE, FORWARD })
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "equityForward") != null);
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all equity options.
	 * @since	TFP 1.0
	 */
	public static final Category	EQUITY_OPTION
		= new RefinableCategory ("EQUITY OPTION", 
				new Category [] { EQUITY_DERIVATIVE, OPTION })
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "equityOption") != null);
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all equity options.
	 * @since	TFP 1.0
	 */
	public static final Category	EQUITY_OPTION_SHORT_FORM
		= new RefinableCategory ("EQUITY OPTION SHORT FORM", 
				new Category [] { EQUITY_DERIVATIVE, OPTION })
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "brokerEquityOption") != null);
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all equity options.
	 * @since	TFP 1.0
	 */
	public static final Category	EQUITY_OPTION_TRANSACTION_SUPPLEMENT
		= new RefinableCategory ("EQUITY OPTION OPTION TRANSACTION SUPPLEMENT", 
				new Category [] { EQUITY_DERIVATIVE, OPTION })
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "equityOptionTransactionSupplement") != null);
			}
		};

	/**
	 * A <CODE>Category</CODE> representing all credit derivatives.
	 * @since	TFP 1.0
	 */
	public static final Category	CREDIT_DERIVATIVE
		= new AbstractCategory ("(CREDIT DERIVATIVE)", PRODUCT_TYPE);
	
	/**
	 * A <CODE>Category</CODE> representing all total return swaps.
	 * @since	TFP 1.0
	 */
	public static final Category	TOTAL_RETURN_SWAP
		= new RefinableCategory ("TOTAL RETURN SWAP", SWAP)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (false);
			}		
		};
		
	/**
	 * A <CODE>Category</CODE> representing all credit default swaps.
	 * @since	TFP 1.0
	 */
	public static final Category	CREDIT_DEFAULT_SWAP
		= new RefinableCategory ("CREDIT DEFAULT SWAP",
				new Category [] { CREDIT_DERIVATIVE, SWAP })
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "creditDefaultSwap") != null);
			}		
		};
				
	/**
	 * A <CODE>Category</CODE> representing an asset swap.
	 * @since	TFP 1.0
	 */
	public static final Category	ASSET_SWAP
		= new AbstractCategory ("ASSET SWAP", SWAP);
	
	/**
	 * A <CODE>Category</CODE> representing all forward rate agreements.
	 * @since	TFP 1.0
	 */
	public static final Category	FORWARD_RATE_AGREEMENT
		= new RefinableCategory ("FORWARD RATE AGREEMENT", INTEREST_RATE_DERIVATIVE)
		{
			/**
			 * {@inheritDoc}
			 */
			protected boolean isApplicable (final Object value)
			{
				return (XPath.path ((Element) value, "fra") != null);
			}		
		};

	/**
	 * Attempts to determine the type of product used within a trade.
	 *
	 * @param	trade			A trade <CODE>Element</CODE>.
	 * @return	A <CODE>Category</CODE> instance based on the trades
	 * 			contained product type, or <CODE>null</CODE>.
	 * @since	TFP 1.0
	 */
	public static Category classify (Element trade)
	{
		return (PRODUCT_TYPE.classify (trade));
	}

	/**
	 * Ensures that no instance can be constructed.
	 * @since	TFP 1.0
	 */
	private ProductType ()
	{ }
}