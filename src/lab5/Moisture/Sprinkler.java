package lab5.Moisture;

/**
 * <h1>Sprinkler Thread</h1>
 *
 * This thread increases the current
 * moisture by the desired rate
 * every second
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class Sprinkler implements Runnable {

    private MoistureModel model;
    private boolean runThread;
    private double increment;
    private double currentMoisture;
    private long delay = 1000;

    /**
     * <h1>Constructor</h1>
     *
     * Takes model as parameter and sets it
     * to variable. Also sets default values.
     *
     * @param model
     */
    public Sprinkler(MoistureModel model)
    {
        this.model = model;
        runThread = true;
    }

    /**
     * <h1>Stop Thread</h1>
     *
     * Sets the runThread boolean to false,
     * which stops the while loop in the
     * run() method
     */
    public void stopThread()
    {
        runThread = false;
    }

    /**
     * <h1>Main Run Method</h1>
     *
     * Called when thread started, the
     * sprinkler increases soil moisture
     * by specified rate.
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
            increment = model.getMoistureRate();
            currentMoisture = model.getCurrentMoisture();

            // increase humidity
            currentMoisture += increment;

            // update model
            model.setCurrentMoisture(currentMoisture);

            System.out.println("Current Moisture: " + currentMoisture);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Sprinkler turned off successfully");
    }
}