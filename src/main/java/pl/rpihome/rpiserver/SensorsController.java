package pl.rpihome.rpiserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rpihome.rpiserver.PI4J.AnalogSensors;
import pl.rpihome.rpiserver.PI4J.Sensors;
import pl.rpihome.rpiserver.PI4J.StepperMotor;

import java.util.HashMap;

import static java.lang.Math.round;

@RestController
public class SensorsController {
    private Sensors sensors;
    private AnalogSensors analogSensors;
    private int BlindPosition = 5;
    private Led led;

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

    @RequestMapping("/readHumidity")
    public int chuj5() {
        analogSensors = new AnalogSensors();
        return analogSensors.getValue(2);
    }

    @RequestMapping("/stepperMotorUP")
    public void chuj6() {
        StepperMotor stepperMotor = new StepperMotor("up");
        try {
            stepperMotor.stepper();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BlindPosition--;
    }

    @RequestMapping("/stepperMotorDOWN")
    public void chuj7() {
        StepperMotor stepperMotor = new StepperMotor("down");
        try {
            stepperMotor.stepper();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BlindPosition++;
    }

    @RequestMapping("/stepperMotorGetPosition")
    public int chuj8() {
        return BlindPosition;
    }

    @RequestMapping("/TurnOnLed")
    public void chuj9() {
        led = new Led();
        led.TurnOn();
    }

    @RequestMapping("/TurnOffLed")
    public void chuj10() {
        led.TurnOff();
    }
}
