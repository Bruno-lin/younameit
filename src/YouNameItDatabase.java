import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class YouNameItDatabase {
    public Map<String, String> database;

    public YouNameItDatabase(String filePath) throws IOException {
        database = new HashMap<>();

        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while (true) {
            // 一次读一行
            String line = bufferedReader.readLine();
            if (null == line)
                break;
            //substring() 方法返回字符串的子字符串.
            String name = line.substring(0, Integer.parseInt(" "));
            database.put(name, line);
        }
    }

    public YouNameItRecord findRecord(String name) {
         String key = database.get(name);
         YouNameItRecord record = new YouNameItRecord();
         record.parseRecord(key);
         return record;
    }
}