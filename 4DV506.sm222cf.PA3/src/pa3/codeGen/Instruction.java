package pa3.codeGen;

import java.io.Serializable;

public class Instruction implements Serializable {

	private static final long serialVersionUID = 1L;
	private int code;
	private Object argument;

	public Instruction(int c, Object arg) {
		code = c;
		argument = arg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getArgument() {
		return argument;
	}

	public void setArgument(Object arg) {
		argument = arg;
	}
}
