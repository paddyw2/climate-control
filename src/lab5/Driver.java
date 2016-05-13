package lab5;

import lab5.Humidity.HumidModel;
import lab5.Humidity.HumidityController;
import lab5.Moisture.MoistureController;
import lab5.Moisture.MoistureModel;
import lab5.Temperature.TempController;
import lab5.Temperature.TempModel;

/**
 * <h1>Driver Class</h1>
 *
 * Creates all models, controllers, the main view, and starts application.
 *
 * @author  Patrick Withams
 * @version 1.0
 * @since   2016-04-08
 */
public class Driver {

    public static void main(String[] args)
    {
        // create main view
        MainView mainView = new MainView();

        // create models
        TempModel tempModel = new TempModel();
        HumidModel humidModel = new HumidModel();
        MoistureModel moistureModel = new MoistureModel();

        // create controllers
        TempController tempController = new TempController(mainView, tempModel);
        HumidityController humidityController = new HumidityController(mainView, humidModel);
        MoistureController moistureController = new MoistureController(mainView, moistureModel);

        // pass to main controller
        Controller controller = new Controller(tempController, humidityController, moistureController, mainView);

        mainView.setVisible(true);
    }

}
