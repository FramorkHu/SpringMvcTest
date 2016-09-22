package com.myorg.generateAcc;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;

/**
 * Created by huyan on 2016/9/18.
 */
public class CalculateIncome {

    public static void main(String[] args) {

        Map<String, Double> mapIncomes = new LinkedHashMap<>();

        mapIncomes.put("瘦身女皇",2000.00);
        mapIncomes.put("随心之旅+尚女神",1306.25-500+1039.24);
        mapIncomes.put("体坛咨讯",1459.61);
        mapIncomes.put("八卦热点头条+王者荣耀钻石解说",590.13+1358.84);

        mapIncomes.put("美女爱渣男+王者荣耀上王者",1017.36+1062.01);

        mapIncomes.put("曼巴足球+企鹅漫画",1831.62+1746.99);



        //List<Double> incomes = new ArrayList<>(mapIncomes.values());
        List<Double> incomes = new ArrayList<>();
        incomes.add(590.13);
        incomes.add(1062.01);
        incomes.add(1017.36);
        incomes.add(1039.24);
        incomes.add(1358.84);
        incomes.add(1306.25-500);
        incomes.add(1459.61);
        incomes.add(1831.62);
        incomes.add(1746.99);

        new CalculateIncome().doCalculate(mapIncomes, 0.25);
        //new CalculateIncome().doCalculate(incomes, 0.25);
    }


    public void doCalculate(Map<String, Double> incomes, double factor){


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

        System.out.println("\n总金额："+allIncome);
    }

    public void doCalculate(List<Double> incomes, double factor){


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
    }
}
