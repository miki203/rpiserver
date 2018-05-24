package pl.rpihome.rpiserver.PI4J;

import com.pi4j.io.gpio.*;

public class Led2 {

    private GpioController gpio;
    private GpioPinDigitalOutput ledPin;

    public void TurnOn() {
        gpio = GpioFactory.getInstance();
        ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, PinState.HIGH);
    }

    public void TurnOff() {
        ledPin.low();
        gpio.shutdown();
        gpio.unprovisionPin(ledPin);
    }
}