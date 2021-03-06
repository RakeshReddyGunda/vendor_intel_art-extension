
/**
*
* L: Branching is not allowed: if-else breaks optimization's limitations
* L: Expected result: no sinking because of if-else statement in a loop
* M, N: No limitations on basic blocks number, so if-else is allowed if other limitations are not broken
* M, N: Expected result: 1 sinking for each method
*
**/

public class Main {                                                                                                                                                   
    final int iterations = 100;

    public int testLoopAddInt() {
        int testVar = 100;
        int additionalVar = 0;
        int setToConstantVar = 3;
        for (int i = -10; i < iterations; i++) {
              testVar += setToConstantVar;
              additionalVar += (i*2)%5;
	      if (additionalVar > 10) {
		    additionalVar++;
	      }
        }
        testVar = testVar + additionalVar;
        return testVar;
    }

    public int testLoopSubInt() {
        int testVar = 100;
        int additionalVar = 0;
        int setToConstantVar = 3;
        for (int i = -10; i < iterations; i++) {
              testVar -= setToConstantVar;
              additionalVar += (i*2)%5;
	      if (additionalVar > 10) {
		  additionalVar++;
	      }
        }
        testVar = testVar + additionalVar;
        return testVar;
    }

    public long testLoopSubLong() {
        long testVar = 100;
        long additionalVar = 0;
        long setToConstantVar = 3;
        for (long i = -10; i < iterations; i++) {
              testVar -= setToConstantVar;
              additionalVar += (i*2)%5;
	      if (additionalVar > 10) {
		  additionalVar++;
	      }
        }
        testVar = testVar + additionalVar;
        return testVar;
    }

    public int testLoopMulInt(int n) {
        int testVar = 1;
        int additionalVar = 0;
        int setToConstantVar = 6;
        for (int i = -3; i < 6; i++) {
              testVar *= setToConstantVar;
              additionalVar += (i*2)%5 + n%2 + n %3 - n%5 + n%6 - i%2 + i%3;
	      if (additionalVar > 10) {
		  additionalVar++;
	      }
        }
        testVar = testVar + additionalVar;
        return testVar;
    }

    public long testLoopMulLong(long n) {
        long testVar = 1;
        long additionalVar = 0;
        long setToConstantVar = 6L;
        for (long i = -3; i < 9; i++) {
              testVar *= setToConstantVar;
              additionalVar += (i*2)%5 + n%2 + n %3 - n%5 + n%6 - i%2 + i%3;
	      if (additionalVar > 10) {
		  additionalVar++;
	      }
        }
        testVar = testVar + additionalVar;
        return testVar;
    }

    public int testLoopDivInt() {
        int testVar = 40000000;
        int additionalVar = 0;
        int setToConstantVar = 2;
        for (int i = -10; i < 15; i++) {
              testVar /= setToConstantVar;
              additionalVar += (i*2)%5;
	      if (additionalVar > 10) {
		  additionalVar++;
	      }
        }
        testVar = testVar + additionalVar;
        return testVar;
    }

    public long testLoopDivLong() {
        long testVar = 40000000;
        long additionalVar = 0;
        long setToConstantVar = 2;
        for (long i = -10; i < 15; i++) {
              testVar /= setToConstantVar;
              additionalVar += (i*2)%5;
	      if (additionalVar > 10) {
		  additionalVar++;
	      }
        }
        testVar = testVar + additionalVar;
        return testVar;
    }

    public int testLoopRemInt() {
        int testVar = 100;
        int additionalVar = 0;
        int setToConstantVar = 3;
        for (int i = -10; i < iterations; i++) {
              testVar %= setToConstantVar;
              additionalVar += (i*2)%5;
	      if (additionalVar > 10) {
		  additionalVar++;
	      }
        }
        testVar = testVar + additionalVar;
        return testVar;
    }

    public long testLoopRemLong() {
        long testVar = 100;
        long additionalVar = 0;
        long setToConstantVar = 3;
        for (long i = -10; i < iterations; i++) {
              testVar %= setToConstantVar;
              additionalVar += (i*2)%5;
	      if (additionalVar > 10) {
		  additionalVar++;
	      }
        }
        testVar = testVar + additionalVar;
        return testVar;
    }

    public long testLoopAddLong() {
        long testVar = 100;
        long additionalVar = 0;
        long setToConstantVar = 3;
        for (long i = -10; i < iterations; i++) {
              testVar += setToConstantVar;
              additionalVar += (i*2)%5;
	      if (additionalVar > 10) {
		  additionalVar++;
	      }
        }
        testVar = testVar + additionalVar;
        return testVar;
    }

    public static void main(String[] args)
    {
         System.out.println(new Main().testLoopAddInt());
System.out.println(new Main().testLoopAddLong());
System.out.println(new Main().testLoopRemLong());
System.out.println(new Main().testLoopRemInt());
System.out.println(new Main().testLoopDivLong());
System.out.println(new Main().testLoopDivInt());
System.out.println(new Main().testLoopMulLong(10L));
System.out.println(new Main().testLoopMulInt(10));
System.out.println(new Main().testLoopSubLong());
System.out.println(new Main().testLoopSubInt());
    }

}  

