package com.myorg.generateAcc;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huyan on 2016/9/14.
 */
public class GenerateAccountData {


    public static final String dyAccBaseInfoPath = "E:\\StudyBench\\SpringMvcTest\\src\\main\\accountData\\dyBaseAccount";
    public static final String cdAccBaseInfoPath = "E:\\StudyBench\\SpringMvcTest\\src\\main\\accountData\\cdBaseAccount";
    public static final String dyAccountPath = "E:\\StudyBench\\SpringMvcTest\\src\\main\\accountData\\dyAccount";
    public static final String cdAccountPath = "E:\\StudyBench\\SpringMvcTest\\src\\main\\accountData\\cdAccount";
    public static final String generatePath = "E:\\StudyBench\\SpringMvcTest\\src\\main\\clickAdd\\generateData";


    public static void main(String[] args) throws Exception {
        GenerateAccountData accountData = new GenerateAccountData();

        accountData.generateAccInfo(dyAccBaseInfoPath, dyAccountPath);
        accountData.generateAccInfo(cdAccBaseInfoPath, cdAccountPath);

    }


    public void generateAccInfo(String baseDataPath, String accInfoPath) throws Exception {

        List<String> dyBaseDatas = FileUtil.readFileLines(baseDataPath);
        List<String> generateData = FileUtil.readFileLines(generatePath);

        Map<String , String> generateDataMap = getAccountKeyMap(generateData);

        List<String> dyAcc = new ArrayList<>();

        for (String data : dyBaseDatas){

            if (generateDataMap.containsKey(data)){
                dyAcc.add(generateDataMap.get(data));
            }
        }

        FileUtil.writeData(dyAcc, accInfoPath);

    }


    public Map<String, String> getAccountKeyMap(Collection<String> datas){


        Map<String, String> result = new HashMap<>();

        for (String data: datas){

            int n = data.indexOf("|");
            String account = data.substring(0, n);

            result.put(account, data);

        }

        return result;
    }
}
