package com.theironyard.kyru.testing;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by kdrudy on 7/25/16.
 */
public class Testing {

    public static void main(String args[]) {
        System.out.println("Testing");

        Testing t = new Testing();

        t.mersennePrime();

//        for(int i = 0;i<10;i++) {
//            System.out.println(t.dissociatedPressFormatted(3, 1, 15, new File("./resources/timecube.txt")));
//        }
    }

    private void mersennePrime() {
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

    public boolean isPrime(int value) {
        return isPrime(new Long(value));
    }
    public boolean isPrime(long value) {
        if(value == 0 || value == 1) { return false; }
        if(value == 2) { return true; }
        if(value%2 == 0) { return false; }

        int max = (int)Math.sqrt(value) + 1;
        for(int i = 3;i < max; i+=2) {
            if(value % i == 0) { return false; }
        }
        return true;
    }

    public boolean isPrime(BigInteger value) {
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

    public String dissociatedPressFormatted(int pullWords, int match, int total, String filename) {
        String sentence = "";

        do {
            sentence = dissociatedPress(pullWords, match, total, filename);

            //Fix formatting on text
            sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);

            if (sentence.indexOf(".") > 0) {
                sentence = sentence.substring(0, sentence.indexOf(".") + 1);
            } else {
                sentence = sentence.trim() + ".";
            }

        } while(sentence.split(" ").length < 5); //Ensure we have some kind of sentence and not just a few words

        return sentence;
    }

    public String dissociatedPress(int pullWords, int match, int total, String filename) {
        try {

            //Read in the file and split up the words
            //FileReader fr = new FileReader(textFile);

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    this.getClass().getClassLoader().getResourceAsStream(filename)));

            ArrayList<String> textWords = new ArrayList<>();

            while(br.ready()) {
                String line = br.readLine();
                String[] words = line.split(" ");
                for(String word : words) {
                    if(!"".equals(word.trim())) {
                        textWords.add(word);
                    }
                }
            }

            //Find a random starting point
            int totalWords = textWords.size();
            int startingPoint = (int)(Math.random()*(totalWords - pullWords));

            //Pull in initial words and set up match words
            String[] nextMatch = new String[match];
            StringBuilder newText = new StringBuilder();
            int wordCount = 0;

            for(int i = 0;i<pullWords;i++) {
                newText.append(textWords.get(startingPoint+i));
                newText.append(" ");
                wordCount++;
                if(i - (pullWords - match) >= 0) {
                    nextMatch[i - (pullWords - match)] = textWords.get(startingPoint+i);
                }
            }

            //Continue matching until we get all the words we want
            while(wordCount < total) {
                ArrayList<Integer> possibleNewStart = new ArrayList<>();
                //Find next match
                for(int i = 0;i<totalWords;i++) {
                    if (textWords.get(i).equals(nextMatch[0])) {
                        boolean validMatch = true;
                        for (int j = 0; j < match; j++) {
                            if (!textWords.get(i + j).equals(nextMatch[j])) {
                                validMatch = false;
                            }
                        }
                        if (validMatch) {
                            possibleNewStart.add(i);
                        }
                    }
                }
                int newStart = (int)(Math.random()*possibleNewStart.size());

                for(int i = 0;i<pullWords;i++) {
                    newText.append(textWords.get(possibleNewStart.get(newStart)+nextMatch.length+i));
                    newText.append(" ");
                    wordCount++;
                    if(i - (pullWords - match) >= 0) {
                        nextMatch[i - (pullWords - match)] = textWords.get(possibleNewStart.get(newStart)+nextMatch.length+i);
                    }
                }
            }

            //Print out line
            return newText.toString();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + filename);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String();
    }

    public boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}
