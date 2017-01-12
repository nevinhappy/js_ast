package com.mindlin.jsast.impl.tree;

import com.mindlin.jsast.tree.ClassPropertyTree;
import com.mindlin.jsast.tree.ObjectPropertyKeyTree;
import com.mindlin.jsast.tree.Tree;
import com.mindlin.jsast.tree.TypeTree;

public class ClassPropertyTreeImpl<T extends Tree> extends AbstractTypedPropertyTree implements ClassPropertyTree<T> {
	protected final T value;
	protected final boolean isStatic;
	protected final AccessModifier access;
	protected final PropertyDeclarationType declaration;
	
	public ClassPropertyTreeImpl(long start, long end, boolean readonly, boolean isStatic, AccessModifier access, PropertyDeclarationType declaration, ObjectPropertyKeyTree key, TypeTree type, T value) {
		super(start, end, readonly, key, type);
		this.isStatic = isStatic;
		this.access = access;
		this.declaration = declaration;
		this.value = value;
	}
	
	@Override
	public T getValue() {
		return value;
	}
	
	@Override
	public boolean isStatic() {
		return isStatic;
	}
	
	@Override
	public AccessModifier getAccess() {
		return access;
	}
	
	@Override
	public PropertyDeclarationType getDeclarationType() {
		return declaration;
	}
	
}