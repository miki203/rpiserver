package pl.rpihome.rpiserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rpihome.rpiserver.PI4J.*;

import java.util.HashMap;

@RestController
public class SensorsController {
    private Sensors sensors;

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
        if (sensors == null)
            sensors = new Sensors();

        new Thread(() -> sensors.enableMotionSensor()).start();
    }

    @RequestMapping("/disableMotionSensor")
    public void chuj2() {
        if (sensors != null)
            new Thread(() -> sensors.disableMotionSensor()).start();
    }

    @RequestMapping("/readLight")
    public int chuj3() {
        if (sensors == null)
            sensors = new Sensors();

        final int[] tmp = new int[1];
        Thread thread = new Thread(() -> tmp[0] = sensors.readLight());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tmp[0];
    }

    @RequestMapping("/readTemperature")
    public int chuj4() {
        if (sensors == null)
            sensors = new Sensors();

        final int[] tmp = new int[1];
        Thread thread = new Thread(() -> tmp[0] = sensors.readTemperature());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tmp[0];
    }

    @RequestMapping("/readHumidity")
    public int chuj5() {
        if (sensors == null)
            sensors = new Sensors();

        final int[] tmp = new int[1];
        Thread thread = new Thread(() -> tmp[0] = sensors.readHumidity());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tmp[0];
    }

    @RequestMapping("/stepperMotorUP")
    public void chuj6() {
        if (sensors == null)
            sensors = new Sensors();

        new Thread(() -> sensors.stepperMotorUP()).start();
    }

    @RequestMapping("/stepperMotorDOWN")
    public void chuj7() {
        if (sensors == null)
            sensors = new Sensors();

        new Thread(() -> sensors.stepperMotorDOWN()).start();
    }

    @RequestMapping("/stepperMotorGetPosition")
    public int chuj8() {
        if (sensors == null)
            sensors = new Sensors();

        final int[] tmp = new int[1];
        Thread thread = new Thread(() -> tmp[0] = sensors.stepperMotorGetPosition());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tmp[0];
    }

    @RequestMapping("/ChangeOfStateLed1")
    public void chuj9() {
        if (sensors == null)
            sensors = new Sensors();

        new Thread(() -> sensors.ChangeOfStateLed1()).start();
    }

    @RequestMapping("/ChangeOfStateLed2")
    public void chuj10() {
        if (sensors == null)
            sensors = new Sensors();

        new Thread(() -> sensors.ChangeOfStateLed2()).start();
    }

    @RequestMapping("/GetMotionStatus")
    public int chuj13() {
        if (sensors == null)
            sensors = new Sensors();

        final int[] tmp = new int[1];
        Thread thread = new Thread(() -> tmp[0] = sensors.isMotionStatus());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tmp[0];
    }

    @RequestMapping("/GetLed1status")
    public int chuj14() {
        if (sensors == null)
            sensors = new Sensors();

        final int[] tmp = new int[1];
        Thread thread = new Thread(() -> tmp[0] = sensors.getLed1status());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tmp[0];
    }

    @RequestMapping("/GetLed2status")
    public int chuj15() {
        if (sensors == null)
            sensors = new Sensors();

        final int[] tmp = new int[1];
        Thread thread = new Thread(() -> tmp[0] = sensors.getLed2status());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tmp[0];
    }

}
