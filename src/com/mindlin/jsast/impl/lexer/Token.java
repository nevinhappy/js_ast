package com.mindlin.jsast.impl.lexer;

import com.mindlin.jsast.impl.parser.JSOperator;
import com.mindlin.jsast.impl.parser.JSSpecialGroup;

public class Token {
	protected final TokenKind kind;
	protected final long position;
	protected final String text;
	protected final Object value;

	public Token(long position, TokenKind kind, String text, Object value) {
		this.position = position;
		this.kind = kind;
		this.text = text;
		this.value = value;
	}

	public long getStart() {
		return position;
	}

	public long getLength() {
		return text == null ? 0 : text.length();
	}

	public long getEnd() {
		return getStart() + getLength();
	}

	public String getText() {
		return text;
	}

	public TokenKind getKind() {
		return kind;
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue() {
		return (T) value;
	}

	public boolean isSpecial() {
		return getKind() == TokenKind.SPECIAL;
	}

	public boolean isOperator() {
		return getKind() == TokenKind.OPERATOR;
	}

	public boolean isKeyword() {
		return getKind() == TokenKind.KEYWORD;
	}

	public boolean isBracket() {
		return getKind() == TokenKind.BRACKET;
	}

	public boolean isIdentifier() {
		return getKind() == TokenKind.IDENTIFIER;
	}

	public boolean isLiteral() {
		final TokenKind kind = getKind();
		return kind == TokenKind.STRING_LITERAL || kind == TokenKind.BOOLEAN_LITERAL || kind == TokenKind.NULL_LITERAL
				|| kind == TokenKind.REGEX_LITERAL || kind == TokenKind.NUMERIC_LITERAL
				|| kind == TokenKind.TEMPLATE_LITERAL;
	}

	/**
	 * Is end of statement. EOL or EOF
	 * 
	 * @return
	 */
	public boolean isEOS() {
		return getKind() == TokenKind.SPECIAL && (getValue() == JSSpecialGroup.EOF || getValue() == JSSpecialGroup.EOL
				|| getValue() == JSSpecialGroup.SEMICOLON);
	}

	public boolean matches(TokenKind kind, Object value) {
		if (getKind() != kind)
			return false;
		if (getValue() == value)
			return true;
		return getValue() != null && getValue().equals(value);
	}

	public Token reinterpretAsIdentifier() {
		Object value;
		switch (getKind()) {
			case IDENTIFIER:
				return this;
			case KEYWORD:
			case NUMERIC_LITERAL:
			case BOOLEAN_LITERAL:
				value = this.getValue().toString();
				break;
			case NULL_LITERAL:
				value = "null";
				break;
			case OPERATOR:
				value = this.<JSOperator>getValue().getText();
				break;
			case SPECIAL:
				if (this.getValue() == JSSpecialGroup.SEMICOLON)
					value = ';';
				// Fallthrough intentional
				// There is no way to possibly reinterpret these
			case REGEX_LITERAL:
			case STRING_LITERAL:
			case TEMPLATE_LITERAL:
			case BRACKET:
			case COMMENT:
			default:
				throw new UnsupportedOperationException(this + " cannot be reinterpreted as an identifier");
		}
		return new Token(getStart(), TokenKind.IDENTIFIER, getText(), value);
	}

	@Override
	public String toString() {
		//@formatter:off
		StringBuilder sb = new StringBuilder(70)//High end of expected outputs
				.append(this.getClass().getSimpleName())
				.append("{kind=").append(getKind())
				.append(",value=").append(value)
				.append(",start=").append(getStart())
				.append(",end=").append(getEnd());
		//@formatter:on

		if (getText() == null)
			sb.append(",text=null");
		else
			sb.append(",text=\"").append(getText()).append('"');

		sb.append('}');
		return sb.toString();
	}
}