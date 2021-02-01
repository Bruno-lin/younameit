import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class YouNameItDatabase {
    public List<String> database;

    public YouNameItDatabase(String filePath) throws IOException {
        database = new ArrayList<>();

        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while (true) {
            // 一次读一行
            String line = bufferedReader.readLine();
            if (null == line)
                break;
            System.out.println(line);
            database.add(line);
        }
    }


}