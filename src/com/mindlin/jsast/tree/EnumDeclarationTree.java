package com.mindlin.jsast.tree;

import java.util.Set;

public interface EnumDeclarationTree extends ExpressionTree {

	IdentifierTree getIdentifier();

	Set<ObjectPropertyTree> getValues();

	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.ENUM_DECLARATION;
	}
}
