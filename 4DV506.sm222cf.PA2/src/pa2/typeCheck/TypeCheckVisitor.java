package pa2.typeCheck;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import pa2.symbolTable.ClassRecord;
import pa2.symbolTable.MethodRecord;
import pa2.symbolTable.SymbolTable;
import pa2.symbolTable.VariableRecord;
import sm222cf.grammar.MiniJavaBaseVisitor;
import sm222cf.grammar.MiniJavaParser.AddExpressionContext;
import sm222cf.grammar.MiniJavaParser.AndExpressionContext;
import sm222cf.grammar.MiniJavaParser.ArrayAccessExpressionContext;
import sm222cf.grammar.MiniJavaParser.ArrayInstantiationExpressionContext;
import sm222cf.grammar.MiniJavaParser.BoolLitExpressionContext;
import sm222cf.grammar.MiniJavaParser.CharacterExpressionContext;
import sm222cf.grammar.MiniJavaParser.ClassDeclarationContext;
import sm222cf.grammar.MiniJavaParser.DivExpressionContext;
import sm222cf.grammar.MiniJavaParser.DotcharatExpressionContext;
import sm222cf.grammar.MiniJavaParser.DotlengthExpressionContext;
import sm222cf.grammar.MiniJavaParser.EqualityExpressionContext;
import sm222cf.grammar.MiniJavaParser.FieldDeclarationContext;
import sm222cf.grammar.MiniJavaParser.GreaterthanExpressionContext;
import sm222cf.grammar.MiniJavaParser.IdentifierContext;
import sm222cf.grammar.MiniJavaParser.IdentifierExpressionContext;
import sm222cf.grammar.MiniJavaParser.IntegerLitExpressionContext;
import sm222cf.grammar.MiniJavaParser.LessThanExpressionContext;
import sm222cf.grammar.MiniJavaParser.LocalDeclarationContext;
import sm222cf.grammar.MiniJavaParser.MainClassContext;
import sm222cf.grammar.MiniJavaParser.MainMethodContext;
import sm222cf.grammar.MiniJavaParser.MethodBodyContext;
import sm222cf.grammar.MiniJavaParser.MethodCallExpressionContext;
import sm222cf.grammar.MiniJavaParser.MethodCallParamsContext;
import sm222cf.grammar.MiniJavaParser.MethodDeclarationContext;
import sm222cf.grammar.MiniJavaParser.MulExpressionContext;
import sm222cf.grammar.MiniJavaParser.NotExpressionContext;
import sm222cf.grammar.MiniJavaParser.ObjectInstantiationExpressionContext;
import sm222cf.grammar.MiniJavaParser.OrExpressionContext;
import sm222cf.grammar.MiniJavaParser.ParameterListContext;
import sm222cf.grammar.MiniJavaParser.PrintStatementContext;
import sm222cf.grammar.MiniJavaParser.ProgramContext;
import sm222cf.grammar.MiniJavaParser.ReturnStatementContext;
import sm222cf.grammar.MiniJavaParser.StatementContext;
import sm222cf.grammar.MiniJavaParser.StringExpressionContext;
import sm222cf.grammar.MiniJavaParser.SubExpressionContext;
import sm222cf.grammar.MiniJavaParser.TypeContext;
import sm222cf.grammar.MiniJavaParser.WhileStatementContext;

@SuppressWarnings("rawtypes")
public class TypeCheckVisitor extends MiniJavaBaseVisitor {
	SymbolTable symbolTable;
	int errorCount;

	public TypeCheckVisitor(SymbolTable table) {
		this.symbolTable = table;
		errorCount = 0;
	}

	// program:mainClass classDeclaration*;
	@Override
	public Object visitProgram(ProgramContext ctx) {
		int i = 0;
		int n = ctx.getChildCount();
		visitMainClass((MainClassContext) ctx.getChild(i++));
		for (; i < n; i++)
			visitClassDeclaration((ClassDeclarationContext) ctx.getChild(i));
		return null;
	}

	// mainClass:'class' identifier '{' mainMethod '}';
	@Override
	public Object visitMainClass(MainClassContext ctx) {
		symbolTable.enterScope();
		visit(ctx.getChild(3));
		symbolTable.exitScope();
		return null;
	}

	// classDeclaration:'class' identifier '{' fieldDeclaration*
	// methodDeclaration* '}';
	@Override
	public Object visitClassDeclaration(ClassDeclarationContext ctx) {
		int i = 3;
		int n = ctx.getChildCount();
		symbolTable.enterScope();
		for (; i < n - 1; i++) {
			ParseTree child = ctx.getChild(i);
			if (child instanceof FieldDeclarationContext)
				visitFieldDeclaration((FieldDeclarationContext) child);
			else
				visitMethodDeclaration((MethodDeclarationContext) child);
		}
		symbolTable.exitScope();
		return null;
	}

	// mainMethod:'public' 'static' 'void' 'main' '(' 'String' '[' ']'
	// identifier ')' '{'statement+ '}';
	@Override
	public Object visitMainMethod(MainMethodContext ctx) {
		int i = 11;
		symbolTable.enterScope();
		while (ctx.getChild(i) instanceof StatementContext) {
			visit(ctx.getChild(i++));
		}
		symbolTable.exitScope();
		return null;
	}

	// 'System.out.println' LP(expression) RP SC;
	@Override
	public Object visitPrintStatement(PrintStatementContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine() + "]Wrong type in Print Expression";
		String type = (String) visit(ctx.getChild(2));
		if (!(type.equals("int") || type.equals("String") || type
				.equals("char"))) {
			System.err.println(errMsg);
			errorCount++;
		}
		return null;

	}

	@Override
	// 'while' LP expression RP statement;
	public Object visitWhileStatement(WhileStatementContext ctx) {
		String errMsg = "[err#"
				+ errorCount
				+ " - @line:"
				+ ctx.getStart().getLine()
				+ "]Wrong type in WhileStmt Condition. It should be \" boolean \" ";
		String expressionType = (String) visit(ctx.getChild(2));
		if (!expressionType.equals("boolean")) {
			System.err.println(errMsg);
			errorCount++;
			return null;
		}
		visit(ctx.getChild(4));
		return null;
	}

	@Override
	// type identifier SC;
	public Object visitFieldDeclaration(FieldDeclarationContext ctx) {
		return super.visitFieldDeclaration(ctx);
	}

	@Override
	// methodDeclaration: public (type|'void') identifier '(' parameterList? ')'
	// '{'
	// methodBody '}';
	public Object visitMethodDeclaration(MethodDeclarationContext ctx) {
		int n = ctx.getChildCount();
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine() + "] ";
		int i = 1;
		ParseTree methodReturnType = ctx.getChild(i++);
		String returnType;
		if (methodReturnType instanceof TerminalNodeImpl) {
			returnType = null;
		} else {
			returnType = (String) visitType((TypeContext) methodReturnType);
		}
		i += 2; // escape 'identifier' '('
		symbolTable.enterScope();
		if (ctx.getChild(i) instanceof ParameterListContext) {
			visitParameterList((ParameterListContext) ctx.getChild(i++));
		}
		i += 2; // escape ')' '{'
		String actualReturn = (String) visitMethodBody((MethodBodyContext) ctx
				.getChild(i++));
		if (returnType == null && actualReturn == null) {
			// we cannot use '.equals'
			symbolTable.exitScope();
			return null;

		}
		if (returnType == null || actualReturn == null) {
			if (returnType == null && actualReturn != null) {
				System.err.println(errMsg+"\'void\' method \""+ ctx.getChild(2).getText()+"\" cannot have return statement!");
				errorCount++;
				symbolTable.exitScope();
				return null;
			}
			if(actualReturn==null && returnType!=null){
				System.err.println(errMsg+actualReturn+" method \""+ ctx.getChild(2).getText()+"\" should have return statement!");
				errorCount++;
				symbolTable.exitScope();
				return null;
			}
			
		}
		if (!returnType.equals(actualReturn)) {
			System.err
					.println(errMsg+"different declared type \'"+returnType+ "\' and return type \'"+ actualReturn+"\' in Method: "
							+ ctx.getChild(2).getText());
			errorCount++;
		}
		symbolTable.exitScope();
		return null;
	}

	@Override
	public Object visitParameterList(ParameterListContext ctx) {
		// TODO Auto-generated method stub
		return super.visitParameterList(ctx);
	}

	@Override
	public Object visitType(TypeContext ctx) {
		return ctx.getChild(0).getText();
	}

	// methodBody:localDeclaration* statement* (returnStatement)?;
	@Override
	public Object visitMethodBody(MethodBodyContext ctx) {
		int n = ctx.getChildCount();
		int i = 0;
		while (ctx.getChild(i) instanceof LocalDeclarationContext) {
			visitLocalDeclaration((LocalDeclarationContext) ctx.getChild(i));
			i++;
		}
		while (ctx.getChild(i) instanceof StatementContext) {
			if (ctx.getChild(i).getChild(0) instanceof ReturnStatementContext) {
				return visitReturnStatement((ReturnStatementContext) ctx
						.getChild(i).getChild(0));
			} else
				visitStatement((StatementContext) ctx.getChild(i));
			i++;
		}

		return null;
	}

	// returnStatement: 'return' expression SC ;
	@Override
	public Object visitReturnStatement(ReturnStatementContext ctx) {
		return visit(ctx.getChild(1));
	}

	@Override
	public Object visitIdentifier(IdentifierContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine() + "]Undifined ID: ";
		if (symbolTable.lookup(ctx.getText()) == null) {
			System.err.println(errMsg + ctx.getText());
			errorCount++;
			return null;
		}
		return symbolTable.lookup(ctx.getText()).getType();
	}

	@Override
	public Object visitIdentifierExpression(IdentifierExpressionContext ctx) {
		if (ctx.getChildCount() > 1) {
			return visitIdentifier((IdentifierContext) ctx.getChild(1));
		}
		return visitIdentifier((IdentifierContext) ctx.getChild(0));
	}

	// 'new' 'int' LSB expression RSB
	@Override
	public Object visitArrayInstantiationExpression(
			ArrayInstantiationExpressionContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in Array instantiation. ";
		String type = (String) visitType((TypeContext) ctx.getChild(1));
		if (type != null) {
			if (type.equals("int"))
				return "int[]";
		}
		System.err.println(errMsg
				+ "in MiniJava only \'Integer\' arrays are acceptable");
		errorCount++;
		return null;
	}

	// 'new' identifier '(' ')'
	@Override
	public Object visitObjectInstantiationExpression(
			ObjectInstantiationExpressionContext ctx) {
		String type = (String) visitIdentifier((IdentifierContext) ctx
				.getChild(1));
		return type;
	}

	// expression '.length'
	@Override
	public Object visitDotlengthExpression(DotlengthExpressionContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in Lenght reference ";
		String type = (String) visit(ctx.getChild(0));
		if (type != null) {
			if (type.equals("int[]") || type.equals("String"))
				return "int";
		}
		System.err
				.println(errMsg
						+ " for "
						+ ctx.getChild(0).getText()
						+ " \".length\" is applicable on \"int[]\" and \"String\" types only");
		errorCount++;
		return null;
	}

	@Override
	// expression '.chatAt(' expression ')'
	public Object visitDotcharatExpression(DotcharatExpressionContext ctx) {
		String errMsg = "[err#"
				+ errorCount
				+ " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in charAt reference (it must be String) : ";
		String type1 = (String) visit(ctx.getChild(0));// name
		String type2 = (String) visit(ctx.getChild(2)); // index
		if (type1 == null || type2 == null) {
			System.err.println(errMsg + ctx.getChild(0).getText()
					+ " for index " + ctx.getChild(2).getText());
			errorCount++;
			return null;
		}
		if (!type1.equals("String")) {
			System.err.println(errMsg + ctx.getChild(0).getText()
					+ " for index " + ctx.getChild(2).getText());
			errorCount++;
			return null;
		}
		if (!type2.equals("int")) {
			System.err.println(errMsg + ctx.getChild(0).getText()
					+ " for index " + ctx.getChild(2).getText());
			errorCount++;
			return null;
		}
		return "char";
	}

	@Override
	public Object visitStringExpression(StringExpressionContext ctx) {
		return "String";
	}

	@Override
	public Object visitCharacterExpression(CharacterExpressionContext ctx) {
		return "char";
	}

	@Override
	public Object visitIntegerLitExpression(IntegerLitExpressionContext ctx) {
		return "int";
	}

	@Override
	public Object visitBoolLitExpression(BoolLitExpressionContext ctx) {
		return "boolean";
	}

	@Override
	// expression ('.' identifier methodCallParams)+
	public Object visitMethodCallExpression(MethodCallExpressionContext ctx) {
		int i = 0;
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine() + "] ";
		String className = (String) visit(ctx.getChild(i));
		ClassRecord classRec = (ClassRecord) symbolTable.lookup(className);
		if (classRec == null) {
			System.err.println("Undifined Class Name: \"" + className + "\"");
			errorCount++;
			return null;
		}
		MethodRecord mRec = null;
		int n = ctx.getChildCount();
		i += 2; // skip '.' after (class/method) instantiation
		for (; i < n; i++) {// we might have call chain or .length() or
							// .charAt() after method
			String mName = ctx.getChild(i).getText();
			mRec = (MethodRecord) classRec.getMethodList().get(mName);
			if (mRec == null) {// Undefined name
				System.err.println(errMsg + "Undifined Method Name: \"" + mName
						+ "\" for class " + className + "\"");
				errorCount++;
				return null;
			}
			List<VariableRecord> paramList = mRec.getParamList();
			i += 1; // after method name we have methodCallParams in shape
					// '('(expression(',' expression)*)? ')'
			ArrayList<ParseTree> methodCallParams = visitMethodCallParams((MethodCallParamsContext) ctx
					.getChild(i));
			if (paramList.size() != methodCallParams.size()) {
				System.err.println(errMsg
						+ "Wrong number of parameters in calling " + mName);
				errorCount++;
			} else {
				for (int j = 0; j < methodCallParams.size(); j++) {
					String methodCallParamType = (String) visit(methodCallParams
							.get(j));
					String actualType = paramList.get(j).getType();
					if (!actualType.equals(methodCallParamType)) {
						System.err.println(errMsg
								+ "Parameter type error in calling \"" + mName
								+ "\" parameter " + (j + 1) + " should be, "
								+ actualType + " but visited, "
								+ methodCallParamType);
						errorCount++;
					}
				}
			}
		}
		return mRec.getType();
	}

	@Override
	// '('(expression(',' expression)*)? ')'
	public ArrayList<ParseTree> visitMethodCallParams(
			MethodCallParamsContext ctx) {
		int n = ctx.getChildCount();
		ArrayList<ParseTree> children = new ArrayList<ParseTree>();
		for (int i = 0; i < n; i++) {
			ParseTree child = ctx.getChild(i);
			if (child instanceof TerminalNodeImpl
					&& (child.getText().equals("(")
							|| child.getText().equals(",") || child.getText()
							.equals(")")))
				continue;
			children.add(child);
		}
		return children;
	}

	@Override
	// expression LSB expression RSB
	public Object visitArrayAccessExpression(ArrayAccessExpressionContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in Array Read : ";
		String type1 = (String) visit(ctx.getChild(0));// name
		String type2 = (String) visit(ctx.getChild(2));// index
		if (type1 == null || type2 == null) {
			System.err.println(errMsg + ctx.getChild(0).getText()
					+ " index of " + ctx.getChild(2).getText());
			errorCount++;
			return null;
		}
		if (!type1.equals("int[]")) {
			System.err.println(errMsg + ctx.getChild(0).getText()
					+ " index of " + ctx.getChild(2).getText());
			errorCount++;
			return null;
		}
		if (!type2.equals("int")) {
			System.err.println(errMsg + ctx.getChild(0).getText()
					+ " index of " + ctx.getChild(2).getText());
			errorCount++;
			return null;
		}
		return "int";
	}

	@Override
	// expression PLUS expression
	public Object visitAddExpression(AddExpressionContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "] Incompatible type in add expression: ";
		String typeLHS = (String) visit(ctx.getChild(0));
		String typeRHS = (String) visit(ctx.getChild(2));
		if (typeLHS == null || typeRHS == null) {
			System.err.println(errMsg + ctx.getChild(0).getText() + " or "
					+ ctx.getChild(2).getText() + " Has type of null");
			errorCount++;
			return null;
		}
		if (!typeLHS.equals("int") && !typeRHS.equals("int")
				&& !typeLHS.equals("String") && !typeRHS.equals("String")) {
			System.err.println(errMsg
					+ "Addition can be done on String or int types only!");
			errorCount++;
		}
		if (typeLHS != typeRHS) {
			System.err.println(errMsg
					+ "LHS and RHS should have same type (int or String)");
			errorCount++;
			return null;
		}

		return typeLHS;
	}

	@Override
	// expression DIV expression
	public Object visitDivExpression(DivExpressionContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in division expression: ";
		String typeLHS = (String) visit(ctx.getChild(0));
		String typeRHS = (String) visit(ctx.getChild(2));
		if (typeLHS == null || typeRHS == null) {
			System.err.println(errMsg + "either " + ctx.getChild(0).getText()
					+ " or " + ctx.getChild(2).getText() + " Has type of null");
			errorCount++;
			return null;
		}
		if (!typeLHS.equals("int") || !typeRHS.equals("int")) {
			System.err.println(errMsg
					+ "division can be done on int types only!");
			errorCount++;
		}
		return typeLHS;
	}

	@Override
	// expression TIMES expression
	public Object visitMulExpression(MulExpressionContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in multiply expression: ";
		String typeLHS = (String) visit(ctx.getChild(0));
		String typeRHS = (String) visit(ctx.getChild(2));
		if (typeLHS == null || typeRHS == null) {
			System.err.println(errMsg + "either " + ctx.getChild(0).getText()
					+ " or " + ctx.getChild(2).getText() + " Has type of null");
			errorCount++;
			return null;
		}
		if (!typeLHS.equals("int") || !typeRHS.equals("int")) {
			System.err.println(errMsg
					+ "multiplication can be done on int types only!");
			errorCount++;
		}
		return typeLHS;
	}

	@Override
	public Object visitSubExpression(SubExpressionContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in subtitution expression: ";
		String typeLHS = (String) visit(ctx.getChild(0));
		String typeRHS = (String) visit(ctx.getChild(2));
		if (typeLHS == null || typeRHS == null) {
			System.err.println(errMsg + "either " + ctx.getChild(0).getText()
					+ " or " + ctx.getChild(2).getText() + " Has type of null");
			errorCount++;
			return null;
		}
		if (!typeLHS.equals("int") || !typeRHS.equals("int")) {
			System.err.println(errMsg
					+ "subtitution can be done on int types only!");
			errorCount++;
		}
		return typeLHS;
	}

	@Override
	public Object visitLessThanExpression(LessThanExpressionContext ctx) {
		int n = ctx.getChildCount();
		String typeRHS;
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in less than comparison: ";
		String typeLHS = (String) visit(ctx.getChild(0));
		if (n > 3) {
			typeRHS = (String) visit(ctx.getChild(3));
		} else {
			typeRHS = (String) visit(ctx.getChild(2));
		}
		if (typeLHS == null || typeRHS == null) {
			System.err.println(errMsg + "either " + ctx.getChild(0).getText()
					+ " or " + ctx.getChild(2).getText() + " Has type of null");
			errorCount++;
			return null;
		}
		if (!typeLHS.equals("int") || !typeRHS.equals("int")) {
			System.err.println(errMsg
					+ "<, <= operations can be done on \'int\' types only!");
			errorCount++;
			return null;
		}
		return "boolean";
	}

	@Override
	public Object visitGreaterthanExpression(GreaterthanExpressionContext ctx) {
		int n = ctx.getChildCount();
		String typeRHS;
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in greater than comparison: ";
		String typeLHS = (String) visit(ctx.getChild(0));
		if (n > 3) {
			typeRHS = (String) visit(ctx.getChild(3));
		} else {
			typeRHS = (String) visit(ctx.getChild(2));
		}
		if (typeLHS == null || typeRHS == null) {
			System.err.println(errMsg + "either " + ctx.getChild(0).getText()
					+ " or " + ctx.getChild(2).getText() + " Has type of null");
			errorCount++;
			return null;
		}
		if (!typeLHS.equals("int") || !typeRHS.equals("int")) {
			System.err.println(errMsg
					+ ">, >= operations can be done on \'int\' types only!");
			errorCount++;
			return null;
		}
		return "boolean";
	}

	@Override
	public Object visitAndExpression(AndExpressionContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in logical AND: ";
		String typeLHS = (String) visit(ctx.getChild(0));
		String typeRHS = (String) visit(ctx.getChild(2));
		if (typeLHS == null || typeRHS == null) {
			System.err.println(errMsg + "either " + ctx.getChild(0).getText()
					+ " or " + ctx.getChild(2).getText() + " Has type of null");
			errorCount++;
			return null;
		}
		if (!typeLHS.equals("boolean") || !typeRHS.equals("boolean")) {
			System.err.println(errMsg
					+ "&& operation can be done on \'boolean\' types only!");
			errorCount++;
		}
		return typeLHS;
	}

	@Override
	public Object visitEqualityExpression(EqualityExpressionContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in equality comparison: ";
		String typeLHS = (String) visit(ctx.getChild(0));
		String typeRHS = (String) visit(ctx.getChild(3));
		if (typeLHS == null || typeRHS == null) {
			System.err.println(errMsg + "either " + ctx.getChild(0).getText()
					+ " or " + ctx.getChild(2).getText() + " Has type of null");
			errorCount++;
			return null;
		}
		if (!typeLHS.equals("boolean") || !typeRHS.equals("boolean")) {
			System.err
					.println(errMsg
							+ "== , != operation can be done on \'boolean\' types only!");
			errorCount++;
		}
		return typeLHS;
	}

	@Override
	public Object visitOrExpression(OrExpressionContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in logical OR: ";
		String typeLHS = (String) visit(ctx.getChild(0));
		String typeRHS = (String) visit(ctx.getChild(2));
		if (typeLHS == null || typeRHS == null) {
			System.err.println(errMsg + "either " + ctx.getChild(0).getText()
					+ " or " + ctx.getChild(2).getText() + " Has type of null");
			errorCount++;
			return null;
		}
		if (!typeLHS.equals("boolean") || !typeRHS.equals("boolean")) {
			System.err.println(errMsg
					+ " || operation can be done on \'boolean\' types only!");
			errorCount++;
		}
		return typeLHS;
	}

	@Override
	public Object visitNotExpression(NotExpressionContext ctx) {
		String errMsg = "[err#" + errorCount + " - @line:"
				+ ctx.getStart().getLine()
				+ "]Incompatible type in logical NOT: ";
		String type = (String) visit(ctx.getChild(1));
		if (type == null) {
			System.err.println(errMsg
					+ " type of expression after \'!\' is null");
			errorCount++;
			return null;
		}
		if (!type.equals("boolean")) {
			System.err.println(errMsg
					+ " logical \'!\' can be done on \'boolean\' types only!");
		}
		return type;
	}

}
