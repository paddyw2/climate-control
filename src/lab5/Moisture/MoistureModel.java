package lab5.Moisture;

/**
 * <h1>Moisture Model</h1>
 *
 * This model stores the moisture data
 * <br>
 * This includes the user moisture settings,
 * such as moisture rate, sample rate, and external
 * weather
 * <br>
 * It also includes the current moisture
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class MoistureModel {

    private final double DEFAULT_MOISTURE = 50;

    private double origMoisture;
    private double moistureLower;
    private double moistureUpper;
    private double moistureRate;
    private double sampleRate;
    private double externalWeather;
    private double currentMoisture;
    private double sprinklerStatus;
    private double envStatus;

    /**
     * <h1>Constructor</h1>
     *
     * Sets variables to default settings
     */
    public MoistureModel()
    {
        origMoisture = DEFAULT_MOISTURE;
        moistureRate = 1;
        sampleRate = 1;
        externalWeather = 0;
        currentMoisture = origMoisture;
        moistureLower = origMoisture;
        moistureUpper = origMoisture;
        sprinklerStatus = 0;
        envStatus = 0;
    }

    /*

        Set Methods

     */

    public synchronized void setEnvStatus(double envStatus) {
        this.envStatus = envStatus;
    }

    public synchronized void setOrigMoisture(double origMoisture) {
        this.origMoisture = origMoisture;

        this.currentMoisture = origMoisture;
    }

    public synchronized void setMoistureLower(double moistureLower) {
        this.moistureLower = moistureLower;
    }

    public synchronized void setMoistureUpper(double moistureUpper) {
        this.moistureUpper = moistureUpper;
    }

    public synchronized void setMoistureRate(double moistureRate) {
        this.moistureRate = moistureRate;
    }

    public synchronized void setSampleRate(double sampleRate) {
        this.sampleRate = sampleRate;
    }

    public synchronized void setExternalWeather(double externalWeather) {
        this.externalWeather = externalWeather;
    }

    public synchronized void setCurrentMoisture(double currentMoisture) {
        double moisture = currentMoisture;
        if (currentMoisture > 100)
            moisture = 100;
        else if (currentMoisture < 0)
            moisture = 0;

        this.currentMoisture = moisture;
    }

    public synchronized void setSprinklerStatus(double sprinklerStatus) {
        this.sprinklerStatus = sprinklerStatus;
    }

    /*

        Get Methods

     */

    public synchronized double getEnvStatus() {
        return envStatus;
    }

    public synchronized double getSprinklerStatus() {
        return sprinklerStatus;
    }

    public synchronized double getOrigMoisture() {
        return origMoisture;
    }

    public synchronized double getMoistureLower() {
        return moistureLower;
    }

    public synchronized double getMoistureUpper() {
        return moistureUpper;
    }

    public synchronized double getMoistureRate() {
        return moistureRate;
    }

    public synchronized double getSampleRate() {
        return sampleRate;
    }

    public synchronized double getExternalWeather() {
        return externalWeather;
    }

    public synchronized double getCurrentMoisture() {
        return currentMoisture;
    }
}
