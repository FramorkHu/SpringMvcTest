package MyGlaxyTest;

import java.util.List;

/**
 * Created by huyan on 16/11/2.
 */
public class QueryAnswer extends GalaxyRomanConversion {


    public void doQuery(){

        for (String query : queryLineList){
            if (query.startsWith("how much")){
                parseQueryLine(query);
            } else if (query.startsWith("how many")){
                parseCreditsQueryLine(query);
            }
        }

    }

    private void parseCreditsQueryLine(String line){

        String pattern = "how\\s+many\\s+credits\\s+is\\s+(.*)(silver|gold|iron)";
        List<String> queryInfos = extractLineInfo(line, pattern);
        if (queryInfos.isEmpty()){
            System.err.println("I have no idea what you are talking about");
            return;
        }

        String romanValue = galaxyNumToRomanNum(queryInfos.get(0));
        double romanDecimal = new RomanCalculateRule().calculateRomanNumber(romanValue);
        String currency = queryInfos.get(1);

        double currencyPrice = creditsInfoMap.get(currency);

        System.out.println(queryInfos.get(0)+" "+currency + " is " + currencyPrice*romanDecimal + " Credits");

    }

    private void parseQueryLine(String line){

        String pattern = "how\\s+much\\s+is\\s+([^?]*)";

        List<String> queryInfos = extractLineInfo(line, pattern);
        if (queryInfos.isEmpty()){
            System.err.println("I have no idea what you are talking about");
            return;
        }

        String romanValue = galaxyNumToRomanNum(queryInfos.get(0));

        double romanDecimal = new RomanCalculateRule().calculateRomanNumber(romanValue);

        System.out.println(queryInfos.get(0)+" is "+ romanDecimal);

    }
}
