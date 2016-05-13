package lab5.Temperature;

/**
 * <h1>Furnace Thread</h1>
 *
 * This thread increases the current
 * temperature by the desired temp
 * rate every second
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class Furnace implements Runnable {

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
    public Furnace(TempModel model)
    {
        this.model = model;
        runThread = true;
    }

    /**
     * <h1>Stop Thread</h1>
     *
     * Changes the runThread variable to false, which
     * stops the while loop in the run() method
     */
    public void stopThread()
    {
        runThread = false;
    }

    /**
     * <h1>Main Run Method</h1>
     *
     * Called when the furnace thread is started. This
     * simulates a furnace, incrementing the current
     * temperature by the temp rate, while also taking
     * into account the external weather effect.
     */
    public void run()
    {
        // sleep on first loop to allow initial temperature to show
        try {
            System.out.println("Initial sleep");
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
            System.out.println(e.getMessage());
        }

        while(runThread)
        {
            // get latest data
            weather = model.getExternalWeather();
            increment = model.getTempRate();
            currentTemp = model.getCurrentTemp();
            currentTemp += increment;

            // update model
            model.setCurrentTemp(currentTemp);

            System.out.println("Current Temp: " + currentTemp);

            // sleep at end of every loop
            try {
                System.out.println("Sleeping");
                Thread.sleep(delay);
                System.out.println("Sleep finished, one second later");
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Furnace turned off successfully");
    }
}