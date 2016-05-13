package lab5.Moisture;

/**
 * <h1>Moisture Environment Thread</h1>
 *
 * The moisture environment thread that
 * increments the moisture level by the
 * specified value every second
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class MoistureEnvironment implements Runnable {

    private MoistureModel model;
    private boolean runThread;
    private double currentTemp;
    private double weather;
    private long delay = 1000;

    /**
     * <h1>Constructor</h1>
     *
     * Takes model as parameter and sets
     * it as variable
     *
     * @param model
     */
    public MoistureEnvironment(MoistureModel model)
    {
        this.model = model;
        runThread = true;
    }

    /**
     * <h1>Stop Thread</h1>
     *
     * Sets the runThread boolean to
     * false, stopping the while loop
     * in the run() method
     */
    public void stopThread()
    {
        runThread = false;
    }

    /**
     * <h1>Main Run Method</h1>
     *
     * Called when the thread is started. It
     * increments the moisture by the specified
     * value every second.
     */
    public void run()
    {
        // sleep before starting loop
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
            System.out.println(e.getMessage());
        }

        while(runThread)
        {
            weather = model.getExternalWeather();
            currentTemp = model.getCurrentMoisture();

            // weather effect
            currentTemp += weather;

            // update model
            model.setCurrentMoisture(currentTemp);

            System.out.println("Environment Temp: " + currentTemp);

            // sleep at end of every loop
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Moisture environment turned off successfully");
    }
}


