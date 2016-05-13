package lab5.Humidity;

/**
 * <h1>Humidity Environment Thread</h1>
 *
 * The humidity environment thread that
 * increments the humidity level by the
 * specified value every second
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class HumidityEnvironment implements Runnable {

    private HumidModel model;
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
    public HumidityEnvironment(HumidModel model)
    {
        this.model = model;
        runThread = true;
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
     * Called when thread is started, it
     * increments the humidity by the
     * specified environment rate every
     * second
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
            currentTemp = model.getCurrentHumid();

            // weather effect
            currentTemp += weather;

            // update model
            model.setCurrentHumid(currentTemp);

            System.out.println("Environment Temp: " + currentTemp);

            // sleep at end of every loop
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Humidity environment turned off successfully");
    }
}


