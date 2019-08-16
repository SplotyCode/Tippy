package io.github.splotycode.tippy.gui;

import io.github.splotycode.tippy.Tippy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcComponent extends JScrollBar implements ActionListener {

    private JTextField input = new JTextField();
    private JPanel results = new JPanel();

    {
        setPreferredSize(new Dimension(800, 400));
        setLayout(new BorderLayout());

        results.setLayout(new BoxLayout(results, BoxLayout.Y_AXIS));

        add(new JScrollPane(results), BorderLayout.CENTER);
        add(input, BorderLayout.SOUTH);
        input.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = input.getText();
        input.setText("");

        String result = Tippy.getInstance().exec(command);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JLabel commandLabel = new JLabel(command);
        JLabel resultLabel = new JLabel(result);
        commandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);


        panel.add(commandLabel);
        panel.add(resultLabel);
        results.add(input);
    }
}
