package pl.rpihome.rpiserver.PI4J.MotionSensor;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.GpioUtil;
import org.apache.tomcat.jni.Proc;
import org.omg.SendingContext.RunTime;

import java.io.IOException;

public class PirMotionDetection {
    private GpioPinDigitalInput pirMotionsensor;
    final GpioController gpioPIRMotionSensor;
    public PirMotionDetection() {
        //Create gpio controller for PIR Motion Sensor listening on the pin GPIO_04
        gpioPIRMotionSensor = GpioFactory.getInstance();
        pirMotionsensor = gpioPIRMotionSensor.provisionDigitalInputPin(RaspiPin.GPIO_07, PinPullResistance.PULL_DOWN);

    }

    public void detectMotionAndGlowLED() {
        System.out.println("Starting Pi4J Motion Sensor Example");
        System.out.println("PIR Motion Sensor is ready and looking for any movement..");

        //This is required to enable Non Privileged Access to avoid applying sudo to run Pi4j programs
        GpioUtil.enableNonPrivilegedAccess();

        //Create and register gpio pin listener on PIRMotion Sensor GPIO Input instance
        pirMotionsensor.addListener((GpioPinListenerDigital) event -> {
            //if the event state is High then print "Intruder Detected" and turn the LED ON by invoking the high() method
            if (event.getState().isHigh()) {
                System.out.println("Intruder Detected!");
                try {
                    Process runtime = Runtime.getRuntime().exec("raspistill -o plik.jpg");
                    runtime.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //if the event state is Low then print "All is quiet.." and make the LED OFF by invoking the low() method
            if (event.getState().isLow()) {
                System.out.println("All is quiet...");
            }
        });

    }

    public void disableMotionSensor() {
        pirMotionsensor.removeAllListeners();
    }
}