package pl.rpihome.rpiserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rpihome.rpiserver.PI4J.SensorPhotoresistor;
import pl.rpihome.rpiserver.PI4J.Sensors;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class SensorsController {
    private Sensors sensors;
    private SensorPhotoresistor sensorPhotoresistor;

    @RequestMapping("/")
    public String greeting() {
        return "Halo dziwki!!";
    }

    @RequestMapping("/getMotionSensor")
    public HashMap<String, String> getMotionSensor() {
        HashMap<String, String> testmap = new HashMap<>();
        testmap.put("klucz3", "wartosc3");
        testmap.put("klucz2", "wartosc2");
        testmap.put("klucz1", "wartosc1");
        return testmap;
    }

    @RequestMapping("/enableMotionSensor")
    public void chuj1() {
        if (sensors == null) sensors = new Sensors();
        else sensors.enableMotionSensor();
    }

    @RequestMapping("/disableMotionSensor")
    public void chuj2() {
        if (sensors != null) sensors.disableMotionSensor();
    }

    @RequestMapping("/readLight")
    public int chuj3() {
        sensorPhotoresistor = new SensorPhotoresistor();
        return sensorPhotoresistor.Photoresistor();
    }

    @RequestMapping("/readLight2")
    public int chuj4() {
        return 5;
    }


}
