package io.github.splotycode.tippy.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TippyWindow extends JFrame {

    private GrapthComponent grapthComponent = new GrapthComponent();
    private CalcComponent calcComponent = new CalcComponent();
    private JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, calcComponent, grapthComponent);

    private JMenuBar menu = new JMenuBar();
    private JMenu tools = new JMenu("Tools");

    protected void addMenuItem(JMenu menu, String name, ActionListener listener) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(listener);
        menu.add(item);
    }

    public TippyWindow() throws HeadlessException {
        setTitle("Tippy");
        add(split);

        addMenuItem(tools, "Werte Tabelle", event -> new ValueTable());
        menu.add(tools);

        setJMenuBar(menu);
        pack();
        setVisible(true);
    }
}
