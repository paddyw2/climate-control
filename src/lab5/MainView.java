package lab5;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * <h1>Main View</h1>
 *
 * This is the main view of the application.
 * <br>
 * From here the user can start/stop the simulation, and
 * set the base and target temperatures.
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class MainView extends JFrame {

    private JPanel mainPanel;
    private Container titleContainer;
    private Container iconContainer;
    private Container buttonContainer;
    private Font font;
    private JLabel titleText;
    private JLabel iconLabel;
    private JButton stopButton;
    private JButton startButton;
    private JButton saveSettingsButton;
    private JButton viewSettingsButton;
    private JPanel superPanel;

    // environment panels
    private JPanel subPanel;
    private ConditionView furnacePanel;
    private ConditionView airConPanel;
    private ConditionView humidPanel;
    private ConditionView moisturePanel;

    public MainView() {
        super("Climate Control");
        this.setSize(800, 550);
        this.setLocationRelativeTo(null);

        titleContainer = new Container();
        iconContainer = new Container();
        buttonContainer = new Container();
        titleContainer.setLayout(new GridBagLayout());
        iconContainer.setLayout(new GridBagLayout());
        buttonContainer.setLayout(new GridBagLayout());

        // load custom font
        try {
            InputStream is = MainView.class.getResourceAsStream("assets/font.ttf");
            float size = 30;
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(size);

        } catch (IOException e) {
            System.out.println("Invalid file path");
        } catch (FontFormatException e) {
            System.out.println("Format exception");
        }

        titleText = new JLabel();
        titleText.setFont(font);
        titleText.setForeground(new Color(52, 71, 87));
        titleText.setText("Climate Control");

        iconLabel = new JLabel(new ImageIcon(MainView.class.getResource("assets/icon.png")));

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(247, 242, 236));
        mainPanel.setLayout(new GridBagLayout());

        stopButton = new JButton("Stop");
        stopButton.setName("stopButton");

        startButton = new JButton("Start");
        startButton.setName("startButton");

        saveSettingsButton = new JButton("Save Settings");
        saveSettingsButton.setName("saveSettingsButton");

        viewSettingsButton = new JButton("View Saved Settings");
        viewSettingsButton.setName("viewSettingsButton");

        addItem(titleContainer, titleText, 0, 0, 1, 1, GridBagConstraints.NORTH);
        addItem(iconContainer, iconLabel, 0, 0, 1, 1, GridBagConstraints.CENTER);
        addItem(buttonContainer, startButton, 0, 3, 1, 1, GridBagConstraints.SOUTH);
        addItem(buttonContainer, stopButton, 1, 3, 1, 1, GridBagConstraints.SOUTH);
        addItem(buttonContainer, saveSettingsButton, 2, 3, 1, 1, GridBagConstraints.SOUTH);
        addItem(buttonContainer, viewSettingsButton, 3, 3, 1, 1, GridBagConstraints.SOUTH);

        addItem(mainPanel, titleContainer, 0, 0, 1, 1, GridBagConstraints.NORTH);
        addItem(mainPanel, iconContainer, 0, 1, 1, 1, GridBagConstraints.CENTER);
        addItem(mainPanel, buttonContainer, 0, 2, 1, 1, GridBagConstraints.SOUTH);

        // sub panel setup

        superPanel = new JPanel();
        superPanel.setLayout(new GridLayout(2,0));
        superPanel.add(mainPanel);

        subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(0,4));

        furnacePanel = new ConditionView("Furnace", "furnaceButton");
        airConPanel = new ConditionView("Air Con", "airConButton");
        humidPanel = new ConditionView("Humidifier", "humidButton");
        moisturePanel = new ConditionView("Sprinkler", "moistureButton");

        refreshView();

    }

    /**
     * <h1>Update Temp Details</h1>
     *
     * This method allows the temperature panels to be updated.
     *
     * @param data
     */
    public void updateTemp(double[] data)
    {
        String status;
        furnacePanel.setTemp(String.valueOf(data[2]) + "C");
        airConPanel.setTemp(String.valueOf(data[2]) + "C");

        if (data[4] == 1)
            status = "On";
        else
            status = "Off";

        furnacePanel.setStatus(status);

        if (data[5] == 1)
            status = "On";
        else
            status = "Off";

        airConPanel.setStatus(status);

        refreshView();
    }

    /**
     * <h1>Update Humidity Details</h1>
     *
     * This method allows the temperature panels to be updated.
     *
     * @param data
     */
    public void updateHumid(double[] data)
    {
        String status;
        humidPanel.setTemp(String.valueOf(data[2]) + "%");

        if (data[4] == 1 && data[5] == 1)
            status = "On";
        else
            status = "Off";

        humidPanel.setStatus(status);
        refreshView();
    }

    /**
     * <h1>Update Moisture Details</h1>
     *
     * This method allows the moisture panels to be updated.
     *
     * @param data
     */
    public void updateMoisture(double[] data)
    {
        String status;
        moisturePanel.setTemp(String.valueOf(data[2]) + "%");

        if (data[4] == 1 && data[5] == 1)
            status = "On";
        else
            status = "Off";

        moisturePanel.setStatus(status);
        refreshView();
    }

    /**
     * <h1>Refresh Main View</h1>
     *
     * Refreshes and repaints the main window.
     * <br>
     * Used when any change is made.
     */
    public void refreshView()
    {
        subPanel.add(furnacePanel);
        subPanel.add(airConPanel);
        subPanel.add(humidPanel);
        subPanel.add(moisturePanel);

        superPanel.add(subPanel);

        this.add(superPanel);

        revalidate();
        repaint();
    }

    /**
     * <h1>Reset All Panels to Default Settings</h1>
     *
     * Used when simulation is stopped
     */
    public void resetSimulation()
    {
        String status = "No status";
        furnacePanel.setStatus(status);
        airConPanel.setStatus(status);
        humidPanel.setStatus(status);
        moisturePanel.setStatus(status);
        String temp = "-";
        furnacePanel.setTemp(temp);
        airConPanel.setTemp(temp);
        humidPanel.setTemp(temp);
        moisturePanel.setTemp(temp);
        refreshView();
    }

    // GridBagLayout add methods

    /**
     * <h1>GridBagLayout Add Method 1</h1>
     *
     * Takes a container instead of panel as parameter
     *
     * @param p
     * @param c
     * @param x
     * @param y
     * @param width
     * @param height
     * @param align
     */
    public void addItem(Container p, JComponent c, int x, int y, int width, int height, int align) {
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
     * <h1>GridBagLayout Add Method 2</h1>
     *
     * Takes a panel as a parameter, rather than a container
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
     * <h1>Add Click Listeners to Buttons</h1>
     *
     * Adds the general click listener to all buttons
     *
     * @param listener
     */
    public void addListener(ClickListener listener) {
        stopButton.addActionListener(listener);
        startButton.addActionListener(listener);
        saveSettingsButton.addActionListener(listener);
        viewSettingsButton.addActionListener(listener);
        furnacePanel.addButtonListener(listener);
        airConPanel.addButtonListener(listener);
        humidPanel.addButtonListener(listener);
        moisturePanel.addButtonListener(listener);
    }

    /**
     * <h1>Display Message Pop Up</h1>
     *
     * Used when handling errors, or notifying the user of an event
     *
     * @param message
     */
    public void displayError(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }

}
