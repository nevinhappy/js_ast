package com.mindlin.jsast.tree;

import com.mindlin.jsast.tree.type.AnyTypeTree;
import com.mindlin.jsast.tree.type.ArrayTypeTree;
import com.mindlin.jsast.tree.type.FunctionTypeTree;
import com.mindlin.jsast.tree.type.GenericRefTypeTree;
import com.mindlin.jsast.tree.type.GenericTypeTree;
import com.mindlin.jsast.tree.type.IdentifierTypeTree;
import com.mindlin.jsast.tree.type.IndexTypeTree;
import com.mindlin.jsast.tree.type.InterfaceTypeTree;
import com.mindlin.jsast.tree.type.IntersectionTypeTree;
import com.mindlin.jsast.tree.type.MemberTypeTree;
import com.mindlin.jsast.tree.type.ParameterTypeTree;
import com.mindlin.jsast.tree.type.TupleTypeTree;
import com.mindlin.jsast.tree.type.UnionTypeTree;
import com.mindlin.jsast.tree.type.VoidTypeTree;

public interface TreeVisitor<R, D> {
	R visitAnyType(AnyTypeTree node, D d);
	R visitArrayLiteral(ArrayLiteralTree node, D d);
	R visitArrayPattern(ArrayPatternTree node, D d);
	R visitArrayType(ArrayTypeTree node, D d);
	R visitAssignment(AssignmentTree node, D d);
	R visitAssignmentPattern(AssignmentPatternTree node, D d);
	R visitBinary(BinaryTree node, D d);
	R visitBlock(BlockTree node, D d);
	R visitBooleanLiteral(BooleanLiteralTree node, D d);
	R visitBreak(BreakTree node, D d);
	R visitCast(CastTree node, D d);
	R visitClassDeclaration(ClassDeclarationTree node, D d);
	R visitComment(CommentNode node, D d);
	R visitCompilationUnit(CompilationUnitTree node, D d);
	R visitComputedPropertyKey(ComputedPropertyKeyTree node, D d);
	R visitConditionalExpression(ConditionalExpressionTree node, D d);
	R visitContinue(ContinueTree node, D d);
	R visitDebugger(DebuggerTree node, D d);
	R visitDoWhileLoop(DoWhileLoopTree node, D d);
	R visitEmptyStatement(EmptyStatementTree node, D d);
	R visitEnumDeclaration(EnumDeclarationTree node, D d);
	R visitExport(ExportTree node, D d);
	R visitExpressionStatement(ExpressionStatementTree node, D d);
	R visitForEachLoop(ForEachLoopTree node, D d);
	R visitForLoop(ForLoopTree node, D d);
	R visitFunctionCall(FunctionCallTree node, D d);
	R visitFunctionExpression(FunctionExpressionTree node, D d);
	R visitFunctionType(FunctionTypeTree node, D d);
	R visitGenericRefType(GenericRefTypeTree node, D d);
	R visitGenericType(GenericTypeTree node, D d);
	R visitIdentifier(IdentifierTree node, D d);
	R visitIdentifierType(IdentifierTypeTree node, D d);
	R visitIf(IfTree node, D d);
	R visitImport(ImportTree node, D d);
	R visitIndexType(IndexTypeTree node, D d);
	R visitInterfaceDeclaration(InterfaceDeclarationTree node, D d);
	R visitInterfaceType(InterfaceTypeTree node, D d);
	R visitIntersectionType(IntersectionTypeTree node, D d);
	R visitLabeledStatement(LabeledStatementTree node, D d);
	R visitLiteral(LiteralTree<?> node, D d);
	R visitMemberType(MemberTypeTree node, D d);
	R visitMethodDefinition(MethodDefinitionTree node, D d);
	R visitNew(NewTree node, D d);
	R visitNull(NullLiteralTree node, D d);
	R visitNumericLiteral(NumericLiteralTree node, D d);
	R visitObjectLiteral(ObjectLiteralTree node, D d);
	R visitObjectPattern(ObjectPatternTree node, D d);
	R visitParameter(ParameterTree node, D d);
	R visitParameterType(ParameterTypeTree node, D d);
	R visitParentheses(ParenthesizedTree node, D d);
	R visitRegExpLiteral(RegExpLiteralTree node, D d);
	R visitReturn(ReturnTree node, D d);
	R visitSequence(SequenceTree node, D d);
	R visitStringLiteral(StringLiteralTree node, D d);
	R visitSuper(SuperExpressionTree node, D d);
	R visitSwitch(SwitchTree node, D d);
	R visitTemplateLiteral(TemplateLiteralTree node, D d);
	R visitThis(ThisExpressionTree node, D d);
	R visitThrow(ThrowTree node, D d);
	R visitTry(TryTree node, D d);
	R visitTupleType(TupleTypeTree node, D d);
	R visitUnary(UnaryTree node, D d);
	R visitUnionType(UnionTypeTree node, D d);
	R visitVariableDeclaration(VariableDeclarationTree node, D d);
	R visitVoidType(VoidTypeTree node, D d);
	R visitWhileLoop(WhileLoopTree node, D d);
	R visitWith(WithTree node, D d);
}
