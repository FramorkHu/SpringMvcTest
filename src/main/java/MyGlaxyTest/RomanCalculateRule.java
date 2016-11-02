package MyGlaxyTest;

import java.util.*;

/**
 * Created by huyan on 2016/11/2.
 */
public class RomanCalculateRule {

    private char[] repeatRomanNum = {'I','X','C','M'};
    private char[] noRepeatRomanNum = {'V','L','D'};

    private Map<Character, Integer> repeatCountMap = new HashMap<>();
    private Set<Character> noRepeatSet = new LinkedHashSet<>();

    private Map<Character, Integer> romanNumberDataMap = new HashMap<>();

    public RomanCalculateRule(){

        romanNumberDataMap.put('I', 1);
        romanNumberDataMap.put('V', 5);
        romanNumberDataMap.put('X', 10);
        romanNumberDataMap.put('L', 50);
        romanNumberDataMap.put('C', 100);
        romanNumberDataMap.put('D', 500);
        romanNumberDataMap.put('M', 1000);
    }


    public double calculateRomanNumber(String romanNumber){

        String calculateRomanNumber = romanNumber.toUpperCase();
        char[] romanNumArray = calculateRomanNumber.toCharArray();

        double result = 0;
        double lastNumber =0;
        double currentNumber;
        for (char currentRomanNum : romanNumArray){

            currentNumber = romanNumberDataMap.get(currentRomanNum);
            result = calculateData(result, lastNumber, currentNumber);
            lastNumber = currentNumber;

        }

        return result;

    }

    private double calculateData(double result, double lastNumber, double currentNumber){

        if ( currentNumber > lastNumber){
            result = result - lastNumber + (currentNumber-lastNumber);
        } else {
            result = result + currentNumber;
        }

        return result;
    }

    private void checkRomanNum(char currentNum){

        if ( checkHasChar(repeatRomanNum, currentNum)){

            Integer repeatCount = repeatCountMap.get(currentNum);
            if (repeatCount == null){

            }


        } else if (checkHasChar(noRepeatRomanNum, currentNum)){
            if (noRepeatSet.contains(currentNum)){
                throw new RuntimeException("\"D\", \"L\", and \"V\" can never be repeated.");
            } else {
                noRepeatSet.add(currentNum);
            }
        }
    }


    /**
     * 检查属于那种类型的罗马数字
     * @param datas
     * @param checkData
     * @return
     */
    private boolean checkHasChar(char[] datas, char checkData){

        for (char data : datas){
            if ( data == checkData){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println(new RomanCalculateRule().calculateRomanNumber("MCMIII"));
    }

}
