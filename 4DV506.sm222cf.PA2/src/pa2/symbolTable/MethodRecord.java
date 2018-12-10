package pa2.symbolTable;

import java.util.ArrayList;
import java.util.List;

public class MethodRecord extends Record {

	private List<VariableRecord> paramList;
	private List<VariableRecord> varList;

	public MethodRecord(String id, String type) {
		super(id, type);
		paramList=new ArrayList<VariableRecord>();
		varList=new ArrayList<VariableRecord>();
	}
	
	public void putParam(VariableRecord param){
		paramList.add(param);
	}
	
	public void putVar(VariableRecord var){
		varList.add(var);
	}

	public List<VariableRecord> getParamList() {
		return paramList;
	}

	public void setParamList(List<VariableRecord> paramList) {
		this.paramList = paramList;
	}

	public List<VariableRecord> getVarList() {
		return varList;
	}

	public void setVarList(List<VariableRecord> varList) {
		this.varList = varList;
	}

}
