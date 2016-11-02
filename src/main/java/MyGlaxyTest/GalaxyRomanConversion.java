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
public class GalaxyRomanConversion {

    protected Map<String , Double> creditsInfoMap = new HashMap<>();
    protected Map<String, String> galaxyRomanValueMap = new HashMap<>();
    protected List<String> queryLineList = new ArrayList<>();
    protected List<String> queryCreditsList = new ArrayList<>();

    public void readConversionInfo(String infoFilePath) throws IOException {
        File file = new File(infoFilePath);

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line;

        while ((line = reader.readLine())!=null){

            fileLineParse(line);
        }

        reader.close();
    }

    private void fileLineParse(String lineData){
        String lowCaseLineData = lineData.toLowerCase();
        String[] lineDataArray = lowCaseLineData.trim().split("\\s+");

        if (lowCaseLineData.startsWith("how much")){
            queryLineList.add(lowCaseLineData);
        } else if (lowCaseLineData.startsWith("how many")){
            queryCreditsList.add(lowCaseLineData);
        } else if (lowCaseLineData.endsWith("credits")){
            parseCreditsLine(lowCaseLineData);
        } else if (lineDataArray.length == 3 && "is".equals(lineDataArray[1])){
            galaxyRomanValueMap.put(lineDataArray[0], lineDataArray[2]);
        }
    }

    /**
     * 计算单位货币值
     * @param line
     */
    private void parseCreditsLine(String line){
        String pattern = "(.*)(silver|gold|iron)\\s+is\\s+(\\d+)\\s+credits";
        List<String> creditInfos = extractLineInfo(line, pattern);
        if (creditInfos.isEmpty()){
            System.err.println("I have no idea what you are talking about");
            return;
        }

        String romanValue = galaxyNumToRomanNum(creditInfos.get(0));
        double romanDecimal = new RomanCalculateRule().calculateRomanNumber(romanValue);

        String currency = creditInfos.get(1);
        double credits = Double.parseDouble(creditInfos.get(2));

        creditsInfoMap.put(currency, credits/romanDecimal);
    }

    /**
     * 星际数字转化罗马数字
     * @param galaxyNum
     * @return
     */
    protected String galaxyNumToRomanNum(String galaxyNum){
        String[] galaxyNumArray = galaxyNum.trim().split("\\s+");
        StringBuilder romanNum = new StringBuilder();
        for (String galaxyNumElem : galaxyNumArray){
            String romanNumElem = galaxyRomanValueMap.get(galaxyNumElem);
            if (romanNumElem == null){
                throw new RuntimeException("no roman number matched with["+galaxyNumElem+"]");
            }
            romanNum.append(romanNumElem);
        }

        return romanNum.toString();
    }

    /**
     * 抽取数据行中的信息
     * @param lineData
     * @param patternString
     * @return
     */
    protected List<String> extractLineInfo(String lineData, String patternString){
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
}
