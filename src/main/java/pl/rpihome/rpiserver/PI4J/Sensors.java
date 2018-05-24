package pl.rpihome.rpiserver.PI4J;

import pl.rpihome.rpiserver.PI4J.MotionSensor.PirMotionDetection;

public class Sensors {
    private PirMotionDetection pirMotionDetection;
    private AnalogSensors analogSensors;
    private int BlindPosition = 5;
    private Led1 led1;
    private Led2 led2;
    private StepperMotor stepperMotor;
    private int motionStatus;
    private int led1status;
    private int led2status;

    public Sensors() {
        try {
            pirMotionDetection = new PirMotionDetection();
            //   pirMotionDetection.detectMotionAndGlowLED();
            analogSensors = new AnalogSensors();
            stepperMotor = new StepperMotor();
        } catch (UnsatisfiedLinkError | Exception e) {
            System.err.println("platform does not support this driver");
        }
    }

    public void enableMotionSensor() {
        motionStatus = 1;
        new Thread(() -> pirMotionDetection.detectMotionAndGlowLED()).start();
    }

    public void disableMotionSensor() {
        motionStatus = 0;
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

        stepperMotor.stepper("up");
        BlindPosition--;

    }

    public void stepperMotorDOWN() {

        stepperMotor.stepper("down");
        BlindPosition++;

    }

    public int stepperMotorGetPosition() {
        return BlindPosition;
    }

    public void TurnOnLed1() {

        if (led1 == null) {
            led1status = 1;
            led1 = new Led1();
            led1.TurnOn();
        }

    }

    public void TurnOffLed1() {

        if (led1 != null) {
            led1status = 0;
            led1.TurnOff();
            led1 = null;
        }

    }

    public void TurnOnLed2() {

        if (led1 == null) {
            led2status = 1;
            led2 = new Led2();
            led2.TurnOn();
        }

    }

    public void TurnOffLed2() {

        if (led2 != null) {
            led2status = 0;
            led2.TurnOff();
            led2=null;
        }

    }

    public int isMotionStatus() {
        return motionStatus;
    }

    public int getLed1status() {
        return led1status;
    }

    public int getLed2status() {
        return led2status;
    }
}
