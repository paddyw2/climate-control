package lab5.Temperature;

/**
 * <h1>Temperature Sensor</h1>
 *
 * This thread monitors the current temperature
 * and controls whether or not the furnace or
 * air con are turned on.
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class TempSensor implements Runnable {

    private TempController controller;
    private TempModel model;
    private boolean runThread;
    private double delay;
    private double lowerTemp;
    private double upperTemp;
    private double currentTemp;

    /**
     * <h1>Constructor</h1>
     *
     * Takes controller and model as parameters
     * and sets them as variables
     *
     * @param controller
     * @param model
     */
    public TempSensor(TempController controller, TempModel model)
    {
        this.controller = controller;
        this.model = model;
        delay = model.getSampleRate() * 1000;
        runThread = true;
    }

    /**
     * <h1>Update Temperature View</h1>
     *
     * Gets latest temperature data and calls
     * controller method to update mainView
     */
    public void updateView()
    {
        System.out.println("Updating view");
        double[] data = controller.getTempData();

        controller.updateView(data);
    }

    /**
     * <h1>Stop Thread</h1>
     *
     * Sets runThread boolean to false, stopping
     * the while loop in the run() method
     */
    public void stopThread()
    {
        runThread = false;
    }

    /**
     * <h1>Main Run Method</h1>
     *
     * Called when the thread is started. Monitors the
     * current temperature and turns on/off the air con,
     * furnace, and environment threads accordingly.
     */
    public void run()
    {
        // code that checks every specific time period
        // and calls the controller to update the view
        while(runThread)
        {
            lowerTemp = model.getTempLower();
            upperTemp = model.getTempUpper();
            currentTemp = model.getCurrentTemp();

            if (currentTemp < lowerTemp) {
                controller.stopAirCon();
                controller.startFurnace();
            } else if (currentTemp > upperTemp) {
                controller.stopFurnace();
                controller.startAirCon();
            } else {
                controller.stopFurnace();
                controller.stopAirCon();
                System.out.println("Temperature is in desired range");
                System.out.println(currentTemp);
            }

            updateView();

            try {
                Thread.sleep((long) delay);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Temperature sensor stopped");
    }
}