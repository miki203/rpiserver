package pl.rpihome.rpiserver.PI4J;

import com.pi4j.io.gpio.*;

public class Led1 {

    private GpioController gpio;
    private GpioPinDigitalOutput ledPin;

    public void TurnOn() {
        gpio = GpioFactory.getInstance();
        ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, PinState.HIGH);
    }

    public void TurnOff() {
        ledPin.low();
        gpio.shutdown();
        gpio.unprovisionPin(ledPin);
    }


}
