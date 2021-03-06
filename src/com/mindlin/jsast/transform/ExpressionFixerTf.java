package com.mindlin.jsast.transform;

import com.mindlin.jsast.impl.tree.AssignmentTreeImpl;
import com.mindlin.jsast.impl.tree.BinaryTreeImpl;
import com.mindlin.jsast.impl.tree.CastTreeImpl;
import com.mindlin.jsast.impl.tree.ExpressionStatementTreeImpl;
import com.mindlin.jsast.impl.tree.MemberExpressionTreeImpl;
import com.mindlin.jsast.impl.tree.ParenthesizedTreeImpl;
import com.mindlin.jsast.impl.tree.UnaryTreeImpl;
import com.mindlin.jsast.tree.ArrayLiteralTree;
import com.mindlin.jsast.tree.ArrayPatternTree;
import com.mindlin.jsast.tree.AssignmentPatternTree;
import com.mindlin.jsast.tree.AssignmentTree;
import com.mindlin.jsast.tree.BinaryTree;
import com.mindlin.jsast.tree.BlockTree;
import com.mindlin.jsast.tree.BooleanLiteralTree;
import com.mindlin.jsast.tree.BreakTree;
import com.mindlin.jsast.tree.CastTree;
import com.mindlin.jsast.tree.ClassDeclarationTree;
import com.mindlin.jsast.tree.CommentNode;
import com.mindlin.jsast.tree.CompilationUnitTree;
import com.mindlin.jsast.tree.ComputedPropertyKeyTree;
import com.mindlin.jsast.tree.ConditionalExpressionTree;
import com.mindlin.jsast.tree.ContinueTree;
import com.mindlin.jsast.tree.DebuggerTree;
import com.mindlin.jsast.tree.DoWhileLoopTree;
import com.mindlin.jsast.tree.EmptyStatementTree;
import com.mindlin.jsast.tree.EnumDeclarationTree;
import com.mindlin.jsast.tree.ExportTree;
import com.mindlin.jsast.tree.ExpressionStatementTree;
import com.mindlin.jsast.tree.ExpressionTree;
import com.mindlin.jsast.tree.ForEachLoopTree;
import com.mindlin.jsast.tree.ForLoopTree;
import com.mindlin.jsast.tree.FunctionCallTree;
import com.mindlin.jsast.tree.FunctionExpressionTree;
import com.mindlin.jsast.tree.IdentifierTree;
import com.mindlin.jsast.tree.IfTree;
import com.mindlin.jsast.tree.ImportTree;
import com.mindlin.jsast.tree.InterfaceDeclarationTree;
import com.mindlin.jsast.tree.LabeledStatementTree;
import com.mindlin.jsast.tree.NewTree;
import com.mindlin.jsast.tree.NullLiteralTree;
import com.mindlin.jsast.tree.NumericLiteralTree;
import com.mindlin.jsast.tree.ObjectLiteralTree;
import com.mindlin.jsast.tree.ObjectPatternTree;
import com.mindlin.jsast.tree.ParenthesizedTree;
import com.mindlin.jsast.tree.RegExpLiteralTree;
import com.mindlin.jsast.tree.ReturnTree;
import com.mindlin.jsast.tree.SequenceTree;
import com.mindlin.jsast.tree.StatementTree;
import com.mindlin.jsast.tree.StringLiteralTree;
import com.mindlin.jsast.tree.SuperExpressionTree;
import com.mindlin.jsast.tree.SwitchTree;
import com.mindlin.jsast.tree.TemplateLiteralTree;
import com.mindlin.jsast.tree.ThisExpressionTree;
import com.mindlin.jsast.tree.ThrowTree;
import com.mindlin.jsast.tree.Tree;
import com.mindlin.jsast.tree.Tree.Kind;
import com.mindlin.jsast.tree.TreeVisitor;
import com.mindlin.jsast.tree.TryTree;
import com.mindlin.jsast.tree.TypeAliasTree;
import com.mindlin.jsast.tree.UnaryTree;
import com.mindlin.jsast.tree.UnaryTree.AwaitTree;
import com.mindlin.jsast.tree.VariableDeclarationTree;
import com.mindlin.jsast.tree.WhileLoopTree;
import com.mindlin.jsast.tree.WithTree;
import com.mindlin.jsast.tree.type.AnyTypeTree;
import com.mindlin.jsast.tree.type.ArrayTypeTree;
import com.mindlin.jsast.tree.type.BinaryTypeTree;
import com.mindlin.jsast.tree.type.FunctionTypeTree;
import com.mindlin.jsast.tree.type.GenericRefTypeTree;
import com.mindlin.jsast.tree.type.GenericTypeTree;
import com.mindlin.jsast.tree.type.IdentifierTypeTree;
import com.mindlin.jsast.tree.type.IndexTypeTree;
import com.mindlin.jsast.tree.type.InterfaceTypeTree;
import com.mindlin.jsast.tree.type.MemberTypeTree;
import com.mindlin.jsast.tree.type.ParameterTypeTree;
import com.mindlin.jsast.tree.type.SpecialTypeTree;
import com.mindlin.jsast.tree.type.TupleTypeTree;

/**
 * Expression trees (esp. binary expressions) may be in a form that violates
 * precedence. This transformation adds parentheses to fix it.
 * 
 * @author mailmindlin
 */
public class ExpressionFixerTf implements TreeTransformation<ASTTransformerContext> {
	protected int precedence(Tree.Kind kind) {
		switch (kind) {
			case IDENTIFIER:
				return 21;
			case PARENTHESIZED:
				return 20;
			case MEMBER_SELECT:
			case ARRAY_ACCESS:
			case NEW:// Assuming argument list
				return 19;
			case FUNCTION_INVOCATION:
				return 18;
			case POSTFIX_INCREMENT:
			case POSTFIX_DECREMENT:
				return 17;
			case LOGICAL_NOT:
			case BITWISE_NOT:
			case UNARY_PLUS:
			case UNARY_MINUS:
			case PREFIX_INCREMENT:
			case PREFIX_DECREMENT:
			case TYPEOF:
			case VOID:
			case DELETE:
				return 16;
			case EXPONENTIATION:
			case CAST:
				return 15;
			case MULTIPLICATION:
			case DIVISION:
			case REMAINDER:
				return 14;
			case ADDITION:
			case SUBTRACTION:
				return 13;
			case LEFT_SHIFT:
			case RIGHT_SHIFT:
			case UNSIGNED_RIGHT_SHIFT:
				return 12;
			case LESS_THAN:
			case LESS_THAN_EQUAL:
			case GREATER_THAN:
			case GREATER_THAN_EQUAL:
			case IN:
			case INSTANCEOF:
				return 11;
			case EQUAL:
			case NOT_EQUAL:
			case STRICT_EQUAL:
			case STRICT_NOT_EQUAL:
				return 10;
			case BITWISE_AND:
				return 9;
			case BITWISE_XOR:
				return 8;
			case BITWISE_OR:
				return 7;
			case LOGICAL_AND:
				return 6;
			case LOGICAL_OR:
				return 5;
			case CONDITIONAL:
				return 4;
			case ASSIGNMENT:
			case ADDITION_ASSIGNMENT:
			case SUBTRACTION_ASSIGNMENT:
			case EXPONENTIATION_ASSIGNMENT:
			case MULTIPLICATION_ASSIGNMENT:
			case DIVISION_ASSIGNMENT:
			case REMAINDER_ASSIGNMENT:
			case LEFT_SHIFT_ASSIGNMENT:
			case RIGHT_SHIFT_ASSIGNMENT:
			case UNSIGNED_RIGHT_SHIFT_ASSIGNMENT:
			case BITWISE_AND_ASSIGNMENT:
			case BITWISE_XOR_ASSIGNMENT:
			case BITWISE_OR_ASSIGNMENT:
				return 3;
			case YIELD:
			case YIELD_GENERATOR:
				return 2;
			case SPREAD:
				return 1;
			case SEQUENCE:
				return 0;
			default:
				return 99;
		}
	}
	
	@Override
	public ExpressionTree visitCast(CastTree node, ASTTransformerContext d) {
		ExpressionTree expr = node.getExpression();
		if (precedence(node.getKind()) > precedence(expr.getKind())) {
			expr = new ParenthesizedTreeImpl(expr.getStart(), expr.getEnd(), expr);
			node = new CastTreeImpl(expr, node.getType());
		}
		return node;
	}
	
	@Override
	public ExpressionTree visitConditionalExpression(ConditionalExpressionTree node, ASTTransformerContext d) {
		// TODO Auto-generated method stub
		return TreeTransformation.super.visitConditionalExpression(node, d);
	}
	
	@Override
	public ExpressionTree visitNew(NewTree node, ASTTransformerContext d) {
		// TODO Auto-generated method stub
		return TreeTransformation.super.visitNew(node, d);
	}
	
	@Override
	public ExpressionTree visitAssignment(AssignmentTree node, ASTTransformerContext d) {
		Tree.Kind kind = node.getKind();
		int precedence = precedence(kind);
		ExpressionTree lhs = node.getLeftOperand(), rhs = node.getRightOperand(), oldLhs = lhs, oldRhs = rhs;
		
		if (precedence(lhs.getKind()) < precedence)
			lhs = new ParenthesizedTreeImpl(lhs.getStart(), lhs.getEnd(), lhs);
		if (precedence(rhs.getKind()) < precedence)
			rhs = new ParenthesizedTreeImpl(rhs.getStart(), rhs.getEnd(), rhs);
		
		if (lhs != oldLhs || rhs != oldRhs)
			node = new AssignmentTreeImpl(kind, lhs, rhs);
		
		return node;
	}
	
	@Override
	public ExpressionTree visitBinary(BinaryTree node, ASTTransformerContext d) {
		Tree.Kind kind = node.getKind();
		int precedence = precedence(kind);
		ExpressionTree lhs = node.getLeftOperand(), rhs = node.getRightOperand(), oldLhs = lhs, oldRhs = rhs;
		
		if (precedence(lhs.getKind()) < precedence)
			lhs = new ParenthesizedTreeImpl(lhs.getStart(), lhs.getEnd(), lhs);
		if (precedence(rhs.getKind()) < precedence)
			rhs = new ParenthesizedTreeImpl(rhs.getStart(), rhs.getEnd(), rhs);
		
		if (lhs != oldLhs || rhs != oldRhs) {
			if (kind == Kind.MEMBER_SELECT || kind == Kind.ARRAY_ACCESS)
				node = new MemberExpressionTreeImpl(kind, lhs, rhs);
			else
				node = new BinaryTreeImpl(kind, lhs, rhs);
		}
		
		return node;
	}
	
	@Override
	public ExpressionTree visitUnary(UnaryTree node, ASTTransformerContext d) {
		ExpressionTree expr = node.getExpression();
		
		if (precedence(expr.getKind()) < precedence(node.getKind())) {
			expr = new ParenthesizedTreeImpl(expr.getStart(), expr.getEnd(), expr);
			
			if (node.getKind() == Kind.VOID)
				node = new UnaryTreeImpl.VoidTreeImpl(expr);
			else
				node = new UnaryTreeImpl(node.getStart(), node.getEnd(), expr, node.getKind());
		}
		
		return node;
	}

	@Override
	public StatementTree visitExpressionStatement(ExpressionStatementTree node, ASTTransformerContext d) {
		//Fix expressions starting with empty object literals, as they're misinterperted as empty blocks.
		ExpressionTree left = node.getExpression().accept(new LeftmostThingFinder(), null);
		if (left == null)
			return node;
		//Wrap in parens
		if (left.getKind() == Kind.OBJECT_LITERAL && ((ObjectLiteralTree) left).getProperties().isEmpty())
			return new ExpressionStatementTreeImpl(node.getStart(), node.getEnd(), new ParenthesizedTreeImpl(node.getStart(), node.getEnd(), node.getExpression()));
		return node;
	}
	
	/**
	 * Finds the expression that is, lexically, the most left.
	 * For *why* this is a thing, see {@link ExpressionFixerTf#visitExpressionStatement(ExpressionStatementTree, Void)}.
	 * @author mailmindlin
	 */
	private class LeftmostThingFinder implements TreeVisitor<ExpressionTree, Void> {
		@Override
		public ExpressionTree visitAnyType(AnyTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitArrayLiteral(ArrayLiteralTree node, Void d) {
			return node;
		}
		@Override
		public ExpressionTree visitArrayPattern(ArrayPatternTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitArrayType(ArrayTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitAssignment(AssignmentTree node, Void d) {
			return node.getLeftOperand().accept(this, d);
		}

		@Override
		public ExpressionTree visitAssignmentPattern(AssignmentPatternTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitAwait(AwaitTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitBinary(BinaryTree node, Void d) {
			return node.getLeftOperand().accept(this, d);
		}

		@Override
		public ExpressionTree visitBlock(BlockTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitBooleanLiteral(BooleanLiteralTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitBreak(BreakTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitCast(CastTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitClassDeclaration(ClassDeclarationTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitComment(CommentNode node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitCompilationUnit(CompilationUnitTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitComputedPropertyKey(ComputedPropertyKeyTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitConditionalExpression(ConditionalExpressionTree node, Void d) {
			return node.getCondition().accept(this, d);
		}

		@Override
		public ExpressionTree visitContinue(ContinueTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitDebugger(DebuggerTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitDoWhileLoop(DoWhileLoopTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitEmptyStatement(EmptyStatementTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitEnumDeclaration(EnumDeclarationTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitExport(ExportTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitExpressionStatement(ExpressionStatementTree node, Void d) {
			return node.getExpression().accept(this, d);
		}

		@Override
		public ExpressionTree visitForEachLoop(ForEachLoopTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitForLoop(ForLoopTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitFunctionCall(FunctionCallTree node, Void d) {
			return node.getCallee().accept(this, d);
		}

		@Override
		public ExpressionTree visitFunctionExpression(FunctionExpressionTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitFunctionType(FunctionTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitGenericRefType(GenericRefTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitGenericType(GenericTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitIdentifier(IdentifierTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitIdentifierType(IdentifierTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitIf(IfTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitImport(ImportTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitIndexType(IndexTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitInterfaceDeclaration(InterfaceDeclarationTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitInterfaceType(InterfaceTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitIntersectionType(BinaryTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitLabeledStatement(LabeledStatementTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitMemberType(MemberTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitNew(NewTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitNull(NullLiteralTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitNumericLiteral(NumericLiteralTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitObjectLiteral(ObjectLiteralTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitObjectPattern(ObjectPatternTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitParameterType(ParameterTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitParentheses(ParenthesizedTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitRegExpLiteral(RegExpLiteralTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitReturn(ReturnTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitSequence(SequenceTree node, Void d) {
			return node.getExpressions().isEmpty() ? null : node.getExpressions().get(0);
		}

		@Override
		public ExpressionTree visitSpecialType(SpecialTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitStringLiteral(StringLiteralTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitSuper(SuperExpressionTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitSwitch(SwitchTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitTemplateLiteral(TemplateLiteralTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitThis(ThisExpressionTree node, Void d) {
			return node;
		}

		@Override
		public ExpressionTree visitThrow(ThrowTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitTry(TryTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitTupleType(TupleTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitTypeAlias(TypeAliasTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitUnary(UnaryTree node, Void d) {
			if (node.getKind() == Kind.POSTFIX_DECREMENT || node.getKind() == Kind.POSTFIX_INCREMENT)
				return node.getExpression().accept(this, d);
			return node;
		}

		@Override
		public ExpressionTree visitUnionType(BinaryTypeTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitVariableDeclaration(VariableDeclarationTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitWhileLoop(WhileLoopTree node, Void d) {
			return null;
		}

		@Override
		public ExpressionTree visitWith(WithTree node, Void d) {
			return null;
		}
		
	}
}
