class Sum {
	public static void main(String[] a) {
		System.out.println(new Test().sum(10));
	}
}
/**
 * this is a test program
 * @author amir
 *
 */

class Test {
	public int sum(int num) {
		int sum;
		sum = 0;
		while (0 < num) {
			sum = sum + num;
			num = num - 1;
		}
		return sum;
	}
}
