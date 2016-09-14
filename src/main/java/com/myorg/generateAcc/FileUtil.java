package com.myorg.generateAcc;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by huyan on 2016/9/14.
 */
public class FileUtil {

    public static List<String> readFileLines(String filePath) throws Exception {

        List<String> result = new ArrayList<>();

        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filePath)); // 建立一个输入流对象reader

        BufferedReader br = new BufferedReader(reader);

        String line = br.readLine();
        while (line != null){

            result.add(line);
            line = br.readLine();

        }

        br.close();
        return result;

    }

    public static void writeData(Collection<String> datas, String filePath) throws Exception{

        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath));
        BufferedWriter bw = new BufferedWriter(writer);

        int n = 0;
        int count = datas.size();

        for (String data : datas){

            n++;
            bw.write(data);
            if ( n!= (count-1) ){
                bw.newLine();
            }
        }


        bw.close();
    }
}
