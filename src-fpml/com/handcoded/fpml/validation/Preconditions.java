// Copyright (C),2005-2010 HandCoded Software Ltd.
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

import com.handcoded.fpml.Releases;
import com.handcoded.validation.Precondition;

/**
 * The <CODE>Preconditions</CODE> interface defines a set of <CODE>Precondition
 * </CODE> instances used to detect releases and various products.
 *
 * @author	BitWise
 * @version	$Id$
 * @since	TFP 1.0
 */
public interface Preconditions
{
	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 1-0 compatible
	 * documents.
	 * @since	TFP 1.0
	 */
	public static final Precondition 	R1_0
		= new VersionPrecondition (Releases.R1_0);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 2-0 compatible
	 * documents.
	 * @since	TFP 1.0
	 */
	public static final Precondition 	R2_0
		= new VersionPrecondition (Releases.R2_0);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 3-0 compatible
	 * documents.
	 * @since	TFP 1.0
	 */
	public static final Precondition 	R3_0
		= new VersionPrecondition (Releases.R3_0);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 4-0 compatible
	 * documents.
	 * @since	TFP 1.0
	 */
	public static final Precondition 	R4_0
		= new VersionPrecondition (Releases.R4_0);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 4-1 compatible
	 * documents.
	 * @since	TFP 1.0
	 */
	public static final Precondition 	R4_1
		= new VersionPrecondition (Releases.R4_1);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 4-2 compatible
	 * documents.
	 * @since	TFP 1.0
	 */
	public static final Precondition 	R4_2
		= new VersionPrecondition (Releases.R4_2);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 4-3 compatible
	 * documents.
	 * @since	TFP 1.1
	 */
	public static final Precondition 	R4_3
		= new VersionPrecondition (Releases.R4_3);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 4-4 compatible
	 * documents.
	 * @since	TFP 1.2
	 */
	public static final Precondition 	R4_4
		= new VersionPrecondition (Releases.R4_4);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 4-5 compatible
	 * documents.
	 * @since	TFP 1.2
	 */
	public static final Precondition 	R4_5
		= new VersionPrecondition (Releases.R4_5);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 4-6 compatible
	 * documents.
	 * @since	TFP 1.3
	 */
	public static final Precondition 	R4_6
		= new VersionPrecondition (Releases.R4_6);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 4-7 compatible
	 * documents.
	 * @since	TFP 1.4
	 */
	public static final Precondition 	R4_7
		= new VersionPrecondition (Releases.R4_7);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 4-8 compatible
	 * documents.
	 * @since	TFP 1.4
	 */
	public static final Precondition 	R4_8
		= new VersionPrecondition (Releases.R4_8);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 4-8 compatible
	 * documents.
	 * @since	TFP 1.5
	 */
	public static final Precondition 	R4_9
		= new VersionPrecondition (Releases.R4_9);

	/**
	 * A <CODE>Precondition</CODE> instance that detects any FpML 4-x compatible
	 * document.
	 * @since	TFP 1.5
	 */
	public static final Precondition 	R4_X
		= new VersionRangePrecondition (Releases.R4_0, Releases.R4_9);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 5-0 confirmation
	 * documents.
	 * @since	TFP 1.4
	 */
	public static final Precondition 	R5_0_CONFIRMATION
		= new VersionPrecondition (Releases.R5_0_CONFIRMATION);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 5-0 reporting
	 * documents.
	 * @since	TFP 1.4
	 */
	public static final Precondition 	R5_0_REPORTING
		= new VersionPrecondition (Releases.R5_0_REPORTING);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 5-0 compatible
	 * documents.
	 * @since	TFP 1.4
	 */
	public static final Precondition 	R5_0
		= Precondition.or (R5_0_CONFIRMATION, R5_0_REPORTING);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 5-1 confirmation
	 * documents.
	 * @since	TFP 1.5
	 */
	public static final Precondition 	R5_1_CONFIRMATION
		= new VersionPrecondition (Releases.R5_1_CONFIRMATION);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 5-1 reporting
	 * documents.
	 * @since	TFP 1.5
	 */
	public static final Precondition 	R5_1_REPORTING
		= new VersionPrecondition (Releases.R5_1_REPORTING);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML 5-1 compatible
	 * documents.
	 * @since	TFP 1.5
	 */
	public static final Precondition 	R5_1
		= Precondition.or (R5_1_CONFIRMATION, R5_1_REPORTING);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions that use
	 * XPointer syntax for intra-document links.
	 * @since	TFP 1.0
	 */
	public static final Precondition	R1_0__R2_0
		= new VersionRangePrecondition (Releases.R1_0, Releases.R2_0);

    /**
	 * A <CODE>Precondition</CODE> instance that detects either FpML 1-0,
	 * 2-0 or 3-0 compatible documents.
	 * @since	TFP 1.0
	 */
	public static final Precondition	R1_0__R3_0
		= new VersionRangePrecondition (Releases.R1_0, Releases.R3_0);

    /**
	 * A <CODE>Precondition</CODE> instance that detects either FpML 2-0 or
	 * 3-0 compatible documents.
	 * @since	TFP 1.0
	 */
	public static final Precondition	R2_0__R3_0
		= new VersionRangePrecondition (Releases.R2_0, Releases.R3_0);

    /**
	 * A <CODE>Precondition</CODE> instance that detects either FpML 3-0 or
	 * 4-0 compatible documents.
	 * @since	TFP 1.0
	 */
	public static final Precondition	R3_0__R4_0
		= new VersionRangePrecondition (Releases.R3_0, Releases.R4_0);;

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 5-1 and
	 * later.
	 * @since	TFP 1.5
	 */
	public static final Precondition	R5_1__LATER
		= new VersionRangePrecondition (Releases.R5_1_CONFIRMATION, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 5-0 and
	 * later.
	 * @since	TFP 1.4
	 */
	public static final Precondition	R5_0__LATER
		= new VersionRangePrecondition (Releases.R5_0_CONFIRMATION, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 4-8 and
	 * later.
	 * @since	TFP 1.5
	 */
	public static final Precondition	R4_9__LATER
		= new VersionRangePrecondition (Releases.R4_9, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 4-8 and
	 * later.
	 * @since	TFP 1.4
	 */
	public static final Precondition	R4_8__LATER
		= new VersionRangePrecondition (Releases.R4_8, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 4-7 and
	 * later.
	 * @since	TFP 1.4
	 */
	public static final Precondition	R4_7__LATER
		= new VersionRangePrecondition (Releases.R4_7, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 4-6 and
	 * later.
	 * @since	TFP 1.3
	 */
	public static final Precondition	R4_6__LATER
		= new VersionRangePrecondition (Releases.R4_6, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 4-5 and
	 * later.
	 * @since	TFP 1.2
	 */
	public static final Precondition	R4_5__LATER
		= new VersionRangePrecondition (Releases.R4_5, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 4-4 and
	 * later.
	 * @since	TFP 1.2
	 */
	public static final Precondition	R4_4__LATER
		= new VersionRangePrecondition (Releases.R4_4, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 4-3 and
	 * later.
	 * @since	TFP 1.2
	 */
	public static final Precondition	R4_3__LATER
		= new VersionRangePrecondition (Releases.R4_3, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 4-2 and
	 * later.
	 * @since	TFP 1.1
	 */
	public static final Precondition	R4_2__LATER
		= new VersionRangePrecondition (Releases.R4_2, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 4-1 and
	 * later.
	 * @since	TFP 1.0
	 */
	public static final Precondition	R4_1__LATER
		= new VersionRangePrecondition (Releases.R4_1, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML versions 4-0 and
	 * later.
	 * @since	TFP 1.0
	 */
	public static final Precondition	R4_0__LATER
		= new VersionRangePrecondition (Releases.R4_0, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML version 3-0 and
	 * later.
	 * @since	TFP 1.0
	 */
	public static final Precondition	R3_0__LATER
		= new VersionRangePrecondition (Releases.R3_0, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects FpML version 2-0 and
	 * later.
	 * @since	TFP 1.0
	 */
	public static final Precondition	R2_0__LATER
		= new VersionRangePrecondition (Releases.R2_0, null);

	/**
	 * A <CODE>Precondition</CODE> instance that detects all FpML versions except
	 * 4-0.
	 * @since	TFP 1.0
	 */
	public static final Precondition	NOT_R4_0
		= Precondition.not (R4_0);
}
