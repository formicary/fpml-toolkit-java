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

package com.handcoded.xml.resolver;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Logger;

import javax.xml.transform.stream.StreamSource;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * The <CODE>Catalog</CODE> provides a configurable <CODE>EntityResolver
 * </CODE> for a SAX or DOM based XML parser.
 * <P>
 * Using the various add methods a set of rules can be established to direct
 * the XML parser to the appropriate DTD or schema file overriding any filename
 * contained within the document itself.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public final class Catalog implements EntityResolver
{
	/**
	 * Constructs a new <CODE>Catalog</CODE> which does not contain
	 * resolution rules.
	 *
	 * @param	url				The URL of this <CODE>Catalog</CODE>
	 * @since	TFP 1.0
	 */
	public Catalog (final String url)
	{
//		logger.info ("url = '" +url + "'");
		
		this.url  = url;
	}
	
	/**
	 * Returns the URL associated with this catalog.
	 *
	 * @return 	The URL of the catalog.
	 * @since	TFP 1.0
	 */
	public String getUrl ()
	{
		return (url);
	}
	
	/**
	 * Adds a rule to the catalog directing the given public identifier to
	 * the indicated URI.
	 *
	 * @param	publicId		The public identifier to be mapped.
	 * @param	uri				The corresponding URI.
	 * @param	base			Optional <CODE>xml:base</CODE> value.
	 * @since	TFP 1.0
	 */
	public final void addPublic (final String publicId, final String uri, final String base)
	{
		rules.add (new PublicEntry (publicId, uri, base));
	}
	
	/**
	 * Adds a rule to the catalog directing the given public identifier to
	 * the indicated URI.
	 *
	 * @param	publicId		The public identifier to be mapped.
	 * @param	uri				The corresponding URI.
	 * @since	TFP 1.0
	 */
	public final void addPublic (final String publicId, final String uri)
	{
		addPublic (publicId, uri, null);
	}

	/**
	 * Adds a rule to the catalog directing the given system identifier to
	 * the indicated URI.
	 *
	 * @param	systemId		The system identifier to be mapped.
	 * @param	uri				The corresponding URI.
	 * @param	base			Optional xml:base value.
	 * @since	TFP 1.0
	 */
	public final void addSystem (final String systemId, final String uri, final String base)
	{
		rules.add (new SystemEntry (systemId, uri, base));
	}

	/**
	 * Adds a rule to the catalog directing the given system identifier to
	 * the indicated URI.
	 *
	 * @param	systemId		The system identifier to be mapped.
	 * @param	uri				The corresponding URI.
	 * @since	TFP 1.0
	 */
	public final void addSystem (final String systemId, final String uri)
	{
		addSystem (systemId, uri, null);
	}

	/**
	 * Adds a rule to the catalog which will cause a matching system identifier
	 * to its starting prefix replaced.
	 *
	 * @param	startString		The system identifier prefix to match.
	 * @param	rewritePrefix	The new prefix to replace with.
	 * @since	TFP 1.0
	 */
	public final void addRewriteSystem (final String startString, final String rewritePrefix)
	{
		rules.add (new RewriteSystem (startString, rewritePrefix));
	}

	/**
	 * Adds a rule to the catalog which will direct the resolution for any
	 * system identifier that starts with the given prefix to a sub-catalog
	 * file.
	 *
	 * @param	startString		The system identifier prefix to match
	 * @param	catalog			The URI of the sub-catalog.
	 * @param	base			Optional xml:base value.
	 * @since	TFP 1.0
	 */
	public final void addDelegateSystem (final String startString, final String catalog, final String base)
	{
		rules.add (new DelegateSystem (startString, catalog, base));
	}

	/**
	 * Adds a rule to the catalog which will direct the resolution for any
	 * system identifier that starts with the given prefix to a sub-catalog
	 * file.
	 *
	 * @param	startString		The system identifier prefix to match
	 * @param	catalog			The URI of the sub-catalog.
	 * @since	TFP 1.0
	 */
	public final void addDelegateSystem (final String startString, final String catalog)
	{
		addDelegateSystem (startString, catalog, null);
	}

	/**
	 * Adds a rule to the catalog which will direct the resolution for any
	 * public identifier that starts with the given prefix to a sub-catalog
	 * file.
	 *
	 * @param	startString		The public identifier prefix to match
	 * @param	catalog			The URI of the sub-catalog.
	 * @param	base			The optional xml:base URI
	 * @since	TFP 1.0
	 */
	public final void addDelegatePublic (final String startString, final String catalog, final String base)
	{
		rules.add (new DelegatePublic (startString, catalog, base));
	}

	/**
	 * Adds a rule to the catalog which will direct the resolution for any
	 * public identifier that starts with the given prefix to a sub-catalog
	 * file.
	 *
	 * @param	startString		The public identifier prefix to match
	 * @param	catalog			The URI of the sub-catalog.
	 * @since	TFP 1.0
	 */
	public final void addDelegatePublic (final String startString, final String catalog)
	{
		addDelegatePublic (startString, catalog, null);
	}
	
	public final void addUri (final String name, final String uri, final String base)
	{
		rules.add (new UriEntry (name, uri, base));
	}
	
	public final void addUri (final String name, final String uri)
	{
		addUri (name, uri, null);
	}
	
	public final void addRewriteUri (final String startString, final String rewritePrefix)
	{
		rules.add (new RewriteUri (startString, rewritePrefix));
	}
	
	public final void addDelegateUri (final String startString, final String catalog, final String base)
	{
		rules.add (new DelegateUri (startString, catalog, base));
	}
	
	public final void addDelegateUri (final String startString, final String catalog)
	{
		addDelegateUri (startString, catalog, null);
	}
	
	/**
	 * Adds a rule which will cause resolution to chain to another catalog
	 * if no match can be found in this one.
	 * <P>
	 * This method is only available to the <CODE>XmlCatalogManager</CODE>
	 * class.
	 *
	 * @param	catalog			The URI of the catalog to chain to.
	 * @param	base			The optional xml:base URI
	 * @since	TFP 1.0
	 */
	final void addNextCatalog (final String catalog, final String base)
	{
		rules.add (new NextCatalog (catalog, base));
	}

	/**
	 * Adds a rule which will cause resolution to chain to another catalog
	 * if no match can be found in this one.
	 * <P>
	 * This method is only available to the <CODE>CatalogManager</CODE>
	 * class.
	 *
	 * @param	catalog			The URI of the catalog to chain to.
	 * @since	TFP 1.0
	 */
	final void addNextCatalog (final String catalog)
	{
		addNextCatalog (catalog, null);
	}
	
	/**
	 * Looks up a publicId and/or systemId in the dictionary to see if a local
	 * resource is available for it.
	 *
	 * @param	publicId		The public identifier of the external entity
	 *							being referenced, or null if none was supplied.
	 * @param	systemId		The system identifier of the external entity
	 *							being referenced.
	 * @return  An InputSource object describing the new input source, or
	 *			<CODE>null</CODE> to request that the parser open a regular
	 *			URI connection to the system identifier.
	 * @throws	SAXException If a error occurred creating the new <CODE>
	 *			InputSource</CODE>.
	 * @since	TFP 1.0
	 */
	public final InputSource resolveEntity (final String publicId, final String	systemId)
		throws SAXException
	{
		logger.info ("\npublicId = " + ((publicId != null) ? publicId : "null") +
					 "\nsystemId = " + ((systemId != null) ? systemId : "null"));

		String				result = applyRules (publicId, systemId, new Stack ());
		
		logger.info ("\nresult   = " + ((result   != null) ? result   : "null"));

		if (result != null) {
			if (result.startsWith ("file:")) {
				logger.info ("Opening " + result);
				return (new InputSource (result));
			}
			else {
				logger.info ("Opening " + Catalog.class.getClassLoader().getResource (result).toExternalForm());
				return (new InputSource (Catalog.class.getClassLoader().getResource (result).toExternalForm()));
			}
		}
		
		return (null);
	}

	/**
	 * Attempts to resolve a URI into a <CODE>SourceStream</CODE>
	 * using the rules within the <CODE>Catalog</CODE>.
	 * 
	 * @param 	uri			The URI to be resolved.
	 * @return 	A <CODE>StreamSource</CODE> connected to the resolved resource
	 * 			or <CODE>null</CODE> if the URI was not resolved.
	 * @since	TFP 1.0
	 */
	public final StreamSource resolveUri (final String uri)
		throws SAXException
	{
//		logger.info ("\nuri    = " + ((uri != null) ? uri : "null"));

		String				result = applyRules (uri, new Stack ());

//		logger.info ("\nresult = " + ((result != null) ? result   : "null"));

		if (result != null) {
			if (result.startsWith ("file:"))
				return (new StreamSource (result));
			else {
				System.out.println ("?? " + Catalog.class.getClassLoader().getResource (result).toExternalForm());
				return (new StreamSource (Catalog.class.getClassLoader().getResource (result).toExternalForm()));
			}
		}
		return (null);	
	}
	
	/**
	 * Converts the instance data members to a <CODE>String</CODE> representation
	 * that can be displayed for debugging purposes.
	 *
	 * @return 	The object's <CODE>String</CODE> representation.
	 * @since	TFP 1.0
	 */ 
	public String toString ()
	{
		return (getClass ().getName () + " [" + toDebug () + "]");
	}	

	/**
	 * Converts the instance's member values to <CODE>String</CODE> representations
	 * and concatenates them all together. This function is used by toString and
	 * may be overriden in derived classes.
	 *
	 * @return	The object's <CODE>String</CODE> representation.
	 * @since	TFP 1.0
	 */
	protected String toDebug ()
	{
		StringBuffer		buffer 	= new StringBuffer ();
		
		buffer.append ("rules={");
		for (int index = 0; index < rules.size (); ++index) {
			if (index != 0) buffer.append (',');
			
			buffer.append (rules.elementAt (index).toString ());
		}
		buffer.append ('}');
		
		return (buffer.toString ());
	}
	
	/**
	 * If the supplied name is a public name then it is 'unwrapped' according
	 * to the rules defined in RFC 3151.
	 * <P>
	 * Public names for DTDs (such as those used by DocBook and FpML 1-0 thru
	 * 3-0) are not valid URIs and must undergo a number of character
	 * replacements. This rountine detects a public name by looking for '//'
	 * anywhere in the string.
	 * 
	 * @param 	name			The public name to be wrapped.
	 * @return	A valid URI string, either the original input unmodified or
	 * 			a new URI constructed by the unwrapping process.
	 * @since	TFP 1.0
	 */
	protected String unwrap (String name)
	{
		if (name.indexOf ("//") != -1) {
			StringBuffer buffer = new StringBuffer ();
			
			int		length = name.length ();
			char	ch;
			
			buffer.append ("urn:publicid:");
			for (int index = 0; index < length; ++index) {
				switch (ch = name.charAt (index)) {
				case ' ':
				case '\t':
				case '\r':
				case '\n':
					{
						int			buflen = buffer.length ();
						
						if ((buflen == 0) || (buffer.charAt (buflen - 1) != '+'))
							buffer.append ('+');
						break;
					}
					
				case '/':
					{
						if (((index + 1) < length) && (name.charAt (index + 1) == '/')) {
							buffer.append (':');
							++index;
						}
						else
							buffer.append ("%2F");
						break;
					}
				
				case ':':
				{
					if (((index + 1) < length) && (name.charAt (index + 1) == ':')) {
						buffer.append (';');
						++index;
					}
					else
						buffer.append ("%3A");
					break;
				}
				
				case '+':
					buffer.append ("%2B");
					break;
					
				case ';':
					buffer.append ("%3B");
					break;
					
				case '\'':
					buffer.append ("%27");
					break;
				
				case '?':
					buffer.append ("%3F");
					break;
					
				case '#':
					buffer.append ("%23");
					break;
					
				case '%':
					buffer.append ("%25");
					break;
					
				default:
					buffer.append (ch);
				}
			}
			
			return (buffer.toString ());
		}
			
		return (name);
	}
	
	/**
	 * Logging instance used to record runtime problems.
	 * @since	TFP 1.0
	 */
	private static Logger		logger
		= Logger.getLogger ("com.handcoded.xml.resolver.Catalog");
	
	private interface EntityRule
	{
		/**
		 * Applys the rule instance to the public or system identifier in an
		 * attempt to locate the URI of a resource with can provide the required
		 * information.
		 *
		 * @param	publicId		The public identifier of the external entity
		 *							being referenced, or null if none was supplied.
		 * @param	systemId		The system identifier of the external entity
		 *							being referenced.
		 * @param	catalogs		The stack of catalogs being processed
		 * @return	A new URI if the rule was successfully applied, otherwise
		 *			<CODE>null</CODE>.
		 * @throws	SAXException If an occur was detected during processing.
		 * @since	TFP 1.0
		 */
		public abstract String applyTo (final String publicId, final String systemId, Stack	catalogs)
			throws SAXException;	
	};
	
	private interface UriRule
	{
		/**
		 * Applys the rule instance to the public or system identifier in an
		 * attempt to locate the URI of a resource with can provide the required
		 * information.
		 *
		 * @param	uri				The uri needing to be resolved.
		 * @param	catalogs		The stack of catalogs being processed
		 * @return	A new URI if the rule was successfully applied, otherwise
		 *			<CODE>null</CODE>.
		 * @throws	SAXException If an occur was detected during processing.
		 * @since	TFP 1.0
		 */
		public abstract String applyTo (final String uri, Stack	catalogs)
			throws SAXException;	
	};
	
	/**
	 * The abstract <CODE>CatalogueRule</CODE> class defines the standard API
	 * provided by all rule components.
	 * 
	 * @since	TFP 1.0
	 */
	private abstract class CatalogRule
	{
		/**
		 * Converts the instance data members to a <CODE>String</CODE> representation
		 * that can be displayed for debugging purposes.
		 *
		 * @return 	The object's <CODE>String</CODE> representation.
		 * @since	TFP 1.0
		 */ 
		public String toString ()
		{
			return (getClass ().getName () + "[" + toDebug () + "]");
		}
	
		/**
		 * Constructs a <CODE>CatalogRule</CODE> component.
		 * 
		 * @since	TFP 1.0
		 */
		protected CatalogRule ()
		{ }

		/**
		 * Converts the instance's member values to <CODE>String</CODE> representations
		 * and concatenates them all together. This function is used by toString and
		 * may be overriden in derived classes.
		 *
		 * @return	The object's <CODE>String</CODE> representation.
		 * @since	TFP 1.0
		 */
		protected abstract String toDebug ();
	}
	
	/**
	 * @since	TFP 1.0
	 */
	private abstract class RelativeCatalogRule extends CatalogRule
	{
		/**
		 * Constructs a <CODE>RelativeCatalogRule</CODE> component.
		 */
		protected RelativeCatalogRule (
		final String		base)
		{
			this.base = base;
		}

		/**
		 * Constructs the base URI used to resolve any local URI values.
		 *
		 * @return	The base <CODE>URI</CODE> for resolution.
		 * @throws 	URISyntaxException if the xml:base or the catalog URL is
		 *			invalid.
		 */
		protected final URI	getBase ()
			throws URISyntaxException
		{
			URI				uri;
			
			if (base != null) {
				if (base.indexOf (':') < 0)
					uri = new File (base).toURI ();
				else
					uri = new URI (base);
			}
			else {
				if (url.indexOf (':') < 0)
					uri = new File (url).toURI ();
				else
					uri = new URI (url);
			}
					
			return (uri);
		}
		
		/**
		 * {@inheritDoc}
		 */
		protected String toDebug ()
		{
			return ("base=" + ((base != null) ? base : "null"));
		}
	
		/**
		 * An overriding base URI if supplied.
		 */
		private String		base;
	}

	/**
	 * The <CODE>PublicEntry</CODE> class implements simple public identifier
	 * matching.
	 * 
	 * @since	TFP 1.0
	 */
	private final class PublicEntry extends RelativeCatalogRule implements EntityRule
	{
		/**
		 * Constructs a <CODE>PublicEntry</CODE> instance that will replace
		 * a public identifier with a URI.
		 *
		 * @param	publicId		The public identifier to be matched
		 * @param	uri				The replacement URI.
		 * @param	base			The optional xml:base URI
		 */
		public PublicEntry (
		final String		publicId,
		final String		uri,
		final String		base)
		{
			super (base);
			
			this.publicId = publicId;
			this.uri      = uri;
		}

		/**
		 * {@inheritDoc}
		 */
		public String applyTo (
		final String		publicId,
		final String		systemId,
		Stack				catalogs)
			throws SAXException
		{
			URI					targetUri;
			URI					publicUri;
			
			// Convert the target PublicId value to a URI
			try {
				if (publicId.startsWith ("file:"))
					targetUri = new File (publicId.substring (5)).toURI ();
				else
					targetUri = new URI (unwrap (publicId));
			}
			catch (URISyntaxException error) {
				throw new SAXException ("Failed to normalise targetId", error);
			}
		
			// Convert the catalog PublicId value to a URI
			try {
				publicUri = getBase ().resolve (new URI (unwrap (this.publicId)));
			}
			catch (URISyntaxException error) {
				throw new SAXException ("Failed to normalise publicId", error);
			}
			
			// If they match then replace with the catalog URI
			if (publicUri.equals (targetUri)) {
				try {
					
//	logger.info ("%% base = " + getBase ());
//	logger.info ("%% uri  = " + uri);
//	logger.info ("%% sum  = " + getBase ().resolve (new URI (uri)));
	
					return (getBase ().resolve (new URI (uri)).toString ());
				}
				catch (URISyntaxException error) {
					throw new SAXException ("Failed to resolve target URI", error);
				}
			}

			return (null);
		}
		
		/**
		 * {@inheritDoc}
		 */
		protected String toDebug ()
		{
			return ("publicId=\"" + publicId + "\",uri=\"" + uri + "\"," + super.toDebug ());
		}

		/**
		 * The publicId to match against.
		 */
		private final String	publicId;

		/**
		 * The URI to replace with.
		 */
		private final String	uri;
	}

	/**
	 * The <CODE>SystemEntry</CODE> class implements simple system identifier
	 * matching.
	 * @since	TFP 1.0
	 */
	private final class SystemEntry extends RelativeCatalogRule implements EntityRule
	{
		/**
		 * Constructs a <CODE>SystemEntry</CODE> instance that will replace
		 * a system identifier with a URI.
		 *
		 * @param	systemId		The system identifier to be matched
		 * @param	uri				The replacement URI.
		 * @param	base			The optional xml:base URI
		 */
		public SystemEntry (
		final String		systemId,
		final String		uri,
		final String		base)
		{
			super (base);
			
			this.systemId = systemId;
			this.uri      = uri;
		}

		/**
		 * {@inheritDoc}
		 */
		public String applyTo (
		final String		publicId,
		final String		systemId,
		Stack				catalogs)
			throws SAXException
		{
			URI					targetUri;
			URI					systemUri;
			
			// Convert the target SystemId value to a URI
			try {
				if (systemId.startsWith ("file:"))
					targetUri = new File (systemId.substring (5)).toURI ();
				else
					targetUri = new URI (systemId);
			}
			catch (URISyntaxException error) {
				throw new SAXException ("Failed to normalise targetId", error);
			}
		
			// Convert the catalog SystemId value to a URI
			try {
				systemUri = getBase ().resolve (new URI (this.systemId));
			}
			catch (URISyntaxException error) {
				throw new SAXException ("Failed to normalise systemId", error);
			}
			
			// If they match then replace with the catalog URI
			if (systemUri.equals (targetUri)) {
				try {
					return (getBase ().resolve (new URI (uri)).toString ());
				}
				catch (URISyntaxException error) {
					throw new SAXException ("Failed to resolve target URI", error);
				}
			}

			return (null);
		}

		/**
		 * {@inheritDoc}
		 */
		protected String toDebug ()
		{
			return ("systemId=\"" + systemId + "\",uri=\"" + uri + "\"," + super.toDebug ());
		}

		/**
		 * The systemId to match against.
		 */
		private final String	systemId;

		/**
		 * The URI to replace with.
		 */
		private final String	uri;
	}

	/**
	 * The <CODE>RewriteSystem</CODE> class implements system identifier
	 * rewriting.
	 * @since	TFP 1.0
	 */
	private final class RewriteSystem extends CatalogRule implements EntityRule
	{
		/**
		 * Constructs a <CODE>RewriteSystem</CODE> instance that will replace
		 * the prefix of system identifier with another.
		 *
		 * @param	oldPrefix		The system identifier to be matched.
		 * @param	newPrefix		The replacement prefix.
		 */
		public RewriteSystem (
		final String		oldPrefix,
		final String		newPrefix)
		{
			this.oldPrefix = oldPrefix;
			this.newPrefix = newPrefix;;
		}

		/**
		 * {@inheritDoc}
		 */
		public String applyTo (
		final String		publicId,
		final String		systemId,
		Stack				catalogs)
			throws SAXException
		{
			if (systemId.startsWith (oldPrefix))
				return (newPrefix + systemId.substring (oldPrefix.length ()));

			return (null);
		}

		/**
		 * {@inheritDoc}
		 */
		protected String toDebug ()
		{
			return ("oldPrefix=\"" + oldPrefix + "\",newPrefix=\"" + newPrefix + "\"");
		}

		/**
		 * The URI prefix to match against.
		 */
		private final String	oldPrefix;

		/**
		 * The URI prefix to replace with.
		 */
		private final String	newPrefix;
	}

	/**
	 * The <CODE>DelegateSystem</CODE> class implements system identifier
	 * delegation.
	 * 
	 * @since	TFP 1.0
	 */
	private final class DelegateSystem extends RelativeCatalogRule implements EntityRule
	{
		/**
		 * Constructs a <CODE>DelegateSystem</CODE> instance that will direct
		 * matching system identifier searches to a sub-catalog.
		 *
		 * @param	prefix			The system identifier to be matched.
		 * @param	catalog			The URI of the sub-catalog.
		 * @param	base			The optional xml:base URI
		 */
		public DelegateSystem (
		final String		prefix,
		final String		catalog,
		final String		base)
		{
			super (base);
			
			this.prefix  = prefix;
			this.catalog = catalog;
		}

		/**
		 * {@inheritDoc}
		 */
		public String applyTo (
		final String		publicId,
		final String		systemId,
		Stack				catalogs)
			throws SAXException
		{
			if (systemId.startsWith (prefix))
				return (process (publicId, systemId, catalogs, catalog));

			return (null);
		}

		/**
		 * {@inheritDoc}
		 */
		protected String toDebug ()
		{
			return ("prefix=\"" + prefix + "\",catalog=\"" + catalog + "\"," + super.toDebug ());
		}

		/**
		 * The prefix to match against.
		 */
		final String			prefix;

		/**
		 * The URI of the sub-catalog.
		 */
		final String			catalog;
	}

	/**
	 * The <CODE>DelegatePublic</CODE> class implements public identifier
	 * delegation.
	 * @since	TFP 1.0
	 */
	private final class DelegatePublic extends RelativeCatalogRule implements EntityRule
	{
		/**
		 * Constructs a <CODE>DelegatePublic</CODE> instance that will direct
		 * matching public identifier searches to a sub-catalog.
		 *
		 * @param	prefix			The system identifier to be matched.
		 * @param	catalog			The URI of the sub-catalog.
		 * @param	base			The optional xml:base URI
		 */
		public DelegatePublic (
		final String		prefix,
		final String		catalog,
		final String		base)
		{
			super (base);
			
			this.prefix  = prefix;
			this.catalog = catalog;
		}

		/**
		 * {@inheritDoc}
		 */
		public String applyTo (
		final String		publicId,
		final String		systemId,
		Stack				catalogs)
			throws SAXException
		{
			if (publicId.startsWith (prefix))
				return (process (publicId, systemId, catalogs, catalog));

			return (null);
		}

		/**
		 * {@inheritDoc}
		 */
		protected String toDebug ()
		{
			return ("prefix=\"" + prefix + "\",catalog=\"" + catalog + "\"," + super.toDebug ());
		}

		/**
		 * The prefix to match against.
		 */
		final String			prefix;

		/**
		 * The URI of the sub-catalog.
		 */
		final String			catalog;
	}
	
	private final class UriEntry extends RelativeCatalogRule implements UriRule
	{
		public UriEntry (final String name, final String uri, final String base)
		{
			super (base);
			
			this.name = name;
			this.uri  = uri;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public String applyTo (
		final String		uri,
		Stack				catalogs)
			throws SAXException
		{
			URI					targetUri;
			URI					systemUri;
			
			// Convert the target uri value to a URI
			try {
				if (uri.startsWith ("file:"))
					targetUri = new File (uri.substring (5)).toURI ();
				else
					targetUri = new URI (uri);
			}
			catch (URISyntaxException error) {
				throw new SAXException ("Failed to normalise target URI", error);
			}
		
			// Convert the catalog name value to a URI
			try {
				systemUri = getBase ().resolve (new URI (name));
			}
			catch (URISyntaxException error) {
				throw new SAXException ("Failed to normalise name", error);
			}
			
			// If they match then replace with the catalog URI
			if (systemUri.equals (targetUri)) {
				try {
					return (getBase ().resolve (new URI (this.uri)).toString ());
				}
				catch (URISyntaxException error) {
					throw new SAXException ("Failed to resolve new URI", error);
				}
			}

			return (null);
		}

		final String			name;
		
		final String			uri;
	}
	
	private final class RewriteUri extends CatalogRule implements UriRule
	{
		public RewriteUri (final String startString, final String rewritePrefix)
		{
			this.startString   = startString;
			this.rewritePrefix = rewritePrefix;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public String applyTo (
		final String		uri,
		Stack				catalogs)
			throws SAXException
		{
			if (uri.startsWith (startString))
				return (rewritePrefix + uri.substring (startString.length ()));

			return (null);
		}

		/**
		 * {@inheritDoc}
		 */
		protected String toDebug ()
		{
			return ("startString=\"" + startString + "\",rewritePrefix=\"" + rewritePrefix + "\"");
		}
		
		final String			startString;
		
		final String			rewritePrefix;
	}

	private final class DelegateUri extends RelativeCatalogRule implements UriRule
	{
		public DelegateUri (final String startString, final String catalog, final String base)
		{
			super (base);
			
			this.startString  = startString;
			this.catalog = catalog;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public String applyTo (
		final String		uri,
		Stack				catalogs)
			throws SAXException
		{
			if (uri.startsWith (startString))
				return (process (uri, catalogs, catalog));

			return (null);
		}

		/**
		 * {@inheritDoc}
		 */
		protected String toDebug ()
		{
			return ("startString=\"" + startString + "\",catalog=\"" + catalog + "\"," + super.toDebug ());
		}

		final String		startString;
		
		final String		catalog;
	}

	/**
	 * The <CODE>NextCatalog</CODE> class implements catalog chaining.
	 * @since	TFP 1.0
	 */
	private final class NextCatalog extends RelativeCatalogRule implements EntityRule, UriRule
	{
		/**
		 * Constructs a <CODE>DelegatePublic</CODE> instance that will direct
		 * searches to another catalog.
		 *
		 * @param	catalog			The URI of the chained catalog.
		 * @param	base			The optional xml:base URI
		 */
		public NextCatalog (
		final String		catalog,
		final String		base)
		{
			super (base);
			
			this.catalog = catalog;
		}

		/**
		 * {@inheritDoc}
		 */
		public String applyTo (
		final String		publicId,
		final String		systemId,
		Stack				catalogs)
			throws SAXException
		{
			return (process (publicId, systemId, catalogs, catalog));
		}

		/**
		 * {@inheritDoc}
		 */
		public String applyTo (
		final String		uri,
		Stack				catalogs)
			throws SAXException
		{
			return (process (uri, catalogs, catalog));
		}

		/**
		 * {@inheritDoc}
		 */
		protected String toDebug ()
		{
			return ("catalog=\"" + catalog + "\"," + super.toDebug ());
		}

		/**
		 * The URI of the catalog to chain to.
		 */
		final String			catalog;
	}

	/**
	 * The URL of this catalog.
	 * @since	TFP 1.0
	 */
	private String			url;
	
	/**
	 * The set of all rules in order of definition.
	 * @since	TFP 1.0
	 */
	private Vector			rules		= new Vector ();

	/**
	 * Implements the OASIS search rules for entity resources.
	 *
	 * @param	publicId		The public identifier of the external entity
	 *							being referenced, or null if none was supplied.
	 * @param	systemId		The system identifier of the external entity
	 *							being referenced.
	 * @param	catalogs		A stack of catalogs being processed used to
	 *							detect circular dependency.
	 * @return  The URI of the resolved entity or <CODE>null</CODE>.
	 * @throws	SAXException If an error occurs during processing.
	 * @since	TFP 1.0
	 */
	private String applyRules (final String publicId, final String systemId, Stack catalogs)
		throws SAXException
	{
		String				result = null;

		if (catalogs.contains (this))
			throw new SAXException ("Circular dependency in the XML Catalogs");

		catalogs.push (this);

		// If a system identifier is provided then try to match it explicitly
		// or through a rewriting rule or delegation.
		if ((systemId != null) && (systemId.length () > 0)) {
			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof SystemEntry) {
					if ((result = ((EntityRule) rules.elementAt (index))
							.applyTo (publicId, systemId, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}

			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof RewriteSystem) {
					if ((result = ((EntityRule) rules.elementAt (index))
							.applyTo (publicId, systemId, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}

			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof DelegateSystem) {
					if ((result = ((EntityRule) rules.elementAt (index))
							.applyTo (publicId, systemId, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}
		}

		// If a public identifier is provided then try to match it explicity
		// or through delegation.
		if ((publicId != null) && (publicId.length () > 0)) {
			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof PublicEntry) {
					if ((result = ((EntityRule) rules.elementAt (index))
							.applyTo (publicId, systemId, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}

			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof DelegatePublic) {
					if ((result = ((EntityRule) rules.elementAt (index))
							.applyTo (publicId, systemId, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}
		}

		// Finally try any other chained catalogs
		for (int index = 0; index < rules.size (); ++index) {
			if (rules.elementAt (index) instanceof NextCatalog) {
				if ((result = ((EntityRule) rules.elementAt (index))
						.applyTo (publicId, systemId, catalogs)) != null) {

					catalogs.pop ();
					return (result);
				}
			}
		}

		catalogs.pop ();
		return (null);
	}

	private String applyRules (final String uri, Stack catalogs)
		throws SAXException
	{
		String				result = null;

		if (catalogs.contains (this))
			throw new SAXException ("Circular dependency in the XML Catalogs");

		catalogs.push (this);

		if ((uri != null) && (uri.length () > 0)) {
			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof UriEntry) {
					if ((result = ((UriRule) rules.elementAt (index))
							.applyTo (uri, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}

			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof RewriteUri) {
					if ((result = ((UriRule) rules.elementAt (index))
							.applyTo (uri, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}

			for (int index = 0; index < rules.size (); ++index) {
				if (rules.elementAt (index) instanceof DelegateUri) {
					if ((result = ((UriRule) rules.elementAt (index))
							.applyTo (uri, catalogs)) != null) {

						catalogs.pop ();
						return (result);
					}
				}
			}
		}

		// Finally try any other chained catalogs
		for (int index = 0; index < rules.size (); ++index) {
			if (rules.elementAt (index) instanceof NextCatalog) {
				if ((result = ((UriRule) rules.elementAt (index))
						.applyTo (uri, catalogs)) != null) {

					catalogs.pop ();
					return (result);
				}
			}
		}

		catalogs.pop ();
		return (null);
	}

	/**
	 * Causes the resolution process to recurse into another <CODE>XmlCatalog
	 * </CODE> to find a match.
	 *
	 * @param	publicId		The public identifier of the external entity
	 *							being referenced, or null if none was supplied.
	 * @param	systemId		The system identifier of the external entity
	 *							being referenced.
	 * @param	catalogs		A stack of catalogs being processed used to
	 *							detect circular dependency.
	 * @param	catalog			The URI of the catalog to process next.
	 * @return  The URI of the resolved entity or <CODE>null</CODE>.
	 * @throws	SAXException If an error occurs during processing.
	 * @since	TFP 1.0
	 */
	private String process (final String publicId, final String systemId, Stack catalogs, String catalog)
		throws SAXException
	{
		Catalog			target = CatalogManager.find (catalog);

		return (target.applyRules (publicId, systemId, catalogs));
	}
	
	/**
	 * Causes the resolution process to recurse into another <CODE>XmlCatalog
	 * </CODE> to find a match.
	 *
	 * @param	uri				The public identifier of the external entity
	 *							being referenced, or null if none was supplied.
	 * @param	catalogs		A stack of catalogs being processed used to
	 *							detect circular dependency.
	 * @param	catalog			The URI of the catalog to process next.
	 * @return  The URI of the resolved entity or <CODE>null</CODE>.
	 * @throws	SAXException If an error occurs during processing.
	 * @since	TFP 1.0
	 */
	private String process (final String uri, Stack catalogs, String catalog)
		throws SAXException
	{
		Catalog			target = CatalogManager.find (catalog);

		return (target.applyRules (uri, catalogs));
	}
}