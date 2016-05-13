package lab5;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * <h1>Saved Settings View</h1>
 *
 * This is the saved settings JDialog pop up.
 * <br>
 * Here users can view their saved settings for
 * each of the conditions.
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class SavedSettingsView extends JDialog {

    private JLabel title;
    private JPanel panel;
    private JButton closeButton;

    // temp components
    private JLabel tempTitle;
    private JLabel tempLower;
    private JLabel tempUpper;
    private JLabel tempCurrent;
    private JLabel tempOrig;
    private JLabel furnaceStatus;
    private JLabel airConStatus;
    private JLabel tempExternal;
    private JLabel tempSample;
    private JLabel tempRate;

    // humidity components
    private JLabel humidTitle;
    private JLabel humidLower;
    private JLabel humidUpper;
    private JLabel humidCurrent;
    private JLabel humidOrig;
    private JLabel humidifierStatus;
    private JLabel humidifierOn;
    private JLabel humidExternal;
    private JLabel humidSample;
    private JLabel humidRate;

    // humidity components
    private JLabel moistureTitle;
    private JLabel moistureLower;
    private JLabel moistureUpper;
    private JLabel moistureCurrent;
    private JLabel moistureOrig;
    private JLabel sprinklerStatus;
    private JLabel sprinklerOn;
    private JLabel moistureExternal;
    private JLabel moistureSample;
    private JLabel moistureRate;

    // data
    private double[] tempData;
    private double[] humidData;
    private double[] moistureData;

    private Font font;

    /**
     * <h1>Constructor</h1>
     *
     * Takes parent, and data for each condition as parameters.
     *
     * Gets data from each condition array and sets it in
     * the view
     *
     * @param parent
     * @param tempData
     * @param humidData
     * @param moistureData
     */
    public SavedSettingsView(JFrame parent, double[] tempData, double[] humidData, double[] moistureData) {
        super(parent, true);

        setTitle("View Saved Settings");
        this.tempData = tempData;
        this.humidData = humidData;
        this.moistureData = moistureData;

        try {
            InputStream is = MainView.class.getResourceAsStream("assets/font.ttf");
            float size = 24;
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(size);
        } catch (IOException e) {
            System.out.println("Invalid file path");
        } catch (FontFormatException e) {
            System.out.println("Format exception");
        }

        title = new JLabel();
        title.setFont(font);
        title.setForeground(new Color(52, 71, 87));
        title.setText("Climate Control Settings");

        closeButton = new JButton("Close");
        closeButton.setName("closeSavedSettings");
        setSize(750,400);
        this.setLocationRelativeTo(null);
        panel = new JPanel();
        panel.setBackground(new Color(247, 242, 236));
        panel.setLayout(new GridBagLayout());

        addItem(panel, title, 1, 0, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, closeButton, 1, 20, 1, 1, GridBagConstraints.SOUTH);

        addTempSettings();
        addHumidSettings();
        addMoistureSettings();

        add(panel);
    }

    /**
     * <h1>Add Temp Settings to View</h1>
     *
     * Gets data from temperature data array, and
     * updates view with this data
     */
    public void addTempSettings()
    {
        tempTitle = new JLabel("<html><strong style='color: #545454;'>Temperature Settings</strong></html>");
        tempLower = new JLabel("Lower Temp: " + String.valueOf(tempData[0]) + "C");
        tempUpper = new JLabel("Upper Temp: " + String.valueOf(tempData[1])+ "C");
        tempCurrent = new JLabel("Current Temp: " + String.valueOf(tempData[2])+ "C");
        tempOrig = new JLabel("Original Temp: " + String.valueOf(tempData[3])+ "C");
        furnaceStatus= new JLabel("Furnace Status: " + String.valueOf(tempData[4]));
        airConStatus = new JLabel("Air Con Status: " + String.valueOf(tempData[5]));
        tempExternal = new JLabel("External Weather: " + String.valueOf(tempData[6])+ "C/s");
        tempSample = new JLabel("Sample Rate: " + String.valueOf(tempData[7])+ "C/s");
        tempRate = new JLabel("Temp Rate: " + String.valueOf(tempData[8])+ "C/s");

        addItem(panel, tempTitle, 0, 1, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, tempLower, 0, 2, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, tempUpper, 0, 3, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, tempCurrent, 0, 4, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, tempOrig, 0, 5, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, furnaceStatus, 0, 6, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, airConStatus, 0, 7, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, tempExternal, 0, 8, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, tempSample, 0, 9, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, tempRate, 0, 10, 1, 1, GridBagConstraints.NORTH);
    }

    /**
     * <h1>Add Humidity Settings to View</h1>
     *
     * Gets data from humidity data array, and
     * updates view with this data
     */
    public void addHumidSettings()
    {
        humidTitle = new JLabel("<html><strong style='color: #545454;'>Humidifier Settings</strong></html>");
        humidLower = new JLabel("Lower Humidity: " + String.valueOf(humidData[0]) + "%");
        humidUpper = new JLabel("Upper Humidity: " + String.valueOf(humidData[1]) + "%");
        humidCurrent = new JLabel("Current Humidity: " + String.valueOf(humidData[2]) + "%");
        humidOrig = new JLabel("Original Humidity: " + String.valueOf(humidData[3]) + "%");
        humidifierStatus = new JLabel("Humidifier On: " + String.valueOf(humidData[4]));
        humidifierOn = new JLabel("Environment On: " + String.valueOf(humidData[5]));
        humidExternal = new JLabel("External Weather: " + String.valueOf(humidData[6]) + "%/s");
        humidSample = new JLabel("Sample Rate: " + String.valueOf(humidData[7]) + "%/s");
        humidRate = new JLabel("Humidity Rate: " + String.valueOf(humidData[8]) + "%/s");

        addItem(panel, humidTitle, 1, 1, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, humidLower, 1, 2, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, humidUpper, 1, 3, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, humidCurrent, 1, 4, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, humidOrig, 1, 5, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, humidifierStatus, 1, 6, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, humidifierOn, 1, 7, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, humidExternal, 1, 8, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, humidSample, 1, 9, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, humidRate, 1, 10, 1, 1, GridBagConstraints.NORTH);
    }

    /**
     * <h1>Add Moisture Settings to View</h1>
     *
     * Gets data from moisture data array, and
     * updates view with this data
     */
    public void addMoistureSettings()
    {
        moistureTitle = new JLabel("<html><strong style='color: #545454;'>Sprinkler Settings</strong></html>");
        moistureLower = new JLabel("Lower Moisture: " + String.valueOf(moistureData[0]) + "%");
        moistureUpper = new JLabel("Upper Moisture: " + String.valueOf(moistureData[1]) + "%");
        moistureCurrent = new JLabel("Current Moisture: " + String.valueOf(moistureData[2]) + "%");
        moistureOrig = new JLabel("Original Moisture: " + String.valueOf(moistureData[3]) + "%");
        sprinklerStatus = new JLabel("Sprinkler On: " + String.valueOf(moistureData[4]));
        sprinklerOn = new JLabel("Environment On: " + String.valueOf(moistureData[5]));
        moistureExternal = new JLabel("External Weather: " + String.valueOf(moistureData[6]) + "%/s");
        moistureSample = new JLabel("Sample Rate: " + String.valueOf(moistureData[7]) + "%/s");
        moistureRate = new JLabel("Moisture Rate: " + String.valueOf(moistureData[8]) + "%/s");

        addItem(panel, moistureTitle, 2, 1, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, moistureLower, 2, 2, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, moistureUpper, 2, 3, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, moistureCurrent, 2, 4, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, moistureOrig, 2, 5, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, sprinklerStatus, 2, 6, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, sprinklerOn, 2, 7, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, moistureExternal, 2, 8, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, moistureSample, 2, 9, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, moistureRate, 2, 10, 1, 1, GridBagConstraints.NORTH);
    }

    /**
     * <h1>GridBagLayout Add Method</h1>
     *
     * Adds components to the specified panel, using
     * the necessary GridBagLayout specifications
     *
     * @param p
     * @param c
     * @param x
     * @param y
     * @param width
     * @param height
     * @param align
     */
    public void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
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
     * <h1>Add Click Listeners to Button</h1>
     *
     * Adds the general click listener to close button
     *
     * @param listener
     */
    public void addButtonListener(ClickListener listener)
    {
        closeButton.addActionListener(listener);
    }
}
