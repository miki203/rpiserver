package pl.rpihome.rpiserver.PI4J;

import com.pi4j.component.motor.impl.GpioStepperMotorComponent;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class StepperMotor {

    public void stepper(String up_down) {
        System.out.println("Stepper Motor started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pins #00 to #03 as output pins and ensure in LOW state
        final GpioPinDigitalOutput[] pins = {
                gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, PinState.LOW),
                gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, PinState.LOW),
                gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW),
                gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, PinState.LOW)};

        // this will ensure that the motor is stopped when the program terminates
        gpio.setShutdownOptions(true, PinState.LOW, pins);

        // create motor component
        GpioStepperMotorComponent motor = new GpioStepperMotorComponent(pins);

        // create byte array to demonstrate a single-step sequencing
        // (This is the most basic method, turning on a single electromagnet every time.
        //  This sequence requires the least amount of energy and generates the smoothest movement.)
        byte[] single_step_sequence = new byte[4];
        single_step_sequence[0] = (byte) 0b0001;
        single_step_sequence[1] = (byte) 0b0010;
        single_step_sequence[2] = (byte) 0b0100;
        single_step_sequence[3] = (byte) 0b1000;

        // define stepper parameters before attempting to control motor
        // anything lower than 2 ms does not work for my sample motor using single step sequence
        motor.setStepInterval(2);
        motor.setStepSequence(single_step_sequence);

        // There are 32 steps per revolution on my sample motor, and inside is a ~1/64 reduction gear set.
        // Gear reduction is actually: (32/9)/(22/11)x(26/9)x(31/10)=63.683950617
        // This means is that there are really 32*63.683950617 steps per revolution =  2037.88641975 ~ 2038 steps!
        motor.setStepsPerRevolution(2038);

        if (up_down == "down") {
            System.out.println("   Motor FORWARD for 2 seconds.");
            motor.forward(2000);
        } else if (up_down == "up") {
            System.out.println("   Motor REVERSE for 2 seconds.");
            motor.reverse(2000);
        }

        // test motor control : ROTATE FORWARD with different timing and sequence
//        System.out.println("   Motor FORWARD with slower speed and higher torque for 1 revolution.");
//        motor.setStepSequence(double_step_sequence);
//        motor.setStepInterval(10);
//        motor.rotate(1);
        System.out.println("   Motor STOPPED.");

        // final stop to ensure no motor activity
        motor.stop();

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();
        gpio.unprovisionPin(pins[0]);
        gpio.unprovisionPin(pins[1]);
        gpio.unprovisionPin(pins[2]);
        gpio.unprovisionPin(pins[3]);

        System.out.println("Exiting StepperMotor");
    }

}

