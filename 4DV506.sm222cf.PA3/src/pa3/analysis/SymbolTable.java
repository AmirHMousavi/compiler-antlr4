package pa3.analysis;

public class SymbolTable {

//	private int level;
	private Scope root; // Root of scope tree
	private Scope current;
	
	public SymbolTable() {
		root = new Scope(null);
		current = root;
//		level=0;
	}

	public void enterScope() {
//		System.out.println("ENTERING SCOPE LEVEL: "+(++level));
		current = current.nextChild();
		
	}

	public void exitScope() {
//		System.out.println("EXITING FROM SCOPE LEVEL: "+level--);
		current = current.getParent();
	}

	public void put(String key, Record item) {
		current.put(key, item);
	}

	public Record lookup(String key) {
		return current.lookup(key);
	}

	public void printTable() {
		System.err.println("\n\n\t\tPrinting the Symbol Table:\n");
		System.out.format("+----------------+---------------------+----------------------------+%n");
		System.out.format("|      ID        |         TYPE        |        SOPEC/RECORD        |%n");
		System.out.format("+----------------+---------------------+----------------------------+%n");
		root.printScope();
		System.out.format("+----------------+---------------------+----------------------------+%n");
	}

	public void resetTable() {
		// root.resetScope();
		current = root;
		root.resetScope();
	}

	public void setCurrentClass(Record cRec) {
		current.setContainingClass(cRec);
	}

	public Record getCurrentClass() {
		return current.getContainingClass();
	}
	
	public Scope getCurrent() {
		return current;
	}

	public void setCurrent(Scope current) {
		this.current = current;
	}
}
