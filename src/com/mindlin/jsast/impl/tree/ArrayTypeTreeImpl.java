package com.mindlin.jsast.impl.tree;

import com.mindlin.jsast.tree.type.ArrayTypeTree;
import com.mindlin.jsast.tree.type.TypeTree;

public class ArrayTypeTreeImpl extends AbstractTypeTree implements ArrayTypeTree {
	protected final TypeTree baseType;
	
	public ArrayTypeTreeImpl(long start, long end, boolean implicit, TypeTree baseType) {
		super(start, end, implicit);
		this.baseType = baseType;
	}

	@Override
	public TypeTree getBaseType() {
		return this.baseType;
	}
	
}
