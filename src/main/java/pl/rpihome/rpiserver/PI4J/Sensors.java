package pl.rpihome.rpiserver.PI4J;

import pl.rpihome.rpiserver.PI4J.MotionSensor.PirMotionDetection;

public class Sensors {
    private PirMotionDetection pirMotionDetection;
    private AnalogSensors analogSensors;
    private int BlindPosition = 5;
    private Led led;
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
        pirMotionDetection.detectMotionAndGlowLED();
    }

    public void disableMotionSensor() {
        motionStatus = 0;
        pirMotionDetection.disableMotionSensor();
    }

    public int readLight() {
        int odczytLight = analogSensors.getValue(0);
        int procentReversed = odczytLight * 100 / 1023; // zakres 0-1023
        return 100 - procentReversed;
    }

    public int readTemperature() {
        int odczytTemp = analogSensors.getValue(1);
        double volt = (odczytTemp * 5.0) / 1024.0;
        double temp = -((volt - 0.5) * 100);
        return (int) temp;
    }

    public int readHumidity() {
        int odczytHum = analogSensors.getValue(2);
        int procentReversed = odczytHum * 100 / 1023; // zakres 0-1023
        return 100 - procentReversed; // zakres 0-1023
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

    public void ChangeOfStateLed1() {

        if (led == null) {
            led = new Led();
            led.ChangeOfState(0);
        } else led.ChangeOfState(0);

        if (led1status == 0)
            led1status = 1;
        else if (led1status == 1)
            led1status = 0;
    }

    public void ChangeOfStateLed2() {

        if (led == null) {
            led = new Led();
            led.ChangeOfState(1);
        } else led.ChangeOfState(1);

        if (led2status == 0)
            led2status = 1;
        else if (led2status == 1)
            led2status = 0;
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
