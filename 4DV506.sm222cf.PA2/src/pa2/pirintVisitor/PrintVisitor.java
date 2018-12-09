package pa2.pirintVisitor;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import sm222cf.grammar.MiniJavaBaseVisitor;

@SuppressWarnings("rawtypes")
public class PrintVisitor extends MiniJavaBaseVisitor {

	public Object visitMiniJava(ParseTree tree) {
		return visitAllChildren(tree);

	}

	private ParseTree visitAllChildren(ParseTree node) {
		int index = node.getClass().getName().indexOf('$');
		// print name of the current node
		System.out.println(node.getClass().getName().substring(index + 1)
				+ " has " + node.getChildCount() + " children. ");
		// print name of the children of current node
		for (int i = 0; i < node.getChildCount(); i++) {
			ParseTree child = node.getChild(i);
			if (child instanceof TerminalNodeImpl) {
				int index1 = child.getClass().getName().lastIndexOf('.');
				System.out.println("\t [child "
						+ (i + 1)
						+ "]  -> "
						+ node.getChild(i).getClass().getName()
								.substring(index1 + 1));
			} else {
				System.out.println("\t [child "
						+ (i + 1)
						+ "]  -> "
						+ node.getChild(i).getClass().getName()
								.substring(index + 1));
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

	private void visitTerminalNode(TerminalNode child) {
		int index = child.getClass().getName().lastIndexOf('.');
		System.out.println("    "
				+ child.getClass().getName().substring(index + 1) + " : "
				+ child.getText());

	}

}
