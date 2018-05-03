package pl.rpihome.rpiserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rpihome.rpiserver.PI4J.AnalogSensors;
import pl.rpihome.rpiserver.PI4J.Sensors;

import java.util.HashMap;

import static java.lang.Math.round;

@RestController
public class SensorsController {
    private Sensors sensors;
    private AnalogSensors analogSensors;

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
        analogSensors = new AnalogSensors();
        return analogSensors.getValue(0);
    }

    @RequestMapping("/readTemperature")
    public double chuj4() {
        analogSensors = new AnalogSensors();
        int odczyt = analogSensors.getValue(1);
        double volt = (odczyt * 5.0) / 1024.0;
        double temp = -((volt - 0.5) * 100);
        return round(temp);
    }


}
