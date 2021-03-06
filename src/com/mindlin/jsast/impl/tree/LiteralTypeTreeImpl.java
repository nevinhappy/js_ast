package com.mindlin.jsast.impl.tree;

import java.util.Objects;

import com.mindlin.jsast.tree.LiteralTree;
import com.mindlin.jsast.tree.type.LiteralTypeTree;

public class LiteralTypeTreeImpl<T> extends AbstractTypeTree implements LiteralTypeTree<T> {
	protected final LiteralTree<T> value;
	
	public LiteralTypeTreeImpl(long start, long end, LiteralTree<T> value, boolean implicit) {
		super(start, end, implicit);
		this.value = value;
	}
	
	public LiteralTypeTreeImpl(LiteralTree<T> value, boolean implicit) {
		this(value.getStart(), value.getEnd(), value, implicit);
	}
	
	@Override
	public LiteralTree<T> getValue() {
		return value;
	}
	
	@Override
	protected int hash() {
		return Objects.hash(getKind(), getValue());
	}
	
}
