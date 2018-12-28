package pa3.analysis;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import sm222cf.grammar.MiniJavaBaseVisitor;

@SuppressWarnings("rawtypes")
public class PrintVisitor extends MiniJavaBaseVisitor {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public Object visitMiniJava(ParseTree tree) {
		return visitAllChildren(tree);

	}

	private ParseTree visitAllChildren(ParseTree node) {
		int index = node.getClass().getName().indexOf('$');
		// print name of the current node
		System.out.println(ANSI_GREEN
				+ node.getClass().getName().substring(index + 1) + " has "
				+ node.getChildCount() + " children. " + ANSI_RESET);
		// print name of the children of current node
		for (int i = 0; i < node.getChildCount(); i++) {
			ParseTree child = node.getChild(i);
			if (child instanceof TerminalNodeImpl) {
				System.out.println(ANSI_BLUE + "\t [child " + (i + 1)
						+ "]  -> " + visitTerminalNode((TerminalNode) child)
						+ ANSI_RESET);
			} else {
				System.out.println(ANSI_CYAN
						+ "\t [child "
						+ (i + 1)
						+ "]  -> "
						+ node.getChild(i).getClass().getName()
								.substring(index + 1) + ANSI_RESET);
			}
		}

		for (int i = 0; i < node.getChildCount(); i++) {
			ParseTree child = node.getChild(i);
			if (child instanceof TerminalNode) {
				visitTerminalNode((TerminalNode) child);
			} else {
				visitAllChildren(child);
			}

		}
		return node;
	}

	private String visitTerminalNode(TerminalNode child) {
		int index = child.getClass().getName().lastIndexOf('.');
		return ANSI_YELLOW + "\t \t"
				+ child.getClass().getName().substring(index + 1) + " : "
				+ "\'" + child.getText() + "\'" + ANSI_RESET;

	}

}
