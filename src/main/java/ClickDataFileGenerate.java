import com.github.ltsopensource.core.commons.file.FileUtils;
import org.aspectj.util.FileUtil;

import java.io.*;
import java.util.*;

/**
 * Created by huyan on 2016/8/22.
 */
public class ClickDataFileGenerate {

    private static final String fileDir = "E:\\StudyBench\\SpringMvcTest\\src\\main\\clickAdd\\data";
    private static final String outDir = "E:\\StudyBench\\SpringMvcTest\\src\\main\\clickAdd\\generateData";
    private List<String> gzhNames = new ArrayList<>();

    public ClickDataFileGenerate(){

        gzhNames.add("随心之旅");
        gzhNames.add("曼巴足球");
        gzhNames.add("王者荣耀钻石解说");
        gzhNames.add("企鹅漫画");
       /* gzhNames.add("王者荣耀上王者");
        gzhNames.add("体坛咨讯");
        gzhNames.add("美女爱渣男");
        gzhNames.add("八卦热点头条");
        gzhNames.add("尚女神");*/
    }

    public static void main(String[] args) throws Exception {


        new  ClickDataFileGenerate().writeFile();



    }

    private void writeFile() throws IOException {

        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outDir));

        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        List<String> generateDataList = doGenerate();

        for (String generateData : generateDataList){
            bufferedWriter.write(generateData);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

    }

    private List<String> doGenerate() throws IOException {

        List<String> result = new ArrayList<>();

        List<String> accountDataList = getFileDatas();

        Collections.shuffle(accountDataList);

        for (String accountData : accountDataList){

            Random random = new Random();
            int n = random.nextInt(gzhNames.size());

            String gzh = gzhNames.get(n);
            result.add(accountData+"|"+gzh);

        }

        Collections.shuffle(result);

        return result;
    }


    private List<String > getFileDatas() throws IOException {

        List<String> result= new ArrayList<>();

        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(fileDir)); // 建立一个输入流对象reader

        BufferedReader br = new BufferedReader(reader);

        String line = br.readLine();
        while (line != null){
            result.add(line);

            line = br.readLine();
        }

        br.close();
        return result;
    }
}
