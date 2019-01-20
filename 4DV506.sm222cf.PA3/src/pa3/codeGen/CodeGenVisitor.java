package pa3.codeGen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import pa3.analysis.ClassRecord;
import pa3.analysis.MethodRecord;
import pa3.analysis.Record;
import pa3.analysis.SymbolTable;
import pa3.analysis.VariableRecord;
import sm222cf.grammar.MiniJavaBaseVisitor;
import sm222cf.grammar.MiniJavaParser.AddExpressionContext;
import sm222cf.grammar.MiniJavaParser.BoolLitExpressionContext;
import sm222cf.grammar.MiniJavaParser.ClassDeclarationContext;
import sm222cf.grammar.MiniJavaParser.DivExpressionContext;
import sm222cf.grammar.MiniJavaParser.FieldDeclarationContext;
import sm222cf.grammar.MiniJavaParser.IdentifierContext;
import sm222cf.grammar.MiniJavaParser.IdentifierExpressionContext;
import sm222cf.grammar.MiniJavaParser.IfElseStatementContext;
import sm222cf.grammar.MiniJavaParser.IntegerLitExpressionContext;
import sm222cf.grammar.MiniJavaParser.LessThanExpressionContext;
import sm222cf.grammar.MiniJavaParser.MainClassContext;
import sm222cf.grammar.MiniJavaParser.MainMethodContext;
import sm222cf.grammar.MiniJavaParser.MethodBodyContext;
import sm222cf.grammar.MiniJavaParser.MethodCallExpressionContext;
import sm222cf.grammar.MiniJavaParser.MethodCallParamsContext;
import sm222cf.grammar.MiniJavaParser.MethodDeclarationContext;
import sm222cf.grammar.MiniJavaParser.MulExpressionContext;
import sm222cf.grammar.MiniJavaParser.NotExpressionContext;
import sm222cf.grammar.MiniJavaParser.ObjectInstantiationExpressionContext;
import sm222cf.grammar.MiniJavaParser.PrintStatementContext;
import sm222cf.grammar.MiniJavaParser.ReturnStatementContext;
import sm222cf.grammar.MiniJavaParser.StatementContext;
import sm222cf.grammar.MiniJavaParser.SubExpressionContext;
import sm222cf.grammar.MiniJavaParser.ThisExpressionContext;
import sm222cf.grammar.MiniJavaParser.VariableAssignmentStatementContext;
import sm222cf.grammar.MiniJavaParser.WhileStatementContext;

@SuppressWarnings("rawtypes")
public class CodeGenVisitor extends MiniJavaBaseVisitor implements ICodes {

	private SymbolTable symtab; // From previous iteration
	private MethodRecord currentMethod; // See visitMethodDecl()
	private String currentClass; // See visitClassDecl()
	private ClassFile classFile; // To be saved on disk
	private int next; // instruction counter

	// -----CONSTRUCTOR-------
	public CodeGenVisitor(SymbolTable visitedST) {
		this.setSymtab(visitedST);
		this.setClassfile(new ClassFile());
		setIC(0);
	}

	private String errorMessage(ParseTree ctx) {
		return "[err - @ " + ((ParserRuleContext) ctx).getStart().getLine()
				+ ":"
				+ ((ParserRuleContext) ctx).getStop().getCharPositionInLine()
				+ "] ";

	}

	// ---- METHODS-----------
	private void addInstruction(int code, Object arg) {
		Instruction inst = new Instruction(code, arg);
		Method method = classFile.getMethods().get(
				currentClass + "." + currentMethod.getId());
		method.setInstList(inst);

	}

	// ----'.tjp' FILE WRITER-----

	public void writeToFile(String fileName) {
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		try {
			File file = new File(fileName+".tjp");
			file.getParentFile().mkdirs();
			fileOut = new FileOutputStream(file,false);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(classFile);
			objectOut.close();
		} catch (Exception e) {
			System.err.println("Error in creating \'"+fileName+".tjp\' ");
			e.printStackTrace();
		}
		
		System.out.println("\n \t \'"+fileName+".tjp\' GENERATED SUCCESSFULY.");

	}

	// ----GETTERS/SETTERS------
	public ClassFile getClassfile() {
		return classFile;
	}

	public void setClassfile(ClassFile classfile) {
		this.classFile = classfile;
	}

	public String getCurrentClass() {
		return currentClass;
	}

	public void setCurrentClass(String currentClass) {
		this.currentClass = currentClass;
	}

	public MethodRecord getCurrentMethod() {
		return currentMethod;
	}

	public void setCurrentMethod(MethodRecord currentMethod) {
		this.currentMethod = currentMethod;
	}

	public SymbolTable getSymtab() {
		return symtab;
	}

	public void setSymtab(SymbolTable symtab) {
		this.symtab = symtab;
	}

	public int getIC() {
		return next;
	}

	public void setIC(int iC) {
		next = iC;
	}

	// -------VISITORS--------

	@Override
	// 'class' identifier '{' mainMethod '}';
	public Object visitMainClass(MainClassContext ctx) {
		setCurrentClass(ctx.getChild(1).getText());
		symtab.enterScope();
		visitMainMethod((MainMethodContext) ctx.getChild(3));
		symtab.exitScope();
		addInstruction(STOP, null);
		next++;
		return null;
	}

	@Override
	// 'public' 'static' 'void' 'main' '(' 'String' ('[' ']'|'...') identifier
	// ')' '{'statement+ '}';
	public Object visitMainMethod(MainMethodContext ctx) {
		setCurrentMethod((MethodRecord) symtab.lookup(currentClass + ".main"));
		Method method = new Method();
		classFile.addMethod(currentClass + "." + currentMethod.getId(), method);
		classFile.setMainMethod(method);
		symtab.enterScope();
		for (int i = 0; i < ctx.getChildCount(); i++) {
			if (ctx.getChild(i) instanceof StatementContext) {
				visitStatement((StatementContext) ctx.getChild(i));
			}
		}
		symtab.exitScope();
		return null;
	}

	@Override
	// 'class' identifier '{' fieldDeclaration* methodDeclaration* '}';
	public Object visitClassDeclaration(ClassDeclarationContext ctx) {
		int i = 1;
		int n = ctx.getChildCount();
		currentClass = ctx.getChild(i++).getText(); // Class name
		i++;
		symtab.enterScope();
		for (; i < n - 1; i++) {
			ParseTree child = ctx.getChild(i);
			if (child instanceof FieldDeclarationContext)
				System.err.println(errorMessage(child)
						+ " In Tiny Java We should NOT have Field-Declaration");
			else
				visitMethodDeclaration((MethodDeclarationContext) child);
		}
		symtab.exitScope();
		return null;
	}

	@Override
	// 'public'? (type|'void') identifier '(' parameterList? ')' '{' methodBody
	// '}';
	public Object visitMethodDeclaration(MethodDeclarationContext ctx) {
		int i = 0;
		if (ctx.getChild(0) instanceof TerminalNodeImpl
				&& ctx.getChild(0).getText().equals("public")) {
			i++; // skip 'public'
		}
		i++; // skip type
		String mName = ctx.getChild(i++).getText(); // Method name
		setCurrentMethod((MethodRecord) symtab.lookup(currentClass + "."
				+ mName));
		currentMethod.setList(currentMethod.getParamList());
		currentMethod.setList(currentMethod.getVarList());
		/* classFile */
		Method method = new Method();
		method.setParamList(currentMethod.getParamList());
		method.setList(currentMethod.getList());
		for (int k = 0; k < method.getList().size(); k++) {
			// Initialized the varList with 0 as default value with size of the
			// number of params and vars
			method.getVarValues().add(k, 0);
		}
		classFile.addMethod(currentClass + "." + currentMethod.getId(), method);

		next = 0;
		for (int j = currentMethod.getParamList().size() - 1; j >= 0; j--) {
			// store assign data in related parameter
			addInstruction(ISTORE, j);
			next++;
		}
		symtab.enterScope(); // Enter method scope
		while (!(ctx.getChild(i) instanceof MethodBodyContext)) {
			i++;
		}
		visitMethodBody((MethodBodyContext) ctx.getChild(i));
		symtab.exitScope(); // Exit method scope
		return null;
	}

	@Override
	// expression ('.' identifier methodCallParams)
	public Object visitMethodCallExpression(MethodCallExpressionContext ctx) {
		int i = 0;
		String className = (String) visit(ctx.getChild(i++));
		ClassRecord classRec = (ClassRecord) symtab.lookup(className);
		MethodRecord mRec = null;
		int n = ctx.getChildCount();
		i++; // skip '.' after (class/method) instantiation
		for (; i < n; i++) {
			String mName = ctx.getChild(i).getText();
			mRec = (MethodRecord) classRec.getMethodList().get(mName);
			i += 1; // after method name we have methodCallParams in shape
					// '('(expression(',' expression)*)? ')'
			visitMethodCallParams((MethodCallParamsContext) ctx.getChild(i));
			addInstruction(INVOKEVIRTUAL, className + "." + mName);
			next++;
			className = mRec.getType();
		}
		return null;
	}

	@Override
	// identifier EQ expression SC;
	public Object visitVariableAssignmentStatement(
			VariableAssignmentStatementContext ctx) {
		String LHS = ctx.getChild(0).getChild(0).getText();
		VariableRecord lookup = (VariableRecord) symtab.lookup(LHS);
		visit(ctx.getChild(2));
		addInstruction(ISTORE, currentMethod.getList().indexOf(lookup));
		next++;
		return null;
	}

	@Override
	// Identifier;
	public Object visitIdentifier(IdentifierContext ctx) {
		String varName = ctx.getText();
		Record varRec = symtab.lookup(varName);
		if (varRec == null)
			return null;
		// addInstruction(ILOAD, currentMethod.getList().indexOf(varRec));
		// next++;
		return null;
	}
	
	@Override
	public Object visitThisExpression(ThisExpressionContext ctx) {
		return symtab.getCurrentClass().getId();
	}

	@Override
	public Object visitIdentifierExpression(IdentifierExpressionContext ctx) {
		String varName = ctx.getText();
		Record varRec = symtab.lookup(varName);
		if (varRec == null)
			return null;
		addInstruction(ILOAD, currentMethod.getList().indexOf(varRec));
		next++;
		return null;
	}

	@Override
	public Object visitIntegerLitExpression(IntegerLitExpressionContext ctx) {
		int value = Integer.parseInt(ctx.getText());
		addInstruction(ICONST, value);
		next++;
		return null;
	}

	@Override
	public Object visitBoolLitExpression(BoolLitExpressionContext ctx) {
		String value = ctx.getText();
		if (value.equals("true")) {
			addInstruction(ICONST, 1);
		}
		if (value.equals("false")) {
			addInstruction(ICONST, 0);
		}
		next++;
		return null;
	}

	@Override
	// 'return' expression SC ;
	public Object visitReturnStatement(ReturnStatementContext ctx) {
		visit(ctx.getChild(1));
		addInstruction(IRETURN, null);
		next++;
		return null;
	}

	@Override
	// 'while' LP expression RP statement;
	public Object visitWhileStatement(WhileStatementContext ctx) {
		int go_to = next;
		visit(ctx.getChild(2));
		int if_fale = next;
		addInstruction(IF_FALSE, null);
		next++;
		visit(ctx.getChild(4)); // Generate While-body
		addInstruction(GOTO, go_to);
		next++;
		Method method = classFile.getMethods().get(
				currentClass + "." + currentMethod.getId());
		method.getInstList().get(if_fale).setArgument(next);
		return null;
	}

	@Override
	// 'System.out.println' LP(expression) RP SC;
	public Object visitPrintStatement(PrintStatementContext ctx) {
		visit(ctx.getChild(2));
		addInstruction(PRINT, null);
		next++;
		return null;
	}
	
	

	@Override
	// 'if' LP expression RP statement ('else' statement)?;
	public Object visitIfElseStatement(IfElseStatementContext ctx) {
		visit(ctx.getChild(2)); // Generate condition
		int ifLabel = next;
		addInstruction(IF_FALSE, null);
		next++;
		visit(ctx.getChild(4)); // Generate if-body
		int gotoLabel = next;
		addInstruction(GOTO, null);
		next++;
		Method method = classFile.getMethods().get(
				currentClass + "." + currentMethod.getId());
		method.getInstList().get(ifLabel).setArgument(next);
		if (ctx.getChildCount() > 4) {
			visit(ctx.getChild(6)); // Generate else-body
			method.getInstList().get(gotoLabel).setArgument(next);
		}
		return null;
	}

	@Override
	public Object visitLessThanExpression(LessThanExpressionContext ctx) {
		int n = ctx.getChildCount();
		visit(ctx.getChild(0));
		if (n > 3) {
			visit(ctx.getChild(3));
		} else {
			visit(ctx.getChild(2));
		}
		addInstruction(ILT, null);
		next++;
		return null;
	}

	@Override
	// expression TIMES expression
	public Object visitMulExpression(MulExpressionContext ctx) {
		visit(ctx.getChild(0));
		visit(ctx.getChild(2));
		addInstruction(IMUL, null);
		next++;
		return null;
	}

	@Override
	// expression TIMES expression
	public Object visitAddExpression(AddExpressionContext ctx) {
		visit(ctx.getChild(0));
		visit(ctx.getChild(2));
		addInstruction(IADD, null);
		next++;
		return null;
	}

	@Override
	public Object visitDivExpression(DivExpressionContext ctx) {
		visit(ctx.getChild(0));
		visit(ctx.getChild(2));
		addInstruction(IDIV, null);
		next++;
		return null;
	}

	@Override
	public Object visitSubExpression(SubExpressionContext ctx) {
		visit(ctx.getChild(0));
		visit(ctx.getChild(2));
		addInstruction(ISUB, null);
		next++;
		return null;
	}

	@Override
	public Object visitNotExpression(NotExpressionContext ctx) {
		visit(ctx.getChild(1));
		addInstruction(INOT, null);
		next++;
		return null;
	}

	@Override
	// 'new' identifier '(' ')' # objectInstantiationExpression
	public Object visitObjectInstantiationExpression(
			ObjectInstantiationExpressionContext ctx) {
		return ctx.getChild(1).getText();
	}

}
