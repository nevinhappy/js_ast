package com.mindlin.jsast.impl.tree;

import com.mindlin.jsast.impl.lexer.Token;
import com.mindlin.jsast.tree.ExpressionTree;
import com.mindlin.jsast.tree.IdentifierTree;
import com.mindlin.jsast.tree.TypeTree;
import com.mindlin.jsast.tree.VariableDeclaratorTree;

public class VariableDeclaratorTreeImpl extends AbstractTree implements VariableDeclaratorTree {
	protected final IdentifierTree identifier;
	protected final ExpressionTree initializer;
	protected final TypeTree type;

	public VariableDeclaratorTreeImpl(Token t) {
		this(t.getStart(), t.getEnd(), t.getValue(), null, null);
	}

	public VariableDeclaratorTreeImpl(long start, long end, IdentifierTree identifier, TypeTree type, ExpressionTree initializer) {
		super(start, end);
		this.identifier = identifier;
		this.type = type;
		this.initializer = initializer;
	}

	@Override
	public ExpressionTree getIntitializer() {
		return this.initializer;
	}

	@Override
	public TypeTree getType() {
		return this.type;
	}

	@Override
	public IdentifierTree getIdentifier() {
		return identifier;
	}

}