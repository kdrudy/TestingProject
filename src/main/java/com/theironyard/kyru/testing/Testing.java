package com.theironyard.kyru.testing;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import twitter4j.*;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by kdrudy on 7/25/16.
 */
public class Testing {

    public static void main(String args[]) throws Exception {
        System.out.println("Testing");
        
        System.out.println(Character.getNumericValue('A'));
        System.out.println(Character.getNumericValue('a'));
        System.out.println(Character.getNumericValue('B'));
        System.out.println(Character.getNumericValue('b'));

        Testing t = new Testing();
        System.out.print("\033[H\033[2J");

//        t.mersennePrime();
//
//        Twitter twitter = TwitterFactory.getSingleton();
//        System.out.println(twitter.getRateLimitStatus().get("/statuses/user_timeline"));
//
//        Paging p = new Paging();
//        p.setCount(200);
//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i < 10; i++) {
//            p.setPage(i);
//            List<Status> statuses = twitter.getUserTimeline("davemeltzerWON", p);
//
//            for (Status s : statuses) {
//                if (!s.getText().startsWith("RT")) {
//                    sb.append("^" + s.getText() + "`");
//                }
//
//            }
//        }


//        String dp = t.dissociatedPress(5, 1, 2, 100, "./resources/big.txt");
//        String dp = t.dissociatedPress(5, 1, 2, 500, sb);

//        Scanner scanner = new Scanner(new File("./resources/timecube.txt"));
//        List<Tweet> tweets = t.loadTweetsFile();
//        tweets.stream()
//                .collect(Collectors.groupingBy((tw) -> tw.getUserKey()))
//                .entrySet()
//                .stream()
//                .sorted(Comparator.comparingInt((Map.Entry<String, List<Tweet>> e) -> e.getValue().size()).reversed())
//                .forEach((e) -> {
//                    User user;
//                    try {
//                        user = twitter.showUser(e.getKey());
//                    } catch (TwitterException e1) {
//                        user = null;
//                    }
//                    System.out.println(e.getKey() + " count: " + e.getValue().size() + " exists: " + ((user == null) ? "no" : "yes"));
//                });



//        tweets.stream()
//                .filter((tw) -> tw.getUserKey().equals("ameliebaldwin"))
//                .forEach((tw) -> System.out.println(tw.getText()));
//        StringBuilder sb = new StringBuilder();
//        for(Tweet tweet : tweets) {
//            sb.append(tweet.getText());
//        }
//        while(scanner.hasNext()) {
//            sb.append(scanner.nextLine());
//            sb.append(" ");
//        }

//        MarkovChain mc = new MarkovChain();
//        mc.buildFrequencyTable(sb);
//        String dp = mc.getMarkovChain(500);

//        System.out.println(dp);

//        String[] tweets = dp.split("`\\^");

//        for(String s : tweets) {
//            System.out.println();
//            System.out.println(s);
//        }
//
//        ArrayList<String> sentences = t.extractSentences(dp);
//
//        for(String s : sentences) {
//            System.out.println();
//            System.out.println(s);
//        }
        
        //System.out.println("1113213211: " + t.isPrime(1113213211));

    }

    private List<Tweet> loadTweetsFile() throws IOException {
        //Scanner scanner = new Scanner(new File("./resources/tweets.csv"));
        Reader in = new FileReader("./resources/tweets.csv");
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        List<Tweet> tweets = new ArrayList<>();
        for(CSVRecord record : records) {
            Tweet tweet = new Tweet();
            tweet.setUserId(record.get(0));
            tweet.setUserKey(record.get(1));
            tweet.setCreatedAt(record.get(2));
            tweet.setCreatedStr(record.get(3));
            tweet.setRetweetCount(record.get(4));
            tweet.setRetweeted(record.get(5));
            tweet.setFavoriteCount(record.get(6));
            tweet.setText(record.get(7));
            tweet.setTweetId(record.get(8));
            tweet.setSource(record.get(9));
            tweet.setHashtags(record.get(10));
            tweet.setExpandedUrls(record.get(11));
            tweet.setPosted(record.get(12));
            tweet.setMentions(record.get(13));
            tweet.setRetweetedStatusId(record.get(14));
            tweet.setInReplyToStatusId(record.get(15));

            tweets.add(tweet);
        }

        return tweets;
    }



    private ArrayList<String> extractSentences(String dp) {
        ArrayList<String> sentences = new ArrayList<>();
        Pattern pattern = Pattern.compile("([?.]\\s+[A-Z*@])");
        Matcher m = pattern.matcher(dp);

        m.find();
        int startIndex = m.start() + 1;
        int endIndex = dp.length();
        while (m.find()) {
            endIndex = m.end() - 1;

            sentences.add(dp.substring(startIndex, endIndex));

            startIndex = m.start() + 1;
        }

        return sentences;
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

        for (int i = 0; i < 100; i++) {
            BigInteger value = BigInteger.valueOf(2).pow(i).subtract(BigInteger.ONE);
            long time = System.currentTimeMillis();

            if (isPrime(value)) {
                System.out.println(i + ":" + value + ":" + (System.currentTimeMillis() - time) + "ms");
            } else {
                //System.out.println("NO " + i + ":" + value);
            }
        }
    }

    public boolean isPrime(int value) {
        return isPrime(new Long(value));
    }

    public boolean isPrime(long value) {
        if (value == 0 || value == 1) {
            return false;
        }
        if (value == 2) {
            return true;
        }
        if (value % 2 == 0) {
            return false;
        }

        int max = (int) Math.sqrt(value) + 1;
        for (int i = 3; i < max; i += 2) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isPrime(BigInteger value) {
        BigInteger two = new BigInteger("2");

        if (value.equals(BigInteger.ZERO) || value.equals(BigInteger.ONE)) {
            return false;
        }
        if (value.equals(two)) {
            return true;
        }
        if (value.mod(two).equals(BigInteger.ZERO)) {
            return false;
        }

        BigInteger max = value.divide(BigInteger.valueOf(2));
        for (BigInteger bi = BigInteger.valueOf(3); bi.compareTo(max) < 0; bi = bi.add(two)) {
            if (value.mod(bi).equals(BigInteger.ZERO)) {
                return false;
            }
            max = value.divide(bi);
        }
        return true;
    }

    public String dissociatedPressFormatted(int pullWords, int match, int total, String filename) throws Exception {
        return dissociatedPressFormatted(pullWords, pullWords, match, total, filename);
    }
    public String dissociatedPressFormatted(int initializePullWOrds, int pullWords, int match, int total, String filename) throws Exception {
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

        } while (sentence.split(" ").length < 5); //Ensure we have some kind of sentence and not just a few words

        return sentence;
    }

    public String dissociatedPress(int pullWords, int match, int total, StringBuilder sb) throws Exception {
        return dissociatedPress(pullWords, pullWords, match, total, sb);
    }
    public String dissociatedPress(int initialPullWords, int pullWords, int match, int total, StringBuilder sb) throws Exception {
        Scanner scanner = new Scanner(sb.toString());
        return dissociatedPress(initialPullWords, pullWords, match, total, scanner);
    }

    public String dissociatedPress(int pullWords, int match, int total, String filename) throws Exception {
        return dissociatedPress(pullWords, pullWords, match, total, filename);
    }
    public String dissociatedPress(int initialPullWords, int pullWords, int match, int total, String filename) throws Exception {
        try {
            Scanner scanner = new Scanner(new File(filename));

            return dissociatedPress(initialPullWords, pullWords, match, total, scanner);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + filename);
            e.printStackTrace();
        }
        return null;
    }

    public String dissociatedPress(int pullWords, int match, int total, Scanner scanner) throws Exception {
        return dissociatedPress(pullWords, pullWords, match, total, scanner);
    }

    public String dissociatedPress(int initialPullWords, int pullWords, int match, int total, Scanner scanner) throws Exception {

        if(initialPullWords < match) {
            throw new Exception("Initial pull words can't be less then match words");
        }

        //Read in the file and split up the words
        //FileReader fr = new FileReader(textFile);

        ArrayList<String> textWords = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            for (String word : words) {
                if (!"".equals(word.trim())) {
                    textWords.add(word);
                }
            }
        }

        //Find a random starting point
        int totalWords = textWords.size();
        int startingPoint = (int) (Math.random() * (totalWords - pullWords));

        //Pull in initial words and set up match words
        ArrayList<String> nextMatch = new ArrayList<>();
        StringBuilder newText = new StringBuilder();
        int wordCount = 0;

        for (int i = 0; i < initialPullWords; i++) {
            newText.append(textWords.get(startingPoint + i));
            newText.append(" ");
            wordCount++;
            if (i - (initialPullWords - match) >= 0) {
                nextMatch.add(textWords.get(startingPoint + i));
            }
        }

        //Continue matching until we get all the words we want
        while (wordCount < total) {
            ArrayList<Integer> possibleNewStart = new ArrayList<>();
            //Find next match
            for (int i = 0; i < totalWords; i++) {
                if (textWords.get(i).equals(nextMatch.get(0))) {
                    boolean validMatch = true;
                    for (int j = 0; j < match; j++) {
                        if (i+j >= totalWords || !textWords.get(i + j).equals(nextMatch.get(j))) {
                            validMatch = false;
                        }
                    }
                    if (validMatch) {
                        possibleNewStart.add(i);
                    }
                }
            }

            int newStart = (int) (Math.random() * possibleNewStart.size());

            for (int i = 0; i < pullWords; i++) {
                String newWord = textWords.get(possibleNewStart.get(newStart) + match + i);
                newText.append(newWord);
                newText.append(" ");
                wordCount++;
                nextMatch.remove(0);
                nextMatch.add(newWord);}
        }

        //Print out line
        return newText.toString();
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
