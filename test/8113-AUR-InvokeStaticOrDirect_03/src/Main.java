

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.io.*;

public class Main {

    public static final int NUMBER_OF_STRESS_THREADS = 15;
    public static final int MAX_ARRAY_SIZE = 700;
    public static final int MAX_OBJECTS_NUMBER = 700;


    public int field = 5;
    
    private int getSum(int arg) {
        return arg + 20;
    }



    // AUR example for InvokeStaticOrDirect: only live objects remain in environment
    // not a pure invoke
    public int runTest (int x, int n, Main m, Main other, StringBuilder sb1, String str1) {
    
        n = n + 5;
        //not pure
        for (int j = 0; j < 10000; j++) {
            (new StringBuilder()).append(str1);
        }
        //pure
        return str1.length();

    }

    // wrapper method for runTest
    public int test(int n) {
        Main unusedVar = new Main();
        Main other = new Main();
        String str1 = new String("A");
        StringBuilder sb1 = new StringBuilder(str1);

        int res = 0;
        res =  runTest(n%3, n, unusedVar, other, sb1, str1);
        return res;

    }

    // wrapper method for runTest with GC stress threads running
    public String testWithGCStress(int n) {
        String res = "";
        Thread t = new Thread(new Runnable(){
                public void run() {
                new StressGC().stressGC();
                }
                });

        t.start();
        try {
            res += test(n);
        } catch (Throwable e) {
            res += e;
        }
        try {
            t.join();
        } catch (InterruptedException ie) {
        }
        return res;
    }
    public void runTests() {
        Class c = new Main().getClass();
        Method[] methods = c.getDeclaredMethods();
        Method tempMethod;
        for (int i = 1; i < methods.length; i++) {
            int j = i;
            while (j > 0 &&  methods[j-1].getName().compareTo(methods[j].getName()) > 0) {
                tempMethod = methods[j];
                methods[j] = methods[j-1];
                methods[j-1] = tempMethod;
                j = j - 1;
            }
        }

        Object[] arr = {null};
        for (Method m: methods){
            if (m.getName().startsWith("test")){
                try {
                    String names[] = c.getName().split("\\.");
                    String testName = names[names.length-1];
                    System.out.println("Test "+testName+"; Subtest "+m.getName()+"; Result: "+ m.invoke(this, 10));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    StringWriter sw = new StringWriter();
                    StackTraceElement[] ste = e.getCause().getStackTrace();
                    // To workaround difference between android detailed message and java brief one:
                    System.out.println(e.getCause().getClass());
                    for (int i = 0; i < ((ste.length < 2)?ste.length:2); i++) {
                        System.out.println(ste[i]);
                    }
                }
            }
        }



    }

    public static void main(String[] args)
    {
        new Main().runTests();
    }

}


