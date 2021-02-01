import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;

public class YouNameIt extends ConsoleProgram implements YouNameItConstants {

    JLabel nameLabel;
    JTextField nameTextField;
    JButton clearButton;
    RandomGenerator randomGenerator = RandomGenerator.getInstance();

    public void run() {
        setTitle("名白");


        nameLabel = new JLabel("姓名");
        add(nameLabel, NORTH);

        nameTextField = new JTextField(TEXT_FIELD_WIDTH);
        nameTextField.setEditable(true);
        nameTextField.setActionCommand("name-text-field");
        nameTextField.addActionListener(this);
        add(nameTextField, NORTH);

        btn();

        setVisible(true);
    }

    private void btn() {
        clearButton = new JButton("清除");
        clearButton.setEnabled(true);
        clearButton.addActionListener(this);
        add(clearButton, EAST);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("name-text-field")) {
            if (randomGenerator.nextBoolean()) {
                println("「" + nameTextField.getText() + "」这个名字非常吉利！");
            } else {
                println("「" + nameTextField.getText() + "」这个名字好像不太受欢迎……");
            }
        }
        if (event.getActionCommand().equals("清除")) clearConsole();
    }

    /**
     * 当缩放窗口时，会自动触发这个函数
     */
    @Override
    public void componentResized(ComponentEvent e) {
    }
}
