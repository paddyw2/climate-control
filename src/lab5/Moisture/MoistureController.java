package lab5.Moisture;

import lab5.MainView;
import java.io.*;

/**
 * <h1>Moisture Controller</h1>
 *
 * This controller deals with the moisture objects,
 * such as the sprinkler, moisture sensor, and the
 * environment
 * <br>
 * It creates the temperature simulation threads
 * <br>
 * When necessary it sends info the main controller to
 * update the main view
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class MoistureController {

    private MainView mainView;
    private MoistureModel model;

    // threads
    private MoistureSensor sensor;
    private Sprinkler sprinkler;
    private MoistureEnvironment moistureEnv;
    private Thread sprinklerThread;
    private Thread sensorThread;
    private Thread envThread;

    /**
     * <h1>Constructor</h1>
     *
     * Takes mainView and model as parameters and sets
     * them as variables
     *
     * @param mainView
     * @param model
     */
    public MoistureController(MainView mainView, MoistureModel model)
    {
        this.mainView = mainView;
        this.model = model;
    }

    /**
     * <h1>Start Sprinkler Simulation</h1>
     *
     * Starts the moisture/sprinkler simulation
     */
    public void startSimulation()
    {
        sensor = new MoistureSensor(this, model);
        sensorThread = new Thread(sensor);
        sensorThread.start();
        startEnv();
        System.out.println("Moisture simulation started");
    }

    /**
     * <h1>Stop Sprinkler Simulation</h1>
     *
     * Stops the moisture/sprinkler simulation
     */
    public void stopSimulation()
    {
        try {
            sensor.stopThread();
            stopSprinkler();
            stopEnv();
            sensor = null;
            System.out.println("Moisture simulation stopped");
        } catch (NullPointerException e) {
            System.out.println("Moisture simulation already stopped");
            System.out.println(e.getMessage());
        }
    }

    /**
     * <h1>Update Moisture View</h1>
     *
     * Takes moisture settings as double array and
     * send them to the mainView to be updated
     *
     * @param data
     */
    public void updateView(double[] data)
    {
        mainView.updateMoisture(data);
    }

    /**
     * <h1>Start Sprinkler</h1>
     *
     * Start sprinkler thread
     */
    public void startSprinkler()
    {
        if (model.getSprinklerStatus() == 0) {

            System.out.println("Starting sprinkler up!");
            sprinkler = new Sprinkler(model);
            sprinklerThread = new Thread(sprinkler);
            sprinklerThread.start();
            model.setSprinklerStatus(1);
        } else {
            System.out.println("Sprinkler already on");
        }
    }

    /**
     * <h1>Stop Sprinkler</h1>
     *
     * Destroy the sprinkler and its thread
     */
    public void stopSprinkler()
    {
        try {
            sprinkler.stopThread();
            sprinklerThread = null;
            sprinkler = null;
            model.setSprinklerStatus(0);
        } catch (NullPointerException e) {
            System.out.println("Sprinkler not running");
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
            moistureEnv = new MoistureEnvironment(model);
            envThread = new Thread(moistureEnv);
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
            moistureEnv.stopThread();
            envThread = null;
            moistureEnv = null;
            model.setEnvStatus(0);
        } catch (NullPointerException e) {
            System.out.println("Environment not running");
        }
    }

    /**
     * <h1>Update Moisture Settings</h1>
     *
     * Get input from settings dialog and update
     * model with new info
     *
     * @param view
     */
    public void saveSettings(MoistureSettings view)
    {
        try {
            // get new details
            double moist1 = view.getOrigMoisture();
            double moist2 = view.getMoistureLower();
            double moist3 = view.getMoistureUpper();
            double moist4 = view.getMoistureRate();
            double moist5 = view.getMoistureSample();
            double moist6 = view.getMoistureExternal();

            if (moist1 < 0 || moist1 > 100 ||
                moist2 < 0 || moist2 > 100 ||
                moist3 < 0 || moist3 > 100)
                throw new NumberFormatException("Moisture be between 0 and 100");

            if (moist3 < moist2)
                throw new NumberFormatException("Upper moisture cannot be lower than lower moisture");

            if (moist4 < 0 || moist5 < 0)
                throw new NumberFormatException("Rates cannot be negative");

            // set new details
            model.setOrigMoisture(moist1);
            model.setMoistureLower(moist2);
            model.setMoistureUpper(moist3);
            model.setMoistureRate(moist4);
            model.setSampleRate(moist5);
            model.setExternalWeather(moist6);

            // close window if data valid
            view.dispose();
            System.out.println(model.getOrigMoisture());

        } catch (NumberFormatException e)
        {
            System.out.println("Invalid user input:");
            System.out.println(e.getMessage());
            view.displayError(e.getMessage());
        }
    }

    /**
     * <h1>Get Latest Moisture Data</h1>
     *
     * Gets latest moisture data from moisture
     * model and returns it in a double array
     *
     * @return data
     */
    public double[] getMoistureData()
    {
        double[] data = new double[9];
        data[0] = model.getMoistureLower();
        data[1] = model.getMoistureUpper();
        data[2] = model.getCurrentMoisture();
        data[3] = model.getOrigMoisture();
        data[4] = model.getSprinklerStatus();
        data[5] = model.getEnvStatus();
        data[6] = model.getExternalWeather();
        data[7] = model.getSampleRate();
        data[8] = model.getMoistureRate();

        return data;
    }

    /**
     * <h1>Save Current Settings to File</h1>
     *
     * Saves current moisture settings to
     * text file
     */
    public void saveMoistureSettingsToFile()
    {
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileOutputStream("SavedSettings/moistureSettings.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        double[] data = getMoistureData();

        for (int i=0; i<data.length; i++) {
            output.println(String.valueOf(data[i]));
        }
        output.close();
    }
}