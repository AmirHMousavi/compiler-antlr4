package pa3.codeGen;

public interface ICodes {

	final static int ILOAD = 1;	// 	iload n		Push integer value stored in local variable n.
	final static int ICONST = 2; // iconst v	Push integer value v.
	final static int ISTORE = 3; // istore n	Pop value v and store it in local variable n.
	final static int IADD = 4; // 	iadd		Pop value v1, Pop value v2, Push v2 + v1.
	final static int ISUB = 5; // 	isub		Pop value v1, Pop value v2, Push v2 - v1.
	final static int IMUL = 6; // 	imul		Pop value v1, Pop value v2, Push v2 * v1.
	final static int IDIV = 7; // 	idiv		Pop value v1, Pop value v2, Push v2 / v1.
	final static int ILT = 8; // 	ilt			Pop value v1, Pop value v2, Push 1 if v2 < v1 else Push 0.
	final static int IEQ = 9; // 	ieq			Pop value v1, Pop value v2, Push 1 if v1 = v2 else Push 0.
	final static int IAND = 10; // 	iand		Pop value v1, Pop value v2, Push 0 if v1 * v2 = 0 else Push 1.
	final static int IOR = 11;	// 	ior			Pop value v1, Pop value v2, Push 0 if v1 + v2 = 0 else Push 1.
	final static int INOT = 12; //	inot		Pop value v, Push 1 if v = 0 else Push 0.
	final static int GOTO = 13; //	goto i		Jump to instruction i.
	final static int IF_FALSE = 14; // 		if false i		Pop value v, if v = 0 jump to instruction i, else continue with next instruction.
	final static int INVOKEVIRTUAL = 15; //	invokevirtual m	Push current activation and switch to the method having qualified name m.
	final static int IRETURN = 16; //		ireturn			Pop activation and continue.
	final static int PRINT = 17; //			print			Pop value and print.
	final static int STOP = 18; //			stop			Execution completed.	
}
