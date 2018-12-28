package pa3.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scope {

	private int next = 0; // Next child to visit
	private Scope parent; // Parent scope
	private List<Scope> children = new ArrayList<Scope>(); // Children scopes
	private Map<String, Record> records = new HashMap<String, Record>(); // ID
																			// to
																			// Record
																			// map
	private String name;
	private String type;
	private Record containingClass;

	public Scope(Object object) {
		children = new ArrayList<Scope>();
		records = new HashMap<String, Record>();
		if (object != null)
			parent = (Scope) object;
	}

	public Scope nextChild() {
		// Creates new child on demand
		Scope nextC;
		if (next >= children.size()) {
			// Child does not exist
			nextC = new Scope(this); // ==> create new Scope
			children.add(nextC);
		} else {
			// Child exists
			nextC = (Scope) children.get(next); // ==> visit child
		}
		next++;
		return nextC;
	}

	public Scope getParent() {
		return parent;
	}

	public void put(String key, Record item) {
		records.put(key, item);
	}

	public Record lookup(String key) {
		if (records.containsKey(key)) // Check if in current scope
			return records.get(key);
		else { // Move to enclosing/parent scope
			if (parent == null)
				return null; // Identifier not in table
			else
				return parent.lookup(key); // Delegate request to enclosing
											// scope
		}
	}

	public void printScope() {
		for (Map.Entry<String, Record> entry : records.entrySet()) {
			String key = entry.getKey();
			Record value = entry.getValue();
			String recordClss = value.getClass().getSimpleName();
			System.out.printf("%" + 20 + "s %" + 20 + "s %" + 30 + "s %n", key,
					value.getType(), recordClss);
		}
		for (Scope scope : children) {
			scope.printScope();
		}

	}

	public void resetScope() {// Must be called after each traversal
		next = 0;
		for (int i = 0; i < children.size(); i++)
			((Scope) children.get(i)).resetScope();

	}

	public void setContainingClass(Record record) {
		containingClass = record;
	}

	public Record getContainingClass() {
		return containingClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
