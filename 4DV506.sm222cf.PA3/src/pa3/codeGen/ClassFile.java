package pa3.codeGen;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassFile implements Serializable, ICodes {

	private static final long serialVersionUID = 1L;
	private Map<String, Method> methods;
	private Method mainMethod;

	/* Constructor */
	public ClassFile() {
		methods = new LinkedHashMap<String, Method>();
		mainMethod = new Method();
	}

	public Method getMainMethod() {
		return mainMethod;
	}

	public void setMainMethod(Method method) {
		mainMethod = method;
	}

	public Map<String, Method> getMethods() {
		return methods;
	}

	public void addMethod(String name, Method method) {
		methods.put(name, method);
	}

	public void print() {
		for (Map.Entry<String, Method> method : methods.entrySet()) {
			System.out.println("\nMETHOD " + method.getKey());
			for (int j = 0; j < method.getValue().getList().size(); j++) {
				System.out.print(" #" + j + "="
						+ method.getValue().getList().get(j).getId() + " ");
			}
			System.out.println();
			method.getValue().print();
		}
	}

}
