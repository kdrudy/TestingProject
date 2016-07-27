package com.theironyard.kyru.testing;

import java.math.BigInteger;

/**
 * Created by kdrudy on 7/25/16.
 */
public class Testing {

    public static void main(String args[]) {
        System.out.println("Testing");

//        for(int i = 2100000000;i<Integer.MAX_VALUE;i++) {
//            if(isPrime(i)) {
//                long time = System.currentTimeMillis();
//                System.out.println(i + ":" + isPrime(i) + ":" + (System.currentTimeMillis()-time) + "ms");
//            }
//        }

        //Mersenne Prime //Takes very long past 9th MPs
//        2:3:1ms
//        3:7:0ms
//        5:31:0ms
//        7:127:0ms
//        13:8191:1ms
//        17:131071:1ms
//        19:524287:1ms
//        31:2147483647:16ms
//        61:2305843009213693951:111887ms

        for(int i = 0;i<100;i++) {
            BigInteger value = BigInteger.valueOf(2).pow(i).subtract(BigInteger.ONE);
            long time = System.currentTimeMillis();

            if(isPrime(value)) {
                System.out.println(i + ":" + value + ":" + (System.currentTimeMillis()-time) + "ms");
            } else {
                //System.out.println("NO " + i + ":" + value);
            }
        }
    }

    public static boolean isPrime(int value) {
        return isPrime(new Long(value));
    }
    public static boolean isPrime(long value) {
        if(value == 0 || value == 1) { return false; }
        if(value == 2) { return true; }
        if(value%2 == 0) { return false; }

        int max = (int)Math.sqrt(value) + 1;
        for(int i = 3;i < max; i+=2) {
            if(value % i == 0) { return false; }
        }
        return true;
    }

    public static boolean isPrime(BigInteger value) {
        BigInteger two = new BigInteger("2");

        if(value.equals(BigInteger.ZERO) || value.equals(BigInteger.ONE)) { return false; }
        if(value.equals(two)) { return true; }
        if(value.mod(two).equals(BigInteger.ZERO)) { return false; }

        BigInteger max = value.divide(BigInteger.valueOf(2));
        for(BigInteger bi = BigInteger.valueOf(3); bi.compareTo(max) < 0; bi = bi.add(two)) {
            if(value.mod(bi).equals(BigInteger.ZERO)) {
                return false;
            }
            max = value.divide(bi);
        }
        return true;
    }
}
