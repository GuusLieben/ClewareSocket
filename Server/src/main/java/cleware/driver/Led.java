package cleware.driver;

import cleware.*;

/** @author exludit */
public enum Led {
  RED((byte) 0x10, 1),
  YELLOW((byte) 0x11, 4),
  GREEN((byte) 0x12, 16);

  private byte address;
  private int bitwise;

  Led(byte address, int bitwise) {
    this.address = address;
    this.bitwise = bitwise;
  }

  byte getAddress() {
    return address;
  }

  public int getBitwise() {
    return bitwise;
  }

  public static Led valueOfIgnoreCaseOrNull(String name) {
    for (Led led : Led.values()) {
      if (led.name().equalsIgnoreCase(name)) {
        return led;
      }
    }
    return null;
  }
}
