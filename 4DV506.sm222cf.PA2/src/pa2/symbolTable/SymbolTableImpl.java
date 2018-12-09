package pa2.symbolTable;

public class SymbolTableImpl implements SymbolTable {

	private Scope root;
	private Scope current;
	
	

	public SymbolTableImpl() {
		super();
		this.root = new Scope(null);
		this.current = root;
	}

	@Override
	public void enterScope() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitScope() {
		// TODO Auto-generated method stub

	}

	@Override
	public void put(Symbol key, Record item) {
		// TODO Auto-generated method stub

	}

	@Override
	public Record lookup(Symbol key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printTable() {
		// TODO Auto-generated method stub

	}

}
