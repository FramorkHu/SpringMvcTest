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
    private List<Name> gzhNames = new ArrayList<>();

    public ClickDataFileGenerate(){

        gzhNames.add(new Name("随心之旅", "sxzn"));
        gzhNames.add(new Name("曼巴足球","mbzq"));
        gzhNames.add(new Name("王者荣耀钻石解说","wzry"));
        gzhNames.add(new Name("企鹅漫画","qemh"));
        /*gzhNames.add(new Name("王者荣耀上王者","wzryswz"));
        gzhNames.add(new Name("体坛咨讯","ttzx"));
        gzhNames.add(new Name("美女爱渣男","mnazn"));
        gzhNames.add(new Name("八卦热点头条","bgrdtt"));
        gzhNames.add(new Name("尚女神","sns"));*/

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


        Map<String, List<String>> datas = new HashMap<>();

        List<String> result = new ArrayList<>();

        List<String> accountDataList = getFileDatas();

        Collections.shuffle(accountDataList);

        for (String accountData : accountDataList){

            Random random = new Random();
            int n = random.nextInt(gzhNames.size());

            Name gzh = gzhNames.get(n);
            result.add(accountData+"|"+gzh.getExt());


            List<String> array = datas.get(gzh.getName());
            if (array == null){
                array = new ArrayList<>();
            }
            array.add(accountData);
            datas.put(gzh.getName(), array);

        }

        Collections.shuffle(result);

        for (Map.Entry<String, List<String>> entry: datas.entrySet()){

            System.out.println(entry.getKey()+" "+entry.getValue().size());
        }
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


class Name{


    public Name(String name, String ext){
        this.name = name;
        this.ext = ext;
    }
    private String name;
    private String ext;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}