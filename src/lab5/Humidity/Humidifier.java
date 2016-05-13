package lab5.Humidity;

/**
 * <h1>Humidifier Thread</h1>
 *
 * This thread increases the current
 * humidity by the desired rate
 * every second
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class Humidifier implements Runnable {

    private HumidModel model;
    private boolean runThread;
    private double increment;
    private double currentHumid;
    private long delay = 1000;

    /**
     * <h1>Constructor</h1>
     *
     * Takes model as parameter, and
     * sets it as variable
     *
     * @param model
     */
    public Humidifier(HumidModel model)
    {
        this.model = model;
        runThread = true;
    }

    /**
     * <h1>Stop Thread</h1>
     *
     * Sets runThread boolean to
     * false, stopping the loop
     * in the run() method
     */
    public void stopThread()
    {
        runThread = false;
    }

    /**
     * <h1>Main Run Method</h1>
     *
     * Called when thread is started, it
     * increments the humidity by the
     * specified rate every second
     */
    public void run()
    {
        // initial sleep on first loop
        try {
            System.out.println("Initial sleep");
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
            System.out.println(e.getMessage());
        }

        while(runThread)
        {
            increment = model.getHumidRate();
            currentHumid = model.getCurrentHumid();

            currentHumid += increment;

            // update model
            model.setCurrentHumid(currentHumid);

            System.out.println("Current Humidity: " + currentHumid);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Humidifier turned off successfully");
    }
}