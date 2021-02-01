import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;

public class YouNameIt extends GraphicsProgram implements YouNameItConstants {

    JLabel nameLabel;
    JTextField nameTextField;
    JButton clearButton, queryButton;
    GLine panelLine;
    GLabel panelLabel;
    RandomGenerator randomGenerator = RandomGenerator.getInstance();

    public void run() {
        setTitle("名白");

        nameLabel();

        nameTextField();

        createButtons();
        attachListeners();
        addComponentsToProgram();

        panelFrame();

        setVisible(true);
    }

    private void panelFrame() {
        panelLine = new GLine(0, 20, APPLICATION_WIDTH + 10, 20);
        add(panelLine);

        panelLine = new GLine(0, APPLICATION_HEIGHT - 95, APPLICATION_WIDTH + 20, APPLICATION_HEIGHT - 95);
        add(panelLine);

        for (int i = 0; i < 11; i++) {
            panelLine = new GLine(i * 80, 0, i * 80, APPLICATION_HEIGHT);
            add(panelLine);
        }

        for (int i = 0; i < 11; i++) {
            panelLabel = new GLabel(Integer.toString(1900 + i * 10));
            add(panelLabel, i * 80, APPLICATION_HEIGHT - 75);
        }
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
        queryButton = new JButton("查询");
    }

    private void attachListeners() {
        clearButton.addActionListener(this);
        queryButton.addActionListener(this);
    }

    private void addComponentsToProgram() {
        add(queryButton, NORTH);
        add(clearButton, NORTH);
    }

    public void actionPerformed(ActionEvent event) {
        var command = event.getActionCommand();
        if (command.equals("name-text-field")) {
            if (randomGenerator.nextBoolean()) {
                println("「" + nameTextField.getText() + "」这个名字非常吉利！");
            } else {
                println("「" + nameTextField.getText() + "」这个名字好像不太受欢迎……");
            }
        }
        if (command.equals("清除"))
//            clearConsole();
            if (command.equals("查询")) {
                if (randomGenerator.nextBoolean()) {
                    println("「" + nameTextField.getText() + "」这个名字非常吉利！");
                } else {
                    println("「" + nameTextField.getText() + "」这个名字好像不太受欢迎……");
                }
            }
    }

    /**
     * 当缩放窗口时，会自动触发这个函数
     */
    @Override
    public void componentResized(ComponentEvent e) {
    }
}
