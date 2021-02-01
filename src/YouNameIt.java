import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;

public class YouNameIt extends ConsoleProgram implements YouNameItConstants {

    JLabel nameLabel;
    JTextField nameTextField;
    JButton clearButton,queryButton;
    RandomGenerator randomGenerator = RandomGenerator.getInstance();

    public void run() {
        setTitle("名白");

        nameLabel();

        nameTextField();

        createButtons();
        attachListeners();
        addComponentsToProgram();

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
        queryButton = new JButton("查询");
    }

    private void attachListeners() {
        clearButton.addActionListener(this);
        queryButton.addActionListener(this);
    }

    private void addComponentsToProgram() {
        add(queryButton,NORTH);
        add(clearButton,NORTH);
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
            clearConsole();
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
