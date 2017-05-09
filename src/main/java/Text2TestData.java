/**
 * Created by User on 08.05.2017.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Text2TestData {

    private String fileLocation;

    public Text2TestData(String fileLocation) {
        this.fileLocation = fileLocation;
    }



    public HashMap<String,String[]> getData() throws IOException {
        FileInputStream fs;
        HashMap<String,String[]> keyValuePair=new HashMap<String,String[]>();
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
            String stringLine;
            //Read File Line By Line
            while ((stringLine = br.readLine()) != null) {
                // Print the content on the console
                String[] keyValue = stringLine.split("=");
                keyValuePair.put(keyValue[0], keyValue[1].split(","));
            }


        return keyValuePair;


    }

}
