import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Utils {

    public void openConfig() {
        String pathToConfig = "neuralconfig.cfg";
        File config = new File(pathToConfig);
        BufferedReader br = null;

        if (!config.exists()) {
            // TODO: create a new config file
        }

        try {
            br = new BufferedReader(new FileReader(config));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }









}
