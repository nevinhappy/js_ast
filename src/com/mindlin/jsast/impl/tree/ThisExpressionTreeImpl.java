package com.mindlin.jsast.impl.tree;

import com.mindlin.jsast.impl.lexer.Token;
import com.mindlin.jsast.tree.ThisExpressionTree;

public class ThisExpressionTreeImpl extends AbstractTree implements ThisExpressionTree {
	public ThisExpressionTreeImpl(Token t) {
		this(t.getStart(), t.getEnd());
	}
	
	public ThisExpressionTreeImpl(long start, long end) {
		super(start, end);
	}
}
