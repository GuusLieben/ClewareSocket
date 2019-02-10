package cleware.driver;

/** @author exludit */
public interface TrafficLight {
  boolean switchOn(Led led);

  boolean switchOff(Led led);

  boolean toggleLed(Led led);

  LedState readState(Led led);

  boolean switchOnAllLeds();

  boolean switchOffAllLeds();

  void close();
}
