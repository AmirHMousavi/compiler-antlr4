package pa3.codeGen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pa3.analysis.VariableRecord;

public class Method implements Serializable, ICodes {

	private static final long serialVersionUID = 1L;
	private List<VariableRecord> paramList;
	private List<VariableRecord> list;// // List of parameters and variables together
	private List<Instruction> instList;// Instructions List
	private List<Integer> varValues;
	private int PC = 0;// Program Counter

	/* Constructor */
	public Method() {
		paramList = new ArrayList<VariableRecord>();
		list = new ArrayList<VariableRecord>();
		instList = new ArrayList<Instruction>();
		varValues = new ArrayList<Integer>();
	}

	public int getPC() {
		return PC;
	}

	public void setPC(int pc) {
		PC = pc;
	}

	public List<Instruction> getInstList() {
		return instList;
	}

	public void setInstList(Instruction inst) {
		instList.add(inst);
	}

	public List<VariableRecord> getList() {
		return list;
	}

	public void setList(List<VariableRecord> list) {
		this.list.addAll(list);
	}

	public List<VariableRecord> getParamList() {
		return paramList;
	}

	public void setParamList(List<VariableRecord> paramList) {
		this.paramList = paramList;
	}

	public List<Integer> getVarValues() {
		return varValues;
	}

	public void setVarValues(List<Integer> varValues) {
		this.varValues = varValues;
	}

	public Instruction getInstruction(int n) {
		return instList.get(n);
	}

	public void print() {

		for (int i = 0; i < this.getInstList().size(); i++) {
			Instruction inst = this.getInstList().get(i);
			switch (inst.getCode()) {
			case ILOAD:
				System.out.println(i + "  ILOAD  #"
						+ inst.getArgument().toString());
				break;
			case ICONST:
				System.out.println(i + "  ICONST  "
						+ inst.getArgument().toString());
				break;
			case ISTORE:
				System.out.println(i + "  ISTORE  #"
						+ inst.getArgument().toString());
				break;
			case IADD:
				System.out.println(i + "  IADD");
				break;
			case ISUB:
				System.out.println(i+"  ISUB");
				break;
			case IMUL:
				System.out.println(i + "  IMUL");
				break;
			case IDIV:
				System.out.println(i+"  IDIV");
				break;
			case ILT:
				System.out.println(i + "  ILT");
				break;
			case IEQ:
				System.out.println(i+"  IEQ");
				break;
			case IAND:
				System.out.println(i + "  IAND");
				break;
			case IOR:
				System.out.println(i+"  IOR");
				break;
			case INOT:
				System.out.println(i + "  INOT");
				break;
			case GOTO:
				System.out.println(i + "  GOTO  "
						+ inst.getArgument().toString());
				break;
			case IF_FALSE:
				System.out.println(i + "  IF_FALSE  "
						+ inst.getArgument().toString());
				break;
			case INVOKEVIRTUAL:
				System.out.println(i + "  INVOKEVIRTUAL  "
						+ inst.getArgument().toString());
				break;
			case IRETURN:
				System.out.println(i + "  IRETURN");
				break;
			case PRINT:
				System.out.println(i + "  PRINT");
				break;
			case STOP:
				System.out.println(i + "  STOP");
				break;
			default:
				System.out.println("Undifiend Instruction ");
				break;
			}
		}
	}

}
