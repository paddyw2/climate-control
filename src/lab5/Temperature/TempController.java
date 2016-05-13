package lab5.Temperature;

import lab5.MainView;
import java.io.*;

/**
 * <h1>Temperature Controller</h1>
 *
 * This controller deals with the temperature objects,
 * such as the furnace, air con, temp model and the
 * temp sensor
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
public class TempController {

    private MainView mainView;
    private TempModel model;

    // threads
    private TempSensor sensor;
    private Furnace furnace;
    private AirCon airCon;
    private TemperatureEnvironment tempEnv;
    private Thread furnaceThread;
    private Thread airConThread;
    private Thread sensorThread;
    private Thread envThread;

    /**
     * <h1>Constructor</h1>
     *
     * Takes the mainView and model as parameters, sets them as variables
     *
     * @param mainView
     * @param model
     */
    public TempController(MainView mainView, TempModel model)
    {
        this.mainView = mainView;
        this.model = model;
    }

    /**
     * <h1>Temperature Simulation Start</h1>
     *
     * Starts the temperature simulation
     */
    public void startSimulation()
    {
        sensor = new TempSensor(this, model);
        sensorThread = new Thread(sensor);
        sensorThread.start();
        startEnv();
        System.out.println("Temperature simulation started");
    }

    /**
     * <h1>Temperature Simulation Stop</h1>
     *
     * Stops the temperature simulation, provided it has
     * been started
     */
    public void stopSimulation()
    {
        try {
            sensor.stopThread();
            stopFurnace();
            stopAirCon();
            stopEnv();
            sensor = null;
            System.out.println("Temperature simulation stopped");
        } catch (NullPointerException e) {
            System.out.println("Temperature simulation already stopped");
            System.out.println(e.getMessage());
        }
    }

    /**
     * <h1>Update Main View</h1>
     *
     * Takes temperature data stored in array and
     * sends it to the mainView to be updated
     *
     * @param data
     */
    public void updateView(double[] data)
    {
        mainView.updateTemp(data);
    }

    /**
     * <h1>Start Environment</h1>
     *
     * Start the environment thread
     */
    public void startEnv()
    {
        if(model.getEnvStatus() == 0) {
            System.out.println("Starting temp environment up!");
            tempEnv = new TemperatureEnvironment(model);
            envThread = new Thread(tempEnv);
            envThread.start();
            model.setEnvStatus(1);
        } else {
            System.out.println("Temp environment already on");
        }
    }

    /**
     * <h1>Stop Environment</h1>
     *
     * Stop the environment thread
     */
    public void stopEnv()
    {
        try {
            tempEnv.stopThread();
            envThread = null;
            tempEnv = null;
            model.setEnvStatus(0);
        } catch (NullPointerException e)
        {
            System.out.println("Temp environment not running");
        }
    }

    /**
     * <h1>Start Furnace</h1>
     *
     * Start the furnace thread
     */
    public void startFurnace()
    {
        if (model.getFurnaceStatus() == 0) {

            System.out.println("Starting furnace up!");
            furnace = new Furnace(model);
            furnaceThread = new Thread(furnace);
            furnaceThread.start();
            model.setFurnaceStatus(1);
        } else {
            System.out.println("Furnace already on");
        }

    }

    /**
     * <h1>Stop Furnace</h1>
     *
     * Stop furnace thread
     */
    public void stopFurnace()
    {
        try {
            furnace.stopThread();
            furnaceThread = null;
            furnace = null;
            model.setFurnaceStatus(0);
        } catch (NullPointerException e)
        {
            System.out.println("Furnace not running");
        }
    }

    /**
     * <h1>Start Air Con Thread</h1>
     *
     * Start air cond thread
     */
    public void startAirCon()
    {
        if (model.getAirConStatus() == 0) {

            System.out.println("Starting air con up!");
            airCon = new AirCon(model);
            airConThread = new Thread(airCon);
            airConThread.start();
            model.setAirConStatus(1);
        } else {
            System.out.println("Air con already on");
        }
    }

    /**
     * <h1>Stop Air Con Thread</h1>
     *
     * Stop air con thread
     */
    public void stopAirCon()
    {
        try {
            airCon.stopThread();
            airConThread = null;
            airCon = null;
            model.setAirConStatus(0);
        } catch (NullPointerException e) {
            System.out.println("Air Con not running");
        }
    }

    /**
     * <h1>Update Temperature Settings</h1>
     *
     * Take input from settings pop up and update
     * model with new info
     *
     * @param view
     */
    public void saveSettings(TempSettings view)
    {
        try {
            // get new details
            double temp1 = view.getOriginalTemp();
            double temp2 = view.getTempLower();
            double temp3 = view.getTempUpper();
            double temp4 = view.getTempRate();
            double temp5 = view.getTempSample();
            double temp6 = view.getTempExternal();

            if (temp3 < temp2)
                throw new NumberFormatException("Upper temperature cannot be lower than lower temperature");

            if (temp4 < 0 || temp5 < 0)
                throw new NumberFormatException("Rates cannot be negative");

            // set new details
            model.setOrigTemp(temp1);
            model.setTempLower(temp2);
            model.setTempUpper(temp3);
            model.setTempRate(temp4);
            model.setSampleRate(temp5);
            model.setExternalWeather(temp6);

            // close window if data valid
            view.dispose();
            System.out.println(model.getOrigTemp());

        } catch (NumberFormatException e) {
            System.out.println("Invalid user input:");
            System.out.println(e.getMessage());
            view.displayError(e.getMessage());
        }
    }

    /**
     * <h1>Get Temperature Data</h1>
     *
     * Get temperature data from model and return
     * as double array
     *
     * @return data
     */
    public double[] getTempData()
    {
        double[] data = new double[9];
        data[0] = model.getTempLower();
        data[1] = model.getTempUpper();
        data[2] = model.getCurrentTemp();
        data[3] = model.getOrigTemp();
        data[4] = model.getFurnaceStatus();
        data[5] = model.getAirConStatus();
        data[6] = model.getExternalWeather();
        data[7] = model.getSampleRate();
        data[8] = model.getTempRate();

        return data;
    }

    /**
     * <h1>Save Settings to File</h1>
     *
     * Get current data from model and save
     * to text file
     */
    public void saveTempSettingsToFile()
    {
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileOutputStream("SavedSettings/tempSettings.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        double[] data = getTempData();

        for (int i=0; i<data.length; i++) {
            output.println(String.valueOf(data[i]));
        }
        output.close();
    }
}