package pl.rpihome.rpiserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rpihome.rpiserver.PI4J.*;

import java.util.HashMap;

import static java.lang.Math.round;

@RestController
public class SensorsController {
    private Sensors sensors;
    private AnalogSensors analogSensors;
    private int BlindPosition = 5;
    private Led1 led1;
    private Led2 led2;

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
        if (sensors == null) {
            sensors = new Sensors();
            sensors.enableMotionSensor();
        } else sensors.enableMotionSensor();
    }

    @RequestMapping("/disableMotionSensor")
    public void chuj2() {
        if (sensors != null) sensors.disableMotionSensor();
    }

    @RequestMapping("/readLight")
    public int chuj3() {
        if (sensors == null) {
            sensors = new Sensors();
            return sensors.readLight();
        } else return sensors.readLight();
    }

    @RequestMapping("/readTemperature")
    public int chuj4() {
        if (sensors == null) {
            sensors = new Sensors();
            return sensors.readTemperature();
        } else return sensors.readTemperature();
        //        analogSensors = new AnalogSensors();
//        int odczyt = analogSensors.getValue(1);
//        double volt = (odczyt * 5.0) / 1024.0;
//        double temp = -((volt - 0.5) * 100);
//        return round(temp);
    }

    @RequestMapping("/readHumidity")
    public int chuj5() {
        if (sensors == null) {
            sensors = new Sensors();
            return sensors.readHumidity();
        } else return sensors.readHumidity();
    }

    @RequestMapping("/stepperMotorUP")
    public void chuj6() {
        if (sensors == null) {
            sensors = new Sensors();
            sensors.stepperMotorUP();
        } else sensors.stepperMotorUP();
    }

    @RequestMapping("/stepperMotorDOWN")
    public void chuj7() {
        if (sensors == null) {
            sensors = new Sensors();
            sensors.stepperMotorDOWN();
        } else sensors.stepperMotorDOWN();
    }

    @RequestMapping("/stepperMotorGetPosition")
    public int chuj8() {
        if (sensors == null) {
            sensors = new Sensors();
            return sensors.stepperMotorGetPosition();
        } else return sensors.stepperMotorGetPosition();
    }

    @RequestMapping("/TurnOnLed1")
    public void chuj9() {
        if (sensors == null) {
            sensors = new Sensors();
            sensors.TurnOnLed1();
        } else sensors.TurnOnLed1();
    }

    @RequestMapping("/TurnOffLed1")
    public void chuj10() {
        if (sensors == null) {
            sensors = new Sensors();
            sensors.TurnOffLed1();
        } else sensors.TurnOffLed1();
    }

    @RequestMapping("/TurnOnLed2")
    public void chuj11() {
        if (sensors == null) {
            sensors = new Sensors();
            sensors.TurnOnLed2();
        } else sensors.TurnOnLed2();
    }

    @RequestMapping("/TurnOffLed2")
    public void chuj12() {
        if (sensors == null) {
            sensors = new Sensors();
            sensors.TurnOffLed2();
        } else sensors.TurnOffLed2();
    }
}
