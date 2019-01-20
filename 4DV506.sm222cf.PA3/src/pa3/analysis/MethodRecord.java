package pa3.analysis;

import java.util.ArrayList;
import java.util.List;

public class MethodRecord extends Record {

	private static final long serialVersionUID = 1L;
	private List<VariableRecord> paramList;
	private List<VariableRecord> varList;
	private List<VariableRecord> list;

	public MethodRecord(String id, String type) {
		super(id, type);
		paramList=new ArrayList<VariableRecord>();
		varList=new ArrayList<VariableRecord>();
		list= new ArrayList<VariableRecord>();
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

	public List<VariableRecord> getList() {
		return list;
	}

	public void setList(List<VariableRecord> list) {
		this.list.addAll(list);
	}

}
