package com.mindlin.jsast.impl.tree;

import java.util.Objects;

import com.mindlin.jsast.impl.lexer.Token;
import com.mindlin.jsast.tree.EmptyStatementTree;

public class EmptyStatementTreeImpl extends AbstractTree implements EmptyStatementTree {

	public EmptyStatementTreeImpl(long start, long end) {
		super(start, end);
	}

	public EmptyStatementTreeImpl(Token t) {
		this(t.getStart(), t.getEnd());
	}
	
	@Override
	protected int hash() {
		return Objects.hash(getKind());
	}
}
