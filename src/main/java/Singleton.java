import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.*;
public class Singleton {
    public static void main(String[] args) {
        System.out.println("");
    }

    private Singleton() {}
    public static String str;
    private static Singleton singletonInstance=new Singleton();
    public static Singleton getSingleInstance() {
        return singletonInstance;
    }
}




// A private Singleton non parameterized constructor.
// A public String instance variable named .
// Write a static method named getSingleInstance that returns the single instance of the Singleton class.