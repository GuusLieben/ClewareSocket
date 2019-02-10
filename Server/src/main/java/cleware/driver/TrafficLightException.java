package cleware.driver;

/** @author exludit */
public class TrafficLightException extends RuntimeException {

  public TrafficLightException(String message, Throwable cause) {
    super(message, cause);
  }

  public TrafficLightException(Throwable cause) {
    super(cause);
  }
}
