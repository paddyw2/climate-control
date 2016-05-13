package lab5.Temperature;

/**
 * <h1>Environment Thread</h1>
 *
 * This thread controls the environment temperature,
 * incrementing by the specificied value every second
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class TemperatureEnvironment implements Runnable {

    private TempModel model;
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
    public TemperatureEnvironment(TempModel model)
    {
        this.model = model;
        runThread = true;
    }

    /**
     * <h1>Stop Thread</h1>
     *
     * Sets the runThread variable to false,
     * stopping the while loop in the run()
     * method
     */
    public void stopThread()
    {
        runThread = false;
    }

    /**
     * <h1>Main Run Method</h1>
     *
     * The method that is called when
     * the thread is started. Increments
     * the specified temperature value
     * every second
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
            currentTemp = model.getCurrentTemp();

            // weather effect
            currentTemp += weather;

            // update model
            model.setCurrentTemp(currentTemp);

            System.out.println("Environment Temp: " + currentTemp);

            // sleep at end of every loop
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Temperature environment turned off successfully");
    }
}

