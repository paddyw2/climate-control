package lab5.Humidity;

/**
 * <h1>Humidity Sensor</h1>
 *
 * This thread monitors the current humidity
 * and controls whether or not the humidifier
 * is turned on.
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class HumidSensor implements Runnable {

    private HumidityController controller;
    private HumidModel model;
    private boolean runThread;
    private double delay;
    private double lowerHumid;
    private double upperHumid;
    private double currentHumid;

    /**
     * <h1>Constructor</h1>
     *
     * Takes controller and model as parameters
     * and sets them as variables
     *
     * @param controller
     * @param model
     */
    public HumidSensor(HumidityController controller, HumidModel model)
    {
        this.controller = controller;
        this.model = model;
        delay = model.getSampleRate() * 1000;
        runThread = true;
    }

    /**
     * <h1>Update Humidity View</h1>
     *
     * Gets latest data and sends it to
     * the controller to update the
     * humidity panel
     */
    public void updateView()
    {
        System.out.println("Updating view");
        double[] data = controller.getHumidData();
        controller.updateView(data);
    }

    /**
     * <h1>Stop Thread</h1>
     *
     * Sets runThread boolean to false,
     * stopping the loop in the run()
     * method
     */
    public void stopThread()
    {
        runThread = false;
    }

    /**
     * <h1>Main Run Method</h1>
     *
     * Called when the thread is started, it
     * analyzes the current temperature and
     * decides whether or not to turn on the
     * humidifier
     */
    public void run()
    {
        // code that checks every specific time period
        // and calls the controller to update the view
        while(runThread)
        {
            lowerHumid = model.getHumidLower();
            upperHumid = model.getHumidUpper();
            currentHumid = model.getCurrentHumid();

            if (currentHumid < lowerHumid) {
                controller.startHumidifier();
            } else if (currentHumid > upperHumid) {
                controller.stopHumidifier();
                System.out.println("Too humid, turning off humidifier");
            } else {
                controller.stopHumidifier();
                System.out.println("Humidity is in desired range");
                System.out.println(currentHumid);
            }

            updateView();

            try {
                Thread.sleep((long) delay);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
                System.out.println(e.getMessage());
            }
        }
    }
}