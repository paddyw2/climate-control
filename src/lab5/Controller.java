package lab5;

import lab5.Humidity.HumidSettings;
import lab5.Humidity.HumidityController;
import lab5.Moisture.MoistureController;
import lab5.Moisture.MoistureSettings;
import lab5.Temperature.TempController;
import lab5.Temperature.TempSettings;
import java.io.*;
import java.util.Scanner;

/**
 * <h1>Main Controller</h1>
 *
 * This is the main controller, and is concerned with
 * taking the users start/stop simulation input and passing
 * it on to the sub controllers, such as temp, humid etc.
 * <br>
 * It then takes input from the sub controllers in order
 * to update the main view.
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class Controller {

    private TempController tempController;
    private HumidityController humidityController;
    private MoistureController moistureController;

    private MainView mainView;
    private TempSettings tempSettings;
    private HumidSettings humidSettings;
    private MoistureSettings moistureSettings;

    private boolean simulation;

    private SavedSettingsView savedSettingsView;

    /**
     * <h1>Constructor</h1>
     *
     * Takes sub controllers and main view as parameters and assigns them to variables.
     * <br>
     * Also sets the simulation boolean to the default false value, and tries to create
     * the saved settings directory in case it does not already exist.
     *
     * @param tempController
     * @param humidityController
     * @param moistureController
     * @param mainView
     */
    public Controller(TempController tempController, HumidityController humidityController, MoistureController moistureController, MainView mainView)
    {
        this.tempController = tempController;
        this.humidityController = humidityController;
        this.moistureController = moistureController;

        this.mainView = mainView;
        mainView.addListener(new ClickListener(this));

        simulation = false;

        // create saved settings directory
        // in case it has been deleted
        File dir = new File("SavedSettings");
        boolean created = dir.mkdir();
        if (created)
        	System.out.println("Directory created");
        else
            System.out.println("Did not create directory, may already exist");
    }

    /**
     * <h1>Start Simulation</h1>
     *
     * When the start button is clicked, the simulation is started
     * by calling the sub controller start methods.
     * <br>
     * If the simulation is already running, a pop up is displayed.
     */
    public void startSimulation()
    {
        if (!simulation) {

            tempController.startSimulation();
            humidityController.startSimulation();
            moistureController.startSimulation();

            simulation = true;
        } else {
            mainView.displayError("Simulation already running");
        }
    }

    /**
     * <h1>Stop Simulation</h1>
     *
     * When the stop button is clicked the simulation is stopped
     * by calling the sub controller stop methods.
     * <br>
     * If the simulaion is already stopped, a pop up is displayed.
     */
    public void stopSimulation()
    {
        if (simulation) {
            tempController.stopSimulation();
            humidityController.stopSimulation();
            moistureController.stopSimulation();

            mainView.resetSimulation();
            simulation = false;
        } else {
            mainView.displayError("Simulation already stopped");
        }
    }

    /**
     * <h1>Display Temperature Settings Dialog</h1>
     *
     * When the user clicks the temperature settings, a input field
     * dialog is displayed.
     */
    public void setTempSettings()
    {
        tempSettings = new TempSettings(mainView);
        tempSettings.addStartStopListener(new ClickListener(this));
        tempSettings.setVisible(true);
    }

    /**
     * <h1>Save Temperature Settings, Close Dialog</h1>
     *
     * When the save button is clicked, try save settings and
     * close dialog
     */
    public void saveTempSettings()
    {
        // get data from settings view, send to
        // temp controller to update
        tempController.saveSettings(tempSettings);

        // if simulation running while new settings
        // created, the restart the simulation to
        // show updated settings
        if (simulation) {
            tempController.stopSimulation();
            tempController.startSimulation();
        }
    }

    public void setHumidSettings()
    {
        humidSettings = new HumidSettings(mainView);
        humidSettings.addStartStopListener(new ClickListener(this));
        humidSettings.setVisible(true);
    }

    public void saveHumidSettings()
    {
        humidityController.saveSettings(humidSettings);

        if (simulation) {
            humidityController.stopSimulation();
            humidityController.startSimulation();
        }
    }

    public void setMoistureSettings()
    {
        moistureSettings = new MoistureSettings(mainView);
        moistureSettings.addStartStopListener(new ClickListener(this));
        moistureSettings.setVisible(true);
    }

    public void saveMoistureSettings()
    {
        moistureController.saveSettings(moistureSettings);

        if (simulation) {
            moistureController.stopSimulation();
            moistureController.startSimulation();
        }
    }

    public void saveSettingsToFile()
    {
        tempController.saveTempSettingsToFile();
        humidityController.saveHumidSettingsToFile();
        moistureController.saveMoistureSettingsToFile();

        mainView.displayError("Settings saved");
    }

    public double[] readConditionSavedFile(String fileName)
    {
        Scanner inputStream = null;
        String inputString = null;
        double[] data = new double[9];
        int i = 0;

        // try open file for reading
        try {
            inputStream = new Scanner(new FileInputStream("SavedSettings/" + fileName));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            data[0] = -1;
            return data;
        }

        // read each line into data, unless any line is not
        // a double
        while (inputStream.hasNextLine()) {
            inputString = inputStream.nextLine();
            try {
                data[i] = Double.parseDouble(inputString);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                data[0] = -1;
                return data;
            }
            i++;
        }
        inputStream.close();

        return data;
    }

    public void viewSavedSettings()
    {
        double[] tempData = readConditionSavedFile("tempSettings.txt");
        double[] humidData = readConditionSavedFile("humidSettings.txt");
        double[] moistureData = readConditionSavedFile("moistureSettings.txt");

        if (tempData[0] == -1 || humidData[0] == -1 || moistureData[0] == -1) {
            mainView.displayError("Error: Make sure you have saved your settings first");
        } else {
            savedSettingsView = new SavedSettingsView(mainView, tempData, humidData, moistureData);
            savedSettingsView.addButtonListener(new ClickListener(this));
            savedSettingsView.setVisible(true);
        }

    }

    public void closeSavedSettings()
    {
        try {
            savedSettingsView.dispose();
        } catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }


}
