/**
 * 请创建一个YouNameItRecord类，实现该接口，用来表示数据库中的一行记录
 */
public interface YouNameItRecordInterface {
    /**
     * 对一行数据进行解析，并保存进当前记录中。然后即可使用getName、getRanking、toString来获取记录中的内容。
     *
     * @param dataLine 一行数据，以字符串表示，一般来自names-data.txt中的某一行
     */
    void parseRecord(String dataLine);

    /**
     * 返回当前记录的名字
     *
     * @return 名字
     */
    String getName();

    /**
     * 返回当前记录的名字在某个十年里的排名
     *
     * @param decadesAfter1900 第几个十年，0代表1900-1909，1代表1910-1919，以此类推
     * @return 该名字在对应十年期间的排名
     */
    String getRanking(String decadesAfter1900);

    /**
     * 返回当前记录的字符串表示，方便用户迅速理解此记录的内容。例如"Daniel 56 55 44 40 22 19 18 11 6 7 9"应返回
     * "Daniel [56, 55, 44, 40, 22, 19, 18, 11, 6, 7, 9]"
     * 注意：格式必须严格按照上一行的例子所示
     *
     * @return 当前记录的字符串表示
     */
    String toString();
}
