package pa3.codeGen;

import java.io.IOException;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;

import pa3.analysis.SymbolTable;
import pa3.analysis.SymbolTableVisitor;
import pa3.analysis.TypeCheckVisitor;
import sm222cf.grammar.MiniJavaLexer;
import sm222cf.grammar.MiniJavaParser;

public class CodeGen {

	public static void main(String[] args) {

		// File file=new File("./testClasses/binarysearch.java");
		CharStream input = null;
		String fileName = "";
		if (args.length > 0) {
			try {
				input = CharStreams.fromFileName(args[0]);
				fileName = args[0].substring(0, args[0].lastIndexOf('.'));
			} catch (IOException e) {
				System.err.println("THE GIVEN FILE PATH IS WRONG!");
				System.err.println("IF EXECUTING THE JAR, CHECK YOUR COMMAND "
						+ " java -jar CodeGen.jar <filePath> \n"
						+ "OTHERWISE CHECK THE MAIN METHOD IN CodeGen.java");
				return;
			}
		} else {

			try {
				String file = "./tinyjava_samples/FacFib.java";
				input = CharStreams.fromFileName(file);
				fileName = file.substring(0, file.lastIndexOf('.'));

			} catch (IOException e) {
				System.err.println("THE GIVEN FILE PATH IS WRONG!!");
				System.err.println("IF EXECUTING THE JAR, CHECK YOUR COMMAND "
						+ " java -jar CodeGen.jar <filePath> \n"
						+ "OTHERWISE CHECK THE MAIN METHOD IN CodeGen.java");
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
		// PrintVisitor pv = new PrintVisitor();
		// pv.visitMiniJava(tree);

		// --------SymbolTableVisitor--------
		SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor();
		SymbolTable visitedST = (SymbolTable) symbolTableVisitor.visit(tree);
		if (symbolTableVisitor.getErrorFlag()) {
			System.err
					.println("THE PROGRAM COTAINS ERRORS! \n CHECK CONSOLE AND PARSE TREE WINDOW FOR MORE INFO!");
		} else {
			visitedST.printTable();
			visitedST.resetTable();

			// ------TypeCheckVisitor
			TypeCheckVisitor tcv = new TypeCheckVisitor(visitedST);
			tcv.visit(tree);
			if (tcv.getErrorCount() > 0) {
				System.err.println("Program Contains " + tcv.getErrorCount()
						+ " Type Errors!");
				System.err.println("The bytecode cannot be generated!");
			} else {
				visitedST.resetTable();
				CodeGenVisitor cgv = new CodeGenVisitor(visitedST);
				cgv.visit(tree);
				System.out.println("\n\t PRINTING ICODEs");
				cgv.getClassfile().print();
				cgv.writeToFile(fileName);

			}
		}

	}

}
