package pl.rpihome.rpiserver.PI4J;

import org.springframework.stereotype.Component;
import pl.rpihome.rpiserver.PI4J.MotionSensor.PirMotionDetection;

public class Sensors {
    private PirMotionDetection pirMotionDetection;
    private AnalogSensors analogSensors;
    private int BlindPosition = 5;
    private Led1 led1;
    private Led2 led2;
    private StepperMotor stepperMotor;

    public Sensors() {
        try {
            pirMotionDetection = new PirMotionDetection();
            //   pirMotionDetection.detectMotionAndGlowLED();
            analogSensors = new AnalogSensors();
            stepperMotor = new StepperMotor();
        } catch (UnsatisfiedLinkError e) {
            System.err.println("platform does not support this driver");
        } catch (Exception e) {
            System.err.println("platform does not support this driver");

        }
    }

    public void enableMotionSensor() {
        new Thread(() -> pirMotionDetection.detectMotionAndGlowLED()).start();
    }

    public void disableMotionSensor() {
        pirMotionDetection.disableMotionSensor();
    }

    public int readLight() {
        return analogSensors.getValue(0);
    }

    public int readTemperature() {
        int odczyt = analogSensors.getValue(1);
        double volt = (odczyt * 5.0) / 1024.0;
        double temp = -((volt - 0.5) * 100);
        return (int) temp;
    }

    public int readHumidity() {
        return analogSensors.getValue(2);
    }

    public void stepperMotorUP() {
        new Thread(() -> {
            stepperMotor.stepper("up");
            BlindPosition--;
        }).start();
    }

    public void stepperMotorDOWN() {
        new Thread(() -> {
            stepperMotor.stepper("down");
            BlindPosition++;
        }).start();
    }

    public int stepperMotorGetPosition() {
        return BlindPosition;
    }

    public void TurnOnLed1() {
        new Thread(() -> {
            if (led1 == null) {
                led1 = new Led1();
                led1.TurnOn();
            }
        }).start();
    }

    public void TurnOffLed1() {
        new Thread(() -> {
            if (led1 != null) {
                led1.TurnOff();
                led1 = null;
            }
        }).start();
    }

    public void TurnOnLed2() {
        new Thread(() -> {
            if (led2 == null) {
                led2 = new Led2();
                led2.TurnOn();
            }
        }).start();
    }

    public void TurnOffLed2() {
        new Thread(() -> {
            if (led2 != null) {
                led2.TurnOff();
                led2 = null;
            }
        }).start();
    }
}
