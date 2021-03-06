package com.mindlin.jsast.transform;

import com.mindlin.jsast.tree.type.GenericTypeTree;
import com.mindlin.jsast.tree.type.TypeTree;

public interface TypeContext {
	TypeTree getTypeForName(String name);
	void defineType(String name, TypeTree type);
	void undefineType(String name);
	void defineGenericParameter(GenericTypeTree parameter);
}
