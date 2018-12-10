// Generated from MiniJava.g4 by ANTLR 4.7.1
package sm222cf.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniJavaParser}.
 */
public interface MiniJavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MiniJavaParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MiniJavaParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void enterMainClass(MiniJavaParser.MainClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#mainClass}.
	 * @param ctx the parse tree
	 */
	void exitMainClass(MiniJavaParser.MainClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#mainMethod}.
	 * @param ctx the parse tree
	 */
	void enterMainMethod(MiniJavaParser.MainMethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#mainMethod}.
	 * @param ctx the parse tree
	 */
	void exitMainMethod(MiniJavaParser.MainMethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(MiniJavaParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(MiniJavaParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(MiniJavaParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(MiniJavaParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(MiniJavaParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#localDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalDeclaration(MiniJavaParser.LocalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#localDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalDeclaration(MiniJavaParser.LocalDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(MiniJavaParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MiniJavaParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MiniJavaParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(MiniJavaParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(MiniJavaParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MiniJavaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MiniJavaParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(MiniJavaParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(MiniJavaParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MiniJavaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MiniJavaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStatement(MiniJavaParser.DoWhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStatement(MiniJavaParser.DoWhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(MiniJavaParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(MiniJavaParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(MiniJavaParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(MiniJavaParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#arrayAssignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterArrayAssignmentStatement(MiniJavaParser.ArrayAssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#arrayAssignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitArrayAssignmentStatement(MiniJavaParser.ArrayAssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#variableAssignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssignmentStatement(MiniJavaParser.VariableAssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#variableAssignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssignmentStatement(MiniJavaParser.VariableAssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(MiniJavaParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(MiniJavaParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MiniJavaParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MiniJavaParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#ifElseStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfElseStatement(MiniJavaParser.IfElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#ifElseStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfElseStatement(MiniJavaParser.IfElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#nestedStatement}.
	 * @param ctx the parse tree
	 */
	void enterNestedStatement(MiniJavaParser.NestedStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#nestedStatement}.
	 * @param ctx the parse tree
	 */
	void exitNestedStatement(MiniJavaParser.NestedStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MiniJavaParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MiniJavaParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#methodCallStatement}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallStatement(MiniJavaParser.MethodCallStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#methodCallStatement}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallStatement(MiniJavaParser.MethodCallStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectInstantiationExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterObjectInstantiationExpression(MiniJavaParser.ObjectInstantiationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectInstantiationExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitObjectInstantiationExpression(MiniJavaParser.ObjectInstantiationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code greaterthanExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGreaterthanExpression(MiniJavaParser.GreaterthanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code greaterthanExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGreaterthanExpression(MiniJavaParser.GreaterthanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayInstantiationExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayInstantiationExpression(MiniJavaParser.ArrayInstantiationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayInstantiationExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayInstantiationExpression(MiniJavaParser.ArrayInstantiationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(MiniJavaParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(MiniJavaParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodCallExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallExpression(MiniJavaParser.MethodCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodCallExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallExpression(MiniJavaParser.MethodCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(MiniJavaParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(MiniJavaParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDivExpression(MiniJavaParser.DivExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDivExpression(MiniJavaParser.DivExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(MiniJavaParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(MiniJavaParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolLitExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolLitExpression(MiniJavaParser.BoolLitExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolLitExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolLitExpression(MiniJavaParser.BoolLitExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(MiniJavaParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(MiniJavaParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(MiniJavaParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(MiniJavaParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayAccessExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessExpression(MiniJavaParser.ArrayAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayAccessExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessExpression(MiniJavaParser.ArrayAccessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStringExpression(MiniJavaParser.StringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStringExpression(MiniJavaParser.StringExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpression(MiniJavaParser.AddExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpression(MiniJavaParser.AddExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fieldAccessExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFieldAccessExpression(MiniJavaParser.FieldAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fieldAccessExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFieldAccessExpression(MiniJavaParser.FieldAccessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisExpression(MiniJavaParser.ThisExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisExpression(MiniJavaParser.ThisExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lessThanExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLessThanExpression(MiniJavaParser.LessThanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lessThanExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLessThanExpression(MiniJavaParser.LessThanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(MiniJavaParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalityExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(MiniJavaParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dotlengthExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDotlengthExpression(MiniJavaParser.DotlengthExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dotlengthExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDotlengthExpression(MiniJavaParser.DotlengthExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code characterExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCharacterExpression(MiniJavaParser.CharacterExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code characterExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCharacterExpression(MiniJavaParser.CharacterExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerLitExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLitExpression(MiniJavaParser.IntegerLitExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerLitExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLitExpression(MiniJavaParser.IntegerLitExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubExpression(MiniJavaParser.SubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubExpression(MiniJavaParser.SubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dotcharatExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDotcharatExpression(MiniJavaParser.DotcharatExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dotcharatExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDotcharatExpression(MiniJavaParser.DotcharatExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulExpression(MiniJavaParser.MulExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link MiniJavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulExpression(MiniJavaParser.MulExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJavaParser#methodCallParams}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallParams(MiniJavaParser.MethodCallParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJavaParser#methodCallParams}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallParams(MiniJavaParser.MethodCallParamsContext ctx);
}