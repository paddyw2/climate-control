package lab5.Humidity;

/**
 * <h1>Humidity Model</h1>
 *
 * This model stores the humidity data
 * <br>
 * This includes the user humidity settings,
 * such as humidity rate, sample rate, and external
 * weather
 * <br>
 * It also includes the current humidity
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class HumidModel {

    private final double DEFAULT_HUMID = 50;

    private double origHumid;
    private double humidLower;
    private double humidUpper;
    private double humidRate;
    private double sampleRate;
    private double externalWeather;
    private double currentHumid;
    private double humidStatus;
    private double envStatus;

    /**
     * <h1>Constructor</h1>
     *
     * Sets variables to default values
     */
    public HumidModel()
    {
        origHumid = DEFAULT_HUMID;
        humidRate = 1;
        sampleRate = 1;
        externalWeather = 0;
        currentHumid = origHumid;
        humidLower = origHumid;
        humidUpper = origHumid;
        humidStatus = 0;
        envStatus = 0;
    }

    /*

        Set Methods

     */

    public synchronized void setOrigHumid(double origHumid) {
        this.origHumid = origHumid;

        this.currentHumid = origHumid;
    }

    public synchronized void setHumidLower(double humidLower) {
        this.humidLower = humidLower;
    }

    public synchronized void setHumidUpper(double humidUpper) {
        this.humidUpper = humidUpper;
    }

    public synchronized void setHumidRate(double humidRate) {
        this.humidRate = humidRate;
    }

    public synchronized void setSampleRate(double sampleRate) {
        this.sampleRate = sampleRate;
    }

    public synchronized void setExternalWeather(double externalWeather) {
        this.externalWeather = externalWeather;
    }

    public synchronized void setCurrentHumid(double currentHumid) {
        double humid = currentHumid;
        if (currentHumid > 100)
            humid = 100;
        else if (currentHumid < 0)
            humid = 0;

        this.currentHumid = humid;
    }

    public synchronized void setHumidStatus(double humidStatus) {
        this.humidStatus = humidStatus;
    }

    public synchronized void setEnvStatus(double envStatus) {
        this.envStatus = envStatus;
    }

    /*

        Get Methods

     */

    public synchronized double getOrigHumid() {
        return origHumid;
    }

    public synchronized double getHumidLower() {
        return humidLower;
    }

    public synchronized double getHumidUpper() {
        return humidUpper;
    }

    public synchronized double getHumidRate() {
        return humidRate;
    }

    public synchronized double getSampleRate() {
        return sampleRate;
    }

    public synchronized double getExternalWeather() {
        return externalWeather;
    }

    public synchronized double getCurrentHumid() {
        return currentHumid;
    }

    public synchronized double getHumidStatus() {
        return humidStatus;
    }

    public synchronized double getEnvStatus() {
        return envStatus;
    }
}
