package io.github.splotycode.tippy.gui;

import javax.swing.*;
import java.awt.*;

public class TippyWindow extends JFrame {

    private GrapthComponent grapthComponent = new GrapthComponent();
    private CalcComponent calcComponent = new CalcComponent();
    private JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, calcComponent, grapthComponent);

    public TippyWindow() throws HeadlessException {
        setTitle("Tippy");
        add(split);
        pack();
        setVisible(true);
    }
}
