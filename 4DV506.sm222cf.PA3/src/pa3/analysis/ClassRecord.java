package pa3.analysis;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassRecord extends Record {
	private Map<String, MethodRecord> methodList;
	private Map<String, VariableRecord> fieldList;

	/* Constructor */
	public ClassRecord(String name,String type) {
		super(name, type);
		methodList = new LinkedHashMap<String, MethodRecord>();
		fieldList = new LinkedHashMap<String, VariableRecord>();
	}

	public Map<String, VariableRecord> getFieldList() {
		return fieldList;
	}

	public void setFieldList(Map<String, VariableRecord> fieldList) {
		this.fieldList = fieldList;
	}

	public void putField(String fName, VariableRecord field) {
		fieldList.put(fName, field);
	}

	public void putMethod(String mName, MethodRecord currentMethod) {
		methodList.put(mName, currentMethod);
	}

	public Map<String, MethodRecord> getMethodList() {
		return methodList;
	}

	public void setMethodList(Map<String, MethodRecord> methodList) {
		this.methodList = methodList;
	}


}
