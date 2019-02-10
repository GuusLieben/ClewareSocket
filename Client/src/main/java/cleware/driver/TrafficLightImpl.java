package cleware.driver;

import com.codeminders.hidapi.*;
import org.apache.commons.lang.*;
import org.slf4j.*;

import java.io.*;

/** @author exludit */
public class TrafficLightImpl implements TrafficLight {

  private static final Logger LOGGER = LoggerFactory.getLogger(TrafficLightImpl.class);

  private static final String SWITCH_ON_ERROR = "Led {} could not be switched on";
  private static final String SWITCH_OFF_ERROR = "Led {} could not be switched off";

  private final HIDManager hidManager;
  private final HIDDevice hidDevice;

  TrafficLightImpl(HIDManager hidManager, HIDDevice hidDevice) {
    this.hidManager = hidManager;
    this.hidDevice = hidDevice;
  }

  @Override
  public boolean switchOn(Led led) {
    try {
      byte[] writeBuffer = createSwitchOnBuffer(led);
      int writtenBytes = hidDevice.write(writeBuffer);

      Validate.isTrue(
          writtenBytes == writeBuffer.length, "Not all bytes from the were written to usb");
      LOGGER.debug("Switch on '{}' Led", led.name());
      return true;
    } catch (IOException e) {
      LOGGER.error(SWITCH_ON_ERROR, led.name());
      throw new TrafficLightException(e);
    }
  }

  @Override
  public boolean switchOff(Led led) {
    try {
      byte[] writeBuffer = createSwitchOffBuffer(led);
      int writtenBytes = hidDevice.write(writeBuffer);
      Validate.isTrue(
          writtenBytes == writeBuffer.length, "Not all bytes from the were written to usb");
      LOGGER.debug("Switch off '{}' Led", led.name());
      return true;
    } catch (IOException e) {
      LOGGER.error(SWITCH_OFF_ERROR, led.name());
      throw new TrafficLightException(e);
    }
  }

  public LedState readState(Led led) {
    try {
      byte[] readBuffer = new byte[1];
      hidDevice.read(readBuffer);
      int mod = -(readBuffer[0]) % 97;
      return ((mod & led.getBitwise()) == 0) ? LedState.ON : LedState.OFF;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return LedState.OFF;
  }

  @Override
  public boolean toggleLed(Led led) {
    return readState(led) == LedState.ON ? switchOff(led) : switchOn(led);
  }

  @Override
  public boolean switchOnAllLeds() {
    for (Led led : Led.values()) switchOn(led);
    return true;
  }

  @Override
  public boolean switchOffAllLeds() {
    for (Led led : Led.values()) switchOff(led);
    return true;
  }

  private byte[] createSwitchOnBuffer(Led led) {
    return new byte[] {(byte) 0x0, (byte) 0x0, led.getAddress(), (byte) 0x1};
  }

  private byte[] createSwitchOffBuffer(Led led) {
    return new byte[] {(byte) 0x0, (byte) 0x0, led.getAddress(), (byte) 0x0};
  }

  @Override
  public void close() {
    try {
      hidDevice.close();
      hidManager.release();
    } catch (IOException e) {
      LOGGER.error("USB Device could not be closed");
      throw new TrafficLightException(e);
    }
  }
}
