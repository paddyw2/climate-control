package lab5.Moisture;

/**
 * <h1>Moisture Sensor</h1>
 *
 * This thread monitors the current moisture
 * and controls whether or not the sprinkler
 * is turned on.
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class MoistureSensor implements Runnable {

    private MoistureController controller;
    private MoistureModel model;
    private boolean runThread;
    private double delay;
    private double lowerMoisture;
    private double upperMoisture;
    private double currentMoisture;

    /**
     * <h1>Constructor</h1>
     *
     * Takes controller and model as parameters
     * and sets them to variables
     *
     * @param controller
     * @param model
     */
    public MoistureSensor(MoistureController controller, MoistureModel model)
    {
        this.controller = controller;
        this.model = model;
        delay = model.getSampleRate() * 1000;
        runThread = true;
    }

    /**
     * <h1>Update Moisture View</h1>
     *
     * Sends latest data to the controller
     * to update the moisture/sprinkler
     * panel with the latest info
     */
    public void updateView()
    {
        System.out.println("Updating view");
        double[] data = controller.getMoistureData();
        controller.updateView(data);
    }

    /**
     * <h1>Stop Thread</h1>
     *
     * Sets runThread boolean to false,
     * stopping the while loop in the
     * run() method
     */
    public void stopThread()
    {
        runThread = false;
    }

    /**
     * <h1>Main Run Method</h1>
     *
     * Called when thread is started, and
     * analyzes current moisture and decides
     * whether or not to turn on the sprinkler
     */
    public void run()
    {
        // code that checks every specific time period
        // and calls the controller to update the view
        while(runThread)
        {
            lowerMoisture = model.getMoistureLower();
            upperMoisture = model.getMoistureUpper();
            currentMoisture = model.getCurrentMoisture();

            if (currentMoisture < lowerMoisture) {
                controller.startSprinkler();
            } else if (currentMoisture > upperMoisture) {
                controller.stopSprinkler();
                System.out.println("Too much moisture, turning off sprinkler");
            } else {
                controller.stopSprinkler();
                System.out.println("Moisture is in desired range");
                System.out.println(currentMoisture);
            }

            updateView();

            try {
                Thread.sleep((long) delay);
            } catch (InterruptedException e)
            {
                System.out.println("Sleep interrupted");
                System.out.println(e.getMessage());
            }
        }
    }
}

