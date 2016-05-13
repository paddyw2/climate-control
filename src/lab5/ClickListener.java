package lab5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1>Click Listener</h1>
 *
 * This action listener processes all click
 * events
 * <br>
 * It identifies the button clicked by buttonName
 * and then calls the appropriate method
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class ClickListener implements ActionListener{

    private Controller controller;

    /**
     * <h1>Constructor</h1>
     *
     * Takes controller as parameter and sets it as variable
     *
     * @param controller
     */
    public ClickListener(Controller controller)
    {
        this.controller = controller;
    }

    /**
     * <h1>actionPerformed</h1>
     *
     * Overrides action listener method and processes any click
     * by button name
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton source = (JButton) e.getSource();

        String buttonName = source.getName();

        switch (buttonName) {
            case "startButton":
                controller.startSimulation();
                break;
            case "stopButton":
                controller.stopSimulation();
                break;
            case "furnaceButton":
                controller.setTempSettings();
                break;
            case "airConButton":
                controller.setTempSettings();
                break;
            case "humidButton":
                controller.setHumidSettings();
                break;
            case "moistureButton":
                controller.setMoistureSettings();
                break;
            case "tempSettings":
                controller.saveTempSettings();
                break;
            case "humidSettings":
                controller.saveHumidSettings();
                break;
            case "moistureSettings":
                controller.saveMoistureSettings();
                break;
            case "saveSettingsButton":
                controller.saveSettingsToFile();
                break;
            case "viewSettingsButton":
                controller.viewSavedSettings();
                break;
            case "closeSavedSettings":
                controller.closeSavedSettings();
                break;
            default:
                System.out.println("No action matching given button");
                break;
        }

    }
}
