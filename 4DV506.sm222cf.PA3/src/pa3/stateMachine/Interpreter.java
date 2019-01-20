package pa3.stateMachine;

public class Interpreter {
	public static void main(String[] args) throws Exception {
		/* Interpreting */
		//String file = args[0];
		String file = "./tinyjava_samples/Sum.tjp";
		System.out.println("\n Final Results From StackMachine:");
		StackMachine SM = new StackMachine(file);
		SM.execute();
	}
}
