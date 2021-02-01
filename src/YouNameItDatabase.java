import java.util.ArrayList;
import java.util.List;

public class YouNameItDatabase {
    public List<String> database;

    public YouNameItDatabase() {
        database = new ArrayList<>();

        database.add("Aaron 193 208 218 274 279 232 132 36 32 31 41");
        database.add("Lily 295 341 353 456 713 873 902 0 705 326 126");
    }

    public String findRecord(String name) {
        return database.stream()
                .filter(record -> record.startsWith(name))
                .findFirst()
                .orElse(null);
    }
}