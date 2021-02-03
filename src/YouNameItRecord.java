import java.util.Arrays;

public class YouNameItRecord implements YouNameItRecordInterface {

    private String name;
    private String[] ranks;

    /**
     * 对一行数据进行解析，并保存进当前记录中。然后即可使用getName、getRanking、toString来获取记录中的内容。
     *
     * @param dataLine 一行数据，以字符串表示，一般来自names-data.txt中的某一行
     */
    @Override
    public void parseRecord(String dataLine) {
        //split()通过指定分隔符对字符串进行切片
        // \\s 表示 空格,回车,换行等空白符,
        //+号表示一个或多个的意思
        if (dataLine == null) return;
        ranks = dataLine.split("\\s+");
        name = ranks[0];
        ranks = Arrays.copyOfRange(ranks, 1, ranks.length);
    }

    /**
     * 返回当前记录的名字
     *
     * @return 名字
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * 返回当前记录的名字在某个十年里的排名
     *
     * @param decadesAfter1900 第几个十年，0代表1900-1909，1代表1910-1919，以此类推
     * @return 该名字在对应十年期间的排名
     */
    @Override
    public int getRanking(int decadesAfter1900) {
        if (ranks == null) return -1;
            return Integer.parseInt(ranks[decadesAfter1900]);
    }

    /**
     * 返回当前记录的字符串表示，方便用户迅速理解此记录的内容。例如"Daniel 56 55 44 40 22 19 18 11 6 7 9"应返回
     * "Daniel [56, 55, 44, 40, 22, 19, 18, 11, 6, 7, 9]"
     * 注意：格式必须严格按照上一行的例子所示
     *
     * @return 当前记录的字符串表示
     */
    @Override
    public String toString() {
        return (name + " " + Arrays.toString(ranks));
    }
}
