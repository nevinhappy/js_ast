package com.mindlin.jsast.tree.type;

import com.mindlin.jsast.tree.Tree;
import com.mindlin.jsast.tree.TypeTree;

public interface IndexTypeTree extends TypeTree {
	TypeTree getIndexType();
	
	TypeTree getReturnType();
	
	@Override
	default Tree.Kind getKind() {
		return Tree.Kind.INDEX_TYPE;
	}
}
