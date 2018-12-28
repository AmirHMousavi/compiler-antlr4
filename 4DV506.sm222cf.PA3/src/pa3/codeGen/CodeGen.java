package pa3.codeGen;

import java.io.IOException;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;

import pa3.analysis.PrintVisitor;
import pa3.analysis.SymbolTable;
import pa3.analysis.SymbolTableVisitor;
import pa3.analysis.TypeCheckVisitor;
import sm222cf.grammar.MiniJavaLexer;
import sm222cf.grammar.MiniJavaParser;

public class CodeGen {
	public static final String BGSTYLE = "\u001b[47;1m";
	public static final String BOLD = "\u001b[1m";
	public static final String UNDERLINE = "\u001b[4m";
	public static final String RED = "\u001B[31m";
	public static final String YELLOW = "\u001b[33;1m";
	public static final String RESET = "\u001B[0m";
	
	public static void main(String[] args) {


		// File file=new File("./testClasses/binarysearch.java");
		CharStream input = null;
		if (args.length > 0) {
			try {
				input = CharStreams.fromFileName(args[0]);
			} catch (IOException e) {
				System.out.println(BGSTYLE + RED + BOLD + UNDERLINE
						+ "THE GIVEN FILE PATH IS WRONG!" + RESET);
				System.out.println(YELLOW + BOLD
						+ "IF EXECUTING THE JAR, CHECK YOUR COMMAND "
						+ " java -jar MJCompiler <filePath> \n"
						+ "OTHERWISE CHECK THE MAIN METHOD" + RESET);
				return;
			}
		} else {

			try {
				input = CharStreams.fromFileName("./tinyjava_samples/Sum.java");

			} catch (IOException e) {
				System.out.println(BGSTYLE + RED + BOLD + UNDERLINE
						+ "THE GIVEN FILE PATH IS WRONG!!" + RESET);
				System.out.println(YELLOW + BOLD
						+ "IF EXECUTING THE JAR, CHECK YOUR COMMAND "
						+ " java -jar MJCompiler <filePath> \n"
						+ "OTHERWISE CHECK THE MAIN METHOD" + RESET);
				return;
			}
		}
		// -----Parse Input Program----------
		MiniJavaLexer lexer = new MiniJavaLexer(input);
		MiniJavaParser parser = new MiniJavaParser(new BufferedTokenStream(
				lexer));
		ParseTree tree = parser.program();
		Trees.inspect(tree, parser);

		// ---------PrintVisitor-------------
		PrintVisitor pv = new PrintVisitor();
		pv.visitMiniJava(tree);

		// --------SymbolTableVisitor--------
		SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor();
		SymbolTable visitedST = (SymbolTable) symbolTableVisitor.visit(tree);
		if (symbolTableVisitor.getErrorFlag()) {
			System.err
					.println(BGSTYLE
							+ RED
							+ "THE PROGRAM COTAINS ERRORS! \n CHECK CONSOLE AND PARSE TREE WINDOW FOR MORE INFO!");
		} else {
			visitedST.printTable();
			visitedST.resetTable();

			// ------TypeCheckVisitor
			TypeCheckVisitor tcv = new TypeCheckVisitor(visitedST);
			tcv.visit(tree);
		}

	
	}

}
