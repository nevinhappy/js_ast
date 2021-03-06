package com.mindlin.jsast.impl.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mindlin.jsast.impl.lexer.JSLexer;
import com.mindlin.jsast.impl.parser.JSParser.Context;
import com.mindlin.jsast.tree.type.ArrayTypeTree;
import com.mindlin.jsast.tree.type.BinaryTypeTree;
import com.mindlin.jsast.tree.type.IdentifierTypeTree;
import com.mindlin.jsast.tree.type.InterfaceTypeTree;
import com.mindlin.jsast.tree.type.SpecialTypeTree.SpecialType;
import com.mindlin.jsast.tree.type.TupleTypeTree;
import com.mindlin.jsast.tree.type.TypeTree;
import com.mindlin.jsast.tree.InterfacePropertyTree;
import com.mindlin.jsast.tree.ObjectPropertyKeyTree;
import com.mindlin.jsast.tree.Tree.Kind;

import static com.mindlin.jsast.impl.parser.JSParserTest.*;

public class TypeTest {
	
	@SuppressWarnings("unchecked")
	static <T extends TypeTree> T parseType(String expr, Kind expectedKind) {
		JSLexer lexer = new JSLexer(expr);
		T result = (T) new JSParser().parseType(lexer, new Context());
		assertTrue("Did not read whole statement", lexer.isEOF());
		assertEquals(expectedKind, result.getKind());
		return result;
	}
	
	static void assertIdentifierType(String name, int numGenerics, TypeTree type) {
		assertEquals(Kind.IDENTIFIER_TYPE, type.getKind());
		assertIdentifier(name, ((IdentifierTypeTree)type).getIdentifier());
		assertEquals(numGenerics, ((IdentifierTypeTree)type).getGenerics().size());
	}
	
	@Test
	public void testIdentifierType() {
		IdentifierTypeTree type = parseType("Foo", Kind.IDENTIFIER_TYPE);
		assertIdentifierType("Foo", 0, type);
	}
	
	@Test
	public void testIdentifierTypeWithGeneric() {
		IdentifierTypeTree type = parseType("Foo<T>", Kind.IDENTIFIER_TYPE);
		assertIdentifierType("Foo", 1, type);
		assertIdentifierType("T", 0, type.getGenerics().get(0));
	}
	
	@Test
	public void testArrayType() {
		ArrayTypeTree type = parseType("T[]", Kind.ARRAY_TYPE);
		assertIdentifierType("T", 0, type.getBaseType());
	}
	
	@Test
	public void testNestedArrayType() {
		ArrayTypeTree type = parseType("T[][]", Kind.ARRAY_TYPE);
		
		assertEquals(Kind.ARRAY_TYPE, type.getBaseType().getKind());
		
		ArrayTypeTree base1 = (ArrayTypeTree) type.getBaseType();
		assertIdentifierType("T", 0, base1.getBaseType());
	}
	
	@Test
	public void testGenericArrayType() {
		ArrayTypeTree type = parseType("Array<T>", Kind.ARRAY_TYPE);
		assertIdentifierType("T", 0, type.getBaseType());
	}
	
	@Test
	public void testIdentifierTypeWithGenerics() {
		IdentifierTypeTree type = parseType("Map<K,V>", Kind.IDENTIFIER_TYPE);
		assertIdentifierType("Map", 2, type);
		assertIdentifierType("K", 0, type.getGenerics().get(0));
		assertIdentifierType("V", 0, type.getGenerics().get(1));
	}
	
	@Test
	public void testUnionType() {
		BinaryTypeTree type = parseType("A | B", Kind.TYPE_UNION);
		assertIdentifierType("A", 0, type.getLeftType());
		assertIdentifierType("B", 0, type.getRightType());
	}
	
	@Test
	public void testIntersectionType() {
		BinaryTypeTree type = parseType("A & B", Kind.TYPE_INTERSECTION);
		assertIdentifierType("A", 0, type.getLeftType());
		assertIdentifierType("B", 0, type.getRightType());
	}
	
	@Test
	public void testIntersectionTypeWithGenerics() {
		BinaryTypeTree type = parseType("A<T> & B<R>", Kind.TYPE_INTERSECTION);
		assertIdentifierType("A", 1, type.getLeftType());
		assertIdentifierType("T", 0, ((IdentifierTypeTree)type.getLeftType()).getGenerics().get(0));
		assertIdentifierType("B", 1, type.getRightType());
		assertIdentifierType("R", 0, ((IdentifierTypeTree)type.getRightType()).getGenerics().get(0));
	}
	
	@Test
	public void testSimpleInlineInterfaceType() {
		InterfaceTypeTree type = parseType("{a:Foo}", Kind.INTERFACE_TYPE);
		assertEquals(1, type.getProperties().size());
		
		InterfacePropertyTree prop0 = type.getProperties().get(0);
		ObjectPropertyKeyTree key0 = prop0.getKey();
		assertFalse(key0.isComputed());
		assertIdentifier("a", key0);
		assertIdentifierType("Foo", 0, prop0.getType());
	}
	
	@Test
	public void testTupleType() {
		TupleTypeTree type = parseType("[string, number]", Kind.TUPLE_TYPE);
		assertEquals(2, type.getSlotTypes().size());
		assertSpecialType(SpecialType.STRING, type.getSlotTypes().get(0));
		assertSpecialType(SpecialType.NUMBER, type.getSlotTypes().get(1));
	}
	
	@Test
	public void testParentheticalType() {
		BinaryTypeTree intersection = parseType("A&(B|C)", Kind.TYPE_INTERSECTION);
		assertIdentifierType("A", 0, intersection.getLeftType());
		
		assertEquals(Kind.TYPE_UNION, intersection.getRightType().getKind());
		BinaryTypeTree union = (BinaryTypeTree) intersection.getRightType();
		assertIdentifierType("B", 0, union.getLeftType());
		assertIdentifierType("C", 0, union.getRightType());
	}
}
