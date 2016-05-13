package lab5.Temperature;

/**
 * <h1>Air Con Thread</h1>
 *
 * This is the air con thread that decreases
 * the temperature by the desired temp rate
 * every second
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 *
 */
public class AirCon implements Runnable {

    private TempModel model;
    private boolean runThread;
    private double increment;
    private double currentTemp;
    private double weather;
    private long delay = 1000;

    /**
     * <h1>Constructor</h1>
     *
     * Takes model as parameter and sets it as variable
     *
     * @param model
     */
    public AirCon(TempModel model)
    {
        this.model = model;
        runThread = true;
    }

    /**
     * <h1>Stop Thread</h1>
     *
     * Sets stopThread variable to false, which
     * stops the while loop in the run() method
     */
    public void stopThread() {
        runThread = false;
    }

    /**
     * <h1>Main Run Method</h1>
     *
     * Called when thread is started. This acts as
     * air conditioning, decrementing the current
     * temperature by the temp rate and taking into
     * account the environment temperature too.
     */
    public void run()
    {
        while(runThread)
        {
            // get latest data
            weather = model.getExternalWeather();
            increment = model.getTempRate();
            currentTemp = model.getCurrentTemp();
            currentTemp -= increment;

            // update model
            model.setCurrentTemp(currentTemp);

            System.out.println("Current Temp: " + currentTemp);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Air Con turned off successfully");
    }
}