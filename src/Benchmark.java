import java.util.*;

class Record {
    String name;
    String data;
}

public class Benchmark {

    public static final int SEED = 10;
    public static final int NAME_LENGTH = 6;        // 名字的长度
    public static final int DATA_LENGTH = 10;       // 数据的长度，即有10个排名信息
    public static final int INSERT_SIZE = 1000;     // 插入1000个随机数据
    public static final int QUERY_SIZE = 1000;      // 查询次数

    Random random = new Random();
    List<Record> list = new ArrayList<>();
    Map<String, Record> map = new HashMap<>();

    void resetSeed(int seed) {
        random.setSeed(seed);
    }

    // 随机生成名字
    String generateRandomName() {
        int a = 97;
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < NAME_LENGTH; i++) {
            // FIXME: 用了较为低效的String操作来生成随机字符串
            name.append((char) (random.nextInt(26) + a));    // 产生一个a-z之间的随机字母
        }
        return name.toString();
    }

    // 随机生成排名数据
    String generateRandomData() {
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < DATA_LENGTH; i++) {
            // 用了高效的StringBuilder操作的生成随机字符串
            data.append(random.nextInt(1000) + 1);   // 产生一个1-1000之间的随机数
            data.append(' ');
        }
        return data.toString();
    }

    // 随机生成entry
    Record generateRandomEntry() {
        Record record = new Record();
        record.name = generateRandomName();
        record.data = generateRandomData();
        return record;
    }

    void insertToList() {
        for (int i = 0; i < INSERT_SIZE; i++) {
            Record record = generateRandomEntry();
            list.add(record);
        }
    }

    void insertToMap() {
        for (int i = 0; i < INSERT_SIZE; i++) {
            Record record = generateRandomEntry();
            map.put(record.name, record);
        }
    }

    int queryList() {
        int count = 0;

        for (int i = 0; i < QUERY_SIZE; i++) {
            String name = generateRandomName();
            for (Record record : list) {
                if (record.name.equals(name)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    int queryMap() {
        int count = 0;

        for (int i = 0; i < QUERY_SIZE; i++) {
            String name = generateRandomName();
            if (map.containsKey(name)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        long startTime, endTime, duration;

        Benchmark benchmark = new Benchmark();

        benchmark.resetSeed(SEED);
        startTime = System.nanoTime();
        benchmark.insertToList();
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;
        System.out.println("插入list耗时: " + duration + "ms");

        benchmark.resetSeed(SEED);
        startTime = System.nanoTime();
        benchmark.insertToMap();
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;
        System.out.println("插入hashmap耗时: " + duration + "ms");

        int count;
        benchmark.resetSeed(SEED + 1);
        startTime = System.nanoTime();
        count = benchmark.queryList();
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;
        System.out.println("查询list耗时的：" + duration + "ms，找到" + count + "匹配项");

        benchmark.resetSeed(SEED + 1);
        startTime = System.nanoTime();
        count = benchmark.queryMap();
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;
        System.out.println("查询hashmap耗时: " + duration + "ms，找到" + count + "匹配项");
    }
}
