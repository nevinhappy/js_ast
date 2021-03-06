package com.mindlin.jsast.impl.tree;

import java.util.List;
import java.util.Objects;

import com.mindlin.jsast.tree.IdentifierTree;
import com.mindlin.jsast.tree.TypeAliasTree;
import com.mindlin.jsast.tree.type.GenericTypeTree;
import com.mindlin.jsast.tree.type.TypeTree;

public class TypeAliasTreeImpl extends AbstractTree implements TypeAliasTree {
	protected final IdentifierTree alias;
	protected final List<GenericTypeTree> genericParams;
	protected final TypeTree value;
	
	public TypeAliasTreeImpl(long start, long end, IdentifierTree alias, List<GenericTypeTree> params, TypeTree value) {
		super(start, end);
		this.alias = alias;
		this.genericParams = params;
		this.value = value;
	}
	
	@Override
	public IdentifierTree getAlias() {
		return this.alias;
	}

	@Override
	public List<GenericTypeTree> getGenericParameters() {
		return this.genericParams;
	}

	@Override
	public TypeTree getValue() {
		return this.value;
	}
	
	@Override
	protected int hash() {
		return Objects.hash(getKind(), getAlias(), getGenericParameters(), getValue());
	}
	
}
