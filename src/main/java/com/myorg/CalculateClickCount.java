package com.myorg;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huyan on 2016/5/18.
 */
public class CalculateClickCount {

    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws Exception {
        new CalculateClickCount().addCount();


    }


    public void addCount() throws Exception {

        File file = new File("C:\\Users\\happy\\Downloads\\逍遥安卓下载\\clickAdd2016-08-25.txt");


        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(file)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line ;
        line = br.readLine();
        Map<String, Integer> result = new HashMap<>();
        Set<String> account = new HashSet<>();
        int allCount = 0;
        while (line != null){
            int n = 4;
            String [] datas = line.split(" ");
            String countStr ;
            int count;
            String accountName;
            try {
                countStr = datas[n].substring(4, datas[n].length() - 1);
                count= Integer.parseInt(countStr);
                accountName = datas[n+1].substring(5,datas[n+1].length() - 1);
            } catch (Exception e){
                n = n-1;
                countStr = datas[n].substring(4, datas[n].length() - 1);
                count = Integer.parseInt(countStr);
                accountName = datas[n+1].substring(5,datas[n+1].length() - 1);

            }

            String attName = datas[n-1];
            Integer attCount = result.get(datas[n-1]);
            if (attCount == null){
                attCount = 0;
            }
            attCount += count;
            result.put(attName, attCount);
            allCount+= count;

            String patternStr = "(^\\d+\\|\\w*@?\\w*)";
            Pattern pattern = Pattern.compile(patternStr);

            Matcher matcher = pattern.matcher(accountName);
            matcher.find();
            String tempAccountName = matcher.group(0) == null? accountName.substring(0, accountName.lastIndexOf("|")) : matcher.group(0);

            account.add(tempAccountName);
            line = br.readLine();
        }

        System.out.println(allCount);
        System.out.println(result);
        System.out.println(account);
        System.out.println(account.size());
    }

}
