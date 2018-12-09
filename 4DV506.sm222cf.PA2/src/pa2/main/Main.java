/**
 * practical assignment 2, Compiler Construction I, 4DV506
 * @teacher Jonas Lundberg
 * @TeacherAssistant Kostiantyn Kucher
 * @author S.Amir Mousavi (sm222cf)
 */
package pa2.main;

import java.io.IOException;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;

import pa2.pirintVisitor.PrintVisitor;
import sm222cf.grammar.MiniJavaLexer;
import sm222cf.grammar.MiniJavaParser;

public class Main {
	// Java Prefs Problem on Windows10
	// https://stackoverflow.com/questions/16428098/groovy-shell-warning-could-not-open-create-prefs-root-node
	public static void main(String[] args) {
		// File file=new File("./testClasses/binarysearch.java");
		CharStream input = null;
		try {
			input = CharStreams.fromFileName("./testFiles/simple.java");
		} catch (IOException e) {
			e.printStackTrace();
		}
		MiniJavaLexer lexer = new MiniJavaLexer(input);
		MiniJavaParser parser = new MiniJavaParser(new BufferedTokenStream(
				lexer));
		ParseTree tree = parser.program();
		Trees.inspect(tree, parser);

		// ---------PrintVisitor-------------
		PrintVisitor pv = new PrintVisitor();
		pv.visitMiniJava(tree);

		// --------SymbolTableVisitor--------

	}

}
