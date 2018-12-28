// A test program for TinyJava that computes the faculty
// and the Fibonacci number for two different integers.
// Correct answers are 120 and 55.

class FacFib {
  public static void main(String[] a){
	 System.out.println(new FacFibImpl().compute(5,10));
  }
}

class FacFibImpl {
  int compute(int first, int second) {
    int fac;
    int fib;
    int returnValue;

    if ( !(first < 1)  && !(second < 1) ) {
      fac = this.computeFac(first);
      System.out.println(fac);

      fib = this.computeFib(second);
      System.out.println(fib);
      returnValue = 1;
	 }
    else {
      returnValue = 0;
	 }
    return returnValue;
  }

  int computeFac(int num){
	 int fac;
	 if (num < 1)
	   fac = 1;
	 else
	   fac = num * (this.computeFac(num-1));
    return fac;
  }

  int computeFib(int num) {
    int f0;
    int f1;
    int fib;
    int i;

    f0 = 0;
    f1 = 1;
    fib = num;

    i = 1;
    while (i < num) {
      fib = f1 + f0;
      f0 = f1;
      f1 = fib;
      i = i + 1;
    }
    return fib;
  }

}
