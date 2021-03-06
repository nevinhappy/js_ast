package com.mindlin.jsast.impl.parser;

import java.util.function.Predicate;

import com.mindlin.jsast.impl.lexer.Token;
import com.mindlin.jsast.impl.lexer.TokenKind;

/**
 * Commonly used predicates for matching certain classes of tokens
 * @author mailmindlin
 */
public class TokenPredicate {
	public static Predicate<Token> VARIABLE_START = t->(t.isKeyword() && (t.getValue() == JSKeyword.VAR || t.getValue() == JSKeyword.LET || t.getValue() == JSKeyword.CONST));
	public static Predicate<Token> LET_OR_CONST = t->(t.isKeyword() && (t.getValue() == JSKeyword.LET || t.getValue() == JSKeyword.CONST));
	public static Predicate<Token> IN_OR_OF = t->(t.isKeyword() && (t.getValue() == JSKeyword.IN || t.getValue() == JSKeyword.OF));
	public static Predicate<Token> PARAMETER_TYPE_START = t->(t.isOperator() && (t.getValue() == JSOperator.QUESTION_MARK || t.getValue() == JSOperator.COLON || t.getValue() == JSOperator.ASSIGNMENT));
	public static Predicate<Token> START_OF_PARAMETER = t->{
		if (t.isIdentifier())
			return true;
		if (t.isBracket())
			return t.getValue().equals('{') || t.getValue().equals('[');
		return t.matches(TokenKind.OPERATOR, JSOperator.SPREAD);
	};
	public static Predicate<Token> TYPE_CONTINUATION = t->(t.isOperator() && (t.getValue() == JSOperator.BITWISE_OR || t.getValue() == JSOperator.BITWISE_AND));
	/**
	 * TokenPredicate can't be instantiated
	 */
	private TokenPredicate() {}
}
