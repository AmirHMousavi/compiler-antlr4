
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;

import sm222cf.grammar.MiniJavaLexer;
import sm222cf.grammar.MiniJavaParser;



public class GrammarTest{

	static ArrayList<String> files;
	
	public static void listfiles(String directoryName, ArrayList<String> files) {
		File directory = new File(directoryName);

		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				files.add(file.getPath());
			} else if (file.isDirectory()) {
				listfiles(file.getAbsolutePath(), files);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		files = new ArrayList<String>();
		listfiles("./testFiles", files);
		
		for (String f : files) {
			System.out.println("Reading test program from: " + f);
			
//			//******single file
//			String testProgram="./testFiles/testProgram.java";
//			//******
			
			CharStream input =null;
			try {
				input = CharStreams.fromFileName(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Parse input program
			MiniJavaLexer lexer=new MiniJavaLexer(input);
			MiniJavaParser parser = new MiniJavaParser(new BufferedTokenStream(lexer));
			ParseTree tree=parser.program();

			// Display tree
			Trees.inspect(tree, parser);


			System.out.println("Done!");
		}
	}
	

}