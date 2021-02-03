import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class YouNameItDatabase {
    public Map<String, String> database;

    public YouNameItDatabase(String filePath) {
        database = new HashMap<>();
        readFile(filePath);
    }

    private void readFile(String filePath) {
        File file = new File(filePath);
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (true) {
                // 一次读一行
                String line = bufferedReader.readLine();
                if (null == line)
                    break;
                //substring() 方法返回字符串的子字符串.
                String name = line.substring(0, line.indexOf(" "));
                database.put(name, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YouNameItRecord findRecord(String name) {
        String key = database.get(name);
        YouNameItRecord record = new YouNameItRecord();
        record.parseRecord(key);
        return record;
    }
}