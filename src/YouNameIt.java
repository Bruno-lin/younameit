import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.util.LinkedList;
import java.util.List;


public class YouNameIt extends GraphicsProgram implements YouNameItConstants {

    JLabel nameLabel;
    JTextField nameTextField;
    JButton clearButton, queryButton, deleteButton;

    GLine gLine;
    GLabel gLabel;
    GOval gOval;

    RandomGenerator randomGenerator = RandomGenerator.getInstance();

    YouNameItDatabase youNameItDatabase = new YouNameItDatabase(NAMES_DATA_FILE);

    int[] ranking = new int[11];
    List<GLabel> namePopupList = new LinkedList<>();
    List<String> searchHistory = new LinkedList<>();

    Font font = new Font("", Font.PLAIN, 18);
    Color[] colors = new Color[8];

    @Override
    public void init() {
        setTitle("名白");

        initColors();

        nameLabel();

        nameTextField();

        createButtons();
        attachListeners();
        addComponentsToProgram();

        panelFrame();

        setVisible(true);
    }

    private void nameLabel() {
        nameLabel = new JLabel("姓名");
        add(nameLabel, NORTH);
    }

    private void nameTextField() {
        nameTextField = new JTextField(TEXT_FIELD_WIDTH);
        nameTextField.setEditable(true);
        nameTextField.setActionCommand("name-text-field");
        nameTextField.addActionListener(this);
        add(nameTextField, NORTH);
    }

    private void createButtons() {
        clearButton = new JButton("清除");
        deleteButton = new JButton("删除");
        queryButton = new JButton("查询");
    }

    private void attachListeners() {
        clearButton.addActionListener(this);
        deleteButton.addActionListener(this);
        queryButton.addActionListener(this);
    }

    private void addComponentsToProgram() {
        add(queryButton, NORTH);
        add(clearButton, NORTH);
        add(deleteButton, NORTH);
    }

    public void actionPerformed(ActionEvent event) {
        String name = nameTextField.getText();

        switch (event.getActionCommand()) {
            case "name-text-field":
            case "查询":
                searchHistory.add(name);
                lineChart();
                namePopup();
                break;
            case "清除":
                repaintChart();
                break;
            case "删除":
                searchHistory.remove(name);
                repaintChart();
                break;
        }
    }

    /**
     * 绘制表格
     */
    private void panelFrame() {
        double width = getWidth();
        double height = getHeight();
        //上线
        gLine = new GLine(0, height * 0.04, width, height * 0.04);
        add(gLine);
        //下线
        gLine = new GLine(0, height * 0.93, width, height * 0.93);
        add(gLine);
        //纵线
        for (int i = 0; i < 12; i++) {
            gLine = new GLine(i * (width / 11.0), 0, i * (width / 11.0), height);
            add(gLine);
        }
        //横坐标
        for (int i = 0; i < 11; i++) {
            gLabel = new GLabel(Integer.toString(1900 + i * 10));
            gLabel.setFont(font);
            add(gLabel, (getWidth()) / 11.0 / 2 - gLabel.getBounds().getWidth() / 2 + i * (getWidth() / 11.0), height * 0.985);
        }
    }

    /**
     * 折线图
     */
    private void lineChart() {
        double width = getWidth();
        double height = getHeight();
        for (String name : searchHistory) {
            Color color = randColor();
            /*折线*/
            for (int i = 0; i < 10; i++) {
                double a = youNameItDatabase.findRecord(name).getRanking(i);
                double b = youNameItDatabase.findRecord(name).getRanking(i + 1);
                gLine = new GLine(i * (width / 11.0) + (width / 22.0), height * 0.93 - (a / 2),
                        (i + 1) * (width / 11.0) + (width / 22.0), height * 0.93 - (b / 2));
                gLine.setColor(color);
                gLine.setLineWidth(2);
                add(gLine);
            }
            /*节点*/
            for (int i = 0; i < 11; i++) {
                double a = youNameItDatabase.findRecord(name).getRanking(i);
                gOval = new GOval(i * (width / 11.0) + (width / 22.0) - 4, height * 0.93 - (a / 2) - 3.5, 6, 6);
                gOval.setColor(color);
                gOval.setFilled(true);
                add(gOval);
            }

            /*节点名字*/
            double a = youNameItDatabase.findRecord(name).getRanking(10);/**/
            gLabel = new GLabel(name);
            gLabel.setColor(randColor());
            gLabel.setFont(font);
            add(gLabel, 10 * (width / 11.0) + (width / 22.0) + 5, height * 0.93 - (a / 2) - 8);
        }

    }

    /**
     * 次数出现最多的名字突出
     */
    private void namePopup() {
        int width = getWidth();
        int height = getHeight();

        String name = nameTextField.getText();
        for (int i = 0; i < 11; i++) {
            if (searchHistory.size() > 1) {
                remove(namePopupList.get(i));
            }

            if (youNameItDatabase.findRecord(name).getRanking(i) >= ranking[i]) {
                ranking[i] = youNameItDatabase.findRecord(name).getRanking(i);
                gLabel = new GLabel(name);
                gLabel.setFont(font);
                add(gLabel, (width / 11.0) / 2 - gLabel.getBounds().getWidth() / 2 + i * (width / 11.0), height * 0.03);
                if (searchHistory.size() == 1) {
                    namePopupList.add(i, gLabel);
                } else {
                    namePopupList.set(i, gLabel);
                }
            }
            add(namePopupList.get(i));
        }
    }

    /**
     * 当缩放窗口时，会自动触发这个函数
     */
    @Override
    public void componentResized(ComponentEvent e) {
        //缩放时重绘表格以及根据查询历史记录恢复图像
        repaintChart();
    }

    /**
     * 重画
     */
    private void repaintChart() {
        //清屏，重绘表格线条和横坐标
        clear();
        panelFrame();
        lineChart();
    }

    private void initColors() {
        colors[0] = new Color(15, 150, 22);
        colors[1] = new Color(0, 0, 0);
        colors[2] = new Color(224, 100, 10);
        colors[3] = new Color(212, 163, 241);
        colors[4] = new Color(6, 100, 238);
        colors[5] = new Color(224, 84, 224);
        colors[6] = new Color(100, 100, 100);
        colors[7] = new Color(100, 100, 255);
    }

    private Color randColor() {
        return colors[randomGenerator.nextInt(0, 7)];
    }
}