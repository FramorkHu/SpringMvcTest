package com.myorg.generateAcc;

import java.io.File;
import java.io.IOException;
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
    public static final String allAccountPath = "E:\\StudyBench\\SpringMvcTest\\src\\main\\clickAdd\\data";
    String dataTime = "2016-09-26";

    public static void main(String[] args) throws Exception {
        GenerateAccountData accountData = new GenerateAccountData();

        accountData.splitAccountToFile();


    }

    private void splitAccountToFile() throws Exception {
        generateAccInfo(dyAccBaseInfoPath, dyAccountPath);
        generateAccInfo(cdAccBaseInfoPath, cdAccountPath);
    }

    private void writeBaseFileInfo() throws Exception {
        Map<String, String> filePathMap = new HashMap<>();
        filePathMap.put("C:\\Users\\happy\\Downloads\\逍遥安卓下载\\clickAdd"+dataTime+".txt", cdAccBaseInfoPath);
        filePathMap.put("C:\\Users\\happy\\Documents\\Tencent Files\\475616043\\FileRecv\\clickAdd"+dataTime+".txt", dyAccBaseInfoPath);


        List<AccountInfo> accountInfos = getAddAccountInfo(filePathMap);
        AccountInfo accountInfo = mergeAccount(accountInfos);

        writeAccountInfo(accountInfos);
        FileUtil.writeData(accountInfo.getAccountInfo(), allAccountPath);

        printAllAccount(accountInfo);
    }

    private void printClickCount() throws Exception {
        Map<String, String> filePathMap = new HashMap<>();

        //filePathMap.put("C:\\Users\\happy\\Downloads\\逍遥安卓下载\\clickAdd"+dataTime+".txt", cdAccBaseInfoPath);
        filePathMap.put("C:\\Users\\happy\\Documents\\Tencent Files\\475616043\\FileRecv\\clickAdd"+dataTime+".txt", dyAccBaseInfoPath);

        List<AccountInfo> accountInfos = getAddAccountInfo(filePathMap);
        AccountInfo accountInfo = mergeAccount(accountInfos);

        printAllAccount(accountInfo);
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

    private void printAllAccount(AccountInfo accountInfo){

        System.out.println(accountInfo.getAllClickCount());
        System.out.println(accountInfo.getAttClickCount());
        System.out.println(accountInfo.getAccountInfo());
        System.out.println(accountInfo.getAccountInfo().size());
    }

    public void writeAccountInfo(List<AccountInfo> accountInfos) throws Exception {

        for (AccountInfo accountInfo : accountInfos){

            String baseInfoPath = accountInfo.getBaseAccountFilePath();
            FileUtil.writeData(accountInfo.getOnlyAccount(), baseInfoPath);
        }

    }


    public List<AccountInfo> getAddAccountInfo(Map<String, String> filePathMap) throws Exception {

        List<AccountInfo> accountInfos = new ArrayList<>();

        for (Map.Entry<String, String> filePath : filePathMap.entrySet()){

            List<String> dataLines = FileUtil.readFileLines(filePath.getKey());
            AccountInfo accountInfo = accountData(dataLines);
            accountInfo.setBaseAccountFilePath(filePath.getValue());

            accountInfos.add(accountInfo);
        }

        return accountInfos;
    }

    private AccountInfo mergeAccount(List<AccountInfo> accountInfos){

        AccountInfo result = new AccountInfo();
        int allAccount =0 ;
        Collection<String> accounts = new HashSet<>();
        Collection<String> onlyAccounts = new HashSet<>();
        Map<String, Integer> attClickCount = new HashMap<>();

        for (AccountInfo accountInfo : accountInfos){
            allAccount += accountInfo.getAllClickCount();
            accounts.addAll(accountInfo.getAccountInfo());
            onlyAccounts.addAll(accountInfo.getOnlyAccount());
            Map<String, Integer> clickCountMap = accountInfo.getAttClickCount();

            for (Map.Entry<String, Integer> entry : clickCountMap.entrySet()){
                Integer attCount = attClickCount.get(entry.getKey());
                if (attCount == null){
                    attCount = 0;
                }
                attCount += entry.getValue();
                attClickCount.put(entry.getKey(), attCount);

            }

        }

        result.setAllClickCount(allAccount);
        result.setAccountInfo(accounts);
        result.setOnlyAccount(onlyAccounts);
        result.setAttClickCount(attClickCount);

        return result;
    }

    private AccountInfo accountData(List<String> addLineDatas){

        Map<String, Integer> accountAddCount = new HashMap<>();
        Set<String> allAccount = new HashSet<>();
        Set<String> onlyAccount = new HashSet<>();
        int allCount = 0;
        AccountInfo accountInfo = new AccountInfo();

        for (String addLineData : addLineDatas){
            String [] datas = addLineData.split(" ");
            String clickCount = datas[4].substring(4, datas[4].length() - 1);

            int count = Integer.parseInt(clickCount);
            String account  = datas[5].substring(5,datas[5].length() - 1);
            String attName = datas[3];

            Integer attCount = accountAddCount.get(attName);
            if (attCount == null){
                attCount = 0;
            }
            attCount += count;
            accountAddCount.put(attName, attCount);
            allCount+= count;

            String patternStr = "(^\\d+\\|\\w*[@,-]?\\w*)";
            Pattern pattern = Pattern.compile(patternStr);

            Matcher matcher = pattern.matcher(account);
            matcher.find();
            String tempAccountName = matcher.group(0) == null? account.substring(0, account.lastIndexOf("|")) : matcher.group(0);

            allAccount.add(tempAccountName);

        }

        for (String accountTemp : allAccount){

            String accName = accountTemp.split("\\|")[0];
            if (onlyAccount.contains(accName)){
                System.out.println(accName);
            }
            onlyAccount.add(accName);

        }

        accountInfo.setAccountInfo(allAccount);
        accountInfo.setOnlyAccount(onlyAccount);
        accountInfo.setAttClickCount(accountAddCount);
        accountInfo.setAllClickCount(allCount);

        return accountInfo;
    }

}
