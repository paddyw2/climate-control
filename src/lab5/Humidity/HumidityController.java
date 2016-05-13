package lab5.Humidity;

import lab5.MainView;
import java.io.*;

/**
 * <h1>Humidity Controller</h1>
 *
 * This controller deals with the humidity objects
 * <br>
 * It creates the humidity simulation threads
 * <br>
 * When necessary it sends info the main controller to
 * update the main view
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class HumidityController {

    private MainView mainView;
    private HumidModel model;

    // threads
    private HumidSensor sensor;
    private Humidifier humidifier;
    private HumidityEnvironment humidEnv;
    private Thread humidThread;
    private Thread sensorThread;
    private Thread envThread;

    /**
     * <h1>Constructor</h1>
     *
     * Takes mainView and model as parameters
     * and sets them to variables
     *
     * @param mainView
     * @param model
     */
    public HumidityController(MainView mainView, HumidModel model)
    {
        this.mainView = mainView;
        this.model = model;
    }

    /**
     * <h1>Start Humidity Simulation</h1>
     *
     * Starts humidity simulation
     */
    public void startSimulation()
    {
        sensor = new HumidSensor(this, model);
        sensorThread = new Thread(sensor);
        sensorThread.start();
        startEnv();
        System.out.println("Humidity simulation started");
    }

    /**
     * <h1>Stop Humidity Simulation</h1>
     *
     * If humidity simulation is running,
     * the simulation is stoppped
     */
    public void stopSimulation()
    {
        try {
            sensor.stopThread();
            stopHumidifier();
            stopEnv();
            sensor = null;
            System.out.println("Humidity simulation stopped");
        } catch (NullPointerException e) {
            System.out.println("Humidity simulation already stopped");
            System.out.println(e.getMessage());
        }
    }

    /**
     * <h1>Update Humidity View</h1>
     *
     * Gets latest humidity data and
     * sends to the controller to update
     * in the condition panel
     *
     * @param data
     */
    public void updateView(double[] data)
    {
        mainView.updateHumid(data);
    }

    /**
     * <h1>Start Humidifier</h1>
     *
     * Starts humidifier, if it is not
     * already on
     */
    public void startHumidifier()
    {
        if (model.getHumidStatus() == 0) {

            System.out.println("Starting humidifier up!");
            humidifier = new Humidifier(model);
            humidThread = new Thread(humidifier);
            humidThread.start();
            model.setHumidStatus(1);

        } else {
            System.out.println("Humidifier already on");
        }
    }

    /**
     * <h1>Stop Humidifier</h1>
     *
     * Stops humidifier, if it is not
     * already turned off
     */
    public void stopHumidifier()
    {
        try {
            humidifier.stopThread();
            humidThread = null;
            humidifier = null;
            model.setHumidStatus(0);
        } catch (NullPointerException e)
        {
            System.out.println("Humidifier not running");
        }
    }

    /**
     * <h1>Start Environment</h1>
     *
     * Start environment thread
     */
    public void startEnv()
    {
        if (model.getEnvStatus() == 0) {
            System.out.println("Starting environment up!");
            humidEnv = new HumidityEnvironment(model);
            envThread = new Thread(humidEnv);
            envThread.start();
            model.setEnvStatus(1);
        } else {
            System.out.println("Environment already on");
        }
    }

    /**
     * <h1>Stop Environment</h1>
     *
     * Destroy the environment and its thread
     */
    public void stopEnv()
    {
        try {
            humidEnv.stopThread();
            envThread = null;
            envThread = null;
            model.setEnvStatus(0);
        } catch (NullPointerException e) {
            System.out.println("Environment not running");
        }
    }

    /**
     * <h1>Update Humidity Settings</h1>
     *
     * Get humidity dialog input settings, and,
     * if valid, update the model with them
     *
     * @param view
     */
    public void saveSettings(HumidSettings view)
    {
        try {
            // get new details
            double humid1 = view.getOrigHumid();
            double humid2 = view.getHumidLower();
            double humid3 = view.getHumidUpper();
            double humid4 = view.getHumidRate();
            double humid5 = view.getHumidSample();
            double humid6 = view.getHumidExternal();

            if (humid1 < 0 || humid1 > 100 ||
                humid2 < 0 || humid2 > 100 ||
                humid3 < 0 || humid3 > 100)
                throw new NumberFormatException("Humidity be between 0 and 100");

            if (humid3 < humid2)
                throw new NumberFormatException("Upper humidity cannot be lower than lower humidity");

            if (humid4 < 0 || humid5 < 0)
                throw new NumberFormatException("Rates cannot be negative");

            // set new details
            model.setOrigHumid(humid1);
            model.setHumidLower(humid2);
            model.setHumidUpper(humid3);
            model.setHumidRate(humid4);
            model.setSampleRate(humid5);
            model.setExternalWeather(humid6);

            // close window if data valid
            view.dispose();
            System.out.println(model.getOrigHumid());

        } catch (NumberFormatException e) {
            System.out.println("Invalid user input:");
            System.out.println(e.getMessage());
            view.displayError(e.getMessage());
        }
    }

    /**
     * <h1>Get Humidity Data</h1>
     *
     * Get latest humidity data from model
     * and return as double array
     *
     * @return data
     */
    public double[] getHumidData()
    {
        double[] data = new double[9];
        data[0] = model.getHumidLower();
        data[1] = model.getHumidUpper();
        data[2] = model.getCurrentHumid();
        data[3] = model.getOrigHumid();
        data[4] = model.getHumidStatus();
        data[5] = model.getEnvStatus();
        data[6] = model.getExternalWeather();
        data[7] = model.getSampleRate();
        data[8] = model.getHumidRate();

        return data;
    }

    /**
     * <h1>Save Humidity Settings to File</h1>
     *
     * Save current humidity settings to a text file
     */
    public void saveHumidSettingsToFile()
    {
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileOutputStream("SavedSettings/humidSettings.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        double[] data = getHumidData();

        for (int i=0; i<data.length; i++) {
            output.println(String.valueOf(data[i]));
        }
        output.close();
    }
}