package pl.rpihome.rpiserver.PI4J;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;

import java.io.IOException;

public class AnalogSensors {

    // SPI device
    static SpiDevice spi = null;
    int conversion_value;

    public int getValue(int channel) {

        try {
            spi = SpiFactory.getInstance(SpiChannel.CS0,
                    SpiDevice.DEFAULT_SPI_SPEED, // default spi speed 1 MHz
                    SpiDevice.DEFAULT_SPI_MODE); // default spi mode 0

            conversion_value = getConversionValue(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conversion_value;
    }

    public static int getConversionValue(int channel) throws IOException {

        // create a data buffer and initialize a conversion request payload
        byte data[] = new byte[]{
                (byte) 0b00000001,                              // first byte, start bit
                (byte) (0b10000000 | (((channel & 7) << 4))),    // second byte transmitted -> (SGL/DIF = 1, D2=D1=D0=0)
                (byte) 0b00000000                               // third byte transmitted....don't care
        };

        // send conversion request to ADC chip via SPI channel
        byte[] result = spi.write(data);

        // calculate and return conversion value from result bytes
        int value = (result[1] << 8) & 0b1100000000; //merge data[1] & data[2] to get 10-bit result
        value |= (result[2] & 0xff);
        return value;
    }
}