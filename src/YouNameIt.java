import acm.program.*;
import acm.util.RandomGenerator;

import javax.swing.*;
import java.awt.event.*;

public class YouNameIt extends ConsoleProgram implements YouNameItConstants {

    JLabel nameLabel;
    JTextField nameTextField;
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

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("name-text-field")) {
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
