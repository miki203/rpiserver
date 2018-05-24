package pl.rpihome.rpiserver.PI4J;

import com.pi4j.io.gpio.*;

public class Led {

    private GpioController gpio;
    private GpioPinDigitalOutput ledPin[] = new GpioPinDigitalOutput[2];

    public Led() {
        gpio = GpioFactory.getInstance();
        ledPin[0] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);
        ledPin[1] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
    }

    public void ChangeOfState(int nr) {
        if (ledPin[nr].isHigh()) {
            ledPin[nr].low();
        } else if (ledPin[nr].isLow()) {
            ledPin[nr].high();
        }
    }

}
