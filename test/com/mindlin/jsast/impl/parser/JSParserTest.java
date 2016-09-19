package com.mindlin.jsast.impl.parser;

import static com.mindlin.jsast.impl.TestUtils.assertNumberEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mindlin.jsast.exception.JSSyntaxException;
import com.mindlin.jsast.impl.lexer.JSLexer;
import com.mindlin.jsast.impl.parser.JSParser.Context;
import com.mindlin.jsast.tree.ExpressionTree;
import com.mindlin.jsast.tree.IdentifierTree;
import com.mindlin.jsast.tree.NumericLiteralTree;
import com.mindlin.jsast.tree.StatementTree;
import com.mindlin.jsast.tree.StringLiteralTree;
import com.mindlin.jsast.tree.Tree.Kind;

@RunWith(Suite.class)
@SuiteClasses({ArrayLiteralTest.class, BinaryExpressionTest.class, ForLoopTest.class, IdentifierTest.class, ImportStatementTest.class, UnaryOperatorTest.class, VariableDeclarationTest.class })
public class JSParserTest {
	
	protected static final void assertLiteral(ExpressionTree expr, String value) {
		assertEquals(Kind.STRING_LITERAL, expr.getKind());
		assertEquals(value, ((StringLiteralTree)expr).getValue());
	}
	
	/**
	 * Assert if a number literal matches an expected value. Doubles are considered to be
	 * equivalent with a .0001 tolerance.
	 * @param value
	 * @param expr
	 */
	protected static final void assertLiteral(Number value, ExpressionTree expr) {
		assertEquals(Kind.NUMERIC_LITERAL, expr.getKind());
		Number actual = ((NumericLiteralTree)expr).getValue();
		assertNumberEquals(actual, value);
	}
	
	protected static final void assertIdentifier(String name, ExpressionTree expr) {
		assertEquals(Kind.IDENTIFIER, expr.getKind());
		assertEquals(name, ((IdentifierTree)expr).getName());
	}
	
	protected static void assertExceptionalExpression(String expr, String errorMsg) {
		try {
			parseExpression(expr);
			fail(errorMsg);
		} catch (JSSyntaxException e) {
			
		}
	}
	
	protected static void assertExceptionalStatement(String stmt, String errorMsg) {
		try {
			parseStatement(stmt);
			fail(errorMsg);
		} catch (JSSyntaxException e) {
		}
	}
	
	@SuppressWarnings("unchecked")
	protected static <T extends StatementTree> T parseStatement(String stmt) {
		return (T) new JSParser().parseStatement(new JSLexer(stmt), new Context());
	}
	
	@SuppressWarnings("unchecked")
	protected static <T extends ExpressionTree> T parseExpression(String expr) {
		return (T) new JSParser().parseNextExpression(new JSLexer(expr), new Context());
	}
}