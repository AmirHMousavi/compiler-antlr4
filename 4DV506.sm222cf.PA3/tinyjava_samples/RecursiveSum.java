// A test program for TinyJava

class RecursiveSum {
  public static void main(String[] a){
	 System.out.println(new Test().sum(10));
  }
}

class Test {
  int sum(int num) {
    int sum;
    if (num < 2)
      sum = 1;
    else
      sum = num + this.sum(num-1);
    return sum;
  }
}
