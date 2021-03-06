package com.mindlin.jsast.impl.tree;

import java.util.List;
import java.util.Objects;

import com.mindlin.jsast.tree.IdentifierTree;
import com.mindlin.jsast.tree.type.IdentifierTypeTree;
import com.mindlin.jsast.tree.type.TypeTree;

public class IdentifierTypeTreeImpl extends AbstractTypeTree implements IdentifierTypeTree {
	protected final IdentifierTree name;
	protected final List<TypeTree> generics;
	
	public IdentifierTypeTreeImpl(long start, long end, boolean implicit, IdentifierTree name, List<TypeTree> generics) {
		super(start, end, implicit);
		this.name = name;
		this.generics = generics;
	}
	
	@Override
	public IdentifierTree getIdentifier() {
		return this.name;
	}
	
	@Override
	public List<TypeTree> getGenerics() {
		return this.generics;
	}
	
	@Override
	protected int hash() {
		return Objects.hash(getKind(), getIdentifier(), getGenerics());
	}
	
}
