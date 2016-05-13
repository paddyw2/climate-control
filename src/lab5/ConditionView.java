package lab5;

import javax.swing.*;
import java.awt.*;

/**
 * <h1>Condition JPanel Template</h1>
 *
 * This is the JPanel template that all condition
 * views are based on.
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class ConditionView extends JPanel {

    private JLabel title;
    private JLabel temp;
    private JLabel status;
    private JButton settingsButton;

    public ConditionView(String panelTitle, String buttonName)
    {
        this.setLayout(new GridBagLayout());

        this.setBackground(new Color(251, 251, 251));

        title = new JLabel(panelTitle);
        title.setForeground(new Color(69, 69, 69));
        temp = new JLabel("-");
        temp.setForeground(new Color(69, 69, 69));
        title.setForeground(new Color(69, 69, 69));
        status = new JLabel("No status");
        status.setForeground(new Color(69, 69, 69));
        settingsButton = new JButton("Settings");
        settingsButton.setName(buttonName);

        addItem(this, title, 0, 0, 1, 1, GridBagConstraints.NORTH);
        addItem(this, temp, 0, 1, 1, 1, GridBagConstraints.NORTH);
        addItem(this, status, 0, 2, 1, 1, GridBagConstraints.NORTH);
        addItem(this, settingsButton, 0, 3, 1, 1, GridBagConstraints.SOUTH);

        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    }

    /**
     * <h1>Set Panel Status</h1>
     *
     * Allows the panel status JLabel to be set
     *
     * @param text
     */
    public void setStatus(String text)
    {
        if(text.equals("On"))
            status.setForeground(new Color(88, 238, 124));
        else if (text.equals("Off"))
            status.setForeground(new Color(255, 118, 114));
        else
            status.setForeground(new Color(69, 69, 69));

        status.setText(text);
    }

    /**
     * <h1>Set Panel Condition</h1>
     *
     * Allows the panel condition JLabel to be set
     *
     * @param text
     */
    public void setTemp(String text)
    {
        temp.setText(text);
    }


    /**
     * <h1>GridBagLayout Add Method</h1>
     *
     * Allows components to be added in accordance with
     * GridBagLayout
     *
     * @param p
     * @param c
     * @param x
     * @param y
     * @param width
     * @param height
     * @param align
     */
    public void addItem(JPanel p, Container c, int x, int y, int width, int height, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        p.add(c, gc);
    }

    /**
     * <h1>Add Settings Button Listener</h1>
     *
     * Adds a listener to the panel settings button
     *
     * @param listener
     */
    public void addButtonListener(ClickListener listener) {
        settingsButton.addActionListener(listener);
    }

}
