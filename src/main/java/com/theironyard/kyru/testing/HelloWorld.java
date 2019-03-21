package com.theironyard.kyru.testing;


/**
 * Created by kdrudy on 8/3/16.
 */
public class HelloWorld {

    public static void main(String args[]) {

        System.out.println("Hello, World!");

    }
    
    public static int multiplication(int a, int b) {
        
        int sum = 0;
        
        //5 * 3 = 5 + 5 + 5
        
        for(int i = 0;i<Math.abs(b); i++) {
            sum += Math.abs(a);
        }
        
        if( a<0 ^ b<0 ) {
            return -sum;
        }
        
        return sum;
    }
}
