package com.theironyard.kyru.testing;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Kyle on 3/8/2017.
 */
public class MarkovChain {
    HashMap<String, HashMap<String, Integer>> frequency = new HashMap<>();

    public void buildFrequencyTable(StringBuilder text) {
        String[] parts = text.toString().split(" ");
        for(int i = 0;i<parts.length-1;i++) {
            String word = parts[i];
            String nextWord = parts[i+1];
            if(frequency.containsKey(word)) { //Old initial word
                HashMap<String, Integer> next = frequency.get(word);
                if (next.containsKey(nextWord)) { //Old next word
                    next.replace(nextWord, next.get(nextWord)+1);
                } else { //New next word
                    next.put(nextWord, 1);
                }
            } else { //New initial word
                HashMap<String, Integer> next = new HashMap<>();
                next.put(nextWord, 1);
                frequency.put(word, next);
            }
        }
    }

    private String getNextWord(String word) {
        String returnWord = "";

        if(frequency.containsKey(word)) {
            HashMap<String, Integer> possibleWords = frequency.get(word);
            int possibleCount = 0;
            for(String s : possibleWords.keySet()) {
                possibleCount += possibleWords.get(s);
            }
            int selection = (int) (Math.random()*possibleCount);
            for(String s : possibleWords.keySet()) {
                selection -= possibleWords.get(s);
                if(selection <= 0) {
                    returnWord = s;
                    break;
                }
            }
        }

        return returnWord;
    }

    public String getMarkovChain(int words) {
        int count = frequency.size();
        int start = (int) (Math.random()*count);
        String word = (String) frequency.keySet().toArray()[start];
        StringBuilder sb = new StringBuilder();
        sb.append(word);
        sb.append(" ");
        for(int i = 1;i<words;i++) {
            word = getNextWord(word);
            sb.append(word);
            sb.append(" ");
        }
        return sb.toString();
    }
}
