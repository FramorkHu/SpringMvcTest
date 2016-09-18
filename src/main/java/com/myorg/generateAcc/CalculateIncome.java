package com.myorg.generateAcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huyan on 2016/9/18.
 */
public class CalculateIncome {

    public static void main(String[] args) {

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

        new CalculateIncome().doCalculate(incomes, 0.25);
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

        System.out.println("总金额："+allIncome);
    }

}
