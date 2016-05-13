package lab5.Temperature;

import lab5.ClickListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * <h1>Temperature Settings Pop Up</h1>
 *
 * A JDialog that allows the user to update
 * their temperature simulation settings.
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class TempSettings extends JDialog {

    private JTextField fieldOne;
    private JTextField fieldTwo;
    private JTextField fieldTwo2;
    private JTextField fieldThree;

    private JTextField fieldFour;
    private JTextField fieldFive;

    private JLabel fieldOneText;
    private JLabel fieldTwoText;
    private JLabel fieldThreeText;

    private JLabel fieldFourText;
    private JLabel fieldFiveText;

    private JButton closeButton;

    /**
     * <h1>Constructor</h1>
     *
     * Sets up dialog with necessary fields
     * and labels
     *
     * @param parent
     */
    public TempSettings(JFrame parent)
    {
        super(parent, true);
        this.setTitle("Temperature Settings");

        this.setSize(250, 260);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(247, 242, 236));
        this.setAlwaysOnTop(true);

        fieldOne = new JTextField(10);
        fieldOne.setToolTipText("Enter starting temperature (C)");
        fieldTwo = new JTextField(4);
        fieldTwo.setToolTipText("Enter lower acceptable temperature (C)");
        fieldTwo2 = new JTextField(4);
        fieldTwo2.setToolTipText("Enter upper acceptable temperature (C)");
        fieldThree = new JTextField(10);
        fieldThree.setToolTipText("Enter temperature rate, in C per second i.e. 0.0167 = 1C per minute");

        fieldFour = new JTextField(10);
        fieldFour.setToolTipText("Enter sensor refresh rate in seconds");
        fieldFive = new JTextField(10);
        fieldFive.setToolTipText("Enter the external temperature effect, in C per second i.e. 0.0167 = 1C per minute");

        Border padding = BorderFactory.createEmptyBorder(5,0,0,0);

        fieldOneText = new JLabel("Starting Temp:");
        fieldOneText.setBorder(padding);
        fieldTwoText = new JLabel("Desired Range:");
        fieldTwoText.setBorder(padding);
        fieldThreeText = new JLabel("Temp Rate:");
        fieldThreeText.setBorder(padding);
        fieldFourText = new JLabel("Sample Rate:");
        fieldFourText.setBorder(padding);
        fieldFiveText = new JLabel("External Effect:");
        fieldFiveText.setBorder(padding);

        closeButton = new JButton("OK");
        closeButton.setName("tempSettings");

        panel.setLayout(new GridBagLayout());

        addItem(panel, fieldOneText, 0, 0, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, fieldOne, 1, 0, 1, 1, GridBagConstraints.NORTH);

        addItem(panel, fieldTwoText, 0, 1, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, fieldTwo, 1, 1, 1, 1, GridBagConstraints.WEST);
        addItem(panel, fieldTwo2, 1, 1, 1, 1, GridBagConstraints.EAST);

        addItem(panel, fieldThreeText, 0, 2, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, fieldThree, 1, 2, 1, 1, GridBagConstraints.NORTH);

        addItem(panel, fieldFourText, 0, 3, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, fieldFour, 1, 3, 1, 1, GridBagConstraints.NORTH);

        addItem(panel, fieldFiveText, 0, 4, 1, 1, GridBagConstraints.NORTH);
        addItem(panel, fieldFive, 1, 4, 1, 1, GridBagConstraints.NORTH);

        addItem(panel, closeButton, 1, 8, 1, 1, GridBagConstraints.SOUTH);

        this.add(panel);
    }

    /*

        Text Field Get Methods

     */
    public double getOriginalTemp() throws NumberFormatException
    {
        return Double.parseDouble(fieldOne.getText());
    }

    public double getTempLower() throws NumberFormatException
    {
        return Double.parseDouble(fieldTwo.getText());
    }

    public double getTempUpper() throws NumberFormatException
    {
        return Double.parseDouble(fieldTwo2.getText());
    }

    public double getTempRate() throws NumberFormatException
    {
        return Double.parseDouble(fieldThree.getText());
    }

    public double getTempSample() throws NumberFormatException
    {
        return Double.parseDouble(fieldFour.getText());
    }

    public double getTempExternal() throws NumberFormatException
    {
        return Double.parseDouble(fieldFive.getText());
    }


    /**
     * <h1>GridBagLayout Add Method</h1>
     *
     * Adds components to the specified frame according
     * to the GridBagLayout specifications
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
     * <h1>Add Button Listener</h1>
     *
     * Adds listener to the close button
     *
     * @param listener
     */
    public void addStartStopListener(ClickListener listener)
    {
        closeButton.addActionListener(listener);
    }

    /**
     * <h1>Pop Up Message</h1>
     *
     * Triggers a pop up message, used for notifications
     * and error messages
     *
     * @param message
     */
    public void displayError(String message)
    {
        JOptionPane.showMessageDialog(this, message);
    }
}
