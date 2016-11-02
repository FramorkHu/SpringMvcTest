package MyGlaxyTest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huyan on 2016/11/2.
 */
public class InputDataParse {

    private Map<String , Double> creditsDataMap = new HashMap<>();
    private Map<String, String> galaxyRomanMap = new HashMap<>();


    public void fileOperate(String filePath) throws IOException {

        File file = new File(filePath);

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line;

        while ((line = reader.readLine())!=null){

            lineOperate(line);
        }

        reader.close();

    }

    private void lineOperate(String lineData){

        String lowCaseLineData = lineData.toLowerCase();
        String[] lineDataArray = lowCaseLineData.trim().split("\\s+");

        if (lowCaseLineData.startsWith("how much")){
            parseQueryLine(lowCaseLineData);
        } else if (lowCaseLineData.startsWith("how many")){
            parseCreditsQueryLine(lowCaseLineData);

        } else if (lowCaseLineData.endsWith("credits")){
            parseCreditsLine(lowCaseLineData);
        } else if (lineDataArray.length == 3 && "is".equals(lineDataArray[1])){
            galaxyRomanMap.put(lineDataArray[0], lineDataArray[2]);
        }
    }


    private void parseCreditsQueryLine(String line){

        String pattern = "how\\s+many\\s+credits\\s+is\\s+(.*)(silver|gold|iron)";
        List<String> queryInfos = parseLine(line, pattern);
        if (queryInfos.isEmpty()){
            System.err.println("I have no idea what you are talking about");
            return;
        }

        String romanValue = galaxyNumToRomanNum(queryInfos.get(0));
        double romanDecimal = new RomanCalculateRule().calculateRomanNumber(romanValue);
        String currency = queryInfos.get(1);

        double currencyPrice = creditsDataMap.get(currency);

        System.out.println(queryInfos.get(0)+" "+currency+" is "+ currencyPrice*romanDecimal + " Credits");

    }

    private void parseQueryLine(String line){

        String pattern = "how\\s+much\\s+is\\s+([^?]*)";

        List<String> queryInfos = parseLine(line, pattern);
        if (queryInfos.isEmpty()){
            System.err.println("I have no idea what you are talking about");
            return;
        }

        String romanValue = galaxyNumToRomanNum(queryInfos.get(0));

        double romanDecimal = new RomanCalculateRule().calculateRomanNumber(romanValue);

        System.out.println(queryInfos.get(0)+" is "+ romanDecimal);

    }

    /**
     * 计算单位货币值
     * @param line
     */
    private void parseCreditsLine(String line){
        String pattern = "(.*)(silver|gold|iron)\\s+is\\s+(\\d+)\\s+credits";
        List<String> creditInfos = parseLine(line, pattern);
        if (creditInfos.isEmpty()){
            System.err.println("I have no idea what you are talking about");
            return;
        }

        String romanValue = galaxyNumToRomanNum(creditInfos.get(0));
        double romanDecimal = new RomanCalculateRule().calculateRomanNumber(romanValue);

        String currency = creditInfos.get(1);
        double credits = Double.parseDouble(creditInfos.get(2));

        creditsDataMap.put(currency, credits/romanDecimal);
    }

    /**
     * 星际数字转化罗马数字
     * @param galaxyNum
     * @return
     */
    private String galaxyNumToRomanNum(String galaxyNum){
        String[] galaxyNumArray = galaxyNum.trim().split("\\s+");
        StringBuilder romanNum = new StringBuilder();
        for (String galaxyNumElem : galaxyNumArray){
            String romanNumElem = galaxyRomanMap.get(galaxyNumElem);
            if (romanNumElem == null){
                throw new RuntimeException("no roman number matched with["+galaxyNumElem+"]");
            }
            romanNum.append(romanNumElem);
        }

        return romanNum.toString();
    }

    public List<String> parseLine(String lineData, String patternString){
        List<String> result = new ArrayList<>();

        String line = lineData.toLowerCase();

        Pattern r = Pattern.compile(patternString);
        Matcher matcher = r.matcher(line);

        if (matcher.find()){

            int count = matcher.groupCount();

            for (int i=1; i<=count; i++){
                result.add(matcher.group(i).trim());
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {


        new InputDataParse().fileOperate("E:\\StudyBench\\SpringMvcTest\\src\\main\\java\\GalaxyTest\\Input");
    }


}
