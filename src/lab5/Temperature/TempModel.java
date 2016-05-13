package lab5.Temperature;

/**
 * <h1>Temperature Model</h1>
 *
 * This model stores the temperature data
 * <br>
 * This includes the user temperature settings,
 * such as temp rate, sample rate, and external
 * weather
 * <br>
 * It also includes the current temp
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class TempModel {

    private final double DEFAULT_TEMP = 25;

    private double origTemp;
    private double tempLower;
    private double tempUpper;
    private double tempRate;
    private double sampleRate;
    private double externalWeather;
    private double currentTemp;
    private double furnaceStatus;
    private double airConStatus;
    private double envStatus;

    /**
     * <h1>Constructor</h1>
     *
     * Sets variables to default settings
     */
    public TempModel()
    {
        origTemp = DEFAULT_TEMP;
        tempRate = 1;
        sampleRate = 1;
        externalWeather = 0;
        currentTemp = origTemp;
        tempLower = origTemp;
        tempUpper = origTemp;
    }

    /*

        Set Methods

     */

    public synchronized void setEnvStatus(double envStatus) {
        this.envStatus = envStatus;
    }

    public void setFurnaceStatus(double furnaceStatus) {
        this.furnaceStatus = furnaceStatus;
    }

    public void setAirConStatus(double airConStatus) {
        this.airConStatus = airConStatus;
    }

    public synchronized void setOrigTemp(double origTemp) {
        this.origTemp = origTemp;
        // if the user is changing the starting temp
        // the process is essentially restarted, so
        // the current temperature is also changed
        this.currentTemp = origTemp;
    }

    public synchronized void setTempRate(double tempRate) {
        this.tempRate = tempRate;
    }

    public synchronized void setExternalWeather(double externalWeather) {
        this.externalWeather = externalWeather;
    }

    public synchronized void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public synchronized void setTempLower(double tempLower) {
        this.tempLower = tempLower;
    }

    public synchronized void setTempUpper(double tempUpper) {
        this.tempUpper = tempUpper;
    }

    public synchronized void setSampleRate(double sampleRate) {
        this.sampleRate = sampleRate;
    }

    /*

        Get Methods

     */

    public synchronized double getEnvStatus() {
        return envStatus;
    }

    public synchronized double getFurnaceStatus() {
        return furnaceStatus;
    }

    public synchronized double getAirConStatus() {
        return airConStatus;
    }

    public synchronized double getOrigTemp() {
        return origTemp;
    }

    public synchronized double getTempRate() {
        return tempRate;
    }

    public synchronized double getExternalWeather() {
        return externalWeather;
    }

    public synchronized double getCurrentTemp() {
        return currentTemp;
    }

    public synchronized double getTempLower() {
        return tempLower;
    }

    public synchronized double getTempUpper() {
        return tempUpper;
    }

    public synchronized double getSampleRate() {
        return sampleRate;
    }
}