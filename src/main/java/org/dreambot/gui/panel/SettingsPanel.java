package org.dreambot.gui.panel;

import org.dreambot.gui.components.DreamButton;
import org.dreambot.gui.components.DreamCheckBox;
import org.dreambot.gui.components.DreamLabel;
import org.dreambot.gui.components.DreamPanel;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsPanel extends DreamPanel {
    private DreamCheckBox profitModeCheckbox = new DreamCheckBox();
    private DreamPanel content;
    public SettingsPanel() {
        this.setBorder(new EmptyBorder(7, 8, 7, 8));
        this.add(content = new DreamPanel(), BorderLayout.NORTH);
        content.setLayout(new GridLayout(0, 2));
        content.add(new DreamLabel("Prioritise Profit"));
        content.add(profitModeCheckbox);
    }

    public DreamCheckBox getProfitModeCheckbox() {
        return profitModeCheckbox;
    }
}
