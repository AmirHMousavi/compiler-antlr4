/*
 * gradually add code to test
 * */
class simple {
	public static void main(String[] a){
	System.out.println(new BT().start(0,new int[5],true,"string",'A'));
	}
}

class BT {
	int size;
	int[] numbers;
	Tree root;
	String str;
	boolean bool;
	char cha;
	
	public int start(int n, int[] arr, boolean b, String strmp, char ch){
		root.init();
		size = -n*arr.length;
		size = strmp.length + 2 + 3 - 1 * 4;
	    numbers[0]=arr[1];
	    size= numbers.length;
		bool=((!b && n<=size)|| !(strmp.charAt(0)!= 'b' && 3<2 ));
		cha=str.charAt(0);
		return 99;
	}

}

class Tree {
	public void init(){
		System.out.println("[initialization]");
	}

}
