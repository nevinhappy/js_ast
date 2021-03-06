package com.mindlin.jsast.transform;

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
import com.mindlin.jsast.tree.ObjectPropertyKeyTree;
import com.mindlin.jsast.tree.ParenthesizedTree;
import com.mindlin.jsast.tree.PatternTree;
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
import com.mindlin.jsast.tree.type.TypeTree;

public interface TreeTransformation<D> extends TreeVisitor<Tree, D> {
	
	@Override
	default TypeTree visitAnyType(AnyTypeTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitArrayLiteral(ArrayLiteralTree node, D d) {
		return node;
	}
	
	@Override
	default PatternTree visitArrayPattern(ArrayPatternTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitArrayType(ArrayTypeTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitAssignment(AssignmentTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitAssignmentPattern(AssignmentPatternTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitAwait(AwaitTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitBinary(BinaryTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitBlock(BlockTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitBooleanLiteral(BooleanLiteralTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitBreak(BreakTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitCast(CastTree node, D d) {
		return node;
	}
	
	@Override
	default Tree visitClassDeclaration(ClassDeclarationTree node, D d) {
		return node;
	}
	
	@Override
	default CommentNode visitComment(CommentNode node, D d) {
		return node;
	}
	
	@Override
	default CompilationUnitTree visitCompilationUnit(CompilationUnitTree node, D d) {
		return node;
	}
	
	@Override
	default ObjectPropertyKeyTree visitComputedPropertyKey(ComputedPropertyKeyTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitConditionalExpression(ConditionalExpressionTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitContinue(ContinueTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitDebugger(DebuggerTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitDoWhileLoop(DoWhileLoopTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitEmptyStatement(EmptyStatementTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitEnumDeclaration(EnumDeclarationTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitExport(ExportTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitExpressionStatement(ExpressionStatementTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitForEachLoop(ForEachLoopTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitForLoop(ForLoopTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitFunctionCall(FunctionCallTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitFunctionExpression(FunctionExpressionTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitFunctionType(FunctionTypeTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitGenericRefType(GenericRefTypeTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitGenericType(GenericTypeTree node, D d) {
		return node;
	}
	
	@Override
	default Tree visitIdentifier(IdentifierTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitIdentifierType(IdentifierTypeTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitIf(IfTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitImport(ImportTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitIndexType(IndexTypeTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitInterfaceDeclaration(InterfaceDeclarationTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitInterfaceType(InterfaceTypeTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitIntersectionType(BinaryTypeTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitLabeledStatement(LabeledStatementTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitMemberType(MemberTypeTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitNew(NewTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitNull(NullLiteralTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitNumericLiteral(NumericLiteralTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitObjectLiteral(ObjectLiteralTree node, D d) {
		return node;
	}
	
	@Override
	default PatternTree visitObjectPattern(ObjectPatternTree node, D d) {
		return node;
	}
	
	@Override
	default Tree visitParameterType(ParameterTypeTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitParentheses(ParenthesizedTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitRegExpLiteral(RegExpLiteralTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitReturn(ReturnTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitSequence(SequenceTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitSpecialType(SpecialTypeTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitStringLiteral(StringLiteralTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitSuper(SuperExpressionTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitSwitch(SwitchTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitTemplateLiteral(TemplateLiteralTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitThis(ThisExpressionTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitThrow(ThrowTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitTry(TryTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitTupleType(TupleTypeTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitTypeAlias(TypeAliasTree node, D d) {
		return node;
	}
	
	@Override
	default ExpressionTree visitUnary(UnaryTree node, D d) {
		return node;
	}
	
	@Override
	default TypeTree visitUnionType(BinaryTypeTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitVariableDeclaration(VariableDeclarationTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitWhileLoop(WhileLoopTree node, D d) {
		return node;
	}
	
	@Override
	default StatementTree visitWith(WithTree node, D d) {
		return node;
	}
}
