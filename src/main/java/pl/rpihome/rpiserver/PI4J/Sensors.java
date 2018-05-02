package pl.rpihome.rpiserver.PI4J;

import org.springframework.stereotype.Component;
import pl.rpihome.rpiserver.PI4J.MotionSensor.PirMotionDetection;

public class Sensors {
    private PirMotionDetection pirMotionDetection;

    public Sensors() {
        try {
            pirMotionDetection = new PirMotionDetection();
            pirMotionDetection.detectMotionAndGlowLED();
        } catch (UnsatisfiedLinkError e) {
            System.err.println("platform does not support this driver");
        } catch (Exception e) {
            System.err.println("platform does not support this driver");

        }
    }

    public void enableMotionSensor() {
        pirMotionDetection.detectMotionAndGlowLED();
    }

    public void disableMotionSensor() {
        pirMotionDetection.disableMotionSensor();
    }

}
