package org.dreambot.gui;

import org.dreambot.gui.components.DreamButton;
import org.dreambot.gui.components.DreamFrame;
import org.dreambot.gui.components.DreamPanel;
import org.dreambot.gui.components.DreamTabbedPane;
import org.dreambot.gui.panel.SettingsPanel;

import java.awt.*;

public class MyGUI extends DreamFrame {
    DreamPanel settingsPanel = new SettingsPanel();
    DreamPanel content = new DreamPanel();
    public MyGUI() {
        super("FOSS AIO");
        DreamTabbedPane tabbedPane = new DreamTabbedPane();
        tabbedPane.add("Settings", settingsPanel);


        setResizable(true);
        setSize(500,200);
        content.setLayout(new GridLayout(2, 0));
        content.add(tabbedPane, BorderLayout.CENTER);
        content.add(new DreamButton("test"));
        this.add(content);
    }

    public static void main(String[] args) {
        MyGUI gui = new MyGUI();
        gui.setVisible(true);
    }
}
