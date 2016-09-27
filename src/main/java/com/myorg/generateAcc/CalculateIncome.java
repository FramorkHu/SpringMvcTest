package com.myorg.generateAcc;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;

/**
 * Created by huyan on 2016/9/18.
 */
public class CalculateIncome {


    public static void main(String[] args) {

        Map<String, Map<String, Double>> personIncom = new LinkedHashMap<>();

        Map<String, Double> kingsleyIncomes = new LinkedHashMap<>();

        kingsleyIncomes.put("瘦身女皇",2000.00);
        kingsleyIncomes.put("随心之旅+尚女神",1306.25-500+1039.24);
        kingsleyIncomes.put("体坛咨讯",1459.61);
        kingsleyIncomes.put("八卦热点头条+王者荣耀钻石解说",590.13+1358.84);
        personIncom.put("kingsley", kingsleyIncomes);

        Map<String, Double> wyIncomes = new LinkedHashMap<>();
        wyIncomes.put("美女爱渣男+王者荣耀上王者",1017.36+1062.01);
        personIncom.put("王一", wyIncomes);

        Map<String, Double> wkIncomes = new LinkedHashMap<>();
        wkIncomes.put("曼巴足球+企鹅漫画",1831.62+1746.99);
        personIncom.put("王可", wkIncomes);

        Map<String, Double> addIncomes = new LinkedHashMap<>();
        addIncomes.put("研磨时光",150.00);
        addIncomes.put("奥运123",150.00);
        addIncomes.put("职业篮球教练",150.00);
        personIncom.put("阿达达", addIncomes);

        new CalculateIncome().calculateIncome(personIncom, 0.25);

    }


    public void calculateIncome(Map<String, Map<String, Double>> personIncom, double factor){

        double total = 0;
        for (Map.Entry<String, Map<String, Double>> entry : personIncom.entrySet()){
            System.out.println(entry.getKey());
            total += doCalculate(entry.getValue(), factor);
        }

        System.out.println("总计:" +total);
    }

    public double doCalculate(Map<String, Double> incomes, double factor){


        double allIncome = 0;

        for (Map.Entry<String, Double> incomeEntry : incomes.entrySet()){
            double tax = 0;
            String accName = incomeEntry.getKey();
            double income = incomeEntry.getValue();

            if (income > 4000){
                tax = (income - income*0.2) * 0.2;
            } else if (income>800 && income<= 4000){

                tax = (income-800) * 0.2;
            }

            double thisIncome = (income - tax) * factor;

            System.out.println(accName+":-------税["+tax+"] 税后收人["+thisIncome+"]");
            allIncome += thisIncome;
        }

        System.out.println("\n总金额："+allIncome+"\n");
        return allIncome;
    }

    public double doCalculate(List<Double> incomes, double factor){


        double allIncome = 0;

        for (Double income : incomes){
            double tax = 0;

            if (income > 4000){
                tax = (income - income*0.2) * 0.2;
            } else if (income>800 && income<= 4000){

                tax = (income-800) * 0.2;
            }

            double thisIncome = (income - tax) * factor;

            System.out.println(thisIncome);
            allIncome += thisIncome;
        }

        System.out.println("\n总金额："+allIncome);

        return allIncome;
    }
}
