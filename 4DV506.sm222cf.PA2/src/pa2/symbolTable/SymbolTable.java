package pa2.symbolTable;

public interface SymbolTable {

	public void enterScope();

	public void exitScope();

	public void put(Symbol key, Record item);

	public Record lookup(Symbol key);

	public void printTable(); // Used during development
}
