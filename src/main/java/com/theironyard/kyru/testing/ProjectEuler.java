package com.theironyard.kyru.testing;

import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by kdrudy on 7/28/16.
 */
public class ProjectEuler {

    static int counter = 0;

    public static void main(String args[]) {

        ProjectEuler pe = new ProjectEuler();

        //31626
        //pe.amicableNumbersPE21();

        //648
        //pe.factorialDigitSumPE20();

        //171
        //pe.countingSundaysPE19();

        //1074
        //pe.maxPathSumPE18();

        //21124
        //pe.numberLetterCountsPE17();

        //1366
        //pe.powerDigitSumPE16();

        //137846528820
        //pe.latticePathsPE15();
    }

    public void amicableNumbersPE21() {

        int sums[] = new int[10000];

        //Get sum of divisors under 10000
        for(int i = 0;i<10000;i++) {
            sums[i] = sumOfDivisorsPE21(i);
        }
        ArrayList<Integer> amicable = new ArrayList<>();
        for(int i = 0;i<10000;i++) {
            if(sums[i] < 10000) {
                if(sums[sums[i]] == i && sums[i] != i) {
                    amicable.add(i);
                    System.out.println("added " + i);
                }
            }
        }

        //Get sum of amicable
        int total = 0;
        for(int val : amicable) {
            total += val;
        }

        System.out.println("amicable sum: " + total);
    }

    private int sumOfDivisorsPE21(int value) {
        ArrayList<Integer> divisors = new ArrayList<>();
        divisors.add(1);

        int max = (value/2)+1;
        for(int i = 2;i<max;i++) {
            if(value%i == 0) {
                divisors.add(i);
            }
        }

        //Add up divisors
        int sum = 0;
        for(int divisor : divisors) {
            sum+= divisor;
        }
        return sum;
    }

    public void factorialDigitSumPE20() {

        BigInteger value = BigInteger.valueOf(100);

        for(int i = 99;i>0;i--) {
            value = value.multiply(BigInteger.valueOf(i));
        }

        String valueString = value.toString();
        int total = 0;
        for(int i = 0;i<valueString.length();i++) {
            total += Integer.parseInt(valueString.substring(i, i+1));
        }
        System.out.println(valueString + ":" + total);

    }

    public void countingSundaysPE19() {
        int daysPassed = 1;
        int year = 1900;

        int sundays = 0;

        for(int i = 0;i<101;i++) {
            System.out.println(year+i);
            for (int j = 0; j < 12; j++) {
                daysPassed += daysInMonthPE19(j, year+i);

                //Is Sunday?
                if (daysPassed % 7 == 0 && i >= 1) sundays++;
            }
            System.out.println("Accumulated Sundays: " + sundays);
        }
    }

    private int daysInMonthPE19(int month, int year) {
        int days = 0;
        switch(month) {
            case 0:
                days = 31;
                break;
            case 1:
                if(year%4 == 0 && year%400 != 0) days = 29;
                else days = 28;
                break;
            case 2:
                days = 31;
                break;
            case 3:
                days = 30;
                break;
            case 4:
                days = 31;
                break;
            case 5:
                days = 30;
                break;
            case 6:
                days = 31;
                break;
            case 7:
                days = 31;
                break;
            case 8:
                days = 30;
                break;
            case 9:
                days = 31;
                break;
            case 10:
                days = 30;
                break;
            case 11:
                days = 31;
                break;
        }
        return days;
    }

    public void maxPathSumPE18() {
        int gridSize = 15;
        File gridFile = new File("./resources/grid.txt");

        try {
            FileReader fr = new FileReader(gridFile);
            BufferedReader br = new BufferedReader(fr);

            int grid[][] = new int[gridSize][gridSize];
            int row = 0;
            while(br.ready()) {

                String line = br.readLine();
                String lineParts[] = line.split(" ");
                for(int i = 0;i<gridSize;i++) {
                    if(lineParts.length>i) {
                        grid[row][i] = Integer.parseInt(lineParts[i]);
                    } else {
                        grid[row][i] = 0;
                    }
                }
                row++;
            }

            for(int i = gridSize-2;i>=0;i--) {
                for(int j = 0;j<gridSize-1;j++) {
                    if(grid[i+1][j] > grid[i+1][j+1]) {
                        grid[i][j] += grid[i+1][j];
                    } else {
                        grid[i][j] += grid[i+1][j+1];
                    }
                }
            }

            System.out.println(grid[0][0]);
//
//            for(int i = 0;i<gridSize;i++) {
//                StringBuilder sb = new StringBuilder();
//                for(int j = 0;j<gridSize;j++) {
//                    sb.append(grid[i][j] + " ");
//                }
//                System.out.println(sb.toString());
//            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        }

    private void numberLetterCountsPE17() {
        int totalLetters = 0;
        for(int i = 1;i<=1000;i++) {
            totalLetters+=getNumberAsWordsPE17(i).length();
        }
        System.out.println(totalLetters);
    }


    private String getNumberAsWordsPE17(int num) {
        String number = Integer.toString(num);

        StringBuilder sb = new StringBuilder();

        for(int i = 0;i<number.length();i++) {
            char value = number.charAt(i);

            if(number.length() - i == 2) {
                //Special case 10-19
                if(value == '1') {
                    switch(number.charAt(i+1)) {
                        case '0':
                            sb.append("ten");
                            break;
                        case '1':
                            sb.append("eleven");
                            break;
                        case '2':
                            sb.append("twelve");
                            break;
                        case '3':
                            sb.append("thirteen");
                            break;
                        case '4':
                            sb.append("fourteen");
                            break;
                        case '5':
                            sb.append("fifteen");
                            break;
                        case '6':
                            sb.append("sixteen");
                            break;
                        case '7':
                            sb.append("seventeen");
                            break;
                        case '8':
                            sb.append("eighteen");
                            break;
                        case '9':
                            sb.append("nineteen");
                            break;
                    }
                }
                //Tens digit
                else {
                    switch (value) {
                        case '2':
                            sb.append("twenty");
                            break;
                        case '3':
                            sb.append("thirty");
                            break;
                        case '4':
                            sb.append("forty");
                            break;
                        case '5':
                            sb.append("fifty");
                            break;
                        case '6':
                            sb.append("sixty");
                            break;
                        case '7':
                            sb.append("seventy");
                            break;
                        case '8':
                            sb.append("eighty");
                            break;
                        case '9':
                            sb.append("ninety");
                            break;
                    }
                }
            } else {
                if(number.length() - i == 1) {
                    if(number.length() == 1 || (number.length() > 1 && number.charAt(i-1) != '1')){
                        switch (value) {
                            case '1':
                                sb.append("one");
                                break;
                            case '2':
                                sb.append("two");
                                break;
                            case '3':
                                sb.append("three");
                                break;
                            case '4':
                                sb.append("four");
                                break;
                            case '5':
                                sb.append("five");
                                break;
                            case '6':
                                sb.append("six");
                                break;
                            case '7':
                                sb.append("seven");
                                break;
                            case '8':
                                sb.append("eight");
                                break;
                            case '9':
                                sb.append("nine");
                                break;
                        }
                    }
                } else {
                    switch(value) {
                        case '1':
                            sb.append("one");
                            break;
                        case '2':
                            sb.append("two");
                            break;
                        case '3':
                            sb.append("three");
                            break;
                        case '4':
                            sb.append("four");
                            break;
                        case '5':
                            sb.append("five");
                            break;
                        case '6':
                            sb.append("six");
                            break;
                        case '7':
                            sb.append("seven");
                            break;
                        case '8':
                            sb.append("eight");
                            break;
                        case '9':
                            sb.append("nine");
                            break;
                    }
                    if(number.length() -i == 3 && value != '0') {
                        sb.append("hundred");
                        if(number.charAt(i+1) != '0' || number.charAt(i+2) != '0') {
                            sb.append("and");
                        }
                    }
                    if(number.length() - i == 4) {
                        sb.append("thousand");
                    }
                }
            }
        }

        return sb.toString();

    }

    private void powerDigitSumPE16() {
        BigInteger value = BigInteger.valueOf(2).pow(1000);
        String valueString = value.toString();
        int total = 0;
        for(int i = 0;i<valueString.length();i++) {
            total += Integer.parseInt(valueString.substring(i, i+1));
        }
        System.out.println(valueString + ":" + total);
    }

    private void latticePathsPE15() {
        int xSize = 20;
        int ySize = 20;

        long grid[][] = new long[xSize+1][ySize+1];

        //Initialize
        for(int i = 0;i<=xSize; i++) {
            for(int j = 0;j<=ySize;j++) {
                grid[i][j] = -1;
            }
        }

        for(int i = 0;i<=xSize; i++) {
            grid[0][i] = 1;
        }
        for(int i = 0;i<=ySize; i++) {
            grid[i][0] = 1;
        }

        while(!isCompletePE15(grid)) {
            //Add up grid
            for(int i = 1;i<grid.length;i++) {
                for(int j = 1;j<grid[0].length;j++) {
                    if(grid[i-1][j] != -1 && grid[i][j-1] != 0) {
                        grid[i][j] = grid[i-1][j] + grid[i][j-1];
                    }

                }
            }
        }

        System.out.println("Paths: " + grid[xSize][ySize]);
    }

    private boolean isCompletePE15(long[][] grid) {
        for(int i = 0;i<grid.length;i++) {
            for(int j = 0;j<grid[0].length;j++) {
                if(grid[i][j] == -1) { return false; }
            }
        }

        return true;
    }
}
