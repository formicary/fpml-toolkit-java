// Copyright (C),2005-2011 HandCoded Software Ltd.
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

package com.handcoded.classification.xml;

final class IfNode extends ExprNode
{
	public IfNode (final ExprNode testExpr, final ExprNode thenExpr, final ExprNode elseExpr)
	{
		this.testExpr = testExpr;
		this.thenExpr = thenExpr;
		this.elseExpr = elseExpr;
	}

	public boolean evaluate (final Object value)
	{	
		return ((testExpr.evaluate (value) ? thenExpr : elseExpr).evaluate (value));
	}
	
	private final ExprNode		testExpr;

	private final ExprNode		thenExpr;
	
	private final ExprNode		elseExpr;
}