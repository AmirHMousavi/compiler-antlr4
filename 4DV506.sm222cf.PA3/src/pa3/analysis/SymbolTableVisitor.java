package pa3.analysis;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import sm222cf.grammar.MiniJavaBaseVisitor;
import sm222cf.grammar.MiniJavaParser.ClassDeclarationContext;
import sm222cf.grammar.MiniJavaParser.FieldDeclarationContext;
import sm222cf.grammar.MiniJavaParser.IdentifierContext;
import sm222cf.grammar.MiniJavaParser.IdentifierExpressionContext;
import sm222cf.grammar.MiniJavaParser.LocalDeclarationContext;
import sm222cf.grammar.MiniJavaParser.MainClassContext;
import sm222cf.grammar.MiniJavaParser.MainMethodContext;
import sm222cf.grammar.MiniJavaParser.MethodBodyContext;
import sm222cf.grammar.MiniJavaParser.MethodDeclarationContext;
import sm222cf.grammar.MiniJavaParser.NestedStatementContext;
import sm222cf.grammar.MiniJavaParser.ParameterContext;
import sm222cf.grammar.MiniJavaParser.ParameterListContext;
import sm222cf.grammar.MiniJavaParser.ProgramContext;
import sm222cf.grammar.MiniJavaParser.StatementContext;
import sm222cf.grammar.MiniJavaParser.TypeContext;

@SuppressWarnings("rawtypes")
public class SymbolTableVisitor extends MiniJavaBaseVisitor {
	SymbolTable symbolTable;
	ClassRecord currentClass;
	MethodRecord currentMethod;
	boolean errorFlag;

	public SymbolTableVisitor() {
		super();
		this.symbolTable = new SymbolTable();
		this.currentClass = null;
		this.currentMethod = null;
		this.errorFlag = false;
	}

	public boolean getErrorFlag() {
		return this.errorFlag;
	}

	// program:mainClass classDeclaration*;
	@Override
	public Object visitProgram(ProgramContext ctx) {
		int i = 0;
		int n = ctx.getChildCount();
		visitMainClass((MainClassContext) ctx.getChild(i++));
		for (; i < n; i++)
			visitClassDeclaration((ClassDeclarationContext) ctx.getChild(i));
		return symbolTable;
	}

	// mainClass:'class' identifier '{' mainMethod '}';
	@Override
	public Object visitMainClass(MainClassContext ctx) {
		int i = 0;
		i++; // skip 'CLASS'
		String cName = ctx.getChild(i++).getText(); // CLASS NAME

		currentClass = new ClassRecord(cName, cName);
		symbolTable.put(cName, currentClass);// Place the record of the class
												// into the current scope
		symbolTable.enterScope();// ENTER NEW SCOPE FOR CLASS STUFF
		symbolTable.setCurrentClass(currentClass);// SET THE NAME OF THE NEW
													// which is current now
													// SCOPE
		i++;// skip '{'
		visitMainMethod((MainMethodContext) ctx.getChild(i++));
		i++;// skip '}'
		symbolTable.exitScope();
		return null;
	}

	// classDeclaration:'class' identifier '{' fieldDeclaration*
	// methodDeclaration* '}';
	@Override
	public Object visitClassDeclaration(ClassDeclarationContext ctx) {
		int i = 0;
		int n = ctx.getChildCount();

		i++;// skip 'CLASS'
		String cName = ctx.getChild(i++).getText();// NAME OF CLASS
		currentClass = new ClassRecord(cName, cName);
		if (symbolTable.lookup(cName) != null) {
			errorFlag = true;
			System.err.println("[Duplicated]: Class name \"" + cName
					+ "\" already defined");
		} else {
			symbolTable.put(cName, currentClass);
			symbolTable.enterScope();
			symbolTable.setCurrentClass(currentClass);
		}
		i++; // skip '{'
		for (; i < n - 1; i++) {
			ParseTree child = ctx.getChild(i);
			if (child instanceof FieldDeclarationContext)
				visitFieldDeclaration((FieldDeclarationContext) child);
			else
				visitMethodDeclaration((MethodDeclarationContext) child);
		}
		i++;// skip '}'
		symbolTable.exitScope();
		return null;
	}

	// mainMethod:'public' 'static' 'void' 'main' '(' 'String' '[' ']'
	// identifier ')' '{'statement+ '}';
	@Override
	public Object visitMainMethod(MainMethodContext ctx) {
		int i = 0;
		currentMethod = new MethodRecord("main", null);
		if (symbolTable.lookup(currentClass.getId() + ".main") != null) {
			errorFlag = true;
			System.err.println("main method already defined!");
		} else {
			symbolTable.put(currentClass.getId() + ".main", currentMethod);
			currentClass.putMethod("main", currentMethod);
			i += 11; // skip 'public' 'static' 'void' 'main' '(' 'String' '['
						// ']'
						// identifier ')' '{'
			symbolTable.enterScope();// ENTERING NEW SCOPE TO STORE MAIN METHOD
										// STUFFS, which here are only
										// statements
			while (ctx.getChild(i) instanceof StatementContext) {
				visitStatement((StatementContext) ctx.getChild(i++));
			}

			i++;// skip '}'
			symbolTable.exitScope();
		}
		return null;
	}

	@Override
	public Object visitStatement(StatementContext ctx) {
		ParseTree child = ctx.getChild(0);
		visit(child);
		return null;
	}

	// type identifier SC;
	@Override
	public Object visitFieldDeclaration(FieldDeclarationContext ctx) {
		int i = 0;
		String type = (String) visitType((TypeContext) ctx.getChild(i++));
		String name = ctx.getChild(i++).getText();
		i++; // skip SC
		VariableRecord var = new VariableRecord(name, type);
		if (symbolTable.lookup(name) != null) {
			errorFlag = true;
			System.err.println("[Duplicated] Field Variable \"" + name
					+ "\" already defined");
		} else {
			symbolTable.put(name, var);
			currentClass.putField(name, var);// Store field variable in class's
												// fieldList
		}
		return null;
	}

	// type identifier SC;
	@Override
	public Object visitLocalDeclaration(LocalDeclarationContext ctx) {
		int i = 0;
		String type = (String) visitType((TypeContext) ctx.getChild(i++));
		String name = ctx.getChild(i++).getText();
		i++; // skip SC
		VariableRecord var = new VariableRecord(name, type);
		if (symbolTable.lookup(name) != null) {
			errorFlag = true;
			System.err.println("[Duplicated] Field Variable \"" + name
					+ "\" already defined");
		} else {
			symbolTable.put(name, var);
			currentMethod.putVar(var);// Store variable in method
		}
		return null;
	}

	// 'public' (type|'void') identifier '(' parameterList? ')' '{' methodBody
	// '}';
	@Override
	public Object visitMethodDeclaration(MethodDeclarationContext ctx) {
		int i = 0;
		i++; // skip 'public'
		ParseTree methodReturnType = ctx.getChild(i++);
		String returnType;
		if (methodReturnType instanceof TerminalNodeImpl) {
			returnType = null;
		} else {
			returnType = (String) visitType((TypeContext) methodReturnType);
		}
		String mName = ctx.getChild(i++).getText();
		if (currentClass.getId().equals(mName)) {
			errorFlag = true;
			System.err
					.println("The method name is the same as class name! we do not have construcotrs in MiniJava");
		}
		i++;// skip '('
		currentMethod = new MethodRecord(mName, returnType);
		if (symbolTable.lookup(currentClass.getId() + "." + mName) != null) {
			errorFlag = true;
			System.err.println("[Duplicated] Method name \"" + mName
					+ "\" already defined");
		} else {
			symbolTable.put(currentClass.getId() + "." + mName, currentMethod);
			currentClass.putMethod(mName, currentMethod);
			symbolTable.enterScope();
			symbolTable.setCurrentClass(currentClass);
			if (ctx.getChild(i) instanceof ParameterListContext) {
				visitParameterList((ParameterListContext) ctx.getChild(i++));
			}
			i += 2;// skip ')' '{'
			visitMethodBody((MethodBodyContext) ctx.getChild(i++));
			i++; // skip '}'
			symbolTable.exitScope();
		}
		return null;
	}

	// 'int' '['']'| 'boolean'| 'int' | 'char' | 'String' | identifier;
	@Override
	public Object visitType(TypeContext ctx) {

		return ctx.getText();
	}

	// parameter(',' parameter)*;
	@Override
	public Object visitParameterList(ParameterListContext ctx) {
		int n = ctx.getChildCount();
		for (int i = 0; i < n; i += 2)
			// skipping ','s
			visitParameter((ParameterContext) ctx.getChild(i));
		return null;
	}

	// parameter ==> type identifier
	@Override
	public Object visitParameter(ParameterContext ctx) {
		int i = 0;
		String type = (String) visitType((TypeContext) ctx.getChild(i++));
		String name = ctx.getChild(i++).getText();
		VariableRecord var = new VariableRecord(name, type);
		if (symbolTable.lookup(name) != null) {
			errorFlag = true;
			System.err.println("[Duplicated] parameter name \"" + name
					+ "\'\" already defined");
		} else {
			symbolTable.put(name, var);
			currentMethod.putParam(var);// Store Parameter in method
		}
		return null;
	}

	// localDeclaration* statement* (returnStatement)?;
	@Override
	public Object visitMethodBody(MethodBodyContext ctx) {
		int i = 0;
		int n = ctx.getChildCount();
		for (; i < n; i++) {
			ParseTree child = ctx.getChild(i);
			// just visit whatever it is :)
			visit(child);
		}
		return null;
	}

	@Override
	public Object visitNestedStatement(NestedStatementContext ctx) {
		int i = 0;
		int n = ctx.getChildCount();
		i++;
		for (; i < n - 1; i++) {
			if (ctx.getChild(i) instanceof StatementContext)
				visitStatement((StatementContext) ctx.getChild(i));
		}
		return null;
	}

	@Override
	public Object visitIdentifier(IdentifierContext ctx) {
		return ctx.getText();
	}
	
	@Override
	public Object visitIdentifierExpression(IdentifierExpressionContext ctx) {
		if(ctx.getChildCount()>1) return ctx.getChild(1).getText();
		return ctx.getChild(0).getText();
		
	}

}
